package ar.edu.unlu.oca.controlador;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.EnumSet;

import ar.edu.unlu.oca.modelo.Ficha;
import ar.edu.unlu.oca.modelo.IJuego;
import ar.edu.unlu.oca.modelo.IJugador;
import ar.edu.unlu.oca.modelo.Tablero;
import ar.edu.unlu.oca.vista.IVista;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

// Las Exceptions serán RemoteException

public class Controlador implements IControladorRemoto, Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<IVista> vistas = new ArrayList<IVista>();
	private IJuego modelo;
	private IJugador jugador;

	public Controlador() {		

	}
	
	@Override
	public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
		this.modelo = (IJuego) modeloRemoto;
		// Verificar si la partida comenzó
		fichasDisponibles();
	}
	
	public void agregarVista(IVista vista) {
		vistas.add(vista);
	}	

	@Override
	public void actualizar(IObservableRemoto modelo, Object args) throws RemoteException {
		Eventos evento = (Eventos) args;
		switch(evento) {
		case JUGADOR_AGREGADO:
			mostrarJugadores();
			break;
		case COMENZAR_PARTIDA:
			// TODO: podría configurar algo especial al comienzo de la partida. Ej: orden de jugadores
			actualizarTablero();
			mostrarTurno();
			break;
//		case LIMITE_JUGADORES:
//			
//			break;
//		case MOSTRAR_CASILLA_DADOS:		// PODRIA SER LO MISMO QUE TURNO_TERMINADO
//			mostrarDados();
//			mostrarCasilla();
//			break;
		case TURNO_TERMINADO:
			mostrarDados();
			actualizarTablero();
			mostrarDescripcionCasilla();
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
		} catch (RemoteException e) {
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

	private void mostrarDescripcionCasilla() {
		try {
			for (IVista vista : vistas)
				vista.mostrarDescripcionCasilla(modelo.getCasillaActual());
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
		EnumSet<Ficha> fichasDisponibles;
		try {
			fichasDisponibles = modelo.fichasDisponibles();
			for (IVista vista : vistas) {
				vista.mostrarFichas(fichasDisponibles);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/*
	 * ###########################
	 * LLAMADAS AL MODELO
	 * ###########################
	 */
	
	public void iniciarPartida() {
		try {
			modelo.iniciarJuego();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void jugarTurno() {
		try {
			modelo.jugarTurno();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cargarJugador(String nombre, int color) {
		try {
			// Al agregar el último jugador, no da tiempo al ultimo controlador de asignar this.jugador, porque comienza la partida (luego lo termina asignando)
			this.jugador = modelo.cargarJugador(nombre, color);
			for (IVista vista : vistas) {
				modelo.iniciarJuego(); // Arranca si completo el cupo de jugadores
				vista.mostrarJugadores(modelo.getJugadores());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void actualizarTablero() {
		try {
			for (IVista vista : vistas) {
				vista.actualizarTablero(modelo.getTablero());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	// TODO: con RMI, debería cerrar la vista creo
	public void cerrarApp() {
		try {
			this.modelo.cerrar();  	// TODO -> cerrar correctamente, de manera no brusca
			System.exit(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public IJugador getJugador() {
		return this.jugador;
	}

}
