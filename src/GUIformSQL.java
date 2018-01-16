import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JTextField;

import GUItools.GUIobjects;
import mySQL.MySQL_101;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIformSQL {

	JFrame frame;
	private JTextField textIP;
	private JTextField textPort;
	private JTextField textUser;
	private JTextField textPas;
	private JTextField textDB;
	private JLabel lblPort;
	private JLabel lblUser;
	private JLabel lblPassworld;
	private JLabel lblDb;

	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIformSQL window = new GUIformSQL();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIformSQL() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()  {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textIP = new JTextField();
		textIP.setText("5.29.193.52");
		textIP.setColumns(10);
		textIP.setBounds(162, 30, 116, 20);
		frame.getContentPane().add(textIP);
		
		textPort = new JTextField();
		textPort.setText("3306");
		textPort.setColumns(10);
		textPort.setBounds(162, 59, 116, 20);
		frame.getContentPane().add(textPort);
		
		textUser = new JTextField();
		textUser.setText("oop1");
		textUser.setColumns(10);
		textUser.setBounds(162, 89, 116, 20);
		frame.getContentPane().add(textUser);
		
		textPas = new JTextField();
		textPas.setText("Lambda1();");
		textPas.setColumns(10);
		textPas.setBounds(162, 120, 116, 20);
		frame.getContentPane().add(textPas);
		
		textDB = new JTextField();
		textDB.setText("oop_course_ariel");
		textDB.setColumns(10);
		textDB.setBounds(162, 154, 116, 20);
		frame.getContentPane().add(textDB);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MySQL_101._ip = textIP.getText();
				MySQL_101._port = textPort.getText();
				MySQL_101._user = textUser.getText();
				MySQL_101._password = textPas.getText();
				MySQL_101._urld = textDB.getText();
				GUIobjects.isFinishSQL = true;
				frame.setVisible(false);
			}
		});
		btnOk.setBounds(162, 199, 106, 39);
		frame.getContentPane().add(btnOk);
		
		JLabel lblIp = new JLabel("ip:");
		lblIp.setBounds(139, 30, 36, 14);
		frame.getContentPane().add(lblIp);
		
		lblPort = new JLabel("port:");
		lblPort.setBounds(129, 59, 46, 14);
		frame.getContentPane().add(lblPort);
		
		lblUser = new JLabel("user:");
		lblUser.setBounds(129, 89, 46, 14);
		frame.getContentPane().add(lblUser);
		
		lblPassworld = new JLabel("password:");
		lblPassworld.setBounds(107, 120, 68, 14);
		frame.getContentPane().add(lblPassworld);
		
		lblDb = new JLabel("DB:");
		lblDb.setBounds(139, 154, 30, 14);
		frame.getContentPane().add(lblDb);
	}
}
