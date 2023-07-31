package ar.edu.unlu.oca.modelo.casillas;

import ar.edu.unlu.oca.modelo.Jugador;
import ar.edu.unlu.oca.modelo.Tablero;

public class CasillaPozo extends Casilla {

	public CasillaPozo(int i) {
		super(i);
		appendDescripcion("(*) POZO => El jugador no podrá moverse hasta que otro jugador lo libere (que pase por la casilla POZO)");
	}
	
	public String accion(Tablero tablero, Jugador jugador, boolean movidaEspecial) {
		jugador.setPozo();
		return getDescripcion();
	}

	public String liberarJugadores() {
		String jugadoresLiberados = "";
		for (Jugador jugador : jugadores) {
			if (jugador.liberarDelPozo()) {
				jugadoresLiberados += "El <font color="+jugador.getFicha().HTMLColor+">jugador "+jugador.getNombre()+" ("+jugador.getFicha()+")</font> ha sido liberado del pozo!\n";
			}
		}
		return jugadoresLiberados;
	}

}
