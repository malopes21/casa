package org;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PainelControle extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnInit;
	private PainelDesenho painelDesenho;
	
	public PainelControle(PainelDesenho painelDesenho) {
		this.painelDesenho = painelDesenho;
		btnInit = new JButton("Inicia");
		btnInit.addActionListener(this);
		this.add(btnInit);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInit) {
			//painelDesenho.aplicaRotacao();
			//painelDesenho.aplicaComposicao();
			painelDesenho.aplicaAnimacao();
		}
	}
}
