package ar.edu.unlu.oca.modelo.casillas;

import java.util.HashMap;

import ar.edu.unlu.oca.modelo.Jugador;
import ar.edu.unlu.oca.modelo.Tablero;

public class CasillaOca extends Casilla {
	private int nuevaPosicion;
    private final HashMap<Integer, Integer> CASILLAS_OCA = new HashMap<Integer, Integer>(){private static final long serialVersionUID = -1686911704292410115L;

	{
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
	
	@Override
	public String accion(Tablero tablero, Jugador jugador, boolean movidaEspecial) {
		if (!movidaEspecial) {			
			jugador.moverFicha(tablero, this.nuevaPosicion - this.getPosicion(), true);
			jugador.darTurno();
		}
		return getDescripcion();
	}


}
