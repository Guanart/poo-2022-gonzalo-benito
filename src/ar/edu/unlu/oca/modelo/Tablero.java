package ar.edu.unlu.oca.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

import ar.edu.unlu.oca.modelo.casillas.Casilla;
import ar.edu.unlu.oca.modelo.casillas.CasillaDado;
import ar.edu.unlu.oca.modelo.casillas.CasillaOca;
import ar.edu.unlu.oca.modelo.casillas.CasillaPerderTurno;
import ar.edu.unlu.oca.modelo.casillas.CasillaPozo;
import ar.edu.unlu.oca.modelo.casillas.CasillaPuente;
import ar.edu.unlu.oca.modelo.casillas.CasillaTransportar;

public class Tablero implements Serializable {
	private static final long serialVersionUID = -4802809576529065351L;
	final static int CASILLA_INICIAL = 0;
    final static int CASILLA_FINAL = 63;
    final static int CASILLA_POSADA = 19;
    // No podrás volver a tirar el dado hasta que otro jugador pase por esa casilla
    final static int CASILLA_POZO = 31;
    // Retrocede hasta la casilla 30
    final static int CASILLA_LABERINTO = 42;
    // Pierde dos turnos
    final static int CASILLA_CARCEL = 56;
    // Regresa a la casilla 1
    final static int CASILLA_CALAVERA = 58;
    // Sumar el número de la casilla y el número de la tirada y avanzar la cantidad resultante
    private final ArrayList<Integer> CASILLAS_DADO = new ArrayList<Integer>(Arrays.asList(26, 53));
    // Avanza a la siguiente casilla OCA
    private final ArrayList<Integer> CASILLAS_OCA = new ArrayList<Integer>(Arrays.asList(5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59));
    // Avanza a casilla posada y pierde un turno
    private final ArrayList<Integer> CASILLAS_PUENTE = new ArrayList<Integer>(Arrays.asList(6, 12));
    
	private ArrayList<Casilla> casillas = new ArrayList<Casilla>();
	
	
	private final int DERECHA = 0;
	private final int ARRIBA = 1;
	private final int IZQUIERDA = 2;
	private final int ABAJO = 3;

	public void inicializar(Queue<Jugador> jugadores) {		
		for (int i=CASILLA_INICIAL; i<=CASILLA_FINAL; ++i) {	
			Casilla casilla;
			if (CASILLAS_DADO.contains(i)) {
				casilla = new CasillaDado(i);
			} else if (CASILLAS_OCA.contains(i)) {
				casilla = new CasillaOca(i);
			} else if (CASILLAS_PUENTE.contains(i)) {
				casilla = new CasillaPuente(i);
			} else if (CASILLA_POSADA==i) {
				casilla = new CasillaPerderTurno(i);
			} else if (CASILLA_POZO==i) {
				casilla = new CasillaPozo(i);
			} else if (CASILLA_LABERINTO==i) {
				casilla = new CasillaTransportar(i);
			} else if (CASILLA_CARCEL==i) {
				casilla = new CasillaPerderTurno(i);
			} else if (CASILLA_CALAVERA==i) {
				casilla = new CasillaTransportar(i);
//			} else if (CASILLA_FINAL==i) {
			} else {
				casilla = new Casilla(i);
			}
			casillas.add(casilla);	
		}
		
//		int i = 0;
//		while (i < casillas.size()) {
//			i++;
//		}
		// Al saltar de la 27 a la 33, salta a la casilla 3
		int unidad = 60;
		int direccion = DERECHA;
		int limiteSupX = 620;
		int limiteInfX = 0;
		int limiteSupY = 375;
		int limiteInfY = 0;
		int x = -40;	// La primera x para la casilla 0 es x=20
		int y = 375;
		// Coordenadas para Interfaz Gráfica
		for (Casilla casilla : casillas) {
			switch (direccion) {
			case DERECHA:
                if (x + unidad < limiteSupX) {
                    x += unidad;
                } else {
                	limiteSupX -= unidad;
                    direccion = ARRIBA;
                    y -= unidad;
                }
    			casilla.setX(x);
    			casilla.setY(y);
                break;
			case ARRIBA:
				if (y - unidad > limiteInfY) {
					y -= unidad;
				} else {
					limiteInfY += unidad;
					direccion = IZQUIERDA;
                    x -= unidad;
				}
    			casilla.setX(x);
    			casilla.setY(y);
				break;
			case IZQUIERDA:
                if (x - unidad > limiteInfX) {
                    x -= unidad;
                } else {
                	limiteInfX += unidad;
                    direccion = ABAJO;
                    y += unidad;
                }
    			casilla.setX(x);
    			casilla.setY(y);
                break;
			case ABAJO:
				if (y + unidad < limiteSupY) {
					y += unidad;
				} else {
					limiteSupY -= unidad;
					direccion = DERECHA;
                    x += unidad;
				}
    			casilla.setX(x);
    			casilla.setY(y);
				break;
			}
		}
		
		for (Jugador jugador : jugadores) {		
			jugador.inicializar(this, getCasilla(CASILLA_INICIAL));
		}
	}
	
	
	public ArrayList<Casilla> getCasillas() {
		return casillas;
	}

	public Casilla getCasilla(int nroCasilla) {
		return casillas.get(nroCasilla);
	}
	
	public CasillaPozo getCasillaPozo() {
		return (CasillaPozo) casillas.get(CASILLA_POZO);
	}
		
}






//package ar.edu.unlu.oca.modelo.tablero;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import ar.edu.unlu.oca.modelo.tablero.acciones.AccionModificarPosicion;
//import ar.edu.unlu.oca.modelo.tablero.acciones.AccionModificarTurnos;
//import ar.edu.unlu.oca.modelo.tablero.acciones.AccionPozo;
//import ar.edu.unlu.oca.modelo.tablero.acciones.IAccion;
//
//public class Tablero {
//	private final int CASILLA_INICIAL = 0;
//    private final int CASILLA_FINAL = 63;
//    private final int CASILLA_POSADA = 19;
//    // No podrás volver a tirar el dado hasta que otro jugador pase por esa casilla
//    private final int CASILLA_POZO = 31;
//    // Retrocede hasta la casilla 30
//    private final int CASILLA_LABERINTO = 42;
//    // Pierde dos turnos
//    private final int CASILLA_CARCEL = 56;
//    // Regresa a la casilla 1
//    private final int CASILLA_CALAVERA = 58;
//    // Sumar el número de la casilla y el número de la tirada y avanzar la cantidad resultante
//    private final ArrayList<Integer> CASILLAS_DADO = new ArrayList<Integer>(Arrays.asList(26, 53));
//    // Avanza a la siguiente casilla OCA
//    private final ArrayList<Integer> CASILLAS_OCA = new ArrayList<Integer>(Arrays.asList(5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59));
//    // Avanza a casilla posada y pierde un turno
//    private final ArrayList<Integer> CASILLAS_PUENTE = new ArrayList<Integer>(Arrays.asList(6, 12));
//    
//	private ArrayList<Casilla> casillas = new ArrayList<Casilla>();
//
//	void inicializar() {
//		for (int i=0; i<=63; ++i) {	
//			ArrayList<IAccion> acciones = new ArrayList<IAccion>();
//			if (CASILLAS_DADO.contains(i)) {
//				acciones.add(new AccionModificarPosicion());
//			} else if (CASILLAS_OCA.contains(i)) {
//				acciones.add(new AccionModificarPosicion());
//			} else if (CASILLAS_PUENTE.contains(i)) {
//				acciones.add(new AccionModificarPosicion());	// se mueve a CASILLA_POSADA
//				acciones.add(new AccionModificarTurnos());
//			} else if (CASILLA_POSADA==i) {
//				acciones.add(new AccionModificarTurnos());
//			} else if (CASILLA_POZO==i) {
//				acciones.add(new AccionPozo());
//			} else if (CASILLA_LABERINTO==i) {
//				accion = new Accion();
//			} else if (CASILLA_CARCEL==i) {
//				accion = new Accion();
//			} else if (CASILLA_CALAVERA==i) {
//				accion = new Accion();
//			} else if (CASILLA_FINAL==i) {
//				accion = new Accion();
//			} else {
//				accion = new Accion();
//			}
//			casillas.add(new Casilla(acciones));	
//		}
//	}
//	
//	public Casilla getCasilla(int nroCasilla) {
//		return casillas.get(nroCasilla);
//	}
//
//}
