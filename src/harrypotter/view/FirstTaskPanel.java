package harrypotter.view;

import harrypotter.exceptions.InCooldownException;

import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.exceptions.OutOfRangeException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.FinalizeActionListener;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.MoveListener;
import harrypotter.model.tournament.SpellListener;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.ObstacleCell;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

@SuppressWarnings("serial")
public class FirstTaskPanel extends JPanel implements MoveListener, FinalizeActionListener, SpellListener {

	private BufferedImage[][] cellsImage = new BufferedImage[10][10];

	// ////////////////////////////
	private static final String voiceName = "kevin16";
	private Image doubleBuffering;
	private Graphics doubleBufferGraphics;
	private BufferedImageLoader b = new BufferedImageLoader();
	private Image ravenTrait;

	private FirstTask task;
	private BufferedImage mapDesign1 = (new BufferedImageLoader()).loadImage("Green Screen 3D Flip Book Animation.png");

	private BufferedImage[][] mapObstacleView = new BufferedImage[10][10];

	private Image mapDesign;

	private Image eggCell;

	private BufferedImage playerInfo = b.loadImage("Imagem14.png");

	private ArrayList<Player> championsView = new ArrayList<Player>();
	private ArrayList<Player> winnersView = new ArrayList<Player>();
	private int currentIndex = 0;

	private Player currentChampionView;

	private SpellButton currentSpell1;
	private SpellButton currentSpell2;
	private SpellButton currentSpell3;

	private Player currentPlayerIdle;

	private BufferedImage gryffindorMap = new BufferedImageLoader().loadImage("harrymap 2_00000.png");
	private BufferedImage slytherinMap = new BufferedImageLoader().loadImage("darcomap_00000.png");
	private BufferedImage hufflepuffMap = new BufferedImageLoader().loadImage("cedricmap_00000.png");
	private BufferedImage ravenclawMap = new BufferedImageLoader().loadImage("betmap_00000.png");
	private BufferedImage slytherinChampionIdle = new BufferedImageLoader().loadImage("draco front_00000.png");

	private Player gryffindorChampion = new Player("Harry Potter Standing Sprite sheet_00000.png", 680, 384, 6, 6,
			"Gryffindor");
	private Player hufflepuffChampion = new Player("Cidric Stand idle_00000.png", 680, 384, 5, 11, "Hufflepuff");
	private Player ravenclawChampion = new Player("beet front_00000.png", 1866, 1040, 3, 10, "Ravenclaw");
	private Player slytherinChampion = new Player(slytherinChampionIdle, 1866, 1040, 2, 10, "Slytherin");

	private BufferedImage currentWizardName;

	// /////////////////VIEW UPDATED ATTRIBUTES BY MODEL //////////////////

	private JProgressBar hpBar;
	private JProgressBar ipBar;
	private JButton useTrait;

	private JComboBox<String> ablitesOfPotions;
	private JButton usePotion;

	private String traitActivateView = "NOT ACTIVATE";

	private boolean onFire = false;
	private Player fire = new Player("Fire Sprite sheet_00000.png", 100, 70, 5, 10, "");

	private int markX1 = 0;
	private int markY1 = 0;
	private int markX2 = 0;
	private int markY2 = 0;

	private boolean onDamageSpell = false;

	private int damageX = 0;
	private int damageY = 0;
	private Player obstacle1 = new Player((new BufferedImageLoader()).loadImage("obstacle_00000.png"), 97, 88, 1, 14,
			"");

	private JButton[][] obstaclesInfo = new JButton[10][10];

	public FirstTaskPanel(FirstTask task) throws IOException, IndexOutOfBoundsException, InterruptedException {

		// /////////////////TODO DEMO/////////////

		//
		ImageIcon a = new ImageIcon("Choose Background.gif");
		mapDesign = a.getImage();
		ImageIcon ii = new ImageIcon("eggCell.gif");
		eggCell = ii.getImage();
		ImageIcon k1 = new ImageIcon("raven.jpg");
		ravenTrait = k1.getImage();
		JButton exit = new JButton((new ImageIcon("exit icon.png")));
		exit.setRolloverIcon((new ImageIcon("exit1 icon.png")));
		exit.setBounds(1300, 9, 50, 50);
		exit.setOpaque(false);
		exit.setBorderPainted(false);
		exit.setBackground(Color.red);
		exit.setContentAreaFilled(false);
		exit.setFocusable(false);
		playsound("Trumpet Fanfare Sound Effect.wav");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}
		});
		add(exit);

		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size() - 1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size() - 1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size() - 1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size() - 1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size() - 1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size() - 1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size() - 1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size() - 1);
		ravenclawChampion.getFrames().remove(ravenclawChampion.getFrames().size() - 1);

		slytherinChampion.getFrames().remove(slytherinChampion.getFrames().size() - 1);
		slytherinChampion.getFrames().remove(slytherinChampion.getFrames().size() - 1);
		slytherinChampion.getFrames().remove(slytherinChampion.getFrames().size() - 1);
		slytherinChampion.getFrames().remove(slytherinChampion.getFrames().size() - 1);
		slytherinChampion.getFrames().remove(slytherinChampion.getFrames().size() - 1);
		slytherinChampion.getFrames().remove(slytherinChampion.getFrames().size() - 1);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				BufferedImageLoader b4 = new BufferedImageLoader();
				obstaclesInfo[i][j] = new JButton();
				obstaclesInfo[i][j].setBounds((70 * j) + 205, (60 * i) + 85, 70, 60);
				obstaclesInfo[i][j].setOpaque(false);
				obstaclesInfo[i][j].setRolloverIcon(new ImageIcon("new GameButton1.png"));
				obstaclesInfo[i][j].setBackground(Color.red);
				obstaclesInfo[i][j].setBorderPainted(false);
				obstaclesInfo[i][j].setContentAreaFilled(false);
				obstaclesInfo[i][j].setToolTipText("");
				if (task.getMap()[i][j] instanceof ChampionCell) {
					obstaclesInfo[i][j].setToolTipText(
							"Name : " + ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getName() + "  HP: "
									+ ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getHp() + "   IP:"
									+ ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getIp());
				}
				if (task.getMap()[i][j] instanceof ObstacleCell) {
					obstaclesInfo[i][j]
							.setToolTipText("HP : " + ((ObstacleCell) task.getMap()[i][j]).getObstacle().getHp());

				}
				add(obstaclesInfo[i][j]);

				cellsImage[i][j] = b4.loadImage("Maz" + "e.jpg");
			}
		}

		// ///////////////////////////////
		setLayout(null);

		this.task = task;

		task.setMoveListener(this);
		task.setFinalListener(this);
		task.setSpellListener(this);
		// /////////////////set Health points Bar//////////////////
		UIManager.put("ProgressBar.selectionBackground", Color.black);
		UIManager.put("ProgressBar.selectionForeground", Color.white);
		UIManager.put("ProgressBar.foreground", Color.GREEN);

		setHpBar(new JProgressBar(0, 1000));
		getHpBar().setStringPainted(true);
		fire.updateOnce();
		// ///////////////// set IP Bar/////////////////////////
		UIManager.put("ProgressBar.selectionBackground", Color.black);
		UIManager.put("ProgressBar.selectionForeground", Color.white);
		UIManager.put("ProgressBar.foreground", Color.BLUE);

		setIpBar(new JProgressBar(0, 700));
		getIpBar().setStringPainted(true);

		// ////////////////set 4 CHAMPIONS FOR ANIMATIONS AND
		// SPELLS/////////////////

		Player gryffindorChampion = new Player();

		Player hufflepuffChampion = new Player();

		Player ravenclawChampion = new Player();

		Player slytherinChampion = new Player();

		// ////set 4 CHAMPIONS FOR ANIMATIONS AND SPELLS TO EVERY
		// CHAMPION/////////////////

		for (int i = 0; i < task.getChampions().size(); i++) {
			Player c = null;

			if ((Wizard) (task.getChampions().get(i)) instanceof GryffindorWizard) {
				c = gryffindorChampion;
				getChampionsView().add(c);
				c.setAnimationIdle(this.gryffindorChampion);

				Player harryMapIdle = new Player(gryffindorMap, 68, 58, 5, 6, "");

				harryMapIdle.getFrames().remove(harryMapIdle.getFrames().size() - 1);
				harryMapIdle.getFrames().remove(harryMapIdle.getFrames().size() - 1);
				harryMapIdle.getFrames().remove(harryMapIdle.getFrames().size() - 1);

				harryMapIdle.setHouse("Gryffindor");

				harryMapIdle.update();
				c.getAnimationsViewMap().add(harryMapIdle);

				c.setWizardName(b.loadImage("GryffindorWizard logo.png"));
				getChampionsView().add(c);

			}
			if ((Wizard) (task.getChampions().get(i)) instanceof HufflepuffWizard) {
				c = hufflepuffChampion;
				c.setAnimationIdle(this.hufflepuffChampion);
				// TODO ANIMATIONS FOR MAP
				Player cedricMapIdle = new Player(hufflepuffMap, 68, 58, 4, 6, "");
				cedricMapIdle.getFrames().remove(cedricMapIdle.getFrames().size() - 1);
				cedricMapIdle.getFrames().remove(cedricMapIdle.getFrames().size() - 1);
				cedricMapIdle.getFrames().remove(cedricMapIdle.getFrames().size() - 1);
				cedricMapIdle.getFrames().remove(cedricMapIdle.getFrames().size() - 1);
				cedricMapIdle.setHouse("Hufflepuff");
				cedricMapIdle.update();
				c.getAnimationsViewMap().add(cedricMapIdle);

				c.setWizardName(b.loadImage("HufflepuffWizard logo.png"));
				getChampionsView().add(c);

			}
			if ((Wizard) (task.getChampions().get(i)) instanceof RavenclawWizard) {
				c = ravenclawChampion;
				c.setAnimationIdle(this.ravenclawChampion);

				// TODO ANIMATIONS FOR MAP
				Player ravMapIdle = new Player(ravenclawMap, 68, 58, 5, 6, "");
				ravMapIdle.getFrames().remove(ravMapIdle.getFrames().size() - 1);
				ravMapIdle.getFrames().remove(ravMapIdle.getFrames().size() - 1);
				ravMapIdle.getFrames().remove(ravMapIdle.getFrames().size() - 1);
				ravMapIdle.getFrames().remove(ravMapIdle.getFrames().size() - 1);
				ravMapIdle.setHouse("Ravenclaw");
				ravMapIdle.update();
				c.getAnimationsViewMap().add(ravMapIdle);

				c.setWizardName(b.loadImage("RavenclawWizard logo.png"));
				getChampionsView().add(c);
			}
			if ((Wizard) (task.getChampions().get(i)) instanceof SlytherinWizard) {
				c = slytherinChampion;
				c.setAnimationIdle(this.slytherinChampion);
				// TODO ANIMATIONS FOR MAP
				Player slyMapIdle = new Player(slytherinMap, 68, 58, 3, 6, "");
				slyMapIdle.getFrames().remove(slyMapIdle.getFrames().size() - 1);
				slyMapIdle.getFrames().remove(slyMapIdle.getFrames().size() - 1);
				slyMapIdle.getFrames().remove(slyMapIdle.getFrames().size() - 1);
				slyMapIdle.getFrames().remove(slyMapIdle.getFrames().size() - 1);

				slyMapIdle.setHouse("Slytherin");
				slyMapIdle.update();
				c.getAnimationsViewMap().add(slyMapIdle);

				c.setWizardName(b.loadImage("SlytherinWizard logo.png"));
				getChampionsView().add(c);
			}
			c.setRank(i);
			for (int j = 0; j < ((Wizard) (task.getChampions().get(i))).getSpells().size(); j++) {

				SpellButton spellButton = null;
				if (((Wizard) (task.getChampions().get(i))).getSpells().get(j) instanceof DamagingSpell) {
					spellButton = new SpellButton(((Wizard) (task.getChampions().get(i))).getSpells().get(j).getName(),
							1, ((Wizard) (task.getChampions().get(i))).getSpells().get(j).getCost(),
							((DamagingSpell) ((Wizard) (task.getChampions().get(i))).getSpells().get(j))
									.getDamageAmount());
					Spell object = ((Wizard) (task.getChampions().get(i))).getSpells().get(j);
					spellButton.setSpellObject(((Wizard) (task.getChampions().get(i))).getSpells().get(j));

					getChampionsView().get(i).getSpellsButton().add(spellButton);
					spellButton.setVisible(false);
					spellButton.addItemListener(new ItemListener() {

						@Override
						public void itemStateChanged(ItemEvent e) {

							if (e.getStateChange() == ItemEvent.SELECTED) {
								try {
									Direction d;
									JDialog.setDefaultLookAndFeelDecorated(true);
									int id = Integer.parseInt(JOptionPane.showInputDialog(null,
											"You are now casting Damaging Spell \n Please enter Target Cell : \n 1)FORWARD \n 2)BACKWARD \n 3)LEFT \n 4)RIGHT"));
									switch (id) {

									case 1: {
										d = Direction.FORWARD;
										break;
									}

									case 2: {
										d = Direction.BACKWARD;
										break;
									}
									case 3: {
										d = Direction.LEFT;
										break;
									}
									case 4: {
										d = Direction.RIGHT;
										break;
									}

									default: {
										JOptionPane.showMessageDialog(null, "PLEASE FOLLOW THE INSTRUCTIONS !!!!!",
												JOptionPane.MESSAGE_PROPERTY, 0);
										return;
									}

									}
									try {
										try {
											task.castDamagingSpell(((DamagingSpell) object), d);
										} catch (FontFormatException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									} catch (InCooldownException e1) {
										playsound("Buzzer.wav");
										JOptionPane.showMessageDialog(FirstTaskPanel.this,
												String.format("You should wait " + object.getCoolDown() + " Turns"));

									} catch (NotEnoughIPException e1) {
										playsound("Buzzer.wav");
										JOptionPane.showMessageDialog(null, e1.getMessage(),
												JOptionPane.MESSAGE_PROPERTY, 0);

									} catch (OutOfBordersException e1) {
										playsound("Buzzer.wav");
									} catch (InvalidTargetCellException e1) {
										playsound("Denied.wav");
									} catch (IOException e1) {
										e1.printStackTrace();
									}

								} catch (NumberFormatException e1) {
									JDialog.setDefaultLookAndFeelDecorated(true);
									JOptionPane.showMessageDialog(null, "PLEASE ENTER A VALID NUMBEEEERS !!!!!",
											JOptionPane.MESSAGE_PROPERTY, 0);
								}

							}
						}

					});

				}
				if (((Wizard) (task.getChampions().get(i))).getSpells().get(j) instanceof HealingSpell) {
					spellButton = new SpellButton(((Wizard) (task.getChampions().get(i))).getSpells().get(j).getName(),
							2, ((Wizard) (task.getChampions().get(i))).getSpells().get(j).getCost(),
							((HealingSpell) ((Wizard) (task.getChampions().get(i))).getSpells().get(j))
									.getHealingAmount());
					spellButton.setSpellObject(((Wizard) (task.getChampions().get(i))).getSpells().get(j));
					HealingSpell object = (HealingSpell) ((Wizard) (task.getChampions().get(i))).getSpells().get(j);
					getChampionsView().get(i).getSpellsButton().add(spellButton);
					spellButton.setVisible(false);
					spellButton.addItemListener(new ItemListener() {

						@Override
						public void itemStateChanged(ItemEvent e) {
							if (e.getStateChange() == ItemEvent.SELECTED) {
								try {
									try {
										task.castHealingSpell(object);
									} catch (FontFormatException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								} catch (InCooldownException e1) {
									playsound("Buzzer.wav");
									JOptionPane.showMessageDialog(null, e1.getMessage(), JOptionPane.MESSAGE_PROPERTY,
											0);

								} catch (NotEnoughIPException e1) {
									playsound("Buzzer.wav");
									JOptionPane.showMessageDialog(null, e1.getMessage(), JOptionPane.MESSAGE_PROPERTY,
											0);

								} catch (IOException e1) {

									e1.printStackTrace();
								}

							}
						}
					});

				}
				if (((Wizard) (task.getChampions().get(i))).getSpells().get(j) instanceof RelocatingSpell) {
					spellButton = new SpellButton(((Wizard) (task.getChampions().get(i))).getSpells().get(j).getName(),
							3, ((Wizard) (task.getChampions().get(i))).getSpells().get(j).getCost(),
							((RelocatingSpell) ((Wizard) (task.getChampions().get(i))).getSpells().get(j)).getRange());
					spellButton.setSpellObject(((Wizard) (task.getChampions().get(i))).getSpells().get(j));
					Spell object = ((Wizard) (task.getChampions().get(i))).getSpells().get(j);
					getChampionsView().get(i).getSpellsButton().add(spellButton);
					spellButton.setVisible(false);
					spellButton.addItemListener(new ItemListener() {

						@Override
						public void itemStateChanged(ItemEvent e) {

							if (e.getStateChange() == ItemEvent.SELECTED) {
								try {
									Direction d;
									JDialog.setDefaultLookAndFeelDecorated(true);
									int id = Integer.parseInt(JOptionPane.showInputDialog(null,
											"You are now casting Relocating Spell \n Please enter Target Cell : \n 1)FORWARD \n 2)BACKWARD \n 3)LEFT \n 4)RIGHT"));
									switch (id) {
									case 1: {
										d = Direction.FORWARD;
										break;
									}
									case 2: {
										d = Direction.BACKWARD;
										break;
									}
									case 3: {
										d = Direction.LEFT;
										break;
									}
									case 4: {
										d = Direction.RIGHT;
										break;
									}

									default: {
										JOptionPane.showMessageDialog(null, "PLEASE FOLLOW THE INSTRUCTIONS !!!!!",
												JOptionPane.MESSAGE_PROPERTY, 0);
										return;
									}

									}
									Direction d1;
									JDialog.setDefaultLookAndFeelDecorated(true);
									int id1 = Integer.parseInt(JOptionPane.showInputDialog(null,
											"You are now casting Relocating Spell \n CHOOSE DIRECTION FOR RELOCATING YOUR TARGET OBJECT :  \n 1)FORWARD \n 2)BACKWARD \n 3)LEFT \n 4)RIGHT"));
									switch (id1) {
									case 1: {
										d1 = Direction.FORWARD;
										break;
									}
									case 2: {
										d1 = Direction.BACKWARD;
										break;
									}
									case 3: {
										d1 = Direction.LEFT;
										break;
									}
									case 4: {
										d1 = Direction.RIGHT;
										break;
									}

									default: {
										JOptionPane.showMessageDialog(null, "PLEASE FOLLOW THE INSTRUCTIONS !!!!!",
												JOptionPane.MESSAGE_PROPERTY, 0);
										return;
									}

									}
									int r = Integer.parseInt(JOptionPane.showInputDialog(null,
											"You are now casting Relocating Spell \n ENTER RANGE : "));

									try {
										try {
											task.castRelocatingSpell((RelocatingSpell) object, d, d1, r);
										} catch (FontFormatException e1) {
								
											e1.printStackTrace();
										}
									} catch (InCooldownException e1) {
										playsound("Buzzer.wav");
										JOptionPane.showMessageDialog(FirstTaskPanel.this,
												String.format("You should wait " + object.getCoolDown() + " Turns"));

									} catch (NotEnoughIPException e1) {
										playsound("Buzzer.wav");
										JOptionPane.showMessageDialog(null, e1.getMessage(),
												JOptionPane.MESSAGE_PROPERTY, 0);
										// playsound("Buzzer.wav");
									} catch (OutOfBordersException e1) {
										playsound("Buzzer.wav");
									} catch (InvalidTargetCellException e1) {
										playsound("Denied.wav");
									} catch (OutOfRangeException e1) {
										playsound("Buzzer.wav");
										JOptionPane.showMessageDialog(null, e1.getMessage(),
												JOptionPane.MESSAGE_PROPERTY, 0);
										// playsound("Buzzer.wav");
									} catch (IOException e1) {
										e1.printStackTrace();
									}

								} catch (NumberFormatException e1) {
									JDialog.setDefaultLookAndFeelDecorated(true);
									JOptionPane.showMessageDialog(null, "PLEASE ENTER A VALID NUMBEEEERS !!!!!",
											JOptionPane.MESSAGE_PROPERTY, 0);
								}

							}
						}

					});

				}

			}

		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (task.getMap()[i][j] instanceof ChampionCell) {
					if (((ChampionCell) task.getMap()[i][j]).getChamp() instanceof GryffindorWizard) {
						for (int k = 0; k < championsView.size(); k++) {
							if (championsView.get(k).getAnimationIdle().getHouse().equals("Gryffindor")) {
								championsView.get(k).getAnimationsViewMap().get(0).setX(i);
								championsView.get(k).getAnimationsViewMap().get(0).setY(j);
							}
						}
					} else if (((ChampionCell) task.getMap()[i][j]).getChamp() instanceof HufflepuffWizard) {
						for (int k = 0; k < championsView.size(); k++) {

							if (championsView.get(k).getAnimationIdle().getHouse().equals("Hufflepuff")) {
								championsView.get(k).getAnimationsViewMap().get(0).setX(i);
								championsView.get(k).getAnimationsViewMap().get(0).setY(j);
							}
						}
					} else if (((ChampionCell) task.getMap()[i][j]).getChamp() instanceof RavenclawWizard) {
						for (int k = 0; k < championsView.size(); k++) {
							if (championsView.get(k).getAnimationIdle().getHouse().equals("Ravenclaw")) {
								championsView.get(k).getAnimationsViewMap().get(0).setX(i);
								championsView.get(k).getAnimationsViewMap().get(0).setY(j);
							}
						}
					} else if (((ChampionCell) task.getMap()[i][j]).getChamp() instanceof SlytherinWizard) {
						for (int k = 0; k < championsView.size(); k++) {
							if (championsView.get(k).getAnimationIdle().getHouse().equals("Slytherin")) {

								championsView.get(k).getAnimationsViewMap().get(0).setX(i);
								championsView.get(k).getAnimationsViewMap().get(0).setY(j);
								break;
							}
						}
					}
				}

			}
		}

		// //////////////////////END OF ANIMATOR//////////////////////////

		// //////////////////UPDATE CURRENT VIEW/////////////////////////

		currentChampionView = championsView.get(currentIndex);
		currentPlayerIdle = currentChampionView.getAnimationIdle();

		currentWizardName = currentChampionView.getWizardName();
		if (!currentChampionView.getSpellsButton().isEmpty()) {
			currentSpell1 = currentChampionView.getSpellsButton().get(0);
			currentSpell2 = currentChampionView.getSpellsButton().get(1);
			currentSpell3 = currentChampionView.getSpellsButton().get(2);

			currentSpell1.setBounds(1020, 500, 130, 80);
			currentSpell2.setBounds(1020, 586, 130, 80);
			currentSpell3.setBounds(1160, 500, 130, 80);

			currentSpell1.setVisible(true);
			currentSpell2.setVisible(true);
			currentSpell3.setVisible(true);
			add(currentSpell1);
			add(currentSpell2);
			add(currentSpell3);
			currentSpell1.setToolTipText("Cooldown : " + currentSpell1.getSpellObject().getCoolDown());
			currentSpell2.setToolTipText("Cooldown : " + currentSpell2.getSpellObject().getCoolDown());
			currentSpell3.setToolTipText("Cooldown : " + currentSpell3.getSpellObject().getCoolDown());

		}
		// ///////////////////UPDATE CURRENT VIEW BY MODEL/////////////////

		hpBar.setValue(((Wizard) task.getCurrentChamp()).getHp());
		hpBar.setString("HP: " + ((Wizard) task.getCurrentChamp()).getHp());

		ipBar.setValue(((Wizard) task.getCurrentChamp()).getIp());
		ipBar.setString("IP: " + ((Wizard) task.getCurrentChamp()).getIp());

		hpBar.setBounds(1016, 197, 160, 20);
		ipBar.setBounds(1016, 230, 160, 20);

		add(hpBar);
		add(ipBar);

		ablitesOfPotions = new JComboBox<String>();
		ablitesOfPotions.addItem("None !");
		ablitesOfPotions.setFocusable(false);
		for (int i = 0; i < ((Wizard) task.getCurrentChamp()).getInventory().size(); i++) {
			ablitesOfPotions.addItem(((Wizard) task.getCurrentChamp()).getInventory().get(i).getName());
		}

		add(ablitesOfPotions);

		ablitesOfPotions.setBounds(1016, 460, 160, 30);

		usePotion = new JButton(new ImageIcon("use Potion.png"));
		usePotion.setRolloverIcon(new ImageIcon("use Potion1.png"));
		usePotion.setContentAreaFilled(false);
		usePotion.setOpaque(false);
		usePotion.setBackground(Color.black);

		usePotion.setBounds(1186, 430, 100, 50);
		usePotion.setFocusable(false);
		usePotion.setBorderPainted(false);

		usePotion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					String s = (String) ablitesOfPotions.getSelectedItem();
					Potion p = searchPotion(s);
					task.usePotion(p);
					playsound("USE POTION.wav");
				} catch (NullPointerException e1) {
					if (((Wizard) task.getCurrentChamp()).getInventory().isEmpty()) {
						JDialog.setDefaultLookAndFeelDecorated(true);
						JOptionPane.showMessageDialog(null, "Sorry !!  you don't have any potions in your inventory :(",
								JOptionPane.MESSAGE_PROPERTY, 0);
					} else
						JDialog.setDefaultLookAndFeelDecorated(true);
					JOptionPane.showMessageDialog(null, "Please choose a potion from inventory correctlly",
							JOptionPane.MESSAGE_PROPERTY, 0);

				}

			}
		});

		add(usePotion);

		useTrait = new JButton(new ImageIcon("Trait Activate.png"));
		useTrait.setBounds(1160, 586, 130, 80);
		useTrait.setFocusable(false);
		useTrait.setContentAreaFilled(false);
		useTrait.setBorderPainted(false);
		useTrait.setRolloverIcon(new ImageIcon("Trait Activate1.png"));
		add(useTrait);
		useTrait.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (((Wizard) task.getCurrentChamp()) instanceof GryffindorWizard
							|| ((Wizard) task.getCurrentChamp()) instanceof HufflepuffWizard)
						try {
							task.getCurrentChamp().useTrait();
							playsound("USE TRAIT.WAV");
						} catch (FontFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					else if (((Wizard) task.getCurrentChamp()) instanceof RavenclawWizard) {
						@SuppressWarnings("unchecked")
						ArrayList<Point> x = ((ArrayList<Point>) task.onRavenclawTrait());
						playsound("USE TRAIT.WAV");
						JOptionPane.showMessageDialog(
								null, "TAKE CAREE!! \n Dragon will fire on (" + x.get(0).x + "," + x.get(0).y + ")"
										+ " and (" + x.get(1).x + "," + x.get(1).y + ").",
								JOptionPane.MESSAGE_PROPERTY, 0);

					} else if (((Wizard) task.getCurrentChamp()) instanceof SlytherinWizard) {
						try {
							Direction d;
							JDialog.setDefaultLookAndFeelDecorated(true);
							int id = Integer.parseInt(JOptionPane.showInputDialog(null,
									"You are now using Slytherin Wizard Trait \n You will move two cells in a single move \n Please enter Target Cell : \n 1)FORWARD \n 2)BACKWARD \n 3)LEFT \n 4)RIGHT"));
							switch (id) {
							case 1: {
								d = Direction.FORWARD;
								break;
							}
							case 2: {
								d = Direction.BACKWARD;
								break;
							}
							case 3: {
								d = Direction.LEFT;
								break;
							}
							case 4: {
								d = Direction.RIGHT;
								break;
							}

							default: {
								JOptionPane.showMessageDialog(null, "PLEASE FOLLOW THE INSTRUCTIONS !!!!!",
										JOptionPane.MESSAGE_PROPERTY, 0);
								return;
							}

							}
							try {

								task.onSlytherinTrait(d);
								playsound("USE TRAIT.WAV");
							} catch (FontFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (NumberFormatException e) {
							JDialog.setDefaultLookAndFeelDecorated(true);
							JOptionPane.showMessageDialog(null, "PLEASE ENTER A VALID NUMBEEEERS !!!!!",
									JOptionPane.MESSAGE_PROPERTY, 0);

						}
					}

				} catch (InCooldownException e1) {

					JOptionPane.showMessageDialog(null, e1.getMessage(), JOptionPane.MESSAGE_PROPERTY, 0);
					playsound("Buzzer.wav");

				} catch (OutOfBordersException e1) {
					playsound("Buzzer.wav");
				} catch (InvalidTargetCellException e1) {

					playsound("Denied.wav");
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

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
		g.drawImage(mapDesign, -290, -120, 1680, 1070, this);

		g.drawImage(mapDesign1, -290, -120, 1680, 1070, this);

		g.drawImage(playerInfo, 961, -90, 400, 919, this);
		if (currentWizardName != null) {
			g.drawImage(currentWizardName, 1015, 73, 290, 90, this);
		}
		if (currentPlayerIdle != null) {
			currentPlayerIdle.update();
			g.drawImage(currentPlayerIdle.getSprite(), 1112, 133, 300, 250, null);
		}
		useTrait.setToolTipText("TraitCoolDown : " + ((Wizard) task.getCurrentChamp()).getTraitCooldown());

		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.setColor(Color.BLACK);
		g.drawString("Name:" + ((Wizard) task.getCurrentChamp()).getName(), 1013, 181);
		if (task.isTraitActivated())
			traitActivateView = "ACTIVATE";
		else
			traitActivateView = "NOT ACTIVATE";
		g.drawString("Trait Activate : " + traitActivateView, 1016, 280);
		g.drawString("Allowed Moves : " + task.getAllowedMoves(), 1016, 314);
		try {
			g.drawImage(b.loadImage("Potion Image.png"), 1016, 350, 86, 86, this);
		} catch (IOException e) {

			e.printStackTrace();
		}
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("X " + ((Wizard) task.getCurrentChamp()).getInventory().size() + "", 1116, 413);

		if (cellsImage != null) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {

					if (((i == task.getMarkedCells().get(0).x && j == task.getMarkedCells().get(0).y)
							|| (i == task.getMarkedCells().get(1).x && j == task.getMarkedCells().get(1).y))
							&& ((Wizard) task.getCurrentChamp()) instanceof RavenclawWizard
							&& task.isTraitActivated()) {

						g.drawImage(ravenTrait, (70 * (task.getMarkedCells().get(0).y)) + 205,
								(60 * (task.getMarkedCells().get(0).x) + 85), 70, 60, null);
						g.drawImage(ravenTrait, (70 * (task.getMarkedCells().get(1).y)) + 205,
								(60 * (task.getMarkedCells().get(1).x) + 85), 70, 60, null);

					} else
						g.drawImage(cellsImage[i][j], (70 * (j)) + 205, (60 * (i)) + 85, 70, 60, null);

					if (task.getMap()[i][j] instanceof ObstacleCell) {

						BufferedImage obstacle2 = obstacle1.getSprite();

						g.drawImage(obstacle2, (70 * (j)) + 195, (60 * (i)) + 60, 100, 90, this);
						obstacle1.update();

					}
					if (i == 4 && j == 4) {
						g.drawImage(eggCell, (((70 * (j)) + 215)), (((60 * (i)) + 95)), 50, 30, this);

					}

					if (task.getMap()[i][j] instanceof ChampionCell) {

						if (((ChampionCell) task.getMap()[i][j]).getChamp() instanceof GryffindorWizard) {
							for (int k = 0; k < championsView.size(); k++) {
								if (championsView.get(k).getAnimationsViewMap().get(0).getSprite() != null) {
									if (championsView.get(k).getAnimationIdle().getHouse() != null) {
										if (championsView.get(k).getAnimationIdle().getHouse().equals("Gryffindor")) {
											BufferedImage currentImage = championsView.get(k).getAnimationsViewMap()
													.get(0).getSprite();

											g.drawImage(currentImage,
													(((70 * (championsView.get(k).getAnimationsViewMap().get(0).getY()))
															+ 205)),
													(((60 * (championsView.get(k).getAnimationsViewMap().get(0).getX()))
															+ 77)),
													70, 60, this);

											championsView.get(k).getAnimationsViewMap().get(0).update();

										}
									}
								}
							}

						}
						if (((ChampionCell) task.getMap()[i][j]).getChamp() instanceof HufflepuffWizard) {

							for (int k = 0; k < championsView.size(); k++) {
								if (championsView.get(k).getAnimationsViewMap().get(0).getSprite() != null) {
									if (championsView.get(k).getAnimationIdle().getHouse().equals("Hufflepuff")) {
										BufferedImage currentImage = championsView.get(k).getAnimationsViewMap().get(0)
												.getSprite();
										g.drawImage(currentImage,
												(((70 * (championsView.get(k).getAnimationsViewMap().get(0).getY()))
														+ 205)),
												(((60 * (championsView.get(k).getAnimationsViewMap().get(0).getX()))
														+ 77)),
												80, 60, this);
										championsView.get(k).getAnimationsViewMap().get(0).update();
									}

								}
							}

						}
						if (((ChampionCell) task.getMap()[i][j]).getChamp() instanceof RavenclawWizard) {
							for (int k = 0; k < championsView.size(); k++) {

								if (championsView.get(k).getAnimationsViewMap().get(0).getSprite() != null) {

									if (championsView.get(k).getAnimationIdle().getHouse().equals("Ravenclaw")) {
										BufferedImage currentImage = championsView.get(k).getAnimationsViewMap().get(0)
												.getSprite();
										g.drawImage(currentImage,
												(((70 * (championsView.get(k).getAnimationsViewMap().get(0).getY()))
														+ 205)),
												(((60 * (championsView.get(k).getAnimationsViewMap().get(0).getX()))
														+ 77)),
												80, 60, this);
										championsView.get(k).getAnimationsViewMap().get(0).update();
									}

								}
							}
						}

						if (((ChampionCell) task.getMap()[i][j]).getChamp() instanceof SlytherinWizard) {
							for (int k = 0; k < championsView.size(); k++) {
								if (championsView.get(k).getAnimationsViewMap().get(0).getSprite() != null) {
									if (championsView.get(k).getAnimationIdle().getHouse().equals("Slytherin")) {
										BufferedImage currentImage = championsView.get(k).getAnimationsViewMap().get(0)
												.getSprite();
										g.drawImage(currentImage,
												(((70 * (championsView.get(k).getAnimationsViewMap().get(0).getY()))
														+ 205)),
												(((60 * (championsView.get(k).getAnimationsViewMap().get(0).getX()))
														+ 77)),
												80, 60, this);
										championsView.get(k).getAnimationsViewMap().get(0).update();
									}
								}
							}

						}

					}

				}
			}
		}

		// if (championsView.size() > 1) {
		// BufferedImage currentImage = championsView.get(0)
		// .getAnimationsViewMap().get(0).getSprite();
		// g.drawImage(currentImage, (((70 * (championsView.get(0)
		// .getAnimationsViewMap().get(0).getY())) + 85)),
		// (((60 * (championsView.get(0).getAnimationsViewMap().get(0)
		// .getX())) - 25)), 300, 290, this);
		// championsView.get(0).getAnimationsViewMap().get(0).update();
		// }
		// if (championsView.size() > 2) {
		// BufferedImage currentImage = championsView.get(1)
		// .getAnimationsViewMap().get(0).getSprite();
		// g.drawImage(currentImage, (((70 * (championsView.get(1)
		// .getAnimationsViewMap().get(0).getY())) + 85)),
		// (((60 * (championsView.get(1).getAnimationsViewMap().get(0)
		// .getX())) - 25)), 300, 290, this);
		// championsView.get(1).getAnimationsViewMap().get(0).update();
		// }
		// if (championsView.size() > 3) {
		// BufferedImage currentImage = championsView.get(2)
		// .getAnimationsViewMap().get(0).getSprite();
		// g.drawImage(currentImage, (((70 * (championsView.get(2)
		// .getAnimationsViewMap().get(0).getY())) + 85)),
		// (((60 * (championsView.get(2).getAnimationsViewMap().get(0)
		// .getX())) - 25)), 300, 290, this);
		// championsView.get(2).getAnimationsViewMap().get(0).update();
		// }
		// if (championsView.size() > 4) {
		// BufferedImage currentImage = championsView.get(3)
		// .getAnimationsViewMap().get(0).getSprite();
		// g.drawImage(currentImage, (((70 * (championsView.get(3)
		// .getAnimationsViewMap().get(0).getY())) + 85)),
		// (((60 * (championsView.get(3).getAnimationsViewMap().get(0)
		// .getX())) - 25)), 300, 290, this);
		// championsView.get(3).getAnimationsViewMap().get(0).update();
		// }
		// if(((Wizard)task.getCurrentChamp()) instanceof RavenclawWizard &&
		// task.isTraitActivated()){
		//
		// g.drawImage(
		// ravenTrait, (70*(task.getMarkedCells().get(0).y))+205
		// ,(60 *(task.getMarkedCells().get(0).x) + 80),
		// 70, 60, this);
		//
		// }
		
		hpBar.setMaximum(((Wizard) task.getCurrentChamp()).getDefaultHp());
		ipBar.setMaximum(((Wizard) task.getCurrentChamp()).getDefaultIp());

		if (onFire) {

			try {
				fire.updateOnce();
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			g.drawImage(fire.getSprite(), (((70 * (markY2)) + 205)), (((60 * (markX2)) + 85)), 70, 60, this);
			g.drawImage(fire.getSprite(), (((70 * (markY1)) + 205)), (((60 * (markX1)) + 85)), 70, 60, this);

			if (fire.isFinished()) {
				onFire = false;

				fire.stop1();
			}
		}
		try {
			Thread.sleep(0);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
		if (onDamageSpell) {
			// TODO EXPLOTION ANIMATION;

		}

		// ////////////////TODO DEMOO//////////////////

		// ///////////////////////////////////
		// ImageIcon a = new ImageIcon("Physical Obstacle.gif");
		// Image n = a.getImage();
		// g.drawImage(n, mx, my, 150, 100, this);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		super.paintComponents(g);
		repaint();
		validate();
	}

	public SpellButton getCurrentSpell3() {
		return currentSpell3;
	}

	public void setCurrentSpell3(SpellButton currentSpell3) {
		this.currentSpell3 = currentSpell3;
	}

	public SpellButton getCurrentSpell2() {
		return currentSpell2;
	}

	public void setCurrentSpell2(SpellButton currentSpell2) {
		this.currentSpell2 = currentSpell2;
	}

	public SpellButton getCurrentSpell1() {
		return currentSpell1;
	}

	public void setCurrentSpell1(SpellButton currentSpell1) {
		this.currentSpell1 = currentSpell1;
	}

	public BufferedImage[][] getMapObstacleView() {
		return mapObstacleView;
	}

	public void setMapObstacleView(BufferedImage[][] mapObstacleView) {
		this.mapObstacleView = mapObstacleView;
	}

	public ArrayList<Player> getChampionsView() {
		return championsView;
	}

	public void setChampionsView(ArrayList<Player> championsView) {
		this.championsView = championsView;
	}

	public Player getCurrentChampionView() {
		return currentChampionView;
	}

	public void setCurrentChampionView(Player currentChampionView) {
		this.currentChampionView = currentChampionView;
	}

	public JProgressBar getHpBar() {
		return hpBar;
	}

	public void setHpBar(JProgressBar hpBar) {
		this.hpBar = hpBar;
	}

	public JProgressBar getIpBar() {
		return ipBar;
	}

	public void setIpBar(JProgressBar ipBar) {
		this.ipBar = ipBar;
	}

	public Player getCurrentPlayerIdle() {
		return currentPlayerIdle;
	}

	public void setCurrentPlayerIdle(Player currentPlayerIdle) {
		this.currentPlayerIdle = currentPlayerIdle;
	}

	public FirstTask getTask() {
		return task;
	}

	public void setTask(FirstTask task) {
		this.task = task;
	}

	public Potion searchPotion(String name) {
		for (int i = 0; i < task.getPotions().size(); i++) {
			if (name.equals(task.getPotions().get(i).getName()))
				return task.getPotions().get(i);

		}
		return null;
	}

	public JButton getUseTrait() {
		return useTrait;
	}

	public void setUseTrait(JButton useTrait) {
		this.useTrait = useTrait;
	}

	public ArrayList<Player> getWinnersView() {
		return winnersView;
	}

	public void setWinnersView(ArrayList<Player> winnersView) {
		this.winnersView = winnersView;
	}

	public boolean isOnFire() {
		return onFire;
	}

	public void setOnFire(boolean onFire) {
		this.onFire = onFire;
	}

	public int getMarkX1() {
		return markX1;
	}

	public void setMarkX1(int markX1) {
		this.markX1 = markX1;
	}

	public int getMarkY1() {
		return markY1;
	}

	public void setMarkY1(int markY1) {
		this.markY1 = markY1;
	}

	public int getMarkX2() {
		return markX2;
	}

	public void setMarkX2(int markX2) {
		this.markX2 = markX2;
	}

	public int getMarkY2() {
		return markY2;
	}

	public void setMarkY2(int markY2) {
		this.markY2 = markY2;
	}

	public boolean isOnDamageSpell() {
		return onDamageSpell;
	}

	public void setOnDamageSpell(boolean onDamageSpell) {
		this.onDamageSpell = onDamageSpell;
	}

	public int getDamageX() {
		return damageX;
	}

	public void setDamageX(int damageX) {
		this.damageX = damageX;
	}

	public int getDamageY() {
		return damageY;
	}

	public void setDamageY(int damageY) {
		this.damageY = damageY;
	}

	@Override
	public void onEncounterCollectableCell() {
		playsound("Mario Coin collect sound effect.wav");

	}

	@Override
	public void onEncounterEmptyCell() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (task.getMap()[i][j] instanceof ChampionCell) {
					if (((ChampionCell) task.getMap()[i][j]).getChamp() instanceof GryffindorWizard) {
						for (int k = 0; k < championsView.size(); k++) {
							if (championsView.get(k).getAnimationIdle().getHouse().equals("Gryffindor")) {
								championsView.get(k).getAnimationsViewMap().get(0).setX(i);
								championsView.get(k).getAnimationsViewMap().get(0).setY(j);
							}
						}
					} else if (((ChampionCell) task.getMap()[i][j]).getChamp() instanceof HufflepuffWizard) {
						for (int k = 0; k < championsView.size(); k++) {

							if (championsView.get(k).getAnimationIdle().getHouse().equals("Hufflepuff")) {
								championsView.get(k).getAnimationsViewMap().get(0).setX(i);
								championsView.get(k).getAnimationsViewMap().get(0).setY(j);
							}
						}
					} else if (((ChampionCell) task.getMap()[i][j]).getChamp() instanceof RavenclawWizard) {
						for (int k = 0; k < championsView.size(); k++) {
							if (championsView.get(k).getAnimationIdle().getHouse().equals("Ravenclaw")) {
								championsView.get(k).getAnimationsViewMap().get(0).setX(i);
								championsView.get(k).getAnimationsViewMap().get(0).setY(j);
							}
						}
					} else if (((ChampionCell) task.getMap()[i][j]).getChamp() instanceof SlytherinWizard) {
						for (int k = 0; k < championsView.size(); k++) {
							if (championsView.get(k).getAnimationIdle().getHouse().equals("Slytherin")) {

								championsView.get(k).getAnimationsViewMap().get(0).setX(i);
								championsView.get(k).getAnimationsViewMap().get(0).setY(j);
								break;
							}
						}
					}
				}

			}
		}
	}

	@Override
	public void onChampionWin(Wizard champ) {
		playsound("16163223_1421181781266086_8843417694884593664_n.wav");
		String s = "";
		if (champ instanceof HufflepuffWizard)
			s = "Hufflepuff";
		if (champ instanceof RavenclawWizard)
			s = "Ravenclaw";
		if (champ instanceof GryffindorWizard)
			s = "Gryffindor";
		if (champ instanceof SlytherinWizard)
			s = "Slytherin";
		for (int i = 0; i < championsView.size(); i++) {

			if (s.equals(championsView.get(i).getAnimationsViewMap().get(0).getHouse())) {

				winnersView.add(championsView.get(i));
				System.out.println("Winner :" + championsView.get(i).getAnimationsViewMap().get(0).getHouse());
				try {
					championsView.get(i).getAnimationsViewMap().get(0).setSprite(null);
				} catch (IndexOutOfBoundsException e) {

				}
				championsView.remove(championsView.get(i));

			}
		}

		// TODO ANIMATED SMOKE & SOUND EFFECT

	}

	@Override
	public void onChampionDeath(Wizard champ) {
		playsound("Sad Trombone - Gaming Sound Effect (HD).WAV");
		String s = "";
		if (champ instanceof HufflepuffWizard)
			s = "Hufflepuff";
		if (champ instanceof RavenclawWizard)
			s = "Ravenclaw";
		if (champ instanceof GryffindorWizard)
			s = "Gryffindor";
		if (champ instanceof SlytherinWizard)
			s = "Slytherin";
		for (int i = 0; i < championsView.size(); i++) {
			if (s.equals(championsView.get(i).getAnimationsViewMap().get(0).getHouse())) {
				System.out.println("Loser :" + championsView.get(i).getAnimationsViewMap().get(0).getHouse());

				try {
					championsView.get(i).getAnimationsViewMap().get(0).setSprite(null);
				} catch (IndexOutOfBoundsException e) {

				}
				championsView.remove(championsView.get(i));

			}
		}
		hpBar.setMaximum(((Wizard) task.getCurrentChamp()).getDefaultHp());
		ipBar.setMaximum(((Wizard) task.getCurrentChamp()).getDefaultIp());

	}

	@Override
	public void onDragonFire(int x1, int y1, int x2, int y2) {
		hpBar.setMaximum(((Wizard) task.getCurrentChamp()).getDefaultHp());
		ipBar.setMaximum(((Wizard) task.getCurrentChamp()).getDefaultIp());

		markX1 = x1;
		markY1 = y1;
		markX2 = x2;
		markY2 = y2;
		fire.setFinished(false);
		playsound("Explosion Sound Effect (Powerpuff Girls Version) 2.wav");

		fire.start();
		onFire = true;
		hpBar.setValue(((Wizard) task.getCurrentChamp()).getHp());
		hpBar.setString("HP: " + ((Wizard) task.getCurrentChamp()).getHp());
		ipBar.setValue(((Wizard) task.getCurrentChamp()).getIp());
		ipBar.setString("IP: " + ((Wizard) task.getCurrentChamp()).getIp());

	}

	@Override
	public void onEndingChampionTurn() {
		for (int i = 0; i < currentChampionView.getSpellsButton().size(); i++) {
			if (currentChampionView.getSpellsButton().get(i).isSelected())
				currentChampionView.getSpellsButton().get(i).setSelected(false);
		}

		hpBar.setValue(((Wizard) task.getCurrentChamp()).getHp());
		hpBar.setString("HP: " + ((Wizard) task.getCurrentChamp()).getHp());

		ipBar.setValue(((Wizard) task.getCurrentChamp()).getIp());
		ipBar.setString("IP: " + ((Wizard) task.getCurrentChamp()).getIp());

		currentSpell1.setVisible(false);
		currentSpell2.setVisible(false);
		currentSpell3.setVisible(false);

		for (int i = 0; i < championsView.size(); i++) {
			if (((Wizard) task.getCurrentChamp()) instanceof GryffindorWizard
					&& championsView.get(i).getAnimationIdle().getHouse().equals("Gryffindor")) {
				currentIndex = i;
				System.out.println("G");
				break;
			} else if (((Wizard) task.getCurrentChamp()) instanceof HufflepuffWizard
					&& championsView.get(i).getAnimationIdle().getHouse().equals("Hufflepuff")) {
				currentIndex = i;
				System.out.println("h");
				break;
			} else if (((Wizard) task.getCurrentChamp()) instanceof RavenclawWizard
					&& championsView.get(i).getAnimationIdle().getHouse().equals("Ravenclaw")) {
				currentIndex = i;
				System.out.println("r");
				break;
			} else if (((Wizard) task.getCurrentChamp()) instanceof SlytherinWizard
					&& championsView.get(i).getAnimationIdle().getHouse().equals("Slytherin")) {
				currentIndex = i;
				System.out.println("s");
				break;
			}

		}
		for (int i = 0; i < ((Wizard) task.getCurrentChamp()).getSpells().size(); i++) {
			System.out.println(((Wizard) task.getCurrentChamp()).getSpells().get(i).getName());
		}

		currentChampionView = championsView.get(currentIndex);
		currentPlayerIdle = currentChampionView.getAnimationIdle();

		currentWizardName = currentChampionView.getWizardName();
		for (int i = 0; i < ((Wizard) task.getCurrentChamp()).getSpells().size(); i++) {
			if (((Wizard) task.getCurrentChamp()).getSpells().get(i) instanceof DamagingSpell) {
				currentChampionView.getSpellsButton().add(i, new SpellButton(
						((Wizard) task.getCurrentChamp()).getSpells().get(i).getName(), 1,
						((Wizard) task.getCurrentChamp()).getSpells().get(i).getCost(),
						((DamagingSpell) (((Wizard) task.getCurrentChamp()).getSpells().get(i))).getDamageAmount()));
				Spell object = ((Wizard) (task.getCurrentChamp())).getSpells().get(i);
				currentChampionView.getSpellsButton().get(i).setFocusable(false);
				currentChampionView.getSpellsButton().get(i).addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {

						if (e.getStateChange() == ItemEvent.SELECTED) {
							try {
								Direction d;
								JDialog.setDefaultLookAndFeelDecorated(true);
								int id = Integer.parseInt(JOptionPane.showInputDialog(null,
										"You are now casting Damaging Spell \n Please enter Target Cell : \n 1)FORWARD \n 2)BACKWARD \n 3)LEFT \n 4)RIGHT"));
								switch (id) {

								case 1: {
									d = Direction.FORWARD;
									break;
								}

								case 2: {
									d = Direction.BACKWARD;
									break;
								}
								case 3: {
									d = Direction.LEFT;
									break;
								}
								case 4: {
									d = Direction.RIGHT;
									break;
								}

								default: {
									JOptionPane.showMessageDialog(null, "PLEASE FOLLOW THE INSTRUCTIONS !!!!!",
											JOptionPane.MESSAGE_PROPERTY, 0);
									return;
								}

								}
								try {
									try {
										task.castDamagingSpell((DamagingSpell) object, d);
									} catch (FontFormatException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								} catch (InCooldownException e1) {
									JOptionPane.showMessageDialog(FirstTaskPanel.this,
											String.format("You should wait " + object.getCoolDown() + " Turns"));
									playsound("Buzzer.wav");
								} catch (NotEnoughIPException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), JOptionPane.MESSAGE_PROPERTY,
											0);
									playsound("Buzzer.wav");
								} catch (OutOfBordersException e1) {
									playsound("Buzzer.wav");
								} catch (InvalidTargetCellException e1) {

									playsound("Denied.wav");
								} catch (IOException e1) {
									e1.printStackTrace();
								}

							} catch (NumberFormatException e1) {
								JDialog.setDefaultLookAndFeelDecorated(true);
								JOptionPane.showMessageDialog(null, "PLEASE ENTER A VALID NUMBEEEERS !!!!!",
										JOptionPane.MESSAGE_PROPERTY, 0);
							}

						}
					}

				});

			} else if (((Wizard) task.getCurrentChamp()).getSpells().get(i) instanceof RelocatingSpell) {
				currentChampionView.getSpellsButton().add(i,
						new SpellButton(((Wizard) task.getCurrentChamp()).getSpells().get(i).getName(), 3,
								((Wizard) task.getCurrentChamp()).getSpells().get(i).getCost(),
								((RelocatingSpell) (((Wizard) task.getCurrentChamp()).getSpells().get(i))).getRange()));
				Spell object = ((Wizard) (task.getCurrentChamp())).getSpells().get(i);
				currentChampionView.getSpellsButton().get(i).setFocusable(false);
				currentChampionView.getSpellsButton().get(i).addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {

						if (e.getStateChange() == ItemEvent.SELECTED) {
							try {
								Direction d;
								JDialog.setDefaultLookAndFeelDecorated(true);
								int id = Integer.parseInt(JOptionPane.showInputDialog(null,
										"You are now casting Relocating Spell \n Please enter Target Cell : \n 1)FORWARD \n 2)BACKWARD \n 3)LEFT \n 4)RIGHT"));
								switch (id) {
								case 1: {
									d = Direction.FORWARD;
									break;
								}
								case 2: {
									d = Direction.BACKWARD;
									break;
								}
								case 3: {
									d = Direction.LEFT;
									break;
								}
								case 4: {
									d = Direction.RIGHT;
									break;
								}

								default: {
									JOptionPane.showMessageDialog(null, "PLEASE FOLLOW THE INSTRUCTIONS !!!!!",
											JOptionPane.MESSAGE_PROPERTY, 0);
									return;
								}

								}
								Direction d1;
								JDialog.setDefaultLookAndFeelDecorated(true);
								int id1 = Integer.parseInt(JOptionPane.showInputDialog(null,
										"You are now casting Relocating Spell \n CHOOSE DIRECTION FOR RELOCATING YOUR TARGET OBJECT :  \n 1)FORWARD \n 2)BACKWARD \n 3)LEFT \n 4)RIGHT"));
								switch (id1) {
								case 1: {
									d1 = Direction.FORWARD;
									break;
								}
								case 2: {
									d1 = Direction.BACKWARD;
									break;
								}
								case 3: {
									d1 = Direction.LEFT;
									break;
								}
								case 4: {
									d1 = Direction.RIGHT;
									break;
								}

								default: {
									JOptionPane.showMessageDialog(null, "PLEASE FOLLOW THE INSTRUCTIONS !!!!!",
											JOptionPane.MESSAGE_PROPERTY, 0);
									return;
								}

								}
								int r = Integer.parseInt(JOptionPane.showInputDialog(null,
										"You are now casting Relocating Spell \n ENTER RANGE : "));

								try {
									try {
										task.castRelocatingSpell((RelocatingSpell) object, d, d1, r);
									} catch (FontFormatException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								} catch (InCooldownException e1) {
									JOptionPane.showMessageDialog(FirstTaskPanel.this,
											String.format("You should wait " + object.getCoolDown() + " Turns"));
									playsound("Buzzer.wav");
								} catch (NotEnoughIPException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), JOptionPane.MESSAGE_PROPERTY,
											0);
									playsound("Buzzer.wav");
								} catch (OutOfBordersException e1) {
									playsound("Buzzer.wav");
								} catch (InvalidTargetCellException e1) {

									playsound("Denied.wav");
								} catch (OutOfRangeException e1) {
									playsound("Buzzer.wav");
								} catch (IOException e1) {
									e1.printStackTrace();
								}

							} catch (NumberFormatException e1) {
								JDialog.setDefaultLookAndFeelDecorated(true);
								JOptionPane.showMessageDialog(null, "PLEASE ENTER A VALID NUMBEEEERS !!!!!",
										JOptionPane.MESSAGE_PROPERTY, 0);
							}

						}
					}

				});

			} else if (((Wizard) task.getCurrentChamp()).getSpells().get(i) instanceof HealingSpell) {
				currentChampionView.getSpellsButton().add(i, new SpellButton(
						((Wizard) task.getCurrentChamp()).getSpells().get(i).getName(), 2,
						((Wizard) task.getCurrentChamp()).getSpells().get(i).getCost(),
						((HealingSpell) (((Wizard) task.getCurrentChamp()).getSpells().get(i))).getHealingAmount()));
				Spell object = ((Wizard) (task.getCurrentChamp())).getSpells().get(i);
				currentChampionView.getSpellsButton().get(i).setFocusable(false);

				currentChampionView.getSpellsButton().get(i).addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							try {
								try {
									task.castHealingSpell((HealingSpell) object);
								} catch (FontFormatException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} catch (InCooldownException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), JOptionPane.MESSAGE_PROPERTY, 0);
								playsound("Buzzer.wav");
							} catch (NotEnoughIPException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), JOptionPane.MESSAGE_PROPERTY, 0);
								playsound("Buzzer.wav");
							} catch (IOException e1) {

								e1.printStackTrace();
							}

						}
					}
				});
			}

			currentChampionView.getSpellsButton().get(i)
					.setSpellObject(((Wizard) task.getCurrentChamp()).getSpells().get(i));

		}

		if (!currentChampionView.getSpellsButton().isEmpty()) {

			currentSpell1 = currentChampionView.getSpellsButton().get(0);
			currentSpell2 = currentChampionView.getSpellsButton().get(1);
			currentSpell3 = currentChampionView.getSpellsButton().get(2);

		}
		remove(currentSpell1);
		remove(currentSpell2);
		remove(currentSpell3);

		currentSpell1.setBounds(1020, 500, 130, 80);
		currentSpell2.setBounds(1020, 586, 130, 80);
		currentSpell3.setBounds(1160, 500, 130, 80);
		currentSpell1.setToolTipText("Cooldown : " + currentSpell1.getSpellObject().getCoolDown());
		currentSpell2.setToolTipText("Cooldown : " + currentSpell2.getSpellObject().getCoolDown());
		currentSpell3.setToolTipText("Cooldown : " + currentSpell3.getSpellObject().getCoolDown());

		add(currentSpell1);
		add(currentSpell2);
		add(currentSpell3);

		currentSpell1.setVisible(true);
		currentSpell2.setVisible(true);
		currentSpell3.setVisible(true);

		hpBar.setValue(((Wizard) task.getCurrentChamp()).getHp());
		ipBar.setValue(((Wizard) task.getCurrentChamp()).getIp());
		hpBar.setMaximum(((Wizard) task.getCurrentChamp()).getDefaultHp());
		ipBar.setMaximum(((Wizard) task.getCurrentChamp()).getDefaultIp());

		remove(ablitesOfPotions);

		ablitesOfPotions = new JComboBox<String>();
		ablitesOfPotions.addItem("None");
		ablitesOfPotions.setFocusable(false);
		for (int i = 0; i < ((Wizard) task.getCurrentChamp()).getInventory().size(); i++) {
			ablitesOfPotions.addItem(((Wizard) task.getCurrentChamp()).getInventory().get(i).getName());
		}

		ablitesOfPotions.setBounds(1016, 460, 160, 30);

		add(ablitesOfPotions);

		validate();
		repaint();

	}

	@Override
	public void onDecreasingCurrentHP() {

		hpBar.setValue(((Wizard) task.getCurrentChamp()).getHp());
		hpBar.setString("HP: " + ((Wizard) task.getCurrentChamp()).getHp());

	}

	@Override
	public void onUsePotion() {

		ipBar.setValue(((Wizard) task.getCurrentChamp()).getIp());
		ipBar.setString("IP: " + ((Wizard) task.getCurrentChamp()).getIp());

		remove(ablitesOfPotions);

		ablitesOfPotions = new JComboBox<String>();
		ablitesOfPotions.addItem("None !");
		ablitesOfPotions.setFocusable(false);
		for (int i = 0; i < ((Wizard) task.getCurrentChamp()).getInventory().size(); i++) {

			ablitesOfPotions.addItem(((Wizard) task.getCurrentChamp()).getInventory().get(i).getName());
		}

		add(ablitesOfPotions);

		ablitesOfPotions.setBounds(1016, 460, 160, 30);

	}

	@Override
	public void onCastingDamagingSpell(int x, int y) {
		ipBar.setValue(((Wizard) task.getCurrentChamp()).getIp());
		ipBar.setString("IP: " + ((Wizard) task.getCurrentChamp()).getIp());

		setDamageX(x);
		setDamageY(y);

		onDamageSpell = true;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if ((!(task.getMap()[i][j] instanceof ObstacleCell)) && mapObstacleView[i][j] != null) {
					mapObstacleView[i][j] = null;
					break;
				}
			}
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				obstaclesInfo[i][j].setToolTipText("EMPTY");
				if (task.getMap()[i][j] instanceof ChampionCell) {
					obstaclesInfo[i][j].setToolTipText(
							"Name : " + ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getName() + "  HP: "
									+ ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getHp() + "   IP:"
									+ ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getIp());
				}
				if (task.getMap()[i][j] instanceof ObstacleCell) {
					obstaclesInfo[i][j]
							.setToolTipText("HP : " + ((ObstacleCell) task.getMap()[i][j]).getObstacle().getHp());

				}

			}
		}

	}

	@Override
	public void onRelocatingObstacle(int oldX, int oldY, int newX, int newY) {

		ipBar.setValue(((Wizard) task.getCurrentChamp()).getIp());
		ipBar.setString("IP: " + ((Wizard) task.getCurrentChamp()).getIp());
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				obstaclesInfo[i][j].setToolTipText("");
				if (task.getMap()[i][j] instanceof ChampionCell) {
					obstaclesInfo[i][j].setToolTipText(
							"Name : " + ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getName() + "  HP: "
									+ ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getHp() + "   IP:"
									+ ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getIp());
				}
				if (task.getMap()[i][j] instanceof ObstacleCell) {
					obstaclesInfo[i][j]
							.setToolTipText("HP : " + ((ObstacleCell) task.getMap()[i][j]).getObstacle().getHp());

				}

			}
		}

	}

	public void onRelocatingChampion(int oldX, int oldY, int newX, int newY) {

		ipBar.setValue(((Wizard) task.getCurrentChamp()).getIp());
		ipBar.setString("IP: " + ((Wizard) task.getCurrentChamp()).getIp());
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				obstaclesInfo[i][j].setToolTipText("");
				if (task.getMap()[i][j] instanceof ChampionCell) {
					obstaclesInfo[i][j].setToolTipText(
							"Name : " + ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getName() + "  HP: "
									+ ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getHp() + "   IP:"
									+ ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getIp());
				}
				if (task.getMap()[i][j] instanceof ObstacleCell) {
					obstaclesInfo[i][j]
							.setToolTipText("HP : " + ((ObstacleCell) task.getMap()[i][j]).getObstacle().getHp());

				}

			}
		}

	}

	@Override
	public void onCastingHealinSpell() {

		hpBar.setValue(((Wizard) task.getCurrentChamp()).getHp());
		hpBar.setString("HP: " + ((Wizard) task.getCurrentChamp()).getHp());

		ipBar.setValue(((Wizard) task.getCurrentChamp()).getIp());
		ipBar.setString("IP: " + ((Wizard) task.getCurrentChamp()).getIp());
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				obstaclesInfo[i][j].setToolTipText("");
				if (task.getMap()[i][j] instanceof ChampionCell) {
					obstaclesInfo[i][j].setToolTipText(
							"Name : " + ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getName() + "  HP: "
									+ ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getHp() + "   IP:"
									+ ((Wizard) ((ChampionCell) task.getMap()[i][j]).getChamp()).getIp());
				}

				if (task.getMap()[i][j] instanceof ObstacleCell) {
					obstaclesInfo[i][j]
							.setToolTipText("HP : " + ((ObstacleCell) task.getMap()[i][j]).getObstacle().getHp());

				}

			}
		}

	}

	@SuppressWarnings("unused")
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

	public static void main(String[] args) throws IOException, IndexOutOfBoundsException, InterruptedException {

		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s1 = new SlytherinWizard("slyth");

		ArrayList<Champion> s = new ArrayList<Champion>();
		g.setName("Ahmed Diab");
		h.setName("Karim Nashhat");
		r.setName("Mohamed Ihab");
		s1.setName("Welya");

//		s.add(g);
		s.add(h);

//		s.add(r);
//		s.add(s1);
		Tournament t = new Tournament();
		t.getChampions().add(g);
		t.getChampions().add(h);
		t.getChampions().add(r);
		t.getChampions().add(s1);
		t.beginTournament();

		g.getSpells().add(t.getSpells().get(0));
		g.getSpells().add(t.getSpells().get(11));
		g.getSpells().add(t.getSpells().get(4));

		h.getSpells().add(t.getSpells().get(3));
		h.getSpells().add(t.getSpells().get(5));
		h.getSpells().add(t.getSpells().get(12));

		r.getSpells().add(t.getSpells().get(0));
		r.getSpells().add(t.getSpells().get(0));
		r.getSpells().add(t.getSpells().get(0));
//
		s1.getSpells().add(t.getSpells().get(4));
		s1.getSpells().add(t.getSpells().get(10));
		s1.getSpells().add(t.getSpells().get(6));

		// ThirdTask t5;
		// try {
		// t5 = new ThirdTask(s);
		// } catch (IOException e1) {
		// TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// pp.getSelectedChampions().add(slytherinChampion);
		// pp.getSelectedChampions().add(slytherinChampion);
		// pp.getSelectedChampions().add(slytherinChampion);
		// pp.getSelectedChampions().add(slytherinChampion);
		FirstTaskPanel pp;
		pp = new FirstTaskPanel(new FirstTask(s));
		JFrame f = new JFrame();
		f.setLayout(null);
		f.setUndecorated(true);
		f.setVisible(true);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pp.setVisible(true);
		pp.setBounds(0, 0, 1380, 780);
		// pp.setBackground(Color.BLACK);
		f.add(pp);
		f.repaint();

		f.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();

				if (pp != null) {

					if (keyCode == KeyEvent.VK_LEFT) {
						try {
							try {
								pp.getTask().moveLeft();
							} catch (FontFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {
							pp.playsound("Buzzer.wav");
						} catch (InvalidTargetCellException e1) {
							pp.playsound("Denied.wav");
						} catch (IOException e1) {

							e1.printStackTrace();
						}

					}
				}
				if (pp != null) {

					if (keyCode == KeyEvent.VK_RIGHT) {
						try {
							try {
								pp.getTask().moveRight();
							} catch (FontFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {

							JOptionPane.showMessageDialog(f, e1.getLocalizedMessage());
						} catch (InvalidTargetCellException e1) {
							JOptionPane.showMessageDialog(f, e1.getLocalizedMessage());
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}
				}
				if (pp != null) {
					if (keyCode == KeyEvent.VK_UP) {
						try {
							try {
								pp.getTask().moveForward();
							} catch (FontFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {
							JOptionPane.showMessageDialog(f, e1.getLocalizedMessage());
						} catch (InvalidTargetCellException e1) {
							JOptionPane.showMessageDialog(f, e1.getLocalizedMessage());
						} catch (IOException e1) {

							e1.printStackTrace();
						}
					}
				}
				if (pp != null) {
					if (keyCode == KeyEvent.VK_DOWN) {
						try {
							try {
								pp.getTask().moveBackward();
							} catch (FontFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {
							JOptionPane.showMessageDialog(f, e1.getLocalizedMessage());
						} catch (InvalidTargetCellException e1) {
							JOptionPane.showMessageDialog(f, e1.getLocalizedMessage());
						} catch (IOException e1) {

							e1.printStackTrace();
						}

					}
				}

			}

		});

		// ThirdTask = new ThirdTask();
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

	@Override
	public void onMerpersonAttack(int i, int j) {
		// TODO Auto-generated method stub

	}
}