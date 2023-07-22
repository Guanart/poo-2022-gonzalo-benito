package ar.edu.unlu.oca.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;

public class VentanaInicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JButton btnIniciar;
	private JRadioButton redRadioButton;
	private JRadioButton blueRadioButton;
	private JRadioButton yellowRadioButton;
	private JRadioButton greenRadioButton;
	private ButtonGroup radioGroup;

	/**
	 * Create the frame.
	 */
	public VentanaInicioSesion() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 247, 103);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		JLabel lblUsuario = new JLabel("Usuario");
		contentPane.add(lblUsuario, "cell 0 0,alignx trailing");
		
		textUsuario = new JTextField();
		contentPane.add(textUsuario, "cell 1 0,growx");
		textUsuario.setColumns(10);
		
//		redRadioButton = new JRadioButton("Rojo");
//		redRadioButton.setForeground(new Color(255, 0, 0));
//		panel.add(redRadioButton, "cell 0 0");
//		
//		blueRadioButton = new JRadioButton("Azul");
//		blueRadioButton.setForeground(new Color(0, 0, 255));
//		panel.add(blueRadioButton, "cell 0 1");
//		
//		yellowRadioButton = new JRadioButton("Amarillo");
//		yellowRadioButton.setForeground(new Color(255, 255, 0));
//		panel.add(yellowRadioButton, "cell 0 2");
//		
//		greenRadioButton = new JRadioButton("Verde");
//		greenRadioButton.setForeground(new Color(0, 255,  0));
//		panel.add(greenRadioButton, "cell 0 3");
//		
//		radioGroup = new ButtonGroup();
//		radioGroup.add(redRadioButton);
//		radioGroup.add(blueRadioButton);
//		radioGroup.add(yellowRadioButton);
//		radioGroup.add(greenRadioButton);

		btnIniciar = new JButton("Iniciar");
		contentPane.add(btnIniciar, "cell 1 1,alignx right");
		
		SwingUtilities.getRootPane(btnIniciar).setDefaultButton(btnIniciar);
	}
	
	public void onClickIniciar(ActionListener listener) {
		this.btnIniciar.addActionListener(listener);
	}
	
	public String getNombreUsuario() {
		return this.textUsuario.getText();
	}

	public int getFicha() {
		JRadioButton button = (JRadioButton) this.radioGroup.getSelection();
		return Integer.parseInt(button.getText());
	}
}
