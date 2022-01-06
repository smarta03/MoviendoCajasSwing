import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

public class VentanaDis extends JFrame implements Runnable, ActionListener {
	
	private JButton filMas, filMen, colMas, colMen, siguiente;
	private JLabel labFilas, labColumnas, lab1, lab2;
	private int filas = 1, columnas = 1;
	private JTable tabla;
	private DefaultTableModel modelo;
	private JScrollPane desplazamiento;
	private JPanel panelTitulo, panelInsertarDatos, panelControl;
	
	public VentanaDis() {
		super("DISEÑADOR");
		this.setSize(new Dimension(600, 400));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//TITULO
		panelTitulo = new JPanel();
		this.getContentPane().add(panelTitulo, BorderLayout.NORTH); 
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 100 , 20));

		lab1 = new JLabel("PASO 1: Introduce el tamaño de la habitación. Tamaño máximo 20x20");
		panelTitulo.add(lab1);
		
		//INSERTAR DATOS
		
		panelInsertarDatos = new JPanel();
		this.getContentPane().add(panelInsertarDatos, BorderLayout.CENTER); 
		panelInsertarDatos.setLayout(new FlowLayout(FlowLayout.CENTER, 100 , 50));
		
		//Filas - etiqueta y botones + -
		JLabel nomFilas = new JLabel("Filas");
		labFilas = new JLabel(filas+"");
		filMas = new JButton();
		filMen = new JButton();
		
		filMas.setLabel(" +");
		filMen.setLabel(" -");
		
		panelInsertarDatos.add(nomFilas);
		panelInsertarDatos.add(labFilas);
		panelInsertarDatos.add(filMas);
		panelInsertarDatos.add(filMen);
		
		filMas.addActionListener(this);
		filMen.addActionListener(this);
		
		//Columnas - etiqueta y botones + -
		
		JLabel nomColumnas = new JLabel("Columnas");
		labColumnas = new JLabel(columnas+"");
		colMas = new JButton();
		colMen = new JButton();
		
		colMas.setLabel("+ ");
		colMen.setLabel("- ");
		
		panelInsertarDatos.add(nomColumnas);
		panelInsertarDatos.add(labColumnas);
		panelInsertarDatos.add(colMas);
		panelInsertarDatos.add(colMen);
		
		colMas.addActionListener(this);
		colMen.addActionListener(this);
		
		//SIGUIENTE PASO
		panelControl = new JPanel();
		this.getContentPane().add(panelControl, BorderLayout.SOUTH); 
		panelControl.setLayout(new FlowLayout(FlowLayout.CENTER, 100 , 50));
		
		siguiente = new JButton();
		siguiente.setLabel("Definir habitacion");
		siguiente.addActionListener(this);
		
		panelControl.add(siguiente);
        
		//pack();
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(" +")==true) {
			if(filas<20) {
				filas++;
				labFilas.setText(filas+"");
				
			}
		}
		
		if(e.getActionCommand().equals(" -")==true) {
			if(filas>1) {
				filas--;
				labFilas.setText(filas+"");
			}
		}
		
		if(e.getActionCommand().equals("+ ")==true) {
			if(columnas<20) {
				columnas++;
				labColumnas.setText(columnas+"");
				
			}
		}
		
		if(e.getActionCommand().equals("- ")==true) {
			if(columnas>1) {
				columnas--;
				labColumnas.setText(columnas+"");
				
			}
		}
		
		if(e.getActionCommand().equals("Definir habitacion")==true) {
			panelTitulo.removeAll();
			panelInsertarDatos.removeAll();
			panelControl.removeAll();
			
			//CREO UN METODO NUEVO PARA LA SIGUIENTE VENTANA
			definirHabitacion();
		}
	}

	private void definirHabitacion() {
		// TODO Auto-generated method stub
		
		lab2 = new JLabel();
		
		lab1.setText("PASO 2: define la forma de la habitación quitando cuadrados.");
		lab2.setText("Recuerda que no puedesdejar espacios en blanco en medio de la habitacion");
		
		panelTitulo.add(lab1);
		panelTitulo.add(lab2);
		
		
		this.repaint();
		
	}
	
}
