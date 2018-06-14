package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

public class Tile {
	public Random r = new Random();
	protected int w, h;
	protected int x, y;
	protected int type;
	public static final int LEFT = 2, RIGHT = 1, TOP = 0, BOTTOM = 3;
	public static final int EMPTY = 0, GRASS = 1, WALL = 2, END = 3, START = 4;
	private boolean last = false;
	private int img;
	
	private Image[] grasses = {
//			Toolkit.getDefaultToolkit().getImage("assets/grass0.png"),
			Toolkit.getDefaultToolkit().getImage("assets/grass1.png"),
			Toolkit.getDefaultToolkit().getImage("assets/grass2.png"),
			Toolkit.getDefaultToolkit().getImage("assets/grass3.png"),
			Toolkit.getDefaultToolkit().getImage("assets/grass4.png"),
			Toolkit.getDefaultToolkit().getImage("assets/grass5.png"),
	};
	private Image wall = Toolkit.getDefaultToolkit().getImage("assets/greybrick.png");
	
	public Tile(int width, int height) {
		w = width;
		h = height;
		img = r.nextInt(grasses.length);
	}

	public void paint(int gx, int gy, Graphics2D g) {
		x = gx;
		y = gy;
		if(type == GRASS) {
			g.drawImage(grasses[img], x, y, w, h, null);
		}
		else if(type == WALL){
			g.setColor(Color.BLACK);
			g.drawImage(wall, x, y, w, h, null);
		}
		if(ParTeeGolf.DEBUG) {
			if(last) {
				g.setColor(Color.PINK);
				g.fillRect(getX(), getY(), w, h);
			}
			else {
				g.setColor(Color.RED);
				g.drawRect(getX(), getY(), w, h);
			}
			g.setStroke(new BasicStroke(3));
			
		}
	}
	
	public boolean contains(int ox, int oy) {
		return (ox >= x && ox <= x + w) && (oy >= y && oy <= y + h);
	}
	
	public int compare(Tile other) {
		int result = -1;
		if(other.getY() == y) {
			if(other.getX() < x)
				result = LEFT;
			if(other.getX() > x)
				result = RIGHT;
		}
		if(other.getX() == x) {
			if(other.getY() < y)
				result = TOP;
			if(other.getY() > y)
				result = BOTTOM;
		}
		return result;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public void setLast(boolean last) {
		this.last = last;
	}

	public int getType() {
		return type;
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
