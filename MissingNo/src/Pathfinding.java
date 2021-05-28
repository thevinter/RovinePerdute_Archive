import java.util.ArrayList;
import java.util.Collections;

/**Questa classe si dedica principalmente al calcolo del percorso ottimale
 * @author Giovanni
 *
 */
public class Pathfinding {

	
	
	/**Questo metodo trova tutti i collegamenti che ogni nodo ha con gli altri (posizioniValide)
	 * @param matrix
	 * @param size
	 * @return ArrayList<ArrayList<Integer>> tuttePosizioniValide
	 */
	private ArrayList<ArrayList<Integer>> tuttePosizioniValide(double[][] matrix, int size){
		ArrayList<ArrayList<Integer>> tuttePosizioniValide= new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer>posizioniValide = new ArrayList<Integer>();
		for (int i=0; i<size;i++) {
			for (int j=0; j<size; j++) {
				if (matrix[i][j]>0.0)
					posizioniValide.add(j);
					
			}
			ArrayList<Integer>temp = new ArrayList<Integer>(posizioniValide);
			
			tuttePosizioniValide.add(temp);
			posizioniValide.clear();
		}
		return tuttePosizioniValide;
	}
	
	/**Questo metodo cerca percorsi alternativi più ottimali
	 * @param matrix
	 * @param size
	 * @param listaD
	 * @param firstPath
	 * @param pred
	 * @return percorsoOttimale
	 */
	private ArrayList<Integer> alternativePaths(double[][] matrix,int size, ArrayList<Double> listaD, ArrayList<Integer> firstPath, ArrayList<Integer> pred ){
		ArrayList<Integer> validPaths = new ArrayList<Integer>();//Percorsi che sfruttano lo stesso carburante
		ArrayList<ArrayList<Integer>> linkToValidPaths = new ArrayList<ArrayList<Integer>>();//Tutti i percorsi con la stessa minima lunghezza
		//Controllo se esistono altri percorsi che usano lo stesso carburante
		//La logica è che la lista D contiene la distanza ottimale di tutti gli elementi rispetto al nodo 0
		//Quindi tra tutti gli elementi cerco quelli la cui distanza dal nodo 0 + la distanza dall'ultimo nodo è pari alla minima distanza totale
		double epsilon=0.01; //Necessario per confrontare due doubles
		for (int i=0; i<size-1; i++) {
			if (Math.abs((listaD.get(i)+matrix[i][size-1])-(listaD.get(size-1)))<=epsilon&&firstPath.get(firstPath.size()-2)!=i) {
				validPaths.add(i);
			}
		}
		//Se non esistono, ritorna il percorso trovato originariamente
		if (validPaths.size()==0) {
			System.out.println("Non ci sono percorsi alternativi");
			return firstPath;
		}
		

		int min= firstPath.size();//Valore iniziale di lunghezza minima
		//Controllo la lunghezza di questi percorsi, e raccolgo tutti quelli che hanno percorsi inferiori
		for (int i=0; i<validPaths.size(); i++) {
			ArrayList<Integer> temp=theRightPath(pred,validPaths.get(i));//Costruzione del percorso fino al nodo
			temp.add(size-1);//Aggiungo al percorso la destinazione finale
			int temp2=temp.size();//Lunghezza totale del percorso
			if(temp2==min) {//Se la lunghezza è la stessa, aggiungi il percorso nella lista dei percorsi viabili
				ArrayList<Integer> temp3= new ArrayList<Integer>(temp);//Cazzate indegne necessarie perchè gli arraylist fanno schifo
				linkToValidPaths.add(temp3);
			}
			else if (temp2<min) {//Se la lunghezza di questo percorso è inferiore rispetto a quelli precedenti
				min =temp2; //Assegna a min la lunghezza più corta
				linkToValidPaths.clear();//Pulisci tutto
				ArrayList<Integer> temp3= new ArrayList<Integer>(temp);
				linkToValidPaths.add(temp3);//....e aggiungi ai percorsi validi
			}
		}
		//Se non esistono, ritorna il percorso trovato originariamente
		if(linkToValidPaths.size()==0) {
			System.out.println("Non ci sono percorsi alternativi");
			return firstPath;
		}
		//Se i percorsi che ho raccolto hanno la stezza lunghezza del percorso originario, aggiungi alla lista dei percorsi il percorso originario
		if (min==firstPath.size())
			linkToValidPaths.add(firstPath);
		
		System.out.println("Controllando percorsi alternativi con stessa lunghezza.. "+ linkToValidPaths);
		
		ArrayList<Integer> linkToValidPathsTwo = new ArrayList<Integer>();//Lista di indici a linkToValidPaths che indicano per l'appunto i percorsi con i valori delle città più alti
		
		//Ora bisogna controllare la posizione delle città belle all'interno dei vari percorsi
		for (int i=0; i<min; i++) {//Ciclo tra le posizioni delle liste
			int max=linkToValidPaths.get(0).get(i);//prendo come valore più piccolo il i-esimo elemento del primo percorso
			//System.out.println("Valore max preso " +max);
			for (int j=0; j<linkToValidPaths.size(); j++) {//Ciclo tra le liste (parto da 1 perchè ho gia preso lo 0)
				//System.out.println("il valore che controllo è " +linkToValidPaths.get(j).get(i));
				if(linkToValidPaths.get(j).get(i)==max) {//Stessa idea del ciclo di prima
					linkToValidPathsTwo.add(j);
				}
			
				else if (linkToValidPaths.get(j).get(i)>max) {
					max =linkToValidPaths.get(j).get(i);
					//System.out.println("nuovo valore max "+max);
					linkToValidPathsTwo.clear();
					linkToValidPathsTwo.add(j);

				}
				
			}
			
			if (linkToValidPathsTwo.size()==1) {
				System.out.println("Trovato Percorso Alternativo!");
				return linkToValidPaths.get(linkToValidPathsTwo.get(0));
			}
			linkToValidPathsTwo.clear();
		}
		System.out.println("non dovrebbe succedere");//Beh, non dovrebbe succedere
		return null;
		
		
	}
	
	
	/**Prende l'indice del nodo più piccolo
	 * @param posizioniValide
	 * @param listaD
	 * @return nodoValido
	 */
	private int nodoValido (ArrayList<Integer> posizioniValide, ArrayList<Double>listaD) {
		double minore=-1;
		int nodoValido=-1;
		for (int i=0; i<posizioniValide.size(); i++) {
			double tempMin=listaD.get(posizioniValide.get(i));
			if (tempMin<minore||minore==-1) {
				minore=tempMin;
				nodoValido=i;
			}
		}
		return nodoValido;
	}
	
	
	
	/**beh, l'algoritmo di Dijkstra (dovrebbe funzionare sui percorsi non percorribili)
	 * Se ci sono domande sul nome delle variabili, http://www.andreaminini.com/informatica/teoria-dei-grafi/algoritmo-di-dijkstra (int L rappresenta S) 
	 * @param matrix
	 * @param size
	 * @return percorsoOttimale
	 */
	public ArrayList<Integer> dijkstraMagic(double [][] matrix, int size){
		ArrayList<Integer> pred= new ArrayList <Integer>();//Lista nodi precedenti migliori per ciascun nodo
		ArrayList<Double> listaD = new ArrayList <Double>();//Lista costo minimo per raggiungere un nodo a partire da L
		ArrayList<Integer> listaL =new ArrayList <Integer>();//Lista nodi da analizzare
		ArrayList<ArrayList<Integer>> tuttePosizioniValide= tuttePosizioniValide(matrix, size);//La lista di tutte le posizioni valide
		//System.out.println(tuttePosizioniValide);
		for (int i=0; i<size; i++) {
			pred.add(i);
			listaD.add(-1.0);
		}
		listaD.set(0, 0.0);
		int L=0;//Nodo da controllare
		while (listaD.size()>0) {
		for (int i=0; i<tuttePosizioniValide.get(L).size(); i++) {
			int temp =tuttePosizioniValide.get(L).get(i);
			double distanzaTemp=listaD.get(L)+matrix[L][temp];
			//System.out.println("la distanzaTemp tra " +L + " e " + posizioniValide(matrix, L, size).get(i)+" è di: " +distanzaTemp);
			//System.out.println("listaD in " +posizioniValide(matrix, L, size).get(i) +" è " +listaD.get(posizioniValide(matrix, L, size).get(i)));
			if (distanzaTemp<listaD.get(temp)|| listaD.get(temp)==-1) {
				listaD.set(temp, distanzaTemp);
				//System.out.println("listaD " +listaD);
				pred.set(temp, L);
				//System.out.println("pred " +pred);
				listaL.add(temp);
	
			}
			
		}
		
		L= nodoValido(listaL, listaD);
		if (L==-1) {
			System.out.println("Percorso Trovato...");	
			ArrayList<Integer> theRightPath0 = theRightPath(pred, size-1);
			ArrayList<Integer> theSecondPath = alternativePaths(matrix, size, listaD, theRightPath0, pred);//Cerco percorsi alternativi
			return theSecondPath;
		}
		int temp= listaL.get(L);
		listaL.remove(L);
		L=temp;
		//System.out.println("nodo minore scelto è " + L);
		
		}
		System.out.println("Percorso Non Trovato :(");
		return null;
	}
	
	
	/**Metodo che sistema il percorso, fa sempre parte di Dijkstra
	 * Richiede la lista dei nodi precedenti migliori per ciascun nodo e l'indice del nodo di destinazione
	 * @param pred
	 * @param dest
	 * @return percorsoGiusto
	 */
	private ArrayList <Integer>theRightPath(ArrayList <Integer> pred, int dest){
		
		//System.out.println(pred);
		ArrayList<Integer> percorso = new ArrayList<Integer>();
		int temp=pred.get(dest);
		percorso.add(0,temp);
		if (temp==pred.size()-1)
			return null;
			while (temp!=0) {
				
				percorso.add(0,pred.get(temp));
				temp= pred.get(temp);
			}
		percorso.add(dest);
		//System.out.println(percorso);
		return percorso;
	}
	
	
	/**Questo metodo calcola il carburante totale usato in un determinato percorso
	 * 
	 * @param theRightPath
	 * @param matrix
	 * @return carburanteTot
	 */
	public double totalFuel(ArrayList<Integer> theRightPath, double[][] matrix) {
		double total=0.0;
		for (int i=0; i<theRightPath.size()-1; i++) {
			total+= matrix[theRightPath.get(i)][theRightPath.get(i+1)];
		}
		return Math.round(total*100.0)/100.0;
	}
	
	
	
	
}


