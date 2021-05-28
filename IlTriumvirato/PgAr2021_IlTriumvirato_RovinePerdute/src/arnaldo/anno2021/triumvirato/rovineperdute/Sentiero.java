package arnaldo.anno2021.triumvirato.rovineperdute;

public class Sentiero implements Comparable<Sentiero>{
	
	private Villaggio partenza;
	private Villaggio destinazione;
	private int idPartenza;
	private int idDestinazione;
	private double costoOrizzontale;
	private double costoVerticale;
	
	
	public double costoDijsktra;
	public static boolean veicoloOrizzontale;
	
	public Sentiero() {
		this.destinazione=new Villaggio();
	}
	
	public Sentiero(Villaggio partenza, Villaggio destinazione, double costoOrizzontale, double costoVerticale) {
		this.partenza=partenza;
		this.destinazione=destinazione;
		this.costoOrizzontale=costoOrizzontale;
		this.costoVerticale=costoVerticale;
	}
	
	public Sentiero(int idPartenza, int idDestinazione) {
		super();
		this.idPartenza = idPartenza;
		this.idDestinazione = idDestinazione;
	}
	
	public Sentiero(Villaggio partenza, Villaggio destinazione) {
		this.partenza=partenza;
		this.destinazione=destinazione;
		this.idPartenza=partenza.getId();
		this.idDestinazione=destinazione.getId();
		this.generateCosts();
	}
	
	
	//non è stato creato un costruttore apposito per avere la sicurezza di preservare lo stesso indirizzo in memoria
	/***
	 * Aggiorna le informazioni riportate da questo sentiero, da solo l'id di partenza e destinazione ai riferimenti ai villaggi di partenza e destinazione
	 * @param partenza è il villaggio di partenza
	 * @param destinazione è il villaggio di destinazione
	 */
	public void aggiornaSentiero(Villaggio partenza, Villaggio destinazione) {
		this.partenza=partenza;
		this.destinazione=destinazione;
		this.idPartenza=partenza.getId();
		this.idDestinazione=destinazione.getId();
		this.generateCosts();
	}
	
	
	
	public Villaggio getPartenza() {
		return partenza;
	}
	public void setPartenza(Villaggio partenza) {
		this.partenza = partenza;
	}
	
	
	public Villaggio getDestinazione() {
		return destinazione;
	}
	public void setDestinazione(Villaggio destinazione) {
		this.destinazione = destinazione;
	}
	
	
	public double getCostoOrizzontale() {
		return costoOrizzontale;
	}
	public void setCostoOrizzontale(double costoOrizzontale) {
		this.costoOrizzontale = costoOrizzontale;
	}
	
	
	public double getCostoVerticale() {
		return costoVerticale;
	}
	public void setCostoVerticale(double costoVerticale) {
		this.costoVerticale = costoVerticale;
	}
	
	
	public int getIdPartenza() {
		return idPartenza;
	}
	public void setIdPartenza(int idPartenza) {
		this.idPartenza = idPartenza;
	}

	
	public int getIdDestinazione() {
		return idDestinazione;
	}
	public void setIdDestinazione(int idDestinazione) {
		this.idDestinazione = idDestinazione;
	}

	
	public void generateCosts() { //deve essere fatto il controllo se il collegamento esiste, su metodo chiamante
		this.generateHorizontalCost();
		this.generateVerticalCost();
	}
	
	
	public void generateHorizontalCost() {
		this.costoOrizzontale=this.partenza.getCoordinate().getHorizontalDistance(destinazione.getCoordinate());
	}

	public void generateVerticalCost() {
		this.costoVerticale=this.partenza.getCoordinate().getVerticalDistance(destinazione.getCoordinate());
	}

	/**
	 * Calcola il costo di un sentiero in base alla tipologia di veicolo
	 * @param v è il veicolo utilizzato per percorrere il cammino
	 * @return il costo
	 */
	public double getCostoSentiero(Veicolo v) {
		if(v.getMezzo()==TipologiaSpostamento.ORIZZONTALE) {
			return this.costoOrizzontale;
		} else if(v.getMezzo()==TipologiaSpostamento.VERTICALE) {
			return this.costoVerticale;
		}
		return -1;
	}

/**
 * CompareTo per il TreeSet di sentiero (In tal modo è ordinato automaticamente)
 * due sentieri vengono comparati in base al costo
 * se entrambi hanno il costo uguale, viene aggiunto prima quello con l'id partenza minore
 */
	@Override
    public int compareTo(Sentiero o) {
        if(veicoloOrizzontale) {
            if(this.getCostoOrizzontale()>o.getCostoOrizzontale()) {
                return 1;
            }else if(this.getCostoOrizzontale()<o.getCostoOrizzontale()) {
                return -1;
            }else if(this.getCostoOrizzontale()==o.getCostoOrizzontale() && this.getIdPartenza() > o.getIdPartenza()) {
                return 1;
            }else if(this.getCostoOrizzontale()==o.getCostoOrizzontale() && this.getIdPartenza() < o.getIdPartenza()) {
                return -1;
            }else {
                return 0;
            }
        }else{
            if(this.getCostoVerticale()>o.getCostoVerticale()) {
                return 1;
            }else if(this.getCostoVerticale()<o.getCostoVerticale()) {
                return -1;
            }else if(this.getCostoVerticale()==o.getCostoVerticale() && this.getIdPartenza() > o.getIdPartenza()) {
                return 1;
            }else if(this.getCostoVerticale()==o.getCostoVerticale() && this.getIdPartenza() < o.getIdPartenza()) {
                return -1;
            }else {
                return 0;
            }

        }
    }
	
	public boolean isWorsePath(double distanceTravelledBeforeThis, Veicolo v) {
		return this.destinazione.nodeHasWorsePath(distanceTravelledBeforeThis+this.getCostoSentiero(v));
	}
	
	
}
