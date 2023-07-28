package ar.edu.unlu.oca.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ar.edu.unlu.oca.modelo.IJugador;
import ar.edu.unlu.oca.modelo.Tablero;
import ar.edu.unlu.oca.modelo.casillas.Casilla;

public class TableroGrafica extends JPanel  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9192330162075045419L;
	private Tablero tablero;
	private int fichaAncho = 25;
	private int fichaAlto = 25;

	
	
	public TableroGrafica() {
	}

	public void paintComponent(Graphics g) {
	
        super.paintComponent(g);
		
		URL path = getClass().getResource("../resources/images/tablero_original.jpg");
		BufferedImage tablero = null;
		try {
			tablero = ImageIO.read(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon tablero1 = new ImageIcon(tablero.getScaledInstance(600,400,Image.SCALE_SMOOTH));
		
        g.drawImage(tablero1.getImage(), 0, 0, getWidth(), getHeight(), this);

        // Dibujar la ficha
        
        if (this.tablero != null) {        	
        	ArrayList<Casilla> casillas = new ArrayList<Casilla>(this.tablero.getCasillas());
        	
        	for (Casilla casilla : casillas) {
        		if (casilla.tieneJugadores()) {   
        			ArrayList<IJugador> jugadores = null;
        			try {
        				jugadores = casilla.getJugadores();
        			} catch (RemoteException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			int x = casilla.getX();
        			int y = casilla.getY();
        			g.setColor(jugadores.get(0).getFicha().swingColor);		// Como se superponen, solo muestro 1 (podría hacer un foreach y pisarlo)
        			g.fillOval(x, y, fichaAncho, fichaAlto);
        		}
        	}
        }
        
        // Casilla Inicial (1)
//        int x = 80;     // Para avanzar horizontalmente, modificar x de a 60 unidades
//        int y = 375;	// Para avanzar verticalmente, modificar y de a 60 unidades
        
//        g.setColor(Color.RED);
//        g.fillOval(x, y, fichaAncho, fichaAlto);
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

	public void actualizarTablero(Tablero tablero) {
		this.tablero = tablero;
		repaint();
	}
}
