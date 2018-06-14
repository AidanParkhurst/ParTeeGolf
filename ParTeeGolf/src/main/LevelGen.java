package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelGen {
	public LevelGen(String path) throws FileNotFoundException{
		ArrayList<Level> res = new ArrayList<Level>();
		File src = new File(path);
		Scanner scan = new Scanner(src);
		
		ArrayList<Tile[]> tgrid = new ArrayList<Tile[]>();
		Tile[][] grid;
		int col = 0, row = 0;
		int s = 0, sw = 0, sh = 0;
		
		while(scan.hasNext()) {
			String curr = scan.next();
			if(curr.startsWith("lvls")) {
				String[] temp = curr.split(" ");
				s = Integer.parseInt(temp[1]);
				String[] dem = temp[2].split("x");
				sw = s / Integer.parseInt(dem[0]);
				sh = s / Integer.parseInt(dem[1]);
			}
			if(curr.startsWith("lvle")) {
				grid = new Tile[row][col];
				for(Tile t[]: tgrid) {
					grid[tgrid.indexOf(t)] = t;
					for(int i = 0; i < t.length; i++)
						grid[tgrid.indexOf(t)][i] = tgrid.get(tgrid.indexOf(t))[i];
				}
				res.add(new Level(grid));
				col = 0;
				row = 0;
			}
			else {
				char[] line = curr.toCharArray();
				col = line.length;
				tgrid.add(new Tile[col]);
				for(int i = 0; i < line.length; i++) {
					Tile t = new Tile(sw, sh);
					switch(line[0]) {
					case '-':
						t.setType(Tile.EMPTY);
						break;
					case '=':
						t.setType(Tile.GRASS);
						break;
					case '#':
						t.setType(Tile.WALL);
					case '@':
						t.setType(Tile.START);
					case '*':
						t.setType(Tile.END);
					}
					tgrid.get(row)[i] = t;
				}
				row++;
			}
		}
		scan.close();
	}
}
