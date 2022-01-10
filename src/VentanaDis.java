import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.*;

public class VentanaDis extends JFrame implements Runnable, ActionListener, Serializable {

	private JButton filMas, filMen, colMas, colMen, siguiente, atras, comprobar, jugar;
	private JLabel labFilas, labColumnas, lab1, lab2, lab3, lab4, lab5, comprobacion;
	private int filas = 1, columnas = 1;
	private JTable tabla;
	private DefaultTableModel modelo;
	private JScrollPane desplazamiento;
	private JPanel panelTitulo, panelInsertarDatos, panelControl;
	private ArrayList<String> entrada;

	public VentanaDis() {
		super("DISEÑADOR");
		this.setSize(new Dimension(600, 400));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		panelTitulo = new JPanel();
		this.getContentPane().add(panelTitulo, BorderLayout.NORTH); 
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 20 , 20));

		panelInsertarDatos = new JPanel();
		this.getContentPane().add(panelInsertarDatos, BorderLayout.CENTER); 
		panelInsertarDatos.setLayout(new FlowLayout(FlowLayout.CENTER, 20 , 50));

		panelControl = new JPanel();
		this.getContentPane().add(panelControl, BorderLayout.SOUTH); 
		panelControl.setLayout(new FlowLayout(FlowLayout.CENTER, 20 , 50));

		pantallaEstablecerTamannio();

	}

	private void pantallaEstablecerTamannio() {

		//TITULO
		lab1 = new JLabel("PASO 1: Introduce el tamaño de la habitación. Tamaño máximo 20x20");
		panelTitulo.add(lab1);

		//INSERTAR DATOS
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

		//PANEL CONTROL

		//PASO ATRAS


		atras = new JButton();
		atras.setLabel("<--Menu principal");
		atras.addActionListener(this);

		panelControl.add(atras);

		//SIGUIENTE PASO

		siguiente = new JButton();
		siguiente.setLabel("Definir habitacion-->");
		siguiente.addActionListener(this);

		panelControl.add(siguiente);


		pack();
		setVisible(true);

	}

	private void pantallaDefinirHabitacion() {
		// TODO Auto-generated method stub

		//PANEL TITULO
		//panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
		lab2 = new JLabel();
		lab3 = new JLabel();

		lab1.setText("PASO 2: define la forma de la habitación marcando las casillas con una X.");
		lab2.setText("Recuerda que no puedes dejar espacios en blanco en medio de la habitacion");
		lab3.setText("Debe ser una forma geométrica.");

		panelTitulo.add(lab1);
		panelTitulo.add(lab2);
		panelTitulo.add(lab3);

		//panelTitulo.setMaximumSize(new Dimension(20,20));


		//PANEL INSERTAR DATOS
		//Tabla
		if (tabla == null) {
			tabla = new JTable();
			modelo = new DefaultTableModel();
			desplazamiento = new JScrollPane(tabla);   

			//Para que no marque la fila al seleccionar la celda
			tabla.setCellSelectionEnabled(false);

			//Para bloquear poder arrastrar columnas
			tabla.getTableHeader().setReorderingAllowed(false);

			//Quitar la cabecera de la tabla
			tabla.setTableHeader(null);
		}

		modelo.setColumnCount(columnas);
		modelo.setRowCount(filas);
		tabla.setModel(modelo);

		//Alinear texto de celdas en el centro
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);

		//Rellenar las paredes exteriores
		for(int i=0;i<columnas;i++){
			for(int j=0;j<filas;j++) {
				tabla.getColumnModel().getColumn(i).setCellRenderer(render);
				if(i==0 || i==columnas-1 || j==0 || j==filas-1) {
					tabla.setValueAt("x", j, i);
				} else if(tabla.getValueAt(j, i)=="x" || tabla.getValueAt(j, i)=="X") {
					tabla.setValueAt(null, j, i);
				}
			}   
		}

		tabla.setRowHeight(20);

		panelInsertarDatos.add(desplazamiento);
		desplazamiento.setBorder(null);

		//PANEL CONTROL

		//Volver a pantalla anterior, establecer el tamannio de la habitacion
		atras.setText("<--Editar tamaño");
		atras.addActionListener(this);
		panelControl.add(atras);

		//Siguiente paso
		siguiente.setLabel("Colocar objetos-->");
		siguiente.addActionListener(this);
		panelControl.add(siguiente);



		panelTitulo.setPreferredSize(new Dimension(600,150));
		pack();
		this.repaint();

	}

	private void pantallaColocarObjetos() {
		// TODO Auto-generated method stub

		//PANEL TITULO
		panelTitulo.setPreferredSize(new Dimension(600,250));

		lab2 = new JLabel();
		lab3 = new JLabel();
		lab4 = new JLabel();
		lab5 = new JLabel();

		lab1.setText("PASO 3: Coloca los simbolos en la habitacion");
		lab2.setText("@ Robot. Solamente se puede colocar 1");
		lab3.setText("! Destino de caja. Mismo numero que cajas");
		lab4.setText("# Caja. Mismo numero que destinos");
		lab5.setText("1 Paredes intermedias. Todas las que quieras, pero que tenga solucion!");

		panelTitulo.add(lab1);
		panelTitulo.add(lab2);
		panelTitulo.add(lab3);
		panelTitulo.add(lab4);
		panelTitulo.add(lab5);

		//PANEL INSERTAR DATOS
		//Tabla

		panelInsertarDatos.add(desplazamiento);
		desplazamiento.setBorder(null);

		//PANEL CONTROL

		//Anterior
		atras.setText("<--Editar habitacion");
		atras.addActionListener(this);
		panelControl.add(atras);

		//Comprobar habitacion
		comprobacion = new JLabel();
		comprobar = new JButton();		

		panelTitulo.add(comprobacion);
		panelControl.add(comprobar);

		comprobar.setLabel("Comprobar habitacion");
		comprobar.addActionListener(this);

		//Guardar

		siguiente.setText("Guardar");
		siguiente.addActionListener(this);
		panelControl.add(siguiente);
		siguiente.setVisible(false);

		//Jugar
		jugar = new JButton("Jugar");
		jugar.addActionListener(this);
		panelControl.add(jugar);
		jugar.setVisible(false);

		pack();
		repaint();
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

		if(e.getActionCommand().equals("<--Menu principal")==true) {
			this.setVisible(false);
		}

		if(e.getActionCommand().equals("Definir habitacion-->")==true) {
			panelTitulo.removeAll();
			panelInsertarDatos.removeAll();
			panelControl.removeAll();

			//CREO UN METODO NUEVO PARA LA SIGUIENTE VENTANA
			pantallaDefinirHabitacion();
		}

		if(e.getActionCommand().equals("<--Editar tamaño")==true) {
			panelTitulo.removeAll();
			panelInsertarDatos.removeAll();
			panelControl.removeAll();

			pantallaEstablecerTamannio();
		}

		if(e.getActionCommand().equals("Colocar objetos-->")==true) {
			panelTitulo.removeAll();
			panelInsertarDatos.removeAll();
			panelControl.removeAll();

			pantallaColocarObjetos();
		}

		if(e.getActionCommand().equals("<--Editar habitacion")==true) {
			panelTitulo.removeAll();
			panelInsertarDatos.removeAll();
			panelControl.removeAll();

			pantallaDefinirHabitacion();
		}


		if(e.getActionCommand().equals("Comprobar habitacion")==true) {
			//Recoger la forma de la habitacion
			//La recoge cuando pulsa el boton colocar elementos

			if(entrada!=null) {
				entrada=null;
			}

			entrada = new ArrayList<String>();

			String cadena = "";
			entrada.add(filas+" "+columnas+" ");

			for(int i=0;i<filas;i++){
				for(int j=0;j<columnas;j++) {
					if(tabla.getValueAt(i, j)==null) {
						cadena += "-";
					}else {
						if (tabla.getValueAt(i, j).equals("x")==true || tabla.getValueAt(i, j).equals("X")==true)
							cadena += "0";
						else
							cadena += tabla.getValueAt(i, j).toString().toLowerCase();
					}
				}   
				entrada.add(cadena);
				cadena = "";
			}

			LeerEntradaHabitacion leerEntrada = new LeerEntradaHabitacion();
			leerEntrada.restablecer();
			leerEntrada.setEntrada(entrada);

			//			Thread hilo = new Thread(leerEntrada);
			//			hilo.start();

			leerEntrada.leerEntradaHabitacion();

			if(leerEntrada.getResultado()==null) {
				comprobacion.setText("Entrada mal formada");
				comprobacion.setForeground(Color.RED);
			} else {
				comprobacion.setText("Entrada correcta");
				comprobacion.setForeground(Color.GREEN);

				siguiente.setVisible(true);
				jugar.setVisible(true);

				pack();
			}

			repaint();
		}

		if(e.getActionCommand().equals("Guardar")==true) {
			try {
				atras.removeActionListener(this);
				siguiente.removeActionListener(this);
				//Abrir cuadro de dialogo para guardar archivo
				JFileChooser saveFile = new JFileChooser();
				saveFile.showSaveDialog(this);
				
				//saveFile.setFileFilter(new FileNameExtensionFilter("txt file","txt"));
				//Almacenar la url y nombre del fichero insertado
				String url = saveFile.getSelectedFile().getAbsolutePath();         
				if(!url.contains("txt")) 
					url += ".txt";

				//File file = new File("C:\\Users\\Sergi\\Desktop\\ejemplo.txt");
				//Escribir en el fichero el arraylist de entrada
				File file = new File(url);

				FileWriter escribir = new FileWriter(file);

				String ent = "";
				for(String fila:entrada) 
					ent += fila + '\n';

				for(int i=0; i<ent.length();i++)
					escribir.write(ent.charAt(i));

				escribir.close();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		
		if(e.getActionCommand().equals("Jugar")==true) {
			setVisible(false);
			VentanaJug ventanajug = new VentanaJug();
			Thread hilo = new Thread(ventanajug);
			hilo.start();
		}

	}



}
