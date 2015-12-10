package agentsWithServices;

import jadex.bridge.IInternalAccess;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.annotation.ServiceComponent;
 
@Service
public class ChatService implements IChatService {
 
	@ServiceComponent
	protected IInternalAccess agent;
 
	public void message(final String sender, final String text) {
		System.out.println(agent.getComponentIdentifier().getLocalName() + ": received from " + sender + " message " + text);
	}
 
}