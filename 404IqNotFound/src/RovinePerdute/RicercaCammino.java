package RovinePerdute;

import java.util.ArrayList;
import java.util.Vector;

public class RicercaCammino
{
	//ArrayList<PercorsoPossibile> scelte = new ArrayList<>();
	private Vector<Double> d = new Vector<>(); // vettore distanze minime da i a sorgente
	private Vector<Boolean> v = new Vector<>(); // vettore booleano per sapere se i è stato visitato
	private Vector<Integer> p = new Vector<>(); // vettore degli id dei nodi precedenti nel cammino tra i e x iniziale
	private static int n=0; // numero dei nodi
	private  ArrayList<Integer> camminoMinimo;
	private  double carburante;



	public RicercaCammino(ArrayList<Nodo> paese)
	{
		// nodo iniziale
		int x = 0; // set nodo iniziale, è sempre 0 "campo base"
		n= paese.size(); // set numero nodi
		setVettori(); // set dei vettori distanza da origine, nodi precedenti e visita

		d.set(x,0.0); // set d(x)=0
		int j=0;
		double m = 0;
		do
		{


			// trovo il nodo che ha distanza minima da x a quelli non ancora visitati
			for (int i = 0; i < n; i++)
			{
				m = Double.POSITIVE_INFINITY;
				if (!v.get(i)) // se v(i)=false
				{
					if (d.get(i) <= m) // se d(i)<=(+inf)
					{
						m = d.get(i);
						j = i;
					}
				}

				if (m != Double.POSITIVE_INFINITY) // se ci sono ancora nodi da visitare
				{
					v.set(j, true); // marca il nodo come visitato
					// esamina i nodi frontiera (neighbours) per j
					for (int k = 0; k < n; k++)
					{
						if (G(j, k) > 0) // se esiste l'arco j->k
						{
							if(d.get(k) == (d.get(j) + G(j, k)))
							{
								if (!(j<k && k<n-1))
								{
									d.set(k, d.get(j) + G(j, k));
									p.set(k, j);
								}
							}
							else if (d.get(k) > (d.get(j) + G(j, k)))
							{
								d.set(k, d.get(j) + G(j, k));
								p.set(k, j);
							}
						}
					}
				}

			}
		}while(m!=Double.POSITIVE_INFINITY); // tutti i nodi sono stati visitati

		camminoMinimo=camminoMinimo();
		carburante=calcoloCarburante();
	}

	/**
	 * inizializzo la distanza da x a +inf,
	 * visita a falso,
	 * nodi precedenti a 0 (nessun nodo precedente)
	 */
	private void setVettori()
	{
		for (int i = 0; i < n; i++)
		{
			d.add(i, Double.POSITIVE_INFINITY);
			v.add(i, false);
			p.add(i, 0);
		}
	}

	/**
	 *
	 * @param a nodo partenza
	 * @param b nodo arrivo
	 * @return arco a->b
	 */
	private static double G(int a, int b)
	{
		return Spedizione.getArco(a, b);
	}

	private  ArrayList<Integer> camminoMinimo()
	{
		ArrayList<Integer> camminoInverso = new ArrayList<>();
		int j=n-1; // id di Rovine Perdute
		camminoInverso.add(j);
		do // cammino da i a x
		{
			camminoInverso.add(p.get(j));
			j=p.get(j);
		}while(j!=0); // finché j non è l'origine (0)
		ArrayList<Integer> camminoCorretto = new ArrayList<>();
		for (int i = camminoInverso.size()-1; i >=0; i--)
		{
			// inverto l'array per andare da x a i
			camminoCorretto.add(camminoInverso.get(i));
		}
		return camminoCorretto;
	}

	/**
	 * @return distanza dall'origine di RP
	 */
	private  double calcoloCarburante()
	{
		return d.get(n-1);
	}

	public double getCarburante() {
		return carburante;
	}

	public ArrayList<Integer> getCamminoMinimo() {
		return camminoMinimo;
	}



		//ALtro algoritmo per Dijkstra e tentativo di A*
		/*

		public RovinePerdute.RicercaCammino(double[][] graph, int source)
		{
			int count = graph.length;
			boolean[] visitedVertex = new boolean[count];
			double[] distance = new double[count];
			for (int i = 0; i < count; i++) {
				visitedVertex[i] = false;
				distance[i] = Double.POSITIVE_INFINITY;
				p.add(i,0);
			}

			// Distance of self loop is zero
			distance[source] = 0;
			for (int i = 0; i < count; i++) {

				// Update the distance between neighbouring vertex and source vertex
				int u = findMinDistance(distance, visitedVertex);
				if(u==-1)
				{
					System.out.println("Errore: "+i);
				}

				visitedVertex[u] = true;

				// Update all the neighbouring vertex distances
				for (int v = 0; v < count; v++)
				{
					if(!visitedVertex[v] && graph[u][v] > 0 && (distance[u] + graph[u][v] == distance[v]))
					{
						if(!(u<v && v< graph.length-1))
						{
							distance[v] = distance[u] + graph[u][v];
							p.set(v,u);
						}
					}
					else if (!visitedVertex[v] && graph[u][v] > 0 && (distance[u] + graph[u][v] < distance[v]))
					{
						distance[v] = distance[u] + graph[u][v];
						p.set(v,u);
					}
				}
			}
			camminoMinimo=calcoloRottaD(graph.length-1);
			carburante=distance[graph.length - 1];

		}

		// Finding the minimum distance
		private int findMinDistance(double[] distance, boolean[] visitedVertex) {
			double minDistance = Double.POSITIVE_INFINITY;
			int minDistanceVertex = 0;
			for (int i = 0; i < distance.length; i++) {
				if (!visitedVertex[i] && distance[i] < minDistance) {
					minDistance = distance[i];
					minDistanceVertex = i;
				}
			}
			return minDistanceVertex;
		}

		private ArrayList<Integer> calcoloRottaD(int arrivo)
		{
			ArrayList<Integer> camminoInverso = new ArrayList<>();
			camminoInverso.add(arrivo);
			do // cammino da i a x
			{
				camminoInverso.add(p.get(arrivo));
				arrivo=p.get(arrivo);
			}while(arrivo!=0); // finché j non è l'origine (0)
			ArrayList<Integer> camminoCorretto = new ArrayList<>();
			for (int i = camminoInverso.size()-1; i >=0; i--)
			{
				// inverto l'array per andare da x a i
				camminoCorretto.add(camminoInverso.get(i));
			}
			return camminoCorretto;
		}

		public RovinePerdute.RicercaCammino(ArrayList<RovinePerdute.Nodo> vertici, int origine) // A* per XY
		{
			ArrayList<Double> stima = new ArrayList<>();
			for (int i = 0; i < vertici.size(); i++) // inizializzo le distanze euclidee tra ogni nodo e l'arrivo
			{
				stima.add(Math.hypot(vertici.get(i).getX()-vertici.get(vertici.size()-1).getX(),vertici.get(i).getY()-vertici.get(vertici.size()-1).getY()));
			}

			x=0; // set nodo iniziale, è sempre 0 "campo base"
			n= vertici.size(); // set numero nodi
			setVettori(); // set dei vettori distanza da origine, nodi precedenti e visita

			d.set(x,0.0); // set d(x)=0
			int j=0;
			double m = 0;
			do
			{
				//v.set(j, true); // marca il nodo come visitato
				// esamina i nodi frontiera (neighbours) per j
				Vector<Double> f = new Vector<>();
				Vector<Integer> f2 = new Vector<>();
				for (int k = 0; k < n; k++)
				{
					if (G(j, k) > 0) // se esiste l'arco j->k
					{
						double temp= (G(j,k)+stima.get(k));
						f.add(temp);
						f2.add(k);
					}
				}
				int a=f2.get(f.indexOf(Collections.min(f)));
				p.set(a,j);
				d.set(a,d.get(j)+G(j,a));
				j=a;

			}while(j!=n-1); // tutti i nodi sono stati visitati

			camminoMinimo=camminoMinimo();
			carburante=calcoloCarburante();
		}*/


	}




