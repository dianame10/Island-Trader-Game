package seng201_project;
import core.GameEnvironment;
import core.Island;
import core.Store;


/**
 * Defines a user interface (UI)for a GameEnvironment class
 */
public interface GameEnviromentUi {
	
    /**
     * A description of the name requirements for the player
     */
    String NAME_REQUIREMENTS = "Your name must be between 3 and 15 characters\n"
    		+ "and must not include numbers or special characters";
    
    /**
     * A description of the input requirements if the player want to buy or sell goods
     */
    String INPUT_REQUIREMENTS = "Please enter a valid number";
    
    /**
     * A regular expression for validating a name against the name requirements
     */
    String NAME_REGEX = "[a-zA-Z]{3,15}";
    
    /**
     * A regular expression for validating an input number against the input requirements if the player want to buy or sell goods
     */
    String INPUT_REGEX = "[0-9]{1,}";
    
    /**
     * Initializes the UI and sets up the given Game Environment 
     * It also asks the player to input his name
     * Once setup name is complete UI must call GameEnvironment#setName(String user name)
     * @param game The GameEnvironment that this UI interacts with
     */
    void setupName(GameEnvironment game);
    
    /**
     * Start the UI and manage their selected days and ship
     */
    void setupAdventure();
    
    /**
     * Start the UI and display store specification
     * @param store the store at the current location that the player want to visit
     */
    void enterStore(Store store);
    
    /**
     * See the chosen island specification
     * @param island The island which the player want to see
     */
    void seeIsland(Island island);
    
    /**
     * Start the UI which display the selected ship specification
     */
    void seeShip();
    
    /**
     * Start the UI which displays the specification about the Route
     */
    void seeRoute();
    
    /**
     * Display pop up message that the crews have been paid by the player
     */
    void payCrew();
    
    /**
     * Start the UI and display loading
     */
    void loading();
    
    /**
     * Start the UI and display information about the random event that player meet
     * @param event The random event that the player meet
     */
    void meetRandomEvent(String event);
    
    /**
     * Start the UI which shows the information about the goods that has been sold
     */
    void seeSoldItem();
    
    /**
     * Start the UI which displays the information that the game is over, the reason, the total profit, the traveling days, and the score
     * @param reason a String tells the reason why the game is ended
     */
    void gameOver(String reason);

	/**
	 * Start the UI and displays the options that player can choose
	 * The actions are:
	 * 1. Go to store at current location
	 * 2. See Ship Specification
	 * 3. Sail to another island
	 * 4. End the game
	 */
	void enterMain();
	
	/**
	 * Start the UI and make player plays rock paper scissor game
	 */
	void playRockPaperScissor();
	
	/**
	 * Start the UI and make player plays rolling a die game
	 */
	void playRollingADie();

	/**
	 * Displays a message to the UI
	 * @param string the message that shown to the player
	 */
	void tellPlayer(String string);

}
