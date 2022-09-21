package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 910, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		
		Panel panel = new Panel();
		panel.setBackground(SystemColor.window);
		panel.setBounds(0, 0, 910, 537);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(-50, 0, 732, 501);
		imagenFondo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/menu-img.png")));
		panel.add(imagenFondo);
		
		JLabel logo = new JLabel("");
		logo.setBounds(722, 80, 150, 156);
		logo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/aH-150px.png")));
		panel.add(logo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 500, 910, 37);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCopyR = new JLabel("Desarrollado por Gonzalo Cozzo © 2022");
		lblCopyR.setBounds(315, 11, 284, 19);
		lblCopyR.setForeground(new Color(240, 248, 255));
		lblCopyR.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel_1.add(lblCopyR);
		
		//Barra para controlar la ventana 
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		panel.add(header);
		
		//Botón salir
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object [] opciones ={"Aceptar","Cancelar"};
				 int eleccion = JOptionPane.showOptionDialog(rootPane,"En realidad desea realizar cerrar la aplicacion","Mensaje de Confirmacion",
				 JOptionPane.YES_NO_OPTION,
				 JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
				 if (eleccion == JOptionPane.YES_OPTION)
				 {
				 System.exit(0);
				 }else{
				 }
			}
		});
		btnSalir.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(775, 440, 44, 40);
		panel.add(btnSalir);
                
                JLabel lblSalir = new JLabel("SALIR");
		lblSalir.setBounds(770, 410, 60, 24);
		lblSalir.setBackground(SystemColor.window);
		panel.add(lblSalir);
		lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalir.setForeground(SystemColor.textHighlight);
		lblSalir.setFont(new Font("Roboto Light", Font.PLAIN, 20));
	
		
		//Botón Login
		JPanel btnLogin = new JPanel(); 
		btnLogin.setBounds(754, 300, 83, 70);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnLogin.setLayout(null);
		btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnLogin.setBackground(SystemColor.window);
		panel.add(btnLogin);
		
		JLabel imagenLogin = new JLabel("");
		imagenLogin.setBounds(0, 0, 80, 70);
		btnLogin.add(imagenLogin);
		imagenLogin.setHorizontalAlignment(SwingConstants.CENTER);
		imagenLogin.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/login.png")));
		
		JLabel lblTitulo = new JLabel("LOGIN");
		lblTitulo.setBounds(754, 265, 83, 24);
		lblTitulo.setBackground(SystemColor.window);
		panel.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(SystemColor.textHighlight);
		lblTitulo.setFont(new Font("Roboto Light", Font.PLAIN, 20));
	}
	
	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }
    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
}
}
