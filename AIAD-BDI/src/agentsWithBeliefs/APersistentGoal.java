package agentsWithBeliefs;

import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalRecurCondition;
import jadex.bdiv3.runtime.ChangeEvent;
 
@Goal(recur=true)
public class APersistentGoal {
 
	@GoalRecurCondition(beliefs="currentTime")
	public boolean checkRecur(ChangeEvent event) {
		System.out.println("Keeping goal at " + (long) event.getValue());
		return true;
	}
 
}