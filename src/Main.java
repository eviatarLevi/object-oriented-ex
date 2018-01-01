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

		scanDB DB = importCsv.csvMergeTodb("C:/1/ex/algo2/in/comb", 1);
		wifiDB wDB = runAlgo.Algo1(DB);
		exportCsv.DBtoCsv("C:/1/ex/algo1/out/algo1t.csv", DB);
		scanDB DBnoGps = importCsv.csvMergeTodb("C:/1/ex/algo2/in/combNoGps", 3);
		runAlgo.Algo2(DBnoGps, DB);
		exportCsv.DBtoCsv("C:/1/ex/algo2/out/algo2.csv", DBnoGps);
		exportCsv.wifiDBtoCsv("C:/1/ex/algo1/out/algo1.csv", wDB);

		
		
		
		
		
	}

}
