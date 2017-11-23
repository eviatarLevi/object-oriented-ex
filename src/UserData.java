
public class UserData {

	String username;
	String devicename;
	String id;

	public UserData ()
	{
		this.username="unknown";
		this.devicename="unknown";
		this.id="0";
	}

	public UserData (String username, String devicename, String id)
	{
		this.username=username;
		this.devicename=devicename;
		this.id=id;
	}
	
	public UserData (UserData data)
	{
		this.username=data.username;
		this.devicename=data.devicename;
		this.id=data.id;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getDevicename() 
	{
		return devicename;
	}

	public void setDevicename(String devicename) 
	{
		this.devicename = devicename;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}


	public String toString() 
	{
		return "name: "+username+"\n"+
				"devicename: "+devicename+"\n"+
				"id: "+id+"\n"				;

	}





}
