package ar.edu.unlu.oca.modelo.casillas;

import ar.edu.unlu.oca.modelo.Jugador;
import ar.edu.unlu.oca.modelo.Tablero;

public class CasillaPuente extends Casilla {
	private int nuevaPosicion;

	public CasillaPuente(int i) {
		super(i);
		this.nuevaPosicion = 19;
		appendDescripcion(" - PUENTE -> Avanza a la casilla "+Integer.toString(nuevaPosicion)+" (POSADA) y pierde un turno");
	}
	
	public String accion(Tablero tablero, Jugador jugador) {
		jugador.moverFicha(tablero, this.nuevaPosicion - jugador.getCasillaActual());
		jugador.incTurnoPerdido(1);
		return getDescripcion();
	}


}
