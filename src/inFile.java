import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author evitar levi & shahar botesazan
 * @version 1.0
 */
public class inFile {
	
	/**
	 * 
	 * @param folderName
	 * @return linkedScan (linkeList with data)
	 * @throws IOException
	 * @see linkedScan 
	 * @link linkedScan
	 */
	public static linkedScan toDB (String folderName) throws IOException {
		linkedScan lS = new linkedScan();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // date format 
		//DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // date format 
		File folder = new File(folderName);
		FilenameFilter filter = new FilenameFilter() {// csv file filter
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		};
		for (File file : folder.listFiles(filter)){ //for all csv file
			FileInputStream fi = new FileInputStream(file);

			String username = null,  devicename = null,  id = null;
			double latitude=0,  longitude=0,  altitude=0,  accutacy = 0;
			String mac = null, ssid = null,  authmode = null, type = null;
			Date firstsin = null; int chanel = 0,  rssi = 0; 

			try {
				int ch;
				int rowCount = 0;
				int columCount = 0;
				String str = "";
				while(true) {
					ch = fi.read();
					if (ch!=','&& ch != -1 && ch !=10)
						str+=(char)ch+"";
					else{
						if (rowCount == 0) {
							if (columCount == 2)
								id = str;
							else if (columCount == 4)
								devicename = str;
						}
						else if (rowCount >1)
						{
							switch(columCount) {
							case 0: mac = new String(str); break;
							case 1: ssid = new String(str); break;
							case 2: authmode = new String(str); break;
							case 3: firstsin = format.parse(str); break;
							case 4: chanel = Integer.parseInt(str); break;
							case 5: rssi = Integer.parseInt(str); break;
							case 6: latitude = Double.parseDouble(str); break;
							case 7: longitude = Double.parseDouble(str); break;
							case 8: altitude = Double.parseDouble(str); break;
							case 9: accutacy = Double.parseDouble(str); break;
							case 10: type = new String(str);
							lS.add(username, devicename, id, latitude,
									longitude, altitude, accutacy, mac,
									ssid, authmode, type, firstsin, chanel, rssi); // add to data
							break;
							}
						}
						str = "";
						if(ch==-1)
							break;
					}
					if (ch == ',') columCount++;
					if (ch == 10) {rowCount++; columCount=0;}
				}
				fi.close();
				System.out.println("csv read complete");
			} 
			catch (Exception e) {
				fi.close();
				System.out.println("ERROR inFile");
				System.out.println(e);
			}
		}
		return lS;
	}
}
