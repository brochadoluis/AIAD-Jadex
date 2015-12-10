package agentsWithBeliefs;

import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.Description;
 
@Agent
@Description("An agent with beliefs and plans.")
public class BeliefPlanAgentBDI {
 
	@Agent
	protected BDIAgent agent;
 
	private int value;
 
	@Belief
	public int getValue() {
		return value;
	}
 
	@Belief
	public void setValue(int value) {
		this.value = value;
	}
 
	@Belief(dynamic=true)
	protected boolean evenValue = (value % 2 == 0);
 
	@Plan(trigger=@Trigger(factchangeds="value"))
	public void newValuePlan(ChangeEvent event) {
		int v = (int) event.getValue();
		System.out.println("New value: " + v);
		System.out.println("EvenValue: " + evenValue);
	}
 
	@Plan(trigger=@Trigger(factchangeds="evenValue"))
	public void checkParityPlan() {
		System.out.println("Parity changed!");
	}
	
	@AgentBody
	public void body() {
		value = 0;   // does not fire the plan, only through setter
		agent.waitForDelay(1000).get();
		System.out.println("---");
		this.setValue(value + 2);
		agent.waitForDelay(1000).get();
		System.out.println("---");
		this.setValue(value + 2);
		agent.waitForDelay(1000).get();
		System.out.println("---");
		this.setValue(value + 1);
	}
 
}