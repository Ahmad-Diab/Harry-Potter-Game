package harrypotter.view;

import java.awt.FontFormatException;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.tournament.SwapMapListener;
import harrypotter.model.tournament.Tournament;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements SwapMapListener {
	
	private MenuPanel map = new MenuPanel();
	private ChoosePanel choice = new ChoosePanel();
	private SpellsPanel spellsChoice = new SpellsPanel();
	private ParticipantPanel participant;

	private FirstTaskWinnersPanel firstWinners;
	private SecondTaskWinnersPanel secondWinners;
	private TriwizardChampion thirdWinners;

	private Tournament tournment = new Tournament();

	private FirstTaskPanel firstTaskView;
	private SecondTaskPanel secondTaskView;
	private ThirdTaskPanel thirdTaskView;

	private FirstTaskReview firstTaskReview;
	private SecondTaskReview secondTaskReview;
	private ThirdTaskReview thirdTaskReview;

	private GameOver gameover ;

	public GameFrame() throws IOException, IndexOutOfBoundsException, InterruptedException, FontFormatException {

		tournment.setSwitchListener(this);
		setFocusable(true);
		setAutoRequestFocus(true);

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (firstTaskView != null) {

					if (keyCode == KeyEvent.VK_LEFT) {
						try {
							try {

								firstTaskView.getTask().moveLeft();

							} catch (FontFormatException e1) {
								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {

							playsound("Buzzer.wav");
						} catch (InvalidTargetCellException e1) {
							playsound("Denied.wav");

						} catch (IOException e1) {

							e1.printStackTrace();
						}

					}
				}
				if (firstTaskView != null) {

					if (keyCode == KeyEvent.VK_RIGHT) {
						try {
							try {
								firstTaskView.getTask().moveRight();
							} catch (FontFormatException e1) {

								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {

							playsound("Buzzer.wav");
						} catch (InvalidTargetCellException e1) {
							playsound("Denied.wav");
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}
				}
				if (firstTaskView != null) {
					if (keyCode == KeyEvent.VK_UP) {
						try {
							try {
								firstTaskView.getTask().moveForward();
							} catch (FontFormatException e1) {

								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {
							playsound("Buzzer.wav");
						} catch (InvalidTargetCellException e1) {
							playsound("Denied.wav");
						} catch (IOException e1) {

							e1.printStackTrace();
						}
					}
				}
				if (firstTaskView != null) {
					if (keyCode == KeyEvent.VK_DOWN) {
						try {
							try {
								firstTaskView.getTask().moveBackward();
							} catch (FontFormatException e1) {

								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {
							playsound("Buzzer.wav");
						} catch (InvalidTargetCellException e1) {
							playsound("Denied.wav");
						} catch (IOException e1) {

							e1.printStackTrace();
						}

					}
				}
				if (secondTaskView != null) {

					if (keyCode == KeyEvent.VK_LEFT) {
						try {
							try {
								secondTaskView.getTask().moveLeft();
							} catch (FontFormatException e1) {

								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {
							playsound("Buzzer.wav");
						} catch (InvalidTargetCellException e1) {
							playsound("Denied.wav");
						} catch (IOException e1) {

							e1.printStackTrace();
						}

					}
				}
				if (secondTaskView != null) {

					if (keyCode == KeyEvent.VK_RIGHT) {
						try {
							try {
								secondTaskView.getTask().moveRight();
							} catch (FontFormatException e1) {

								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {

							playsound("Buzzer.wav");
						} catch (InvalidTargetCellException e1) {
							playsound("Denied.wav");
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}
				}
				if (secondTaskView != null) {
					if (keyCode == KeyEvent.VK_UP) {
						try {
							try {
								secondTaskView.getTask().moveForward();
							} catch (FontFormatException e1) {

								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {
							playsound("Buzzer.wav");
						} catch (InvalidTargetCellException e1) {
							playsound("Denied.wav");
						} catch (IOException e1) {

							e1.printStackTrace();
						}
					}
				}
				if (secondTaskView != null) {
					if (keyCode == KeyEvent.VK_DOWN) {
						try {
							try {
								secondTaskView.getTask().moveBackward();
							} catch (FontFormatException e1) {

								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {
							playsound("Buzzer.wav");
						} catch (InvalidTargetCellException e1) {
							playsound("Denied.wav");
						} catch (IOException e1) {

							e1.printStackTrace();
						}

					}
				}
				if (thirdTaskView != null) {

					if (keyCode == KeyEvent.VK_LEFT) {
						try {
							try {
								thirdTaskView.getTask().moveLeft();
							} catch (FontFormatException e1) {

								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {
							playsound("Buzzer.wav");
						} catch (InvalidTargetCellException e1) {
							playsound("Denied.wav");
						} catch (IOException e1) {

							e1.printStackTrace();
						}

					}
				}
				if (thirdTaskView != null) {

					if (keyCode == KeyEvent.VK_RIGHT) {
						try {
							try {
								thirdTaskView.getTask().moveRight();
							} catch (FontFormatException e1) {

								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {

							playsound("Buzzer.wav");
						} catch (InvalidTargetCellException e1) {
							playsound("Denied.wav");
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}
				}
				if (thirdTaskView != null) {
					if (keyCode == KeyEvent.VK_UP) {
						try {
							try {
								thirdTaskView.getTask().moveForward();
							} catch (FontFormatException e1) {

								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {
							playsound("Buzzer.wav");
						} catch (InvalidTargetCellException e1) {
							playsound("Denied.wav");
						} catch (IOException e1) {

							e1.printStackTrace();
						}
					}
				}
				if (thirdTaskView != null) {
					if (keyCode == KeyEvent.VK_DOWN) {
						try {
							try {
								thirdTaskView.getTask().moveBackward();
							} catch (FontFormatException e1) {

								e1.printStackTrace();
							}
						} catch (OutOfBordersException e1) {
							playsound("Buzzer.wav");
						} catch (InvalidTargetCellException e1) {
							playsound("Denied.wav");
						} catch (IOException e1) {

							e1.printStackTrace();
						}

					}
				}

			}

		});
		
		BufferedImageLoader b4 = new BufferedImageLoader();
		BufferedImage r = b4.loadImage("26747.png");
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(r).getImage(), new Point(8, 8),
				"custom cursor"));
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		getMap().setBounds(0, 0, 1380, 780);
		getContentPane().add(getMap());

		getMap().getStart().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playsound("Harry Potter and the Sorcerer's Stone Soundtrack - 01. Prologue.wav");
				getMap().remove(getMap().getExit());
				getMap().remove(getMap().getStart());

				ImageIcon a = new ImageIcon("1.gif");

				getMap().setBackground(a.getImage());

				Timer timer = new Timer(7000, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (getChoice() != null)
							getChoice().setBounds(0, 0, 1380, 780);
						if (getMap() != null)
							getContentPane().remove(getMap());
						setMap(null);
						if (getChoice() != null)
							getContentPane().add(getChoice());
						
						
						

						repaint();
						validate();

					}
				});

				timer.setInitialDelay(8600);
				timer.start();

			}

		});
		repaint();
		choice.getEnterName().setFocusable(true);

		getChoice().getChoose().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getChoice().getEnterName().getText().equals("")) {
					JOptionPane.showMessageDialog(GameFrame.this, String.format("Please Enter Your NAME !!"));
				} else {

					spellsChoice.setBounds(0, 0, 1380, 780);
					getChoice().setVisible(false);
					remove(getChoice());

					add(spellsChoice);

					getChoice().getcWizard().setChampion(getChoice().getEnterName().getText());
					getChoice().getSelectedChampions().add(getChoice().getcWizard());
					getChoice().getcWizard().setUserName(getChoice().getEnterName().getText());
					getChoice().getWizards().remove(getChoice().getcWizard());
					getChoice().getSelectedWizards().add(getChoice().getCurrentWizard());
					getChoice().getWizardName().remove(getChoice().getCurrentWizard());

					validate();
					repaint();

				}
			}

		});

		spellsChoice.getAdd().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				if (spellsChoice.getSelectedButtons() < 3) {
					if (3 - spellsChoice.getSelectedButtons() == 1)
						JOptionPane.showMessageDialog(GameFrame.this,
								String.format("You should enter another Spell !!"));
					else {
						int d = 3 - spellsChoice.getSelectedButtons();
						JOptionPane.showMessageDialog(GameFrame.this,
								String.format("You should enter another " + d + " Spells !!"));
					}
				} else if (spellsChoice.getSelectedButtons() == 3) {

					getChoice().getcWizard().setSpells(spellsChoice.collectSpells());
					if (getChoice().getWizards().isEmpty()) {
						Champion c = null;
						// Create champions after choosing them all
						for (int i = 0; i < getChoice().getSelectedChampions().size(); i++) {
							if (getChoice().getSelectedChampions().get(i).getHouse().equals("Gryffindor")) {
								c = new GryffindorWizard(getChoice().getSelectedChampions().get(i).getChampion());
							}
							if (getChoice().getSelectedChampions().get(i).getHouse().equals("Hufflepuff")) {
								c = new HufflepuffWizard(getChoice().getSelectedChampions().get(i).getChampion());
							}
							if (getChoice().getSelectedChampions().get(i).getHouse().equals("Ravenclaw")) {
								c = new RavenclawWizard(getChoice().getSelectedChampions().get(i).getChampion());
							}
							if (getChoice().getSelectedChampions().get(i).getHouse().equals("Slytherin")) {
								c = new SlytherinWizard(getChoice().getSelectedChampions().get(i).getChampion());
							}

							tournment.addChampion(c);
							if (getChoice().getSelectedChampions().get(i).getSpells() != null)
								for (int j = 0; j < getChoice().getSelectedChampions().get(i).getSpells().size(); j++) {
									for (int k = 0; k < tournment.getSpells().size(); k++) {

										if (getChoice().getSelectedChampions().get(i).getSpells().get(j)
												.equals(tournment.getSpells().get(k).getName())) {
											((Wizard) c).getSpells().add(tournment.getSpells().get(k));

										}

									}
								}

						}
						try {
							tournment.beginTournament();

						} catch (IOException e) {
							e.printStackTrace();
						}

						// TODO PARTICIPANT PANEL

						remove(spellsChoice);
						
						try {
							participant = new ParticipantPanel(getChoice().getSelectedChampions(),
									tournment.getFirstTask());
						} catch (IOException e1) {

							e1.printStackTrace();
						} catch (FontFormatException e1) {

							e1.printStackTrace();
						}
						participant.setBounds(0, 0, 1380, 780);
						add(participant);

						choice.setParchment(null);
						choice.setGryffindorChampion(null);
						choice.setHufflepuffChampion(null);
						choice.setRavenclawChampion(null);
						choice.setSlytherinChampion(null);
						choice.setBackground(null);
						choice.a=null;
						spellsChoice.setSpellsButton(null);
						setChoice(null);
						spellsChoice = null;

						participant.getBeginTournment().addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {

								getContentPane().remove(participant);

								participant = null;

								try {
									firstTaskReview = new FirstTaskReview();
								} catch (IOException | FontFormatException e1) {
									e1.printStackTrace();
								}

								firstTaskReview.setBounds(0, 0, 1380, 780);

								getContentPane().add(firstTaskReview);
								System.out.println("print in begin");
								firstTaskReview.getNext().addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										getContentPane().remove(firstTaskReview);

										try {
											firstTaskView = new FirstTaskPanel(tournment.getFirstTask());
										} catch (IOException | IndexOutOfBoundsException | InterruptedException e1) {

											e1.printStackTrace();
										}

										firstTaskView.setBounds(0, 0, 1380, 780);

										getContentPane().add(firstTaskView);
										
										
										
										firstTaskReview = null;
										System.out.println("print in begin");

									}
								});

							}
						});

					} else {
						getChoice().setVisible(true);
						getContentPane().remove(spellsChoice);
						getContentPane().add(getChoice());

						System.out.println(getChoice().getSelectedChampions()
								.get(getChoice().getSelectedChampions().size() - 1).getSpells().size());
						getChoice().getSelectedSpells().add(spellsChoice.collectSpells());

						if (!getChoice().getWizards().isEmpty()) {
							getChoice().setCurrentIndexW(0);

							getChoice().setcWizard(getChoice().getWizards().get(getChoice().getCurrentIndexW()));

							getChoice()
									.setCurrentWizard(getChoice().getWizardName().get(getChoice().getCurrentIndexW()));

							repaint();
						}
						getChoice().getEnterName().setText("");
					}
				}
				repaint();
			}
		});
		// if(participant!=null)

		repaint();
		validate();

	}

	public static void main(String[] args)
			throws IOException, IndexOutOfBoundsException, InterruptedException, FontFormatException {

		new GameFrame();

	}

	public SpellsPanel getSpellsChoice() {
		return spellsChoice;
	}

	public void setSpellsChoice(SpellsPanel spellsChoice) {
		this.spellsChoice = spellsChoice;
	}

	public FirstTaskPanel getFirstTaskView() {
		return firstTaskView;
	}

	public void setFirstTaskView(FirstTaskPanel firstTaskView) {
		this.firstTaskView = firstTaskView;
	}

	@Override
	public void onFinishingFirstTask() throws IOException, FontFormatException {

		if (tournment.getSecondTask() != null) {

			firstWinners = new FirstTaskWinnersPanel(tournment.getFirstTask().getWinners());
			getContentPane().remove(firstTaskView);
			firstWinners.setBounds(0, 0, 1380, 780);
			getContentPane().add(firstWinners);
			firstTaskView = null;
			firstWinners.getNext().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					remove(firstWinners);
					try {
						secondTaskReview = new SecondTaskReview();
					} catch (IOException | FontFormatException e1) {

						e1.printStackTrace();
					}

					secondTaskReview.setBounds(0, 0, 1380, 780);

					getContentPane().add(secondTaskReview);
					System.out.println("print in begin");
					firstWinners = null;
					secondTaskReview.getNext().addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							getContentPane().remove(secondTaskReview);

							try {
								secondTaskView = new SecondTaskPanel(tournment.getSecondTask());
							} catch (IOException | IndexOutOfBoundsException e1) {

								e1.printStackTrace();
							}

							secondTaskView.setBounds(0, 0, 1380, 780);

							getContentPane().add(secondTaskView);
							System.out.println("print in begin");
							secondTaskReview = null;

							validate();
							repaint();

						}
					});

					/////////////////////////////////////////////////////////////////

					validate();
					repaint();

				}
			});
			validate();
			repaint();

			////////////////////////////////////////////////////

		} else {
			gameover = new GameOver();
			remove(firstTaskView);
			gameover.setBounds(0, 0, 1380, 780);
			add(gameover);
			firstTaskView = null;
			repaint();
			validate();
			gameover.getPlayAgain().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						choice = new ChoosePanel();
					} catch (IOException e1) {

						e1.printStackTrace();
					} catch (FontFormatException e1) {
						e1.printStackTrace();
					}
					remove(gameover);
					choice.setBounds(0, 0, 1380, 780);
					add(choice);
					GameFrame.this.dispose();
					try {
						GameFrame f = new GameFrame();
						f.remove(f.getMap());
						f.getChoice().setBounds(0, 0, 1380, 780);
						f.add(f.getChoice());

					} catch (IndexOutOfBoundsException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {

						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					} catch (FontFormatException e1) {
						e1.printStackTrace();
					}

				}
			});

		}

	}

	@Override
	public void onFinishingSecondTask() throws IOException, FontFormatException {
		if (tournment.getThirdTask() != null) {

			secondWinners = new SecondTaskWinnersPanel(tournment.getSecondTask().getWinners());
			secondWinners.setBounds(0, 0, 1380, 780);
			getContentPane().remove(secondTaskView);
			getContentPane().add(secondWinners);
			secondTaskView = null;
			
			secondWinners.getNext().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					remove(secondWinners);
					secondWinners = null;

					try {
						thirdTaskReview = new ThirdTaskReview();
					} catch (IOException | FontFormatException e1) {

						e1.printStackTrace();
					}

					thirdTaskReview.setBounds(0, 0, 1380, 780);

					getContentPane().add(thirdTaskReview);
					System.out.println("print in begin");

					thirdTaskReview.getNext().addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							getContentPane().remove(thirdTaskReview);

							try {
								thirdTaskView = new ThirdTaskPanel(tournment.getThirdTask());
							} catch (IOException | IndexOutOfBoundsException e1) {

								e1.printStackTrace();
							}

							thirdTaskView.setBounds(0, 0, 1380, 780);
							thirdTaskReview = null;

							getContentPane().add(thirdTaskView);
							System.out.println("print in begin");

							validate();
							repaint();

						}
					});

				}
			});
			validate();
			repaint();

		} else {
			gameover = new GameOver();
			remove(secondTaskView);
			gameover.setBounds(0, 0, 1380, 780);
			add(gameover);
			secondTaskView = null;
			repaint();
			validate();
			gameover.getPlayAgain().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						choice = new ChoosePanel();
					} catch (IOException e1) {

						e1.printStackTrace();
					} catch (FontFormatException e1) {
						e1.printStackTrace();
					}
					remove(gameover);
					choice.setBounds(0, 0, 1380, 780);
					add(choice);
					GameFrame.this.dispose();
					try {
						GameFrame f = new GameFrame();
						f.remove(f.getMap());
						f.getChoice().setBounds(0, 0, 1380, 780);
						f.add(f.getChoice());

					} catch (IndexOutOfBoundsException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					} catch (FontFormatException e1) {
						e1.printStackTrace();
					}

				}
			});

		}

	}

	@Override
	public void onFinishingThirdTask() throws IOException, FontFormatException {

		if (tournment.getThirdTask().getWinner() != null) {
			thirdWinners = new TriwizardChampion(tournment.getThirdTask().getWinner());
			thirdWinners.setBounds(0, 0, 1380, 780);
			getContentPane().remove(thirdTaskView);

			getContentPane().add(thirdWinners);
			thirdTaskView = null;
			thirdWinners.getNext().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					try {
						gameover = new GameOver();
					} catch (IOException e) {

						e.printStackTrace();
					}
					remove(thirdWinners);
					gameover.setBounds(0, 0, 1380, 780);
					add(gameover);
					// secondTaskView=null;
					repaint();
					validate();

				}
			});
			validate();
			repaint();

		} else {
			gameover = new GameOver();
			remove(thirdTaskView);
			gameover.setBounds(0, 0, 1380, 780);
			add(gameover);
			thirdTaskView = null;
			repaint();
			validate();
			gameover.getPlayAgain().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						choice = new ChoosePanel();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (FontFormatException e1) {
						e1.printStackTrace();
					}
					remove(gameover);
					choice.setBounds(0, 0, 1380, 780);
					add(choice);
					GameFrame.this.dispose();
					try {
						GameFrame f = new GameFrame();
						f.remove(f.getMap());
						f.getChoice().setBounds(0, 0, 1380, 780);
						f.add(f.getChoice());

					} catch (IndexOutOfBoundsException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					} catch (FontFormatException e1) {
						e1.printStackTrace();
					}

				}
			});

		}

	}

	public ParticipantPanel getParticipant() {
		return participant;
	}

	public void setParticipant(ParticipantPanel participant) {
		this.participant = participant;
	}

	public FirstTaskWinnersPanel getFirstWinners() {
		return firstWinners;
	}

	public void setFirstWinners(FirstTaskWinnersPanel firstWinners) {
		this.firstWinners = firstWinners;
	}

	public TriwizardChampion getThirdWinners() {
		return thirdWinners;
	}

	public void setThirdWinners(TriwizardChampion thirdWinners) {
		this.thirdWinners = thirdWinners;
	}

	public GameOver getGameover() {
		return gameover;
	}

	public void setGameover(GameOver gameover) {
		this.gameover = gameover;
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

	public MenuPanel getMap() {
		return map;
	}

	public void setMap(MenuPanel map) {
		this.map = map;
	}

	public ChoosePanel getChoice() {
		return choice;
	}

	public void setChoice(ChoosePanel choice) {
		this.choice = choice;
	}

}
