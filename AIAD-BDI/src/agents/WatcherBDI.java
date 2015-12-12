/**
 * @author Luis
 * 
 */
package agents;

import gui.Map;

import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
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
public class WatcherBDI {

	@Agent
	protected BDIAgent watcher;

	public int[] pos ={0,0};
	
	@Belief(dynamic = true)
	public boolean fire = false;
	
	@Plan(trigger=@Trigger(factchangeds="fire"))
	public void newValuePlan(ChangeEvent event) {
		boolean updatedFireStatus = (boolean) event.getValue();
		if(updatedFireStatus)
		System.out.println("Watcher: Fire alert, calling Command. Fire at (" + pos[0] + ", " + pos[1] +")");

		SServiceProvider.getService(watcher.getServiceProvider(), WorldService.class, RequiredServiceInfo.SCOPE_PLATFORM)
		.addResultListener(new DefaultResultListener<WorldService>() {
			public void resultAvailable(WorldService alert) {
				alert.serviceFire(pos,fire);
			}
		});
		
	}

	@AgentBody
	public void body()
	{
		fire = true;
		
	}
}


