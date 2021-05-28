import java.util.ArrayList;

public class Main {

    private final static int MIN = 0, MAX = 5;
    private final static String NOME_FILE_MAP5 = "RovinePerdute/test_file/PgAr_Map_5.xml";
    private final static String NOME_FILE_MAP12 = "RovinePerdute/test_file/PgAr_Map_12.xml";
    private final static String NOME_FILE_MAP50 = "RovinePerdute/test_file/PgAr_Map_50.xml";
    private final static String NOME_FILE_MAP200 = "RovinePerdute/test_file/PgAr_Map_200.xml";
    private final static String NOME_FILE_MAP2000 = "RovinePerdute/test_file/PgAr_Map_2000.xml";
    private final static String NOME_FILE_MAP10000 = "RovinePerdute/test_file/PgAr_Map_10000.xml";



    public static void main(String[] args) {

        GestioneFile file = new GestioneFile();
        ArrayList<Squadra> squadre = new ArrayList<Squadra>();
        int scelta;

        System.out.println("Benvenuto!");
        scelta = InputDati.leggiIntero("Elenco dei file:\n0) Map_5\n1) Map_12\n2) Map_50\n3) Map_200\n4) Map_2000\n5) Map_10000\n\nScegli un file da leggere:",MIN,MAX);

        switch (scelta){
            case 0:
                file.leggiFile(NOME_FILE_MAP5);
                break;
            case 1:
                file.leggiFile(NOME_FILE_MAP12);
                break;
            case 2:
                file.leggiFile(NOME_FILE_MAP50);
                break;
            case 3:
                file.leggiFile(NOME_FILE_MAP200);
                break;
            case 4:
                file.leggiFile(NOME_FILE_MAP2000);
                break;
            case 5:
                file.leggiFile(NOME_FILE_MAP10000);
                break;
        }

        squadre.add(new Squadra("Tonatiuh",0, file.getCitta()));
        squadre.add(new Squadra("Metztli", 1, file.getCitta()));

        for (int i=0; i<squadre.size(); i++){
            squadre.get(i).setCarburanteConsumato();
        }

        if (file.scriviFile(squadre)){
            System.out.println("Il file Routes è stato creato!");
        }else{
            System.out.println("Errore nella creazione del file Routes");
        }

    }

}
