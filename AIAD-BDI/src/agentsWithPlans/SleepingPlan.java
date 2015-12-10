package agentsWithPlans;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;

@Plan
public class SleepingPlan {
 
	private int secs;
 
	public SleepingPlan(int seconds) {
		secs = seconds;
	}
 
	@PlanBody
	public void sleepingPlanBody() {
		System.out.println("Let's to this!");
 
		// Apart from this example, DON'T use Thread.sleep.
		// There are better ways to pause an agent without blocking the whole app.
		try {
			Thread.sleep(secs * 1000);
			System.out.println("I fell asleep for " + secs + " seconds!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
 
}