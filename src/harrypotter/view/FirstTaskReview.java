package harrypotter.view;

import java.awt.Color;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FirstTaskReview extends JPanel implements MouseMotionListener{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/////////
//private int countAddSpell = 0;

boolean mouseOn, isDragged = false;
int x, y, mx, my;

	private Image doubleBuffering;
	private Graphics doubleBufferGraphics;
	
	private static final String voiceName = "kevin16";

	private Parchment parchment = new Parchment("PARCHMENT 12_00000.PNG", 1754, 780, 5, 5);
//	private Font harryFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("ParryHotter.ttf")))
//			.deriveFont(Font.CENTER_BASELINE, 25);
	private Parchment round1 = new Parchment("round1_00000.PNG", 830, 466, 5, 7);
	
	private Player dumbledoor= new Player(
			"dumbledoor talking11_00000.png", 778, 440, 9,22,
			"Gryffindor");

	private JButton next;

	private Image mapDesign;

	private boolean onTalk= false;
	
	private int count=0;

	public FirstTaskReview() throws IOException, FontFormatException  {
		
		///////////////////TODO DEMO/////////////

				 addMouseListener(new Mouse());
				 addMouseMotionListener(this);
				 
				//

		ImageIcon a = new ImageIcon("Choose Background.gif");
		mapDesign = a.getImage();
		setLayout(null);
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
		setNext(new JButton());
		setNext(new JButton(new ImageIcon("next.png")));
		add(getNext());
		getNext().setOpaque(false);
		getNext().setBackground(Color.red);
		getNext().setBorderPainted(false);
		getNext().setRolloverIcon(new ImageIcon("next roll.png"));
		getNext().setContentAreaFilled(false);
		getNext().setVisible(false);

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
			round1.updateOnce();
		} catch (IndexOutOfBoundsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		g.drawImage(round1.getSprite(), 100, 50, 1254, 576, this);
//		if(!parchment.isFinished()!round1.isFinished())
		try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (getParchment() != null) {
			if (round1.isFinished()) {
				try {
					getParchment().updateOnce();

				} catch (IndexOutOfBoundsException | InterruptedException e1) {
					e1.printStackTrace();
				}
				g.drawImage(getParchment().getSprite(), -220, -50, 1750, 770, this);
				if (getParchment().isFinished()) {

					// TODO THE BEGEINING
					getNext().setVisible(true);

					// ////////////////TODO DEMOO//////////////////
					try {
						g.drawImage((new BufferedImageLoader()).loadImage("Task 1.png"), 453, 46, 400, 100, this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						g.drawImage((new BufferedImageLoader()).loadImage("task 1 review.png"), 142, 168, 800, 200,
								this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						g.drawImage((new BufferedImageLoader()).loadImage("wizards traits task 1.png"), 148, 277, 800,
								200, this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dumbledoor.update();
					g.drawImage(dumbledoor.getSprite(), 867, 199, 400, 304,
							null);

					getNext().setBounds(1066, 494, 150, 100);
					if(!onTalk &&count==20){
                              speak("The aim of this task is to reach and take a dragon egg");
                              speak("but be aware of the dragon ");
                              speak(" as he will try to stop you by throwing flames at you at each turn");
		                      onTalk=true;
					}
					count++;
				}

			}
			// ////////////////TODO DEMOO//////////////////

			 if (mouseOn) {
			 // g.setColor(Color.RED);
			
//			 g.drawString("X Coordinate : " + mx + " Y Coordinate : " + my, 50,
//			 90);
			
			 }
			/////////////////////////////////////
			 
			 
			repaint();

			super.paintComponents(g);
		}
	}
	// ////////////////////////////////TODO DEMO/////////////

	public static void main(String[] args) throws IOException, FontFormatException {
		FirstTaskReview pp = new FirstTaskReview();
		JFrame f = new JFrame();
		f.setLayout(null);
		f.setVisible(true);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pp.setVisible(true);
		pp.setBounds(0, 0, 1380, 780);
		// pp.setBackground(Color.BLACK);
		f.add(pp);
		f.repaint();

	}

	public Parchment getParchment() {
		return parchment;
	}

	public void setParchment(Parchment parchment) {
		this.parchment = parchment;
	}
	
	private void speak(String toBeSaid) {
		Voice voice;
		VoiceManager vm = VoiceManager.getInstance();
		voice = vm.getVoice(voiceName);
		voice.allocate();
		voice.setDurationStretch(1f);
		voice.setVolume(100f);
		try {
			voice.speak(toBeSaid);
		} catch (Exception ee) {

		}
	}
	// ////////////////////////////////TODO DEMO/////////////
		public class Mouse extends MouseAdapter {
			@Override
			public void mousePressed(MouseEvent e) {

				super.mousePressed(e);
				int xCoodinate = e.getX();
				int yCoordinate = e.getY();
				x = xCoodinate;
				y = yCoordinate;

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				super.mouseEntered(e);
				mouseOn = true;
			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				super.mouseExited(arg0);
				mouseOn = false;
			}

		}

		@Override
		public void mouseDragged(MouseEvent e) {

			mx = e.getX();
			my = e.getY();
			isDragged = true;
			e.consume();

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mx = e.getX();
			my = e.getY();
			isDragged = false;
			e.consume();
		}

		public JButton getNext() {
			return next;
		}

		public void setNext(JButton next) {
			this.next = next;
		}

}
