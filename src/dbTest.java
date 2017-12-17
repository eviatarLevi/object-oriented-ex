import static org.junit.Assert.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
/**
 * 
 * @author evitar levi & shahar botesazan
 *
 */
public class dbTest {
/**
 * @test for
 */
	@Test
	public void testAddToLinkedscan() {
		linkedScan DB = new linkedScan();
		DB.add("123", "abc", "ABC", 3.444, 3.233, 2, 0, "aa:bb:00:dd:33", "home" ,"wpa", "wifi",new Date(), 2, -5);
		assertEquals(DB.head.sC.networks[0].id, "ABC");
		assertEquals(DB.head.sC.networks[0].mac, "aa:bb:00:dd:33");
		if(DB.head.sC.size==0)
			fail("error");
		
	}
	@Test
	public void testDateFilter() throws IOException, ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // date format
		// read
		linkedScan DB = inFile.toDB("testFile/scan");
		linkedScan DBf; // DB for filter
		// date filter
		Date d1 = format.parse("2017-10-27 16:00:00");
		Date d2 = format.parse("2017-10-27 16:20:00");
		DBf = DB.dateFilter(d1,d2);
		if(DBf.size>0)
			if(!(DBf.head.sC.networks[0].firstsin.after(d1) &&
					DBf.head.sC.networks[0].firstsin.before(d2)))
				fail("error");
		
		
		
	}

	@Test
	public void testIdFilter() throws IOException {
		// read
		linkedScan DB = inFile.toDB("testFile/scan");
		linkedScan DBf; // DB for filter
		//id filter
		DBf = DB.idFilter("model=SHIELD Tablet");
		if(DBf.size>0)
			assertEquals(DBf.head.sC.networks[0].id, "model=SHIELD Tablet");
		
	}

	@Test
	public void testLocaFilter() throws IOException {
		linkedScan DB = inFile.toDB("testFile/scan");
		linkedScan DBf; // DB for filter
		// location filter (lat , lon , distans)
		double x=32.16766121892341,y=34.80988155918773,dist=0.000001;
		
		DBf = DB.locaFilter(x,y, dist);
		if(DBf.size>0)
			if(!(DBf.head.sC.networks[0].CurrentLatitude>x-dist &&
					DBf.head.sC.networks[0].CurrentLatitude<x+dist &&
					DBf.head.sC.networks[0].CurrentLongitude>y-dist &&
					DBf.head.sC.networks[0].CurrentLongitude<y+dist))
				fail("error");
	}

}
