/**
 * 
 */
package agents;

import gui.Map;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bridge.service.annotation.Service;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.Arguments;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;

@Agent
@Service
@Arguments
@Description("Map")
public class MapBDI {
	
	@Agent
	protected BDIAgent mapBDI;
	public Map mapp;
	
	@Belief(updaterate=5000)
	protected long time = System.currentTimeMillis();
	

	@Plan(trigger=@Trigger(factchangeds="time"))
	protected void printTime() throws InterruptedException
	{
		//if(mapp.getFire().isEmpty()){
			//mapp.startFire();
			//mapp.printMap();
		//}

	}
	
	@AgentCreated
	public void init(){
		mapp = new Map();
		//mapp.startFire();
		
	}

}
