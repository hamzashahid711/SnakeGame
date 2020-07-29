package package1;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class frame extends JFrame {
	
	
	
	public frame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when x'd out the program will close
		
		setTitle("Snake");// setting the title 
		setResizable(false); 
		
		
		pan();
	}
	public void pan() {
		Screen s = new Screen();
		add(s); //adds the screen object to the window 
		pack(); // whatever the screen size is it will fit in the window 
		
		
		
		setLocationRelativeTo(null); //center the frame
		setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		new frame();
	}

}
