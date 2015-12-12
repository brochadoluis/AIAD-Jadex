import gui.Menu;

import jadex.base.Starter;
import jadex.bridge.IExternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.search.SServiceProvider;
import jadex.bridge.service.types.cms.IComponentManagementService;
import jadex.commons.future.IFuture;
import jadex.commons.future.ThreadSuspendable;


public class main {

	public static void main(String[] args){
		IFuture<IExternalAccess>	platfut	= Starter.createPlatform(args);
		final ThreadSuspendable	sus	= new ThreadSuspendable();
		final IExternalAccess	platform	= platfut.get(sus);
		System.out.println("Started platform: "+ platform.getComponentIdentifier());
		
		IComponentManagementService cms = SServiceProvider.getService(platform.getServiceProvider(),
				IComponentManagementService.class, RequiredServiceInfo.SCOPE_PLATFORM).get(sus);
		
		Menu menu = new Menu(cms, sus);
		
		
	}
}