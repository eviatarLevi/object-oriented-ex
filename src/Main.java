import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import algo.runAlgo;
import db.scanDB;
import db.wifiDB;
import files.exportCsv;
import files.exportKML;
import files.importCsv;
import filters.locationFilter;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub

		scanDB DB = importCsv.csvMergeTodb("C:/1/r", 1);
		wifiDB wDB = runAlgo.Algo1(DB);
		exportCsv.wifiDBtoCsv("C:/1/e/test2.csv", wDB);
		scanDB DBnoGps = importCsv.csvMergeTodb("C:/1/c", 3);
		runAlgo.Algo2(DBnoGps, DB);	
		exportCsv.DBtoCsv("C:/1/e/test.csv", DBnoGps);
		exportKML.dbToKml("C:/1/e/test1.kml", DB);
		exportKML.dbToKml("C:/1/e/test2.kml", DBnoGps);

		
		
		
		
		
	}

}
