package harrypotter.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;




public class Player extends Animator {
	private String champion;
	private String house;
	
	private ArrayList<String> spells;
	
	private ArrayList<SpellButton>spellsButton=new ArrayList<SpellButton>();
	private Player animationIdle;
	private ArrayList<Player>animationsViewMap= new ArrayList<Player>() ;
	
	private BufferedImage WizardName;
	private String userName;
	private int x;
	private int y;
	
	private int rank;
	

	public Player(String path, int x, int y, int r, int c, 
			String house) throws IOException {
		this.house = house;
		BufferedImageLoader l = new BufferedImageLoader();
		BufferedImage bi = l.loadImage(path);
		SpriteSheet sp = new SpriteSheet(bi);
		ArrayList<BufferedImage> s = new ArrayList<BufferedImage>();
		this.setFrames(s);
		this.setSprite(bi);
		this.addAnimations(s, sp, x, y, r, c);
		this.start();
		
	}

	public Player(BufferedImage spriteSheet, int x, int y, int r, int c) throws IOException {

		
		SpriteSheet sp = new SpriteSheet(spriteSheet);
		ArrayList<BufferedImage> s = new ArrayList<BufferedImage>();
		this.setFrames(s);
		this.setSprite(spriteSheet);
		this.addAnimationsByRow(s, sp, x, y, r, c);
		this.start();
		
	}
	public Player(BufferedImage spriteSheet, int x, int y, int r, int c,String house) throws IOException {

		
		SpriteSheet sp = new SpriteSheet(spriteSheet);
		ArrayList<BufferedImage> s = new ArrayList<BufferedImage>();
		this.setFrames(s);
		this.setSprite(spriteSheet);
		this.addAnimations(s, sp, x, y, r, c);
		this.start();
		this.house=house;
		
	}

	public Player(ArrayList<BufferedImage> frames) {

		setFrames(frames);
	}
	public Player(){
		
	}


	public void updateOnce() throws IndexOutOfBoundsException,
			InterruptedException {
		if (isRunning()) {
			setCurrentFrame(getCurrentFrame() + 1);

			try {
				setSprite(getFrames().get(getCurrentFrame()));

			} catch (IndexOutOfBoundsException e) {
				setFinished(true);
				setRunning(false);
				setCurrentFrame(getFrames().size() - 1);
				setSprite(getFrames().get(getCurrentFrame()));
			}

		}
	}

	public void updateReverseOnce() throws IndexOutOfBoundsException,
			InterruptedException {
		if (isRunning()) {
			setCurrentFrame(getCurrentFrame() - 1);

			try {
				setSprite(getFrames().get(getCurrentFrame()));

			} catch (IndexOutOfBoundsException e) {
				setFinished(true);
				pause();
				setCurrentFrame(0);
				setSprite(getFrames().get(getCurrentFrame()));
			}

		}
	}

	public String getChampion() {
		return champion;
	}

	public void setChampion(String champion) {
		this.champion = champion;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public ArrayList<String> getSpells() {
		return spells;
	}

	public void setSpells(ArrayList<String> spells) {
		this.spells = spells;
	}

	public ArrayList<SpellButton> getSpellsButton() {
		return spellsButton;
	}

	public void setSpellsButton(ArrayList<SpellButton> spellsButton) {
		this.spellsButton = spellsButton;
	}

	public ArrayList<Player> getAnimationsViewMap() {
		return animationsViewMap;
	}

	public void setAnimationsViewMap(ArrayList<Player> animationsViewMap) {
		this.animationsViewMap = animationsViewMap;
	}

	public Player getAnimationIdle() {
		return animationIdle;
	}

	public void setAnimationIdle(Player animationIdle) {
		this.animationIdle = animationIdle;
	}

	public BufferedImage getWizardName() {
		return WizardName;
	}

	public void setWizardName(BufferedImage wizardName) {
		WizardName = wizardName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	

}
