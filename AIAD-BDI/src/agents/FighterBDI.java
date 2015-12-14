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
public class FighterBDI implements FireAlertService{

	@Agent
	protected BDIAgent fighter;

	private int[] firePos = {0,0};
	Map mapp = new Map();
	//public int[][] map = Map.getMap();

	@Belief(updaterate = 500)
	private boolean fire;

	@Plan(trigger=@Trigger(factchangeds="fire"))
	public void newValuePlan(ChangeEvent event) {
		boolean updatedFireStatus = (boolean) event.getValue();
		if(updatedFireStatus)
			System.out.println("Fighter: Dispatch order received, going to (" + firePos[0] + "," + firePos[1]+")");
		mapp.moveFighter(firePos);
		mapp.putOutFire();
	}


	@AgentBody
	public void body() throws InterruptedException{
		fighter.waitForDelay(1000).get();
		System.out.println("Map size: " + mapp.map.length);
		//int[] aproachPos =	getShortestPathtoFire(firePos[0],firePos[1],mapp.map.length);
		Thread.sleep(2000);
		System.out.println("Arrived to map after 2 secons");
		System.out.println("Putting out the fire");

	}


	private int[] getShortestPathtoFire(int i, int j, int length) {
		int[] path = {-1,-1};
		if(i == 0 && j >= 0){ // horizontal axis
			for(int k = j; k < length; k++){
				for(int l = i; l < length; l++){
					if(mapp.map[l][k] != 3){
						path[0] = l;
						path[1] = k;
						break;
					}
				}
			}
		}
		return path;
	}
	/*if(j == 0 && i > 0){ // vertical axis
		for(int k = 0; k < length; k++){
			if(forest[k][j] != 7){
				path[0] = k;
				path[1] = 0;
				break;
			}
			else{
				System.out.println("i and j : "+ i +" , " + j);
				i++;
			}
		}
	}*/

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
