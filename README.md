# EspressoTests
Open Source Android Applications Tested using Espresso Test Recorder

## Android Testing: Espresso Test Recorder
Uno dei problemi principali nel creare una suite di test è quella di dover imparare un nuovo framework. Spesso gli sviluppatori evitano di creare dei test automatizzati per questo motivo (quindi per mancanza di tempo).

Esso si basa sul framework Espresso e permette di aggiungere UI test, in app esistenti, in maniera semplice e interattiva.
Per avviare tale tool basterà andare sul menu run -> Record Espresso Test



L’anatomia di un test UI, e quindi anche dei test con il framework espresso consiste in:



* trovare una view
* eseguire un’azione
* controllare il risultato
l’interazione(1 e 2) equivale ad un tap e altri tipi di azioni che un utente potrebbe realizzare interagendo con l’app, mentre il controllo serve a verificare l’esistenza di un elemento visuale sullo schermo.

Ad esempio si potrebbe pensare ad un *TextInputLayout*e quindi controllare se viene visualizzato il messaggio di errore a fronte di un input errato.



Per utilizzare il tool, occorre mettere ad off tutte le animazioni sul device di test, perchè potrebbero causare risultati inattesi e potrebbero far fallire il test. Per farlo basta andare in *Settings*>>*Developer options*e mettere su off:
* **Window animation scale**
* **Transition animation scale**
* **Animator duration scale**



Il tool risulta molto semplice ma essendo ancora in fase sperimentale ha diverse limitazioni:
* numero limitato di assertion: è possibile solo verificare se una *View*esiste o non esiste, e nel caso delle *TextView*e sottoclassi anche il “text is”. In caso siano necessarie le altre occorre modificare il codice generato.
* non supporto per le *IdlingResources*, quindi nel caso di animazioni o operazioni asincrone, occorre gestirle manualmente, perché Espresso Test Recorder non sa quanto attendere e quindi il test fallirà per via di View non conosciuta.
* non supporto per onData delle adapterView.
* non supporta per le interazione UI che sono al di fuori del codice dell’applicazione.
### Oggetto di Studio
Per gli esperimenti sono state selezionate 6 applicazioni Android open source con fron- tend GUI dalla repository pubblica F-Droid. La scelta delle applicazioni è stata tale da ga- rantire diversi tipi di interfaccia e diversi tipi di interazione con l’UI. Di seguito riportiamo una tabella con i dettagli delle app valutate. 

- - - -
Nome		|	Versione |	LOC	 |	Linguaggio 
Simply Do 		0.9.3 		8868		 Java 
Primary 			0.3.2 		13684 		Java 
Shopping List 	0.11 		15073 		Java 
Editor 			1.36 		5820 		Java 
Counter 			2.0			 9715 		Java 
BMI Calculator 	4.0.0 		9380 		Java 
- - - -
### Esecuzione
Per poter garantire l’esecuzione dei vasi casi di test in un ambiente consono ad una valuta- zione imparziale è stato opportuno apportare delle modifiche che garantissero la veridicità dei risultati. Per raggiungere ciò è stato utilizzato, in combinazione al tool Espresso Test Recorder, lo strumento Android Orchestrator, il quale permette di automatizzare l’esecu- zione dei test garantendo una pulizia della memoria del dispositivo prima di ogni nuova esecuzione di test. 
Per predisporre il progetto all’utilizzo di Android Test Orchestrator. Predisponendo una versione di *AndroidJUnitRunner*pari ad 1.0 o maggiore, si ha disposizione il tool prece- dentemente citato, il quale permette di eseguire ogni singolo test dell’applicazione in una sua invocazione di Instrumentation. Questo permette di ottenere: 
* Riduzione minima dello stato condiviso tra i test: ogni test è eseguito in una sua istanza pertanto se l’applicazione utilizza stati condivisi, la maggior parte di tali informazioni sarà rimossa tra un’esecuzione e l’altra; 
* Isolamento dei Crash: anche se un singolo test fallisce in crash, l’intera suite non è interrotta, grazie all’esecuzione su separate istanze. Per crash si intende una Run- timeException (e non un semplice fallimento del test) che comporterebbe il crash dell’intera applicazione e non solo di una asserzione mancata. 


### Problema Idling Resources
Il tool proverà a risolvere il problema aggiungendo delle Thread.sleep() e il seguente commento
```
// Added a sleep statement to match the app’s execution delay.
// The recommended way to handle such scenarios is to use Espresso idling resources:
// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
```
Ma questo approccio non risolve il problema, in quanto nel test si resta in attesa di eventi UI e/o network call, quindi operazioni asincrone e come dice il commento occorre risolvere usando le *IdlingResources*manualmente.