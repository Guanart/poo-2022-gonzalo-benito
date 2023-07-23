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

	@Override
	public String accion(Tablero tablero, Jugador jugador, boolean movidaEspecial) {
		if (!movidaEspecial) {	
			int aux = this.nuevaPosicion - jugador.getCasillaActual().getPosicion();
			jugador.moverFicha(tablero, aux, true);
			jugador.incTurnoPerdido(1);
		}
		return getDescripcion();
	}


}
