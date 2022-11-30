package ar.edu.unlu.oca.controlador;

import java.util.ArrayList;
import java.util.EnumSet;

import ar.edu.unlu.oca.modelo.Ficha;
import ar.edu.unlu.oca.modelo.IJugador;
import ar.edu.unlu.oca.modelo.Jugador;

public interface IJuego {
	// Configuración
	void cargarJugador(String nombre, int color);
	void iniciarJuego();
	ArrayList<IJugador> getJugadores();
	EnumSet<Ficha> fichasDisponibles();
	IJugador getJugadorActual();
	void jugarTurno();
	String getCasillaActual();
	String mostrarDado();

}
