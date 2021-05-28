import it.unibs.fp.mylib.*;


public class Main
{
	private static final String BENVENUTO = "\n\nBenvenuto, programma dei Runtime Terrors\n";
	private static final String[] SCELTE = {"PgAr_Map_5.xml", "PgAr_Map_12.xml", "PgAr_Map_50.xml", "PgAr_Map_200.xml", "PgAr_Map_2000.xml", "PgAr_Map_10000.xml", "Custom"};
	private static final String CUSTOM = "\nInserisci il nome del file da utilizzare: ";
	private static final String SCELTA = "Scelta file";
	
	public static void main(String[] args)
	{
		int scelta;
		System.out.println(BENVENUTO);
		MyMenu menu = new MyMenu(SCELTA, SCELTE);
		scelta = menu.scegli();
		switch (scelta)
		{
			case 1:
				Reader.readInput(SCELTE[0]);
				break;
			case 2:
				Reader.readInput(SCELTE[1]);
				break;
			case 3:
				Reader.readInput(SCELTE[2]);
				break;
			case 4:
				Reader.readInput(SCELTE[3]);
				break;
			case 5:
				Reader.readInput(SCELTE[4]);
				break;
			case 6:
				Reader.readInput(SCELTE[5]);
				break;
			case 7:
				Reader.readInput(InputDati.leggiStringaNonVuota(CUSTOM));
				break;
			case 0:
				System.exit(0);
				break;
		}
		DataProcessing.calcolaPercorso(true);
		DataProcessing.resetCities();
		DataProcessing.calcolaPercorso(false);
		Writer.writeOutput();
	}
}
