package test.core.simulator;


import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

/**
 * This class tests the TimeInstant operations.
 * 
 * @author Sascha Winde, Clara Bluemm
 *
 */
public class TimeInstantTest extends TestCase{

	TimeInstant testTime1;
	TimeInstant testTime2;
	TimeInstant testTimeLong;
	
	
	@Before
	public void setUp() throws Exception {
	       Experiment experiment = new Experiment("Test Experiment", java.util.concurrent.TimeUnit.SECONDS, java.util.concurrent.TimeUnit.HOURS, null);

		
		this.testTime1 = new TimeInstant(10);
		this.testTime2 = new TimeInstant(5);
		this.testTimeLong = new TimeInstant(100.0);
	}
	
	/**
	 * This test checks the given TimeInstant as double vale.
	 */
	public void testTimeAsDouble()
	{
		assertTrue(10 == testTime1.getTimeAsDouble());
	}
	

	/**
	 * This test checks the given TimeInstant as long vale.
	 */
	public void testAsLong()
	{
		assertTrue(100.0 == testTimeLong.getTimeRounded());
	}
	
	/**
	 * TimeInstants can be sequenced. This test checks, if a TimeInstant
	 * is before or after another TimeInstant. 
	 */
	public void testTimeSequence()
	{
		TimeInstant.isBefore(testTime2, testTime1);
		assertTrue(TimeInstant.isBefore(testTime2, testTime1));
		assertTrue(TimeInstant.isBeforeOrEqual(testTime2, testTime1));
		assertFalse(TimeInstant.isBefore(testTime1, testTime2));
		assertTrue(TimeInstant.isAfter(testTime1, testTime2));
		assertTrue(TimeInstant.isAfterOrEqual(testTime1, testTime2));
		assertFalse(TimeInstant.isAfter(testTime2, testTime1));
	}
	
	/**
	 * This test checks if two TimeInstants are equal.
	 */
	public void testTimeEqual()
	{
		assertFalse(TimeInstant.isEqual(testTime2, testTime1));
	}
	
	/**
	 * This test checks if two TimeInstants are equal, using the compare Interface.
	 */
	public void testCompareTo()
	{
		assertEquals(1, testTime1.compareTo(testTime2));
	}
	

	@After
	public void tearDown() throws Exception {
	}

}
