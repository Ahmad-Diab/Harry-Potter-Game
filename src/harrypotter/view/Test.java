package harrypotter.view;

import harrypotter.model.world.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;



public class Test {

	public static void main(String[] args) {
		JFrame f= new JFrame();
		f.setSize(200, 200);
		f.setVisible(true);
		System.out.println(Direction.FORWARD+"  ");
		
		f.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_SPACE)
					System.exit(0);
			
			}
		});

	}

}
