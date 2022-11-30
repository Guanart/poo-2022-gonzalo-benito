package ar.edu.unlu.oca.modelo.tablero;

import ar.edu.unlu.oca.modelo.Jugador;

public class Casilla {

	private int posicion;

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

	public String accion(Jugador jugador) {
		return descripcion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	protected void appendDescripcion(String descripcion) {
		this.descripcion = this.descripcion+descripcion;
	}

}
