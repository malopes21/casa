package org;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PainelDesenho extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Timer timer = new Timer(30, this);

	int centerX, centerY;
	int maxX, maxY;
	float pixelSize, rWidth = 100.0F, rHeight = 100.0F;
	private List<Geometria> geometrias = new ArrayList<Geometria>();

	////// -new

	private boolean primeiraVez = true;
	private RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
	private BufferedImage bi;
	private Graphics2D big;

	//////

	void initgr() {
		Dimension d = getSize();
		maxX = d.width - 1;
		maxY = d.height - 1;
		pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
		centerX = maxX / 2;
		centerY = maxY / 2;
	}

	int ix(float x) {
		return Math.round(centerX + x / pixelSize);
	}

	int iy(float y) {
		return Math.round(centerY - y / pixelSize);
	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		if (primeiraVez) {
			initGeometrias();
			initgr();
			bi = (BufferedImage) createImage(maxX, maxY);
			big = bi.createGraphics();
			big.setStroke(new BasicStroke(4.0f));
			big.setRenderingHints(qualityHints);
			primeiraVez = false;
		}

		big.setPaint(Color.BLACK);
		big.fillRect(0, 0, maxX, maxY);

		Geometria eixos = geometrias.get(0);
		drawGeometria(big, eixos, Color.white);

		Geometria ret1 = geometrias.get(1);
		drawGeometria(big, ret1, Color.green);
		
		Geometria ret2 = geometrias.get(2);
		drawGeometria(big, ret2, Color.blue);

		g2.drawImage(bi, 0, 0, this);

	}

	void drawGeometria(Graphics g, Geometria geom, Color color) {
		if (color != null) {
			g.setColor(color);
		}
		for (Aresta aresta : geom.getArestas()) {
			Point2D p1 = geom.getPontos().get(aresta.getIni());
			Point2D p2 = geom.getPontos().get(aresta.getFim());
			drawLinha(g, p1, p2);
		}
	}

	void drawLinha(Graphics g, Point2D p1, Point2D p2) {
		g.drawLine(ix(p1.x), iy(p1.y), ix(p2.x), iy(p2.y));
	}

	public void aplicaTransformacao() {

		Geometria ret1 = geometrias.get(1);
		Transformacoes.translacao(ret1, 15.0f, -4.0f);
		repaint();
	}

	public void aplicaRotacao() {

		Geometria ret1 = geometrias.get(1);
		Transformacoes.rotacao(ret1, 30f);
		repaint();
	}

	public void aplicaComposicaoSemRepaint() {

		Geometria ret1 = geometrias.get(1);

		Point2D base = pontoMedio(ret1);

		float[][] T1 = Transformacoes.matTrans(-base.x, -base.y);
		float[][] T2 = Transformacoes.matRot(30);
		float[][] T3 = Transformacoes.matTrans(base.x, base.y);

		float[][] Tf = Transformacoes.matComp(Transformacoes.matComp(T1, T2), T3);

		Transformacoes.composicao(ret1, Tf);
		Transformacoes.rotacao(ret1, 2);

		// Transformacoes.translacao(ret1, -1, 0);
		
		Geometria ret2 = geometrias.get(2);
		Transformacoes.rotacao(ret2, 2);
	}

	public void aplicaComposicao() {

		aplicaComposicaoSemRepaint();
	}

	public void aplicaAnimacao() {
		timer.start();
	}

	private Point2D pontoMedio(Geometria geom) {
		Point2D ponto = new Point2D();
		for (Point2D p : geom.getPontos()) {
			ponto.x = ponto.x + p.x;
			ponto.y = ponto.y + p.y;
		}
		ponto.x = ponto.x / geom.getPontos().size();
		ponto.y = ponto.y / geom.getPontos().size();
		return ponto;
	}

	private void initGeometrias() {

		// o eixo principal
		Geometria geometria = new Geometria();
		geometria.setId("eixos");
		geometria.getPontos().add(new Point2D(-100, 0));
		geometria.getPontos().add(new Point2D(100, 0));
		geometria.getPontos().add(new Point2D(0, -100));
		geometria.getPontos().add(new Point2D(0, 100));

		geometria.getArestas().add(new Aresta(0, 1));
		geometria.getArestas().add(new Aresta(2, 3));

		geometrias.add(geometria);

		// o retangulo de teste
		geometria = new Geometria();
		geometria.setId("retangulo1");
		geometria.getPontos().add(new Point2D(35, 1));
		geometria.getPontos().add(new Point2D(40, 1));
		geometria.getPontos().add(new Point2D(40, 3));
		geometria.getPontos().add(new Point2D(35, 3));

		geometria.getArestas().add(new Aresta(0, 1));
		geometria.getArestas().add(new Aresta(1, 2));
		geometria.getArestas().add(new Aresta(2, 3));
		geometria.getArestas().add(new Aresta(3, 0));

		geometrias.add(geometria);
		
		// o retangulo ddo centro
		geometria = new Geometria();
		geometria.setId("retangulo2");
		geometria.getPontos().add(new Point2D(-20, -2));
		geometria.getPontos().add(new Point2D(20, -2));
		geometria.getPontos().add(new Point2D(20, 2));
		geometria.getPontos().add(new Point2D(-20, 2));

		geometria.getArestas().add(new Aresta(0, 1));
		geometria.getArestas().add(new Aresta(1, 2));
		geometria.getArestas().add(new Aresta(2, 3));
		geometria.getArestas().add(new Aresta(3, 0));

		geometrias.add(geometria);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		aplicaComposicao();
		repaint();
	}

}
