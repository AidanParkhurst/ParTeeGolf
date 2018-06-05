package main;

import java.awt.Graphics2D;

public class LevelGrid {
	private int col, row;
	private int ww, wh;
	private int sw, sh;
	private int xoff, yoff;
	private int s;
	
	public LevelGrid(int c, int r, int side) {
		col = c;
		row = r;
		s = side;
		sw = s/col;
		sh = s/row;
	}
	
	public void paint(Graphics2D g) {
		if(ww != 0 && wh != 0) {
			xoff = (ww - s) / 2;
			yoff = (wh - s) / 2;
			
			for(int i = 0; i < col; i++) {
				for(int j = 0; j < row; j++) {
					g.drawRect(xoff + (i * sw), yoff + (j * sh), sw, sh);
				}
			}
		}
	}
	
	public void setWw(int ww) {
		this.ww = ww;
	}

	public void setWh(int wh) {
		this.wh = wh;
	}

}
