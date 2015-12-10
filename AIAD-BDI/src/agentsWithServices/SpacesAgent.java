package agentsWithServices;

import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;
import jadex.commons.future.IntermediateDefaultResultListener;
import jadex.micro.MicroAgent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;
 
import java.util.Collection;
 
@Description("This agent provides a basic chat service.")
@Agent
@ProvidedServices(@ProvidedService(type=IChatService.class, implementation=@Implementation(ChatService.class)))
@RequiredServices(@RequiredService(name="chatservices", type=IChatService.class, multiple=true, binding=@Binding(dynamic=true, scope=Binding.SCOPE_PLATFORM)))
public class SpacesAgent {
 
	@Agent
	protected MicroAgent agent;

 
	@AgentBody
	public void executeBody() {
		IFuture<Collection<IChatService>> chatservices = agent.getServiceContainer().getRequiredServices("chatservices");
		chatservices.addResultListener(new IntermediateDefaultResultListener<IChatService>() {
			public void intermediateResultAvailable(IChatService cs) {
				cs.message(agent.getComponentIdentifier().getLocalName(), agent.getAgentName());
			}
		});
	}
 
}