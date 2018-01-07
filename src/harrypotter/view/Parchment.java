package harrypotter.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Parchment extends Animator {

	public Parchment(String path, int x, int y, int r, int c)
			throws IOException {
		BufferedImageLoader l = new BufferedImageLoader();
		BufferedImage bi = l.loadImage(path);
		SpriteSheet sp = new SpriteSheet(bi);
		ArrayList<BufferedImage> s = new ArrayList<BufferedImage>();
		this.setFrames(s);
		this.setSprite(bi);
		this.addAnimations(s, sp, x, y, r, c);
		this.start();
	}

	public void updateOnce() throws IndexOutOfBoundsException,
			InterruptedException {
		if (isRunning()) {
			setCurrentFrame(getCurrentFrame() + 1);

			try {
				setSprite(getFrames().get(getCurrentFrame()));

			} catch (IndexOutOfBoundsException e) {
				setFinished(true);
				pause();
				setCurrentFrame(getFrames().size() - 1);

				setSprite(getFrames().get(getCurrentFrame()));
			}

		}
	}

	public void updateReverseOnce() throws IndexOutOfBoundsException,
			InterruptedException {
		setFinished(false);
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

}
