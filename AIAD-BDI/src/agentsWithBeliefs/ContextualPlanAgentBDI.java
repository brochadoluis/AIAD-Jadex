package agentsWithBeliefs;

import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanAPI;
import jadex.bdiv3.annotation.PlanAborted;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanContextCondition;
import jadex.bdiv3.annotation.PlanFailed;
import jadex.bdiv3.annotation.PlanPassed;
import jadex.bdiv3.runtime.IPlan;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
 
@Agent
@Description("An agent with a contextual plan.")
public class ContextualPlanAgentBDI {
 
	@Agent
	protected BDIAgent agent;
 
	@Belief(updaterate=1000)
	protected long currentTime = System.currentTimeMillis();
 
	public long getCurrentTime() {
		return currentTime;
	}
 
	@AgentBody
	public void body() {
		agent.adoptPlan(new ContextualPlan());
	}
 
	@Plan
	public class ContextualPlan {
 
		private long startingTime;
 
		@PlanAPI
		protected IPlan plan;
 
		@PlanContextCondition(beliefs="currentTime")
		public boolean checkCondition() {
			return getCurrentTime() - this.startingTime < 3000;
		}
 
		@PlanBody
		public void body() {
			this.startingTime = getCurrentTime();
			System.out.println("Pausing plan at " + getCurrentTime());
			plan.waitFor(10000).get();
			System.out.println("Plan resumed at " + getCurrentTime());
		}
 
		@PlanPassed
		public void passed() {
			System.out.println("Plan finished successfully at " + getCurrentTime());
		}
 
		@PlanAborted
		public void aborted() {
			System.out.println("Plan aborted at " + getCurrentTime());
		}
 
		@PlanFailed
		public void failed(Exception e) {
			System.out.println("Plan failed: " + e);
		}
 
	}
 
}