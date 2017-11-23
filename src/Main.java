import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // date format 
		// read
		linkedScan DB = inFile.toDB("testFile/scan");
		DB.toCsv("C:/1/2/test.csv");
		
		linkedScan DBf; // DB for filter
		//id filter
		/*
		DBf = DB.idFilter("model=SHIELD Tablet");
		*/
		
		// date filter
		/*
		Date d1 = format.parse("2017-10-27 16:00:00");
		Date d2 = format.parse("2017-10-27 16:20:00");
		DBf = DB.dateFilter(d1,d2);
		*/
		
		// location filter (lat , lon , distans)
		
		//DBf = DB.locaFilter(32.16766121892341,34.80988155918773, 0.000001);
		
		
		DB.toKml("C:/1/2/new.kml");
		//DBf.toKml("C:/1/2/new.kml"); // after filter
	}

}
