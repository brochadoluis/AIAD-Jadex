/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * @author Hugo
 * 
 */
public class Map {
	
	int[] watch;
	
	public Map() {
    	map = mapClear;
    	watch = new int[2];
    	watch[0] = 2;
    	watch[1] = 2;
    }

	public int[][] getMap() {
        return map;
    }
	
	public void setMap(int x, int y, int value) {
		map[y][x] = value;
    }

	
	public int moveWatch() {
		int res = 0;
		int x = watch[0];
		int y = watch[1];
		
		
		
		Random r = new Random();
		int d = r.nextInt(4);
		d=0;
		
		if(watch[0] == 12){
			d=1;
			if(watch[1]==6 && watch[0] == 12){
				d=2;
			}
		}
		if(watch[1]>=6 && watch[1] <=10){
			d=2;
			if(watch[1]>=6 && watch[0] == 2){
				d=1;
			}
			if(watch[1]==11)
				d=0;
		}
		if(watch[1]==11 && watch[0] == 12)
			d=1;
		if(watch[1]==14)
			d=2;
		if(watch[0]==0 && watch[1]==14)
			d=4;
		switch (d){
			case 0:
				if(map[y][x + 1] == 0){
					watch[0] = x + 1;
					map[y][x + 1] = 4;
					map[y][x] = 0;
					res = 1;
				}
				else
					res = 0;
				break;
			case 1:
				if(map[y + 1][x] == 0){
					watch[1] = y + 1;
					map[y + 1][x] = 4;
					map[y][x] = 0;
					res = 1;
				}
				else
					res = 0;
				break;
			case 2:
				if(map[y][x - 1] == 0){
					watch[0] = x - 1;
					map[y][x - 1] = 4;
					map[y][x] = 0;
					res = 1;
				}
				else
					res = 0;
				break;
			case 3:
				if(map[y - 1][x] == 0){
					watch[1] = y - 1;
					map[y - 1][x] = 4;
					map[y][x] = 0;
					res = 1;
				}
				else
					res = 0;
				break;
			case 4:
				watch[0]=1;
				watch[1]=2;
				map[y][x]=0;
				break;
		}
		return res;
    }
	
    //0 - burnable path; 1 - base/commander; 2 - Firefighter; 3 - fire; 4 - watcher
    private static int[][] mapClear={
            {1,2,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {2,2,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,4,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };
    
    private static int[][] map={
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };
    
    public void printMap(){
    	String res = "";
    	int num = 0;
    	for(int i = 0; i < 15;i++){
    		if(i < 10)
    			res += i +" ";
    		else
    			res += i;
    		res += "|";
    		for(int j = 0; j < 15;j++){
    			num = map[i][j];
    			switch(num){
    			case 0:
    				res += "   ";
    				break;
    			case 1:
    				res += " B ";
    				break;
    			case 2:
    				res += " F ";
    				break;
    			case 3:
    				res += " f ";
    				break;
    			case 4:
    				res += " W ";
    				break;
    			}
    			res += "|";
    		}
    		res += "\n";
    	}
    	res += "    0   1   2   3   4   5   6   7   8   9  10  11  12  13  14";
    	System.out.println(res);
    }
    
    public void startFire(){
    	
    	Random r = new Random();
    	
    	int x = r.nextInt(4) + 10;
    	int y = r.nextInt(4) + 10;
    	if(map[y][x] == 0){
    		map[y][x] = 3;}
    	if(map[y + 1][x] == 0){
        	map[y + 1][x] = 3;}
    	if(map[y][x + 1] == 0){
        	map[y][x + 1] = 3;}
    	if(map[y + 1][x + 1] == 0){
        	map[y + 1][x + 1] = 3;}
    	
    }
    
    public ArrayList<int[]> lookAround(int[] is) {
		ArrayList<int[]> adjacent = new ArrayList<int[]>();
		int i = is[0];
		int j = is[1];
		if (j - 1 >= 0) {
			if (map[i][j - 1] == 3) {
				int[] res = new int[2];
				res[1] = i;
				res[0] = j - 1;
				adjacent.add(res);
			}
			if (i + 1 < 15) {
				if (map[i + 1][j - 1] == 3) {
					int[] res = new int[2];
					res[1] = i + 1;
					res[0] = j - 1;
					adjacent.add(res);
				}
			}
		}
		if (i - 1 >= 0) {
			if (map[i - 1][j] == 3) {
				int[] res = new int[2];
				res[1] = i - 1;
				res[0] = j;
				adjacent.add(res);
			}
			if (j - 1 >= 0) {
				if (map[i - 1][j - 1] == 3) {
					int[] res = new int[2];
					res[1] = i - 1;
					res[0] = j - 1;
					adjacent.add(res);
				}
			}
		}
		if (j + 1 < 15) {
			if (map[i][j + 1] == 3) {
				int[] res = new int[2];
				res[1] = i;
				res[0] = j + 1;
				adjacent.add(res);
			}
			if (i - 1 >= 0) {
				if (map[i - 1][j + 1] == 3) {
					int[] res = new int[2];
					res[1] = i - 1;
					res[0] = j + 1;
					adjacent.add(res);
				}
			}
		}
		if (i + 1 < 15) {
			if (map[i + 1][j] == 3) {
				int[] res = new int[2];
				res[1] = i + 1;
				res[0] = j;
				adjacent.add(res);
			}
			if (j + 1 < 15) {
				if (map[i + 1][j + 1] == 3) {
					int[] res = new int[2];
					res[1] = i + 1;
					res[0] = j + 1;
					adjacent.add(res);
				}
			}
		}

		return adjacent;
	}
	
	public ArrayList<int[]> lookArounder(int[] is) {
		ArrayList<int[]> adjacent = new ArrayList<int[]>();
		int i = is[0];
		int j = is[1];
		if (j - 2 >= 0) {
			if (map[i][j - 2] == 3) {
				int[] res = new int[2];
				res[1] = i;
				res[0] = j - 2;
				adjacent.add(res);
			}
			if (i + 1 < 15) {
				if (map[i + 1][j - 2] == 3) {
					int[] res = new int[2];
					res[1] = i + 1;
					res[0] = j - 2;
					adjacent.add(res);
				}
			}
			if (i - 1 >= 0) {
				if (map[i - 1][j - 2] == 3) {
					int[] res = new int[2];
					res[1] = i - 1;
					res[0] = j - 2;
					adjacent.add(res);
				}
			}
			if (i + 2 < 15) {
				if (map[i + 2][j - 2] == 3) {
					int[] res = new int[2];
					res[1] = i + 2;
					res[0] = j - 2;
					adjacent.add(res);
				}
			}
		}
		if (i - 2 >= 0) {
			if (map[i - 2][j] == 3) {
				int[] res = new int[2];
				res[1] = i - 2;
				res[0] = j;
				adjacent.add(res);
			}
			if (j - 1 >= 0) {
				if (map[i - 2][j - 1] == 3) {
					int[] res = new int[2];
					res[1] = i - 2;
					res[0] = j - 1;
					adjacent.add(res);
				}
			}
			if (j + 1 < 15) {
				if (map[i - 2][j + 1] == 3) {
					int[] res = new int[2];
					res[1] = i - 2;
					res[0] = j + 1;
					adjacent.add(res);
				}
			}
			if (j - 2 >= 0) {
				if (map[i - 2][j - 2] == 3) {
					int[] res = new int[2];
					res[1] = i - 2;
					res[0] = j - 2;
					adjacent.add(res);
				}
			}
		}
		if (j + 2 < 15) {
			if (map[i][j + 2] == 3) {
				int[] res = new int[2];
				res[1] = i;
				res[0] = j + 2;
				adjacent.add(res);
			}
			if (i - 1 >= 0) {
				if (map[i - 1][j + 2] == 3) {
					int[] res = new int[2];
					res[1] = i - 1;
					res[0] = j + 2;
					adjacent.add(res);
				}
			}
			if (i - 2 >= 0) {
				if (map[i - 2][j + 2] == 3) {
					int[] res = new int[2];
					res[1] = i - 2;
					res[0] = j + 2;
					adjacent.add(res);
				}
			}
			if (i + 1 < 15) {
				if (map[i + 1][j + 2] == 3) {
					int[] res = new int[2];
					res[1] = i + 1;
					res[0] = j + 2;
					adjacent.add(res);
				}
			}
			if (i + 2 < 15) {
				if (map[i + 2][j + 2] == 3) {
					int[] res = new int[2];
					res[1] = i + 2;
					res[0] = j + 2;
					adjacent.add(res);
				}
			}
			
		}
		if (i + 2 < 15) {
			if (map[i + 2][j] == 3) {
				int[] res = new int[2];
				res[1] = i + 2;
				res[0] = j;
				adjacent.add(res);
			}
			if (j + 1 < 15) {
				if (map[i + 2][j + 1] == 3) {
					int[] res = new int[2];
					res[1] = i + 2;
					res[0] = j + 1;
					adjacent.add(res);
				}
			}
			if (j - 1 < 15) {
				if (map[i + 2][j - 1] == 3) {
					int[] res = new int[2];
					res[1] = i + 2;
					res[0] = j - 1;
					adjacent.add(res);
				}
			}
		}

		return adjacent;
	}

}
