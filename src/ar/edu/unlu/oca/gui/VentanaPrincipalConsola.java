package ar.edu.unlu.oca.gui;

import java.awt.Color;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.StringReader;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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
import javax.swing.JEditorPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class VentanaPrincipalConsola extends JFrame {
	
	private static final long serialVersionUID = 9137138318622948232L;
	private JPanel contentPane;
	private JTextField textInput;
	private JButton btnEnviar;
	private JEditorPane txtHistorico;
	private JScrollPane scrollPane;
	private JScrollBar verticalScrollBar;
//	private JTextPane txtHistorico;
		
	/**
	 * Create the frame.
	 * @throws BadLocationException 
	 */
	public VentanaPrincipalConsola() {
		setLocationRelativeTo(null);
		setBounds(100, 100, 859, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][50px][::80px]", "[grow][]"));
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0 3 1,grow");
		
		txtHistorico = new JEditorPane();
		txtHistorico.setContentType("text/html");
		txtHistorico.setText("");
		
		txtHistorico.setEditable(false);
		scrollPane.setViewportView(txtHistorico);
		
		DefaultCaret caret = (DefaultCaret) txtHistorico.getCaret();
		caret.setUpdatePolicy(3);
		
        verticalScrollBar = scrollPane.getVerticalScrollBar();
		
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
		appendHTML(txtHistorico, texto);
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());
	}
	
    private static void appendHTML(JEditorPane editorPane, String html) {
        HTMLEditorKit kit = (HTMLEditorKit) editorPane.getEditorKit();
        try {
            StringReader reader = new StringReader(html);
            kit.read(reader, editorPane.getDocument(), editorPane.getDocument().getLength());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	
}
