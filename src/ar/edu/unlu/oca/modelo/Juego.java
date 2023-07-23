package ar.edu.unlu.oca.modelo;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import ar.edu.unlu.oca.controlador.Eventos;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;


public class Juego extends ObservableRemoto implements IJuego, Serializable {
	private static final long serialVersionUID = -4695236711809599703L;
	private Dado dado;
	private Tablero tablero;
	private Queue<Jugador> jugadores;
	private String descripcionUltimaCasilla;
	private int cantJugadores;
	
	public Juego(int cantJugadores) {
		this.dado = new Dado();
		this.tablero = new Tablero();
		this.cantJugadores = cantJugadores;
		this.jugadores = new LinkedBlockingQueue<Jugador>(cantJugadores);
		System.out.println("Se levantó el servidor");
	}

	
	// Configuracion
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
	
	@Override
	public IJugador cargarJugador(String nombre, int color) throws RemoteException {
		Jugador jugador = new Jugador(nombre, Ficha.values()[color-1]);
		try {
			jugadores.add(jugador);
			notificarObservadores(Eventos.JUGADOR_AGREGADO);
			System.out.println("Jugador agregado");
			System.out.println(jugador);
		} catch (IllegalStateException e) {
			notificarObservadores(Eventos.LIMITE_JUGADORES);
		}
		
		return jugador;
	}
	
	// Partida en juego
	public void iniciarJuego() throws RemoteException {
//		turnoJugador = 0;		// arranca el primer jugador del ArrayList -> TODO: tirar dados para ver quien arranca
//		jugadores.peek().darTurno();
		if (jugadores.size()==cantJugadores) {
			tablero.inicializar(jugadores);
			notificarObservadores(Eventos.COMENZAR_PARTIDA);
			System.out.println("Partida iniciada");
		}

	}
	
	private void terminarJuego() throws RemoteException {
		System.out.println("Partida terminada");
	}	
	
	public void cerrar() {
		System.out.println("Se cerró el servidor");
		System.exit(0);
	}


	public String mostrarDado() throws RemoteException {
		return dado.cara();
	}


	public void jugarTurno() throws RemoteException {
		Jugador jugador = jugadores.peek();
		System.out.println("Turno de: "+jugador.getNombre());
		Eventos evento = jugador.jugar(tablero, dado);
		descripcionUltimaCasilla = jugador.getDescripcionCasillaActual();
		System.out.println("Lanzó: "+jugador.getUltimaTirada());
		System.out.println(descripcionUltimaCasilla);
		System.out.println(evento);
		System.out.println("------------------------------------");

		if (jugador.gano(tablero)) {
			notificarObservadores(Eventos.FIN_JUEGO);
			terminarJuego();
		}

		if (evento != Eventos.TURNO_GANADO) {
			jugadores.add(jugadores.poll());
		}
		
		notificarObservadores(evento);
//		descripcionUltimaCasilla = tablero.getCasilla(nuevaPosicion).accion(jugadorActual); 
//		notificarObservadores(Eventos.MOSTRAR_CASILLA_DADOS);
			
	}


//	@Override
	public String getCasillaActual() {
		return descripcionUltimaCasilla;
	}

	public IJugador getJugadorActual() throws RemoteException {
		return jugadores.peek();
	}
	
	public Tablero getTablero() throws RemoteException {
		return tablero;
	}
 

}
