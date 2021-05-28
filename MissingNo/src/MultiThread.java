import java.util.ArrayList;
import java.util.concurrent.Callable;

/**Questa classe permette di parallelizzare il processo di pathfinding
 * @author Giovanni
 * 
 *
 */
public class MultiThread implements Callable<ArrayList<Integer>>{
	
	private double matrix[][];
	private int size;
	
	public MultiThread(double matrix[][], int size) {
		this.matrix=matrix;
		
		this.size=size;
	}
	

	@Override
	public ArrayList<Integer> call() throws Exception {
		Pathfinding pathfinding = new Pathfinding();
			return pathfinding.dijkstraMagic(matrix, size);
		
	}

	
	
}
