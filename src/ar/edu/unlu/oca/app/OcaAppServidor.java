package ar.edu.unlu.oca.app;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ar.edu.unlu.oca.modelo.IJuego;
import ar.edu.unlu.oca.modelo.Juego;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.servidor.Servidor;
import ar.edu.unlu.oca.services.Serializador;

public class OcaAppServidor {
	public static final boolean DEV = true;
    private static Serializador serializador = new Serializador("datos.dat");

	public static void main(String[] args) {
		
		///////////////////////////////////////
		///			CONFIG INICIAL			///
		///////////////////////////////////////

		String ip;
		String port;
		String jugadores;
		String cargarPartida;
		
		if (DEV) {
			ip = "127.0.0.1";
			port = "8888";
			jugadores = "2";
			cargarPartida = "Cargar partida";
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
		}
	
		///////////////////////////////////////
		///			CREAR ARCHIVO			///
		///////////////////////////////////////
		
        File archivo = new File("datos.dat");
        if (!archivo.exists()) {
        	try {
        		archivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }

		///////////////////////////////////////
		///			LEER ARCHIVO			///
		///////////////////////////////////////		

		Object guardado = serializador.readFirstObject();
        
		///////////////////////////////////////
		///			CREAR PARTIDA			///
		///////////////////////////////////////		

		Juego modelo;
		if (guardado == null) {
			modelo = new Juego();
			cargarPartida = (String) JOptionPane.showInputDialog(
					null, 
					"¿Iniciar una nueva partida?", "Iniciar partida", 
					JOptionPane.QUESTION_MESSAGE,
					null,
					new String[]{"Nueva partida"},
					"Nueva partida"
					);		
		} else {
				modelo = (Juego) guardado;
				
				try {
					if (modelo.esPartidaComenzada()) {
						cargarPartida = (String) JOptionPane.showInputDialog(
								null, 
								"¿Desea cargar su última partida guardada? ¿O iniciar una nueva partida?", "Cargar partida", 
								JOptionPane.QUESTION_MESSAGE,
								null,
								new String[]{"Nueva partida", "Cargar partida"},
								"Nueva partida"
								);
					} else {
						cargarPartida = (String) JOptionPane.showInputDialog(
								null, 
								"¿Iniciar una nueva partida?", "Iniciar partida", 
								JOptionPane.QUESTION_MESSAGE,
								null,
								new String[]{"Nueva partida"},
								"Nueva partida"
								);				
					}
				} catch (RemoteException e) {
				
				}
			}
									
		if (cargarPartida.equalsIgnoreCase("Nueva partida")) {
			jugadores = (String) JOptionPane.showInputDialog(
					null, 
					"Seleccione la cantidad de jugadores para la partida", "Cantidad de jugadores", 
					JOptionPane.QUESTION_MESSAGE,
					null,
					new String[]{"2", "3", "4"},
					2
					);
			modelo.nuevaPartida(Integer.parseInt(jugadores));
		}
		
		try {			
			System.out.println(modelo);
		} catch (Exception e) {
			
		}
		
		///////////////////////////////////////
		///			LEVANTAR SERVER			///
		///////////////////////////////////////		

		Servidor servidor = new Servidor(ip, Integer.parseInt(port));
		try {
			servidor.iniciar(modelo);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (RMIMVCException e) {
			e.printStackTrace();
		}
		
		
		///////////////////////////////////////
		///			GUARDAR ARCHIVO			///
		///////////////////////////////////////

		String guardarPartida = "No";
		while (true) {			
			guardarPartida = (String) JOptionPane.showInputDialog(
					null, 
					"¿Desea salir y guardar la partida en su estado actual?", "Guardar partida", 
					JOptionPane.QUESTION_MESSAGE,
					null,
//					new String[]{"Si", "No"},
					new String[]{"Si"},
					"Si"
					);
			try {
				if (guardarPartida == null) {
					break;
				}
				if (guardarPartida.equalsIgnoreCase("Si")) {
	            	try {	            		
	            		archivo.delete();
	            		archivo.createNewFile();
	            	} catch (Exception e) {
	        			e.printStackTrace();
	            	}
					serializador.writeOneObject(modelo);
					modelo.guardarPartida();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
