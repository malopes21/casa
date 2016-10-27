package test.test02;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.image.*;


public class AnimatorNotBi extends JApplet 
       implements ActionListener {
   protected Timer animationTimer = new Timer(30, this);
   private int angulo=5;
   private boolean primeiraVez = true;
   private  RenderingHints qualityHints = new
               RenderingHints(RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);
   private GradientPaint gp = new GradientPaint(150, 150, Color.green, 250, 250, Color.yellow);

   private Rectangle2D.Double retangulo = new Rectangle2D.Double(200, 200, 60, 60);

	public void init() {
        setBackground(Color.white);
        setForeground(Color.black);
		animationTimer.start();
    }

   public void paint( Graphics g )
   {
	  Graphics2D g2 = (Graphics2D) g;

	  if (primeiraVez) {
	    g2.setRenderingHints(qualityHints);
		primeiraVez = false;
	  }
      g2.setStroke(new BasicStroke(12.0f));

	  angulo = angulo + 5;
	  if (angulo == 360) angulo = 0;

	  g2.setPaint(Color.white);
	  g2.fillRect(100, 100, 300, 300);
	  g2.translate(230,230);
	  g2.rotate((Math.PI*angulo)/180.0);
	  g2.translate(-230,-230);
  	  g2.setPaint(gp);
	  g2.drawRect(180, 180, 100, 100);
	  g2.setColor(Color.red);
	  g2.draw(new Line2D.Double(180, 180, 280, 180));

   }

   public void actionPerformed( ActionEvent e )
   {
      repaint();
   }

   public static void main( String args[] )
   {
      AnimatorNotBi anim = new AnimatorNotBi();
	  anim.getContentPane().setBackground(Color.white);

      JFrame app = new JFrame( "AnimatorNotBi" );
      app.getContentPane().add( anim, BorderLayout.CENTER );

      app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	  anim.init();
	  app.setSize(450,400);
	  app.setLocation(100,100);
      app.show();
   }
}