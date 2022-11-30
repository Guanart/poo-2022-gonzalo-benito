package ar.edu.unlu.oca.modelo.tablero.casillasEspeciales;

import ar.edu.unlu.oca.modelo.IJugador;
import ar.edu.unlu.oca.modelo.Jugador;
import ar.edu.unlu.oca.modelo.tablero.Casilla;

public class CasillaPuente extends Casilla {
	private int nuevaPosicion;

	public CasillaPuente(int i) {
		super(i);
		this.nuevaPosicion = 19;
		appendDescripcion(" - PUENTE -> Avanza a la casilla "+Integer.toString(nuevaPosicion)+" (POSADA) y pierde un turno");
	}
	
	public String accion(IJugador jugador) {
		jugador.saltarCasilla(nuevaPosicion);
		jugador.incTurnoPerdido(1);
		return getDescripcion();
	}


}
