package ar.edu.unlu.oca.modelo.casillas;

import ar.edu.unlu.oca.modelo.Jugador;
import ar.edu.unlu.oca.modelo.Tablero;

public class CasillaTransportar extends Casilla {
	private int nuevaPosicion;
    private final int CASILLA_CALAVERA = 58;
    private final int CASILLA_LABERINTO = 42;

	public CasillaTransportar(int i) {
		super(i);
		switch (i) {
		case CASILLA_LABERINTO:
			this.nuevaPosicion = 30;
			appendDescripcion(" - LABERINTO -> Retrocede a la casilla 30");
			break;
		case CASILLA_CALAVERA:
			this.nuevaPosicion = 1;
			appendDescripcion(" - CALAVERA -> Retrocede a la casilla 1");
			break;
		}
	}
	
	public String accion(Tablero tablero, Jugador jugador) {
		jugador.moverFicha(tablero, this.nuevaPosicion - jugador.getCasillaActual());
		return getDescripcion();
	}


}
