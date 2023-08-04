package ar.edu.unlu.oca.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Map;

import javax.swing.JOptionPane;

import ar.edu.unlu.oca.controlador.Controlador;
import ar.edu.unlu.oca.gui.VentanaPrincipalGrafica;
import ar.edu.unlu.oca.modelo.Ficha;
import ar.edu.unlu.oca.modelo.IJugador;
import ar.edu.unlu.oca.modelo.Tablero;
import ar.edu.unlu.oca.gui.VentanaInicioSesion;

public class VistaGrafica implements IVista {

	private VentanaInicioSesion vInicioSesion;
	private VentanaPrincipalGrafica vPrincipal;
	private Controlador controlador;

	
	public VistaGrafica(Controlador controlador) {
		super();
		this.vInicioSesion = new VentanaInicioSesion();
		this.vPrincipal = new VentanaPrincipalGrafica();
		setControlador(controlador);		
				
		// Agrega el comportamiento (ActionListener) que debe efectuar al hacer click 
		this.vInicioSesion.onClickIniciar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String usuario = vInicioSesion.getNombreUsuario().trim();
				int ficha = vInicioSesion.getFicha();
				if (controlador.esPartidaComenzada()) {
					if (!usuario.isBlank()) {					
						controlador.entrarPartidaGuardada(usuario);
						mostrarJuego();
					} else {
						JOptionPane.showMessageDialog(vInicioSesion, "Ingrese su nombre", "Error", JOptionPane.WARNING_MESSAGE);
					}
				} else {					
					if (!usuario.isBlank() && ficha != 0) {					
						controlador.cargarJugador(usuario, ficha);
						mostrarJuego();
					} else {
						JOptionPane.showMessageDialog(vInicioSesion, "Ingrese un nombre y seleccione una ficha", "Error", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		this.vPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (JOptionPane.showConfirmDialog(vPrincipal, "¿Estás seguro que quieres salir de la partida?",
						"¿Cerrar juego?", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					controlador.salir();
					System.exit(0);
				}
			}
		});
		
		this.vPrincipal.onClickDados(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.jugarTurno();
			}
		});
		
		this.vPrincipal.onSpaceDados(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				controlador.jugarTurno();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		this.vPrincipal.onClickRanking(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verRanking();
            }
        });

	}
	
	private void mostrarJuego() {
		this.vInicioSesion.setVisible(false);
		this.vPrincipal.setVisible(true);
	}
			
	private void imprimirTexto(String texto) {
		this.vPrincipal.imprimirDescripcion(texto);
	}
	
	@Override
	public void iniciar() {
		this.vInicioSesion.setVisible(true);
		this.vPrincipal.setVisible(false);
	}
	
	@Override
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
		this.controlador.agregarVista(this);
	}

	@Override
	public void verRanking() {
		Map<String, Integer> ranking = controlador.getRanking();
		this.vPrincipal.verRanking(ranking);
	}

	@Override
	public void partidaGuardada() {
		imprimirTexto("[*] EL SERVIDOR FUE APAGADO, Y LA PARTIDA HA SIDO GUARDADA. POR FAVOR, LEVANTAR EL SERVIDOR Y CONECTARSE NUEVAMENTE.");
		this.vPrincipal.activarDado(false);
	}

	@Override
	public void terminarJuego(IJugador jugador) {
		this.vPrincipal.activarDado(false);
	}

	@Override
	public void mostrarFichas(EnumSet<Ficha> fichasDisponibles) {
		this.vInicioSesion.mostrarFichas(fichasDisponibles);
	}

	@Override
	public void mostrarJugadores(ArrayList<IJugador> usuarios) {
		for (IJugador iJugador : usuarios) {			
			System.out.println(iJugador.getNombre());
		}
		System.out.println(controlador.getJugador());
		this.vPrincipal.actualizarListaUsuarios(usuarios, controlador.getJugador());
	}

	@Override
	public void actualizarTablero(Tablero tablero) {
		this.vPrincipal.actualizarTablero(tablero);
	}
	
	@Override
	public void mostrarTurno(IJugador jugadorActual) {
		this.vPrincipal.activarDado(jugadorActual.getFicha()==controlador.getJugador().getFicha());
		this.vPrincipal.activarDado(controlador.getJugador());		
		this.vPrincipal.mostrarTurno(jugadorActual);		
	}
	
	@Override
	public void mostrarDescripcionCasilla(String descripcionCasilla) {
		imprimirTexto(descripcionCasilla);		
	}
	
}
