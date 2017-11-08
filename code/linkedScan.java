import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
//linkedlist for the samples
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
	public linkedScan locaFilter (Double X,Double Y,Double dist)
	{
		linkedScan DbN = new linkedScan();
		Node n = head;
		while(n!=null)
		{
			if (n.sC.networks[0]!=null){
				Double Xn = n.sC.networks[0].CurrentLatitude;
				Double Yn = n.sC.networks[0].CurrentLongitude; 
				if (X+dist<=Xn && X-dist>=Xn && Y+dist<=Yn && Y-dist>=Yn)
				{
					DbN.add(n.sC);
				}
			}
			n=n.next;
		}
		return DbN;
	}
	// data to kml
	public boolean toKml(String xmlFilePath) {

		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			Element root = document.createElement("kml");
			Attr attr = document.createAttribute("xmlns");
			attr.setValue("http://www.opengis.net/kml/2.2");
			document.appendChild(root);
			root.setAttributeNode(attr);
			Element Document = document.createElement("Document");
			root.appendChild(Document);
			Element dname = document.createElement("name");
			dname.appendChild(document.createTextNode("abc"));
			Document.appendChild(dname);
			Element Folder = document.createElement("Folder");
			Document.appendChild(Folder);
			Element fname = document.createElement("name");
			fname.appendChild(document.createTextNode("cde"));
			Folder.appendChild(fname);
			int count = 0;
			Node n = head;
			while (n != null) { // while for samples
				for (int i = 0; i < n.sC.networks.length; i++) //for for netword in one samples
				{
					if (n.sC.networks[i]!=null){
						Element placemark = document.createElement("Placemark");
						Attr at = document.createAttribute("id");
						at.setValue(count++ + "A"+i);
						placemark.setAttributeNode(at);
						Folder.appendChild(placemark);
						Element name = document.createElement("name");
						name.appendChild(document.createTextNode(n.sC.networks[i].ssid));
						placemark.appendChild(name);
						Element description = document.createElement("description");
						String desc = n.sC.networks[i].toString();
						description.appendChild(document.createTextNode(desc)); 
						placemark.appendChild(description);
						Element point = document.createElement("Point");
						Element coordinates = document.createElement("coordinates");
						coordinates.appendChild(document.createTextNode(n.sC.networks[i].CurrentLongitude+","+n.sC.networks[i].CurrentLatitude));
						point.appendChild(coordinates);
						placemark.appendChild(point);
					}
				}
				n = n.next;
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));
			transformer.transform(domSource, streamResult);
			System.out.println("Done creating XML File");
			return true;
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			return false;
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			return false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
	}
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
						else if (net.chanel < 35)
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
