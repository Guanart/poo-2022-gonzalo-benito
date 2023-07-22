package ar.edu.unlu.oca.modelo;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import ar.edu.unlu.oca.controlador.Eventos;
import ar.edu.unlu.oca.modelo.tablero.Tablero;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;


public class Juego extends ObservableRemoto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Dado dado;
	private Tablero tablero;
	private Queue<Jugador> jugadores;
//	private String descripcionUltimaCasilla;
	
	public Juego(int cantJugadores) {
		this.dado = new Dado();
		this.tablero = new Tablero();
		this.jugadores = new LinkedBlockingQueue<Jugador>(cantJugadores);
	}

	
	// Configuracion
	
	public void cargarJugador(String nombre, int color) throws RemoteException {
		try {
			jugadores.add(new Jugador(nombre, Ficha.values()[color-1]));
			notificarObservadores(Eventos.JUGADOR_AGREGADO);			
		} catch (IllegalStateException e) {
			notificarObservadores(Eventos.LIMITE_JUGADORES);
		}
	}

	public ArrayList<IJugador> getJugadores() throws RemoteException {
		return new ArrayList<IJugador>(jugadores);
	}

	public EnumSet<Ficha> fichasDisponibles() throws RemoteException {
		EnumSet<Ficha> fichasDisponibles = EnumSet.allOf(Ficha.class);
		for (Jugador jugador : jugadores) {
			if (fichasDisponibles.contains(jugador.getFicha())) {
				fichasDisponibles.remove(jugador.getFicha());
			}
		}
		return fichasDisponibles;
	}

	
	
	
	// Partida en juego
	public void iniciarJuego() throws RemoteException {
//		turnoJugador = 0;		// arranca el primer jugador del ArrayList -> TODO: tirar dados para ver quien arranca
//		jugadores.peek().darTurno();
		tablero.inicializar(jugadores);
		notificarObservadores(Eventos.COMENZAR_PARTIDA);
	}
	
	private void terminarJuego() throws RemoteException {
		System.exit(0);
	}

	public IJugador getJugadorActual() throws RemoteException {
		return jugadores.peek();
	}
	

	public String mostrarDado() throws RemoteException {
		return dado.cara();
	}


	public void jugarTurno() throws RemoteException {
		Jugador jugador = jugadores.peek();
		Eventos evento = jugador.jugar(tablero, dado);
		if (evento != Eventos.TURNO_GANADO) {
			jugadores.add(jugadores.poll());
		}
		notificarObservadores(evento);
//		descripcionUltimaCasilla = tablero.getCasilla(nuevaPosicion).accion(jugadorActual); 
//		notificarObservadores(Eventos.MOSTRAR_CASILLA_DADOS);
		
		if (jugador.gano()) {
			notificarObservadores(Eventos.FIN_JUEGO);
			terminarJuego();
		}
		
	}


//	@Override
//	public String getCasillaActual() {
//		return descripcionUltimaCasilla;
//	}


	public void cerrar() {
		System.out.println("Se cerró el servidor");		
	}

}
