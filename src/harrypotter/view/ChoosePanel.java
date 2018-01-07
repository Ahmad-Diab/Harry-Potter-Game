package harrypotter.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings({ "serial" })
public class ChoosePanel extends JPanel {
	private Image doubleBuffering;
	private Graphics doubleBufferGraphics;

	private Parchment parchment = new Parchment("Sprite Sheet Open Frame.PNG",
			407, 719, 3, 6);

	private Image background;
	private BufferedImageLoader b = new BufferedImageLoader();

	private JButton left;
	private JButton right;
	private JButton choose;

	private ArrayList<Player> wizards = new ArrayList<Player>();
	
	
	private Player gryffindorChampion = new Player(
			"Harry Potter Standing Sprite sheet_00000.png", 680, 384, 6, 6,
			"Gryffindor");
	private Player hufflepuffChampion = new Player(
			"Cidric Stand idle_00000.png", 680, 384, 5, 11, "Hufflepuff");
	private Player ravenclawChampion = new Player("beet front_00000.png",
			1866, 1040,3, 10,"Ravenclaw");
	private Player slytherinChampion = new Player("draco front_00000.png", 1866, 1040,2, 10,"Slytherin");


	private ArrayList<Image> wizardName = new ArrayList<Image>();
	private ArrayList<Player> selectedChampions = new ArrayList<Player>();
	private ArrayList<Image> selectedWizards = new ArrayList<Image>();
	private ArrayList<ArrayList<String>> selectedSpells = new ArrayList<ArrayList<String>>();
	private int currentIndexW = 0;
	private Player cWizard;
	private JTextArea enterName = new JTextArea(102, 120);

	private Image currentWizard;
	private Image playerName = b.loadImage("Player Name.png");
	private Font harryFont = Font.createFont(Font.TRUETYPE_FONT,
			new FileInputStream(new File("ParryHotter.ttf"))).deriveFont(
			Font.BOLD, 25);
	ImageIcon a;

	public ChoosePanel() throws IOException, FontFormatException {
		
		a = new ImageIcon("Choose Background.gif");
		background = a.getImage();

		getWizards().add(getGryffindorChampion());
		getWizards().add(getHufflepuffChampion());
		getWizards().add(getRavenclawChampion());
		getWizards().add(getSlytherinChampion());

		Image g = b.loadImage("GryffindorWizard logo.png");
		Image h = b.loadImage("HufflepuffWizard logo.png");
		Image r = b.loadImage("RavenclawWizard logo.png");
		Image s = b.loadImage("SlytherinWizard logo.png");
		
		getWizardName().add(g);
		getWizardName().add(h);
		getWizardName().add(r);
		getWizardName().add(s);
		
		hufflepuffChampion.getFrames().remove(hufflepuffChampion.getFrames().size()-1);
		hufflepuffChampion.getFrames().remove(hufflepuffChampion.getFrames().size()-1);
		hufflepuffChampion.getFrames().remove(hufflepuffChampion.getFrames().size()-1);
		hufflepuffChampion.getFrames().remove(hufflepuffChampion.getFrames().size()-1);
		hufflepuffChampion.getFrames().remove(hufflepuffChampion.getFrames().size()-1);
		hufflepuffChampion.getFrames().remove(hufflepuffChampion.getFrames().size()-1);
		hufflepuffChampion.getFrames().remove(hufflepuffChampion.getFrames().size()-1);
		
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
		
		getParchment().getFrames().remove(13);
		getParchment().getFrames().remove(13);
		getParchment().getFrames().remove(13);
		getParchment().getFrames().remove(13);
		getParchment().getFrames().remove(13);

		getHufflepuffChampion().getFrames().remove(34);
		getHufflepuffChampion().getFrames().remove(34);

		setLayout(null);

		getGryffindorChampion().update();
		getHufflepuffChampion().update();
		getRavenclawChampion().update();
		getSlytherinChampion().update();

		getWizards().get(getCurrentIndexW());
		setcWizard(getWizards().get(getCurrentIndexW()));
		setCurrentWizard(getWizardName().get(getCurrentIndexW()));

		JButton exit = new JButton((new ImageIcon("exit icon.png")));
		exit.setRolloverIcon((new ImageIcon("exit1 icon.png")));
		exit.setBounds(1300, 9, 50, 50);
		exit.setOpaque(false);
		exit.setBorderPainted(false);
		exit.setBackground(Color.red);
		exit.setContentAreaFilled(false);
		exit.setFocusable(true);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);

			}
		});
		add(exit);

		setRight(new JButton(new ImageIcon("next.png")));
		getRight().setBorderPainted(false);
		getRight().setBounds(760, 657, 72, 72);
		this.add(getRight());
		getRight().setVisible(false);
		getRight().setRolloverIcon(new ImageIcon("next roll.png"));
		getRight().setBackground(Color.RED);
		getRight().setOpaque(false);
		getRight().setDoubleBuffered(false);
		getRight().setContentAreaFilled(false);
		getRight().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getCurrentIndexW() == getWizards().size() - 1)
					setCurrentIndexW(0);
				else
					setCurrentIndexW(getCurrentIndexW() + 1);
				setcWizard(getWizards().get(getCurrentIndexW()));
				setCurrentWizard(getWizardName().get(getCurrentIndexW()));

			}
		});

		setLeft(new JButton(new ImageIcon("left1.png")));
		getLeft().setBounds(542, 657, 72, 72);
		add(getLeft());
		getLeft().setBackground(Color.RED);
		getLeft().setVisible(false);
		getLeft().setOpaque(false);
		getLeft().setBorderPainted(false);
		getLeft().setDoubleBuffered(false);
		getLeft().setContentAreaFilled(false);
		getLeft().setRolloverIcon(new ImageIcon("left1 roll.png"));
		
		getLeft().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getCurrentIndexW() == 0)
					setCurrentIndexW(getWizards().size() - 1);
				else
					setCurrentIndexW(getCurrentIndexW() - 1);
				setcWizard(getWizards().get(getCurrentIndexW()));
				setCurrentWizard(getWizardName().get(getCurrentIndexW()));
			}
		});

		setChoose(new JButton(new ImageIcon("select.png")));
		getChoose().setBounds(620, 657, 122, 72);
		this.add(getChoose());
		getChoose().setVisible(false);
		getChoose().setBackground(Color.ORANGE);
		getChoose().setOpaque(false);
		getChoose().setBorderPainted(false);
		getChoose().setContentAreaFilled(false);
		getChoose().setRolloverIcon(new ImageIcon("select roll.png"));

		getEnterName().setBounds(595, 600, 210, 50);
		getEnterName().setCaretColor(Color.red);
		enterName.setFocusable(true);

		getEnterName().setFont(harryFont);
		getEnterName().getFont().deriveFont(Font.BOLD);
		getEnterName().setVisible(false);

		add(getEnterName());

		repaint();
		validate();

	}

	public void paint(Graphics g) {
		doubleBuffering = createImage(getWidth(), getHeight());
		doubleBufferGraphics = doubleBuffering.getGraphics();
		paintComponents(doubleBufferGraphics);
		g.drawImage(doubleBuffering, 0, 0, this);
	}

	public void paintComponents(Graphics g) {
		g.drawImage(background, 0, 0, 1380, 780, this);
		if (getParchment() != null) {
			try {
				getParchment().updateOnce();

			} catch (IndexOutOfBoundsException | InterruptedException e1) {
				e1.printStackTrace();
			}
			g.drawImage(getParchment().getSprite(), 500, -205, 420, 1200, this);
			if (!getParchment().isFinished()) {

				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
			
			if (getParchment().isFinished()) {
				getRight().setVisible(true);
				getLeft().setVisible(true);
				getChoose().setVisible(true);
				getEnterName().setVisible(true);
				try {
					g.drawImage(b.loadImage("Choose a Champion.png"), 517, -10,
							350, 180, this);
				} catch (IOException e) {

					e.printStackTrace();
				}
				if (getCurrentWizard() != null)
					g.drawImage(getCurrentWizard(), 560, 104, 290, 90, null);

				if (getcWizard() != null) {

					getcWizard().update();
					if(getcWizard().getHouse().equals("Ravenclaw")||getcWizard().getHouse().equals("Slytherin")){
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						g.drawImage(getcWizard().getSprite(), 494, 185, 400, 304,
								null);
					}
					
					else
					g.drawImage(getcWizard().getSprite(), 454, 155, 500, 404,
							null);
				}

				g.drawImage(playerName, 559, 524, 200, 104, null);

			}

		}

		repaint();
		super.paintComponents(g);
	}

	public Parchment getParchment() {
		return parchment;
	}

	public void setParchment(Parchment parchment) {
		this.parchment = parchment;
	}

	public JButton getLeft() {
		return left;
	}

	public void setLeft(JButton left) {
		this.left = left;
	}

	public JButton getRight() {
		return right;
	}

	public void setRight(JButton right) {
		this.right = right;
	}

	public JButton getChoose() {
		return choose;
	}

	public void setChoose(JButton choose) {
		this.choose = choose;
	}

	public ArrayList<Player> getWizards() {
		return wizards;
	}

	public void setWizards(ArrayList<Player> wizards) {
		this.wizards = wizards;
	}

	public Player getGryffindorChampion() {
		return gryffindorChampion;
	}

	public void setGryffindorChampion(Player gryffindorChampion) {
		this.gryffindorChampion = gryffindorChampion;
	}

	public Player getHufflepuffChampion() {
		return hufflepuffChampion;
	}

	public void setHufflepuffChampion(Player hufflepuffChampion) {
		this.hufflepuffChampion = hufflepuffChampion;
	}

	public Player getRavenclawChampion() {
		return ravenclawChampion;
	}

	public void setRavenclawChampion(Player ravenclawChampion) {
		this.ravenclawChampion = ravenclawChampion;
	}

	public Player getSlytherinChampion() {
		return slytherinChampion;
	}

	public void setSlytherinChampion(Player slytherinChampion) {
		this.slytherinChampion = slytherinChampion;
	}

	public ArrayList<Image> getWizardName() {
		return wizardName;
	}

	public void setWizardName(ArrayList<Image> wizardName) {
		this.wizardName = wizardName;
	}

	public ArrayList<Player> getSelectedChampions() {
		return selectedChampions;
	}

	public void setSelectedChampions(ArrayList<Player> selectedChampions) {
		this.selectedChampions = selectedChampions;
	}

	public ArrayList<Image> getSelectedWizards() {
		return selectedWizards;
	}

	public void setSelectedWizards(ArrayList<Image> selectedWizards) {
		this.selectedWizards = selectedWizards;
	}

	public ArrayList<ArrayList<String>> getSelectedSpells() {
		return selectedSpells;
	}

	public void setSelectedSpells(ArrayList<ArrayList<String>> selectedSpells) {
		this.selectedSpells = selectedSpells;
	}

	public Player getcWizard() {
		return cWizard;
	}

	public void setcWizard(Player cWizard) {
		this.cWizard = cWizard;
	}

	public Image getCurrentWizard() {
		return currentWizard;
	}

	public void setCurrentWizard(Image currentWizard) {
		this.currentWizard = currentWizard;
	}

	public int getCurrentIndexW() {
		return currentIndexW;
	}

	public void setCurrentIndexW(int currentIndexW) {
		this.currentIndexW = currentIndexW;
	}

	public JTextArea getEnterName() {
		return enterName;
	}

	public void setEnterName(JTextArea enterName) {
		this.enterName = enterName;
	}

}
