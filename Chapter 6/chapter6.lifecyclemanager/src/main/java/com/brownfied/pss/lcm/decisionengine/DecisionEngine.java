package com.brownfied.pss.lcm.decisionengine;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brownfied.pss.lcm.deploymentengine.DeploymentEngine;
import com.brownfied.pss.lcm.deploymentrules.DeploymentRules;
import com.brownfied.pss.lcm.scalingpolicy.ScalingPolicies;

@Component
public class DecisionEngine{
	@Autowired
	ScalingPolicies scalingPolicies;
	
	@Autowired
	DeploymentEngine deploymentEngine;
	
	@Autowired
	DeploymentRules deploymentRules;
	
	public boolean execute(String serviceId, Map metrics){
		if(scalingPolicies.getPolicy(serviceId).execute(serviceId, metrics)){		
			return deploymentEngine.scaleUp(deploymentRules.getDeploymentRules(serviceId), serviceId);	
		}
		return false;
	}
}