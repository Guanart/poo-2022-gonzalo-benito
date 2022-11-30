package ar.edu.unlu.oca.modelo.tablero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// No lo uso en esta version
import ar.edu.unlu.oca.modelo.tablero.acciones.AccionModificarPosicion;
import ar.edu.unlu.oca.modelo.tablero.acciones.AccionModificarTurnos;
import ar.edu.unlu.oca.modelo.tablero.acciones.AccionPozo;
import ar.edu.unlu.oca.modelo.tablero.acciones.IAccion;
import ar.edu.unlu.oca.modelo.tablero.casillasEspeciales.CasillaDado;
import ar.edu.unlu.oca.modelo.tablero.casillasEspeciales.CasillaMover;
import ar.edu.unlu.oca.modelo.tablero.casillasEspeciales.CasillaOca;
import ar.edu.unlu.oca.modelo.tablero.casillasEspeciales.CasillaPerderTurno;
import ar.edu.unlu.oca.modelo.tablero.casillasEspeciales.CasillaPozo;
import ar.edu.unlu.oca.modelo.tablero.casillasEspeciales.CasillaPuente;
import ar.edu.unlu.oca.modelo.tablero.casillasEspeciales.CasillaTransportar;

public class Tablero {
	private final int CASILLA_INICIAL = 0;
    private final int CASILLA_FINAL = 63;
    private final int CASILLA_POSADA = 19;
    // No podrás volver a tirar el dado hasta que otro jugador pase por esa casilla
    private final int CASILLA_POZO = 31;
    // Retrocede hasta la casilla 30
    private final int CASILLA_LABERINTO = 42;
    // Pierde dos turnos
    private final int CASILLA_CARCEL = 56;
    // Regresa a la casilla 1
    private final int CASILLA_CALAVERA = 58;
    // Sumar el número de la casilla y el número de la tirada y avanzar la cantidad resultante
    private final ArrayList<Integer> CASILLAS_DADO = new ArrayList<Integer>(Arrays.asList(26, 53));
    // Avanza a la siguiente casilla OCA
    private final ArrayList<Integer> CASILLAS_OCA = new ArrayList<Integer>(Arrays.asList(5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59));
    // Avanza a casilla posada y pierde un turno
    private final ArrayList<Integer> CASILLAS_PUENTE = new ArrayList<Integer>(Arrays.asList(6, 12));
    
	private ArrayList<Casilla> casillas = new ArrayList<Casilla>();

	public void inicializar() {		
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
	}
	
	public Casilla getCasilla(int nroCasilla) {
		return casillas.get(nroCasilla);
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
