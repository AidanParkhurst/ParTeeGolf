package main;

import java.awt.Graphics2D;

public class Level {
	private int ww, wh;
	private int xoff, yoff;
	private int s;
	private Tile[][] grid;
	
	public Level(Tile[][] g, int s_){
		grid = g;
		s = s_;
	}
	
	public void paint(Graphics2D g) {
		xoff = (ww - s) / 2;
		yoff = (wh - s) / 2;
		for(int i = 0; i < grid.length; i++) {
			Tile[] row = grid[i];
			for(int j = 0; j < row.length; j++) {
				row[j].paint(xoff + j * row[j].getW(), yoff + i * row[j].getH(), g);
			}
		}
	}	
	public void setWw(int ww) {
		this.ww = ww;
	}

	public void setWh(int wh) {
		this.wh = wh;
	}

	public Tile[][] getGrid() {
		return grid;
	}

}
