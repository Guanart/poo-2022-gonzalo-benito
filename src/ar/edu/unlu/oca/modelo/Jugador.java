package ar.edu.unlu.oca.modelo;

import java.io.Serializable;

import ar.edu.unlu.oca.controlador.Eventos;
import ar.edu.unlu.oca.modelo.casillas.Casilla;

public class Jugador implements IJugador, Serializable {
	private static final long serialVersionUID = 6850634892505303832L;
	private Ficha ficha;
	private boolean estaEnPozo = false;
	private Casilla casilla;
	private int turnosExtra = 0;
	private int turnosPerdidos = 0;
	private String nombre;
	private int ultimaTirada;	// El ultimo dado obtenido
	private String descripcionCasillaActual;	
	private final int CASILLA_FINAL = 63;

	
	public Jugador(String nombre, Ficha ficha) {
		super();
//		this.casilla = 0;
		this.nombre = nombre;
		this.ficha = ficha;
	}

	public Eventos jugar(Tablero tablero, Dado dado) {
		if (turnosPerdidos > 0) {
			turnosPerdidos--;
			return Eventos.TURNO_TERMINADO;
		}
		else if (estaEnPozo) {
			return Eventos.TURNO_TERMINADO;
		}
		moverFicha(tablero, dado.tirar(), false);
		System.out.println("Turnos extra: "+turnosExtra);
		System.out.println("Turnos perdidos: "+turnosPerdidos);
		if (turnosExtra > 0) {
			turnosExtra--;
			return Eventos.TURNO_GANADO;
		}
		else if (turnosPerdidos > 0) {
			return Eventos.TURNO_PERDIDO;
		}
		else if (estaEnPozo) {
			return Eventos.ESTA_EN_POZO;
		}

		return Eventos.TURNO_TERMINADO;
	}
		
	/*
	 * var movidaEspecial indica si se mueve la ficha por el lanzamiento de dados, o por una acción de una casilla ; evita bucles al ejecutar moverFicha()
	 */
	public void moverFicha(Tablero tablero, int cantCasillas, boolean movidaEspecial) {
		this.ultimaTirada = cantCasillas;		// ???????????
		// Estoy en la 61, saco 4 -> debo quedar en la 61
		int aux = casilla.getPosicion() + cantCasillas;
		Casilla casillaAnterior = casilla;		// Podria validar cual es la casilla anterior, y tomar una decision con la accion de la casilla
		if (aux > CASILLA_FINAL) {
			int i = CASILLA_FINAL - (aux % CASILLA_FINAL);
			this.casilla = tablero.getCasilla(i);
		} else {
			this.casilla = tablero.getCasilla(aux);
		}
		
		// Verifica si pasó por el pozo
		if (casilla.getPosicion()>=Tablero.CASILLA_POZO && casillaAnterior.getPosicion()<Tablero.CASILLA_POZO) {
			tablero.getCasillaPozo().liberarJugadores();
		}
		
		// Modifica el estado del tablero (casillas)
		casillaAnterior.eliminarJugador(this);
		descripcionCasillaActual = casilla.agregarJugador(tablero, this, movidaEspecial);		
	}
	
	public void inicializar(Tablero tablero, Casilla casillaInicial) {
		casilla = casillaInicial;
		descripcionCasillaActual = casilla.agregarJugador(tablero, this, true);				
	}

	public boolean gano(Tablero tablero) {
		return casilla==tablero.getCasilla(Tablero.CASILLA_FINAL);
	}
		
	public void darTurno() {
		turnosExtra++;
	}
	
//	public int saltarCasilla(int nuevaCasilla) {
//		
//		this.casilla = nuevaCasilla;
//		return casilla;
//	}

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
	
	public void incTurnoPerdido(int turnos) {
		turnosPerdidos += turnos;
	}

	public void decTurnoPerdido() {
		turnosPerdidos--;
	}


	// INTERFACE
	@Override
	public Casilla getCasillaActual() {
		return casilla;	// podría retornar la casilla
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public Ficha getFicha() {
		return ficha;
	}

	public String getDescripcionCasillaActual() {
		return descripcionCasillaActual;
	}

}
