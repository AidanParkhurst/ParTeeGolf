package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelGen {
	
	public LevelGen(){
		
	}

	public ArrayList<Level> generate(String path){
		ArrayList<Level> res = new ArrayList<Level>();
		File src = new File(path);
		Scanner scan = null;
		try {
			scan = new Scanner(src);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Tile[]> tgrid = new ArrayList<Tile[]>();
		Tile[][] grid;
		int col = 0, row = 0;
		int s = 0, sw = 0, sh = 0;
		
		while(scan.hasNextLine()) {
			String curr = scan.nextLine();
			if(curr.startsWith("lvls")) {
				String[] temp = curr.split(" ");
				s = Integer.parseInt(temp[1]);
				String[] dem = temp[2].split("x");
				sw = s / Integer.parseInt(dem[0]);
				sh = s / Integer.parseInt(dem[1]);
			}
			else if(curr.startsWith("lvle")) {
				grid = new Tile[row][col];
				for(Tile t[]: tgrid) {
					grid[tgrid.indexOf(t)] = t;
				}
				tgrid = new ArrayList<Tile[]>();
				res.add(new Level(grid,s));
				col = 0;
				row = 0;
			}
			else {
				char[] line = curr.toCharArray();
				col = line.length;
				tgrid.add(new Tile[col]);
				for(int i = 0; i < col; i++) {
					Tile t = new Tile(sw, sh);
					switch(line[i]) {
					case '-':
						t.setType(Tile.EMPTY);
						break;
					case '=':
						t.setType(Tile.GRASS);
						break;
					case '#':
						t.setType(Tile.WALL);
						break;
					case '@':
						t.setType(Tile.START);
						break;
					case '*':
						t.setType(Tile.END);
						break;
					}
					tgrid.get(row)[i] = t;
				}
				row++;
			}
		}
		scan.close();
		return res;
	}

}
