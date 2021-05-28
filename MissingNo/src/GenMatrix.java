import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

/**Questa classe crea matrici diverse in base ai veicoli
 * @author Giovanni
 *
 */
public class GenMatrix {
	
	ArrayList<City> cities;
	int size;
	public GenMatrix (ArrayList<City> cities) {
		this.cities=cities;
		this.size=cities.size();
	}
	
	/**Dati due punti A e B di coordinate (i , j)  e  (k , l) ne calcola la distanza
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return distance
	 */
	public double distanceTwoPoints(int i, int j, int k, int l) {
		return Math.sqrt(Math.pow((i-k), 2)+Math.pow((j-l), 2));
	}
	
	
	/**Questo metodo crea un ArrayList che contiene le matrici associate dei percorsi Tonatiuh e Metztli
	 * @return	ArrayList<double [][]> BothMatrix()
	 * @throws XMLStreamException
	 */
	public ArrayList<double [][]> genBothMatrix()throws XMLStreamException{
		double[][] matrixT = new double[size][size];
		double[][] matrixM = new double[size][size];
		for (int i=0; i<size;i++) {
			for (int j=0; j<cities.get(i).getLinksArray().size();j++) {
				int temp=cities.get(i).getLinksArray().get(j);
				//Arrotondato alla seconda cifra decimale, non dovrebbe causare problemi
				double distanceT= Math.round((distanceTwoPoints(cities.get(i).getX(),cities.get(i).getY(),cities.get(temp).getX(),cities.get(temp).getY()))*100.0)/100.0;
				matrixT[i][temp]=distanceT;
				double distanceM= Math.abs(cities.get(i).getH()-cities.get(temp).getH());
				matrixM[i][temp]=distanceM;
			}
			
		}
		ArrayList<double [][]> bothMatrix= new ArrayList<double [][]>();
		bothMatrix.add(matrixT);
		bothMatrix.add(matrixM);
		return bothMatrix;
		
	}
	
	
	 //Inizialmente il processo di creazioni delle matrici associate era gestito da due metodi diversi
	//Poi ho notato che essendo i processi molto simili, potevo fare entrambe le matrici in un colpo
	//Per velocizzare le cose, potevo parallelizzare le creazioni delle due matrici, ma tanto le cose sono lo stesso molto veloci
	/*public double[][] genTonatiuh() throws XMLStreamException {
		
		double[][] matrix = new double[cities.size()][cities.size()];
		for (int i=0; i<cities.size();i++) {
			for (int j=0; j<cities.get(i).getLinksArray().size();j++) {
				int temp=cities.get(i).getLinksArray().get(j);
				//Arrotondato alla seconda cifra decimale, non dovrebbe causare problemi
				double distance= Math.round((distanceTwoPoints(cities.get(i).getX(),cities.get(i).getY(),cities.get(temp).getX(),cities.get(temp).getY()))*100.0)/100.0;
				matrix[i][temp]=distance;
			}
			
		}
		return matrix;
		
	}*/
	
	/*public double[][] genMetztli() throws XMLStreamException{
		double[][] matrix = new double[cities.size()][cities.size()];
		for (int i=0; i<cities.size();i++) {
			for (int j=0; j<cities.get(i).getLinksArray().size();j++) {
			int temp=cities.get(i).getLinksArray().get(j);
			double distance= Math.abs(cities.get(i).getH()-cities.get(temp).getH());
			matrix[i][temp]=distance;
			}
			
		}
		return matrix;
		
	}*/
	
	
	
	
	
	
	

}
