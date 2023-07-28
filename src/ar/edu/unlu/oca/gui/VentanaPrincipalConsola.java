package ar.edu.unlu.oca.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class VentanaPrincipalConsola extends JFrame {
	
	private static final long serialVersionUID = 9137138318622948232L;
	private JPanel contentPane;
	private JTextField textInput;
	private JButton btnEnviar;
//	private JTextPane txtHistorico;  TODO
	private JTextArea txtHistorico;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalConsola frame = new VentanaPrincipalConsola();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public VentanaPrincipalConsola() {
		setLocationRelativeTo(null);
		setBounds(100, 100, 859, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][50px][::80px]", "[grow][]"));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, "cell 0 0 3 1,grow");
		
//		txtHistorico = new JTextPane(); TODO
		txtHistorico = new JTextArea();
		txtHistorico.setEditable(false);
		scrollPane_1.setViewportView(txtHistorico);
		DefaultCaret caret = (DefaultCaret) txtHistorico.getCaret();
		caret.setUpdatePolicy(2);
		
//		JScrollPane scrollPane = new JScrollPane();
//		contentPane.add(scrollPane, "cell 1 0 2 1,grow");
		
		textInput = new JTextField();
		contentPane.add(textInput, "cell 0 1 2 1,growx");
		textInput.setColumns(10);
		
		btnEnviar = new JButton("Enviar");
		contentPane.add(btnEnviar, "cell 2 1,growx");
		
		SwingUtilities.getRootPane(btnEnviar).setDefaultButton(btnEnviar);
		addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		    	textInput.requestFocusInWindow();
		    }
		}); 
	}
	
	public void onClickEnviar(ActionListener listener) {
		this.btnEnviar.addActionListener(listener);
	}
			
	public String getTextoInput() {
		return this.textInput.getText();
	}

	public void setTextoInput(String texto) {
		this.textInput.setText(texto);
	}
	
	public void setTextoHistorico(String texto) {
		this.txtHistorico.append(texto);
	}
	

	
}
