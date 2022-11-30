package ar.edu.unlu.oca.modelo;

public class Jugador implements IJugador {
	private Ficha ficha;
	private boolean estaEnPozo = false;
	private int casilla;
	private int turnos = 0;
	private int turnosPerdidos = 0;
	private String nombre;
	private int ultimaTirada;
	
	private final int CASILLA_FINAL = 63;

	
	public Jugador(String nombre, Ficha ficha) {
		super();
		this.ficha = ficha;
		this.casilla = 0;
		this.turnos = 0;
		this.nombre = nombre;
	}

	public int jugar(int tirada) {
		quitarTurno(1);
		return moverFicha(tirada);
	}

	public int moverFicha(int cantCasillas) {
		this.ultimaTirada = cantCasillas;
		// Estoy en la 61, saco 4 -> debo quedar en la 61
		int aux = this.casilla + cantCasillas;
		if (aux > CASILLA_FINAL) {
			this.casilla = CASILLA_FINAL - (aux % CASILLA_FINAL);
		} else {
			this.casilla = aux;
		}
		return casilla;
	}
	
	public boolean gano() {
		return casilla==CASILLA_FINAL;
	}
	
	public boolean tieneTurnos() {
		return turnos > 0;
	}
	
	public void darTurno() {
		++turnos;
	}
	
	public void quitarTurno(int cantTurnos) {
		turnos -= cantTurnos;
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
