/**
 * @author Luis
 * 
 */
package agents;

import jadex.bdi.runtime.PlanFailureException;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.search.SServiceProvider;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Arguments;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Agent
@Service
@Arguments
@ProvidedServices(@ProvidedService(type=WorldService.class))
@Description("FireFighters Central")
public class CommandBDI implements WorldService  {
	@Agent
	protected BDIAgent command;
	private int[] firePos = {0,0};

	@Belief
	private boolean fire = false;

	@Plan(trigger=@Trigger(factchangeds="fire"))
	public void dispatchFirefighters(ChangeEvent event) {
		boolean updatedFireStatus = (boolean) event.getValue();
		if(updatedFireStatus)
			if(fire)
			{
				SServiceProvider.getService(command.getServiceProvider(), FireAlertService.class, RequiredServiceInfo.SCOPE_PLATFORM)
				.addResultListener(new DefaultResultListener<FireAlertService>() {
					public void resultAvailable(FireAlertService dispatch) {
						receivedAlert(dispatch);
					}
				});
			}

	}

	@AgentBody
	public void body() {		
		command.waitForDelay(500).get();
	}
	public void receivedAlert(FireAlertService dispatch){
		System.out.println("Command: Fire alert received! Dispatching fighters to (" + firePos[0] + ", " + firePos[1] +")");
		dispatch.dispatchFighters(firePos, fire);
	}


	@Belief
	public int[] getFirePos() {
		return firePos;
	}

	@Belief
	public boolean getFire() {
		return fire;
	}

	@Belief
	public void setFire(int x, int y, boolean f) {
		this.firePos[0] = x;
		this.firePos[1] = y;
		this.fire = f;
	}

	/*
	public class Mover {
		Timer timer;

		public Mover(int seconds) {
			timer = new Timer();
			moving = true;
			timer.schedule(new MoveTime(), seconds);
		}

		class MoveTime extends TimerTask {
			public void run() {
				moving = false;
				timer.cancel(); //Terminate the timer thread
			}
		}

	}*/

	@Override
	public IFuture<Void> serviceFire(int[] pos , boolean f){
		this.setFire(pos[0], pos[1],f);
		return null;
	}

	/*@Override
	public IFuture<Void> serviceFire(int[] pos, boolean f) {
		// TODO Auto-generated method stub
		return null;
	}*/
}