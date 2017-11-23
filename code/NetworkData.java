import java.util.Date;
public class NetworkData extends Location {

	String mac;      
	String ssid;     
	String authmode; 
	int chanel;    
	int rssi;      
	String type;   


	public NetworkData()
	{
		this.mac="unknown";
		this.ssid="unknown";
		this.authmode="unknown";
		this.chanel=0;
		this.rssi=99;
		this.type="unknown";
	}

	public NetworkData (Location L , String mac, String ssid, String authmode, int chanel, int rssi, String type)
	{
		super(L);
		this.mac=mac;
		this.ssid=ssid;
		this.authmode=authmode;
		this.chanel=chanel;
		this.rssi=rssi;
		this.type=type;
	}

	public String getMac() 
	{
		return mac;
	}

	public void setMac(String mac) 
	{
		this.mac = mac;
	}

	public String getSsid() 
	{
		return ssid;
	}

	public void setSsid(String ssid) 
	{
		this.ssid = ssid;
	}

	public String getAuthmode() 
	{
		return authmode;
	}

	public void setAuthmode(String authmode) 
	{
		this.authmode = authmode;
	}

	public int getChanel() 
	{
		return chanel;
	}

	public void setChanel(int chanel) 
	{
		this.chanel = chanel;
	}

	public int getRssi() 
	{
		return rssi;
	}

	public void setRssi(int rssi) 
	{
		this.rssi = rssi;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public Date getFirstsin() 
	{
		return firstsin;
	}

	public void setFirstsin(Date firstsin) 
	{
		this.firstsin = firstsin;
	}

	public String toString ()
	{
		return 
		"mac: "+mac+"\n"+ 
		"ssid: "+ssid+"\n"+
		"authmode: "+authmode+"\n"+
		"chanel: "+chanel+"\n"+
		"rssi: "+rssi+"\n"+
		"type: "+type+"\n"+
		"firstsin: "+ firstsin;
		
	}


}
