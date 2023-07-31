package ar.edu.unlu.oca.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.oca.modelo.Ficha;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.util.EnumSet;

import javax.swing.JRadioButton;

public class VentanaInicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JButton btnIniciar;
	private JRadioButton redRadioButton;
	private JRadioButton blueRadioButton;
	private JRadioButton violetRadioButton;
	private JRadioButton greenRadioButton;
	private ButtonGroup colorRadioGroup;

	/**
	 * Create the frame.
	 */
	public VentanaInicioSesion() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 288, 135);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[center]", "[][][]"));
		
		JLabel lblUsuario = new JLabel("Usuario");
		contentPane.add(lblUsuario, "cell 0 0");
		
		textUsuario = new JTextField();
		contentPane.add(textUsuario, "cell 0 0");
		textUsuario.setColumns(12);
		
		
		redRadioButton = new JRadioButton("Rojo");
		redRadioButton.setForeground(new Color(255, 0, 0));
		redRadioButton.setEnabled(false);
		
		blueRadioButton = new JRadioButton("Azul");
		blueRadioButton.setForeground(new Color(0, 0, 255));
		blueRadioButton.setEnabled(false);

		violetRadioButton = new JRadioButton("Violeta");
		violetRadioButton.setForeground(new Color(128, 0, 255));
		violetRadioButton.setEnabled(false);

		greenRadioButton = new JRadioButton("Verde");
		greenRadioButton.setForeground(new Color(0, 255,  0));
		greenRadioButton.setEnabled(false);

		colorRadioGroup = new ButtonGroup();
		colorRadioGroup.add(redRadioButton);
		colorRadioGroup.add(violetRadioButton);
		colorRadioGroup.add(greenRadioButton);
		colorRadioGroup.add(blueRadioButton);
		
		contentPane.add(redRadioButton, "cell 0 1");
		contentPane.add(violetRadioButton, "cell 0 1");
		contentPane.add(greenRadioButton, "cell 0 1");
		contentPane.add(blueRadioButton, "cell 0 1");
		btnIniciar = new JButton("Iniciar");
		contentPane.add(btnIniciar, "cell 0 2,alignx center");
		
		SwingUtilities.getRootPane(btnIniciar).setDefaultButton(btnIniciar);
	}
	
	public void onClickIniciar(ActionListener listener) {
		this.btnIniciar.addActionListener(listener);
	}
	
	public String getNombreUsuario() {
		return this.textUsuario.getText();
	}

	public int getFicha() {
		int color = 0;
		if (redRadioButton.isSelected()) {
			color = 1;
		}
		else if (violetRadioButton.isSelected()) {
			color = 2;
		}
		else if (greenRadioButton.isSelected()) {
			color = 3;
		}
		else if (blueRadioButton.isSelected()) {
			color = 4;
		}

		return color;
	}

	public void mostrarFichas(EnumSet<Ficha> fichasDisponibles) {
		for (Ficha ficha : fichasDisponibles) {
			switch (ficha) {
			case ROJA:
				redRadioButton.setEnabled(true);
				break;
			case VIOLETA:
				violetRadioButton.setEnabled(true);
				break;
			case VERDE:
				greenRadioButton.setEnabled(true);
				break;
			case AZUL:
				blueRadioButton.setEnabled(true);
				break;

			default:
				break;
			}
		}
	}
}
