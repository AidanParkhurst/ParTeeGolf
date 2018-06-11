package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class GolfBall {
	protected double x, y;
	protected int radius = 7;
	protected double xVel, yVel;

	public GolfBall() {
		
	}
	
	public void paint(Graphics2D g) {
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.WHITE);
		g.fillArc((int)x - radius, (int)y - radius, radius*2, radius*2, 0, 360);
		g.setColor(Color.BLACK);
		g.drawArc((int)x - radius, (int)y - radius, radius*2, radius*2, 0, 360);
//		Image ball = Toolkit.getDefaultToolkit().getImage("assets/Golfball.png");
//		g.drawImage(ball, (int)x-radius, (int)y-radius, radius*2, radius*2, null);

	}

	public void update(LevelGrid l) {
		Tile[][] grid = l.getGrid();
		for(int i = 0; i < grid.length; i++) {
			Tile[] row = grid[i];
			for(int j = 0; j < row.length; j++) {
				Tile t = row[j];
				if(t.getFilled()) {
					
				}
			}
		}
		
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
