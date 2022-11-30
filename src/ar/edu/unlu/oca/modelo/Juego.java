package ar.edu.unlu.oca.modelo;

import java.util.ArrayList;
import java.util.EnumSet;

import ar.edu.unlu.oca.controlador.Eventos;
import ar.edu.unlu.oca.controlador.IJuego;
import ar.edu.unlu.oca.modelo.tablero.Tablero;
import ar.edu.unlu.oca.utils.Observable;
import ar.edu.unlu.oca.utils.Observador;


public class Juego implements Observable, IJuego {

	private Dado dado = new Dado();
	private Tablero tablero = new Tablero();
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private ArrayList<Observador> observadores = new ArrayList<Observador>();
	private int turnoJugador = 0;
	private Jugador jugadorActual;
	private String descripcionUltimaCasilla;
	
	public Juego() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void agregarObservador(Observador observador) {
		observadores.add(observador);
	}


	@Override
	public void notificarObservadores(Object o) {
		Eventos evento = (Eventos) o;
		for (Observador observador : observadores) {
			observador.notificar(this, evento);
		}
		
	}

	
	
	
	// Configuracion
	
	@Override
	public void cargarJugador(String nombre, int color) {
		if (jugadores.size() < 4) {			
			jugadores.add(new Jugador(nombre, Ficha.values()[color-1]));
			notificarObservadores(Eventos.JUGADOR_AGREGADO);
		} else {
			notificarObservadores(Eventos.LIMITE_JUGADORES);
		}
	}

	@Override
	public ArrayList<IJugador> getJugadores() {
		return new ArrayList<IJugador>(jugadores);
	}

	@Override
	public EnumSet<Ficha> fichasDisponibles() {
		EnumSet<Ficha> fichasDisponibles = EnumSet.allOf(Ficha.class);
		for (Jugador jugador : jugadores) {
			if (fichasDisponibles.contains(jugador.getFicha())) {
				fichasDisponibles.remove(jugador.getFicha());
			}
		}
		return fichasDisponibles;
	}

	
	
	
	// Partida en juego
	@Override
	public void iniciarJuego() {
		turnoJugador = 0;		// arranca el primer jugador del ArrayList -> TODO: tirar dados para ver quien arranca
		tablero.inicializar();
		jugadores.get(turnoJugador).darTurno();
		notificarObservadores(Eventos.COMENZAR_PARTIDA);
	}
	
	private void terminarJuego() {
		jugadores = new ArrayList<Jugador>();
		turnoJugador = 0;
		jugadorActual = null;
		descripcionUltimaCasilla = null;
	}

	@Override
	public IJugador getJugadorActual() {
		return jugadores.get(turnoJugador);
	}
	
	@Override

	public String mostrarDado() {
		return dado.cara();
	}


	@Override
	public void jugarTurno() {	
		jugadorActual = jugadores.get(turnoJugador);
		int nuevaPosicion = jugadorActual.jugar(dado.tirar());
		
		// CASILLA POZO
		if ((nuevaPosicion>=31) && (nuevaPosicion-jugadorActual.getUltimaTirada())<31) {
			for (Jugador jugador : jugadores) {
				jugador.liberarDelPozo();
			}		
		}
		
		descripcionUltimaCasilla = tablero.getCasilla(nuevaPosicion).accion(jugadorActual); 
		notificarObservadores(Eventos.MOSTRAR_CASILLA_DADOS);
		
		if (jugadorActual.gano()) {
			notificarObservadores(Eventos.FIN_JUEGO);
			terminarJuego();
			return;
		}
		else if (!jugadorActual.tieneTurnos() || jugadorActual.estaEnPozo()) {
			siguienteJugador();
		}
		
		notificarObservadores(Eventos.TURNO_TERMINADO);
	}

	void siguienteJugador() {
		turnoJugador = (turnoJugador+1) % jugadores.size();
		Jugador sigJugador = jugadores.get(turnoJugador);
		if (sigJugador.tieneTurnosPerdidos()) {
			sigJugador.decTurnoPerdido();
			siguienteJugador();
		} else if (sigJugador.estaEnPozo()) {
			siguienteJugador();
		} else {
			sigJugador.darTurno();
		}
	}

	@Override
	public String getCasillaActual() {
		return descripcionUltimaCasilla;
	}

}
