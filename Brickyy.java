import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

	public class Brickyy extends JPanel implements KeyListener, ActionListener, Runnable {

 		//Movement keys..

 		static boolean right = false;
 		static boolean left = false;

		//Variable declaration for ball

 		int bllx = 160;
 		int blly = 218;

 		//Variable declaration for bat

 		int btx = 230;
		int bty = 420;

 		//Variable declaration for brick

 		int brckx = 70;
 		int brcky = 50;

 		int score=0;

 		//Declaring ball, paddle,bricks

 		Rectangle Ball = new Rectangle(bllx, blly, 5, 5);
 		Rectangle Bat = new Rectangle(btx, bty, 40, 5);

 		// Rectangle Brick;// = new Rectangle(brickx, bricky, 30, 10);
 		Rectangle[] Brick = new Rectangle[12];

 		Brickyy() { }

 			public static void main(String[] args) {
  				JFrame frame = new JFrame();
  				Brickyy game = new Brickyy();
  				JButton button = new JButton("Restart");


  		frame.setTitle("Brick by boring brick");
  		frame.setSize(505, 500);
  		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 		frame.add(game);

  		frame.add(button, BorderLayout.SOUTH);
  		frame.setLocationRelativeTo(null);
  		frame.setResizable(false);
  		frame.setVisible(true);
  		button.addActionListener(game);

  		game.addKeyListener(game);
  		game.setFocusable(true);
  		Thread t = new Thread(game);
  		t.start();

 }

 	//Declaring ball, paddle, bricks

 		public void paint(Graphics g) {

 			super.paint(g);
 			Image img1 = Toolkit.getDefaultToolkit().getImage("g.jpg");
  			g.drawImage(img1, -100, -500, this);
  			g.setColor(Color.blue);
  			g.fillOval(Ball.x, Ball.y, Ball.width, Ball.height);
  			g.setColor(Color.green);
  			g.fill3DRect(Bat.x, Bat.y, Bat.width, Bat.height, true);
  			Image img2 = Toolkit.getDefaultToolkit().getImage("down.jpg");
  			g.drawImage(img2, 20, 251, this);

  	//g.fillRect(0, 251, 450, 200);
  		g.setColor(Color.red);
  		g.drawRect(0, 0, 500, 470);
  		g.drawString("Score: "+score, 10, 10);
  			for (int i = 0; i < Brick.length; i++) {
  			 	if (Brick[i] != null) {
    			g.fill3DRect(Brick[i].x, Brick[i].y, Brick[i].width,
      			Brick[i].height, true);
   			}
  	}

  			if (ballFallDown == true || bricksOver == true) {
   				Font f = new Font("Arial", Font.BOLD, 20);
   				g.setFont(f);
   				g.drawString(status, 150, 250);
   				g.drawString("Score: "+score, 200, 200);
   				ballFallDown = false;
   				bricksOver = false;
  		}

 }

 		//Games's loop

 		//======

		int mvex = -1;
 		int mvey = -1;

	 	boolean ballFallDown = false;
 		boolean bricksOver = false;

 		int count = 0;
 		String status;
 		public void run() {


  	//Creation of bricks
  		for (int i = 0; i < Brick.length; i++) {
   			Brick[i] = new Rectangle(brckx, brcky, 30, 10);
   			if (i == 5) {

    		brckx = 70;
    		brcky = 62;

 			}

   			if (i == 9) {

    		brckx = 100;
    		brcky = 74;

   			}

   			brckx += 31;

  			}



  		//Ball reverses
		//ballFallDown
  			while (true) {
		//If if if
   			for (int i = 0; i < Brick.length; i++) {
    		if (Brick[i] != null) {
     		if (Brick[i].intersects(Ball)) {
      		Brick[i] = null;
      		score=score+5;

      	// movex = -movex;
      		mvey = -mvey;
      		count++;
     		}
    	}
   }

   //==============================================================

   			if (count == Brick.length) {//Checking
    		bricksOver = true;
    		status = "NANALO KA NA PO";
    		repaint();
   	}
   //=============================================================
   			repaint();
   			Ball.x += mvex;
   			Ball.y += mvey;

   		if (left == true) {

    		Bat.x -= 3;
    		right = false;
   		}
  		if (right == true) {
    		Bat.x += 3;
    		left = false;
   		}
   		if (Bat.x <= 4) {
    		Bat.x = 4;
   		} else if (Bat.x >= 460) {
    		Bat.x = 460;
   		}
  	//Ball reverses when strikes the bat
   		if (Ball.intersects(Bat)) {
    		mvey = -mvey;
   		}
   //=============================================
   //Ball reverses when touches left and right boundary

   		if (Ball.x <= 0 || Ball.x + Ball.height >= 500) {
    		mvex = -mvex;
   	}
   		if (Ball.y <= 0) {
    		mvey = -mvey;
  	}
   		if (Ball.y >= 420) {// when ball falls below bat game is over...
    		ballFallDown = true;
    		status = "NATALO KA PO";

    		repaint();
		//System.out.print("game");
   	}
   		try {
    		Thread.sleep(10);
   		} catch (Exception ex) {
   	}

  }

 }

 //End of loop

 		//Keyevents
 		@Override
 		public void keyPressed(KeyEvent e) {
  		int keyCode = e.getKeyCode();
  		if (keyCode == KeyEvent.VK_LEFT) {
   		left = true;
   		//System.out.print("left");
  	}

  	if (keyCode == KeyEvent.VK_RIGHT) {
   		right = true;
   	//System.out.print("right");
  	}
 }

 	@Override
 		public void keyReleased(KeyEvent e) {
  		int keyCode = e.getKeyCode();
  		if (keyCode == KeyEvent.VK_LEFT) {
   		left = false;
  	}

  		if (keyCode == KeyEvent.VK_RIGHT) {
   		right = false;
  	}
 }

 	@Override
 		public void keyTyped(KeyEvent arg0) {

 	}

 	@Override
 		public void actionPerformed(ActionEvent e) {
  		String str = e.getActionCommand();
  		if (str.equals("Restart")) {
  		score = 0;
		System.out.print("hi");
   		this.restart();
	}
 }

	 	public void restart() {

  			requestFocus(true);
  		//....................
  		//Variables declaration for ball
    		bllx = 160;
  			blly = 218;


  		//Variables declaration for bat
  			btx = 170;
    		bty = 250;


  		//Variables declaration for brick
  			brckx = 70;
  			brcky = 50;

  		//Declaring ball, paddle, bricks
  			Ball = new Rectangle(bllx, blly, 5, 5);
  			Bat = new Rectangle(btx, bty, 40, 5);
  		// Rectangle Brick;
  			Brick = new Rectangle[12];

  		mvex = -1;
  		mvey = -1;
  		ballFallDown = false;
  		bricksOver = false;
  		count = 0;
  		status = null;

  	//Creation of bricks again

  		for (int i = 0; i < Brick.length; i++) {
   			Brick[i] = new Rectangle(brckx, brcky, 30, 10);
   			if (i == 5) {
    		brckx = 70;
    		brcky = 62;
   		}
   			if (i == 9) {
    		brckx = 100;
    		brcky = 74;
   		}
   			brckx += 31;
	  	}
  		repaint();
 	}
}
