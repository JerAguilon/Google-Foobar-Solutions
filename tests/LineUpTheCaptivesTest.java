import org.junit.Test;
import static org.junit.Assert.*;

public class LineUpTheCaptivesTest{
	@Test
	public void smallNumberTest1() {
		assertEquals(LineUpTheCaptives.answer(2,2,3),"2");	
	}

	@Test
	public void smallNumerTest2() {
		assertEquals(LineUpTheCaptives.answer(1,2,6), "24");
	}

	@Test(timeout=1000)
	public void overflowTest() {
		assertEquals(LineUpTheCaptives.answer(2,1,40), "523022617466601111760007224100074291200000000");		
	}
}