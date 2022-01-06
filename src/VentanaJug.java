import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

public class VentanaJug extends JFrame implements Runnable, ActionListener {
	
	//private JFrame ventanadis ;
	private JButton prueba;
	private JTextArea cajaText;
	
	public VentanaJug() {
		super("JUGAR");
		this.setSize(new Dimension(600, 600));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		JPanel panelTitulo = new JPanel();
		this.getContentPane().add(panelTitulo, BorderLayout.NORTH); 
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 100 , 20));

		JPanel panelMenu = new JPanel();
		this.getContentPane().add(panelMenu, BorderLayout.CENTER); 
		panelMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 100 , 50));

		
		prueba = new JButton();
		prueba.setLabel("Prueba");
		
		cajaText = new JTextArea();
		panelTitulo.add(cajaText);
		
		panelTitulo.add(prueba);
		
		prueba.addActionListener(this);
		
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
