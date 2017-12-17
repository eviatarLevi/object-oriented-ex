import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class networkLocationDB {
	ArrayList<NetworkLocation> list  = new ArrayList<NetworkLocation>();
	public networkLocationDB(linkedScan DB){
		linkedScan.Node n = DB.head;
		while (n != null) {
			for (int i = 0; i < n.sC.size; i++) {
				NetworkData nD = n.sC.networks[i];
				list.add(new NetworkLocation(nD.mac,nD.getCurrentLatitude(),
						nD.getCurrentLongitude(),nD.getAltitudeMeters(),weightC(nD.getRssi())));
			}
			n = n.next;
		}
		sort();
		list = algoLocation1();
		System.out.println("Done algo 1");
	}
	public static double weightC(int x)
	{
		return (1/(double)(x*x));
	}
	public NetworkLocation findMac(String mac){
		for (int i = 0; i < list.size(); i++) {
			if(mac.equals(list.get(i).mac))
				return list.get(i);
		}
		return null;
		
	}
	public void sort()
	{
		Collections.sort(list, new Comparator<NetworkLocation>(){
			public int compare(NetworkLocation s1, NetworkLocation s2) {
				return s1.getMac().compareToIgnoreCase(s2.getMac());
			}
		});
	}
	public ArrayList<NetworkLocation> algoLocation1()
	{
		ArrayList<NetworkLocation> newList  = new ArrayList<NetworkLocation>();
		double sumLat=0, sumLon=0,sumAlt=0,sumWeight=0;
		for (int i = 0; i < list.size(); i++) {
			list.get(i).lat *= list.get(i).weight;
			list.get(i).lon *= list.get(i).weight;
			list.get(i).alt *= list.get(i).weight;
			sumLat += list.get(i).lat;
			sumLon += list.get(i).lon;
			sumAlt += list.get(i).alt;
			sumWeight += list.get(i).weight;
			try {
				if(i<list.size()-1){
					if(!list.get(i).mac.equals(list.get(i+1).mac)){
						newList.add(new NetworkLocation(list.get(i).mac,sumLat/sumWeight,
								sumLon/sumWeight,sumAlt/sumWeight));
						sumLat =0; sumLon=0; sumAlt=0; sumWeight=0;
					}
				}
				else
					newList.add(new NetworkLocation(list.get(i).mac,sumLat/sumWeight,
							sumLon/sumWeight,sumAlt/sumWeight));
			} catch (Exception e) {
				System.err.println(e);
			}
			
		}
		return newList;

	}
	public boolean toCsv(String fileName) throws IOException {
		try {
			PrintWriter pw = new PrintWriter(new File(fileName));
			StringBuilder sb = new StringBuilder();
			sb.append("mac,Lat,Lon,Alt");
			sb.append(System.getProperty("line.separator"));
			for (int i = 0; i < list.size(); i++) {
				sb.append(list.get(i));
				sb.append(System.getProperty("line.separator"));
			}
			System.out.println("csv create complete");
			pw.append(sb.toString());
			pw.close();
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}
}
