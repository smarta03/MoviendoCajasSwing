import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

public class VentanaJug extends JFrame implements Runnable, ActionListener {

	//private JFrame ventanadis ;
	private JButton resolver, guardar, cargar, anterior, siguiente;
	private JLabel etiquetaTiempo, tiempo;
	private int time;

	public VentanaJug() {
		super("JUGAR");
		this.setSize(new Dimension(600, 600));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		JPanel panelTitulo = new JPanel();
		this.getContentPane().add(panelTitulo, BorderLayout.NORTH); 
		panelTitulo.setLayout(new FlowLayout(FlowLayout.LEFT, 20 , 20));

		JPanel panelMenu = new JPanel();
		this.getContentPane().add(panelMenu, BorderLayout.CENTER); 
		panelMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 200 , 5));

		JPanel panelControl = new JPanel();
		this.getContentPane().add(panelMenu, BorderLayout.CENTER); 
		panelMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 200 , 5));
		
		panelTitulo.setBackground(Color.GREEN);
	

		resolver = new JButton("Resolver");
		panelTitulo.add(resolver);
		resolver.addActionListener(this);

		etiquetaTiempo = new JLabel("Tiempo");
		time = 360;
		tiempo = new JLabel(time+"");
		panelTitulo.add(etiquetaTiempo);
		panelTitulo.add(tiempo);

		//JProgressBar barraProg = new JProgressBar();
		JSlider barraSlid = new JSlider(JSlider.HORIZONTAL,0,1000,20 );
		setLayout( new GridLayout(2,1) );
		//add( barraProg );
		barraSlid.setValue(360);
		barraSlid.setPaintTicks(true);
		barraSlid.setMajorTickSpacing(100);
		barraSlid.setMinorTickSpacing(25);
		//barraSlid.setBorder( new TitledBorder("Desplazame") );
		barraSlid.addChangeListener( new ChangeListener() {
			public void stateChanged( ChangeEvent evt ) {
				tiempo.setText(barraSlid.getValue()+"");
			}
		} );
		panelTitulo.add(barraSlid);
		
		
		JLabel espacio = new JLabel("                                      ");
		guardar = new JButton("Guardar");
		cargar = new JButton("Cargar");
		anterior = new JButton("<-");
		siguiente = new JButton("->");
	
		guardar.addActionListener(this);
		cargar.addActionListener(this);
		anterior.addActionListener(this);
		siguiente.addActionListener(this);
	
		panelTitulo.add(espacio);
		panelTitulo.add(guardar);
		panelTitulo.add(cargar);
		panelTitulo.add(anterior);
		panelTitulo.add(siguiente);
		

		panelTitulo.setPreferredSize(new Dimension(600,100));
		pack();
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getActionCommand().equals("Resolver")==true) {

		}

	}


}
