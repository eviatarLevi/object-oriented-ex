package files;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import db.scanDB;
import db.wifiDB;
import wifiScan.Wifi;
import wifiScan.wifiContiner;



public class exportCsv {
	
	/**
	 * @see data to csv
	 * @param fileName
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean DBtoCsv(String fileName, scanDB db) throws IOException {
		try {
			PrintWriter pw = new PrintWriter(new File(fileName));
			StringBuilder sb = new StringBuilder();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			sb.append("Time,ID,Lat,Lon,Alt,#WiFi networks");
			for (int i = 1; i <= 10; i++) {
				sb.append(",SSID"+i+",MAC"+i+",chanel"+i+",Signal"+i);
			}
			sb.append(System.getProperty("line.separator"));
			for (int i = 0; i < db.getSize(); i++) {
				wifiContiner wc = db.getIndex(i);
				sb.append(df.format(wc.getDate())+"," + wc.getDeviceID() + "," + wc.getLat() + "," 
						+ wc.getLon() +","+wc.getAlt() + "," + wc.getSize());
				for (int j = 0; j < wc.getSize(); j++) {
					Wifi w = wc.getWifi(j);
					sb.append(","+w.getSsid()+"," + w.getMac() +"," + w.getChanel() +"," + w.getSignal());
				}
				sb.append(System.getProperty("line.separator"));
			}
			pw.append(sb.toString());
			pw.close();
			System.out.println("csv create complete");
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	public static boolean wifiDBtoCsv(String fileName, wifiDB wd) throws IOException {
		try {
			PrintWriter pw = new PrintWriter(new File(fileName));
			StringBuilder sb = new StringBuilder();
			sb.append("mac,ssid,chanel,Lat,Lon,Alt");
			sb.append(System.getProperty("line.separator"));
			for (int i = 0; i < wd.getSize(); i++) {
				sb.append(wd.getIndex(i));
				sb.append(System.getProperty("line.separator"));
			}
			System.out.println("csv create complete");
			pw.append(sb.toString());
			pw.close();
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}
	
	
}
