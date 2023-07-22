package ar.edu.unlu.oca.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.EnumSet;

import javax.swing.JOptionPane;

import ar.edu.unlu.oca.controlador.Controlador;
import ar.edu.unlu.oca.gui.VentanaPrincipalGrafica;
import ar.edu.unlu.oca.modelo.Ficha;
import ar.edu.unlu.oca.modelo.IJugador;
import ar.edu.unlu.oca.gui.VentanaInicioSesion;

public class VistaGrafica implements IVista {


	private VentanaInicioSesion vInicioSesion;
	private VentanaPrincipalGrafica vPrincipal;
	private Controlador controlador;

	
//	public VistaConsola(Controlador controlador) {
//		setControlador(controlador);
//		this.vPrincipal = new VentanaPrincipalConsola();
//		// Agrega el comportamiento (ActionListener) que debe efectuar al hacer click
//		this.vPrincipal.onClickEnviar(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				clickHandler(vPrincipal.getTextoInput().trim());
//				vPrincipal.setTextoInput("");
//			}
//		});
//
//		this.vPrincipal.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent arg0) {
//				controlador.cerrarApp();
//			}
//		});
//
//	}

	
	public VistaGrafica(Controlador controlador) {
		super();
		this.vInicioSesion = new VentanaInicioSesion();
		this.vPrincipal = new VentanaPrincipalGrafica();
		setControlador(controlador);		
		// Agrega el comportamiento (ActionListener) que debe efectuar al hacer click 
		this.vInicioSesion.onClickIniciar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controlador.cargarJugador(vInicioSesion.getNombreUsuario(), vInicioSesion.getFicha());
				mostrarJuego();
			}
		});
		this.vPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
		        if (JOptionPane.showConfirmDialog(vPrincipal, 
			            "¿Estás seguro que quieres salir de la partida?", "¿Cerrar juego?", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
			            controlador.cerrarApp();
			            System.exit(0);
			        }
			}			
		});
		
		this.vPrincipal.onClickDados(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.jugarTurno();
			}
		});

		
	}
	
	@Override
	public void mostrarJugadores(ArrayList<IJugador> usuarios) {
		this.vPrincipal.actualizarListaUsuarios(usuarios);
	}

	
		
	public void iniciar() {
		this.mostrarInicioSesion();
	}
	
	private void mostrarJuego() {
		this.vInicioSesion.setVisible(false);
		this.vPrincipal.setVisible(true);
	}
		
	private void mostrarInicioSesion() {
		this.vInicioSesion.setVisible(true);
		this.vPrincipal.setVisible(false);
	}

	@Override
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
		controlador.agregarVista(this);
	}

	@Override
	public void nuevaPartida() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarFichas(EnumSet<Ficha> fichasDisponibles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cargarJugador(String input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void iniciarPartida() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarTurno(IJugador jugadorActual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarCasilla(String descripcionCasilla) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarDado(String valorDado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarGanador(IJugador jugadorActual) {
		// TODO Auto-generated method stub
		
	}

}
