package test.functional;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import org.compiere.model.BaseUtil;
import org.junit.Test;

/**
 * Tests to make sure rounding functionality works correctly. 
 * 
 * @author Daniel Tamm
 */
public class BaseUtilTest {

	protected void setUp()
	{
		System.out.println("Testing rounding functionality");
	}
	
	@Test
	public void testRoundPrecision() {

		BigDecimal amount = new BigDecimal("234894.5678");
		BigDecimal roundC;
		for (int i=-5; i<4; i++) {
			roundC = BaseUtil.roundToPrecision(amount, i);
			System.out.println("Precision " + i + " : " + amount + " -> " + roundC);
			switch(i) {
				case -4: assertEquals(230000.0, roundC.doubleValue()); break;
				case -3: assertEquals(235000.0, roundC.doubleValue()); break;
				case -2: assertEquals(234900.0, roundC.doubleValue()); break;
				case -1: assertEquals(234890.0, roundC.doubleValue()); break;
				case 0: assertEquals(234895.0, roundC.doubleValue()); break;
				case 1: assertEquals(234894.6, roundC.doubleValue()); break;
				case 2: assertEquals(234894.57, roundC.doubleValue()); break;
				case 3: assertEquals(234894.568, roundC.doubleValue()); break;
				case 4: assertEquals(234894.5678, roundC.doubleValue()); break;
			}
		}
		
	}
	
	@Test
	public void testRound1() {
		
		BigDecimal amount = new BigDecimal("245.67");
		
		BigDecimal noRound = BaseUtil.round(amount, "N");
		System.out.println("No round " + amount + " -> " + noRound);
		assertEquals(245.67, noRound.doubleValue(), 0.0);
		
		BigDecimal roundWholeNumber = BaseUtil.round(amount, "0");
		System.out.println("Whole number " + roundWholeNumber + " -> " + roundWholeNumber);
		assertEquals(246.0, roundWholeNumber.doubleValue(), 0.0);
		
		BigDecimal round10 = BaseUtil.round(amount, "T");
		System.out.println("Tens " + amount + " -> " + round10);
		assertEquals(250.0, round10.doubleValue(), 0.0);

		BigDecimal roundQ = BaseUtil.round(amount, "Q");
		System.out.println("Quarter " + amount + " -> " + roundQ);
		assertEquals(245.75, roundQ.doubleValue(), 0.0);
		
		BigDecimal roundD = BaseUtil.round(amount, "D");
		System.out.println("Dime " + amount + " -> " + roundD);
		assertEquals(245.7, roundD.doubleValue(), 0.0);
		
		BigDecimal roundN = BaseUtil.round(amount, "5");
		System.out.println("Nickel " + amount + " -> " + roundN);
		assertEquals(245.65, roundN.doubleValue(), 0.0);
		
		BigDecimal round9 = BaseUtil.round(amount, "9");
		System.out.println("9/5 " + amount + " -> " + round9);
		assertEquals(249.0, round9.doubleValue(), 0.0);
		
	}
	
	@Test
	public void testRound2() {

		BigDecimal amount = new BigDecimal("1233.56");
		
		BigDecimal round9 = BaseUtil.round(amount, "9");
		System.out.println("9/5 " + amount + " -> " + round9);
		assertEquals(1235.0, round9.doubleValue(), 0.0);
		
	}

}
