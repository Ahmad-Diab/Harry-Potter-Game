package harrypotter.view;

import harrypotter.model.character.Champion ;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.Tournament;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SecondTaskWinnersPanel extends JPanel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image doubleBuffering;
	private Graphics doubleBufferGraphics;

	private FirstTask firstTask;
	private Player currentChamp1;
	private BufferedImage currentLogo1;
	private BufferedImage currentLogo2;
	private BufferedImage currentLogo3;
	private BufferedImage currentLogo4;
	
	private Player currentChamp2;
	private Player currentChamp3;
	private Player currentChamp4;
	private Font harryFont = Font.createFont(Font.TRUETYPE_FONT,
			new FileInputStream(new File("ParryHotter.ttf"))).deriveFont(
			Font.CENTER_BASELINE, 30);

	private Parchment parchment = new Parchment("PARCHMENT 12_00000.PNG", 1754,
			780, 5, 5);

	private Image mapDesign;

	private BufferedImage slytherinChampionIdle = new BufferedImageLoader()
			.loadImage("draco front_00000.png");
	private Player gryffindorChampion = new Player(
			"Harry Potter Standing Sprite sheet_00000.png", 680, 384, 6, 6,
			"Gryffindor");
	private Player hufflepuffChampion = new Player(
			"Cidric Stand idle_00000.png", 680, 384, 5, 11, "Hufflepuff");
	private Player ravenclawChampion = new Player("beet front_00000.png",
			1866, 1040,3, 10,"Ravenclaw");
	private Player slytherinChampion = new Player(slytherinChampionIdle, 1866,
			1040, 2, 10, "Slytherin");
	private BufferedImage gryffindorLogo=(new BufferedImageLoader()).loadImage("Gryffindor Crest.png");
    private BufferedImage hufflepuffLogo=(new BufferedImageLoader()).loadImage("Hufflepuff Crest.png");
    private BufferedImage ravenclawLogo=(new BufferedImageLoader()).loadImage("Ravenclaw Crest.png");
    private BufferedImage slytherinLogo=(new BufferedImageLoader()).loadImage("Slytherin Crest.gif");
    		
    private JButton next;
 public SecondTaskWinnersPanel(ArrayList<Champion> winners)
			throws IOException, FontFormatException {

	
		setLayout(null);

		ImageIcon a = new ImageIcon("Choose Background.gif");
		mapDesign = a.getImage();
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
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size()-1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size()-1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size()-1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size()-1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size()-1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size()-1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size()-1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size()-1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size()-1);
	
		slytherinChampion.getFrames().remove(slytherinChampion.getFrames().size()-1);
		slytherinChampion.getFrames().remove(slytherinChampion.getFrames().size()-1);
		slytherinChampion.getFrames().remove(slytherinChampion.getFrames().size()-1);
		slytherinChampion.getFrames().remove(slytherinChampion.getFrames().size()-1);
		slytherinChampion.getFrames().remove(slytherinChampion.getFrames().size()-1);
		slytherinChampion.getFrames().remove(slytherinChampion.getFrames().size()-1);
	
		setNext(new JButton());
		setNext(new JButton(new ImageIcon("next1.png")));
		add(getNext());
		getNext().setOpaque(false);
		getNext().setBackground(Color.red);
		getNext().setBorderPainted(false);
//		getNext().setRolloverIcon(new ImageIcon("next2.png"));
		getNext().setContentAreaFilled(false);
		getNext().setVisible(false);
		getNext().setBounds(1064, 612, 150, 100);

		add(next);

		for (int i = 0; i < winners.size(); i++) {
			if (i == 0) {
				if (((Wizard) winners.get(i)) instanceof GryffindorWizard) {
					currentChamp1 = gryffindorChampion;
					currentChamp1.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo1=gryffindorLogo;
				}
				if (((Wizard) winners.get(i)) instanceof HufflepuffWizard) {
					currentChamp1 = hufflepuffChampion;
					currentChamp1.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo1=hufflepuffLogo;
				}
				if (((Wizard) winners.get(i)) instanceof RavenclawWizard) {
					currentChamp1 = ravenclawChampion;
					currentChamp1.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo1=ravenclawLogo;
				}
				if (((Wizard) winners.get(i)) instanceof SlytherinWizard) {
					currentChamp1 = slytherinChampion;
					currentChamp1.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo1=slytherinLogo;
				}
			}
			if (i == 1) {
				if (((Wizard) winners.get(i)) instanceof GryffindorWizard) {
					currentChamp2 = gryffindorChampion;
					currentChamp2.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo2=gryffindorLogo;
				}
				if (((Wizard) winners.get(i)) instanceof HufflepuffWizard) {
					currentChamp2 = hufflepuffChampion;
					currentChamp2.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo2=hufflepuffLogo;
				}
				if (((Wizard) winners.get(i)) instanceof RavenclawWizard) {
					currentChamp2 = ravenclawChampion;
					currentChamp2.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo2=ravenclawLogo;
				}
				if (((Wizard) winners.get(i)) instanceof SlytherinWizard) {
					currentChamp2 = slytherinChampion;
					currentChamp2.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo2=slytherinLogo;
				}
			}
			if (i == 2) {
				if (((Wizard) winners.get(i)) instanceof GryffindorWizard) {
					currentChamp3 = gryffindorChampion;
					currentChamp3.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo3=gryffindorLogo;
				}
				if (((Wizard) winners.get(i)) instanceof HufflepuffWizard) {
					currentChamp3 = hufflepuffChampion;
					currentChamp3.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo3=hufflepuffLogo;
				}
				if (((Wizard) winners.get(i)) instanceof RavenclawWizard) {
					currentChamp3 = ravenclawChampion;
					currentChamp3.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo3=ravenclawLogo;
				}
				if (((Wizard) winners.get(i)) instanceof SlytherinWizard) {
					currentChamp3 = slytherinChampion;
					currentChamp3.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo3=slytherinLogo;
				}
			}
			if (i == 3) {
				if (((Wizard) winners.get(i)) instanceof GryffindorWizard) {
					currentChamp4 = gryffindorChampion;
					currentChamp4.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo4=gryffindorLogo;
				}
				if (((Wizard) winners.get(i)) instanceof HufflepuffWizard) {
					currentChamp4 = hufflepuffChampion;
					currentChamp4.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo4=hufflepuffLogo;
				}
				if (((Wizard) winners.get(i)) instanceof RavenclawWizard) {
					currentChamp4 = ravenclawChampion;
					currentChamp4.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo4=ravenclawLogo;
				}
				if (((Wizard) winners.get(i)) instanceof SlytherinWizard) {
					currentChamp4 = slytherinChampion;
					currentChamp4.setUserName(((Wizard) winners.get(i))
							.getName());
					currentLogo4=slytherinLogo;
				}
			}
		}

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

		if (getParchment() != null) {
			try {
				getParchment().updateOnce();

			} catch (IndexOutOfBoundsException | InterruptedException e1) {
				e1.printStackTrace();
			}
			g.drawImage(getParchment().getSprite(), -220, -50, 1750, 770, this);
			if (getParchment().isFinished()) {
				getNext().setVisible(true);
				// TODO WINNERS
				try {
					g.drawImage((new BufferedImageLoader())
							.loadImage("Second task winners.png"), 306, 58, 900,
							96, this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.setFont(harryFont);

				if (currentChamp1 != null) {

					currentChamp1.update();

					g.drawImage(currentChamp1.getSprite(), 200, 206, 350, 254,
							this);
					g.drawString(currentChamp1.getUserName(), 289, 494);
					g.drawImage(currentLogo1, 420, 291,100, 131, this);
				}
				if (currentChamp2 != null) {

					currentChamp2.update();

					g.drawImage(currentChamp2.getSprite(), 443, 202, 380, 284,
							this);
					g.drawString(currentChamp2.getUserName(), 546, 494);
					g.drawImage(currentLogo2, 671,296,100, 111, this);

				}
				if (currentChamp3 != null) {

					currentChamp3.update();

					g.drawImage(currentChamp3.getSprite(), 686, 172, 380, 284,
							this);
					g.drawString(currentChamp3.getUserName(), 809, 494);
					g.drawImage(currentLogo3, 916,291,100, 114, this);


				}
				if (currentChamp4 != null) {

					currentChamp4.update();

					g.drawImage(currentChamp4.getSprite(), 933, 200, 380, 284,
							this);
					g.drawString(currentChamp4.getUserName(), 1095, 499);
					g.drawImage(currentLogo4, 1173,276,100, 114, this);

				}
				g.drawString("Player Name : ", 82, 492);

			}

			repaint();
		

		
			if (!getParchment().isFinished()) {
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		repaint();
		super.paintComponents(g);

	}

	public Image getDoubleBuffering() {
		return doubleBuffering;
	}

	public void setDoubleBuffering(Image doubleBuffering) {
		this.doubleBuffering = doubleBuffering;
	}

	public Graphics getDoubleBufferGraphics() {
		return doubleBufferGraphics;
	}

	public void setDoubleBufferGraphics(Graphics doubleBufferGraphics) {
		this.doubleBufferGraphics = doubleBufferGraphics;
	}

	public FirstTask getFirstTask() {
		return firstTask;
	}

	public void setFirstTask(FirstTask firstTask) {
		this.firstTask = firstTask;
	}

	public Player getCurrentChamp2() {
		return currentChamp2;
	}

	public void setCurrentChamp2(Player currentChamp2) {
		this.currentChamp2 = currentChamp2;
	}

	public Player getCurrentChamp1() {
		return currentChamp1;
	}

	public void setCurrentChamp1(Player currentChamp1) {
		this.currentChamp1 = currentChamp1;
	}

	public Player getCurrentChamp3() {
		return currentChamp3;
	}

	public void setCurrentChamp3(Player currentChamp3) {
		this.currentChamp3 = currentChamp3;
	}

	public Player getCurrentChamp4() {
		return currentChamp4;
	}

	public void setCurrentChamp4(Player currentChamp4) {
		this.currentChamp4 = currentChamp4;
	}

	public Parchment getParchment() {
		return parchment;
	}

	public void setParchment(Parchment parchment) {
		this.parchment = parchment;
	}

	public Image getMapDesign() {
		return mapDesign;
	}

	public void setMapDesign(Image mapDesign) {
		this.mapDesign = mapDesign;
	}
	public static void main(String[] args) throws IOException,
			FontFormatException {
		Player gryffindorChampion = new Player(
				"Harry Potter Standing Sprite sheet_00000.png", 680, 384, 6, 6,
				"Gryffindor");
		Player hufflepuffChampion = new Player("Cidric Stand idle_00000.png",
				680, 384, 5, 11, "Hufflepuff");
		Player ravenclawChampion = new Player("draco front info_00000.png",
				678, 384, 6, 6, "Ravenclaw");
		Player slytherinChampion = new Player("weleya front info_00000.png",
				678, 384, 6, 6, "Slytherin");

		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		 RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s1 = new SlytherinWizard("slyth");

		ArrayList<Player> s = new ArrayList<Player>();
		gryffindorChampion.setUserName("Ahmed Diab");
		hufflepuffChampion.setUserName("Karim Nashhat");
		ravenclawChampion.setUserName("Mohamed Ihab");
		slytherinChampion.setUserName("Welya");

		s.add(gryffindorChampion);
		s.add(hufflepuffChampion);

		s.add(ravenclawChampion);
		s.add(slytherinChampion);
		Tournament t = new Tournament();
		t.getChampions().add(g);
		 t.getChampions().add(h);
		 t.getChampions().add(r);
		t.getChampions().add(s1);
		t.beginTournament();

		SecondTaskWinnersPanel pp = new SecondTaskWinnersPanel(t.getChampions());
		// pp.getSelectedChampions().add(slytherinChampion);
		// pp.getSelectedChampions().add(slytherinChampion);
		// pp.getSelectedChampions().add(slytherinChampion);
		// pp.getSelectedChampions().add(slytherinChampion);
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

	public JButton getNext() {
		return next;
	}

	public void setNext(JButton next) {
		this.next = next;
	}
}