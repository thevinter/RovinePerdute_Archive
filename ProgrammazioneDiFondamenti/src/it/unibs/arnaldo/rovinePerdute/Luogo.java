package it.unibs.arnaldo.rovinePerdute;

public class Luogo {

    //attributi
    private int id;
    private String nome;
    private Posizione posizione;

    /**
     * <h3>Metodo corstruttore per un Luogo</h3>
     * @param id codice univoco associato al luogo
     * @param nome nominativo del luogo
     * @param posizione coordinata composta da x, y e h (ovvero l'altitudine)
     */
    public Luogo(int id, String nome, Posizione posizione) {
        this.id = id;
        this.nome = nome;
        this.posizione = posizione;
    }

    /**
     * <h3>Metodo per la creazione di un Luogo con i valori di default</h3>
     */
    public  Luogo() { }

    //get e set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }

}
