package ar.edu.unlu.oca.modelo;

import java.io.Serializable;
import java.util.Random;

class Dado implements Serializable {
	private static final long serialVersionUID = 5754244542039931041L;
	private Random numeroAleatorio = new Random();
	private int cara;
	
	public Dado() {

	}
	
	public int tirar() {
		cara = numeroAleatorio.nextInt(6)+1;
		return cara;
	}
	
	public String cara() {
		return Integer.toString(cara);
	}
}
