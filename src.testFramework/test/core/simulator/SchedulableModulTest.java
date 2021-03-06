package test.core.simulator;


import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import desmoj.core.simulator.Experiment;

import test.implementation.TestModel;
import test.implementation.TestSchedulable;

/**
 * This class tests if a schedulable object is scheduled or not.
 * 
 * @author Sascha Winde, Clara Bluemm
 *
 */
public abstract class SchedulableModulTest extends TestCase {

	
	TestModel model;
	TestSchedulable schedul;
	
	
	
	@Before
	public void setUp() throws Exception {
		this.model = new TestModel();
		Experiment experiment = new Experiment("Test Experiment", java.util.concurrent.TimeUnit.SECONDS, java.util.concurrent.TimeUnit.HOURS, null);
		model.connectToExperiment(experiment);
		this.schedul = new TestSchedulable(model, "First Test Schedulable", false);
	}

	public void testIsScheduled()
	{
		assertFalse(schedul.isScheduled());
		
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
