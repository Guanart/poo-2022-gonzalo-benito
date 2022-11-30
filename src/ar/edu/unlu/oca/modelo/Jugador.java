package ar.edu.unlu.oca.modelo;

public class Jugador implements IJugador {
	private Ficha ficha;
	private boolean estaEnPozo = false;
	private int casilla;
	private int turnos = 0;
	private int turnosPerdidos = 0;
	private String nombre;
	private boolean gano; // si ya llegó al final
	private int ultimaTirada;
	
	private final int CASILLA_FINAL = 63;

	
	public Jugador(String nombre, Ficha ficha) {
		super();
		this.ficha = ficha;
		this.casilla = 0;
		this.turnos = 0;
		this.nombre = nombre;
		this.gano = false;
	}


	@Override
	public String getNombre() {
		return nombre;
	}


	@Override
	public Ficha getFicha() {
		return ficha;
	}
	
	@Override
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
	
	@Override
	public int saltarCasilla(int nuevaCasilla) {
		this.casilla = nuevaCasilla;
		return casilla;
	}
	
	@Override
	public void darTurno() {
		++turnos;
	}
	
	@Override
	public void quitarTurno(int cantTurnos) {
		turnos -= cantTurnos;
	}

	@Override
	public int getCasillaActual() {
		return casilla;
	}

	public void setPozo() {
		estaEnPozo = true;
	}
	
	@Override
	public boolean estaEnPozo() {
		return estaEnPozo;
	}
	
	@Override
	public void liberarDelPozo() {
		estaEnPozo = false;
	}

	@Override
	public int getUltimaTirada() {
		return this.ultimaTirada;
	}
	
	public boolean gano() {
		return casilla==CASILLA_FINAL;
	}
	
	public boolean tieneTurnos() {
		return turnos > 0;
	}


	@Override
	public int jugar(int tirada) {
		quitarTurno(1);
		return moverFicha(tirada);
	}


	public boolean tieneTurnosPerdidos() {
		return turnosPerdidos > 0;
	}

	@Override
	public void incTurnoPerdido(int turnos) {
		turnosPerdidos += turnos;
	}

	@Override
	public void decTurnoPerdido() {
		--turnosPerdidos;
	}

	@Override
	public void cayoEnPozo() {
		// TODO Auto-generated method stub
		
	}

	
}
