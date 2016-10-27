package org;

import javax.swing.JFrame;

public class Editor extends JFrame {

	private static final long serialVersionUID = 1L;

	public Editor() {
		super("Editor 2D.");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		PainelDesenho painelDesenho = new PainelDesenho();
		this.add("Center", painelDesenho);
		this.add("North", new PainelControle(painelDesenho));
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Editor();
	}
}

