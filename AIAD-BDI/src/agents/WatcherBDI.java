/**
 * @author Luis
 * 
 */
package agents;

import java.util.ArrayList;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalTargetCondition;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.search.SServiceProvider;
import jadex.commons.future.DefaultResultListener;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
@Agent
@Service
@Description("Watcher")
public class WatcherBDI extends FighterBDI{

	@Agent
	protected BDIAgent watcher;

	public int[] pos ={0,0};

	@Belief(dynamic = true)
	public boolean fire = false;

	@Belief(updaterate=200)
	protected long time = System.currentTimeMillis();

	@Plan(trigger=@Trigger(factchangeds="fire"))
	public void comunicatingFire(ChangeEvent event) {
		boolean updatedFireStatus = (boolean) event.getValue();
		if(updatedFireStatus)
			if(fire){
				System.out.println("Watcher: Fire alert, calling Command. Fire at (" + pos[0] + ", " + pos[1] +")");
				SServiceProvider.getService(watcher.getServiceProvider(), WorldService.class, RequiredServiceInfo.SCOPE_PLATFORM)
				.addResultListener(new DefaultResultListener<WorldService>() {
					public void resultAvailable(WorldService alert) {
						alert.serviceFire(pos,fire);
					}
				});
			}		
	}

	@Plan(trigger=@Trigger(factchangeds="time"))
	protected void printTime()
	{
		if(fire==false){
			mapp.startFire();
			if(!mapp.getFire().isEmpty()){
				setFire(pos[0] = mapp.getFire().get(0)[0], pos[0] = mapp.getFire().get(0)[1], true);
			}
			ArrayList<int[]> res = new ArrayList<int[]>();
			mapp.moveWatch();
			res.addAll(mapp.lookAround(mapp.watch));
			res.addAll(mapp.lookArounder(mapp.watch));
			if(!res.isEmpty()){
				fire=true;
				pos[0]=res.get(0)[0];
				pos[1]=res.get(0)[1];
				System.out.println("posx: " + pos[0] + "\tposy: " + pos[1]);

			}
		}

	}
}