package arnaldo.anno2021.triumvirato.rovineperdute;

public class ContenitoreDatiOutput {
	
	Cammino camminoSquadra1;
	Cammino camminoSquadra2;
	
	ContenitoreDatiOutput(){
		camminoSquadra1=new Cammino();
		camminoSquadra2=new Cammino();
	}
	ContenitoreDatiOutput(Cammino camminoSquadra1,Cammino camminoSquadra2){
		this.camminoSquadra1=camminoSquadra1;
		this.camminoSquadra2=camminoSquadra2;
	}

	
	public Cammino getCamminoSquadra1() {
		return camminoSquadra1;
	}
	public void setCamminoSquadra1(Cammino camminoSquadra1) {
		this.camminoSquadra1 = camminoSquadra1;
	}

	
	public Cammino getCamminoSquadra2() {
		return camminoSquadra2;
	}
	public void setCamminoSquadra2(Cammino camminoSquadra2) {
		this.camminoSquadra2 = camminoSquadra2;
	}
	
	/**
	 * Aggiorna i costi dei cammini
	 */
	public void aggiornaCosti(Veicolo v1, Veicolo v2) {
		camminoSquadra1.aggiornaCosto(v1);
		camminoSquadra2.aggiornaCosto(v2);
	}
	
}
