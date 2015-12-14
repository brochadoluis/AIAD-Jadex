/**
 * 
 */
package gui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Hugo
 * 
 */
public class Main {
	public static void main(String[] args) {

		Map map = new Map();
		Scanner s = new Scanner(System.in);
		String ss = "";
		map.startFire();
		map.printMap();
		System.out.println("nextstep");
		ArrayList<int[]> res = new ArrayList<int[]>();

		map.moveWatch();
		res.addAll(map.lookAround(map.watch));
		res.addAll(map.lookArounder(map.watch));
		if(!res.isEmpty()){
			int pos[] = new int[2];
			pos[0]=res.get(0)[0];
			pos[1]=res.get(0)[1];
			System.out.println("posx: " + pos[0] + "\tposy: " + pos[1]);
			map.moveFighter(pos);
			map.putOutFire();
		}
		map.printMap();
	}

}
