import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame implements ActionListener{	

	public VentanaPrincipal() {
		super("MOVIENDO CAJAS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void lanzar() {

		JPanel panelTitulo = new JPanel();
		this.getContentPane().add(panelTitulo, BorderLayout.NORTH); 
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 100 , 20));

		JPanel panelMenu = new JPanel();
		this.getContentPane().add(panelMenu, BorderLayout.CENTER); 
		panelMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 100 , 50));

		JLabel titulo = new JLabel("Menu principal");
		panelTitulo.add(titulo);

		JButton dis  = new JButton(); 
		JButton jugar  = new JButton();

		dis.setLabel("DISEÑADOR");
		dis.setMinimumSize(new Dimension(120,30));
		dis.setMaximumSize(new Dimension(200,50));
		dis.setPreferredSize(new Dimension(150,50));

		jugar.setLabel("JUGAR");
		jugar.setMinimumSize(new Dimension(120,30));
		jugar.setMaximumSize(new Dimension(200,50));
		jugar.setPreferredSize(new Dimension(150,50));

		panelMenu.add(dis); 
		panelMenu.add(jugar);

		dis.addActionListener(this);
		jugar.addActionListener(this);
		
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getActionCommand().equals("DISEÑADOR")==true) {
			VentanaDis ventanadis = new VentanaDis();
			Thread hilo = new Thread(ventanadis);
			hilo.start();

		}

		if(e.getActionCommand().equals("JUGAR")==true) {
			VentanaJug ventanajug = new VentanaJug();
			Thread hilo = new Thread(ventanajug);
			hilo.start();
		}

	}



}
