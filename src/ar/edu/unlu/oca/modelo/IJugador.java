package ar.edu.unlu.oca.modelo;

public interface IJugador {
	int getCasillaActual();
	String getNombre();
	Ficha getFicha();
	int moverFicha(int cantCasillas);
	void darTurno();
	void quitarTurno(int cantTurnos);
	void cayoEnPozo();
	int saltarCasilla(int nuevaCasilla);
	int getUltimaTirada();
	boolean tieneTurnos();
	boolean gano();
	int jugar(int tirada);
	boolean tieneTurnosPerdidos();
	boolean estaEnPozo();
	void incTurnoPerdido(int turnos);
	void decTurnoPerdido();
	void liberarDelPozo();
	void setPozo();
}
