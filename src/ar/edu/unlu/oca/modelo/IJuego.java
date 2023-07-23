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
	void jugarTurno() throws RemoteException;
	String getCasillaActual() throws RemoteException;
	String mostrarDado() throws RemoteException;
	void cerrar() throws RemoteException;
	Tablero getTablero();
}