package agentsWithServices;

import jadex.bridge.service.search.SServiceProvider;
import jadex.bridge.service.types.clock.IClockService;
import jadex.micro.MicroAgent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
import jadex.bridge.service.RequiredServiceInfo;
 
import java.util.Date;
 
@Description("This agent declares a required clock service.")
@Agent
public class Clock2Agent {
 
	@Agent
	MicroAgent agent;
 
	@AgentBody
	public void executeBody() {
		IClockService cs = SServiceProvider.getService(agent.getServiceProvider(), IClockService.class, RequiredServiceInfo.SCOPE_PLATFORM).get();
		System.out.println("Time for a chat, buddy: " + new Date(cs.getTime()));
	}
 
}