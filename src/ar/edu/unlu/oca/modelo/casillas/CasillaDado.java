package ar.edu.unlu.oca.modelo.casillas;

import ar.edu.unlu.oca.modelo.Jugador;
import ar.edu.unlu.oca.modelo.Tablero;

public class CasillaDado extends Casilla {

	public CasillaDado(int posicion) {
		super(posicion);
		appendDescripcion(" - DADO -> Avanza %d casillas");
	}
	
	public String accion(Tablero tablero, Jugador jugador) {
		int cantAvanzar = getPosicion() + jugador.getUltimaTirada();
		jugador.moverFicha(tablero, cantAvanzar);
		return String.format(getDescripcion(), cantAvanzar);
	}


}
