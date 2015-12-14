/**
 * @author Luis
 * 
 */
package agents;

import java.util.ArrayList;

import edu.uci.ics.jung.graph.Forest;
import gui.Map;

import jadex.bdi.runtime.PlanFailureException;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.service.annotation.Service;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Arguments;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;

@Agent
@Service
@Arguments
@ProvidedServices(@ProvidedService(type=FireAlertService.class))
@Description("FireFighter")
public class FighterBDI extends MapBDI implements FireAlertService{

	@Agent
	protected BDIAgent ffighter;

	private int[] firePos = {0,0};
	private int[] firep;
	//public int[][] map = Map.getMap();

	@Belief
	private boolean fire;
	
	@Belief(updaterate=200)
	protected long time = System.currentTimeMillis();

	@Plan(trigger=@Trigger(factchangeds="fire"))
	public void newValuePlan(ChangeEvent event) {
		boolean updatedFireStatus = (boolean) event.getValue();
		firep = firePos;
		if(updatedFireStatus)
			System.out.println("Fighter: Dispatch order received, going to (" + firePos[0] + "," + firePos[1]+")");
		;
	}

	@Plan(trigger=@Trigger(factchangeds="time"))
	protected void printTime() throws InterruptedException
	{
		boolean found = false;
		if(!mapp.lookArounder(mapp.fighter).isEmpty() && !mapp.lookAround(mapp.fighter).isEmpty() && fire == true)
			found = true;
			mapp.putOutFire();
		if(fire == true && found == false){
			//Thread.sleep(2000);
			int pos[] = firep;
			mapp.moveFighter(pos);
			
		}
		if(mapp.getFire().isEmpty()){
			int[] ini = {1,1};
			mapp.moveFighter(ini);
			setFire(0,0,false);
			mapp.printMap();
			System.out.println("Fighter: Fire extinguished. Returning to base");
		}
	}

	@AgentBody
	public void body() throws InterruptedException{
		ffighter.waitForDelay(1000).get();
		//int[] aproachPos =	getShortestPathtoFire(firePos[0],firePos[1],mapp.map.length);
		Thread.sleep(2000);
		System.out.println("Arrived to map after 2 secons");
		System.out.println("Putting out the fire");

	}

	@Override
	public IFuture<Void> dispatchFighters(int[] pos, boolean f) {
		this.setFire(pos[0], pos[1], f);
		return null;
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
	
}
