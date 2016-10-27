package test.test01;

import javax.swing.JPanel;

public class animacao extends JPanel implements Runnable {
	Thread anime = null;

	public void start() {
		anime = new Thread(this);
		anime.start();
	}

	public void run() {
		for (int animframe = 0; animframe < 10; animframe++) {
			// configurando o quadro
			// setup_animacao_frame (animframe);
			// redesenhando a tela
			repaint();
			// faz um intervalo de 0,1 segundos (100 milionésimos
			// de segundo) entre os frames
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
