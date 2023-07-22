package ar.edu.unlu.oca.modelo;

public enum Ficha {
	ROJA(1, "Roja"),
	VIOLETA(2, "Violeta"),
	VERDE(3, "Verde"),
	AZUL(4, "Azul");
	
    public int opcion;
	public String label;

	Ficha(int opcion, String label) {
        this.opcion = opcion;
        this.label = label;
    }

}
