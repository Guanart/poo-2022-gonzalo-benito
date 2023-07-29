package ar.edu.unlu.oca.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.EnumSet;

import javax.swing.JFrame;

import ar.edu.unlu.oca.controlador.Controlador;
import ar.edu.unlu.oca.controlador.Eventos;
import ar.edu.unlu.oca.gui.VentanaPrincipalConsola;
import ar.edu.unlu.oca.modelo.Ficha;
import ar.edu.unlu.oca.modelo.IJugador;
import ar.edu.unlu.oca.modelo.Tablero;
import ar.edu.unlu.oca.modelo.casillas.Casilla;


public class VistaConsola extends JFrame implements IVista {

	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VentanaPrincipalConsola vPrincipal;
	private Controlador controlador;
	private Enum<?> estadoActual;;
	// Durante la carga de jugadores, utilizo:
	private EnumSet<Ficha> fichasDisponibles;
	private int ficha;
	private String nombreJugador;
	

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
				if (controlador.getJugador() != null) {					
					controlador.salir();
				}
				System.exit(0);
			}
		});
	}

	private void clickHandler(String input) {
		if (estadoActual == OpcionesMenuPrincipal.INICIO) {
			switch (input) {
			case "1":
				nombreJugador = "";
				ficha = ((Ficha) fichasDisponibles.toArray()[0]).opcion;
				menuEntrarPartida();
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
		} else if (estadoActual == OpcionesMenuEntrarPartida.INICIO) {
			switch (input) {
			case "1":
				entrarPartida();
				break;
			case "2":
				println("Ingrese su nombre: ");
				estadoActual = OpcionesMenuEntrarPartida.CARGAR_NOMBRE;
				break;
			case "3":
				estadoActual = OpcionesMenuEntrarPartida.SELECCIONAR_FICHA;
				controlador.fichasDisponibles();
				println("Ingrese su ficha: ");
				break;
			case "4":
				menuPrincipal();
				break;
			default:
				println("Opción no válida");
			}
		} else if (estadoActual == OpcionesMenuEntrarPartida.CARGAR_NOMBRE) {
			this.nombreJugador = input;
			estadoActual = OpcionesMenuEntrarPartida.INICIO;
			menuEntrarPartida();
		} else if (estadoActual == OpcionesMenuEntrarPartida.SELECCIONAR_FICHA) {
			try {
				this.ficha = Integer.parseInt(input);
				if (ficha>=1 && ficha <=4) {					
					estadoActual = OpcionesMenuEntrarPartida.INICIO;
					menuEntrarPartida();
				}
			} catch (Exception e) {
				println("Ingrese una opción válida");
			}
		} else if (estadoActual == OpcionesPartidaEnCurso.ESPERA) {
//			controlador.jugarTurno();
		} else if (estadoActual == OpcionesPartidaEnCurso.JUEGA) {
			controlador.jugarTurno();
		}
	}

	private void println(String texto) {
		vPrincipal.setTextoHistorico("<p>"+texto+"</p>");
	}
	
	private void print(String texto) {
		vPrincipal.setTextoHistorico(texto);
	}

	private void println() {
		println("");
	}
	
	private void menuPrincipal() {
		estadoActual = OpcionesMenuPrincipal.INICIO;
		println("------------------------------------------------");
        for (OpcionesMenuPrincipal e : OpcionesMenuPrincipal.values()) {
            println(e.label);
        }
	}

	private void menuEntrarPartida() {
		estadoActual = OpcionesMenuEntrarPartida.INICIO;
		println("------------------------------------------------");
        for (OpcionesMenuEntrarPartida e : OpcionesMenuEntrarPartida.values()) {
        	if (e==OpcionesMenuEntrarPartida.CARGAR_NOMBRE) {
        		println(e.label+" ("+this.nombreJugador+")");
        	} else if (e==OpcionesMenuEntrarPartida.SELECCIONAR_FICHA) {
        		println(e.label+" ("+Ficha.values()[this.ficha-1]+")");
        	} else {        		
        		println(e.label);
        	}
        }
	}
	

	private void entrarPartida() {
		if (!nombreJugador.isBlank() && (0<ficha || ficha<5)) {		
			println("------------------------------------------------");
			print("Has ingresado a la partida!");
			controlador.cargarJugador(nombreJugador, ficha);
			estadoActual = OpcionesPartidaEnCurso.ESPERA;
		} else {
			println("Datos inválidos");
		}
	}
	

	@Override
	public void iniciar() {
		this.vPrincipal.setVisible(true);
		menuPrincipal();
	}

	@Override
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
		controlador.agregarVista(this);
	}
	
	@Override
	public void verHistorico() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminarJuego(IJugador iJugador) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mostrarFichas(EnumSet<Ficha> fichasDisponibles) {
		this.fichasDisponibles = fichasDisponibles;
		if (this.estadoActual==OpcionesMenuEntrarPartida.SELECCIONAR_FICHA) {			
			println("------------------------------------------------");
			println("[*] FICHAS DISPONIBLES");
			for (Ficha ficha : fichasDisponibles) {
				println(ficha.opcion+") "+ficha.label);
			}
			println();
		}
	}
	
	@Override
	public void mostrarJugadores(ArrayList<IJugador> jugadores) {
		print("------------------------------------------------");
		print("[*] LISTA DE JUGADORES");
		for (IJugador jugador : jugadores) {
			if(controlador.getJugador() != null && jugador.getFicha()==controlador.getJugador().getFicha()) {					
				print("<font color="+jugador.getFicha().HTMLColor+">"+jugador.getNombre()+" ("+jugador.getFicha()+") (tú)</font>");
			} else {				
				print("<font color="+jugador.getFicha().HTMLColor+">"+jugador.getNombre()+" ("+jugador.getFicha()+")</font>");
			}
		}
	}
		
	@Override
	public void actualizarTablero(Tablero tablero) throws RemoteException {
		String str = "";
		for (int i=Tablero.CASILLA_INICIAL; i<=Tablero.CASILLA_FINAL; ++i) {	
            str += "| ";
            ArrayList<IJugador> jugadores = tablero.getCasilla(i).getJugadores();
            if (!jugadores.isEmpty()) {
            	for (IJugador jugador : jugadores) {		
            		str += "<font color="+jugador.getFicha().HTMLColor+">"+jugador.getFicha()+" </font>";
				}
            	str += "\t";
            } else {            	
            	if (Tablero.CASILLAS_DADO.contains(i)) {
            		str += "DADO" + "\t";
            	} else if (Tablero.CASILLAS_OCA.contains(i)) {
            		str += "OCA" + "\t";
            	} else if (Tablero.CASILLAS_PUENTE.contains(i)) {
            		str += "PUENTE" + "\t";
            	} else if (Tablero.CASILLA_POSADA==i) {
            		str += "POSADA" + "\t";
            	} else if (Tablero.CASILLA_POZO==i) {
            		str += "POZO" + "\t";
            	} else if (Tablero.CASILLA_LABERINTO==i) {
            		str += "LABERINTO" + "\t";
            	} else if (Tablero.CASILLA_CARCEL==i) {
            		str += "CARCEL" + "\t";
            	} else if (Tablero.CASILLA_CALAVERA==i) {
            		str += "CALAVERA" + "\t";
            	} else if (Tablero.CASILLA_FINAL==i) {
            		str += "FIN" + "\t";
            	} else {
            		str += "Casilla " + i + "\t";
            	}
            }

            if (i % 10 == 0) {
                str += "|<br>";
            }
		}
		println(str);		
	}
	
	@Override
	public void mostrarTurno(IJugador jugadorActual) {
		if (jugadorActual.getFicha()==controlador.getJugador().getFicha()) {			
			print("------------------------------------------------");
			println("1) Tirar dados y avanzar");
			estadoActual = OpcionesPartidaEnCurso.JUEGA;
		} else {
			estadoActual = OpcionesPartidaEnCurso.ESPERA;
		}
	}

	@Override
	public void mostrarDescripcionCasilla(String descripcionCasilla) {
		println(descripcionCasilla);
	}

}
