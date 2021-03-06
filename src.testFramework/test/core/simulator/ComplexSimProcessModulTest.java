package test.core.simulator;


import java.util.Enumeration;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import desmoj.core.simulator.Experiment;

import test.implementation.TestComplexSimProcess;
import test.implementation.TestModel;
import test.implementation.TestSimProcess;

/**
 * This class tests the functionality of the class  ComplexSimProcess.
 * 
 * @author Sascha Winde, Clara Bluemm
 *
 * 
 */
public abstract class ComplexSimProcessModulTest extends TestCase {

	
	
	public TestModel model;
	public TestSimProcess simProcess1;
	public TestSimProcess simProcess2;
	public TestComplexSimProcess complex;
	
	
	@Before
	public void setUp() throws Exception {
		
		this.model = new TestModel();
		Experiment experiment = new Experiment("Test Experiment", java.util.concurrent.TimeUnit.SECONDS, java.util.concurrent.TimeUnit.HOURS, null);
		model.connectToExperiment(experiment);
		
		this.simProcess1 = new TestSimProcess(model, "First Test SimProcess", false);
		this.simProcess2 = new TestSimProcess(model, "Second Test SimProcess", false);
		this.complex = new TestComplexSimProcess(model, "First Test ComplexSimProcess", false);
	}
	
	
	/**
	 * This Test checks the given Name of the ComplexSimProcess
	 */
	public void testName()
	{
		assertEquals("First Test ComplexSimProcess#1", complex.getName());
	}
	
	
	/**
	 * This Test checks the functionality to add, remove or check for components,
	 * given to a ComplexSimProcess
	 */
	public void testComponents()
	{
		assertNotNull(complex.getComponents());
		assertFalse(complex.hasComponents());
		complex.addComponent(simProcess1);
		complex.addComponent(simProcess2);
		assertTrue(complex.hasComponents());
		assertTrue(complex.contains(simProcess1));
		assertTrue(complex.contains(simProcess2));
		Enumeration e = complex.getComponents();
		assertNotNull(e);
		assertFalse(e == complex.getComponents());
		complex.removeComponent(simProcess1);
		assertFalse(complex.contains(simProcess1));
		assertTrue(complex.hasComponents());
		assertTrue(complex.contains(simProcess2));
		complex.removeAllComponents();
		assertFalse(complex.contains(simProcess2));
		assertFalse(complex.hasComponents());
	}

	@After
	public void tearDown() throws Exception {
	}

}
