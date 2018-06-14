package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class GolfBall {
	protected double x, y;
	protected int radius = 7;
	protected double xVel, yVel;
	protected boolean cRight, cLeft, cTop, cBottom;
	protected Tile inLast = null;
	public GolfBall() {
		
	}
	
	public void paint(Graphics2D g) {
		if(ParTeeGolf.DEBUG) {
			g.setColor(Color.RED);
			g.setStroke(new BasicStroke(3));
			g.drawRect((int)x-radius, (int)y-radius, radius * 2, radius * 2);
		}
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.WHITE);
		g.fillArc((int)x - radius, (int)y - radius, radius*2, radius*2, 0, 360);
		g.setColor(Color.BLACK);
		g.drawArc((int)x - radius, (int)y - radius, radius*2, radius*2, 0, 360);

	}

	public void update(Level l) {
		
		Tile[][] grid = l.getGrid();
		for(int i = 0; i < grid.length; i++) {
			Tile[] row = grid[i];
			for(int j = 0; j < row.length; j++) {
				Tile t = row[j];
				if(t.contains((int)x, (int)y)) {
					if(!(t.getType() == Tile.WALL)) {
						inLast = t;
						t.setLast(true);
						resetC();
					}
					else {
						if(t.compare(inLast) == Tile.LEFT) {
							cRight = true;
						}
						if(t.compare(inLast) == Tile.RIGHT) {
							cLeft = true;
						}
						if(t.compare(inLast) == Tile.BOTTOM) {
							cTop = true;
						}
						if(t.compare(inLast) == Tile.TOP) {
							cBottom = true;
						}
					}
				}
				else {
					t.setLast(false);
				}
			}
		}
		if(cRight && xVel > 0 || cLeft && xVel < 0)
			xVel *= -1;
		if(cTop && yVel < 0 || cBottom && yVel > 0)
			yVel *= -1;
		x += xVel;
		y += yVel;
		if(Math.abs(xVel) > 0) {
			xVel *= .97;
		}
		if(Math.abs(yVel) > 0) {
			yVel *= .97;
		}
		if(Math.abs(xVel) < 1 && Math.abs(yVel) < 1) {
			xVel = 0;
			yVel = 0;
		}
	}
	
	public void resetC() {
		cLeft = false;
		cRight = false;
		cTop = false;
		cBottom = false;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getxVel() {
		return xVel;
	}
	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public double getyVel() {
		return yVel;
	}
	public void setyVel(double yVel) {
		this.yVel = yVel;
	}
}
