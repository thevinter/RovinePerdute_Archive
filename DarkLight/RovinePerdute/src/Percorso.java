import java.util.ArrayList;

/**
 * Classe che si occupa della creazione del percorso meno dispendioso
 */
public class Percorso {

    private ArrayList<Citta> citta; //ArrayList contente le città lette
    private ArrayList<Citta> rotta; //arraylist contenente le città che vanno a formare il percorso meno dispendioso
    private double mat[][]; //matrice che rappresenta il grafo (indici = id città; valori = dispanza tra le 2 città (-1 se non vi è collegamento))
    private int infinito=999999999;
    private int r[];//array contenente id delle città che formano la rotta


    /**
     * Metodo costruttore che inizializza l'arraylist città, rotta e matrice mat
     * @param citta
     */
    public Percorso(ArrayList<Citta> citta) {
        this.citta = citta;
        this.mat = new double[citta.size()][citta.size()];
        this.rotta= new ArrayList<Citta>();
    }


    /**
     * Metodo che ritorna l'arraylist citta
     * @return citta
     */
    public ArrayList<Citta> getCitta() {
        return citta;
    }


    /**
     * metodo che setta l'arraylist citta
     * @param citta
     */
    public void setCitta(ArrayList<Citta> citta) {
        this.citta = citta;
    }


    /**
     * metodo che ritorna l'arraylist rotta
     * @return rotta
     */
    public ArrayList<Citta> getRotta() {
        return rotta;
    }


    /**
     * Metodo che setta l'arraylist rotta
     * @param rotta
     */
    public void setRotta(ArrayList<Citta> rotta) {
        this.rotta = rotta;
    }


    /**
     * Metodo che crea la rotta e restituisce la distanza totale equivalente al carburanteConsumato
     * @param tipoVeicolo
     * @return carburanteConsumato
     */
    public double creaRotta(int tipoVeicolo){
        generaMat(tipoVeicolo);
       // stampaMat();
        return rotta();
    }


    /**
     * Metodo che genera la matrice contenente le distanze tra le varie città:<br>
     * Le distanze sono calcolate con formule differenti in base al tipo di veicolo (se 0 distanza calcolata su x e y; se 1 distanza calcolata su z)s
     * @param tipoVeicolo
     */
    public void generaMat(int tipoVeicolo){

        boolean trovato; //indica se esiste un arco tra i due nodi

        for(int i=0; i<citta.size(); i++){
            for (int j=0; j<citta.size(); j++){
                if(i==j){//-1 se nodo con se stesso
                    mat[i][j] = -1;
                }else{
                    trovato = false;
                    for (int k=0; k<citta.get(i).getLink().size() && trovato==false; k++){
                        if (citta.get(i).getLink().get(k)==j){//se esiste un arco che collega i due nodi
                            switch (tipoVeicolo){//in base al tipo di veicolo calcolo la distanza
                                case 0:
                                    mat[i][j] = calcolaDistanzaTipo0(citta.get(i),citta.get(j));
                                    break;
                                case 1:
                                    mat[i][j] = calcolaDistanzaTipo1(citta.get(i),citta.get(j));
                                    break;
                            }
                            trovato = true;
                        }
                    }
                    if (trovato == false){//-1 se non esiste un arco che collega i due nodi
                        mat[i][j] = -1;
                    }

                }
            }
        }

    }


    /**
     * Metodo che calcola la distanza su x e y tra le città passate per parametro
     * @param citta1
     * @param citta2
     * @return distanza
     */
    public double calcolaDistanzaTipo0(Citta citta1, Citta citta2){

        return Math.sqrt(Math.pow(citta2.getPosizione().getX()-citta1.getPosizione().getX(),2) + Math.pow(citta2.getPosizione().getY()-citta1.getPosizione().getY(),2));

    }


    /**
     * Metodo che calcola la distanza su z tra le città passate per parametro
     * @param citta1
     * @param citta2
     * @return distanza
     */
    public double calcolaDistanzaTipo1(Citta citta1, Citta citta2){

        double distanza;

        distanza = citta1.getPosizione().getZ() - citta2.getPosizione().getZ();

        if (distanza >= 0) {
            return distanza;
        }else {
            return distanza * (-1);
        }

    }


    /**
     * Metodo che stampa la metrice mat
     */
    public void stampaMat(){
        for (int i=0; i<citta.size();i++){
            for (int j=0; j<citta.size();j++){
                System.out.print(" "+mat[i][j]);
            }
            System.out.println();
        }
    }


    /**
     * Metodo che calcola la rotta aggiungendo le città toccate all'arraylist rotta e calcola il carburante consumato.
     * @return carburante consumato
     */
    public double rotta()
    {

        int n = citta.size();
        int p[]= new int[n];
        double d[]= new double[n];
        double m,carburante=0;
        int j=0;
        for(int i=0;i<n;i++) {
            d[i]=infinito;//setto ad infinito la distanza
            p[i]=-1;// setto le celle per la rotta a -1
        }
        for(int i=0;i<n-1;i++) {
            for(j=i;j<n;j++)
                if(mat[i][j]>0) {//se la distanza è >0
                    if (d[i] > mat[i][j]) {//se la distanza dell' arco è minore di quella gia settata
                        d[i] = mat[i][j];// setto la distanza dell' arco
                        p[i]=j;// scrivo l'id del nodo per cui passo
                    }
                }
            if(p[i]!=-1)//se ho settato una posizione in p[i] allora
                i=p[i]-1;// setto i come p[i] -1 cosi non torno indietro ma vado avanti nella mat
        }
        int t=0;
        for(int i=0 ; i<n; i++)
            if(d[i]<infinito) {
                carburante += d[i];//sommo tutte le distanze e trovo il carburante richiesto
                        t++;
            };
            j=0;
            r=new int [t];
        for(int i=0 ; i<n; i++)
            if(p[i]>0)//se la p[i]>0 ovvero se è presente un nodo a cui andare
            {
               r[j]=p[i];//inserisco il valore cosi da ottenere il percorso
               j++;
            }
        rotta.add(citta.get(0));//setto la prima citta "punto di partenza" della rotta
        for(int i=0;i<r.length;i++)
        {
            rotta.add(citta.get(r[i]));//setto la rotta con la citta contenuta in r[i]
        }
        return carburante;

    }


}