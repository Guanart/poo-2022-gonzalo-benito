package ar.edu.unlu.oca.controlador;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Map;

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
//			mostrarJugadores();
			break;
		case JUGADOR_ELIMINADO:
			mostrarJugadores();
			break;
		case JUGADOR_PARTIDA_GUARDADA:
			actualizarTablero();
			mostrarDescripcionCasilla();
			break;
		case COMENZAR_PARTIDA:
			// TODO: podría configurar algo especial al comienzo de la partida. Ej: orden de jugadores
			actualizarTablero();
			mostrarDescripcionCasilla();
			mostrarTurno();
			break;
		case ESTA_EN_POZO:
			actualizarTablero();
			mostrarDescripcionCasilla();
			mostrarTurno();
			break;
		case TURNO_PERDIDO:
			actualizarTablero();
			mostrarDescripcionCasilla();
			mostrarTurno();
			break;
		case TURNO_GANADO:
			actualizarTablero();
			mostrarDescripcionCasilla();
			mostrarTurno();
			break;
		case TURNO_TERMINADO:
			actualizarTablero();
			mostrarDescripcionCasilla();
			mostrarTurno();
			break;
		case FIN_JUEGO:
			actualizarTablero();
			mostrarDescripcionCasilla();
			terminarJuego();
			break;
		case PARTIDA_GUARDADA:
			partidaGuardada();
			break;
		default:
			break;
		}		
	}
	

	private void partidaGuardada() {
		try {
			for (IVista vista : vistas)
				vista.partidaGuardada();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void terminarJuego() {
		try {
			for (IVista vista : vistas)
				vista.terminarJuego(modelo.getJugadorActual());
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
			this.jugador = modelo.jugarTurno();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cargarJugador(String nombre, int color) {
		try {
			// Al agregar el último jugador, no da tiempo al ultimo controlador de asignar this.jugador, porque comienza la partida (luego lo termina asignando)
			this.jugador = modelo.cargarJugador(nombre, color);
			mostrarJugadores();
			modelo.iniciarJuego(); // Arranca si completo el cupo de jugadores
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void entrarPartidaGuardada(String nombre) {
		try {
			// Al agregar el último jugador, no da tiempo al ultimo controlador de asignar this.jugador, porque comienza la partida (luego lo termina asignando)
			this.jugador = modelo.entrarPartidaGuardada(nombre);
			mostrarJugadores();
			mostrarTurno();
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
	
	public IJugador getJugador() {
		return this.jugador;
	}

	public void salir() {
		try {
			modelo.salir(jugador);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean esPartidaComenzada() {
		boolean esPartidaComenzada = false;
		try {
			esPartidaComenzada = modelo.esPartidaComenzada();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esPartidaComenzada;
	}

	public Map<String, Integer> getRanking() {
		Map<String, Integer> ranking = null;
		try {
			ranking = modelo.getRanking();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ranking;
	}

}
