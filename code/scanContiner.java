import java.util.Date;

public class scanContiner {
		 NetworkData[] networks;
		 int size;
		
		public scanContiner()
		{
			networks = new NetworkData[10];
			size = 0;
		}
		public void add(String username, String devicename,String  id,
									double latitude, double longitude,
										double altitude, double accutacy,
											String mac,String ssid, String authmode,
												String type , Date firstsin , int chanel, int rssi )
		{
			UserData user = new UserData(username,devicename,id);
			Location l = new Location(user,latitude,longitude,altitude,accutacy,firstsin);
			if(size<10){
				networks[size++] = new NetworkData(l,mac,ssid,authmode,chanel,rssi,type);
			}
			else
			{
				int rssiMin = 0;
				for (int i = 1; i < networks.length; i++) {
					if (networks[i].rssi > networks[rssiMin].rssi)
						rssiMin = i;
				}
				if (networks[rssiMin].rssi > rssi)
					networks[rssiMin] = new NetworkData(l,mac,ssid,authmode,chanel,rssi,type);
			}
		}
}
