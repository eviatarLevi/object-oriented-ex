package main.java.mySQL;

import java.sql.PreparedStatement;
import java.io.IOException;
import java.nio.channels.GatheringByteChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.db.scanDB;
import main.java.files.exportCsv;

import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MySQL_101 {

	  public static String _ip = "5.29.193.52";
	  public static String _port = "3306";
	  public static String _urld = "oop_course_ariel";
	  public static String _url = "jdbc:mysql://"+_ip+":"+ _port+"/"+_urld;
	  public static String _user = "oop1";
	  public static String _password = "Lambda1();";
	  public static Connection _con = null;
	//  private static String _url = "jdbc:mysql://"+_ip+":3306/oop_course_ariel";
      
    public static void main(String[] args) throws IOException, NumberFormatException, ParseException {
    	scanDB db = getData();
    	//scanDB db = new scanDB();
    	//insert_table2(1000, db);
    	getData();
    	exportCsv.DBtoCsv("C://1/testDB.csv", db);
  //  	insert_table1(max_id);
    }
   
    public synchronized static scanDB getData() throws NumberFormatException, ParseException {
        Statement st = null;
        ResultSet rs = null;
        scanDB db = new scanDB();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {     
            _con = DriverManager.getConnection(_url, _user, _password);
            st = _con.createStatement();
            PreparedStatement pst = _con.prepareStatement("SELECT * FROM ex4_db");
            rs = pst.executeQuery();
            int ind=0;
            while (rs.next()) {
            	int size = rs.getInt(7);
            	int len = 7+2*size;
            	int index = db.addScan(Double.parseDouble(rs.getString(4)),
            			Double.parseDouble(rs.getString(5)),
            			Double.parseDouble(rs.getString(6)),
            			format.parse(rs.getString(2)),rs.getString(3));
            		for(int i=8;i<len;i+=2){
            			 db.addWifi(index, rs.getString(i), "" , 0, Integer.parseInt(rs.getString(i+1)));
            		}
            	
            	ind++;
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySQL_101.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (st != null) { st.close(); }
                if (_con != null) { _con.close();  }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(MySQL_101.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return db;
    }
    public static Date getUpdate() throws ParseException 
    {
    	Statement st = null;
        ResultSet rs = null;
        Date date = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {     
            _con = DriverManager.getConnection(_url, _user, _password);
            st = _con.createStatement();
            rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = 'oop_course_ariel' AND TABLE_NAME = 'ex4_db'");
            if (rs.next()) {
               date = format.parse(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySQL_101.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (st != null) { st.close(); }
                if (_con != null) { _con.close();  }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(MySQL_101.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return date;
    }
}