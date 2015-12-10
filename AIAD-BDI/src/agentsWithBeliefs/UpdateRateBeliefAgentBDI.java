package agentsWithBeliefs;

import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Description;
 
@Agent
@Description("An agent with an updating belief.")
public class UpdateRateBeliefAgentBDI {
 
	@Agent
	protected BDIAgent agent;
 
	@Belief(updaterate=1000)
	protected long currentTime = System.currentTimeMillis();
 
	@Plan(trigger=@Trigger(factchangeds="currentTime"))
	protected void printTime(ChangeEvent event) {
		System.out.println((long) event.getValue());
	}
 
}