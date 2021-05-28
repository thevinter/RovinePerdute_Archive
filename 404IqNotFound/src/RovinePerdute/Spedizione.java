package RovinePerdute;

import it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;

public class Spedizione
{
	private static final String MSG_SCELTA= """
			Scegli un numero per decidere quale mappa utilizzare\s
			1 per scegliere la mappa con 5 tappe\s
			2 per scegliere la mappa con 12 tappe\s
			3 per scegliere la mappa con 50 tappe\s
			4 per scegliere la mappa con 200 tappe\s
			5 per scegliere la mappa con 2000 tappe\s
			6 per scegliere la mappa con 10000 tappe""";
	private static ArrayList<Nodo> vertici = new ArrayList<>();
	private static double [][] archi;
	private static InputXml lettura;
	private static final String MAPPA0 = "src/test_file/PgAr_Map_5.xml";
	private static final String MAPPA1 = "src/test_file/PgAr_Map_12.xml";
	private static final String MAPPA2 = "src/test_file/PgAr_Map_50.xml";
	private static final String MAPPA3 = "src/test_file/PgAr_Map_200.xml";
	private static final String MAPPA4 = "src/test_file/PgAr_Map_2000.xml";
	private static final String MAPPA5 = "src/test_file/PgAr_Map_10000.xml";



	public static void main(String[] args)
	{
		int scelta= InputDati.leggiIntero(MSG_SCELTA, 1, 6);

		switch (scelta) //faccio scegliere all'utente da quale lista prendere le tappe
		{
			case 1 -> lettura = new InputXml(MAPPA0);
			case 2 -> lettura = new InputXml(MAPPA1);
			case 3 -> lettura = new InputXml(MAPPA2);
			case 4 -> lettura = new InputXml(MAPPA3);
			case 5 -> lettura = new InputXml(MAPPA4);
			case 6 -> lettura = new InputXml(MAPPA5);
		}
		// leggo le città
		vertici=lettura.getVert();
		// leggo quali città hanno collegamenti
		archi= lettura.getArc();
		// calcolo le distanze tra quelle che hanno collegamenti
		setDistanzeXY();



		// ricerca cammino XY
		//navigatoreXY = new RovinePerdute.RicercaCammino(archi, 0);
		RicercaCammino navigatoreXY = new RicercaCammino(vertici);

		// modifico la matrice per la Z
		modificaArchiZ();

		// ricerca cammino Z
		//navigatoreZ = new RovinePerdute.RicercaCammino(archi, 0);
		RicercaCammino navigatoreZ = new RicercaCammino(vertici);




		OutputXml script = new OutputXml(navigatoreXY, navigatoreZ);


	}



	/**
	 * getter di nome da id
	 * @param i indice della citta di cui voglio sapere il nome
	 * @return nome della i-esima città
	 */
	public static String getNomeDaID(int i)
	{
		return vertici.get(i).getNome();
	}




	/**
	 * scorro la matrice in cerca di celle non zero per come l'ho costruita, quando le trovo
	 * calcolo il collegamento i->j con la distanza euclidea
	 */
	private static void setDistanzeXY()
	{
		for (int i = 0; i < archi.length; i++)
		{
			for (int j = 0; j < archi.length; j++)
			{
				if (archi[i][j]>0) // per gli archi con 1 setto la distanza, lascio gli 0 e gli archi non esistenti con -1
				{
					archi[i][j]=Math.hypot(vertici.get(i).getX()-vertici.get(j).getX(), vertici.get(i).getY()-vertici.get(j).getY());
				}
			}
		}
	}



	/**
	 * getter dell'arco a->b
	 * @param a id partenza
	 * @param b id arrivo
	 * @return lunghezza arco a->b
	 */
	public static double getArco(int a, int b)
	{
		return archi[a][b];
	}

	/**
	 * modifico la tabella: i valori già positivi (quindi dove esiste un arco)
	 * li converto alla distanza vertyicale H(a) - H(b)
	 */
	private static void modificaArchiZ()
	{
		for (int i = 0; i < archi.length; i++)
		{
			for (int j = 0; j < archi.length; j++)
			{
				if (archi[i][j]>0) // dove esiste arco
				{
					archi[i][j]=Math.abs(vertici.get(i).getH()-vertici.get(j).getH()); // guardo abs del delta quota
				}
			}
		}
	}

}

