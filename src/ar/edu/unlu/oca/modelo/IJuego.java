package ar.edu.unlu.oca.modelo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.EnumSet;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IJuego extends IObservableRemoto {
	// Configuración
	IJugador cargarJugador(String nombre, int color) throws RemoteException;
	void iniciarJuego() throws RemoteException;
	ArrayList<IJugador> getJugadores() throws RemoteException;
	EnumSet<Ficha> fichasDisponibles() throws RemoteException;
	IJugador getJugadorActual() throws RemoteException;
	IJugador jugarTurno() throws RemoteException;
	String getCasillaActual() throws RemoteException;
	String mostrarDado() throws RemoteException;
	Tablero getTablero() throws RemoteException;
	void salir(IJugador jugador) throws RemoteException;
	boolean esPartidaComenzada() throws RemoteException;
	IJugador entrarPartidaGuardada(String nombre) throws RemoteException;
}