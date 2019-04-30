# EspressoTests
Open Source Android Applications Tested using Espresso Test Recorder

## Android Testing: Espresso Test Recorder
Uno dei problemi principali nel creare una suite di test è quella di dover imparare un nuovo framework. Spesso gli sviluppatori evitano di creare dei test automatizzati per questo motivo (quindi per mancanza di tempo).
 
Esso si basa sul framework Espresso e permette di aggiungere UI test, in app esistenti, in maniera semplice e interattiva.
Per avviare tale tool basterà andare sul menu run -> Record Espresso Test


![](README/1*rE8f5YiFY32apRoTuO8D8g.png)

L’anatomia di un test UI, e quindi anche dei test con il framework espresso consiste in:

![](README/1*NqFr7_5XTBY4B-ASvOvnUQ.png)

* trovare una view
* eseguire un’azione
* controllare il risultato
l’interazione(1 e 2) equivale ad un tap e altri tipi di azioni che un utente potrebbe realizzare interagendo con l’app, mentre il controllo serve a verificare l’esistenza di un elemento visuale sullo schermo.

Ad esempio si potrebbe pensare ad un *TextInputLayout*e quindi controllare se viene visualizzato il messaggio di errore a fronte di un input errato.


![](README/1*Q78Cv-z0SwHFnq-LwWbW4w.png)

Per utilizzare il tool, occorre mettere ad off tutte le animazioni sul device di test, perchè potrebbero causare risultati inattesi e potrebbero far fallire il test. Per farlo basta andare in *Settings*>>*Developer options*e mettere su off:
* **Window animation scale**
* **Transition animation scale**
* **Animator duration scale**


![](README/1*TNIqJLKl-k4KL0wWG3U9lA.png)

Il tool risulta molto semplice ma essendo ancora in fase sperimentale ha diverse limitazioni:
* numero limitato di assertion: è possibile solo verificare se una *View*esiste o non esiste, e nel caso delle *TextView*e sottoclassi anche il “text is”. In caso siano necessarie le altre occorre modificare il codice generato.
* non supporto per le *IdlingResources*, quindi nel caso di animazioni o operazioni asincrone, occorre gestirle manualmente, perché Espresso Test Recorder non sa quanto attendere e quindi il test fallirà per via di View non conosciuta.
* non supporto per onData delle adapterView.
* non supporta per le interazione UI che sono al di fuori del codice dell’applicazione.

### Problema Idling Resources
 Il tool proverà a risolvere il problema aggiungendo delle Thread.sleep() e il seguente commento
```
// Added a sleep statement to match the app’s execution delay.
// The recommended way to handle such scenarios is to use Espresso idling resources:
// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
```
Ma questo approccio non risolve il problema, in quanto nel test si resta in attesa di eventi UI e/o network call, quindi operazioni asincrone e come dice il commento occorre risolvere usando le *IdlingResources*manualmente.
