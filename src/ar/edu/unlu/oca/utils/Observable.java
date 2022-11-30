package ar.edu.unlu.oca.utils;

public interface Observable {
	public void agregarObservador(Observador observador);
	public void notificarObservadores(Object o);

}
