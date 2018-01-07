package harrypotter.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class GameOver extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image doubleBuffering;
	private Graphics doubleBufferGraphics;

	private BufferedImage gameOver;

	private Image mapDesign;
	private Parchment parchment1 = new Parchment("gameover.PNG", 830, 466, 5, 7);
	private Parchment parchment2 = new Parchment("Sprite Sheet Open Frame.PNG", 407, 719, 3, 6);
	private JButton playAgain;
	private JButton exit;

	public GameOver() throws IOException {

		ImageIcon a = new ImageIcon("Choose Background.gif");
		mapDesign = a.getImage();
		setLayout(null);
		playsound("Gameover.wav");

		setPlayAgain(new JButton(new ImageIcon("new GameButton.png")));
		getPlayAgain().setBounds(560, 100, 247, 102);
		getPlayAgain().setOpaque(false);
		getPlayAgain().setRolloverIcon(new ImageIcon("new GameButton1.png"));
		getPlayAgain().setBackground(Color.red);
		getPlayAgain().setBorderPainted(false);
		getPlayAgain().setContentAreaFilled(false);
		getPlayAgain().setVisible(false);
		this.add(getPlayAgain());
		setExit(new JButton(new ImageIcon("Quit.png")));
		getExit().setBackground(Color.RED);
		getExit().setBorderPainted(false);
		getParchment2().getFrames().remove(13);
		getParchment2().getFrames().remove(13);
		getParchment2().getFrames().remove(13);
		getParchment2().getFrames().remove(13);
		getParchment2().getFrames().remove(13);
		getExit().setBounds(560, 460, 247, 102);
		JPanel p = new JPanel();
		p.setBounds(100, 100, 500, 500);
		getExit().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		add(getExit());
		getExit().setVisible(false);
		p.setOpaque(false);
		add(p);
		getPlayAgain().setVisible(false);
		p.setDoubleBuffered(false);
		getExit().setOpaque(false);
		getExit().setDoubleBuffered(false);

		getExit().setContentAreaFilled(false);

		getExit().setRolloverIcon(new ImageIcon("Quit1.png"));

		JButton exit = new JButton((new ImageIcon("exit icon.png")));
		exit.setRolloverIcon((new ImageIcon("exit1 icon.png")));
		exit.setBounds(1300, 9, 50, 50);
		exit.setOpaque(false);
		exit.setBorderPainted(false);
		exit.setBackground(Color.red);
		exit.setContentAreaFilled(false);
		exit.setFocusable(false);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);

			}
		});
		add(exit);

	}

	public void paint(Graphics g) {
		doubleBuffering = createImage(getWidth(), getHeight());
		doubleBufferGraphics = doubleBuffering.getGraphics();
		paintComponents(doubleBufferGraphics);
		g.drawImage(doubleBuffering, 0, 0, this);
	}

	@Override
	public void paintComponents(Graphics g) {
		g.drawImage(mapDesign, -290, -120, 1680, 1070, this);

		try {
			parchment1.updateOnce();
		} catch (IndexOutOfBoundsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		g.drawImage(getParchment1().getSprite(), 0, 0, 1380, 780, this);
		try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (parchment1.isFinished()) {
			try {
				getParchment2().updateOnce();

			} catch (IndexOutOfBoundsException | InterruptedException e1) {
				e1.printStackTrace();
			}
//			g.drawImage(getParchment2().getSprite(), 500, -205, 420, 1200, this);
			if (!getParchment2().isFinished()) {

			} else{
				
			
				
				
				
				
			}
			

		}

		super.paintComponents(g);
		repaint();
		validate();
	}

	public BufferedImage getGameOver() {
		return gameOver;
	}

	public void setGameOver(BufferedImage gameOver) {
		this.gameOver = gameOver;
	}

	public Parchment getParchment1() {
		return parchment1;
	}

	public void setParchment1(Parchment parchment) {
		this.parchment1 = parchment;
	}

	public static void main(String[] args) throws IOException {
		JFrame f = new JFrame();
		f.setLayout(null);
		f.setUndecorated(true);
		f.setVisible(true);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameOver pp = new GameOver();
		pp.setVisible(true);
		pp.setBounds(0, 0, 1380, 780);
		// pp.setBackground(Color.BLACK);
		f.add(pp);
		f.repaint();

	}

	public Parchment getParchment2() {
		return parchment2;
	}

	public void setParchment2(Parchment parchment2) {
		this.parchment2 = parchment2;
	}

	public JButton getPlayAgain() {
		return playAgain;
	}

	public void setPlayAgain(JButton playAgain) {
		this.playAgain = playAgain;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}
	public void playsound(String filename) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}

}
