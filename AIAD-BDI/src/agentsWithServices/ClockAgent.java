package agentsWithServices;

import jadex.bridge.service.types.clock.IClockService;
import jadex.micro.MicroAgent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;
import jadex.bridge.service.RequiredServiceInfo;
 
import java.util.Date;
 
@Description("This agent declares a required clock service.")
@Agent
@RequiredServices(@RequiredService(name="clockservice", type=IClockService.class, binding=@Binding(scope=RequiredServiceInfo.SCOPE_PLATFORM)))
public class ClockAgent {
 
	@Agent
	MicroAgent agent;
 
	@AgentBody
	public void executeBody() {
		IClockService cs = (IClockService) agent.getServiceContainer().getRequiredService("clockservice").get();
		System.out.println("Time for a chat, buddy: " + new Date(cs.getTime()));
	}
 
}