package ar.edu.unlu.oca.modelo.tablero;

public class CasillaFactorySinUsar {
	public static Casilla getCasillaNormal() {
		Accion accion = new Accion();
		Casilla casilla = new Casilla(accion);
		return casilla;
	}
	
	public static Casilla getSaltoOca(int inicio, int fin) {	// CASILLAS: Oca, puente
		return CasillaFactorySinUsar.getCasilla();
	}
	
	public static Casilla getRetrocesoOca() {
		return CasillaFactorySinUsar.getCasilla("red");
	}

	public static Casilla getPerderTurno(int turnos) {
		return CasillaFactorySinUsar.getCasilla("red");
	}
	
	public static Casilla getGanarTurno(int turnos) {
		return CasillaFactorySinUsar.getCasilla("red");
	}
	

	private static Casilla getCasilla(Object arg) {		
		Accion accion = new Accion();
		Casilla casilla = new Casilla(accion);
		return casilla;
	}

}
