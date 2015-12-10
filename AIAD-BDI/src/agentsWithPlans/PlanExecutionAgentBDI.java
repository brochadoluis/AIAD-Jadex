package agentsWithPlans;

import agentsWithPlans.SleepingPlan;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Plans;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
 
@Agent
@Description("An agent executing plans directly.")
@Plans(@Plan(body=@Body(SleepingPlan.class)))
public class PlanExecutionAgentBDI {
 
	@Agent
	protected BDIAgent agent;
 
	@AgentBody
	public void body() {
		agent.adoptPlan(new SleepingPlan(2));
	}
 
}