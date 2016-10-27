package test.test03.bom;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.image.*;


public class AnimatorBi extends JApplet 
       implements ActionListener {
   protected Timer animationTimer = new Timer(15, this);
   private int angulo=3;
   private boolean primeiraVez = true;
   private  RenderingHints qualityHints = new
               RenderingHints(RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);
   private BufferedImage bi;
   private Graphics2D big;
   private GradientPaint gp = new GradientPaint(150, 150, Color.green, 250, 250, Color.yellow);

   private Rectangle2D.Double retangulo = new Rectangle2D.Double(200, 200, 60, 60);
   	public void init() {
        setBackground(Color.black);
        setForeground(Color.black);
		animationTimer.start();
    }

   public void paint( Graphics g )
   {
      //super.paint( g );
	  Graphics2D g2 = (Graphics2D) g;

	  if (primeiraVez) {
		bi = (BufferedImage)createImage(450, 400);
		big = bi.createGraphics();
        big.setStroke(new BasicStroke(12.0f));
	    big.setRenderingHints(qualityHints);
		primeiraVez = false;
	  }

	  big.setPaint(Color.white);
	  big.fillRect(100, 100, 300, 300);
	  
	  big.translate(230,230);
	  big.rotate((Math.PI*angulo)/180.0);
	  big.translate(-230,-230);
  	  
	  big.setPaint(gp);
	  big.drawRect(180, 180, 100, 100);
	  big.setColor(Color.red);
	  big.draw(new Line2D.Double(180, 180, 280, 180));
	  g2.drawImage(bi, 0, 0, this);	

   }

   public void actionPerformed( ActionEvent e )
   {
      repaint();
   }

   public static void main( String args[] )
   {
      AnimatorBi anim = new AnimatorBi();
	  anim.getContentPane().setBackground(Color.white);

      JFrame app = new JFrame( "AnimatorBi" );
      app.getContentPane().add( anim, BorderLayout.CENTER );

      app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	  anim.init();
	  app.setSize(450,400);
	  app.setLocation(100,100);
      app.show();
   }
}