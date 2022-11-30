package ar.edu.unlu.oca.controlador;

import java.util.ArrayList;
import java.util.EnumSet;

import ar.edu.unlu.oca.modelo.Ficha;
import ar.edu.unlu.oca.modelo.IJuego;
import ar.edu.unlu.oca.utils.Observable;
import ar.edu.unlu.oca.utils.Observador;
import ar.edu.unlu.oca.vista.IVista;

// Las Exceptions serán RemoteException

public class Controlador implements Observador {

	private ArrayList<IVista> vistas = new ArrayList<IVista>();
	private IJuego modelo;

	public Controlador() {		

	}
	
	public void agregarVista(IVista vista) {
		vistas.add(vista);
	}

	public void setModelo(IJuego modelo) {
		this.modelo = modelo;
		((Observable) this.modelo).agregarObservador(this);
	}
	
	@Override
	public void notificar(Observable o, Object args) {
		Eventos evento = (Eventos) args;
		switch(evento) {
		case JUGADOR_AGREGADO:
			mostrarJugadores();
			break;
		case COMENZAR_PARTIDA:
			// TODO: podría configurar algo especial al comienzo de la partida. Ej: orden de jugadores
			mostrarTurno();
			break;
		case LIMITE_JUGADORES:
			
			break;
		case MOSTRAR_CASILLA_DADOS:
			mostrarDados();
			mostrarCasilla();
			break;
		case TURNO_TERMINADO:
			mostrarTurno();
			break;
		case FIN_JUEGO:
			terminarJuego();
			break;
		default:
			break;
		}
	}

	/*
	 * ###########################
	 * ACTUALIZACIONES DE LA VISTA
	 * ###########################
	 */

	
	// TODO: podría hacer una función que reciba como parámetros los distintos métodos a ejecutar?
	// Así me ahorro los bucles for, y los try/catch

	private void terminarJuego() {
		mostrarGanador();
		try {
			for (IVista vista : vistas)
				vista.nuevaPartida();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}

	private void mostrarGanador() {
		try {
			for (IVista vista : vistas)
				vista.mostrarGanador(modelo.getJugadorActual());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void mostrarDados() {
		try {
			for (IVista vista : vistas)
				vista.mostrarDado(modelo.mostrarDado());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}

	private void mostrarCasilla() {
		try {
			for (IVista vista : vistas)
				vista.mostrarCasilla(modelo.getCasillaActual());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	private void mostrarTurno() {
		try {
			for (IVista vista : vistas)
				vista.mostrarTurno(modelo.getJugadorActual());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void mostrarJugadores() {
		try {
			for (IVista vista : vistas)
				vista.mostrarJugadores(modelo.getJugadores());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void fichasDisponibles() {
		EnumSet<Ficha> fichasDisponibles = modelo.fichasDisponibles();
		for (IVista vista : vistas) {
			vista.mostrarFichas(fichasDisponibles);
		}
	}


	/*
	 * ###########################
	 * LLAMADAS AL MODELO
	 * ###########################
	 */
	
	public void iniciarPartida() {
		modelo.iniciarJuego();
	}

	public void jugarTurno() {
		modelo.jugarTurno();
	}

	public void cargarJugador(String nombre, int color) {
		try {
			modelo.cargarJugador(nombre, color);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	// TODO: con RMI, debería cerrar la vista creo
	public void cerrarApp() {
		try {
//			this.modelo.cerrar();  	TODO -> cerrar correctamente, de manera no brusca
			System.exit(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
