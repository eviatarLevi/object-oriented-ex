import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import de.micromata.opengis.kml.v_2_2_0.*;

//linkedlist for the samples

/**
 * 
  * @author evitar levi & shahar botesazan
  * @version 1.0
  * 
 */
public class linkedScan {
	public class Node{
		scanContiner sC;
		Node next;
		public Node(){
			sC = new scanContiner();
			next = null;
		}
	}
	Node head;
	int size;
	/**
	 * @see constructor 
	 */
	public linkedScan(){
		head = new Node();
	}
	//add for filters
	public void add(scanContiner s) 
	{
		if (head.sC.networks[0]==null)
			head.sC = s;
		else{
			Node n =  new Node();
			n.sC = s;
			n.next = head;
			head = n;
		}
		size++;
		
	}
	// add for read csv
	/**
	 * @see add scan to linkedScan 
	 * @param username
	 * @param devicename
	 * @param id
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 * @param accutacy
	 * @param mac
	 * @param ssid
	 * @param authmode
	 * @param type
	 * @param firstsin
	 * @param chanel
	 * @param rssi
	 */
	public void add(String username, String devicename,String  id,
						double latitude, double longitude,
							double altitude, double accutacy,
								String mac,String ssid, String authmode,
									String type , Date firstsin , int chanel, int rssi)
	{
		//if new samples time
		if (head.sC.networks[0] == null || !(head.sC.networks[0].firstsin.equals(firstsin))){
			Node n = new Node();
			n.sC.add(username, devicename, id, latitude, 
					longitude, altitude, accutacy, mac, ssid, authmode,
						type, firstsin, chanel, rssi);
			n.next = head;
			head = n;
			size++;
		}
		// 
		else{
			head.sC.add(username, devicename, id, latitude,
						longitude, altitude, accutacy, mac, ssid, authmode,
							type, firstsin, chanel, rssi);
		}
	}
	/**
	 * @see  DateFilter - filtar scan From date first to date last
	 * @param first
	 * @param last
	 * @return linkedScan
	 */
	public linkedScan dateFilter (Date first,Date last)
	{
		linkedScan DbN = new linkedScan();
		Node n = head;
		while(n!=null)
		{
			if (n.sC.networks[0]!=null){
				Date d = n.sC.networks[0].firstsin;
				if (d.after(first) && d.before(last))
				{
					DbN.add(n.sC);
				}
			}
			n=n.next;
		}
		return DbN;
	}
	/**
	 * @see idFilter - filter scan per id
	 * @param id
	 * @return linkedScan
	 */
	public linkedScan idFilter (String id)
	{
		linkedScan DbN = new linkedScan();
		Node n = head;
		while(n!=null)
		{
			if (n.sC.networks[0]!=null){
				if (n.sC.networks[0].id.equals(id))
				{
					DbN.add(n.sC);
				}
			}
			n=n.next;
		}
		return DbN;
	}
	/**
	 * @see locaFilter - filter scan per location
	 * @param X
	 * @param Y
	 * @param dist
	 * @return linkedScan
	 */
	public linkedScan locaFilter (Double X,Double Y,Double dist)
	{
		linkedScan DbN = new linkedScan();
		Node n = head;
		while(n!=null)
		{
			if (n.sC.networks[0]!=null){
				Double Xn = n.sC.networks[0].CurrentLatitude;
				Double Yn = n.sC.networks[0].CurrentLongitude; 
				if (X+dist>=Xn && X-dist<=Xn && Y+dist>=Yn && Y-dist<=Yn)
				{
					DbN.add(n.sC);
				}
			}
			n=n.next;
		}
		return DbN;
	}
	// data to kml
	/**
	 * @see data to kml
	 * @param kmlFilePath
	 * @return boolean
	 */
	public boolean toKml(String kmlFilePath) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Kml kml = KmlFactory.createKml();
			Document document = new Document();
			document.setName("kml");
			//red
			Style red = new Style();IconStyle iconStyleRed = new IconStyle(); 
			Icon iconRed = new Icon(); iconRed.setHref("http://maps.google.com/mapfiles/ms/icons/red-dot.png");
			iconStyleRed.setScale(1);iconStyleRed.setIcon(iconRed);
			red.setId("red"); red.setIconStyle(iconStyleRed);
			document.addToStyleSelector(red);
			//yellow
			Style yellow = new Style();IconStyle iconStyleYellow = new IconStyle(); 
			Icon iconYellow = new Icon(); iconYellow.setHref("http://maps.google.com/mapfiles/ms/icons/yellow-dot.png");
			iconStyleYellow.setScale(1);iconStyleYellow.setIcon(iconYellow);
			yellow.setId("yellow"); yellow.setIconStyle(iconStyleYellow);
			document.addToStyleSelector(yellow);
			//green
			Style green = new Style();IconStyle iconStylegreen = new IconStyle(); 
			Icon icongreen = new Icon(); icongreen.setHref("http://maps.google.com/mapfiles/ms/icons/green-dot.png");
			iconStylegreen.setScale(1);iconStylegreen.setIcon(icongreen);
			green.setId("green"); green.setIconStyle(iconStylegreen);
			document.addToStyleSelector(green);
			
			Folder folder = new Folder();
			folder.setName("wifi");
			//int count = 0;
			Node n = head;
			while (n != null) { // while for samples
				for (int i = 0; i < n.sC.networks.length; i++) //for for netword in one samples
				{
					if (n.sC.networks[i]!=null){
						// Create <Placemark> and set values.
						Placemark placemark = KmlFactory.createPlacemark();
						placemark.setName(n.sC.networks[i].ssid);
						placemark.setDescription(n.sC.networks[i].toString());
						placemark.setVisibility(true);
						placemark.setOpen(false);
						//timeline
						String str=format.format(n.sC.networks[i].firstsin);
						str=str.replace(' ', 'T')+'Z';
						placemark.createAndSetTimeStamp().setWhen(str);
						//COLOR
						if (n.sC.networks[i].rssi < -95)
							placemark.setStyleUrl("#green");
						else if (n.sC.networks[i].rssi < -85)
							placemark.setStyleUrl("#yellow");
						else
							placemark.setStyleUrl("#red");
						// Create <Point> and set values.
						Point point = KmlFactory.createPoint();
						point.setExtrude(false);
						point.setAltitudeMode(AltitudeMode.CLAMP_TO_GROUND);
						point.getCoordinates().add(new Coordinate(n.sC.networks[i].CurrentLongitude,n.sC.networks[i].CurrentLatitude));
						placemark.setGeometry(point);      // <-- point is registered at placemark ownership.
						folder.addToFeature(placemark);
					}
				}
				n = n.next;
			}
			document.addToFeature(folder);
			kml.setFeature(document);
			kml.marshal(new File(kmlFilePath));
			System.out.println("Done creating XML File");
			return true;
		} catch (Exception e) {
			System.out.println("ERROR linkedScan");
			System.out.println(e);
			return false;
		}
		
	}
	/**
	 * @see data to csv
	 * @param fileName
	 * @return boolean
	 * @throws IOException
	 */
	public boolean toCsv(String fileName) throws IOException {
		try {
			PrintWriter pw = new PrintWriter(new File(fileName));
			StringBuilder sb = new StringBuilder();
			sb.append("Time,ID,Lat,Lon,Alt,#WiFi networks,");
			for (int i = 1; i <= 10; i++) {
				sb.append("SSID"+i+",MAC"+i+",Frequncy"+i+",Signal"+i+"," );
			}
			sb.append("\n" );
			Node n = head;
			while (n!=null) // while for samples
			{
				if(n.sC.networks[0]!=null)// for for network in one samples 
				{
					NetworkData net = n.sC.networks[0];
					sb.append(net.firstsin+"," + net.id + "," + net.CurrentLatitude + "," 
								+ net.CurrentLongitude +","+net.AltitudeMeters + "," + n.sC.size+",");
					for (int i = 0; i < n.sC.size; i++) {
						net = n.sC.networks[i];
						String Frequncy;
						if(net.chanel == 0) 
							Frequncy = "gsm";
						else if (net.chanel > 35)
							Frequncy = "5 GHZ";
						else
							Frequncy = "2.4 GHZ";
						sb.append(net.ssid+"," + net.mac +"," + Frequncy +"," + net.rssi+"," );
					}
					sb.append("\n" );
				}
			
				n = n.next;
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
	
	
	
}
