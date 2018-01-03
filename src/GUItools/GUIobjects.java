
package GUItools;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.Format;

import db.scanDB;
import filters.dateFilter;
import filters.filter;
import filters.idFilter;
import filters.locationFilter;

public class GUIobjects {
	public String FolderName;
	public String csvMergeName;
	public scanDB DB1,DBwork;
	public int checksum; 
	public idFilter Idfilter;
	public locationFilter LocFilter;
	public DateFormat format;
	public dateFilter DateFilter;
	public filterMode filterM;

	public void saveObj(String pName) throws IOException
	{
		try {
			FileOutputStream fileOut =
					new FileOutputStream(pName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(Idfilter);
			out.writeObject(LocFilter);
			out.writeObject(DateFilter);
			out.writeObject(filterM);
			out.writeObject(format);
			out.writeInt(checksum);
			out.close();
			fileOut.close();

		} catch (Exception e) {
			System.err.println(e);
			// TODO: handle exception
		}
	}
	public void openObj(String pName)
	{
		try {
			FileInputStream fileIn = new FileInputStream(pName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Idfilter = (idFilter) in.readObject();
			LocFilter = (locationFilter) in.readObject();
			DateFilter = (dateFilter) in.readObject();
			filterM = (filterMode) in.readObject();
			format = (DateFormat) in.readObject();
			checksum = (int)in.readInt();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
	}

}
