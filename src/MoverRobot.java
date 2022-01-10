
import java.util.ArrayList;

public class MoverRobot {


	public MoverRobot() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<ArrayList<String>> moverRobot(ArrayList<ArrayList<String>> matriz2, String mov,
			boolean puedoMover) {
		ArrayList<ArrayList<String>> habitacionTemp = clonarHabitacion(matriz2);
		ArrayList<Integer> robot = encuentraRobot(habitacionTemp);
		int filaRobot = robot.get(0);

		int colRobot = robot.get(1);
		if (mov.equals("d")) {
			return moverRobotDcha(puedoMover, habitacionTemp, filaRobot, colRobot);

		} else if (mov.equals("i")) {
			return moverRobotIzq(puedoMover, habitacionTemp, filaRobot, colRobot);

		} else if (mov.equals("b")) {
			return moverRobotAbajo(puedoMover, habitacionTemp, filaRobot, colRobot);

		} else if (mov.equals("a")) {
			return moverRobotArriba(puedoMover, habitacionTemp, filaRobot, colRobot);
		}
		return null;
	}

	public ArrayList<ArrayList<String>> moverRobotArriba(boolean puedoMover,
			ArrayList<ArrayList<String>> habitacionTemp, int filaRobot, int colRobot) {
		if (habitacionTemp.get(filaRobot - 1).get(colRobot).equals("#") && puedoMover) {
			if (compruebaSiguiente(habitacionTemp, filaRobot - 2, colRobot)=="1") {
				
				habitacionTemp.get(filaRobot - 2).set(colRobot, "*");
				habitacionTemp.get(filaRobot - 1).set(colRobot, "@");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;
			} else if (compruebaSiguineteNormal(habitacionTemp, filaRobot - 2, colRobot)=="1") {
				habitacionTemp.get(filaRobot - 2).set(colRobot, "#");
				habitacionTemp.get(filaRobot - 1).set(colRobot, "@");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;
			}
		} else if (habitacionTemp.get(filaRobot - 1).get(colRobot).equals("*") && puedoMover) {
			
			if (compruebaSiguiente(habitacionTemp, filaRobot - 2, colRobot)=="1") {
			
				habitacionTemp.get(filaRobot - 2).set(colRobot, "*");
				habitacionTemp.get(filaRobot - 1).set(colRobot, "+");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;
			} else if (compruebaSiguineteNormal(habitacionTemp, filaRobot - 2, colRobot)=="1") {
				
				habitacionTemp.get(filaRobot - 2).set(colRobot, "#");
				habitacionTemp.get(filaRobot - 1).set(colRobot, "+");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;
			}
		} else if (habitacionTemp.get(filaRobot - 1).get(colRobot).equals("-")) {
			
			habitacionTemp.get(filaRobot - 1).set(colRobot, "@");
			robotAInicio(habitacionTemp, filaRobot, colRobot);
			return habitacionTemp;
		} else if (habitacionTemp.get(filaRobot - 1).get(colRobot).equals("!")) {
			
			habitacionTemp.get(filaRobot - 1).set(colRobot, "+");
			robotAInicio(habitacionTemp, filaRobot, colRobot);
			return habitacionTemp;
		}
		return habitacionTemp;
	}

	public ArrayList<ArrayList<String>> moverRobotAbajo(boolean puedoMover,
			ArrayList<ArrayList<String>> habitacionTemp, int filaRobot, int colRobot) {
		if (habitacionTemp.get(filaRobot + 1).get(colRobot).equals("#") && puedoMover) {
			
			if (compruebaSiguiente(habitacionTemp, filaRobot + 2, colRobot)=="1") {
				
				habitacionTemp.get(filaRobot + 2).set(colRobot, "*");
				habitacionTemp.get(filaRobot + 1).set(colRobot, "@");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;

			} else if (compruebaSiguineteNormal(habitacionTemp, filaRobot + 2, colRobot)=="1") {
				
				habitacionTemp.get(filaRobot + 2).set(colRobot, "#");
				habitacionTemp.get(filaRobot + 1).set(colRobot, "@");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;
			}
		} else if (habitacionTemp.get(filaRobot + 1).get(colRobot).equals("*") && puedoMover) {
			
			if (compruebaSiguiente(habitacionTemp, filaRobot + 2, colRobot)=="1") {
				
				habitacionTemp.get(filaRobot + 2).set(colRobot, "*");
				habitacionTemp.get(filaRobot + 1).set(colRobot, "+");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;
			} else if (compruebaSiguineteNormal(habitacionTemp, filaRobot + 2, colRobot)=="1") {
				
				habitacionTemp.get(filaRobot + 2).set(colRobot, "#");
				habitacionTemp.get(filaRobot + 1).set(colRobot, "+");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;
			}
		} else if (habitacionTemp.get(filaRobot + 1).get(colRobot).equals("-")) {
			
			habitacionTemp.get(filaRobot + 1).set(colRobot, "@");
			robotAInicio(habitacionTemp, filaRobot, colRobot);
			return habitacionTemp;
		} else if (habitacionTemp.get(filaRobot + 1).get(colRobot).equals("!")) {
			
			habitacionTemp.get(filaRobot + 1).set(colRobot, "+");
			robotAInicio(habitacionTemp, filaRobot, colRobot);
			return habitacionTemp;
		}
		return habitacionTemp;
	}

	public ArrayList<ArrayList<String>> moverRobotIzq(boolean puedoMover,
			ArrayList<ArrayList<String>> habitacionTemp, int filaRobot, int colRobot) {
		if (habitacionTemp.get(filaRobot).get(colRobot - 1).equals("#") && puedoMover) {
			if (compruebaSiguiente(habitacionTemp, filaRobot, colRobot - 2)=="1") {
				
				habitacionTemp.get(filaRobot).set(colRobot - 2, "*");
				habitacionTemp.get(filaRobot).set(colRobot - 1, "@");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;

			} else if (compruebaSiguineteNormal(habitacionTemp, filaRobot, colRobot - 2)=="1") {
				
				habitacionTemp.get(filaRobot).set(colRobot - 2, "#");
				habitacionTemp.get(filaRobot).set(colRobot - 1, "@");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;
			}
		} else if (habitacionTemp.get(filaRobot).get(colRobot - 1).equals("*") && puedoMover) {
			if (compruebaSiguiente(habitacionTemp, filaRobot, colRobot - 2)=="1") {
				
				habitacionTemp.get(filaRobot).set(colRobot - 2, "*");
				habitacionTemp.get(filaRobot).set(colRobot - 1, "+");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;
			} else if (compruebaSiguineteNormal(habitacionTemp, filaRobot, colRobot - 2)=="1") {
				habitacionTemp.get(filaRobot).set(colRobot - 2, "#");
				habitacionTemp.get(filaRobot).set(colRobot - 1, "+");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;
			}
		} else if (habitacionTemp.get(filaRobot).get(colRobot - 1).equals("-")) {
			
			habitacionTemp.get(filaRobot).set(colRobot - 1, "@");
			robotAInicio(habitacionTemp, filaRobot, colRobot);
			return habitacionTemp;
		} else if (habitacionTemp.get(filaRobot).get(colRobot - 1).equals("!")) {
		
			habitacionTemp.get(filaRobot).set(colRobot - 1, "+");
			robotAInicio(habitacionTemp, filaRobot, colRobot);
			return habitacionTemp;
		}
		return habitacionTemp;
	}

	public ArrayList<ArrayList<String>> moverRobotDcha(boolean puedoMover,
			ArrayList<ArrayList<String>> habitacionTemp, int filaRobot, int colRobot) {
		if (habitacionTemp.get(filaRobot).get(colRobot + 1).equals("#") && puedoMover) {
			if (compruebaSiguiente(habitacionTemp, filaRobot, colRobot + 2)=="1") {
				
				habitacionTemp.get(filaRobot).set(colRobot + 2, "*");
				habitacionTemp.get(filaRobot).set(colRobot + 1, "@");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;

			} else if (compruebaSiguineteNormal(habitacionTemp, filaRobot, colRobot + 2)=="1") {
				
				habitacionTemp.get(filaRobot).set(colRobot + 2, "#");
				habitacionTemp.get(filaRobot).set(colRobot + 1, "@");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;
			}
		} else if (habitacionTemp.get(filaRobot).get(colRobot + 1).equals("*") && puedoMover) {
			
			if (compruebaSiguiente(habitacionTemp, filaRobot, colRobot + 2)=="1") {
				
				habitacionTemp.get(filaRobot).set(colRobot + 2, "*");
				habitacionTemp.get(filaRobot).set(colRobot + 1, "+");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;
			} else if (compruebaSiguineteNormal(habitacionTemp, filaRobot, colRobot + 2)=="1") {
				habitacionTemp.get(filaRobot).set(colRobot + 2, "#");
				habitacionTemp.get(filaRobot).set(colRobot + 1, "+");
				robotAInicio(habitacionTemp, filaRobot, colRobot);
				return habitacionTemp;
			}
		} else if (habitacionTemp.get(filaRobot).get(colRobot + 1).equals("-")) {
			
			habitacionTemp.get(filaRobot).set(colRobot + 1, "@");
			robotAInicio(habitacionTemp, filaRobot, colRobot);
			return habitacionTemp;
		} else if (habitacionTemp.get(filaRobot).get(colRobot + 1).equals("!")) {
		
			habitacionTemp.get(filaRobot).set(colRobot + 1, "+");
			robotAInicio(habitacionTemp, filaRobot, colRobot);
			return habitacionTemp;
		}
		return habitacionTemp;
	}

	public ArrayList<Integer> encuentraRobot(ArrayList<ArrayList<String>> m) {
		ArrayList<Integer> sol = new ArrayList<Integer>();
		for (int i = 0; i < m.size(); i++) {
			for (int j = 0; j < m.get(i).size(); j++) {
				if (m.get(i).get(j).equals("@") || m.get(i).get(j).equals("+")) {
					sol.add(i);
					sol.add(j);
					break;
				}
			}
		}
		return sol;
	}

	public ArrayList<ArrayList<String>> clonarHabitacion(ArrayList<ArrayList<String>> m) {
		ArrayList<ArrayList<String>> resutado = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < m.size(); i++) {
			resutado.add((ArrayList<String>) m.get(i).clone());
		}
		return resutado;
	}

	public String compruebaSiguiente(ArrayList<ArrayList<String>> m, int fila, int colunma) {

		if(m.get(fila).get(colunma).equals("!")) {
		
			return "1";
		} else {
			return "0";
		}
	}

	public String compruebaSiguineteNormal(ArrayList<ArrayList<String>> m, int fila, int colunma) {
		if(m.get(fila).get(colunma).equals("-")) {
			
			return "1";
		} else {
			return "0";
		}

	}

	public void robotAInicio(ArrayList<ArrayList<String>> m, int fila, int columna) {
		if (m.get(fila).get(columna).equals("+")) {
			m.get(fila).set(columna, "!");
		} else {
			m.get(fila).set(columna, "-");
		}
	}

}