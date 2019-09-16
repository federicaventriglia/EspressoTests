package com.pan.lintpan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "prova_bottom_sheet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showBottomSheet = findViewById(R.id.showSheetButton);

        showBottomSheet.setOnClickListener(view -> {
            BottomSheetFragment fragment = new BottomSheetFragment();
            fragment.show(getSupportFragmentManager(), TAG);
        });
    }

    //toast 1st button
    public void toastNumbOne(View v){
        Toast.makeText(MainActivity.this, "Button 1", Toast.LENGTH_SHORT).show();
    }

    //toast 2nd button
    public void toastNumbTwo(View v){
        Toast.makeText(MainActivity.this, "Button 2", Toast.LENGTH_SHORT).show();
    }
}
