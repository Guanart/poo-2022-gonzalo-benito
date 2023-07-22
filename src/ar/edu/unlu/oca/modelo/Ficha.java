package ar.edu.unlu.oca.modelo;

public enum Ficha {
	ROJA(1, "Roja", "red"),
	VIOLETA(2, "Violeta", "#8A2BE2"),
	VERDE(3, "Verde", "green"),
	AZUL(4, "Azul", "blue");
	
    public int opcion;
	public String label;
	public String HTMLColor;

	Ficha(int opcion, String label, String html) {
        this.opcion = opcion;
        this.label = label;
        this.HTMLColor = html;
    }

}
