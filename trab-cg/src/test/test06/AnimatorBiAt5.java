package test.test06;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.image.*;

public class AnimatorBiAt5 extends JApplet 
       implements ActionListener {
   protected Timer animationTimer = new Timer(30, this);
   private boolean primeiraVez = true;
   private  RenderingHints qualityHints = new
               RenderingHints(RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);
   private BufferedImage bi;
   private Graphics2D big;
   private GradientPaint gp = new GradientPaint(150, 150, Color.green, 250, 250, Color.yellow);
   private GradientPaint gp2 = new GradientPaint(130, 130, Color.white, 180, 180, Color.blue);
   private GradientPaint gp3 = new GradientPaint(230, 230, Color.red, 350, 250, Color.yellow);
   private AffineTransform at, at2;
   private int deltaAlfa = 5, deltaBeta = -10;

   private  Rectangle2D retangulo = new Rectangle2D.Double(-50, -50, 100, 100);
//	  Ellipse2D elipse = new Ellipse2D.Double(100, 100, 50, 100);
   private  Ellipse2D elipse = new Ellipse2D.Double(-25, -50, 50, 100);
   
   public void init() {
        setBackground(Color.black);
		animationTimer.start();
   }

   public void paint( Graphics g )
   {
	  Graphics2D g2 = (Graphics2D) g;

	  if (primeiraVez) {
		bi = (BufferedImage)createImage(450, 400);
		big = bi.createGraphics();
        big.setStroke(new BasicStroke(18.0f));
		qualityHints.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_SPEED);
	    big.setRenderingHints(qualityHints);
		at = AffineTransform.getTranslateInstance(230, 230);
		at2 = AffineTransform.getTranslateInstance(170, 170);
		primeiraVez = false;
	  }

	  big.clearRect(0, 0, 450, 400);
      
	  at.concatenate(AffineTransform.getRotateInstance((Math.PI*deltaAlfa)/180.0));
	  
	  at2.concatenate(AffineTransform.getRotateInstance((Math.PI*deltaBeta)/180.0));

	  big.setPaint(gp);
	  big.draw(at.createTransformedShape(retangulo));

	  big.setPaint(gp2);
	  big.draw(at2.createTransformedShape(elipse));

  	  big.setPaint(gp3);
	  big.fillOval(250, 200, 100, 60);
	  
	  big.setColor(Color.red);
	  big.draw(new Line2D.Double(180, 180, 280, 180));
	  g2.drawImage(bi, 0, 0, this);	
	 // System.out.println(""+elipse.getX());

   }

   public void actionPerformed( ActionEvent e )
   {
      repaint();
   }

   public static void main( String args[] )
   {
      AnimatorBiAt5 anim = new AnimatorBiAt5();
	  anim.getContentPane().setBackground(Color.white);

      JFrame app = new JFrame( "AnimatorBiAt3" );
      app.getContentPane().add( anim, BorderLayout.CENTER );

      app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	  anim.init();
	  app.setSize(450,400);
	  app.setLocation(100,100);
      app.show();
   }
}