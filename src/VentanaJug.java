import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
	private JPanel panelTitulo, panelMenu;
	private String[][] tablero;
	private Component[] componentes;
	private String nextCharRob, debCharRob;
	private ArrayList<String[][]> historial;
	private KeyboardFocusManager manager;
	private ControladorKeys control;
	private Boolean todaviaHayCaja;
	private JFileChooser loadFile;

	public VentanaJug() {
		super("JUGAR");
		this.setSize(new Dimension(850, 600));
		this.setResizable(false);
		manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		control = new ControladorKeys();
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub

		panelTitulo = new JPanel();
		this.getContentPane().add(panelTitulo, BorderLayout.NORTH);
		//panelTitulo.setLayout(new FlowLayout(FlowLayout.LEFT, 20 , 20));

		panelMenu = new JPanel();
		this.getContentPane().add(panelMenu, BorderLayout.SOUTH); 
		//panelMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 20 , 20));

		/*panelControl = new JPanel();
		this.getContentPane().add(panelControl, BorderLayout.SOUTH); 
		panelControl.setLayout(new FlowLayout(FlowLayout.CENTER, 20 , 20));*/

		//panelTitulo.setBackground(Color.GREEN);

		tablero = null;

		//RESOLVER EN X TIEMPO
		resolver = new JButton("Resolver");
		panelTitulo.add(resolver);
		resolver.addActionListener(this);

		//etiquetaTiempo = new JLabel(new ImageIcon(getClass().getResource("ico/time.png")));
		etiquetaTiempo = new JLabel("Tiempo");
		JLabel seg = new JLabel("s");
		time = 360;
		tiempo = new JLabel(time+"");
		panelTitulo.add(etiquetaTiempo);
		panelTitulo.add(tiempo);
		panelTitulo.add(seg);

		//JProgressBar barraProg = new JProgressBar();
		JSlider barraSlid = new JSlider(JSlider.HORIZONTAL,0,1000,20 );
		setLayout( new GridLayout(2,1) );
		//add( barraProg );
		barraSlid.setValue(360);
		barraSlid.setPaintTicks(true);
		barraSlid.setMajorTickSpacing(100);
		barraSlid.setMinorTickSpacing(25);
		barraSlid.setBackground(new Color(220,220,220));
		//barraSlid.setBorder( new TitledBorder("Desplazame") );
		barraSlid.addChangeListener( new ChangeListener() {
			public void stateChanged( ChangeEvent evt ) {
				tiempo.setText(barraSlid.getValue()+"");
			}
		} );
		panelTitulo.add(barraSlid);

		// GUARDAR, CARGAR, ANTERIOR, SIGUIENTE
		JLabel espacio = new JLabel("                             ");
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

		JLabel instruccion = new JLabel("Desplaza el robot con las flechas del raton");
		panelTitulo.add(instruccion);
		//PANEL menu


		//panelTitulo.setPreferredSize(new Dimension(600,100));
		//pack();

		//Aspecto
		panelTitulo.setBackground(new Color(220,220,220));
		panelMenu.setBackground(new Color(220,220,220));

		resolver.setBackground(new Color(50,50,50,255));
		resolver.setFont(new Font("Agency FB", Font.BOLD, 20));
		resolver.setForeground(Color.WHITE);
		resolver.setBorder(BorderFactory.createLineBorder(new Color(50,50,50,255), 20));

		etiquetaTiempo.setFont(new Font("Agency FB", Font.BOLD, 20));
		etiquetaTiempo.setForeground(Color.BLACK);

		tiempo.setBackground(new Color(50,50,50,255));
		tiempo.setFont(new Font("Agency FB", Font.BOLD, 20));
		tiempo.setForeground(Color.BLACK);

		seg.setBackground(new Color(50,50,50,255));
		seg.setFont(new Font("Agency FB", Font.BOLD, 20));
		seg.setForeground(Color.BLACK);
		
		instruccion.setBackground(new Color(50,50,50,255));
		instruccion.setFont(new Font("Agency FB", Font.BOLD, 20));
		instruccion.setForeground(Color.BLACK);

		//guardar.setBackground(new Color(50,50,50,255));
		guardar.setFont(new Font("Agency FB", Font.BOLD, 20));
		guardar.setForeground(Color.WHITE);
		//guardar.setBorder(BorderFactory.createLineBorder(new Color(50,50,50,255), 20));
		
		guardar.setBackground(new Color(220,220,220));
		guardar.setEnabled(false);
		guardar.setBorder(BorderFactory.createLineBorder(new Color(220,220,220), 20));

		cargar.setBackground(new Color(50,50,50,255));
		cargar.setFont(new Font("Agency FB", Font.BOLD, 20));
		cargar.setForeground(Color.WHITE);
		cargar.setBorder(BorderFactory.createLineBorder(new Color(50,50,50,255), 20));

		anterior.setBackground(new Color(50,50,50,255));
		anterior.setFont(new Font("Agency FB", Font.BOLD, 20));
		anterior.setForeground(Color.WHITE);
		anterior.setBorder(BorderFactory.createLineBorder(new Color(50,50,50,255), 20));

		siguiente.setBackground(new Color(50,50,50,255));
		siguiente.setFont(new Font("Agency FB", Font.BOLD, 20));
		siguiente.setForeground(Color.WHITE);
		siguiente.setBorder(BorderFactory.createLineBorder(new Color(50,50,50,255), 20));


		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getActionCommand().equals("Resolver")==true) {



			if(tablero==null) {
				JOptionPane.showMessageDialog(panelMenu, "Debes cargar una partida primero", "ERROR", 1);
			}else {
				//Mensaje de carga
				//resolver.setText("Calculando...");
				etiquetaTiempo.setText("Calculando...");
				
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
					resolver.setText("Fin");
					repaint();
					//Comprobar si ha tenido solucion o no
					if(res.compareTo("N")!=0) {
						resolver.setText("Fin");
						JOptionPane.showMessageDialog(panelMenu, "Movimientos: " + res + '\n' + "Total: " + res.length(), "Resultado", 1);
						dispose();
					} else {
						JOptionPane.showMessageDialog(panelMenu, "No hay ninguna solucion posible", "Resultado", 1);
						dispose();
					}
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

			time = 360;
			filas = 0;
			columnas = 0;
			robotFil = 0;
			robotCol = 0;
			indiceHistorial = 0;
			totalMov = 0;
			componentes = null;
			nextCharRob = "-"; 
			debCharRob = "-";
			historial = null;
			manager = null;
			control = null;
			tablero = null;
			//todaviaHayCaja = false;


			//Cargar el fichero
			loadFile = new JFileChooser();
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
					panelMenu.setLayout(new GridLayout(filas,columnas,3,3));


					//Anniadir el numero de botones correspondiente
					for(int i=0; i<filas*columnas; i++) {				
						panelMenu.add(new JButton());
					}

					componentes = panelMenu.getComponents();

					//Cargar problema en botones
					int cont = 0;
					for(int i=0; i<filas; i++) {
						for(int j=0; j<columnas; j++) {
							//((AbstractButton)componentes[cont]).setText(tablero[i][j]);
							((AbstractButton)componentes[cont]).setEnabled(false);
							if(tablero[i][j].compareTo("@")==0) {
								((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/robot.png")));	
							} else if(tablero[i][j].compareTo("#")==0) {
								((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/money.png")));	
							} else if(tablero[i][j].compareTo("!")==0) {
								((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/destino.png")));	
							} else if(tablero[i][j].compareTo("1")==0) {
								((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/pared.png")));	
							} else if(tablero[i][j].compareTo("0")==0) {
								((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/pared.png")));	
							} else if(tablero[i][j].compareTo("*")==0) {
								((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/destinoAlcanzado.png")));	
							} else {
								((AbstractButton)componentes[cont]).setIcon(null);
							}
							//((AbstractButton)componentes[cont]).setBackground(new Color(50,50,50,10));
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

					debCharRob="-";
					indiceHistorial = 0;
					historial = new ArrayList<String[][]>();

					historial.add(tabTemp);
					//indiceHistorial++;

					//Controlador de eventos del teclado
					manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
					control = new ControladorKeys();
					manager.addKeyEventDispatcher(control);

					//Quitar boton de cargar
					cargar.setBackground(new Color(220,220,220));
					cargar.setEnabled(false);
					cargar.setBorder(BorderFactory.createLineBorder(new Color(220,220,220), 20));

					//Poner boton guardar
					guardar.setBackground(new Color(50,50,50,255));
					guardar.setFont(new Font("Agency FB", Font.BOLD, 20));
					guardar.setForeground(Color.WHITE);
					guardar.setBorder(BorderFactory.createLineBorder(new Color(50,50,50,255), 20));
					guardar.setEnabled(true);

				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, "La entrada del fichero no es valida, pasate por el diseñador!");
				}
			}
			repaint();
		}

		if(e.getActionCommand().equals("<-")==true) {
			if(indiceHistorial>0) {
				//historial.remove(indiceHistorial);
				indiceHistorial--;
				tablero = historial.get(indiceHistorial);
				totalMov--;
				//Cargar historial en botones
				int cont = 0;
				for(int i=0; i<filas; i++) {
					for(int j=0; j<columnas; j++) {
						((AbstractButton)componentes[cont]).setEnabled(false);
						if(tablero[i][j].compareTo("@")==0) {
							robotFil = i;
							robotCol = j;
							((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/robot.png")));	
						} else if(tablero[i][j].compareTo("#")==0) {
							((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/money.png")));	
						} else if(tablero[i][j].compareTo("!")==0) {
							((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/destino.png")));	
						} else if(tablero[i][j].compareTo("1")==0) {
							((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/pared.png")));	
						} else if(tablero[i][j].compareTo("0")==0) {
							((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/pared.png")));	
						} else if(tablero[i][j].compareTo("*")==0) {
							((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/destinoAlcanzado.png")));	
						} else {
							((AbstractButton)componentes[cont]).setIcon(null);
						} 

						cont++;
					}

				}

			}
		}

		if(e.getActionCommand().equals("->")==true) {
			if(indiceHistorial<historial.size()-1) {
				//historial.remove(indiceHistorial);
				indiceHistorial++;
				tablero = historial.get(indiceHistorial);
				totalMov++;
				//Cargar historial en botones
				int cont = 0;
				for(int i=0; i<filas; i++) {
					for(int j=0; j<columnas; j++) {
						((AbstractButton)componentes[cont]).setEnabled(false);
						if(tablero[i][j].compareTo("@")==0) {
							robotFil = i;
							robotCol = j;
							((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/robot.png")));	
						} else if(tablero[i][j].compareTo("#")==0) {
							((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/money.png")));	
						} else if(tablero[i][j].compareTo("!")==0) {
							((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/destino.png")));	
						} else if(tablero[i][j].compareTo("1")==0) {
							((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/pared.png")));	
						} else if(tablero[i][j].compareTo("0")==0) {
							((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/pared.png")));	
						} else if(tablero[i][j].compareTo("*")==0) {
							((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/destinoAlcanzado.png")));	
						} else {
							((AbstractButton)componentes[cont]).setIcon(null);
						} 

						cont++;
					}

				}
			}
		}

	}

	private class ControladorKeys implements KeyEventDispatcher {
		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {

			todaviaHayCaja = false;

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
								((AbstractButton)componentes[cont]).setEnabled(false);
								if(tablero[i][j].compareTo("@")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/robot.png")));	
								} else if(tablero[i][j].compareTo("#")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/money.png")));	
								} else if(tablero[i][j].compareTo("!")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/destino.png")));	
								} else if(tablero[i][j].compareTo("1")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/pared.png")));	
								} else if(tablero[i][j].compareTo("0")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/pared.png")));	
								} else if(tablero[i][j].compareTo("*")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/destinoAlcanzado.png")));	
								} else {
									((AbstractButton)componentes[cont]).setIcon(null);
								}

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
								((AbstractButton)componentes[cont]).setEnabled(false);
								if(tablero[i][j].compareTo("@")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/robot.png")));	
								} else if(tablero[i][j].compareTo("#")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/money.png")));	
								} else if(tablero[i][j].compareTo("!")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/destino.png")));	
								} else if(tablero[i][j].compareTo("1")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/pared.png")));	
								} else if(tablero[i][j].compareTo("0")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/pared.png")));	
								} else if(tablero[i][j].compareTo("*")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/destinoAlcanzado.png")));	
								} else {
									((AbstractButton)componentes[cont]).setIcon(null);
								}

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
								((AbstractButton)componentes[cont]).setEnabled(false);
								if(tablero[i][j].compareTo("@")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/robot.png")));	
								} else if(tablero[i][j].compareTo("#")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/money.png")));	
								} else if(tablero[i][j].compareTo("!")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/destino.png")));	
								} else if(tablero[i][j].compareTo("1")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/pared.png")));	
								} else if(tablero[i][j].compareTo("0")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/pared.png")));	
								} else if(tablero[i][j].compareTo("*")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/destinoAlcanzado.png")));	
								} else {
									((AbstractButton)componentes[cont]).setIcon(null);
								}

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
								((AbstractButton)componentes[cont]).setEnabled(false);
								if(tablero[i][j].compareTo("@")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/robot.png")));	
								} else if(tablero[i][j].compareTo("#")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/money.png")));	
								} else if(tablero[i][j].compareTo("!")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/destino.png")));	
								} else if(tablero[i][j].compareTo("1")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/pared.png")));	
								} else if(tablero[i][j].compareTo("0")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/pared.png")));	
								} else if(tablero[i][j].compareTo("*")==0) {
									((AbstractButton)componentes[cont]).setIcon(new ImageIcon(getClass().getResource("ico/destinoAlcanzado.png")));	
								} else {
									((AbstractButton)componentes[cont]).setIcon(null);
								}

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

				for(int i=0; i<filas;i++) {
					for(int j=0;j<columnas;j++) {
						if(tablero[i][j].compareTo("#")==0) {
							todaviaHayCaja = true;
						}
					}
				}

				if(!todaviaHayCaja) {
					//JOptionPane pane = new JOptionPane();
					//pane.showMessageDialog(panelMenu, "Has ganado! Movimientos totales: "+totalMov, "ENHORABUENA", 1);

					//manager = null;
					//control = null;

					manager.removeKeyEventDispatcher(control);

					JOptionPane.showMessageDialog(panelMenu, "Has ganado! Movimientos totales: "+totalMov, "ENHORABUENA", 1);
					//panelMenu.removeAll();
					/*
					time = 0;
					filas = 0;
					columnas = 0;
					robotFil = 0;
					robotCol = 0;
					indiceHistorial = 0;
					totalMov = 0;
					//componentes = null;
					nextCharRob = ""; 
					debCharRob = "";
					//historial = null;
					//manager = null;
					//control = null;
					//tablero = null;
					todaviaHayCaja = false;*/



					//Restablecer el boton cargar
					cargar.setBackground(new Color(50,50,50,255));
					cargar.setEnabled(true);
					cargar.setBorder(BorderFactory.createLineBorder(new Color(50,50,50,255), 20));

					dispose();

					//repaint();
				}

			} else if (e.getID() == KeyEvent.KEY_TYPED) {

			}
			return false;

		}

	} 
}
