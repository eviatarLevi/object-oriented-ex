import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
	public static void main(String[] args) throws IOException, ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // date format 
		// read
		linkedScan DB = inFile.toDB("C:/1/1");
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
		/*
		DBf = DB.locaFilter(35.0000, 34.0012, 0.0004);
		*/
		
		DB.toKml("C:/1/2/new.kml");
		//DBf.toKml("C:/1/2/new.kml"); // after filter
	}
}
