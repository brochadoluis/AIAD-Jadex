/**
 * 
 */
package agents;

import gui.Map;
import jadex.bdiv3.BDIAgent;
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

	
	@AgentCreated
	public void init(){
		mapp = new Map();
		
	}

}
