package wifiScan;

public class Wifi {
	protected String mac;
	protected String ssid;
	protected int chanel;
	protected int signal;
	
	public Wifi(){
		
	}
	public Wifi(String mac,String ssid,int chanel)
	{
		this.mac = mac;
		this.ssid = ssid;
		this.chanel = chanel;
	}
	public Wifi(String mac,String ssid,int chanel, int signal)
	{
		this.mac = mac;
		this.ssid = ssid;
		this.chanel = chanel;
		this.signal = signal;
	}
	public Wifi(Wifi w)
	{
		this.mac = w.mac;
		this.ssid = w.ssid;
		this.chanel = w.chanel;
		this.signal = w.signal;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public int getChanel() {
		return chanel;
	}
	public void setChanel(int chanel) {
		this.chanel = chanel;
	}
	public int getSignal() {
		return signal;
	}
	public void setSignal(int signal) {
		this.signal = signal;
	}
	public String toString ()
	{
		return 
		"mac: "+mac+"\n"+ 
		"ssid: "+ssid+"\n"+
		"chanel: "+chanel+"\n"+
		"signal: "+signal+"\n";
	}
	
	
}
