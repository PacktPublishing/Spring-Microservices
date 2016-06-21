package com.brownfied.pss.lcm.scalingpolicy;

import java.util.Map;

public class TpmScalingPolicy implements ScalingPolicy {
	public boolean execute(String serviceId, Map metrics){
		if(metrics.containsKey("gauge.servo.tpm")){
			Double tpm = (Double) metrics.get("gauge.servo.tpm");
			System.out.println("gauge.servo.tpm " + tpm);
			return (tpm > 10);
		}
		return false;
	}
}