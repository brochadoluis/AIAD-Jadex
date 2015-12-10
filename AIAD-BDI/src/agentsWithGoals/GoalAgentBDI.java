package agentsWithGoals;

import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;

@Agent
@Description("An agent with a goal.")
public class GoalAgentBDI {

	@Agent
	protected BDIAgent agent;

	@AgentBody
	public void body() {
		int result = (int) agent.dispatchTopLevelGoal(new AGoal("important goal")).get();
		System.out.println("Finished with " + result + "!");
	}
	
	@Plan(trigger=@Trigger(goals=AGoal.class))
	protected void basicPlan() {
		System.out.println("Executing basic plan.");
	}
	//permite obter como argumento o goal que despoletou o plano, neste caso "important goal", finished with 1
	@Plan(trigger=@Trigger(goals=AGoal.class))
	protected void goalPlan(AGoal goal) {
		System.out.println("Executing goal plan for " + goal.p);
		goal.r = 1;
	}
	//permite obter como argumento o @GoalParameter, tal como anotado no objetivo,
	//neste caso "important goal", finished with 0
	@Plan(trigger=@Trigger(goals=AGoal.class))
	protected void argumentPlan(String s) {
		System.out.println("Executing argument plan for " + s);
	}
	//permite retornar o resultado a colocar no campo do objetivo anotado com @GoalResult
	@Plan(trigger=@Trigger(goals=AGoal.class))
	protected int returnPlan() {
		System.out.println("Executing return plan");
		return 2;
	}
	// permite obter como argumento o @GoalParameter e retornar o resultado a colocar no @GoalResult.
	@Plan(trigger=@Trigger(goals=AGoal.class))
	protected int signaturePlan(String s) {
		System.out.println("Executing signature plan for " + s);
		return 3;
	}
}