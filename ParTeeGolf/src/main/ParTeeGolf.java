package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ParTeeGolf extends JFrame {	
	private static final long serialVersionUID = 8846585003791597470L;
	public static final boolean DEBUG = false;
	public ParTeeGolf() {
		add(new GamePanel());
	}
	
	public static void main(String[] args ) {
		ParTeeGolf w = new ParTeeGolf();
		w.setTitle("ParTee Golf!");
		w.setSize(800,800);
		w.setLocationRelativeTo(null);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setVisible(true);
	}

}

class GamePanel extends JPanel implements MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 5639036629985791496L;
	public static final int FPS = 60;
	int cLvl = -1;
	
//Game Objects
	GolfBall ball;
	LevelGen lg = new LevelGen();
	ArrayList<Level> levels = lg.generate("data/levels.ptg");
	Level currLevel;

//Mouse controls
	protected int cmx, cmy; 	//Current mouseX mouseY, updated in every mouse event.
	protected int mx, my; 		//Saved mouseX and mouseY when mouse is clicked
	protected int power;	 	//Determines speed of hit, based on distance dragged
	protected int powerCap = 20;
	protected boolean dragLine = false; //Whether or not to draw line while dragging
	protected int dragMax = 175;
	protected Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	
	public GamePanel() {
		super();
		ball = new GolfBall();
		addMouseListener(this);
		addMouseMotionListener(this);
		Timer gameClock = new Timer(1000/FPS, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
		});
		gameClock.start();
	}

	@Override
	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2;
		if(ball.isFinished() && cLvl < levels.size() - 1 || cLvl == -1) {
			ball.setxVel(0);
			ball.setyVel(0);
			cLvl++;
			currLevel = levels.get(cLvl);
			currLevel.setWh(getHeight());
			currLevel.setWw(getWidth());
			currLevel.paint(g);
			ball.start(currLevel.getStart().getX() + currLevel.getStart().getW()/2,currLevel.getStart().getY() + currLevel.getStart().getH()/2);
		}
		ball.update(currLevel);
		currLevel.paint(g);
		ball.paint(g);
		if(dragLine) {
			g.setColor(Color.RED);
			g.setStroke(dashed);
			g.drawLine((int)ball.getX(), (int)ball.getY(), (int)(ball.getX() + limit(mx - cmx,dragMax)), (int)(ball.getY() + limit(my - cmy,dragMax)));
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		cmx = e.getX();
		cmy = e.getY();
		int dm = (int)Math.sqrt(Math.pow(e.getX() - mx, 2) + Math.pow(e.getY() - my, 2));
		power = 2 + (dm / 10);
		power = limit(power, powerCap);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		cmx = e.getX();
		cmy = e.getY();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		cmx = mx;
		cmy = my;
		dragLine = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(!(mx == e.getX() && my == e.getY())) {
			ball.setxVel(Math.cos(Math.atan2(my - e.getY(), mx - e.getX())) * power);
			ball.setyVel(Math.sin(Math.atan2(my - e.getY(), mx - e.getX())) * power);
		}
		cmx = e.getX();
		cmy = e.getY();
		dragLine = false;
	}
	
	public int limit(int x, int cap) {
		if(Math.abs(x) > cap)
			return x < 0 ? cap * - 1: cap;
		else
			return x;
	}
//Unhandled
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}