package harrypotter.view;

import harrypotter.model.magic.Spell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class SpellButton extends JToggleButton {
	private JLabel name;
	private JLabel logo;
	private JProgressBar cost;
	private JProgressBar value;
	private Spell spellObject;

	public SpellButton(int cost1, int value1) {
		setIcon(new ImageIcon("Choose.png"));
		;

		setLayout(new GridLayout(4, 0));
		UIManager.put("ProgressBar.selectionBackground", Color.black);
		UIManager.put("ProgressBar.selectionForeground", Color.white);
		UIManager.put("ProgressBar.foreground", Color.GREEN);

		setCost(new JProgressBar(0, 500));
		getCost().setValue(cost1);
		getCost().setString(value1 + "");
		getCost().setStringPainted(true);

		UIManager.put("ProgressBar.selectionBackground", Color.black);
		UIManager.put("ProgressBar.selectionForeground", Color.white);
		UIManager.put("ProgressBar.foreground", Color.BLUE);

		setValue(new JProgressBar(0, 500));
		getValue().setValue(value1);
		getValue().setString(value1 + "");
		getValue().setStringPainted(true);

		this.setLogo(new JLabel("Avada Cadavra"));
		this.name = new JLabel();
		ImageIcon i1 = new ImageIcon("facebook-icon.png");

		this.name = new JLabel(i1);

		add(this.name);
		add(this.getLogo());
		add(getCost());
		add(getValue());
	}

	public SpellButton(String name, int type, int cost1, int value1) {
		super.setActionCommand(name);
//		setOpaque(true);
			
//		setBackground(Color.red);
//		setIcon(new ImageIcon("magic-flame.jpg"));
//		;
		setRolloverIcon(new ImageIcon("SpellButton1.png"));
		setIcon(new ImageIcon("SpellButton.png"));
		setFocusable(false);
		setBorderPainted(false);
		setOpaque(false);
		setBackground(Color.white);
		setLayout(new GridLayout(4, 0));
		UIManager.put("ProgressBar.selectionBackground", Color.black);
		UIManager.put("ProgressBar.selectionForeground", Color.white);
		UIManager.put("ProgressBar.foreground", Color.GREEN);

		setCost(new JProgressBar(0, 500));

		getCost().setValue(cost1);
		getCost().setString(cost1 + "");
		getCost().setStringPainted(true);

		UIManager.put("ProgressBar.selectionBackground", Color.black);
		UIManager.put("ProgressBar.selectionForeground", Color.white);
		UIManager.put("ProgressBar.foreground", Color.BLUE);

		setValue(new JProgressBar(0, 700));
		getValue().setValue(value1);
		getValue().setString(value1 + "");
		getValue().setStringPainted(true);
		
		setForeground(Color.WHITE);
	
		
		JLabel L = new JLabel(name);
		L.setForeground(Color.white);
		this.setLogo(L);
		ImageIcon i1 = null;
		if (type == 1)
			i1 = new ImageIcon("Damaging Spell Icon.png");
		else if (type == 2)
			i1 = new ImageIcon("Healing Spell Icon.png");
		else if (type == 3)
			i1 = new ImageIcon("Relcoating Spell Icon.png");

		this.name = new JLabel(i1);

		add(this.name);
		add(this.getLogo());
		add(getCost());
		add(getValue());
		setForeground(Color.WHITE);
		UIManager.put("Button.foreground", Color.GREEN);
	}

	public SpellButton(ImageIcon b) {
		super(b);
	}

	@Override
	public void paintComponents(Graphics g) {

	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// f.setLocationRelativeTo(null);
		SpellButton s = new SpellButton("Avada Cadavra", 2, 100, 100);

		s.setBounds(0, 0, 180, 180);
		for (int i = 0; i < 3; i++) {
			for (int j = 5; j < 10; j++) {
				SpellButton s1 = new SpellButton("Avada Cadavra", 1, 100, 100);
				s1.setForeground(Color.BLUE);
				s1.setActionCommand("KK");
				s1.setOpaque(true);
//				s1.setBackground(Color.red);
				s1.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent ev) {
						// TODO Auto-generated method stub
						if (ev.getStateChange() == ItemEvent.SELECTED) {
							s1.setIcon(new ImageIcon("SpellButton.png"));
						} 
						else if(ev.getStateChange() == ItemEvent.DESELECTED)
							s1.setIcon(new ImageIcon("SpellButton1.png"));
						
					}
				});

				s1.setBounds(j * 130, i * 80, 130, 80);
				f.add(s1);
			}
		}
		for (int i = 4; i < 6; i++) {
			for (int j = 5; j < 10; j++) {
				SpellButton s1 = new SpellButton("Avada Cadavra", 2, 100, 10);

				s1.setBounds(j * 130, i * 80, 130, 80);
				f.add(s1);
			}
		}
		for (int i = 7; i < 8; i++) {
			for (int j = 5; j < 10; j++) {
				SpellButton s1 = new SpellButton("Avada Cadavra", 3, 10, 100);

				s1.setBounds(j * 130, i * 80, 130, 80);
				f.add(s1);
			}
		}

		f.validate();
		f.repaint();
	}

	public JProgressBar getCost() {
		return cost;
	}

	public void setCost(JProgressBar cost) {
		this.cost = cost;
	}

	public JProgressBar getValue() {
		return value;
	}

	public void setValue(JProgressBar value) {
		this.value = value;
	}

	public JLabel getLogo() {
		return logo;
	}

	public void setLogo(JLabel logo) {
		this.logo = logo;
	}

	public Spell getSpellObject() {
		return spellObject;
	}

	public void setSpellObject(Spell spellObject) {
		this.spellObject = spellObject;
	}

}
