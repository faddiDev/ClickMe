import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

class ClickMe extends JFrame implements MouseListener, Runnable {
	
	private static JPanel mypanel = new JPanel();
	private static JTextField count = new JTextField();
	private static JTextField steady = new JTextField();
	private static JTextField score = new JTextField();
	private static JLabel go = new JLabel("GO!!!");
	
	private int scores = 0;
	private static JLabel picLabel = null;
	private BufferedImage image;
	
	ClickMe() {
		super("Click Me Game");
		setResizable(false);
		setSize(300,300);
		mypanel.setSize(200,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		mypanel.setLayout(null);
		
		addMouseListener(this);
		
		count.setBounds(10,220,125,25);
		steady.setBounds(10,190,125,25);
		score.setBounds(140,190,125,25);
		go.setBounds(50,50,125,50);
		
		count.setEditable(false);
		steady.setEditable(false);
		score.setEditable(false);
		
		try {
			BufferedImage myPicture = ImageIO.read(new File("DSC1.jpg"));
			picLabel = new JLabel(new ImageIcon(myPicture));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		picLabel.setBounds(50,10,200,200);
		
		mypanel.add(picLabel);
		
		mypanel.add(count);
		mypanel.add(steady);
		mypanel.add(score);
		mypanel.add(go);
		
		score.setText("Score : ");
		
		setLocationRelativeTo(null);
		getContentPane().add(mypanel);
		
		setVisible(true);
		
	}
	
	public void mouseClicked(MouseEvent env) {
		//
	}
	
	public void mouseEntered(MouseEvent env) {
		steady.setText("HIT ME...!!!!!");
	}
	
	public void mouseExited(MouseEvent env) {
		steady.setText("Hit Me Please!");
	}
	
	public void mousePressed(MouseEvent env) {
		scores++;
		score.setText("Score : " +Integer.toString(scores));
		
		String[] fileNames = {"DSC2.jpg", "DSC3.jpg", "DSC4.jpg", "DSC5.jpg"};
		int score = 10;
		
		for(int i=0;i<4;i++) {
			if(scores == score) {
				try {
					mypanel.remove(picLabel);
					BufferedImage myPic = ImageIO.read(new File(fileNames[i]));
					picLabel = new JLabel(new ImageIcon(myPic));
					picLabel.setBounds(50,10,200,200);
					mypanel.add(picLabel);
					mypanel.revalidate();
					mypanel.repaint();
				} catch(Exception e) {}
			}
			score +=10;
		}
		
	}
	
	public void mouseReleased(MouseEvent env) {
		//
	}
	
	public void run() {
		try {
			for(int time = 10; time >= 0; --time) {
				Thread.sleep(1000);
				count.setText("Waktu 00:00:0" + Integer.toString(time));
			}
			JOptionPane.showMessageDialog(null, "WAKTU HABIS, SCORE ANDA : " + scores);
		} catch(InterruptedException ex) {}
	}
	
	public static void main(String[] args) {
		ClickMe cm = new ClickMe();
		Thread thread = new Thread(cm);
		thread.start();
	} 	
}