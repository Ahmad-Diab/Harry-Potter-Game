package harrypotter.view;

import harrypotter.model.character.GryffindorWizard;

import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.tournament.Task;
import harrypotter.model.tournament.Tournament;

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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class ParticipantPanel extends JPanel  {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Player> selectedChampions;

	private Image doubleBuffering;
	private Graphics doubleBufferGraphics;

	private Player currentChamp1;

	private JProgressBar hp1 = new JProgressBar();
	private JProgressBar ip1 = new JProgressBar();
	private JProgressBar hp2 = new JProgressBar();
	private JProgressBar ip2 = new JProgressBar();
	private JProgressBar hp3 = new JProgressBar();
	private JProgressBar ip3 = new JProgressBar();
	private JProgressBar hp4 = new JProgressBar();
	private JProgressBar ip4 = new JProgressBar();

	private Player currentChamp2;
	private Player currentChamp3;
	private Player currentChamp4;

	private Parchment parchment = new Parchment("PARCHMENT 12_00000.PNG", 1754,
			780, 5, 5);
	private Font harryFont = Font.createFont(Font.TRUETYPE_FONT,
			new FileInputStream(new File("ParryHotter.ttf"))).deriveFont(
			Font.CENTER_BASELINE, 25);
	private JButton beginTournment;

	private Image mapDesign;
	
	private boolean onTask1;
//	Player cedricMapIdle = new Player(mapSprite, 68, 58,3, 6,"");
	public ParticipantPanel(ArrayList<Player> selected, Task task)
			throws IOException, FontFormatException {

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
				
				JOptionPane.showMessageDialog(null,"The game will be closed","QUIT",0);
				System.exit(0);

			}
		});
		add(exit);
		ImageIcon a = new ImageIcon("Choose Background.gif");
		mapDesign = a.getImage();
		this.setSelectedChampions(selected);
		setLayout(null);
		for (int i = 0; i < selected.size(); i++) {
			if (selected.get(i).getHouse().equals("Gryffindor")) {
				currentChamp1 = getSelectedChampions().get(i);
				break;
			}
		}
		for (int i = 0; i < task.getChampions().size(); i++) {
			if (((Wizard) task.getChampions().get(i)) instanceof GryffindorWizard) {
				UIManager.put("ProgressBar.selectionBackground", Color.black);
				UIManager.put("ProgressBar.selectionForeground", Color.white);
				UIManager.put("ProgressBar.foreground", Color.GREEN);
				hp1 = new JProgressBar(0,
						((Wizard) task.getChampions().get(i)).getDefaultHp());
				UIManager.put("ProgressBar.selectionBackground", Color.black);
				UIManager.put("ProgressBar.selectionForeground", Color.white);
				UIManager.put("ProgressBar.foreground", Color.BLUE);

				ip1 = new JProgressBar(0,
						((Wizard) task.getChampions().get(i)).getDefaultIp());

				hp1.setStringPainted(true);
				ip1.setStringPainted(true);

				hp1.setValue(((Wizard) task.getChampions().get(i)).getHp());
				hp1.setString("HP: "
						+ (((Wizard) task.getChampions().get(i)).getHp()));
				ip1.setValue(((Wizard) task.getChampions().get(i)).getIp());
				ip1.setString("IP: "
						+ (((Wizard) task.getChampions().get(i)).getIp()));

				hp1.setBounds(292, 515, 160, 20);
				ip1.setBounds(292, 564, 160, 20);

				add(hp1);
				add(ip1);
			}
			if (((Wizard) task.getChampions().get(i)) instanceof HufflepuffWizard) {
				UIManager.put("ProgressBar.selectionBackground", Color.black);
				UIManager.put("ProgressBar.selectionForeground", Color.white);
				UIManager.put("ProgressBar.foreground", Color.GREEN);
				hp2 = new JProgressBar(0,
						((Wizard) task.getChampions().get(i)).getDefaultHp());
				UIManager.put("ProgressBar.selectionBackground", Color.black);
				UIManager.put("ProgressBar.selectionForeground", Color.white);
				UIManager.put("ProgressBar.foreground", Color.BLUE);

				ip2 = new JProgressBar(0,
						((Wizard) task.getChampions().get(i)).getDefaultIp());

				hp2.setStringPainted(true);
				ip2.setStringPainted(true);

				hp2.setValue(((Wizard) task.getChampions().get(i)).getHp());
				hp2.setString("HP: "
						+ (((Wizard) task.getChampions().get(i)).getHp()));
				ip2.setValue(((Wizard) task.getChampions().get(i)).getIp());
				ip2.setString("IP: "
						+ (((Wizard) task.getChampions().get(i)).getIp()));
				hp2.setBounds(542, 515, 160, 20);
				ip2.setBounds(542, 564, 160, 20);

				add(hp2);
				add(ip2);
			}
			if (((Wizard) task.getChampions().get(i)) instanceof RavenclawWizard) {
				UIManager.put("ProgressBar.selectionBackground", Color.black);
				UIManager.put("ProgressBar.selectionForeground", Color.white);
				UIManager.put("ProgressBar.foreground", Color.GREEN);
				hp3 = new JProgressBar(0,
						((Wizard) task.getChampions().get(i)).getDefaultHp());
				UIManager.put("ProgressBar.selectionBackground", Color.black);
				UIManager.put("ProgressBar.selectionForeground", Color.white);
				UIManager.put("ProgressBar.foreground", Color.BLUE);

				ip3 = new JProgressBar(0,
						((Wizard) task.getChampions().get(i)).getDefaultIp());

				hp3.setStringPainted(true);
				ip3.setStringPainted(true);

				hp3.setValue(((Wizard) task.getChampions().get(i)).getHp());
				hp3.setString("HP: "
						+ (((Wizard) task.getChampions().get(i)).getHp()));
				ip3.setValue(((Wizard) task.getChampions().get(i)).getIp());
				ip3.setString("IP: "
						+ (((Wizard) task.getChampions().get(i)).getIp()));
				hp3.setBounds(806, 515, 160, 20);
				ip3.setBounds(802, 564, 160, 20);
				add(hp3);
				add(ip3);

			}
			if (((Wizard) task.getChampions().get(i)) instanceof SlytherinWizard) {
				UIManager.put("ProgressBar.selectionBackground", Color.black);
				UIManager.put("ProgressBar.selectionForeground", Color.white);
				UIManager.put("ProgressBar.foreground", Color.GREEN);
				hp4 = new JProgressBar(0,
						((Wizard) task.getChampions().get(i)).getDefaultHp());
				UIManager.put("ProgressBar.selectionBackground", Color.black);
				UIManager.put("ProgressBar.selectionForeground", Color.white);
				UIManager.put("ProgressBar.foreground", Color.BLUE);

				ip4 = new JProgressBar(0,
						((Wizard) task.getChampions().get(i)).getDefaultIp());

				hp4.setStringPainted(true);
				ip4.setStringPainted(true);

				hp4.setValue(((Wizard) task.getChampions().get(i)).getHp());
				hp4.setString("HP: "
						+ (((Wizard) task.getChampions().get(i)).getHp()));
				UIManager.put("ProgressBar.selectionBackground", Color.black);
				UIManager.put("ProgressBar.selectionForeground", Color.white);
				UIManager.put("ProgressBar.foreground", Color.BLUE);
				ip4.setValue(((Wizard) task.getChampions().get(i)).getIp());
				ip4.setString("IP: "
						+ (((Wizard) task.getChampions().get(i)).getIp()));
				hp4.setBounds(1054, 515, 160, 20);
				ip4.setBounds(1054, 564, 160, 20);
				add(hp4);
				add(ip4);
			}
		}

		hp1.setVisible(false);
		ip1.setVisible(false);
		hp2.setVisible(false);
		ip2.setVisible(false);
		hp3.setVisible(false);
		ip3.setVisible(false);
		hp4.setVisible(false);
		ip4.setVisible(false);
		for (int i = 0; i < selected.size(); i++) {
			if (selected.get(i).getHouse().equals("Hufflepuff")) {
				currentChamp2 = getSelectedChampions().get(i);
				break;
			}
		}
		for (int i = 0; i < selected.size(); i++) {
			if (selected.get(i).getHouse().equals("Ravenclaw")) {
				currentChamp3 = getSelectedChampions().get(i);
				break;
			}
		}
		for (int i = 0; i < selected.size(); i++) {
			if (selected.get(i).getHouse().equals("Slytherin")) {
				currentChamp4 = getSelectedChampions().get(i);
				break;
			}
		}

		setBeginTournment(new JButton(new ImageIcon("lets start.png")));
		add(getBeginTournment());
		getBeginTournment().setOpaque(false);
		getBeginTournment().setBackground(Color.red);
		getBeginTournment().setBorderPainted(false);
		getBeginTournment().setRolloverIcon(new ImageIcon("lets start1.png"));
		getBeginTournment().setContentAreaFilled(false);
		getBeginTournment().setVisible(false);
		
		repaint();
		validate();

	}

	public void paint(Graphics g) {
		doubleBuffering = createImage(getWidth(), getHeight());
		doubleBufferGraphics = doubleBuffering.getGraphics();
		paintComponents(doubleBufferGraphics);
		g.drawImage(doubleBuffering, 0, 0, this);
	}

	@Override
	public void paintComponents(Graphics g) {
		// //////////////////////////////////
		// System.out.println("in");
		g.drawImage(mapDesign, -290, -120, 1680, 1070, this);
		
		
	
		if (getParchment() != null) {
			try {
				getParchment().updateOnce();

			} catch (IndexOutOfBoundsException | InterruptedException e1) {
				e1.printStackTrace();
			}
			g.drawImage(getParchment().getSprite(), -220, -50, 1750, 770, this);
			if (getParchment().isFinished()) {

				// TODO THE BEGEINING
				g.setFont(harryFont);

				// currentChamp1.update();

				currentChamp1.update();

				g.drawImage(currentChamp1.getSprite(), 200, 156, 350, 254, this);
				g.drawString(currentChamp1.getUserName(), 289, 444);
				currentChamp2.update();

				g.drawImage(currentChamp2.getSprite(), 443, 132, 380, 284, this);
				g.drawString(currentChamp2.getUserName(), 546, 444);
				currentChamp3.update();

				g.drawImage(currentChamp3.getSprite(), 686, 132, 380, 284, this);
				g.drawString(currentChamp3.getUserName(), 809, 444);
getBeginTournment().setVisible(true);
				hp1.setVisible(true);
				ip1.setVisible(true);
				hp2.setVisible(true);
				ip2.setVisible(true);
				hp3.setVisible(true);
				ip3.setVisible(true);
				hp4.setVisible(true);
				ip4.setVisible(true);
if(currentChamp4!=null){
				currentChamp4.update();

				g.drawImage(currentChamp4.getSprite(), 933, 150, 380, 284, this);
				g.drawString(currentChamp4.getUserName(), 1095, 449);
}
				// TODO LABEL
				try {
					g.drawImage((new BufferedImageLoader())
							.loadImage("Triwizard.png"), 209, 59, 900, 96, this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// beginTournment.setBounds(mx, my, 100, 100);

//				g.drawString("Let's Start", 1107, 663);
				g.drawString("Player Name : ", 82, 442);
				g.drawString("Health Points : ", 67, 523);
				g.drawString("Intelligence Points : ", 67, 575);

			}
//			cedricMapIdle.update();
//			g.drawImage(
//					cedricMapIdle.getSprite(),
//					mx,
//					my,
//					70, 60, this);

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

		getBeginTournment().setBounds(1047, 638, 200, 50);
		repaint();
		super.paintComponents(g);

	}

	public Parchment getParchment() {
		return parchment;
	}

	public void setParchment(Parchment parchment) {
		this.parchment = parchment;
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

		ParticipantPanel pp = new ParticipantPanel(s, t.getFirstTask());
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

	
	public ArrayList<Player> getSelectedChampions() {
		return selectedChampions;
	}

	public void setSelectedChampions(ArrayList<Player> selectedChampions) {
		this.selectedChampions = selectedChampions;
	}

	public JButton getBeginTournment() {
		return beginTournment;
	}

	public void setBeginTournment(JButton beginTournment) {
		this.beginTournment = beginTournment;
	}

	public boolean isOnTask1() {
		return onTask1;
	}

	public void setOnTask1(boolean onTask1) {
		this.onTask1 = onTask1;
	}
}
