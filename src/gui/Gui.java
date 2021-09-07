package gui;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.GameEnvironment;
import core.Island;
import core.Store;
import seng201_project.GameEnviromentUi;

/**
 * A graphical user interface for GameEnvironment class
 */
public class Gui implements GameEnviromentUi {
	/**
	 * The Game Environment that the Gui will interacts with
	 */
	public GameEnvironment game;
	
	/**
	 * The current screen that is visible by the user
	 */
	public Screen currentScreen;
	
	public Screen getCurrentScreen() {
		return this.currentScreen;
	}
	
	
	/**
	 *initialize the Game Environment
	 *make new setupName Screen and set it to be currentScreen that is visible to user
	 */
	@Override
	public void setupName(GameEnvironment incomingGame) {
		this.game = incomingGame;
		currentScreen = new SetupNameScreen(game);		
	}
	 
	/**
	 *close currentScreen that is visible to player and make new Setup Adventure Screen and set it to be currentScreen that is visible to player
	 */
	@Override
	public void setupAdventure() {
		currentScreen.closeWindow();
		currentScreen = new SetupAdventureScreen(game);
	}
	
	/**
	 *close currentScreen that is visible to player and make new Main Screen and set it to be currentScreen that is visible to player
	 */
	@Override
	public void enterMain() {
		currentScreen.closeWindow();
		currentScreen = new MainScreen(game);
	}
	
	/**
	 *close currentScreen that is visible to player and make new Store Screen and set it to be currentScreen that is visible to player
	 */
	@Override
	public void enterStore(Store store) {
		currentScreen.closeWindow();
		currentScreen = new StoreScreen(game);
	}
	
	/**
	 *close currentScreen that is visible to player and make new Island Screen and set it to be currentScreen that is visible to player
	 */
	@Override
	public void seeIsland(Island island) {
		currentScreen.closeWindow();
		currentScreen = new IslandScreen(game, island);
	}
	
	/**
	 *close currentScreen that is visible to player and make new Ship Screen and set it to be currentScreen that is visible to player
	 */
	@Override
	public void seeShip() {
		currentScreen.closeWindow();
		currentScreen = new ShipSpecScreen(game);
	}
	
	/**
	 *close currentScreen that is visible to player and make new Route Screen and set it to be currentScreen that is visible to player
	 */
	@Override
	public void seeRoute() {
		currentScreen.closeWindow();
		currentScreen = new RouteScreen(game);
	}
	
	/**
	 *Show up pop up message that tell player "You paid your crews salary! You now can set sail to your destination!" and launch loading screen
	 */
	@Override
	public void payCrew() {
		game.tellPlayer("You paid your crews sallary! You now can set sail to your destination!");
		game.loading();
	}
	
	/**
	 *close currentScreen that is visible to player and make new loading Screen and set it to be currentScreen that is visible to player
	 */
	@Override
	public void loading() {
		currentScreen.closeWindow();
		currentScreen = new LoadingScreen(game);
	}
	
	
	/**
	 *close currentScreen that is visible to player and make new Sold Item Screen and set it to be currentScreen that is visible to player
	 */
	@Override
	public void seeSoldItem() {
		currentScreen.closeWindow();
		currentScreen = new SoldItemsScreen(game);
	}
	

	/**
	 *close currentScreen that is visible to player and make new meet random Event Screen and set it to be currentScreen that is visible to player
	 *@param event The event that the player will meet
	 */
	@Override
	public void meetRandomEvent(String event) {
		currentScreen.closeWindow();
		currentScreen = new RandomEventScreen(game, event);
	}

	/**
	 *close currentScreen that is visible to player and make new Game Over Screen and set it to be currentScreen that is visible to player
	 *@param message The message that contains why the game ended
	 */
	@Override
	public void gameOver(String message) {
		currentScreen.closeWindow();
		currentScreen = new GameOverScreen(game, message);	
	}

	/**
	 *make new JOptionpane that will show pop up message that tell message to player
	 *@param message the message that will be shown to the player
	 */
	@Override
	public void tellPlayer(String message) {
		JFrame frame = currentScreen.getFrame();
		JOptionPane.showMessageDialog(frame, message);
	}
	
	/**
	 *close currentScreen that is visible to player and make new Rock paper Scissors Screen and set it to be currentScreen that is visible to player
	 */
	@Override
	public void playRockPaperScissor() {
		currentScreen.closeWindow();
		currentScreen = new RockPaperScissorsScreen(game);
	}

	/**
	 *close currentScreen that is visible to player and make new Rolling A Die Screen and set it to be currentScreen that is visible to player
	 */
	@Override
	public void playRollingADie() {
		currentScreen.closeWindow();
		currentScreen = new RollingDiceScreen(game);
	}

}
