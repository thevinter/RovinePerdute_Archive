package it.unibs.fp.mylib;

import java.util.ArrayList;

/*
Questa classe rappresenta un menu testuale generico a piu' voci
Si suppone che la voce per uscire sia sempre associata alla scelta 0 
e sia presentata in fondo al menu

Si può anche invocare un menu senza uscita, questo ovviamente può essere eseguito solo una volta e quindi non è previsto il loop
*/
public class MyMenu
{
  final protected static int EXIT_NUMBER=0;
  final protected static String FRAME = "--------------------------------";
  final protected static String EXIT_OPTION_LINE = EXIT_NUMBER+"\tEsci";
  final protected static String INPUT_REQUEST = "Digita il numero dell'opzione desiderata > ";
  final protected static String DEFAULT_EXIT_MESSAGGE="chiusura effettuata";
  protected String title;
  protected ArrayList<String> options;
  protected String exitMessage;

  public MyMenu () {
	  
  }
  
  public MyMenu (String title, String[] options)
  {
	this.title = title;
	this.options = ManipolaArray.convertToArrayListString(options);
	exitMessage=DEFAULT_EXIT_MESSAGGE;
  }
  
  public MyMenu (String title, String[] options, String exitMessage)
  {
	this.title = title;
	this.options = ManipolaArray.convertToArrayListString(options);
	this.exitMessage=exitMessage;
  }
  
  public MyMenu (String title, ArrayList<String> options)
  {
	this.title = title;
	this.options = options;
	exitMessage=DEFAULT_EXIT_MESSAGGE;
  }
  
  public MyMenu (String title, ArrayList<String> options, String exitMessage)
  {
	this.title = title;
	this.options = options;
	this.exitMessage=exitMessage;
  }
  

  public int choose ()
  {
	printMenu();
	return InputDati.leggiIntero(INPUT_REQUEST, 0, options.size());	 
  }

  public void printMenu ()
  {
	System.out.println(FRAME);
	System.out.println(title);
	System.out.println(FRAME);
    for (int i=0; i<options.size(); i++)
	 {
	  System.out.println( (i+1) + "\t" + options.get(i));
	 }
    System.out.println();
	System.out.println(EXIT_OPTION_LINE);
    System.out.println();
  }
  
  public void loopMenu() {
		int scelta;
		do {
			scelta=choose();
			runSelection(scelta);
			
		}while(scelta!=EXIT_NUMBER);
  }
  
  
		
  /** This method will be overridden
   * 
   */
  public void runSelection(int selection) {
		switch(selection) {
			case EXIT_NUMBER:
				System.out.println(exitMessage);
				break;
			default:
				//undefined
			break;
		
		}
		
	}
}

