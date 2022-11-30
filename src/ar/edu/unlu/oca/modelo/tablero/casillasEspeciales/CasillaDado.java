package ar.edu.unlu.oca.modelo.tablero.casillasEspeciales;

import ar.edu.unlu.oca.modelo.Jugador;
import ar.edu.unlu.oca.modelo.tablero.Casilla;

public class CasillaDado extends Casilla {

	public CasillaDado(int posicion) {
		super(posicion);
		appendDescripcion(" - DADO -> Avanza %d casillas");
	}
	
	public String accion(Jugador jugador) {
		int cantAvanzar = getPosicion() + jugador.getUltimaTirada();
		jugador.moverFicha(cantAvanzar);
		return String.format(getDescripcion(), cantAvanzar);
	}


}
