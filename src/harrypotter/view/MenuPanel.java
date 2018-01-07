package harrypotter.view;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel implements KeyListener {
	private Image doubleBuffering;
	private Graphics doubleBufferGraphics;
	private Parchment parchment = new Parchment("Sprite Sheet Open Frame.PNG",
			407, 719, 3, 6);
	
	private Image background;
	private boolean onOpen;
	
	private JButton start;
	private JButton exit;

	public MenuPanel() throws IOException, IndexOutOfBoundsException,
			InterruptedException {


onOpen=false;
		ImageIcon a = new ImageIcon("menu panel.gif");
		background = a.getImage();
		parchment.getFrames().remove(13);
		parchment.getFrames().remove(13);
		parchment.getFrames().remove(13);
		parchment.getFrames().remove(13);
		parchment.getFrames().remove(13);
		setLayout(null);

		setStart(new JButton(new ImageIcon("new GameButton.png")));
		start.setBounds(340, 100, 247, 102);
		start.setOpaque(false);
		start.setRolloverIcon(new ImageIcon("new GameButton1.png"));
		start.setBackground(Color.red);
		start.setBorderPainted(false);
		start.setContentAreaFilled(false);
	
		this.add(getStart());

		exit = new JButton(new ImageIcon("Quit.png"));
		exit.setBackground(Color.RED);
		exit.setBorderPainted(false);

		exit.setBounds(330, 460, 247, 102);
		JPanel p = new JPanel();
		p.setBounds(100, 100, 500, 500);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		add(exit);
		exit.setVisible(false);
		p.setOpaque(false);
		add(p);
		start.setVisible(false);
		p.setDoubleBuffered(false);
		exit.setOpaque(false);
		exit.setDoubleBuffered(false);
		
		exit.setContentAreaFilled(false);
		
		exit.setRolloverIcon(new ImageIcon("Quit1.png"));
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

		// validate();
		// repaint();

		
		// requestFocusInWindow();
		// setFocusTraversalKeysEnabled(false);
	}

	public void paint(Graphics g) {
		doubleBuffering = createImage(getWidth(), getHeight());
		doubleBufferGraphics = doubleBuffering.getGraphics();
		paintComponents(doubleBufferGraphics);
		g.drawImage(doubleBuffering, 0, 0, this);
	}

	@Override
	public void paintComponents(Graphics g) {
		// super.paintComponents(g);
		g.drawImage(background, 0, 0, 1366, 767, this);

//		if (parchment != null) {
			if (onOpen!=true) {

				getStart().setVisible(true);
				getExit().setVisible(true);
				
			}
			try {
				parchment.updateOnce();

			} catch (IndexOutOfBoundsException | InterruptedException e1) {
				e1.printStackTrace();
			}
//			g.drawImage(parchment.getSprite(), 500, -205, 420, 1200, this);

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//		}
		repaint();
		super.paintComponents(g);
	}

	public void setBackground(Image background) {
		this.background = background;
	}

	public Parchment getParchment() {
		return parchment;
	}

	public void setParchment(Parchment parchment) {
		this.parchment = parchment;
	}

	public JButton getStart() {
		return start;
	}

	public void setStart(JButton start) {
		this.start = start;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_SPACE)
			System.exit(0);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_SPACE)
			System.exit(0);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean isFocusTraversable() {
		return true;
	}

	

	public boolean isOnOpen() {
		return onOpen;
	}

	public void setOnOpen(boolean onOpen) {
		this.onOpen = onOpen;
	}

}
