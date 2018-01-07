package harrypotter.view;

import java.awt.Color;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class SecondTaskReview extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image doubleBuffering;
	private Graphics doubleBufferGraphics;
	
	private static final String voiceName = "kevin16";

	private Parchment parchment = new Parchment("PARCHMENT 12_00000.PNG", 1754, 780, 5, 5);
//	private Font harryFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("ParryHotter.ttf")))
//			.deriveFont(Font.CENTER_BASELINE, 25);
	private Parchment round1 = new Parchment("round2_00000.PNG", 830, 466, 5, 7);

	private JButton next;

	private Image mapDesign;
	
	private boolean onTalk= false;
	
	private int count=0;
	private Player dumbledoor= new Player(
			"dumbledoor talking11_00000.png", 778, 440, 9,22,
			"Gryffindor");


	public SecondTaskReview() throws IOException, FontFormatException {

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
		
		try {
			Thread.sleep(60);
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
						g.drawImage((new BufferedImageLoader()).loadImage("Task 2.png"), 453, 46, 400, 100, this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						g.drawImage((new BufferedImageLoader()).loadImage("task 2 review.png"), 142, 136, 800, 450,
								this);
					} catch (IOException e) {
					
						e.printStackTrace();
					}
					
//					try {
//						g.drawImage((new BufferedImageLoader()).loadImage("wizards traits task 1.png"), 148, 277, 800,
//								200, this);
//					} catch (IOException e) {
						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					dumbledoor.update();
					g.drawImage(dumbledoor.getSprite(), 867, 199, 400, 304,
							null);


					getNext().setBounds(1066, 494, 150, 100);
					
					
				if(!onTalk &&count==20){
					speak("The aim of this task is to reach your tresure cell");
		              speak("but be aware of the merpeople ");
						speak("as they will attack you as soon as you come near them ");
						onTalk=true;
				}
				count++;

				}

			}
			repaint();

			super.paintComponents(g);
		}
	}
	// ////////////////////////////////TODO DEMO/////////////

	public static void main(String[] args) throws IOException, FontFormatException {
		SecondTaskReview pp = new SecondTaskReview();
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

	public JButton getNext() {
		return next;
	}

	public void setNext(JButton next) {
		this.next = next;
	}

}
