package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class GolfBall {
	protected double x, y;
	protected int radius = 10;
	protected double xVel, yVel;

	public GolfBall() {
		
	}
	
	public void paint(Graphics2D g) {
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.WHITE);
		g.fillArc((int)x - radius, (int)y - radius, radius*2, radius*2, 0, 360);
		g.setColor(Color.BLACK);
		g.drawArc((int)x - radius, (int)y - radius, radius*2, radius*2, 0, 360);
	}

	public void update() {
		x += xVel;
		y += yVel;
		if(Math.abs(xVel) > 0) {
			xVel *= .98;
		}
		if(Math.abs(yVel) > 0) {
			yVel *= .98;
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
