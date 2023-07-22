package ar.edu.unlu.oca.modelo.casillas;

import ar.edu.unlu.oca.modelo.Jugador;
import ar.edu.unlu.oca.modelo.Tablero;

public class CasillaPerderTurno extends Casilla {
	private int cantTurnos;

	public CasillaPerderTurno(int i) {
		super(i);
		switch (i) {
		case 19:
			this.cantTurnos = 1;
			appendDescripcion(" - POSADA -> Pierde un turno");
			break;
		case 56:
			this.cantTurnos = 2;
			appendDescripcion(" - CARCEL -> Pierde dos turnos");
			break;
		}
	}

	public String accion(Tablero tablero, Jugador jugador) {
		jugador.incTurnoPerdido(cantTurnos);
		return getDescripcion();
	}

}
