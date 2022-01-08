
import java.util.ArrayList;
import java.util.Scanner;

public class LeerEntradaHabitacion {

	//Sacar a 4 metodos diferentes 

	private int n, m, coordXRobot, coordYRobot, numCajas, numDestinos;
	private int[][] cajas, destinos;
	private String simboloRobotInicial;
	private ArrayList<String> habitacion, entrada;
	private ArrayList<ArrayList<String>> resultado;

	public void setEntrada(ArrayList<String> ent) {
		habitacion = new ArrayList<String>();
		entrada = new ArrayList<String>();
		for(String fila:ent) 
			entrada.add(fila);
	}
	
	public ArrayList<ArrayList<String>> getResultado(){
		return resultado;
	}
	
	public void restablecer() {
		n = 0;
		m = 0;
		habitacion = null;
		entrada = null;
		coordXRobot = 0;
		coordYRobot = 0;
		numCajas = 0;
		numDestinos = 0;
		cajas = null;
		destinos = null;
		resultado = null;
	}

	public void leerEntradaHabitacion() {
		// TODO Auto-generated method stub

		resultado = new ArrayList<ArrayList<String>>();
		
		//Almacenar filas, columnas y la habitacion

		String primeraLinea = entrada.get(0);
		//n y m
		String[] nym = primeraLinea.split(" ");
		n = Integer.parseInt(nym[0]);
		m = Integer.parseInt(nym[1]);
		//habitacion
		for (int i=0; i<entrada.size();i++) {
			if(i!=0) {
				habitacion.add(entrada.get(i));
			}
		}


		// Almacena la wntrada t hace primeras comprobaciones de n y m, pero no revisa si hay
		//robot, cajas y destinos
		boolean entradaValida;

		entradaValida = leerEntrada();
		entradaValida = entradaValida & compruebaHabitacion();

		if(!entradaValida) {
			resultado = null;
		} else {

			ArrayList<ArrayList<String>> habitacionTemp = new ArrayList<ArrayList<String>>();

			for (int i=0; i< habitacion.size(); i++){
				ArrayList<String> temp2 = new ArrayList<String>();

				for (int j = 0; j < habitacion.get(i).length(); j++) {

					char temp = habitacion.get(i).charAt(j);
					if(coordXRobot==j && coordYRobot==i) {
						temp = '@';
					}	
					String charString = temp + "";
					temp2.add(charString);

				}

				habitacionTemp.add(temp2);

			}


			resultado = habitacionTemp;

			//La entrada esta bien formada

			//guardarCajasYDestinos();

			//			Habitacion hab = new Habitacion(habitacion,coordXRobot,coordYRobot,simboloRobotInicial,
			//					n,m, cajas, destinos);
			//		    hab.solucionarHabitacion2();
		}

	}

	private  void guardarCajasYDestinos() {

		cajas = new int[numCajas][2];
		destinos = new int[numDestinos][2];

		int contadorCajas = 0;
		int contadorDestinos = 0;

		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(habitacion.get(i).charAt(j)=='#') {
					cajas[contadorCajas][0]=j;
					cajas[contadorCajas][1]=i;
					habitacion.set(i, habitacion.get(i).substring(0, j)+"-"+habitacion.get(i).substring(j+1,habitacion.get(i).length()));
					contadorCajas++;

				} else if(habitacion.get(i).charAt(j)=='!') {
					destinos[contadorDestinos][0]=j;
					destinos[contadorDestinos][1]=i;
					habitacion.set(i, habitacion.get(i).substring(0, j)+"-"+habitacion.get(i).substring(j+1,habitacion.get(i).length()));
					contadorDestinos++;
				}
			}
		}

	}

	private  boolean compruebaHabitacion() {

		boolean robot = false;
		boolean relacionCD = false;

		//los generalizo
		//int numCajas =0;
		//int numDestinos=0;

		for(int i= 0; i<habitacion.size(); i++) {
			for(int j = 0; j<habitacion.get(i).length();j++) {
				if (habitacion.get(i).charAt(j)=='@' || habitacion.get(i).charAt(j)=='+' ) {
					robot = true;
					coordXRobot = j;
					coordYRobot = i;
					if(habitacion.get(i).charAt(j)=='@') {
						simboloRobotInicial="@";
						habitacion.set(i, habitacion.get(i).substring(0, j)+"-"+habitacion.get(i).substring(j+1,habitacion.get(i).length()));
					} else {
						simboloRobotInicial="!";
						habitacion.set(i, habitacion.get(i).substring(0, j)+"!"+habitacion.get(i).substring(j+1,habitacion.get(i).length()));
					}


				}
				if (habitacion.get(i).charAt(j)=='#') {
					numCajas++;
				}
				if (habitacion.get(i).charAt(j)=='!') {
					numDestinos++;
				}
			}
		}

		return numCajas == numDestinos && robot && numCajas!=0;
	}

	private  boolean leerEntrada() {
		// TODO Auto-generated method stub

		boolean entradaBienFormada = true;		

		//Con menos espacio no puede existir una solucion
		if(n<3 || m<3) {
			entradaBienFormada = false;
		}

		//Comprueba que el tama�o de la matriz corresponde con n y m
		//Comprueba n
		if(habitacion.size()!=n)
			entradaBienFormada = false;
		//Comprueba m
		if(entradaBienFormada == true) {

			int contador=0;
			int espacios = 0;
			boolean salir = false;

			while(!salir) {
				if (contador==habitacion.size()-1) {
					salir=true;
				}

				if(habitacion.get(contador).length()!=m) {
					entradaBienFormada=false;
					salir=true;
					//Comprobar que no son todo blancos
				} else {
					for(int i=0; i<habitacion.get(contador).length();i++) {
						if(habitacion.get(contador).charAt(i)==' ') {
							espacios++;
						}
					}
					if(espacios==m) {
						entradaBienFormada = false;
						salir=true;
					}

					espacios=0;

				}

				contador++;
			}
		}

		return entradaBienFormada;
	}

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//		leerEntradaHabitacion();
//
//	}

}