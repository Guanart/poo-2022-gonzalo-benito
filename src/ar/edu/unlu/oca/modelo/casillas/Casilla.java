package ar.edu.unlu.oca.modelo.casillas;

import java.io.Serializable;
import java.util.ArrayList;

import ar.edu.unlu.oca.modelo.Jugador;
import ar.edu.unlu.oca.modelo.Tablero;

public class Casilla implements Serializable {
	private static final long serialVersionUID = -1292070912659182504L;
	private int posicion;
	protected ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private String descripcion;
	
	public Casilla(int i){
		this.descripcion = "casilla "+Integer.toString(i);
		this.posicion = i;
	}
	
	/*  Se me ocurrió tener una sola clase, y sobrecargar el constructor, los cuales se diferencian según el enumerado que rediban
	 *  Luego, definir el método accion() de alguna forma que lo permita Java
	 * 	public Casilla(EnumCasillasEspeciales enum, Object args){
		
		}

	 * 
	 */
	
	public int getPosicion() {
		return posicion;
	}

	public String accion(Tablero tablero, Jugador jugador, boolean movidaEspecial) {
		return this.descripcion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	protected void appendDescripcion(String descripcion) {
		this.descripcion = this.descripcion+descripcion;
	}

	public String agregarJugador(Tablero tablero, Jugador jugador, boolean movidaEspecial) {
		jugadores.add(jugador);
		return this.accion(tablero, jugador, movidaEspecial);
	}
	
	public void eliminarJugador(Jugador jugador) {
		jugadores.remove(jugador);
	}

}
