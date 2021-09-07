package gui;
import javax.swing.JFrame;

/**
 * Screen Interface that need to be implemented by every screen
 *
 */
public interface Screen {
	/**
	 * Close the window for a particular screen
	 */
	void closeWindow();
	
	
	/**
	 * Gets the frame that will be used to pop the message in JOption Pane
	 * @return the JOption Pane in the current frame
	 */
	JFrame getFrame();

}
