package ar.edu.unlu.oca.modelo.casillas;

import ar.edu.unlu.oca.modelo.Jugador;
import ar.edu.unlu.oca.modelo.Tablero;

public class CasillaDado extends Casilla {

	private static final long serialVersionUID = -5796492652177641110L;

	public CasillaDado(int posicion) {
		super(posicion);
		appendDescripcion("(*) DADO => Avanza %d casillas");
	}
	
	@Override
	public String accion(Tablero tablero, Jugador jugador, boolean movidaEspecial) {
		if (!movidaEspecial) {			
			int cantAvanzar = getPosicion() + jugador.getUltimaTirada();
			jugador.moverFicha(tablero, cantAvanzar, true);
			return String.format(getDescripcion(), cantAvanzar);
		}
		return "";
	}


}
