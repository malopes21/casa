package test.test02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.Timer;

public class AnimatorApp extends JApplet 
       implements ActionListener {
   protected Timer animationTimer = new Timer(50, this);
   private int angulo=0;
   private boolean primeiraVez = true;

   	public void init() {
        setBackground(Color.white);
        setForeground(Color.black);
		animationTimer.start();
    }

   public void paint( Graphics g )
   {
      super.paint( g );
	  Graphics2D g2 = (Graphics2D) g;

	  RenderingHints qualityHints = new
               RenderingHints(RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);

	  g2.setRenderingHints(qualityHints);

	  g2.setFont(new Font("Verdana", Font.BOLD+Font.ITALIC, 18));
	  g2.drawString("Autor: Marcos Lopes", 20,20);
	  g2.setColor(Color.red);
	
//	  angulo =  ((int) ((Math.PI*10.0)/180)));
	  angulo = (angulo + 6)%360;
	  g2.translate(230,230);
	  g2.rotate((Math.PI*angulo)/180.0);
	  g2.translate(-230,-230);
	  g2.fill(new Rectangle2D.Double(200, 200, 60, 60));	
	 
	//  System.out.println("Ok"+angulo);

   }

   public void actionPerformed( ActionEvent e )
   {
      repaint();
   }

   public static void main( String args[] )
   {
      AnimatorApp anim = new AnimatorApp();
	  anim.getContentPane().setBackground(Color.white);

      JFrame app = new JFrame( "LogoAnimatorApp" );
      app.getContentPane().add( anim, BorderLayout.CENTER );

      app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	  anim.init();
	  app.pack();
      app.setSize(450,400);
	  app.setLocation(100,100);
      app.show();
   }
}