package test.test05;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class LogoAnimator extends JPanel 
       implements ActionListener, Serializable {
   protected ImageIcon images[];
   protected int totalImages = 10,
                 currentImage = 0,
                 animationDelay = 100;
   protected Timer animationTimer;

   public LogoAnimator()
   {
      images = new ImageIcon[ totalImages ];

      URL url;
      for ( int i = 0; i < images.length; ++i ) {
         url = getClass().getResource(
                  "T" + i + ".JPG" );
         images[ i ] = new ImageIcon( url );
      }
      setSize( getPreferredSize() );
      startAnimation();
   }

   public void paintComponent( Graphics g )
   {
      super.paintComponent( g );

      if ( images[ currentImage ].getImageLoadStatus() ==
           MediaTracker.COMPLETE ) {
         images[ currentImage ].paintIcon( this, g, 40, 80 );
         currentImage = ( currentImage + 1 ) % totalImages;
      }
   }

   public void actionPerformed( ActionEvent e )
   {
      repaint();
   }

   public void startAnimation()
   {
      if ( animationTimer == null ) {
         currentImage = 0;  
         animationTimer = new Timer( animationDelay, this );
         animationTimer.start();
      }
      else  // continue from last image displayed
         if ( ! animationTimer.isRunning() )
            animationTimer.restart();
   }

   public void stopAnimation()
   {
      animationTimer.stop();
   }

   public Dimension getMinimumSize()
   { 
      return getPreferredSize(); 
   }

   public Dimension getPreferredSize()
   {
	  Dimension d = new Dimension(images[0].getIconWidth(), images[0].getIconHeight());
	  if (d != null) {
		  return d;
	  }
	  else {
		return new Dimension( 230, 80 );
	  }
   }

   public static void main( String args[] )
   {
      LogoAnimator anim = new LogoAnimator();

      JFrame app = new JFrame( "Animador ..." );
      app.getContentPane().add( anim, BorderLayout.CENTER );

      app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      //app.setSize( anim.getPreferredSize().width + 8,
      //             anim.getPreferredSize().height + 30 );
	  app.setSize(400, 400);

	  app.setLocation(200, 200);
	  app.setResizable(false);
      app.show();
   }
}