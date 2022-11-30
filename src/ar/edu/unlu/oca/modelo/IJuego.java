package ar.edu.unlu.oca.modelo;

import java.util.ArrayList;
import java.util.EnumSet;

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
