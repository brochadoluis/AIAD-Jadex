package agentsWithBeliefs;

import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanPassed;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.impl.PlanFailureException;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
 
@Agent
@Description("An agent with a persistent goal.")
public class PersistentGoalAgentBDI {
 
	@Agent
	protected BDIAgent agent;
 
	@Belief
	protected long startingTime = System.currentTimeMillis();
 
	public long getStartingTime() {
		return startingTime;
	}
 
	@Belief(updaterate=1000)
	protected long currentTime = System.currentTimeMillis();
 
	public long getCurrentTime() {
		return currentTime;
	}
 
	@AgentBody
	public void body() {
		agent.dispatchTopLevelGoal(new APersistentGoal()).get();
		System.out.println("Finished");
	}
 
	@Plan(trigger=@Trigger(goals=APersistentGoal.class))
	public class FailingPlan {
 
		@PlanBody
		protected void failingPlan() {
			System.out.println("Attempt at " + getCurrentTime());
			if(getCurrentTime() - getStartingTime() < 3000) {
				throw new PlanFailureException();
			}
		}
 
		@PlanPassed
		public void passed() {
			System.out.println("Plan finished successfully at " + getCurrentTime());
		}
 
	}
 
}