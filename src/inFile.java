import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
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
	 * csv to data
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
				System.err.println("ERROR inFile");
				System.err.println(e);
			}
		}
		return lS;
	}
	/**
	 * 
	 * @param folderName
	 * @return linkedScan (linkeList with data)
	 * @throws IOException
	 * @throws ParseException 
	 * @see linkedScan 
	 * @link linkedScan
	 * merge csv to data
	 */
	public static linkedScan MtoDB (String fileName,int dateType) throws IOException, ParseException {
		linkedScan lS = new linkedScan();
		DateFormat format;
		if (dateType==1)
			format = new  SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		else if (dateType==2)
			format = new  SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		else
			format = new  SimpleDateFormat("dd/MM/yy hh:mm");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {

            br = new BufferedReader(new FileReader(fileName));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                Date firstsin = format.parse(country[0]);
                String  id = country[1];
                for (int i = 2; i < 5; i++) 
                	if (country[i].equals("?"))
                		country[i]="0";
                double latitude=Double.parseDouble(country[2]);
                double longitude=Double.parseDouble(country[3]);
                double altitude=Double.parseDouble(country[4]);
                
                for (int i = 0; i < 10; i++) {
                	if (country.length > i*4 + 6)
                		lS.add("", "", id, latitude,
							longitude, altitude, 0,country[7+i*4],
							country[6+i*4], "", "", firstsin,
							Integer.parseInt(country[8+i*4]),
							(int)Double.parseDouble(country[9+i*4])); // add to data
				}
            }
            System.out.println("csv read complete");
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
		return lS;
	}
}
