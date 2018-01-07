package harrypotter.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SpellsPanel extends JPanel {
	private ArrayList<SpellButton> spellsButton = new ArrayList<SpellButton>();
	private Image spellBackground;

	private Image doubleBuffering;
	private Graphics doubleBufferGraphics;
	private int selectedButtons = 0;
	private JButton add;

	public SpellsPanel() throws IOException {

		setLayout(null);

		ImageIcon a = new ImageIcon("Choose-spells.gif");
		spellBackground = a.getImage();

		addDamageButtons("Damaging Spells.csv");
		addHealingButtons("Healing Spells.csv");
		addRelocatingButtons("Relocating Spells.csv");

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
		
		JLabel name = new JLabel(new ImageIcon("All Spell Icon word.png"));
		add(name);
		name.setBounds(00, 50, 400, 272);
		add = new JButton(new ImageIcon("add spell 1.png"));
		add.setRolloverIcon(new ImageIcon("add spell 2.png"));
		add.setBounds(350, 685, 170, 72);
		add.setBackground(Color.MAGENTA);
		add.setBorderPainted(false);
		add.setOpaque(false);
		add.setContentAreaFilled(false);
		add(add);
		repaint();
		validate();

	}

	public void addDamageButtons(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		int i = 1;
		int j = 3;
		while (line != null) {

			String[] content = line.split(",");
			System.out.println(line);
			System.out.println(content[0]);
			switch (content[0]) {
			case "DMG": {

				if (j == 7) {
					j = 3;
					i++;
				}

				SpellButton temp = new SpellButton(content[1], 1,
						Integer.parseInt(content[2]),
						Integer.parseInt(content[3]));

				temp.setToolTipText("Trait CoolDown : " + content[4]);
				getSpellsButton().add(temp);
				temp.setVisible(true);
				// temp.setIcon(new ImageIcon("Damaging Spell Icon1.png"));
				temp.setBounds((j * 145) + 250, (i * 75) + 15, 130, 75);
				add(temp);
				temp.setOpaque(false);
				  
				temp.setBorderPainted(false);
				temp.setRolloverIcon(new ImageIcon("SpellButton1.png"));
				temp.setIcon(new ImageIcon("SpellButton.png"));
				temp.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent ev) {

						if (ev.getStateChange() == ItemEvent.SELECTED) {

							selectedButtons++;
							temp.setIcon(new ImageIcon("SpellButton1.png"));
						}
						if (ev.getStateChange() == ItemEvent.DESELECTED) {
							selectedButtons--;
							temp.setIcon(new ImageIcon("SpellButton.png"));

						}
						if (selectedButtons > 3) {
						temp.setSelected(false);
						temp.setIcon(new ImageIcon("SpellButton.png"));
						
						JOptionPane.showMessageDialog(
									SpellsPanel.this,
									String.format("You cannot choose more than 3 Spells !!"));
							
						}

					}
				});
				
				break;

			}

			}

			line = br.readLine();
			j++;
			repaint();
		}

		br.close();

	}

	public void addHealingButtons(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();

		int i = 5;
		int j = 3;
		while (line != null) {

			String[] content = line.split(",");
			switch (content[0]) {
			case "HEL": {
				System.out.println("In");
				if (j == 7) {
					j = 3;
					i++;
				}
				SpellButton temp = new SpellButton(content[1], 2,
						Integer.parseInt(content[2]),
						Integer.parseInt(content[3]));
				//
				temp.setToolTipText("Trait CoolDown : " + content[4]);
				getSpellsButton().add(temp);
				Color r = new Color(186, 92, 9);
				temp.setBackground(r);
				// temp.setIcon(new ImageIcon("Damaging Spell Icon1.png"));
				add(temp);
				temp.setBounds((j * 145) + 250, (i * 75) + -20, 130, 75);
				temp.setIcon(new ImageIcon("SpellButton.png"));
				temp.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent ev) {

						if (ev.getStateChange() == ItemEvent.SELECTED) {

							selectedButtons++;
							temp.setIcon(new ImageIcon("SpellButton1.png"));
						}
						if (ev.getStateChange() == ItemEvent.DESELECTED) {
							selectedButtons--;
							temp.setIcon(new ImageIcon("SpellButton.png"));

						}
						if (selectedButtons > 3) {
						temp.setSelected(false);
						temp.setIcon(new ImageIcon("SpellButton.png"));
						
						JOptionPane.showMessageDialog(
									SpellsPanel.this,
									String.format("You cannot choose more than 3 Spells !!"));
							
						}

					}
				});
			}
				break;
			}

			line = br.readLine();
			j++;
		}

		br.close();

	}

	public void addRelocatingButtons(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();

		int i = 8;
		int j = 3;
		while (line != null) {

			String[] content = line.split(",");
			switch (content[0]) {
			case "REL": {
				if (j == 7) {
					j = 3;
					i++;
				}
				// temp1 = new JToggleButton(content[1]);
				//
				SpellButton temp = new SpellButton(content[1], 3,
						Integer.parseInt(content[2]),
						Integer.parseInt(content[3]));
				//
				getSpellsButton().add(temp);
				Color r = new Color(186, 92, 39);
				temp.setBackground(r);
				// temp.setIcon(new ImageIcon("Damaging Spell Icon1.png"));
				add(temp);
				temp.setBounds((j * 145) + 250, (i * 75) + -50, 130, 75);

				temp.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent ev) {

						if (ev.getStateChange() == ItemEvent.SELECTED) {

							selectedButtons++;
							temp.setIcon(new ImageIcon("SpellButton1.png"));
						}
						if (ev.getStateChange() == ItemEvent.DESELECTED) {
							selectedButtons--;
							temp.setIcon(new ImageIcon("SpellButton.png"));

						}
						if (selectedButtons > 3) {
						temp.setSelected(false);
						temp.setIcon(new ImageIcon("SpellButton.png"));
						
						JOptionPane.showMessageDialog(
									SpellsPanel.this,
									String.format("You cannot choose more than 3 Spells !!"));
							
						}

					}
				});
				break;
			}

			}

			line = br.readLine();
			j++;
		}
		br.close();

	}

	public void paint(Graphics g) {
		doubleBuffering = createImage(getWidth(), getHeight());
		doubleBufferGraphics = doubleBuffering.getGraphics();
		paintComponents(doubleBufferGraphics);
		g.drawImage(doubleBuffering, 0, 0, this);
	}

	@Override
	public void paintComponents(Graphics g) {
		try {
			g.drawImage(spellBackground, 0, 0, 1380, 780, this);
			g.drawImage(
					(new BufferedImageLoader()).loadImage("Choose Spell.png"),
					220, 0, 900, 90, this);

		} catch (IOException e) {
			e.printStackTrace();
		}
//		add.setBounds(20,450, 170, 72);
		super.paintComponents(g);
		repaint();
	}

	public ArrayList<String> collectSpells() {
		ArrayList<String> a = new ArrayList<String>();
		for (int i = 0; i < getSpellsButton().size(); i++) {
			if (getSpellsButton().get(i).isSelected()) {
				a.add(getSpellsButton().get(i).getActionCommand());
				getSpellsButton().get(i).setSelected(false);
			}
		}
		return a;
	}

	public int getSelectedButtons() {
		return selectedButtons;
	}

	public void setSelectedButtons(int selectedButtons) {
		this.selectedButtons = selectedButtons;
	}

	public JButton getAdd() {
		return add;
	}

	public void setAdd(JButton add) {
		this.add = add;
	}

	public ArrayList<SpellButton> getSpellsButton() {
		return spellsButton;
	}

	public void setSpellsButton(ArrayList<SpellButton> spellsButton) {
		this.spellsButton = spellsButton;
	}

}
