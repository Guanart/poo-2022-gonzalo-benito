package ar.edu.unlu.oca.modelo;

import java.io.Serializable;

import ar.edu.unlu.oca.controlador.Eventos;
import ar.edu.unlu.oca.modelo.tablero.Tablero;

public class Jugador implements IJugador, Serializable {
	private static final long serialVersionUID = 1L;
	private Ficha ficha;
	private boolean estaEnPozo = false;
	private int casilla;
	private int turnosExtra = 0;
	private int turnosPerdidos = 0;
	private String nombre;
	private int ultimaTirada;	// El ultimo dado obtenido
	
	private final int CASILLA_FINAL = 63;

	
	public Jugador(String nombre, Ficha ficha) {
		super();
		this.casilla = 0;
		this.nombre = nombre;
		this.ficha = ficha;
	}

	public Eventos jugar(Tablero tablero, Dado dado) {
		if (turnosPerdidos > 0) {
			--turnosPerdidos;
			return Eventos.TURNO_PERDIDO;
		}
		else if (estaEnPozo) {
			return Eventos.ESTA_EN_POZO;
		}
		
		moverFicha(tablero, dado.tirar());
		if (turnosExtra > 0) {
			return Eventos.TURNO_GANADO;
		}
		return Eventos.TURNO_TERMINADO;
	}
		
	public void moverFicha(Tablero tablero, int cantCasillas) {
		this.ultimaTirada = cantCasillas;		// ???????????
		// Estoy en la 61, saco 4 -> debo quedar en la 61
		int aux = this.casilla + cantCasillas;
		int casillaAnterior = this.casilla;
		if (aux > CASILLA_FINAL) {
			this.casilla = CASILLA_FINAL - (aux % CASILLA_FINAL);
		} else {
			this.casilla = aux;
		}
		tablero.moverFicha(this, casillaAnterior);
	}
	
	public boolean gano() {
		return casilla==CASILLA_FINAL;
	}
	
	public boolean tieneTurnos() {
		return turnosExtra > 0;
	}
	
	public void darTurno() {
		++turnosExtra;
	}
	
	public void quitarTurno(int cantTurnos) {
		turnosExtra -= cantTurnos;
	}
	
	public int saltarCasilla(int nuevaCasilla) {
		this.casilla = nuevaCasilla;
		return casilla;
	}

	public void setPozo() {
		estaEnPozo = true;
	}
	
	public boolean estaEnPozo() {
		return estaEnPozo;
	}
	
	public void liberarDelPozo() {
		estaEnPozo = false;
	}

	public int getUltimaTirada() {
		return this.ultimaTirada;
	}
	
	public boolean tieneTurnosPerdidos() {
		return turnosPerdidos > 0;
	}

	public void incTurnoPerdido(int turnos) {
		turnosPerdidos += turnos;
	}

	public void decTurnoPerdido() {
		--turnosPerdidos;
	}


	// INTERFACE
	@Override
	public int getCasillaActual() {
		return casilla;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public Ficha getFicha() {
		return ficha;
	}

}
