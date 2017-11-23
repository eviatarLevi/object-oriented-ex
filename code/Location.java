import java.util.Date;

public class Location extends UserData{
	
	double CurrentLatitude;  
	double CurrentLongitude; 
	double AltitudeMeters;   
	double AccuracyMeters;   
	Date firstsin; 
	public Location ()
	{
		this.CurrentLatitude=0;
		this.CurrentLongitude=0;
		this.AltitudeMeters=0;
		this.AccuracyMeters=0;
	}
	
	public Location (UserData user ,double latitude, double longitude, 
						double altitude, double accutacy, Date firtsin)
	{
		super(user);
		this.CurrentLatitude=latitude;
		this.CurrentLongitude=longitude;
		this.AltitudeMeters=altitude;
		this.AccuracyMeters=accutacy;
		this.firstsin = firtsin;
	}
	
	public Location (Location L)
	{
		super(L.username,L.devicename,L.id);
		this.CurrentLatitude=L.CurrentLatitude;
		this.CurrentLongitude=L.CurrentLongitude;
		this.AltitudeMeters=L.AltitudeMeters;
		this.AccuracyMeters=L.AccuracyMeters;
		this.firstsin = L.firstsin;
	}
	
	public double getCurrentLatitude() 
	{
		return CurrentLatitude;
	}

	public void setCurrentLatitude(double currentLatitude) 
	{
		CurrentLatitude = currentLatitude;
	}

	public double getCurrentLongitude() 
	{
		return CurrentLongitude;
	}

	public void setCurrentLongitude(double currentLongitude) 
	{
		CurrentLongitude = currentLongitude;
	}

	public double getAltitudeMeters() 
	{
		return AltitudeMeters;
	}

	public void setAltitudeMeters(double altitudeMeters) 
	{
		AltitudeMeters = altitudeMeters;
	}

	public double getAccuracyMeters() 
	{
		return AccuracyMeters;
	}

	public void setAccuracyMeters(double accuracyMeters) 
	{
		AccuracyMeters = accuracyMeters;
	}
	
	public String toString()
	{
		return "current latitude: "+CurrentLatitude+"\n"+
			   "current longitude: "+CurrentLongitude+"\n"+
			   "altitude meters: " +AltitudeMeters+"\n"+
			   "accuracy meters" +AccuracyMeters; 
	}
	
	
	
	
	
}
