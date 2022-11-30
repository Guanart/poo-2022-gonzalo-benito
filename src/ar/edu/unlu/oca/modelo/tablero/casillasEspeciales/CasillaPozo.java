package ar.edu.unlu.oca.modelo.tablero.casillasEspeciales;

import ar.edu.unlu.oca.modelo.IJugador;
import ar.edu.unlu.oca.modelo.Jugador;
import ar.edu.unlu.oca.modelo.tablero.Casilla;

public class CasillaPozo extends Casilla {

	public CasillaPozo(int i) {
		super(i);
		appendDescripcion(" - POZO -> El jugador no podrá moverse hasta que otro jugador lo libere (que pase por la casilla POZO)");
	}
	
	public String accion(IJugador jugador) {
		jugador.setPozo();
		return getDescripcion();
	}


}
