
public class NetworkLocation {
	String mac;
	double lat;
	double lon;
	double alt;
	double weight;
	public NetworkLocation(String mac, double lat, double lon, double alt){
		this.mac = mac;
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.weight = 0;
	}
	public NetworkLocation(String mac, double lat, double lon, double alt, double weight){
		this.mac = mac;
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.weight = weight;
	}
	public NetworkLocation(NetworkLocation n){
		if (n!=null){
			this.mac = n.mac;
			this.lat = n.lat;
			this.lon = n.lon;
			this.alt = n.alt;
			this.weight = n.weight;
		}
	}
	public String toString()
	{
		return mac + "," + lat + "," + lon + "," + alt;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getAlt() {
		return alt;
	}
	public void setAlt(double alt) {
		this.alt = alt;
	}



}
