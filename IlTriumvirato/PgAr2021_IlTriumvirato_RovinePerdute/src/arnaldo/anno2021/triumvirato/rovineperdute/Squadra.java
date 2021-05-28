package arnaldo.anno2021.triumvirato.rovineperdute;

public class Squadra {
	
	private String nome;
	private Veicolo veicolo;
	
	public Squadra(String nome, Veicolo veicolo) {
		super();
		this.nome = nome;
		this.veicolo = veicolo;
	}

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public Veicolo getVeicolo() {
		return veicolo;
	}
	public void setVeicolo(Veicolo veicolo) {
		this.veicolo = veicolo;
	}
	
	
}
