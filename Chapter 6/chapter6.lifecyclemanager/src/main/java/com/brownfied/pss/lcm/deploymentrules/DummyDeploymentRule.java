package com.brownfied.pss.lcm.deploymentrules;

import com.brownfied.pss.lcm.deploymentengine.DeploymentEngine;

public class DummyDeploymentRule implements DeploymentRule {
	private static final int max_instance=2;
	
	public boolean execute(){
		if(DeploymentEngine.num_instances >= max_instance) return false;
		return true;
	}
}