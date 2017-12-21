package files;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import db.scanDB;
import de.micromata.opengis.kml.v_2_2_0.AltitudeMode;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.IconStyle;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.KmlFactory;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import de.micromata.opengis.kml.v_2_2_0.Style;
import wifiScan.Wifi;
import wifiScan.wifiContiner;

public class exportKML {
	// data to kml
	/**
	 * @see data to kml
	 * @param kmlFilePath
	 * @return boolean
	 */
	public static boolean dbToKml(String kmlFilePath , scanDB db) {
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
			for (int j = 0; j < db.getSize(); j++) {	
			// for for samples
				wifiContiner wc = db.getIndex(j);
				for (int i = 0; i < wc.getSize(); i++) //for for netword in one samples
				{
					Wifi w = wc.getWifi(i);
					if (w!=null){
						// Create <Placemark> and set values.
						Placemark placemark = KmlFactory.createPlacemark();
						placemark.setName(w.getSsid());
						placemark.setDescription(w.toString() + wc.getDate().toString());
						placemark.setVisibility(true);
						placemark.setOpen(false);
						//timeline
						String str=format.format(wc.getDate());
						str=str.replace(' ', 'T')+'Z';
						placemark.createAndSetTimeStamp().setWhen(str);
						//COLOR
						if (w.getSignal() < -95)
							placemark.setStyleUrl("#green");
						else if (w.getSignal() < -85)
							placemark.setStyleUrl("#yellow");
						else
							placemark.setStyleUrl("#red");
						// Create <Point> and set values.
						Point point = KmlFactory.createPoint();
						point.setExtrude(false);
						point.setAltitudeMode(AltitudeMode.CLAMP_TO_GROUND);
						point.getCoordinates().add(new Coordinate(wc.getLon(),wc.getLat()));
						placemark.setGeometry(point);      // <-- point is registered at placemark ownership.
						folder.addToFeature(placemark);
					}
				}
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
}
