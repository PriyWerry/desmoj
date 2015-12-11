package test.implementation;

import desmoj.core.dist.*;
import desmoj.core.simulator.*;
import desmoj.core.statistic.*;
import java.util.concurrent.TimeUnit;

/**
 *  A simple testmodel to provide a reference for the modultest implementations
 * 	
 * @author Sascha Winde
 * 
 * @param owner model
 * @param name java.lang.String
 * @param showInTrace
 */
public class TestModel extends Model {


	   // define model components here
		TestSimProcess process;
	   
	   public TestModel() {
	      super(null, "<Test Model>", true, true);
	   }

	   /** initialise static components */
	   public void init() {
		   this.process = new TestSimProcess(this, "First Test SimProcess", false);
	   }

	   /** activate dynamic components */
	   public void doInitialSchedules() {
		   process.activatePreempt();
	   }

	   /** returns a description of this model to be used in the report */
	   public String description() {
	      return "<Test description>";
	   }

	   // define any additional methods if necessary,
	   // e.g. access methods to model components

	   /** runs the model */
	   public static void main(String[] args) {

	      // create model and experiment
	      TestModel model = new TestModel();
	      Experiment exp = new Experiment("Test Experiment");
	      // and connect them
	      model.connectToExperiment(exp);

	      // set experiment parameters
	      exp.setShowProgressBar(true);
	      TimeInstant stopTime = new TimeInstant(1440, TimeUnit.MINUTES);
	      exp.tracePeriod(new TimeInstant(0), stopTime);
	      exp.stop(stopTime);

	      // start experiment
	      exp.start();

	      // generate report and shut everything off
	      exp.report();
	      exp.finish();
	   }

	} 


