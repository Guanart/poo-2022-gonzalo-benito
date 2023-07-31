package ar.edu.unlu.oca.gui;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.oca.modelo.Ficha;
import ar.edu.unlu.oca.modelo.IJugador;
import ar.edu.unlu.oca.modelo.Tablero;

import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextPane;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaPrincipalGrafica extends JFrame {

	private static final long serialVersionUID = -3016795147387382990L;
	//	private JLabel boardLabel;
	private TableroGrafica boardLabel;
	private JPanel backgroundPanel = new JPanel();
	private JList playerList;
	private JTextPane textPane;
	private JButton botonDados;
	private Icon dadoIcon;
	private Icon prohibidoIcon;
	private JLabel labelTurno;
	private JButton botonRanking;
	private JTable tablaRanking;
	
	/**
	 * Create the frame.
	 */
	public VentanaPrincipalGrafica() {
		setResizable(false);
		backgroundPanel.setBackground(new Color(204, 153, 51));
		backgroundPanel.setLayout(null);

		setTitle("Juego de la Oca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 521);;
		
		botonRanking = new JButton("Ranking 🏆");
		botonRanking.setBounds(708, 140, 97, 23);
		backgroundPanel.add(botonRanking);
				
		boardLabel = new TableroGrafica();
		boardLabel.setBounds(32, 33, 600, 420);
		boardLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
		backgroundPanel.add(boardLabel);
				
		playerList = new JList();
		playerList.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Jugadores", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		playerList.setBounds(671, 33, 171, 97);
		backgroundPanel.add(playerList);
    			
		dadoIcon = new ImageIcon(getClass().getResource("../resources/images/dado.png"));
		prohibidoIcon = new ImageIcon(getClass().getResource("../resources/images/prohibido.png"));

		botonDados = new JButton(dadoIcon);
		botonDados.setIcon(dadoIcon);
    	botonDados.setBackground(new Color(204, 153, 0));
    	botonDados.setBounds(692, 344, 133, 109);
    	botonDados.setEnabled(false);
    	backgroundPanel.add(botonDados);
    	
		setContentPane(backgroundPanel);
		
		textPane = new JTextPane();
		textPane.setContentType("text/html");
		textPane.setEditable(false);
		textPane.setBounds(660, 176, 196, 128);
		backgroundPanel.add(textPane);
		
		labelTurno = new JLabel("Turno de: ");
		labelTurno.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		labelTurno.setBounds(671, 315, 171, 18);
		backgroundPanel.add(labelTurno);
	}
	
	public void verRanking(Map<String, Integer> ranking) {
        String[] columnNames = {"Jugador", "Partidas ganadas"};
        Object[][] matriz = new String[ranking.size()][2];
        
        // Convierte el HashMap a una matriz, para usar los datos en una tabla
        int i = 0;
        for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
            String clave = entry.getKey();
            Integer valor = entry.getValue();

            matriz[i][0] = clave;
            matriz[i][1] = String.valueOf(valor);

            i++;
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(matriz, columnNames);
        tablaRanking = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaRanking);
        
        JFrame rankingFrame = new JFrame("Ranking de Jugadores");
        rankingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rankingFrame.setPreferredSize(new Dimension(300, 200));
        rankingFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        rankingFrame.pack();
        rankingFrame.setLocationRelativeTo(null);
        rankingFrame.setVisible(true);
	}

	public void onClickDados(ActionListener listener) {
		this.botonDados.addActionListener(listener);
	}
	
	public void onClickRanking(ActionListener listener) {
		this.botonRanking.addActionListener(listener);
	}
	
	public void onSpaceDados(KeyListener listener) {
		this.addKeyListener(listener);;
		
	}

	@SuppressWarnings("unchecked")
	public void actualizarListaUsuarios(ArrayList<IJugador> usuarios, IJugador jugadorControlador) {
		this.playerList.setModel(new AbstractListModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 4488607247284152097L;
			@Override
			public Object getElementAt(int arg0) {
				IJugador jugador = usuarios.get(arg0);
				if(jugadorControlador != null && jugador.getFicha()==jugadorControlador.getFicha()) {					
					return "<html><font color='"+jugador.getFicha().HTMLColor+"'>"+jugador.getNombre()+" (tú)</font></html>";
				}
				return "<html><font color='"+jugador.getFicha().HTMLColor+"'>"+jugador.getNombre()+"</font></html>";
			}
			@Override
			public int getSize() {
				return usuarios.size();
			}
		});	
	}
	
	public void activarDado(boolean enable) {
		botonDados.setEnabled(enable);
	}
	
	public void activarDado(IJugador jugadorControlador) {
		if (jugadorControlador.turnosPerdidos() > 0 || jugadorControlador.estaEnPozo()) {
			botonDados.setIcon(prohibidoIcon);
		} else {			
			botonDados.setIcon(dadoIcon);
		}
		botonDados.repaint();
	}

	public void imprimirDescripcion(String descripcionCasilla) {
		textPane.setText(descripcionCasilla);
	}

	public void actualizarTablero(Tablero tablero) {
		boardLabel.actualizarTablero(tablero);
	}

	public void mostrarTurno(IJugador jugadorActual) {
		labelTurno.setText("Turno de: "+jugadorActual.getNombre()+" ("+jugadorActual.getFicha()+")");
	}
}
