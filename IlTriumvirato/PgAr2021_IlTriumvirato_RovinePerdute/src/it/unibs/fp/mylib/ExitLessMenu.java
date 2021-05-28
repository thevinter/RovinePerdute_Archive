package it.unibs.fp.mylib;

import java.util.ArrayList;

public class ExitLessMenu extends MyMenu {
	
	public ExitLessMenu() {
		super();
	}
	
	public ExitLessMenu(String title, ArrayList<String> options) {
		super(title, options);
	}
	
	public ExitLessMenu(String title, String[] options) {
		super(title, options);
	}

	@Override
    public void printMenu() {
		System.out.println(FRAME);
		System.out.println(title);
		System.out.println(FRAME);
		for (int i=0; i<options.size(); i++)
		{
			System.out.println( (i+1) + "\t" + options.get(i));
		}
		System.out.println();
	}
	
	
	public void runSelection(int selection) {
		switch(selection) {
			default:
					//undefined
			break;
			
		}
		
	}
}
