package harrypotter.view;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animator  {

	private ArrayList<BufferedImage> frames;
	private BufferedImage sprite;
	private int frameAtPause, currentFrame;
	private  boolean running = false;
	private boolean finished = false;

	public Animator() {

	}

	public boolean isRunning() {
		return running;
	}

	public Animator(ArrayList<BufferedImage> frames) {

		this.setFrames(frames);
	}

	public void addAnimations(ArrayList<BufferedImage> bf, SpriteSheet sp,
			int x, int y, int r, int c) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				bf.add(sp.grabImage(j * x, i * y, x, y));

			}
		}

	}

	public void addAnimationsByRow(ArrayList<BufferedImage> bf, SpriteSheet sp,
			int x, int y, int r, int c) {
		for (int j = 0; j < c; j++) {
			bf.add(sp.grabImage(j * x, r * y, x, y));

		}

	}

	public void update() {

		if (isRunning()) {
			setCurrentFrame(getCurrentFrame() + 1);

			try {
				setSprite(getFrames().get(getCurrentFrame()));

			} catch (IndexOutOfBoundsException e) {
				// pause();
				setCurrentFrame(0);
				setSprite(getFrames().get(getCurrentFrame()));
			}

		}
	}

	public void updateOnc(int x) throws IndexOutOfBoundsException,
			InterruptedException {
		if (!isRunning()) {
			resume1();
		}
		if (getCurrentFrame() != x) {
			// resume1();

			setCurrentFrame(getCurrentFrame() + 1);

			try {
				setSprite(getFrames().get(getCurrentFrame()));
				if (getCurrentFrame() == x) {
					pause();

					// Animator.sleep(1000);
					resume1();

				}

			} catch (IndexOutOfBoundsException e) {
				// pause();
				// stop();
				setCurrentFrame(0);
				setSprite(getFrames().get(getCurrentFrame()));
			}

		} else {
			if (getCurrentFrame() == x) {
				pause();
				// Timer t= new Timer(1000,new ActionListener() {

				// @Override
				// public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				resume1();
			}
			// });

			// Animator.sleep(1000);

		}
	}

	// }

	public void update4() {
		while (isRunning()) {
			while (getCurrentFrame() < getFrames().size()) {
				setCurrentFrame(getCurrentFrame() + 1);
				try {
					setSprite(getFrames().get(getCurrentFrame()));
				} catch (IndexOutOfBoundsException e) {
					// pause();
					setCurrentFrame(0);
					setSprite(getFrames().get(getCurrentFrame()));
				}

			}
		}
	}

	public void updateLoop(int x) throws InterruptedException {
		if (isRunning()) {
			setCurrentFrame(getCurrentFrame() + 1);

			try {
				setSprite(getFrames().get(getCurrentFrame()));
				if (isRunning()) {
					if (getCurrentFrame() == x) {
						setCurrentFrame(getCurrentFrame() + 1);
						try {
							setSprite(getFrames().get(getCurrentFrame()));
						} catch (IndexOutOfBoundsException e) {

							setCurrentFrame(x);
							setSprite(getFrames().get(getCurrentFrame()));
						}
					}
				}
			} catch (IndexOutOfBoundsException e) {
				// pause();
				setCurrentFrame(0);
				setSprite(getFrames().get(getCurrentFrame()));
			}

		}
	}

	public void pauseAtIndex(int x) {
		if (getCurrentFrame() == x)
			this.pause();

	}

	public void resumeAtIndex(int x) {
		if (getCurrentFrame() == x)
			resume1();
	}

	public void start() {
		setRunning(true);
		// previousTime=0;
		setFrameAtPause(0);
		setCurrentFrame(0);
	}

	public void stop1() {
		setRunning(false);
		// previousTime=0;
		setFrameAtPause(0);
		setCurrentFrame(0);
	}

	public void pause() {
		setFrameAtPause(getCurrentFrame());
		setRunning(false);
	}

	public void resume1() {
		setCurrentFrame(getFrameAtPause());
		setRunning(true);

	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public int getFrameAtPause() {
		return frameAtPause;
	}

	public void setFrameAtPause(int frameAtPause) {
		this.frameAtPause = frameAtPause;
	}

	public ArrayList<BufferedImage> getFrames() {
		return frames;
	}

	public void setFrames(ArrayList<BufferedImage> frames) {
		this.frames = frames;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}
