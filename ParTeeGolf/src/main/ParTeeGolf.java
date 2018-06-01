package main;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ParTeeGolf extends JFrame {	
	private static final long serialVersionUID = 8846585003791597470L;

	public ParTeeGolf() {
		add(new GamePanel());
	}
	
	public static void main(String[] args ) {
		ParTeeGolf w = new ParTeeGolf();
		w.setTitle("ParTee Golf!");
		w.setSize(600,600);
		w.setLocationRelativeTo(null);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setVisible(true);
	}

}

class GamePanel extends JPanel implements MouseListener, MouseMotionListener{
	GolfBall ball;
	
//Mouse controls
	protected int cmx, cmy;
	protected int mx, my;
	protected int nmx, nmy;
	protected int power;
	protected int dm;
	protected boolean dragLine = false;
	protected Stroke dashed = new BasicStroke(3,BasicStroke.CAP_BUTT,
								BasicStroke.JOIN_BEVEL, 0, new float[]{8}, 0);
	
	public GamePanel() {
		super();
		ball = new GolfBall();
	}
	@Override
	public void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2;
		ball.paint(g);
		ball.update();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		nmx = e.getX();
		nmy = e.getY();
		cmx = nmx;
		cmy = nmy;
		dm = (int)Math.sqrt(Math.pow(e.getX() - mx, 2) + Math.pow(e.getY() - my, 2));
		power = dm / 20;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		cmx = e.getX();
		cmy = e.getY();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ball.setxVel(Math.cos(Math.atan2(my - e.getY(), mx - e.getX())) * power);
		ball.setyVel(Math.sin(Math.atan2(my - e.getY(), mx - e.getX())) * power);
		cmx = e.getX();
		cmy = e.getY();
		dragLine = false;
	}
	
//Unused
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}