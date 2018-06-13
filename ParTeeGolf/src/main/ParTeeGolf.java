package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ParTeeGolf extends JFrame {	
	private static final long serialVersionUID = 8846585003791597470L;
	public static final boolean DEBUG = true;
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
	GolfBall ball;
	LevelGrid testl;
	
//Mouse controls
	protected int cmx, cmy; 	//Current mouseX mouseY, updated in every mouse event.
	protected int mx, my; 		//Saved mouseX and mouseY when mouse is clicked
	protected int power;	 	//Determines speed of hit, based on distance dragged 
	protected boolean dragLine = false; //Whether or not to draw line while dragging
	
	public GamePanel() {
		super();
		ball = new GolfBall();
		testl = new LevelGrid(10, 10, 600);
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
		testl.setWh(getHeight());
		testl.setWw(getWidth());
		Graphics2D g = (Graphics2D) g2;
		testl.paint(g);
		ball.update(testl);
		ball.paint(g);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		cmx = e.getX();
		cmy = e.getY();
		int dm = (int)Math.sqrt(Math.pow(e.getX() - mx, 2) + Math.pow(e.getY() - my, 2));
		power = dm / 20;
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
	
//Unhandled
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}