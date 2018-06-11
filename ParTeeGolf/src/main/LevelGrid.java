package main;

import java.awt.Graphics2D;

public class LevelGrid {
	private int col, row;
	private int ww, wh;
	private int sw, sh;
	private int xoff, yoff;
	private int s;
	private Tile[][] grid;
	
	public LevelGrid(int c, int r, int side) {
		col = c;
		row = r;
		s = side;
		sw = s/col;
		sh = s/row;
		grid = new Tile[col][row];
		for(int i = 0; i < grid.length; i++) {
			Tile[] row = grid[i];
			for(int j = 0; j < row.length; j++) {
				row[j] = new Tile(sw, sh);
			}
		}
	}
	
	public void paint(Graphics2D g) {
		xoff = (ww - s) / 2;
		yoff = (wh - s) / 2;
		for(int i = 0; i < grid.length; i++) {
			Tile[] row = grid[i];
			for(int j = 0; j < row.length; j++) {
				row[j].paint(xoff + i * sw, yoff + j * sh, g);
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
