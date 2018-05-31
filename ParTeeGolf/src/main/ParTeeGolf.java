package main;

import javax.swing.JFrame;

//This will presumably be our main
public class ParTeeGolf extends JFrame {	
	private static final long serialVersionUID = 8846585003791597470L;

	public ParTeeGolf() {
		System.out.println("This is the frame starting!");
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