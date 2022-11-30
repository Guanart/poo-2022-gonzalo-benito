package ar.edu.unlu.oca.modelo.tablero.casillasEspeciales;

import java.util.HashMap;

import ar.edu.unlu.oca.modelo.Jugador;
import ar.edu.unlu.oca.modelo.tablero.Casilla;

public class CasillaOca extends Casilla {
	private int nuevaPosicion;
    private final HashMap<Integer, Integer> CASILLAS_OCA = new HashMap<Integer, Integer>(){{
		put(5, 9);
		put(9, 14);
		put(14, 18);
		put(18, 23);
		put(23, 27);
		put(27, 32);
		put(32, 36);
		put(36, 41);
		put(41, 45);
		put(45, 50);
		put(50, 54);
		put(54, 59);
		put(59, 59);
    }};

	public CasillaOca(int i) {
		super(i);
		this.nuevaPosicion = CASILLAS_OCA.get(i);
		appendDescripcion(" - OCA -> Avanza a la casilla "+Integer.toString(nuevaPosicion)+" (OCA) y vuelve a tirar");
	}
	
	public String accion(Jugador jugador) {
		jugador.saltarCasilla(nuevaPosicion);
		jugador.darTurno();
		return getDescripcion();
	}


}
