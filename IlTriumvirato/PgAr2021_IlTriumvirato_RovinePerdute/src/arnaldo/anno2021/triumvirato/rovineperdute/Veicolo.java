package arnaldo.anno2021.triumvirato.rovineperdute;

public class Veicolo {
	
	private TipologiaSpostamento mezzo;

	
	public Veicolo(TipologiaSpostamento mezzo, String nome) {
		this.mezzo = mezzo;
	}
	
	
	public TipologiaSpostamento getMezzo() {
		return mezzo;
	}
	public void setMezzo(TipologiaSpostamento mezzo) {
		this.mezzo = mezzo;
	}
	
}
