package ar.edu.unlu.oca.app;

import ar.edu.unlu.oca.controlador.Controlador;
import ar.edu.unlu.oca.controlador.IJuego;
import ar.edu.unlu.oca.modelo.Juego;
import ar.edu.unlu.oca.vista.IVista;
import ar.edu.unlu.oca.vista.VistaConsola;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public class OcaApp {

	public static void main(String[] args) {
		Controlador controlador1 = new Controlador();
		Controlador controlador2 = new Controlador();
		IVista vistaConsola1 = new VistaConsola(controlador1);
		IVista vistaConsola2 = new VistaConsola(controlador2);
		vistaConsola1.iniciar();
//		vistaConsola2.iniciar();
		
		// Con RMI, se debería setear el modelo remoto con "public <T extends IObservableRemoto> Controlador(T modelo)"
		// cliente.iniciar(controlador);   donde "cliente" es de tipo "Cliente" (rmi)
		IJuego modelo = new Juego();
		controlador1.setModelo(modelo);
		controlador2.setModelo(modelo);
	}

}
