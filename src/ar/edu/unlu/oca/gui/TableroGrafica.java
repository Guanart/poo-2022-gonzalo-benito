package ar.edu.unlu.oca.gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TableroGrafica extends JComponent {
	public TableroGrafica() {
	}

	public void paintComponent(Graphics g) {
		g.drawOval(50, 50, 100, 100);
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame f = new JFrame("Test");
				f.getContentPane().add(new TableroGrafica());
				f.pack();
				f.setVisible(true);
			}
		});
	}
}
