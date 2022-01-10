import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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

import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;

public class VentanaJug extends JFrame implements Runnable, ActionListener {

	//private JFrame ventanadis ;
	private JButton resolver, guardar, cargar, anterior, siguiente;
	private JLabel etiquetaTiempo, tiempo;
	private int time, filas, columnas, robotFil, robotCol, indiceHistorial, totalMov;
	private JPanel panelTitulo, panelMenu, panelControl;
	//private GridLayout tablero;
	private String[][] tablero;
	private Component[] componentes;
	private String nextCharRob, debCharRob;
	private ArrayList<String[][]> historial;
	private KeyboardFocusManager manager;
	private ControladorKeys control;

	public VentanaJug() {
		super("JUGAR");
		this.setSize(new Dimension(600, 600));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		panelTitulo = new JPanel();
		this.getContentPane().add(panelTitulo, BorderLayout.NORTH); 
		panelTitulo.setLayout(new FlowLayout(FlowLayout.LEFT, 20 , 20));

		panelMenu = new JPanel();
		this.getContentPane().add(panelMenu, BorderLayout.CENTER); 
		panelMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 20 , 20));

		panelControl = new JPanel();
		this.getContentPane().add(panelControl, BorderLayout.CENTER); 
		panelControl.setLayout(new FlowLayout(FlowLayout.CENTER, 20 , 20));

		panelTitulo.setBackground(Color.GREEN);


		//RESOLVER EN X TIEMPO
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

		// GUARDAR, CARGAR, ANTERIOR, SIGUIENTE
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

		//PANEL menu

		//tablero = new GridLayout(20,20);
		//this.setLayout(tablero);

		//panelTitulo.setLayout(new FlowLayout(FlowLayout.LEFT, 20 , 20));

		//int[][] datosTablero = new int[][];


		//Control pulsar flechas para mover el robot
		//panelMenu.addKeyListener(this);

		//KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(this);
		debCharRob="-";
		indiceHistorial = 0;
		historial = new ArrayList<String[][]>();


		//panelTitulo.setPreferredSize(new Dimension(600,100));
		pack();
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getActionCommand().equals("Resolver")==true) {

			ArrayList<String> habitacion = new ArrayList<String>();

			String temp = "";
			habitacion.add(filas + " " + columnas + " ");
			for(int i=0;i<filas;i++) {
				for(int j=0;j<columnas;j++) {
					temp += tablero[i][j]; 
				}
				habitacion.add(temp);
				temp = "";
			}

			//Inicio del tiempo de ejecucion
			long inicio = System.currentTimeMillis(); 

			ResolutorCajas resolutor = new ResolutorCajas(habitacion,Double.parseDouble(tiempo.getText()), inicio);
			String res = resolutor.getCadResultado();

			//Calcular timempo de ejecucion
			long fin = System.currentTimeMillis();
			double tiempoEjec = (double) ((fin - inicio)/1000);

			if (tiempoEjec>Double.parseDouble(tiempo.getText())) {
				JOptionPane.showMessageDialog(panelMenu, "Se ha superado el tiempo establecido", "Resultado", 1);
			} else if(res.compareTo("T")==0){
				JOptionPane.showMessageDialog(panelMenu, "Se ha superado el tiempo establecido", "Resultado", 1);
			} else {
				//Comprobar si ha tenido solucion o no
				if(res.compareTo("N")!=0) {
					JOptionPane.showMessageDialog(panelMenu, "Movimientos: " + res + '\n' + "Total: " + res.length(), "Resultado", 1);
				} else {
					JOptionPane.showMessageDialog(panelMenu, "No hay ninguna solucion posible", "Resultado", 1);
				}
			}

		}

		if(e.getActionCommand().equals("Guardar")==true) {
			try {
				//atras.removeActionListener(this);
				//siguiente.removeActionListener(this);
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
				for(int i=0; i<filas; i++) {
					for(int j=0;j<columnas;j++) {
						ent += tablero[i][j];
					}
					ent += '\n';
				}

				/*for(String fila:entrada) 
					ent += fila + '\n';*/

				escribir.write(filas+" "+columnas+" "+'\n');

				for(int i=0; i<ent.length();i++)
					escribir.write(ent.charAt(i));



				escribir.close();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if(e.getActionCommand().equals("Cargar")==true) {

			//Cargar el fichero
			JFileChooser loadFile = new JFileChooser();
			loadFile.showOpenDialog(loadFile);
			String ruta = loadFile.getSelectedFile().getAbsolutePath();
			File f = new File(ruta);
			Scanner scn = null;
			boolean throwEx = false;

			try {
				scn = new Scanner(f);                                          
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(this, "Fichero no encontrado");
				throwEx = true;
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(this, "No se ha seleccionadio ningun fichero");
				throwEx = true;
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
				throwEx = true;
			}

			//Lectura del fichero a un array bidimensional
			if(!throwEx) {

				try {
					panelMenu.removeAll();

					String primeraLinea = scn.nextLine();
					String[] fyc = primeraLinea.split(" ");
					filas = Integer.parseInt(fyc[0]);
					columnas = Integer.parseInt(fyc[1]);

					tablero = new String[filas][columnas];
					for(int i=0; i<filas; i++) {
						String temp = scn.nextLine();
						for(int j=0; j<columnas; j++) {
							tablero[i][j] = temp.charAt(j)+"";
							if(tablero[i][j].equals("@")) {
								robotFil=i;
								robotCol=j;
							}
						}
					}
					scn.close();

					//Establecer los botonoes con el problema
					panelMenu.setLayout(new GridLayout(filas,columnas));


					//Anniadir el numero de botones correspondiente
					for(int i=0; i<filas*columnas; i++) {				
						panelMenu.add(new JButton());
					}

					componentes = panelMenu.getComponents();

					//Cargar problema en botones
					int cont = 0;
					for(int i=0; i<filas; i++) {
						for(int j=0; j<columnas; j++) {
							((AbstractButton)componentes[cont]).setText(tablero[i][j]);
							cont++;
						}

					}

					//Comienza e historial
					String[][] tabTemp = new String[filas][columnas];

					for(int i=0;i<filas;i++) {
						for(int j=0;j<columnas;j++) {
							tabTemp[i][j] = tablero[i][j];
						}
					} 


					historial.add(tabTemp);
					//indiceHistorial++;

					//Controlador de eventos del teclado
					manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
					control = new ControladorKeys();
					manager.addKeyEventDispatcher(control);


				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, "La entrada del fichero no es valida, pasate por el diseñador!");
				}
			}
			repaint();
		}

		if(e.getActionCommand().equals("<-")==true) {
			if(indiceHistorial>0) {
				indiceHistorial--;
				tablero = historial.get(indiceHistorial);
				totalMov++;
				//Cargar historial en botones
				int cont = 0;
				for(int i=0; i<filas; i++) {
					for(int j=0; j<columnas; j++) {
						((AbstractButton)componentes[cont]).setText(tablero[i][j]);
						cont++;
					}

				}

			}
		}

		if(e.getActionCommand().equals("->")==true) {
			if(indiceHistorial<historial.size()) {
				indiceHistorial++;
				tablero = historial.get(indiceHistorial);
				totalMov--;
				//Cargar historial en botones
				int cont = 0;
				for(int i=0; i<filas; i++) {
					for(int j=0; j<columnas; j++) {
						((AbstractButton)componentes[cont]).setText(tablero[i][j]);
						cont++;
					}

				}
			}
		}

	}

	private class ControladorKeys implements KeyEventDispatcher {
		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {

			if (e.getID() == KeyEvent.KEY_PRESSED) {
				//
			} else if (e.getID() == KeyEvent.KEY_RELEASED) {

				if(e.getKeyCode()==KeyEvent.VK_UP ) {

					nextCharRob = tablero[robotFil-1][robotCol];

					//Si no encuentra una pared intermedia o exterior puede moverse

					if(nextCharRob.compareTo("0")!=0) {
						if(nextCharRob.compareTo("1")!=0) {
							//if(nextCharRob.compareTo("*")!=0) {
							if(nextCharRob.compareTo("!")==0) {
								//Se encuentra con un destino
								tablero[robotFil][robotCol]=debCharRob;
								tablero[robotFil-1][robotCol]="@";
								robotFil-=1;
								debCharRob="!";
							} else if(nextCharRob.compareTo("#")==0) {
								//Se encuentra con una caja
								//Mueve la caja a una posicion vacia
								if(tablero[robotFil-2][robotCol].compareTo("-")==0) {
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil-1][robotCol]="@";
									tablero[robotFil-2][robotCol]="#";
									robotFil-=1;
									debCharRob="-";
								} else if(tablero[robotFil-2][robotCol].compareTo("!")==0) {
									//Mueve la caja a un destino
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil-1][robotCol]="@";
									tablero[robotFil-2][robotCol]="*";
									robotFil-=1;
									debCharRob="-";
								} else {
									//No mueve la caja
									debCharRob="-";
								}
							} else if(nextCharRob.compareTo("*")==0) {
								//Se encuentra un destino resuelto

								if(tablero[robotFil-2][robotCol].compareTo("-")==0) {
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil-1][robotCol]="@";
									tablero[robotFil-2][robotCol]="#";
									robotFil-=1;
									debCharRob="!";
								} else if(tablero[robotFil-2][robotCol].compareTo("!")==0) {
									//Mueve la caja a un destino
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil-1][robotCol]="@";
									tablero[robotFil-2][robotCol]="*";
									robotFil-=1;
									debCharRob="!";
								} else {
									//No mueve la caja
									debCharRob="-";
								}

							} else {
								tablero[robotFil][robotCol]=debCharRob;
								tablero[robotFil-1][robotCol]="@";
								robotFil-=1;
								debCharRob="-";
							}

						}

						//debCharRob = nextCharRob;

						//Actualizar botones
						int cont = 0;
						for(int i=0; i<filas; i++) {
							for(int j=0; j<columnas; j++) {
								((AbstractButton)componentes[cont]).setText(tablero[i][j]);
								cont++;
							}

						}
						//}
					}
				}

				if(e.getKeyCode()==KeyEvent.VK_DOWN ) {

					nextCharRob = tablero[robotFil+1][robotCol];

					//Si no encuentra una pared intermedia o exterior puede moverse

					if(nextCharRob.compareTo("0")!=0) {
						if(nextCharRob.compareTo("1")!=0) {
							//if(nextCharRob.compareTo("*")!=0) {
							if(nextCharRob.compareTo("!")==0) {
								//Se encuentra con un destino
								tablero[robotFil][robotCol]=debCharRob;
								tablero[robotFil+1][robotCol]="@";
								robotFil+=1;
								debCharRob="!";
							} else if(nextCharRob.compareTo("#")==0) {
								//Se encuentra con una caja
								//Mueve la caja a una posicion vacia
								if(tablero[robotFil+2][robotCol].compareTo("-")==0) {
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil+1][robotCol]="@";
									tablero[robotFil+2][robotCol]="#";
									robotFil+=1;
									debCharRob="-";
								} else if(tablero[robotFil+2][robotCol].compareTo("!")==0) {
									//Mueve la caja a un destino
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil+1][robotCol]="@";
									tablero[robotFil+2][robotCol]="*";
									robotFil+=1;
									debCharRob="-";
								} else {
									//No mueve la caja
									debCharRob="-";
								}
							} else if(nextCharRob.compareTo("*")==0) {
								//Se encuentra un destino resuelto

								if(tablero[robotFil+2][robotCol].compareTo("-")==0) {
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil+1][robotCol]="@";
									tablero[robotFil+2][robotCol]="#";
									robotFil+=1;
									debCharRob="!";
								} else if(tablero[robotFil-2][robotCol].compareTo("!")==0) {
									//Mueve la caja a un destino
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil+1][robotCol]="@";
									tablero[robotFil+2][robotCol]="*";
									robotFil+=1;
									debCharRob="!";
								} else {
									//No mueve la caja
									debCharRob="-";
								}

							} else {
								tablero[robotFil][robotCol]=debCharRob;
								tablero[robotFil+1][robotCol]="@";
								robotFil+=1;
								debCharRob="-";
							}

						}

						//debCharRob = nextCharRob;

						//Actualizar botones
						int cont = 0;
						for(int i=0; i<filas; i++) {
							for(int j=0; j<columnas; j++) {
								((AbstractButton)componentes[cont]).setText(tablero[i][j]);
								cont++;
							}

						}
						//}
					}
				}

				if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					//tablero[robotFil-1][robotCol]="@";



					nextCharRob = tablero[robotFil][robotCol-1];

					//Si no encuentra una pared intermedia o exterior puede moverse

					if(nextCharRob.compareTo("0")!=0) {
						if(nextCharRob.compareTo("1")!=0) {
							//if(nextCharRob.compareTo("*")!=0) {
							if(nextCharRob.compareTo("!")==0) {
								//Se encuentra con un destino
								tablero[robotFil][robotCol]=debCharRob;
								tablero[robotFil][robotCol-1]="@";
								robotCol-=1;
								debCharRob="!";
							} else if(nextCharRob.compareTo("#")==0) {
								//Se encuentra con una caja
								//Mueve la caja a una posicion vacia
								if(tablero[robotFil][robotCol-2].compareTo("-")==0) {
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil][robotCol-1]="@";
									tablero[robotFil][robotCol-2]="#";
									robotCol-=1;
									debCharRob="-";
								} else if(tablero[robotFil][robotCol-2].compareTo("!")==0) {
									//Mueve la caja a un destino
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil][robotCol-1]="@";
									tablero[robotFil][robotCol-2]="*";
									robotCol-=1;
									debCharRob="-";
								} else {
									//No mueve la caja
									debCharRob="-";
								}
							} else if(nextCharRob.compareTo("*")==0) {
								//Se encuentra un destino resuelto

								if(tablero[robotFil][robotCol-2].compareTo("-")==0) {
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil][robotCol-1]="@";
									tablero[robotFil][robotCol-2]="#";
									robotCol-=1;
									debCharRob="!";
								} else if(tablero[robotFil][robotCol-2].compareTo("!")==0) {
									//Mueve la caja a un destino
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil][robotCol-1]="@";
									tablero[robotFil][robotCol-2]="*";
									robotCol-=1;
									debCharRob="!";
								} else {
									//No mueve la caja
									debCharRob="-";
								}

							} else {
								tablero[robotFil][robotCol]=debCharRob;
								tablero[robotFil][robotCol-1]="@";
								robotCol-=1;
								debCharRob="-";
							}

						}

						//debCharRob = nextCharRob;

						//Actualizar botones
						int cont = 0;
						for(int i=0; i<filas; i++) {
							for(int j=0; j<columnas; j++) {
								((AbstractButton)componentes[cont]).setText(tablero[i][j]);
								cont++;
							}

						}
						//}
					}
				}


				if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					//tablero[robotFil-1][robotCol]="@";



					nextCharRob = tablero[robotFil][robotCol+1];

					//Si no encuentra una pared intermedia o exterior puede moverse

					if(nextCharRob.compareTo("0")!=0) {
						if(nextCharRob.compareTo("1")!=0) {
							//if(nextCharRob.compareTo("*")!=0) {
							if(nextCharRob.compareTo("!")==0) {
								//Se encuentra con un destino
								tablero[robotFil][robotCol]=debCharRob;
								tablero[robotFil][robotCol+1]="@";
								robotCol+=1;
								debCharRob="!";
							} else if(nextCharRob.compareTo("#")==0) {
								//Se encuentra con una caja
								//Mueve la caja a una posicion vacia
								if(tablero[robotFil][robotCol+2].compareTo("-")==0) {
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil][robotCol+1]="@";
									tablero[robotFil][robotCol+2]="#";
									robotCol+=1;
									debCharRob="-";
								} else if(tablero[robotFil][robotCol+2].compareTo("!")==0) {
									//Mueve la caja a un destino
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil][robotCol+1]="@";
									tablero[robotFil][robotCol+2]="*";
									robotCol+=1;
									debCharRob="-";
								} else {
									//No mueve la caja
									debCharRob="-";
								}
							} else if(nextCharRob.compareTo("*")==0) {
								//Se encuentra un destino resuelto

								if(tablero[robotFil][robotCol+2].compareTo("-")==0) {
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil][robotCol+1]="@";
									tablero[robotFil][robotCol+2]="#";
									robotCol+=1;
									debCharRob="!";
								} else if(tablero[robotFil][robotCol+2].compareTo("!")==0) {
									//Mueve la caja a un destino
									tablero[robotFil][robotCol]=debCharRob;
									tablero[robotFil][robotCol+1]="@";
									tablero[robotFil][robotCol+2]="*";
									robotCol+=1;
									debCharRob="!";
								} else {
									//No mueve la caja
									debCharRob="-";
								}

							} else {
								tablero[robotFil][robotCol]=debCharRob;
								tablero[robotFil][robotCol+1]="@";
								robotCol+=1;
								debCharRob="-";
							}

						}

						//debCharRob = nextCharRob;

						//Actualizar botones
						int cont = 0;
						for(int i=0; i<filas; i++) {
							for(int j=0; j<columnas; j++) {
								((AbstractButton)componentes[cont]).setText(tablero[i][j]);
								cont++;
							}

						}
						//}
					}
				}

				//Sumar un movimiento realizado
				totalMov++;

				//CONTROLADOR DE HISTORIAL
				String[][] tabTemp = new String[filas][columnas];

				for(int i=0;i<filas;i++) {
					for(int j=0;j<columnas;j++) {
						tabTemp[i][j] = tablero[i][j];
					}
				} 


				historial.add(tabTemp);
				indiceHistorial++;

				//CONTROLAR GANAR PARTIDA
				boolean todaviaHayCaja = false;
				for(int i=0; i<filas;i++) {
					for(int j=0;j<columnas;j++) {
						if(tablero[i][j].compareTo("#")==0) {
							todaviaHayCaja = true;
						}
					}
				}

				if(!todaviaHayCaja) {
					JOptionPane.showMessageDialog(panelMenu, "Has ganado! Movimientos totales: "+totalMov, "ENHORABUENA", 1);
					tablero = null;
					panelMenu.removeAll();
					repaint();
				}

			} else if (e.getID() == KeyEvent.KEY_TYPED) {

			}
			return false;

		}

	} 
}
