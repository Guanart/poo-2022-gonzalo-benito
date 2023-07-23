package ar.edu.unlu.oca.vista;

import java.util.ArrayList;
import java.util.EnumSet;

import ar.edu.unlu.oca.controlador.Controlador;
import ar.edu.unlu.oca.modelo.Ficha;
import ar.edu.unlu.oca.modelo.IJugador;
import ar.edu.unlu.oca.modelo.Tablero;

public interface IVista {
	void iniciar();
	void setControlador(Controlador controlador);
//	void mostrarMenu();
	
	// Configuracion nueva partida
	void nuevaPartida();
	void mostrarJugadores(ArrayList<IJugador> jugadores);
	void mostrarFichas(EnumSet<Ficha> fichasDisponibles);
	void cargarJugador(String input);
	void iniciarPartida();
	void mostrarTurno(IJugador jugadorActual);
	void mostrarDescripcionCasilla(String descripcionCasilla);
	void mostrarDado(String valorDado);
	void mostrarGanador(IJugador jugadorActual);
	void actualizarTablero(Tablero tablero);
 
}

