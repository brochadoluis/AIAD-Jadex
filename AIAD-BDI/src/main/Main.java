/**
 * 
 */
package main;

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
		while(!ss.equals("s")){
			//map.startFire();
			map.printMap();
			System.out.println("nextstep");
			map.moveWatch();
			ss = s.nextLine();
		}
	}

}
