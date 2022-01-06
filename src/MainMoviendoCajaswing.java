
/**
 * 
 */

/**
 * @author sergi
 *
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMoviendoCajaswing {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		VentanaPrincipal ventanaPri = new VentanaPrincipal();
		ventanaPri.lanzar();
/*
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ventanaMain.lanzar();
			}
		});*/
	}

	/*
	 * private void ventanaPrincipal() {
	 * 
	 * //AcListener lis = new AcListener();
	 * 
	 * //Create and set up the window. JFrame ventanaMain = new
	 * JFrame("Moviendo Cajas ");
	 * ventanaMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * 
	 * JPanel panelTitulo = new JPanel();
	 * ventanaMain.getContentPane().add(panelTitulo, BorderLayout.NORTH);
	 * panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 100 , 20));
	 * 
	 * JPanel panelMenu = new JPanel(); ventanaMain.getContentPane().add(panelMenu,
	 * BorderLayout.CENTER); panelMenu.setLayout(new FlowLayout(FlowLayout.CENTER,
	 * 100 , 50));
	 * 
	 * JLabel titulo = new JLabel("Menu principal"); panelTitulo.add(titulo);
	 * 
	 * JButton dis = new JButton(); JButton jugar = new JButton();
	 * 
	 * dis.setLabel("DISEÑADOR"); dis.setMinimumSize(new Dimension(120,30));
	 * dis.setMaximumSize(new Dimension(200,50)); dis.setPreferredSize(new
	 * Dimension(150,50));
	 * 
	 * jugar.setLabel("JUGAR"); jugar.setMinimumSize(new Dimension(120,30));
	 * jugar.setMaximumSize(new Dimension(200,50)); jugar.setPreferredSize(new
	 * Dimension(150,50));
	 * 
	 * panelMenu.add(dis); panelMenu.add(jugar);
	 * 
	 * dis.addActionListener(this); jugar.addActionListener(this);
	 * 
	 * 
	 * 
	 * JPanel panelMain = new JPanel();
	 * 
	 * ventanaMain.getContentPane().add(panelMain, BorderLayout.NORTH);
	 * 
	 * panelMain.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	 * 
	 * 
	 * JPanel panelPintar = new JPanel();
	 * 
	 * ventanaMain.getContentPane().add(panelPintar, BorderLayout.CENTER);
	 * 
	 * panelPintar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	 * 
	 * 
	 * JPanel panelEtiqueta = new JPanel();
	 * 
	 * ventanaMain.getContentPane().add(panelEtiqueta, BorderLayout.SOUTH);
	 * 
	 * panelEtiqueta.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	 * 
	 * 
	 * 
	 * 
	 * //Panel main
	 * 
	 * 
	 * JButton menos = new JButton(); JButton mas = new JButton();
	 * 
	 * panelMain.add(menos); panelMain.add(mas);
	 * 
	 * JLabel label = new JLabel("Lados"); panelMain.add(label);
	 * 
	 * JTextField lados = new JTextField("0"); panelMain.add(lados);
	 * 
	 * 
	 * 
	 * //Panel etiqueta
	 * 
	 * //Metodo para validar el numero de lados del cuadro de texto lados
	 * 
	 * // String nombrePoligono = calcularNombrePol(lados);
	 * 
	 * // JLabel labEtiqueta = new JLabel("Cuadrado");
	 * 
	 * //Add the ubiquitous "Hello World" label. // JLabel label = new
	 * JLabel("Hello World"); // ventanaMain.getContentPane().add(label);
	 * 
	 * //CREAR OBJETO POLIGONO MODELO //este objeto se lo pasamos panelModelo para
	 * dinujarlo
	 * 
	 * //Display the window. ventanaMain.pack(); ventanaMain.setVisible(true); }
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 */

}
