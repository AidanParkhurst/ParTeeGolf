package main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Tile {
	protected int w, h;
	protected int x, y;
	protected boolean filled;
	
	Image grass = Toolkit.getDefaultToolkit().getImage("assets/slightlylessshittygrass.png");
	
	public Tile(int width, int height) {
		filled = Math.random() < .2;
		w = width;
		h = height;
	}

	public void paint(int gx, int gy, Graphics2D g) {
		x = gx;
		y = gy;
		if(!filled) {
			g.drawImage(grass, x, y, w, h, null);
		}
		else {
			g.fillRect(x, y, w, h);
		}
	}
	
	public boolean getFilled() {
		return filled;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
