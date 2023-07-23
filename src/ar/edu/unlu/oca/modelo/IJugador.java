package ar.edu.unlu.oca.modelo;

import ar.edu.unlu.oca.modelo.casillas.Casilla;

public interface IJugador {
	Casilla getCasillaActual();
	String getNombre();
	Ficha getFicha();
}
