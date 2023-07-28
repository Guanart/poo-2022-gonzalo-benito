package ar.edu.unlu.oca.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.EnumSet;

import javax.swing.JFrame;

import ar.edu.unlu.oca.controlador.Controlador;
import ar.edu.unlu.oca.gui.VentanaPrincipalConsola;
import ar.edu.unlu.oca.modelo.Ficha;
import ar.edu.unlu.oca.modelo.IJugador;
import ar.edu.unlu.oca.modelo.Tablero;

public class VistaConsola extends JFrame implements IVista {

	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VentanaPrincipalConsola vPrincipal;
	private Controlador controlador;
	private Enum<?> estadoActual = OpcionesMenuPrincipal.INICIO;
	// Durante la carga de jugadores, utilizo:
	private String nombreJugadorActual = "-1";	
	private int nroJugadores = -1;
	private String fichaJugadorActual;

	public VistaConsola(Controlador controlador) {
		super();
		setControlador(controlador);
		
		this.vPrincipal = new VentanaPrincipalConsola();
		this.vPrincipal.onClickEnviar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clickHandler(vPrincipal.getTextoInput().trim());
				vPrincipal.setTextoInput("");
			}
		});

		this.vPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				controlador.salir();
				System.exit(0);
			}
		});

	}

	public void clickHandler(String input) {

		if (estadoActual == OpcionesMenuPrincipal.INICIO) {
			switch (input) {
			case "1":
				nuevaPartida();
				break;
			case "2":
				verHistorico();
				break;
			case "3":
				controlador.salir();
				break;
			default:
				println("Opción no válida");
			}
		} else if (estadoActual == OpcionesMenuPrincipal.NUEVA_PARTIDA) {
			switch (input) {
			case "1":
				iniciarPartida();
				break;
			case "2":
				cargarJugadores();
				break;
			case "3":
				controlador.mostrarJugadores();
				nuevaPartida();
				break;
			case "4":
				menuPrincipal();
				break;
			default:
				println("Opción no válida");
			}

		} else if (estadoActual == OpcionesMenuNuevaPartida.CARGAR_JUGADORES) {
			
			if (nroJugadores == -1) {
				if (validarCantJugadores(Integer.parseInt(input))) {					
					println("Cantidad de jugadores inválida");
					return;
				}
				nroJugadores = Integer.parseInt(input);
			}
					
			if (nombreJugadorActual == "-1") { // primer jugador
				println("\nNombre jugador: ");
				nombreJugadorActual = null;
			} else {
				cargarJugador(input);
			}
			
			if (nroJugadores == 0) {
				nroJugadores = -1;
				nombreJugadorActual = "-1";
				estadoActual = OpcionesMenuPrincipal.NUEVA_PARTIDA;
				nuevaPartida();
				return;
			}
		} else if (estadoActual == OpcionesMenuNuevaPartida.INICIAR) {
			controlador.jugarTurno();
		}
	}

	public void println(String texto) {
		vPrincipal.setTextoHistorico(texto + "\n");
	}
	
	public void print(String texto) {
		vPrincipal.setTextoHistorico(texto);
	}

	public void println() {
		println("");
	}

	@Override
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
		controlador.agregarVista(this);
	}

	@Override
	public void iniciar() {
		this.vPrincipal.setVisible(true);
		menuPrincipal();
	}

	private void menuPrincipal() {
		estadoActual = OpcionesMenuPrincipal.INICIO;
		println("------------------------------------------------");
        for (OpcionesMenuPrincipal e : OpcionesMenuPrincipal.values()) {
            println(e.label);
        }
        println();
	}
	
	private void verHistorico() {
		// TODO Auto-generated method stub
		
	}

	public void nuevaPartida() {
		println("------------------------------------------------");
		estadoActual = OpcionesMenuPrincipal.NUEVA_PARTIDA;
        for (OpcionesMenuNuevaPartida e : OpcionesMenuNuevaPartida.values()) {
            println(e.label);
        }
        println();
	}
	
	public void mostrarFichas(EnumSet<Ficha> fichasDisponibles) {
		for (Ficha ficha : fichasDisponibles) {
            println(ficha.opcion+") "+ficha.label);
		}
        println();
	}
	
	@Override
	public void mostrarJugadores(ArrayList<IJugador> jugadores) {
		println("------------------------------------------------");
		println("[*] LISTA DE JUGADORES\n");
		for (IJugador jugador : jugadores) {
			println("Nombre: "+jugador.getNombre());
			println("Ficha: "+jugador.getFicha());
			println();
		}
	}

	private void cargarJugadores() {
		estadoActual = OpcionesMenuNuevaPartida.CARGAR_JUGADORES;
		println("Ingrese cantidad de jugadores [2,4]: ");	
	}
	
	private boolean validarCantJugadores(int cantidad) {
		return cantidad < 2 || cantidad > 4;
	}

	@Override
	public void cargarJugador(String input) {
		if (nombreJugadorActual == null) {
			nombreJugadorActual = input;
			println("Elija una ficha:");
			controlador.fichasDisponibles();
		} else {
			fichaJugadorActual = input;
			controlador.cargarJugador(nombreJugadorActual, Integer.parseInt(fichaJugadorActual));
			nombreJugadorActual = null;
			--nroJugadores;
			if (nroJugadores > 0) {
				println("\nNombre jugador: ");
			}
		}			
	}
	
	
	/*
	 * PARTIDA
	 */
	
	@Override
	public void mostrarTurno(IJugador jugadorActual) {
		println("------------------------------------------------");
		println("Es el turno del jugador: "+jugadorActual.getNombre());
		println("1) Tirar dados y avanzar");
	}

	@Override
	public void mostrarCasilla(String descripcionCasilla) {
		println("Avanza a la "+descripcionCasilla);
		println();
	}
	
	@Override
	public void mostrarDado(String valorDado) {
		println("Valor dados: "+valorDado);
	}

	@Override
	public void mostrarGanador(IJugador jugador) {
		estadoActual = OpcionesMenuPrincipal.NUEVA_PARTIDA;
		println("EL JUGADOR: "+jugador.getNombre()+" HA GANADO");		
	}

	@Override
	public void mostrarDescripcionCasilla(String descripcionCasilla) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarTablero(Tablero tablero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminarJuego(IJugador iJugador) {
		// TODO Auto-generated method stub
		
	}


}
