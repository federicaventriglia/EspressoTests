package com.knirirr.beecount;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteException;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.EditText;

import com.knirirr.beecount.database.Alert;
import com.knirirr.beecount.database.AlertDataSource;
import com.knirirr.beecount.database.Count;
import com.knirirr.beecount.database.CountDataSource;
import com.knirirr.beecount.database.Link;
import com.knirirr.beecount.database.LinkDataSource;
import com.knirirr.beecount.database.Project;
import com.knirirr.beecount.database.ProjectDataSource;
import com.knirirr.beecount.widgets.CountingWidget;
import com.knirirr.beecount.widgets.NotesWidget;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CountingActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener
{
  private static String TAG = "BeeCountCounting";
  private AlertDialog.Builder row_alert;
  private AlertDialog.Builder loop_alert;
  BeeCountApplication beeCount;
  SharedPreferences prefs;
  long project_id;
  LinearLayout count_area;
  LinearLayout notes_area;

  // preferences
  private boolean toastPref;
  private boolean awakePref;
  private boolean fontPref;
  private boolean soundPref;
  private boolean buttonSoundPref;
  private boolean negPref;
  private boolean loopStop;
  private String alertSound;
  private String buttonAlertSound;
  private String buttonAlertDownSound;
  private PowerManager.WakeLock wl;

  // the actual data
  Project project;
  List<Count> counts;
  List<Alert> alerts;
  List<Link> links;
  List<Long> already_counted;

  List<CountingWidget> countingWidgets;

  private ProjectDataSource projectDataSource;
  private CountDataSource countDataSource;
  private AlertDataSource alertDataSource;
  private LinkDataSource linkDataSource;

  private Ringtone countUpAlert;
  private Ringtone countDownAlert;
  private Ringtone alertAlert;



  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_counting);

    Bundle extras = getIntent().getExtras();
    if(extras !=null)
    {
      project_id = extras.getLong("project_id");
    }

    projectDataSource = new ProjectDataSource(this);
    countDataSource = new CountDataSource(this);
    alertDataSource = new AlertDataSource(this);
    linkDataSource = new LinkDataSource(this);
    already_counted = new ArrayList<Long>();

    beeCount = (BeeCountApplication) getApplication();
    //project_id = beeCount.project_id;
    prefs = BeeCountApplication.getPrefs();
    prefs.registerOnSharedPreferenceChangeListener(this);
    getPrefs();

    alertAlert = prepareSound(alertSound);
    countDownAlert = prepareSound(buttonAlertDownSound);
    countUpAlert = prepareSound(buttonAlertSound);

    ScrollView counting_screen = (ScrollView) findViewById(R.id.countingScreen);
    counting_screen.setBackgroundDrawable(beeCount.getBackground());

    count_area = (LinearLayout) findViewById(R.id.countCountLayout);
    notes_area = (LinearLayout) findViewById(R.id.countNotesLayout);

    if (awakePref == true)
    {
      PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
      wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotDimScreen");
    }

  }

  /*
   * So preferences can be loaded at the start, and also when a change is detected.
   */
  private void getPrefs()
  {
    toastPref = prefs.getBoolean("toast_away", false);
    awakePref = prefs.getBoolean("pref_awake", true);
    fontPref = prefs.getBoolean("pref_note_font", false);
    soundPref = prefs.getBoolean("pref_sound",false);
    negPref = prefs.getBoolean("pref_negative",false);
    alertSound = prefs.getString("alert_sound",null);
    buttonSoundPref = prefs.getBoolean("pref_button_sound",false);
    buttonAlertSound = prefs.getString("alert_button_sound",null);
    buttonAlertDownSound = prefs.getString("alert_button_down_sound",null);
    if (buttonAlertDownSound == null) {
      buttonAlertDownSound = buttonAlertSound;
    }
    loopStop = prefs.getBoolean("pref_loopstop",true);
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    // clear any existing views
    count_area.removeAllViews();
    notes_area.removeAllViews();

    // setup the data sources
    projectDataSource.open();
    countDataSource.open();
    alertDataSource.open();
    linkDataSource.open();

    // load the data
    // projects
    Log.i(TAG,"Project ID: " + String.valueOf(project_id));
    try
    {
      project = projectDataSource.getProject(project_id);
    }
    catch (CursorIndexOutOfBoundsException e)
    {
      Log.e(TAG, "Problem loading project: " + e.toString());
      Toast.makeText(CountingActivity.this, getString(R.string.getHelp), Toast.LENGTH_LONG).show();
      finish();
    }

    Log.i(TAG, "Got project: " + project.name);
    getSupportActionBar().setTitle(project.name);
    List<String> extras = new ArrayList<String>();

    // counts
    countingWidgets = new ArrayList<CountingWidget>();
    counts = countDataSource.getAllCountsForProject(project.id);

    // display all the counts by adding them to countCountLayout
    alerts = new ArrayList<Alert>();
    for (Count count : counts)
    {
      CountingWidget widget = new CountingWidget(this,null);
      widget.setCount(count);
      widget.setFont();
      countingWidgets.add(widget);
      count_area.addView(widget);
      if (count.auto_reset > 0)
      {
        extras.add(String.format(getString(R.string.willReset), count.name, count.reset_level, count.auto_reset));
      }

      // add a project note widget if there are any notes
      if (StringUtils.isNotBlank(count.notes))
      {
        NotesWidget count_notes = new NotesWidget(this, null);
        count_notes.setNotes(count.notes);
        count_notes.setFont(fontPref);
        count_area.addView(count_notes);
      }

      // get add all alerts for this project
      List<Alert> tmpAlerts = alertDataSource.getAllAlertsForCount(count.id);
      for (Alert a : tmpAlerts)
      {
        alerts.add(a);
        extras.add(String.format(getString(R.string.willAlert), count.name, a.alert));
      }
    }
    /*
     * A crash here is a mystery, and users should seek further assistance.
     */
    links = new ArrayList<Link>();
    try
    {
      links = linkDataSource.getAllLinksForProject(project_id);
    }
    catch (SQLiteException e)
    {
      Toast.makeText(CountingActivity.this, getString(R.string.getHelp), Toast.LENGTH_LONG).show();
      finish();
    }


    // display summary of links; resets and alerts should already have
    // been dealt with during setup, above
    for (Link l : links)
    {
      String master;
      String slave;
      /*
       * Sometimes it seems that links are not being deleted at the same time as counts.
       * Therefore, the try/catch blocks below deal with this problem by deleting those links.
       */
      try
      {
          slave = getCountFromId(l.slave_id).count.name;
      }
      catch (Exception e)
      {
        Log.e(TAG, "Would have crashed due to not being able to find a slave count; deleting.");
        linkDataSource.deleteLink(l);
        return;
      }
      try
      {
          master = getCountFromId(l.master_id).count.name;
      }
      catch (Exception e)
      {
        Log.e(TAG, "Would have crashed due to not being able to find a master count; deleting.");
        linkDataSource.deleteLink(l);
        return;
      }

      if (l.type == 0)
      {
        extras.add(String.format(getString(R.string.willLinkReset), master, slave, l.increment));
      }
      else if (l.type == 1)
      {
        extras.add(String.format(getString(R.string.willLinkIncrease), master, slave, l.increment));
      }
      else if (l.type == 2)
      {
        extras.add(String.format(getString(R.string.willLinkDecrease), master, slave, l.increment));
      }
    }
    if (!extras.isEmpty())
    {
      NotesWidget extra_notes = new NotesWidget(this,null);
      extra_notes.setNotes(StringUtils.join(extras,"\n"));
      notes_area.addView(extra_notes);
    }

    // display project notes
    // moved to bottom so it doesn't look like a count note
    if (project.notes != null)
    {
      if (!project.notes.isEmpty())
      {
        NotesWidget project_notes = new NotesWidget(this, null);
        project_notes.setNotes(project.notes);
        project_notes.setFont(fontPref);
        notes_area.addView(project_notes);
      }
    }

    // finally, check to see if the screen should be kept on whilst counting
    if (awakePref == true)
    {
      try
      {
        wl.acquire();
      }
      catch (Exception e)
      {
        Log.e(TAG, "Couldn't acquire wakelock: " + e.toString());
      }
    }

  }

  @Override
  protected void onPause()
  {
    super.onPause();

    // save the data
    saveData();
    // save project id in case it is lost on pause
    SharedPreferences.Editor editor = prefs.edit();
    editor.putLong("pref_project_id", project_id);
    editor.commit();

    // close the data sources
    projectDataSource.close();
    countDataSource.close();
    alertDataSource.close();
    linkDataSource.close();


    // N.B. a wakelock might not be held, e.g. if someone is using Cyanogenmod and
    // has denied wakelock permission to BeeCount
    if (awakePref == true)
    {
      if (wl.isHeld())
      {
        try
        {
          wl.release();
        }
        catch (Exception e)
        {
          Log.e(TAG, "Couldn't release wakelock: " + e.toString());
        }
      }
    }

  }

  private void saveData()
  {
    Toast.makeText(CountingActivity.this, getString(R.string.projSaving) + " " + project.name + "!", Toast.LENGTH_SHORT).show();
    for (Count count : counts)
    {
      countDataSource.saveCount(count);
    }
  }

  public void saveAndExit(View view)
  {
    saveData();
    super.finish();
  }

  public void editProject(View view)
  {
    // some stuff to go here
    //beeCount.project_id = project_id;
    Intent intent = new Intent(CountingActivity.this, EditProjectActivity.class);
    intent.putExtra("project_id",project_id);
    startActivity(intent);
  }

  //**************************************

  /*
   * The next few methods are called from a counting widget or from within one of the methods here,
   * to make sure that many nested counts &c. all work on each other.
   * It may well be the case that this means of identifying which widget to increase/decrease
   * by tagging the buttons with the relevant count ID is a bit crap. Suggestions welcome if so.
   * The countUp and countDown methods are overloaded so they can be called by a view tagged with
   * the id of the count to count, or by supplying that ID directly.
   * N.B. Users may link counts to each other to produce infinite incrementing, so this error
   * must be caught here and an error displayed.
   */
  public void countUp(long count_id)
  {
    CountingWidget widget = getCountFromId(count_id);
    if (widget != null)
    {
      widget.countUp();
    }
    checkAlert(widget.count.id, widget.count.count);
    checkLink(widget.count.id, widget.count.count, true);
    if (widget.count.auto_reset > 0)
      checkReset(widget.count);

  }

  public void countDown(long count_id)
  {
    CountingWidget widget = getCountFromId(count_id);
    if (widget != null)
    {
      widget.countDown();
    }
    checkAlert(widget.count.id, widget.count.count);
    checkLink(widget.count.id, widget.count.count, false);
    if (widget.count.auto_reset > 0)
      checkReset(widget.count);
  }

  /*
   * The functions below are triggered by the buttons; the two above by being triggered during
   * a pass through the checkLink function. already_counted is reset when a button is pressed such
   * that a track of what links are triggered for each press is kept each time.
   */

  public void countUp(View view)
  {
    //Log.i(TAG, "View clicked: " + view.toString());
    //Log.i(TAG, "View tag: " + view.getTag().toString());
    buttonSound();
    if (loopStop)
      already_counted.clear();
    long count_id = Long.valueOf(view.getTag().toString());
    CountingWidget widget = getCountFromId(count_id);
    if (widget != null)
    {
      widget.countUp();
    }
    checkAlert(widget.count.id, widget.count.count);
    checkLink(widget.count.id, widget.count.count, true);
    if (widget.count.auto_reset > 0)
      checkReset(widget.count);


  }

  public void countDown(View view)
  {
    //Log.i(TAG, "View clicked: " + view.toString());
    //Log.i(TAG, "View tag: " + view.getTag().toString());
    buttonDownSound();
    if (loopStop)
      already_counted.clear();
    long count_id = Long.valueOf(view.getTag().toString());
    CountingWidget widget = getCountFromId(count_id);
    if (widget != null)
    {
      widget.countDown();
    }
    checkAlert(widget.count.id, widget.count.count);
    checkLink(widget.count.id, widget.count.count, false);
    if (widget.count.auto_reset > 0)
      checkReset(widget.count);
  }

  public void resetCount(long count_id)
  {
    CountingWidget widget = getCountFromId(count_id);
    widget.resetZero();
    checkAlert(widget.count.id, widget.count.count);
  }

  public void edit(View view)
  {
    long count_id = Long.valueOf(view.getTag().toString());
    Intent intent = new Intent(CountingActivity.this, CountOptionsActivity.class);
    intent.putExtra("count_id",count_id);
    intent.putExtra("project_id",project_id);
    startActivity(intent);
  }

  /*
   * This is the lookup to get a counting widget (with references to the
   * associated count) from the list of widgets.
   */
  public CountingWidget getCountFromId(long id)
  {
    for (CountingWidget widget : countingWidgets)
    {
      if (widget.count.id == id)
      {
        return widget;
      }
    }
    return null;
  }

  //**************************************

  /*
   * Link and alert checking...
   */
  public void checkAlert(long count_id, int count_value)
  {
    for (Alert a : alerts)
    {
      if (a.count_id == count_id && a.alert == count_value)
      {
        row_alert = new AlertDialog.Builder(this);
        row_alert.setTitle(getString(R.string.alertTitle));
        row_alert.setMessage(a.alert_text);
        row_alert.setNegativeButton("OK", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface dialog, int whichButton)
          {
            // Cancelled.
          }
        });
        row_alert.show();
        soundAlert();
        break;
      }
    }
  }

  /*
   * If the user has created a link loop then this should alert them.
   */
  public void linkLoopAlert()
  {
    loop_alert = new AlertDialog.Builder(this);
    loop_alert.setTitle(getString(R.string.alertTitle));
    loop_alert.setMessage(R.string.linkLoop);
    loop_alert.setNegativeButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface dialog, int whichButton)
      {
        // Cancelled.
      }
    });
    loop_alert.show();
  }

  /*
   * If the user has set the preference for an audible alert, then sound it here.
   */
  public void soundAlert()
  {
    if (soundPref)
    {
      try
      {
        alertAlert.play();
      }
      catch (Exception e)
      {
        Log.i(TAG,"Error playing alert sound: " + e.toString());
        e.printStackTrace();
      }
    }
  }

  public void buttonSound()
  {
    if (buttonSoundPref)
    {
      try
      {
        countUpAlert.play();
      }
      catch (Exception e)
      {
        Log.i(TAG,"Error playing count up sound: " + e.toString());
        e.printStackTrace();
      }
    }
  }

  public void buttonDownSound()
  {
    if (soundPref)
    {
      try
      {
        countDownAlert.play();
      }
      catch (Exception e)
      {
        Log.i(TAG,"Error playing count down sound: " + e.toString());
        e.printStackTrace();
      }
    }
  }

  public void checkLink(long count_id, int count_value, boolean up)
  {
    for (Link l : links)
    {
      //Log.i(TAG, "In the good bit: " + String.valueOf(count_value % l.increment));
      if (l.master_id == count_id && (count_value % l.increment == 0) && up)
      {
        /*
         * If the slave has already been seen then this counting cycle should come to an end.
         */
        if (loopStop) {
          if (already_counted.contains(l.slave_id)) {
            linkLoopAlert();
            return;
          }
        }
        already_counted.add(l.slave_id);

        if (l.type == 0) // reset
        {
          resetCount(l.slave_id);
          hasReset(l.master_id,l.slave_id);
        }
        else if (l.type == 1) // increase
        {
          countUp(l.slave_id);
          hasIncreased(l.master_id,l.slave_id);
        }
        else if (l.type == 2) // decrease
        {
          countDown(l.slave_id);
          hasDecreased(l.slave_id,l.master_id);
        }
      }
      else if (l.master_id == count_id && ((count_value + 1) % l.increment == 0) && !up)
      {
        if (l.type == 0) // reset
        {
          resetCount(l.slave_id);
          hasReset(l.master_id,l.slave_id);
        }
        else if (l.type == 1) // increment
        {
          countDown(l.slave_id);
          hasIncreased(l.master_id, l.slave_id);
        }
        else if (l.type == 2) // decrease
        {
          countUp(l.slave_id);
          hasDecreased(l.master_id, l.slave_id);
        }
      }
    }
  }

  // resetting might as well call the
  public void checkReset(Count count)
  {
    if (count.auto_reset == count.count)
    {
      resetCount(count.id);
      if (toastPref == false)
      {
        Toast.makeText(CountingActivity.this, String.format(getString(R.string.hasAutoReset),count.name), Toast.LENGTH_SHORT).show();
      }
    }
  }

  /*
   * Pop up various exciting messages if the user has not bothered to turn them off in the
   * settings...
   */

  private void hasIncreased(long master_id, long slave_id)
  {
    if (toastPref == false)
    {
      String master = getCountFromId(master_id).count.name;
      String slave = getCountFromId(slave_id).count.name;
      Toast.makeText(CountingActivity.this, String.format(getString(R.string.postIncr),master,slave), Toast.LENGTH_SHORT).show();
    }
  }

  private void hasDecreased(long master_id, long slave_id)
  {
    if (toastPref == false)
    {
      String master = getCountFromId(master_id).count.name;
      String slave = getCountFromId(slave_id).count.name;
      Toast.makeText(CountingActivity.this, String.format(getString(R.string.postDecr),master,slave), Toast.LENGTH_SHORT).show();
    }
  }

  private void hasReset(long master_id, long slave_id)
  {
    if (toastPref == false)
    {
      String master = getCountFromId(master_id).count.name;
      String slave = getCountFromId(slave_id).count.name;
      Toast.makeText(CountingActivity.this, String.format(getString(R.string.postReset),master,slave), Toast.LENGTH_SHORT).show();
    }
  }


  //**************************************

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.counting, menu);
      return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings)
    {
      startActivity(new Intent(this, SettingsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
      return true;
    }
    else if (id == R.id.menuEditProject)
    {
      Intent intent = new Intent(CountingActivity.this, EditProjectActivity.class);
      intent.putExtra("project_id",project_id);
      startActivity(intent);
      return true;
    }
    else if (id == R.id.menuCalculate)
    {
      Intent intent = new Intent(CountingActivity.this, CalculateActivity.class);
      intent.putExtra("project_id",project_id);
      startActivity(intent);
      return true;
    }
    else if (id == R.id.menuClone)
    {
      cloneProject();
      return true;
    }
    else if (id == R.id.menuSaveExit)
    {
      saveData();
      super.finish();
      return true;
    }
    else if (id == R.id.action_share)
    {
      String project_notes = project.notes;
      String project_name = project.name;
      Intent sendIntent = new Intent();
      sendIntent.setAction(Intent.ACTION_SEND);
      sendIntent.putExtra(Intent.EXTRA_TEXT, project.notes);
      sendIntent.putExtra(Intent.EXTRA_SUBJECT, project.name);
      sendIntent.setType("text/plain");
      startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void onSharedPreferenceChanged(SharedPreferences prefs, String key)
  {
    ScrollView counting_screen = (ScrollView) findViewById(R.id.countingScreen);
    counting_screen.setBackgroundDrawable(null);
    counting_screen.setBackgroundDrawable(beeCount.setBackground());
    getPrefs();
    alertAlert = prepareSound(alertSound);
    countDownAlert = prepareSound(buttonAlertDownSound);
    countUpAlert = prepareSound(buttonAlertSound);
  }

  public Ringtone prepareSound(String name) {
    try
    {
      Uri notification;
      if (StringUtils.isNotBlank(name) && name != null)
      {
        notification = Uri.parse(name);
      }
      else
      {
        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
      }
      Ringtone tone =  RingtoneManager.getRingtone(getApplicationContext(), notification);
      return tone;
    }
    catch (Exception e)
    {
      Log.i(TAG,"Could not set ringtone: " + e.toString());
      e.printStackTrace();
      return null;
    }

  }

  public boolean getNegPref()
  {
    return negPref;
  }

  public void cloneProject()
  {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Title");

    // Set up the input
    final EditText input = new EditText(this);
    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
    input.setInputType(InputType.TYPE_CLASS_TEXT);
    builder.setView(input);

    // Set up the buttons
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      @Override
      public void onClick(DialogInterface dialog, int which)
      {
        String m_Text = input.getText().toString();
        Toast.makeText(CountingActivity.this, "Got text: " + m_Text, Toast.LENGTH_SHORT).show();
        /*
         * Now having to a title for the project it needs to be determined if this is a duplicate
         * of the existing name. If not, then the project can be created.
         */
        if (m_Text.isEmpty())
        {
          Toast.makeText(CountingActivity.this, getString(R.string.newName), Toast.LENGTH_SHORT).show();
          return;
        }
        // These are needed to link old and new counts and alerts
        HashMap<Long, Long> countMap = new HashMap<Long, Long>();

        // Creating the new project
        Project newProject = projectDataSource.createProject(m_Text); // might need to escape the name
        newProject.notes = project.notes;
        projectDataSource.saveProject(newProject);
        for (Count c : countDataSource.getAllCountsForProject(project_id))
        {
          Count newCount = countDataSource.createCount(newProject.id,c.name);
          newCount.notes = c.notes;
          newCount.auto_reset = c.auto_reset;
          newCount.reset_level = c.reset_level;
          newCount.multiplier = c.multiplier;
          countDataSource.saveCount(newCount);

          // prepare alerts
          for (Alert a : alertDataSource.getAllAlertsForCount(c.id))
          {
            Alert newAlert = alertDataSource.createAlert(newCount.id,a.alert,a.alert_text);
          }

          // save relationship between old and new counts
          countMap.put(c.id,newCount.id); //old, new

        }
        for (Link l : linkDataSource.getAllLinksForProject(project_id))
        {
          Long master_id = countMap.get(l.master_id);
          Long slave_id = countMap.get(l.slave_id);
          Link newLink = linkDataSource.createLink(newProject.id,master_id,slave_id,l.increment,l.type);
        }

        // Exit this and go to the list of new projects
        Toast.makeText(CountingActivity.this, getString(R.string.newCopyCreated), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CountingActivity.this, ListProjectActivity.class);
        startActivity(intent);

      }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
      }
    });
    builder.show();
  }

}
