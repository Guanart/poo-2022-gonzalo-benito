package ar.edu.unlu.oca.modelo;

import java.util.Random;

class Dado {
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
