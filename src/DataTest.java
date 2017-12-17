//import static org.junit.Assert.*;
//
//import java.util.Date;
//
//import org.junit.Test;
//
//public class DataTest {
//	@Test
//	public void testNetworkData() {
//		UserData userTest = new UserData("123","abc","ABC");
//		Location locTest = new Location(userTest,3.444,3.233,2,0,new Date());
//		NetworkData netTest = new NetworkData(locTest,"aa:bb:00:dd:33","home","wpa", 2,-5,"wifi");
//		assertEquals(netTest.getMac(), "aa:bb:00:dd:33");
//		
//	}
//
//	@Test
//	public void testGetRssi() {
//		UserData userTest = new UserData("123","abc","ABC");
//		Location locTest = new Location(userTest,3.444,3.233,2,0,new Date());
//		NetworkData netTest = new NetworkData(locTest,"aa:bb:00:dd:33","home","wpa", 2,-5,"wifi");
//		assertEquals(netTest.getRssi(), -5);
//	}
//
//	@Test
//	public void testGetId() {
//		UserData userTest = new UserData("123","abc","ABC");
//		assertEquals(userTest.getId(), "ABC");
//	}
//
//}
