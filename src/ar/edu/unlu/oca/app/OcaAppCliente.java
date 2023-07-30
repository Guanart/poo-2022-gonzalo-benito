package ar.edu.unlu.oca.app;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ar.edu.unlu.oca.controlador.Controlador;
import ar.edu.unlu.oca.vista.IVista;
import ar.edu.unlu.oca.vista.VistaConsola;
import ar.edu.unlu.oca.vista.VistaGrafica;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.cliente.Cliente;

public class OcaAppCliente {
	
	public static final boolean DEV = true;

	public static void main(String[] args) {
		
		String ip;
		String port;
		String ipServidor;
		String portServidor;

		if (DEV) {
			ip = "127.0.0.1";
			ipServidor = "127.0.0.1";
			portServidor = "8888";
			port = (String) JOptionPane.showInputDialog(
					null, 
					"Seleccione el puerto en el que escuchará peticiones el cliente", "Puerto del cliente", 
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					10000
					);
		} else {
			ArrayList<String> ips = Util.getIpDisponibles();
			ip = (String) JOptionPane.showInputDialog(
					null, 
					"Seleccione la IP en la que escuchará peticiones el cliente", "IP del cliente", 
					JOptionPane.QUESTION_MESSAGE, 
					null,
					ips.toArray(),
					null
					);
			port = (String) JOptionPane.showInputDialog(
					null, 
					"Seleccione el puerto en el que escuchará peticiones el cliente", "Puerto del cliente", 
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					10000
					);
			ipServidor = (String) JOptionPane.showInputDialog(
					null, 
					"Seleccione la IP en la corre el servidor", "IP del servidor", 
					JOptionPane.QUESTION_MESSAGE, 
					null,
					null,
					null
					);
			portServidor = (String) JOptionPane.showInputDialog(
					null, 
					"Seleccione el puerto en el que corre el servidor", "Puerto del servidor", 
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					8888
					);
		}
		Controlador controlador = new Controlador();
//		IVista vista = new VistaGrafica(controlador);
		IVista vista = new VistaConsola(controlador);
		Cliente c = new Cliente(ip, Integer.parseInt(port), ipServidor, Integer.parseInt(portServidor));
		vista.iniciar();
		try {
			c.iniciar(controlador);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RMIMVCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
