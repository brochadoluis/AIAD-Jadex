package agentsWithBeliefs;

import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.Goal.ExcludeMode;
import jadex.bdiv3.annotation.GoalMaintainCondition;
import jadex.bdiv3.annotation.GoalTargetCondition;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
 
@Agent
@Description("An agent with a maintain goal.")
public class MaintainGoalAgentBDI {
 
	@Agent
	protected BDIAgent agent;
 
	@Belief
	protected int n = 0;
 
	@Belief(updaterate=1000)
	protected long currentTime = System.currentTimeMillis();
 
	@Plan(trigger=@Trigger(factchangeds="currentTime"))
	protected void increment(ChangeEvent event) {
		System.out.println("---");
		System.out.println("Incrementing..." + (++n));
	}
 
	@AgentBody
	public void body() {
		agent.dispatchTopLevelGoal(new MaintainGoal());
	}
 
	@Goal(excludemode=ExcludeMode.Never)
	public class MaintainGoal {
 
		@GoalMaintainCondition(beliefs="n")
		protected boolean maintain() {
			return n<=4;
		}
 
		@GoalTargetCondition(beliefs="n")
		protected boolean target() {
			return n<3;
		}
 
	}
 
	@Plan(trigger=@Trigger(goals=MaintainGoal.class))
	protected void decrement() {
		System.out.println("Decrementing..." + (--n));
	}
 
}