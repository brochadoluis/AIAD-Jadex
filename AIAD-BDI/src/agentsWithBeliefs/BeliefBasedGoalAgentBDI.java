package agentsWithBeliefs;

import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalCreationCondition;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
 
@Agent
@Description("An agent with a belief-based goal.")
public class BeliefBasedGoalAgentBDI {
 
	@Agent
	protected BDIAgent agent;
 
	@Belief
	protected String s;
 
	@AgentBody
	public void body() {
		s = "I BELIEVE";
	}
 
	@Goal
	public class ABeliefBasedGoal {
 
		@GoalCreationCondition(beliefs="s")
		public ABeliefBasedGoal() {
			System.out.println("belief -> goal -> plan.");
		}
 
	}
 
	@Plan(trigger=@Trigger(goals=ABeliefBasedGoal.class))
	protected void justDoIt() {
		System.out.println("Executing plan");
	}
 
}