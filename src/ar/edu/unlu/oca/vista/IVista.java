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
	
	// Configuracion nueva partida
	void mostrarJugadores(ArrayList<IJugador> jugadores);
	void mostrarFichas(EnumSet<Ficha> fichasDisponibles);
	void mostrarTurno(IJugador jugadorActual);
	void mostrarDescripcionCasilla(String descripcionCasilla);
	void actualizarTablero(Tablero tablero);
	void terminarJuego(IJugador iJugador);
//	void mostrarDados(IJugador jugadorActual);
 
}

