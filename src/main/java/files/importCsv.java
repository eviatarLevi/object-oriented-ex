package main.java.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.db.scanDB;

public class importCsv {
	/**
	 * 
	 * @param folderName
	 * @param dateType
	 * @return scanDB (DB with data)
	 * @throws IOException
	 * @throws ParseException 
	 * @see scanDB 
	 * @link scanDB
	 *  csv to data
	 */
	public static scanDB csvFolderTodb(String folderName,int dateType) throws IOException, ParseException
	{
		scanDB db = new scanDB();
		DateFormat format;
		if (dateType==1)
			format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		else if (dateType==2)
			format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		else
			format = new SimpleDateFormat("dd/MM/yy hh:mm");
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		File folder = new File(folderName);
		try {
			FilenameFilter filter = new FilenameFilter() {// csv file filter
				public boolean accept(File dir, String name) {
					return name.endsWith(".csv");
				}
			};
			for (File file : folder.listFiles(filter)){ //for all csv file
				br = new BufferedReader(new FileReader(file));
				line = br.readLine();
				String[] country = line.split(cvsSplitBy);
				String id = country[2];
				line = br.readLine();
				Date date = null;
				int i = -1;
				while ((line = br.readLine()) != null) {
					country = line.split(cvsSplitBy);
					if (country[10].equals("WIFI"))
						if (i >=0 && date.equals(format.parse(country[3])))
							db.addWifi(i, country[0], country[1], Integer.parseInt(country[4]), Integer.parseInt(country[5]));
						else{
							date = format.parse(country[3]);
							i = db.add(Double.parseDouble(country[6]), Double.parseDouble(country[7]),
									Double.parseDouble(country[8]), date, id, country[0], 
									country[1], Integer.parseInt(country[4]), 
									Integer.parseInt(country[5]));
						}
				}
				System.out.println("csv read complete");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return db;
	}
	/**
	 * 
	 * @param folderName
	 * @param dateType
	 * @return scanDB (DB with data)
	 * @throws IOException
	 * @throws ParseException 
	 * @see scanDB 
	 * @link scanDB
	 *  merge csv to data
	 */
	public static scanDB csvMergeTodb(String fileName,int dateType) throws IOException, ParseException
	{
		scanDB db = new scanDB();
		DateFormat format;
		if (dateType==1)
			format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		else if (dateType==2)
			format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		else
			format = new SimpleDateFormat("dd/MM/yy hh:mm");
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(fileName));
			line = br.readLine();
			String[] country = line.split(cvsSplitBy);
			Date date = null;
			int i = -1;
			while ((line = br.readLine()) != null) {
				country = line.split(cvsSplitBy);
				date = format.parse(country[0]);
				double[] p = new double[3];
				for (int j = 0; j < 3; j++) {
					if(country[2+j].equals("?"))
						p[j] = 0;
					else
						p[j] = Double.parseDouble(country[2+j]);
				}
				i = db.addScan(p[0], p[1],p[2], date, country[1]);
				for (int j = 0; j < 10; j++) {
					if (country.length > j*4 + 6)
						db.addWifi(i, country[7+j*4], country[6+j*4], 
								Integer.parseInt(country[8+j*4]), (int)Double.parseDouble(country[9+j*4]));
				} 	
			}

			System.out.println("merge csv read complete");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return db;
	}

}
