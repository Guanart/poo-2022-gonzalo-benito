package ar.edu.unlu.oca.modelo;

import ar.edu.unlu.oca.modelo.casillas.Casilla;

public interface IJugador {
	Casilla getCasillaActual();
//	int getUltimaTirada();
	String getNombre();
	Ficha getFicha();
	int turnosPerdidos();
	boolean estaEnPozo();
}
