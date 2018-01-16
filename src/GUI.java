
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import GUItools.GUIobjects;
import GUItools.filterMode;
import algo.runAlgo;
import db.scanDB;
import db.wifiDB;
import files.exportCsv;
import files.exportKML;
import files.importCsv;
import filters.dateFilter;
import filters.idFilter;
import filters.locationFilter;
import mySQL.MySQL_101;
import update.DirWatcher;
import update.FileWatcher;
import update.upSQL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.TexturePaint;
import java.awt.Window;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class GUI {

	private JFrame frame;
	private JCheckBox chBfId;
	private JTextField textID;
	private JTextField textLOCx;
	private JTextField textLOCy;
	private JTextField textLOCdist;
	private JTextField textDateS;
	private JTextField textDateE;
	private JTextField textMAC;
	private JTextField textAlgo2String;
	private JTextField textALgo2mac1;
	private JTextField textALgo2Signal1;
	private JTextField textALgo2mac2;
	private JTextField textALgo2Signal2;
	private JTextField textALgo2mac3;
	private JTextField textALgo2Signal3;
	private GUIobjects Gobj = new GUIobjects();
	TimerTask taskFile;
	TimerTask taskFolder;
	Timer timerFolder;
	Timer timerFile;

	/**
	 * @wbp.nonvisual location=654,139
	 */
	private final JFileChooser fileChooser = new JFileChooser();
	private final JFileChooser folderChooser = new JFileChooser();
	private final JFileChooser saveChooser = new JFileChooser();
	private JTextField textIP;
	private JTextField textPort;
	private JTextField textUser;
	private JTextField textPas;
	private JTextField textDB;
	private JLabel kldb;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.getContentPane().setLocation(-248, -74);
		frame.setResizable(false);
		frame.setBounds(100, 100, 640, 389);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		folderChooser.setCurrentDirectory(new java.io.File("."));
		folderChooser.setDialogTitle("select folder");
		folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		folderChooser.setAcceptAllFileFilterUsed(false);
		JTextField projectName = new JTextField();
		projectName.setBounds(25, 11, 203, 41);
		projectName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		projectName.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(projectName.getText().equals("enter project name"))
					projectName.selectAll();
			}
		});
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(248, 71, 368, 235);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textIP = new JTextField();
		textIP.setText("5.29.193.52");
		textIP.setColumns(10);
		textIP.setBounds(156, 11, 116, 20);
		panel.add(textIP);
		
		textPort = new JTextField();
		textPort.setText("3306");
		textPort.setColumns(10);
		textPort.setBounds(156, 40, 116, 20);
		panel.add(textPort);
		
		textUser = new JTextField();
		textUser.setText("oop1");
		textUser.setColumns(10);
		textUser.setBounds(156, 70, 116, 20);
		panel.add(textUser);
		
		textPas = new JTextField();
		textPas.setText("Lambda1();");
		textPas.setColumns(10);
		textPas.setBounds(156, 101, 116, 20);
		panel.add(textPas);
		
		textDB = new JTextField();
		textDB.setText("oop_course_ariel");
		textDB.setColumns(10);
		textDB.setBounds(156, 135, 116, 20);
		panel.add(textDB);
		
		JButton button_2 = new JButton("OK");
		
		button_2.setBounds(156, 180, 106, 39);
		panel.add(button_2);
		
		kldb = new JLabel("DB:");
		kldb.setBounds(133, 135, 30, 14);
		panel.add(kldb);
		
		JLabel lkk = new JLabel("password:");
		lkk.setBounds(101, 101, 68, 14);
		panel.add(lkk);
		
		JLabel label_5 = new JLabel("user:");
		label_5.setBounds(123, 70, 46, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("port:");
		label_6.setBounds(123, 40, 46, 14);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("ip:");
		label_7.setBounds(133, 11, 36, 14);
		panel.add(label_7);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(248, 71, 363, 235);
		frame.getContentPane().add(panel_2);
		panel_2.setVisible(false);

		JButton btnRunAlgo1 = new JButton("");
		btnRunAlgo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wifiDB wDB = null;
				if(Gobj.DBwork != null)
					wDB = runAlgo.Algo1(Gobj.DBwork);		
				String r = null;
				if (wDB != null)
					r = wDB.findLoc(textMAC.getText());
				if(r==null)
					JOptionPane.showMessageDialog(null, "not found", "", 1);				
				else
					JOptionPane.showMessageDialog(null, r, "", 1);

			}
		});
		btnRunAlgo1.setIcon(new ImageIcon("./img/Vi.png"));
		btnRunAlgo1.setBounds(303, 3, 50, 49);
		panel_2.add(btnRunAlgo1);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = runAlgo.Algo2(textAlgo2String.getText(), Gobj.DBwork, false);
				JOptionPane.showMessageDialog(null, msg, "", 1);
			}
		});
		button.setIcon(new ImageIcon("./img/Vi.png"));
		button.setBounds(303, 63, 50, 49);
		panel_2.add(button);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = textALgo2mac1.getText() +"," + textALgo2Signal1.getText() + ","
						+ textALgo2mac2.getText() +"," + textALgo2Signal2.getText() + ","
						+ textALgo2mac3.getText() +"," + textALgo2Signal3.getText() ;
				String msg = runAlgo.Algo2(s, Gobj.DBwork, true);
				JOptionPane.showMessageDialog(null, msg, "", 1);
			}
		});
		button_1.setIcon(new ImageIcon("./img/Vi.png"));
		button_1.setBounds(303, 157, 50, 49);
		panel_2.add(button_1);

		textMAC = new JTextField();
		textMAC.setBounds(80, 29, 205, 20);
		panel_2.add(textMAC);
		textMAC.setColumns(10);

		JLabel lblMac = new JLabel("MAC");
		lblMac.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMac.setBounds(156, 11, 46, 14);
		panel_2.add(lblMac);

		JLabel lblAlgo = new JLabel("ALGO 1");
		lblAlgo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAlgo.setBounds(10, 13, 68, 14);
		panel_2.add(lblAlgo);

		JLabel lblAlgo_1 = new JLabel("ALGO 2");
		lblAlgo_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAlgo_1.setBounds(10, 65, 68, 14);
		panel_2.add(lblAlgo_1);

		JLabel lblString = new JLabel("String");
		lblString.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblString.setBounds(156, 63, 68, 25);
		panel_2.add(lblString);

		textAlgo2String = new JTextField();
		textAlgo2String.setColumns(10);
		textAlgo2String.setBounds(80, 88, 205, 20);
		panel_2.add(textAlgo2String);

		JLabel label = new JLabel("ALGO 2");
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label.setBounds(10, 121, 68, 14);
		panel_2.add(label);

		JLabel lblMac_1 = new JLabel("1");
		lblMac_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMac_1.setBounds(55, 140, 15, 25);
		panel_2.add(lblMac_1);

		textALgo2mac1 = new JTextField();
		textALgo2mac1.setColumns(10);
		textALgo2mac1.setBounds(80, 144, 116, 20);
		panel_2.add(textALgo2mac1);

		JLabel lblMac_2 = new JLabel("MAC");
		lblMac_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMac_2.setBounds(113, 119, 50, 25);
		panel_2.add(lblMac_2);

		textALgo2Signal1 = new JTextField();
		textALgo2Signal1.setColumns(10);
		textALgo2Signal1.setBounds(206, 144, 50, 20);
		panel_2.add(textALgo2Signal1);

		JLabel lblSignal = new JLabel("Signal");
		lblSignal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSignal.setBounds(206, 117, 50, 25);
		panel_2.add(lblSignal);

		JLabel label_1 = new JLabel("2");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(55, 169, 15, 25);
		panel_2.add(label_1);

		textALgo2mac2 = new JTextField();
		textALgo2mac2.setColumns(10);
		textALgo2mac2.setBounds(80, 173, 116, 20);
		panel_2.add(textALgo2mac2);

		textALgo2Signal2 = new JTextField();
		textALgo2Signal2.setColumns(10);
		textALgo2Signal2.setBounds(206, 173, 50, 20);
		panel_2.add(textALgo2Signal2);

		JLabel label_2 = new JLabel("3");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(55, 199, 15, 25);
		panel_2.add(label_2);

		textALgo2mac3 = new JTextField();
		textALgo2mac3.setColumns(10);
		textALgo2mac3.setBounds(80, 203, 116, 20);
		panel_2.add(textALgo2mac3);

		textALgo2Signal3 = new JTextField();
		textALgo2Signal3.setColumns(10);
		textALgo2Signal3.setBounds(206, 203, 50, 20);
		panel_2.add(textALgo2Signal3);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setLayout(null);
		panel_1.setBounds(248, 71, 363, 235);
		frame.getContentPane().add(panel_1);
		panel_1.setVisible(false);
		panel.setVisible(false);
		chBfId = new JCheckBox("id Filter");


		chBfId.setBounds(22, 7, 97, 23);
		panel_1.add(chBfId);

		JCheckBox chBfLoc = new JCheckBox("location Filter");

		chBfLoc.setBounds(22, 60, 97, 23);
		panel_1.add(chBfLoc);

		JCheckBox chBfDate = new JCheckBox("date Filter");

		chBfDate.setBounds(22, 138, 97, 23);
		panel_1.add(chBfDate);

		textID = new JTextField();
		textID.setEditable(false);
		textID.setBounds(32, 37, 142, 20);
		panel_1.add(textID);
		textID.setColumns(10);

		textLOCx = new JTextField();
		textLOCx.setEditable(false);
		textLOCx.setColumns(10);
		textLOCx.setBounds(32, 115, 86, 20);
		panel_1.add(textLOCx);

		textLOCy = new JTextField();
		textLOCy.setEditable(false);
		textLOCy.setColumns(10);
		textLOCy.setBounds(128, 115, 86, 20);
		panel_1.add(textLOCy);

		textLOCdist = new JTextField();
		textLOCdist.setEditable(false);
		textLOCdist.setColumns(10);
		textLOCdist.setBounds(224, 115, 57, 20);
		panel_1.add(textLOCdist);

		JLabel lblX = new JLabel("X");
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblX.setBounds(65, 92, 46, 14);
		panel_1.add(lblX);

		JLabel lblY = new JLabel("Y");
		lblY.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblY.setBounds(160, 92, 46, 14);
		panel_1.add(lblY);

		JLabel lblDist = new JLabel("dist");
		lblDist.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDist.setBounds(235, 92, 46, 14);
		panel_1.add(lblDist);

		textDateS = new JTextField();
		textDateS.setEditable(false);
		textDateS.setColumns(10);
		textDateS.setBounds(88, 168, 203, 20);
		panel_1.add(textDateS);

		textDateE = new JTextField();
		textDateE.setEditable(false);
		textDateE.setColumns(10);
		textDateE.setBounds(88, 199, 203, 20);
		panel_1.add(textDateE);

		JLabel lblStart = new JLabel("start - ");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStart.setBounds(32, 171, 46, 14);
		panel_1.add(lblStart);

		JLabel lblStop = new JLabel("end - ");
		lblStop.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStop.setBounds(32, 202, 46, 14);
		panel_1.add(lblStop);

		JRadioButton RadAnd = new JRadioButton("And");
		RadAnd.setSelected(true);

		RadAnd.setEnabled(false);
		RadAnd.setBounds(208, 7, 57, 23);
		panel_1.add(RadAnd);

		JRadioButton RadOr = new JRadioButton("Or");
		RadOr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RadAnd.setSelected(false);
			}
		});
		RadOr.setEnabled(false);
		RadOr.setBounds(208, 36, 57, 23);
		panel_1.add(RadOr);

		RadAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RadOr.setSelected(false);
			}
		});
		JCheckBox chckbxNot = new JCheckBox("NOT");
		chckbxNot.setBounds(276, 17, 57, 23);
		panel_1.add(chckbxNot);

		JButton runFilter = new JButton("");
		runFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gobj.Idfilter = new idFilter(textID.getText());
				Gobj.LocFilter = null;
				try {
					double x = Double.parseDouble(textLOCx.getText());
					double y = Double.parseDouble(textLOCy.getText());
					double dist = Double.parseDouble(textLOCdist.getText());
					Gobj.LocFilter = new locationFilter(x, y, dist);
				} catch (Exception e2) {

				}
				Gobj.format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
				Date start = null;
				Date end = null;
				try {
					start = Gobj.format.parse(textDateS.getText());
					end = Gobj.format.parse(textDateE.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block

				}
				Gobj.DateFilter = new dateFilter(start, end);
				if(Gobj.checksum==1)
				{
					if(chBfId.isSelected())
						if(chckbxNot.isSelected())
							Gobj.Idfilter.runOnNOT(Gobj.DBwork);
						else
							Gobj.Idfilter.runOn(Gobj.DBwork);
					else if (chBfLoc.isSelected())
						if(chckbxNot.isSelected())
							Gobj.LocFilter.runOnNOT(Gobj.DBwork);
						else
							Gobj.LocFilter.runOn(Gobj.DBwork);
					else if (chBfDate.isSelected())
						if(chckbxNot.isSelected())
							Gobj.DateFilter.runOnNOT(Gobj.DBwork);
						else
							Gobj.DateFilter.runOn(Gobj.DBwork);
				}
				else 
				{
					boolean n;
					if(RadAnd.isSelected())
						if(chckbxNot.isSelected())
							n = true;
						else
							n = false;
					else
						if(chckbxNot.isSelected())
							n = false;
						else
							n = true;
					if(chBfId.isSelected())
						if(n)
							Gobj.Idfilter.runOnNOT(Gobj.DBwork);
						else
							Gobj.Idfilter.runOn(Gobj.DBwork);
					if (chBfLoc.isSelected())
						if(n)
							Gobj.LocFilter.runOnNOT(Gobj.DBwork);
						else
							Gobj.LocFilter.runOn(Gobj.DBwork);
					if (chBfDate.isSelected())
						if(n)
							Gobj.DateFilter.runOnNOT(Gobj.DBwork);
						else
							Gobj.DateFilter.runOn(Gobj.DBwork);
				}
				try {
					Gobj.filterM = new filterMode(RadAnd.isSelected(), 
							chckbxNot.isSelected(), chBfId.isSelected(), 
							chBfLoc.isSelected(), chBfDate.isSelected());
					Gobj.saveObj("./SER/filters.ser");

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "OK", "", 1);

			}
		});
		runFilter.setIcon(new ImageIcon("./img/Vi.png"));
		runFilter.setBounds(303, 92, 50, 49);
		panel_1.add(runFilter);
		chBfId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chBfId.isSelected()) {
					Gobj.checksum++;
				} else {
					Gobj.checksum--;
				}
				if (Gobj.checksum > 2)
				{
					chBfId.setSelected(false);
					actionPerformed(null);
					JOptionPane.showMessageDialog(null, "נייתן לבחור רק 2", "", 1);
				}
			}
		});
		chBfLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chBfLoc.isSelected()) {
					Gobj.checksum++;
				} else {
					Gobj.checksum--;
				}
				if (Gobj.checksum > 2)
				{
					chBfLoc.setSelected(false);
					actionPerformed(null);
					JOptionPane.showMessageDialog(null, "נייתן לבחור רק 2", "", 1);
				}
			}
		});
		chBfDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chBfDate.isSelected()) {
					Gobj.checksum++;
				} else {
					Gobj.checksum--;
				}
				if (Gobj.checksum > 2)
				{
					chBfDate.setSelected(false);
					actionPerformed(null);
					JOptionPane.showMessageDialog(null, "נייתן לבחור רק 2", "", 1);
				}
			}
		});
		chBfId.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chBfId.isSelected()) {
					textID.setEditable(true);
				} else {
					textID.setEditable(false);
				}
				if(Gobj.checksum < 2)
				{
					RadAnd.setEnabled(false);
					RadOr.setEnabled(false);
				}
				else if (Gobj.checksum == 2) 
				{
					RadAnd.setEnabled(true);
					RadOr.setEnabled(true);
				}
			}
		});
		chBfLoc.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chBfLoc.isSelected()) {
					textLOCx.setEditable(true);
					textLOCy.setEditable(true);
					textLOCdist.setEditable(true);
				} else {
					textLOCx.setEditable(false);
					textLOCy.setEditable(false);
					textLOCdist.setEditable(false);
				}

				if(Gobj.checksum < 2)
				{
					RadAnd.setEnabled(false);
					RadOr.setEnabled(false);
				}
				else if (Gobj.checksum == 2) 
				{
					RadAnd.setEnabled(true);
					RadOr.setEnabled(true);
				}
			}
		});
		chBfDate.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chBfDate.isSelected()) {
					textDateE.setEditable(true);
					textDateS.setEditable(true);
				} else {
					textDateE.setEditable(false);
					textDateS.setEditable(false);
				}

				if(Gobj.checksum < 2)
				{
					RadAnd.setEnabled(false);
					RadOr.setEnabled(false);
				}
				else if (Gobj.checksum == 2) 
				{
					RadAnd.setEnabled(true);
					RadOr.setEnabled(true);
				}
			}
		});
		projectName.setText("enter project name");
		frame.getContentPane().add(projectName);
		projectName.setColumns(10);

		JPanel panel_0 = new JPanel();
		panel_0.setBackground(SystemColor.activeCaption);
		panel_0.setBounds(248, 71, 363, 235);
		frame.getContentPane().add(panel_0);
		panel_0.setLayout(null);
		panel_0.setVisible(false);

		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(238, 11, 50, 49);
		btnNewButton.setIcon(new ImageIcon("./img/Vi.png"));
		frame.getContentPane().add(btnNewButton);

		JButton openFb = new JButton("open csv folder");
		openFb.setHorizontalAlignment(SwingConstants.LEFT);

		openFb.setBounds(10, 68, 201, 49);
		openFb.setEnabled(false);
		openFb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		openFb.setIcon(new ImageIcon("./img/Opfoldr.jpg"));
		frame.getContentPane().add(openFb);
		JButton openCb = new JButton("open csv merge file");
		openCb.setHorizontalAlignment(SwingConstants.LEFT);

		openCb.setBounds(10, 126, 201, 49);
		openCb.setEnabled(false);
		openCb.setIcon(new ImageIcon("./img/csv.jpg"));
		openCb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(openCb);



		JButton btnNewButton_1 = new JButton("algo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(true);
				panel_0.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1.setBounds(10, 11, 343, 85);
		panel_0.add(btnNewButton_1);

		JButton btnFilters = new JButton("filters");

		btnFilters.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnFilters.setBounds(10, 96, 343, 85);
		panel_0.add(btnFilters);

		JButton btnCancelFolter = new JButton("cancel filters");
		btnCancelFolter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gobj.DBwork = new scanDB(Gobj.DB1);
				JOptionPane.showMessageDialog(null, "OK", "", 1);
			}
		});
		btnCancelFolter.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnCancelFolter.setBounds(10, 192, 343, 39);
		panel_0.add(btnCancelFolter);

		JButton btnSaveEndExit = new JButton("exit");
		btnSaveEndExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSaveEndExit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSaveEndExit.setBounds(380, 310, 244, 39);
		frame.getContentPane().add(btnSaveEndExit);

		JButton btnSaveproject = new JButton("load FIlters");
		btnSaveproject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gobj.openObj("./SER/filters.ser");
				filterMode f = Gobj.filterM;
				if(f.andORor) {
					RadAnd.setSelected(true);
					RadOr.setSelected(false);
				} else {
					RadAnd.setSelected(false);
					RadOr.setSelected(true);
				}
				if(f.NOT)
					chckbxNot.setSelected(true);
				else
					chckbxNot.setSelected(false);
				if(f.idF)
					chBfId.setSelected(true);
				else
					chBfId.setSelected(false);
				if(f.locF)
					chBfLoc.setSelected(true);
				else
					chBfLoc.setSelected(false);
				if(f.dateF)
					chBfDate.setSelected(true);
				else
					chBfDate.setSelected(false);

				textDateS.setText(Gobj.DateFilter.getStart(Gobj.format));
				textDateE.setText(Gobj.DateFilter.getEnd(Gobj.format));
				textID.setText(Gobj.Idfilter.getId());
				if(Gobj.LocFilter!=null) {
					textLOCx.setText(""+Gobj.LocFilter.getX());
					textLOCy.setText(""+Gobj.LocFilter.getY());
					textLOCdist.setText(""+Gobj.LocFilter.getDist());
				}

			}
		});
		btnSaveproject.setHorizontalAlignment(SwingConstants.LEFT);
		btnSaveproject.setEnabled(false);
		btnSaveproject.setIcon(new ImageIcon("./img/save.jpg"));
		btnSaveproject.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSaveproject.setBounds(335, 8, 201, 49);
		frame.getContentPane().add(btnSaveproject);

		JButton btnCsvExport = new JButton("csv Export");
		btnCsvExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = saveChooser.showSaveDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						exportCsv.DBtoCsv(saveChooser.getSelectedFile().toString() + ".csv", Gobj.DBwork);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnCsvExport.setHorizontalAlignment(SwingConstants.LEFT);
		btnCsvExport.setIcon(new ImageIcon("./img/csvex.jpg"));
		btnCsvExport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCsvExport.setEnabled(false);
		btnCsvExport.setBounds(10, 246, 201, 49);
		frame.getContentPane().add(btnCsvExport);

		JButton btnBack = new JButton("<-");
		btnBack.setEnabled(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(false);
				panel_0.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(258, 316, 65, 30);
		frame.getContentPane().add(btnBack);

		JButton btnKmlExport = new JButton("KML Export");
		btnKmlExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = saveChooser.showSaveDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					exportKML.dbToKml(saveChooser.getSelectedFile().toString() + ".kml", Gobj.DBwork);
				}
			}
		});
		btnKmlExport.setIcon(new ImageIcon("./img/KML.jpg"));
		btnKmlExport.setHorizontalAlignment(SwingConstants.LEFT);
		btnKmlExport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnKmlExport.setEnabled(false);
		btnKmlExport.setBounds(10, 306, 201, 49);
		frame.getContentPane().add(btnKmlExport);
		
		JButton openSQLb = new JButton("open SQL tabel");
		openSQLb.setEnabled(false);
		openSQLb.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent arg0) {
				panel.setVisible(true);
			}
				
			
		});
		openSQLb.setHorizontalAlignment(SwingConstants.LEFT);
		openSQLb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		openSQLb.setBounds(10, 186, 201, 49);
		frame.getContentPane().add(openSQLb);
		openFb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = folderChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION)
				{
					Gobj.FolderName = folderChooser.getSelectedFile().toString();
					try {
						Gobj.DB1 = importCsv.csvFolderTodb(Gobj.FolderName, 1);
						Gobj.DBwork = new scanDB(Gobj.DB1);
						taskFolder = new DirWatcher(Gobj.FolderName,"csv") {

							@Override
							protected void onChange(File file, String action) {
								JOptionPane.showMessageDialog(null, "The file "+file.getName()+" changed");
								try {
									Gobj.DB1 = importCsv.csvFolderTodb(Gobj.FolderName, 1);
								} catch (IOException | ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Gobj.DBwork = new scanDB(Gobj.DB1);
							}
						};
						timerFolder = new Timer();
						timerFolder.schedule( taskFolder , new Date(), 1000 );
						panel_0.setVisible(true);
						btnKmlExport.setEnabled(true);
						btnCsvExport.setEnabled(true);
						btnSaveproject.setEnabled(true);
						btnBack.setEnabled(true);
						panel_1.setVisible(false);
						panel_2.setVisible(false);
					} catch (IOException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "שגיאה בפתיחת תיקיה", "", 1);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "שגיאה בפתיחת תיקיה", "", 1);
			}
		});
		openCb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileFilter(new FileFilter() {
					public String getDescription() {
						return "*.csv";
					}
					public boolean accept(File f) {
						if (f.isDirectory()) 
							return true;
						else 
							return f.getName().toLowerCase().endsWith(".csv");	
					}
				});;
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					Gobj.csvMergeName = fileChooser.getSelectedFile().toString();
					try {
						Gobj.DB1 = importCsv.csvMergeTodb(Gobj.csvMergeName, 1);
						Gobj.DBwork = new scanDB(Gobj.DB1);
						
						taskFile = new FileWatcher(new File(Gobj.csvMergeName)) {

							@Override
							protected void onChange(File file) {
								JOptionPane.showMessageDialog(null, "The file "+file.getName()+" changed");
								try {
									Gobj.DB1 = importCsv.csvMergeTodb(Gobj.csvMergeName, 1);
								} catch (IOException | ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Gobj.DBwork = new scanDB(Gobj.DB1);
							}
						};
						timerFile = new Timer();
						timerFile.schedule( taskFile , new Date(), 1000 );
						panel_0.setVisible(true);
						btnKmlExport.setEnabled(true);
						btnCsvExport.setEnabled(true);
						btnSaveproject.setEnabled(true);
						btnBack.setEnabled(true);
						panel_1.setVisible(false);
						panel_2.setVisible(false);
					} catch (IOException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "שגיאה בפתיחת תיקיה", "", 1);
					}

				}
				else
					JOptionPane.showMessageDialog(null, "שגיאה בפתיחת תיקיה", "", 1);
			}
		});


		projectName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				projectName.setEditable(false);
				openFb.setEnabled(true);
				openCb.setEnabled(true);
				btnSaveproject.setEnabled(true);
				btnSaveEndExit.setEnabled(true);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				projectName.setEditable(false);
				openFb.setEnabled(true);
				openCb.setEnabled(true);
				btnSaveproject.setEnabled(true);
				btnSaveEndExit.setEnabled(true);
				openSQLb.setEnabled(true);

			}
		});
		btnFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(true);
				panel_0.setVisible(false);
			}
		});
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MySQL_101._ip = textIP.getText();
				MySQL_101._port = textPort.getText();
				MySQL_101._user = textUser.getText();
				MySQL_101._password = textPas.getText();
				MySQL_101._urld = kldb.getText();
				try {
					Gobj.DB1 = MySQL_101.getData();
					if (Gobj.DB1 != null)
					{
							Gobj.DBwork = new scanDB(Gobj.DB1);
							panel_0.setVisible(true);
							btnKmlExport.setEnabled(true);
							btnCsvExport.setEnabled(true);
							btnSaveproject.setEnabled(true);
							btnBack.setEnabled(true);
							panel_1.setVisible(false);
							panel_2.setVisible(false);
							panel.setVisible(false);

					}
					else
						JOptionPane.showMessageDialog(null, "שגיאה בפתיחת SQL", "", 1);
				} catch (NumberFormatException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "שגיאה בפתיחת SQL", "", 1);
				}
				
			}
		});

	}
}
