package ar.edu.unlu.oca.gui;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.oca.modelo.IJugador;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Window.Type;

public class VentanaPrincipalGrafica extends JFrame {

	private JLabel boardLabel;
	private JPanel backgroundPanel = new JPanel();
	private JList playerList;
	private JButton botonDados;

	
	/**
	 * Create the frame.
	 */
	public VentanaPrincipalGrafica() {
		setResizable(false);
		backgroundPanel.setBackground(new Color(204, 153, 51));
//		backgroundPanel.setBackground(Color.white);
		backgroundPanel.setLayout(null);

		setTitle("Juego de la Oca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 521);
		
		URL path = getClass().getResource("../resources/images/tablero_original.jpg");
		BufferedImage tablero = null;
		try {
			tablero = ImageIO.read(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon tablero1 = new ImageIcon(tablero.getScaledInstance(600,400,Image.SCALE_SMOOTH));
		
		boardLabel = new JLabel();
		boardLabel.setBounds(32, 33, 610, 410);
		boardLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		ImageIcon tablero1 = new ImageIcon(tablero.getScaledInstance(boardLabel.getWidth(),boardLabel.getHeight(),Image.SCALE_REPLICATE));
		boardLabel.setIcon(tablero1);
		backgroundPanel.add(boardLabel);

		


//		listUsuarios = new JList();
//		listUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		scrollPane.setViewportView(listUsuarios);
				
		playerList = new JList();
		playerList.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Jugadores", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		playerList.setBounds(671, 33, 171, 154);
		backgroundPanel.add(playerList);
    			
    	botonDados = new JButton(new ImageIcon(getClass().getResource("../resources/images/dado.png")));
    	botonDados.setBackground(new Color(204, 153, 0));
    	botonDados.setBounds(688, 334, 133, 109);
    	backgroundPanel.add(botonDados);
    	
		setContentPane(backgroundPanel);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(669, 210, 173, 97);
		backgroundPanel.add(textPane);

	}
	
	public void onClickDados(ActionListener listener) {
		this.botonDados.addActionListener(listener);
	}


	public void actualizarListaUsuarios(ArrayList<IJugador> usuarios) {
		this.playerList.setModel(new AbstractListModel() {
			@Override
			public Object getElementAt(int arg0) {
				return usuarios.get(arg0).getNombre();
			}
			@Override
			public int getSize() {
				return usuarios.size();
			}
		});		
	}
}
