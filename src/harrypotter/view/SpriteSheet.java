package harrypotter.view;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage spriteSheet;

	public SpriteSheet(BufferedImage sp) {
		setSpriteSheet(sp);
	}

	public BufferedImage grabImage(int x, int y, int width, int height) {
		BufferedImage sprite = getSpriteSheet().getSubimage(x, y, width, height);
		return sprite;
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

}
