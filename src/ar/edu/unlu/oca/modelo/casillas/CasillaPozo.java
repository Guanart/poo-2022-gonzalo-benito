package ar.edu.unlu.oca.modelo.casillas;

import ar.edu.unlu.oca.modelo.Jugador;
import ar.edu.unlu.oca.modelo.Tablero;

public class CasillaPozo extends Casilla {

	public CasillaPozo(int i) {
		super(i);
		appendDescripcion(" - POZO -> El jugador no podrá moverse hasta que otro jugador lo libere (que pase por la casilla POZO)");
	}
	
	public String accion(Tablero tablero, Jugador jugador) {
		jugador.setPozo();
		return getDescripcion();
	}

	public void liberarJugadores() {
		for (Jugador jugador : jugadores) {
			jugador.liberarDelPozo();
		}		
	}

}
