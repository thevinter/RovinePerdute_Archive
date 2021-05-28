import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.stream.XMLStreamException;

/**Questa classe è il main (chi l'avrebbe mai detto)
 * @author Giovanni
 * 
 * 
 *
 */
public class RovinePerduteMain {
	
	
	public static void main(String[] args) throws XMLStreamException, InterruptedException, ExecutionException{
			ArrayList<City> cities = null;
			System.out.println("Seleziona quante città vuoi attraversare (prese tutte da test_file)");
			System.out.println("1. 5 città");
			System.out.println("2. 13 città");
			System.out.println("3. 50 città");
			System.out.println("4. 200 città");
			System.out.println("5. 2000 città");
			System.out.println("6. 10000 città");
			System.out.println("Se ci sono errori di memoria, cambia la dimensione dell'Heap Space");
			int num= InputDati.leggiInteroCompreso("Fai la tua scelta: ", 1, 6);
			
			switch (num) {
			case 1:
				cities =new InputOutput().getData("5");
				break;
			case 2:
				cities =new InputOutput().getData("12");
				break;
			case 3:
				cities =new InputOutput().getData("50");
				break;
			case 4:
				cities =new InputOutput().getData("200");
				break;
			case 5:
				cities =new InputOutput().getData("2000");
				break;
			case 6:
				cities =new InputOutput().getData("10000");
				break;
			}
			
			System.out.println("Ora sto calcolando, pazienta un po'");
			System.out.println("");
			long start = System.currentTimeMillis();
			int size= cities.size();
			ArrayList<double [][]> bothMatrix= new GenMatrix(cities).genBothMatrix();
			
			double[][] matrix = bothMatrix.get(0); //Matrice Tonatiuh
			double[][] matrix1 = bothMatrix.get(1); //Matrice Metztli
			//Cose per debug
			/*int k=5;
			for ( int i=0; i<k; i++){
				for (int j=0 ; j<k; j++){
					System.out.print((int)matrix1[i][j]+ " ");
				}
				System.out.print("  :"+i);
				System.out.println("");
			}*/
			/*int size=4;
			double [][] matrix= {
					{ 0.0 , 1.0 , 0.0 , 0.0},
					{ 1.0 , 0.0 , 0.0 , 0.0},
					{ 0.0 , 0.0 , 0.0 ,0.0},
					{ 0.0 , 0.0 , 0.0 , 0.0}
			};
			double [][]matrix1=matrix;*/
			
			
			//Ho letto un tutorial online su come fare le cose su più thread: spero vada bene
			ExecutorService executor = Executors.newSingleThreadExecutor();
			ExecutorService executor1 = Executors.newSingleThreadExecutor();
			Future<ArrayList<Integer>> percorso= executor.submit(new MultiThread(matrix, size));//Percorso Tonatiuh
			Future<ArrayList<Integer>> percorso1= executor1.submit(new MultiThread(matrix1, size));//Percorso Metztli
			
			executor.shutdown();
			executor1.shutdown();
			
			Pathfinding pathfinding = new Pathfinding();
			double carburanteT=0.0;
			double carburanteM=0.0;
			
			if (percorso.get()!=null) 
				carburanteT=pathfinding.totalFuel(percorso.get(), matrix);
			
			if (percorso1.get()!=null) 
				carburanteM=pathfinding.totalFuel(percorso1.get(), matrix1);
			
			long finish = System.currentTimeMillis();
			System.out.println("Trovato i percorsi dopo " + ((finish-start))+ " Millisecondi!" ); //Importantissimo
			System.out.println("");
			System.out.println("Il percorso che Tonatiuh fa è:");
			System.out.println(percorso.get());
			System.out.println("E sono stati percorsi " + carburanteT + " unità");
			System.out.println("");
			System.out.println("Il percorso che Metztli fa è:");
			System.out.println(percorso1.get());
			System.out.println("E sono stati percorsi " + carburanteM + " unità");
			
			System.out.println("");
			System.out.println("Scrittura su Routes.xml..");
			InputOutput test= new InputOutput();
			test.writeXML(percorso.get(), percorso1.get(), carburanteT, carburanteM, cities);
			return;
		}
		
}



