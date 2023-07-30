package ar.edu.unlu.oca.modelo;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import ar.edu.unlu.oca.controlador.Eventos;
import ar.edu.unlu.oca.services.Serializador;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;


public class Juego extends ObservableRemoto implements IJuego, Serializable {
	private static final long serialVersionUID = -4695236711809599703L;
	private Dado dado;
	private Tablero tablero;
	private int cantJugadores;
	private Queue<Jugador> jugadores;
	private Jugador ganador;
	private String descripcionUltimaCasilla;
	private boolean esPartidaComenzada;
    private Map<String, Integer> ranking;
	

	public Juego() {
		this.ranking = new HashMap<>();
	}
	

	public void nuevaPartida(int cantJugadores) {
		this.dado = new Dado();
		this.tablero = new Tablero();
		this.cantJugadores = cantJugadores;
		this.jugadores = new LinkedBlockingQueue<Jugador>(cantJugadores);
		this.ganador = null;
		this.descripcionUltimaCasilla = "";
		this.esPartidaComenzada = false;
		System.out.println("Nueva partida");
	}

	@Override
	public boolean esPartidaComenzada() throws RemoteException {
		return esPartidaComenzada;
	}
	
	// Partida en juego
	public void iniciarJuego() throws RemoteException {
		if (jugadores.size()==cantJugadores) {
			this.esPartidaComenzada = true;
			tablero.inicializar(jugadores);
			Jugador jugador = jugadores.peek();
			descripcionUltimaCasilla = "La partida ha comenzado! Comienza el <font color="+jugador.getFicha().HTMLColor+">jugador "+jugador.getNombre()+" ("+jugador.getFicha()+")</font>";
			notificarObservadores(Eventos.COMENZAR_PARTIDA);
			System.out.println("La partida ha comenzado! Comienza el jugador "+jugadores.peek().getNombre()+" ("+jugador.getFicha()+")");
			System.out.println("------------------------------------");
		}

	}
			
	private void terminarJuego(Jugador jugador) throws RemoteException {
		ganador = jugador;
		System.out.println("Partida terminada");

		if (ranking.containsKey(ganador.getNombre())) {
			ranking.replace(jugador.getNombre(), ranking.get(jugador.getNombre())+1);	// Si existe el nombre en la tabla, incrementa su score en +1
		} else {
			ranking.put(jugador.getNombre(), 1);
		}
		notificarObservadores(Eventos.FIN_JUEGO);
		esPartidaComenzada = false;
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
			System.out.println("El jugador "+jugador.getNombre()+" ("+jugador.getFicha()+") ha entrado a la partida");
		} catch (IllegalStateException e) {
			notificarObservadores(Eventos.LIMITE_JUGADORES);
		}
		
		return jugador;
	}
	
	@Override
	public IJugador entrarPartidaGuardada(String nombre) throws RemoteException {
		for (Jugador jugador : jugadores) {
			if (jugador.getNombre().equals(nombre)) {
				notificarObservadores(Eventos.JUGADOR_PARTIDA_GUARDADA);
				return jugador;
			}
		}
		return null;
	}
		
	@Override
	public void salir(IJugador jugadorSalir) throws RemoteException {	// sale un jugador de la partida
		for (Jugador jugador : jugadores) {
			if (jugador.getFicha()==jugadorSalir.getFicha()) {
				jugadores.remove(jugador);			
				System.out.println("El jugador "+jugador.getNombre()+" ("+jugador.getFicha()+") se ha retirado de la partida");
			}
		}

		notificarObservadores(Eventos.JUGADOR_ELIMINADO);
	}
	
	@Override
	public void guardarPartida() throws RemoteException {
		System.out.println("Partida guardada");
		notificarObservadores(Eventos.PARTIDA_GUARDADA);
		cerrar();
	}

	public void cerrar() {
		System.out.println("Se cerró el servidor");
		System.exit(0);
	}


	public String mostrarDado() throws RemoteException {
		return dado.cara();
	}


	public IJugador jugarTurno() throws RemoteException {
		Jugador jugador = jugadores.peek();
		System.out.println("Turno de: "+jugador.getNombre());
		Eventos evento = jugador.jugar(tablero, dado);
		descripcionUltimaCasilla = jugador.getDescripcionCasillaActual();

		System.out.println("El jugador "+jugador.getNombre()+" ("+jugador.getFicha()+") lanzó "+jugador.getUltimaTirada());
		System.out.println(evento);
		System.out.println();
		System.out.println("------------------------------------");

		if (jugador.gano(tablero)) {
			descripcionUltimaCasilla += "<br><font color="+jugador.getFicha().HTMLColor+">El jugador "+jugador.getNombre()+" ("+jugador.getFicha()+") ha ganado!</font></br>";
			terminarJuego(jugador);
			return jugador;
		}

		if (evento != Eventos.TURNO_GANADO) {
			jugadores.add(jugadores.poll());
		}
		
		notificarObservadores(evento);	
		return jugador;
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

	@Override
	public Map<String, Integer> getRanking() throws RemoteException {
		return ranking;
	}
	
	@Override
	public String toString() {
		return "esPartidaComenzada: "+esPartidaComenzada+"\n"+
				"Jugador actual: "+jugadores.peek().getNombre()+" ("+jugadores.peek().getFicha()+")\n";
	}

}
