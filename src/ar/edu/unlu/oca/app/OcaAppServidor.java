package ar.edu.unlu.oca.app;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ar.edu.unlu.oca.modelo.IJuego;
import ar.edu.unlu.oca.modelo.Juego;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.servidor.Servidor;

public class OcaAppServidor {
	public static final boolean DEV = true;
	
	public static void main(String[] args) {
		String ip;
		String port;
		String jugadores;
		if (DEV) {
			ip = "127.0.0.1";
			port = "8888";
			jugadores = "2";
		} else {
			ArrayList<String> ips = Util.getIpDisponibles();
			ip = (String) JOptionPane.showInputDialog(
					null, 
					"Seleccione la IP en la que escuchará peticiones el servidor", "IP del servidor", 
					JOptionPane.QUESTION_MESSAGE, 
					null,
					ips.toArray(),
					null
					);
			port = (String) JOptionPane.showInputDialog(
					null, 
					"Seleccione el puerto en el que escuchará peticiones el servidor", "Puerto del servidor", 
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					8888
					);
			jugadores = (String) JOptionPane.showInputDialog(
					null, 
					"Seleccione la cantidad de jugadores para la partida", "Cantidad de jugadores", 
					JOptionPane.QUESTION_MESSAGE,
					null,
					new String[]{"2", "3", "4"},
					8888
					);
		}
		
		Juego modelo = new Juego(Integer.parseInt(jugadores));
		Servidor servidor = new Servidor(ip, Integer.parseInt(port));
		try {
			servidor.iniciar(modelo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RMIMVCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
