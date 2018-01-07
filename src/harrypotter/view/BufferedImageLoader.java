package harrypotter.view;



import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;



public class BufferedImageLoader {

	public BufferedImageLoader() {
		
	}
	public BufferedImage loadImage(String path) throws IOException{
		URL url= this.getClass().getResource(path);
		BufferedImage img = ImageIO.read(url);
		return img;
	}
	

}
