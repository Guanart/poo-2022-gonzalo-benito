package ar.edu.unlu.oca.modelo;

import java.awt.Color;

public enum Ficha {
	ROJA(1, "Roja", "red", Color.RED),
	VIOLETA(2, "Violeta", "#8A2BE2", Color.MAGENTA),
	VERDE(3, "Verde", "green", Color.GREEN),
	AZUL(4, "Azul", "blue", Color.BLUE);
	
    public int opcion;
	public String label;
	public String HTMLColor;
	public Color swingColor;

	Ficha(int opcion, String label, String html, Color swingColor) {
        this.opcion = opcion;
        this.label = label;
        this.HTMLColor = html;
        this.swingColor = swingColor;
    }

}
