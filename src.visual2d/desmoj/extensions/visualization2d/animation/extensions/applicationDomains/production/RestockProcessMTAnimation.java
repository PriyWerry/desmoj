package desmoj.extensions.visualization2d.animation.extensions.applicationDomains.production;

import desmoj.extensions.applicationDomains.production.RestockProcessMT;
import desmoj.extensions.visualization2d.animation.core.simulator.ModelAnimation;
import desmoj.extensions.visualization2d.animation.internalTools.SimulationException;

/**
 * Animation of RestockProcessMT. 
 * After instantiation you must execute methods from superclass:<br>
 * createAnimation(...) 			to animate producer for restocking<br>
 * getRestockProcess().activate()	to activate RestockProcess
 * 
 * @version DESMO-J, Ver. 2.5.1d copyright (c) 2015
 * @author christian.mueller@th-wildau.de
 *         For information about subproject: desmoj.extensions.visualization2d
 *         please have a look at: 
 *         http://www.th-wildau.de/cmueller/Desmo-J/Visualization2d/ 
 * 
 *         Licensed under the Apache License, Version 2.0 (the "License"); you
 *         may not use this file except in compliance with the License. You may
 *         obtain a copy of the License at
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *         implied. See the License for the specific language governing
 *         permissions and limitations under the License.
 *
 */
public class RestockProcessMTAnimation extends RestockProcessAnimation{

	public RestockProcessMTAnimation(RestockProcessMT restock) {
		this.restock			= restock;
		this.check(restock);
	}
	
	private void check(RestockProcessMT restock){
		if (! (restock.getModel() instanceof ModelAnimation)) 
			throw new SimulationException("For animation, RestockProcessMT must be initialized with a model of type ModelAnimation");
	}

}