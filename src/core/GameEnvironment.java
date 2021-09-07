package core;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import seng201_project.GameEnviromentUi;
import seng201_project.Main;


/**
 * Manage a selected ship that allowing the player to do trading though out 5 Islands to makes money as much as possible 
 *
 */
public class GameEnvironment {
	
	//The user interface to be used by this game environment
	private GameEnviromentUi ui;
	
	// The list of available ships 
	private ArrayList<Ship> availableShips;
	
	// The list of available Islands
	private ArrayList<Island> availableIslands;
	
	// The selected ship that been selected by the player
	private Ship selectedShip;
	
	// The name of the player
	private String userName;
	
	// total traveling days
	private int travellingDays;
	
	// total days chosen by the player
	private int selectedTravellingDays;
	
	// total remaining days
	private int remainingDays;
	
	// show the current money owned by the player
	private int money;
	
	// The current location of the player
	private Island currentLocation;
	
	// The current chosen Island that chosen by the trader to set as next destination
	private Island currentChosenIsland;
	
	// Random event that might occur
	private RandomEvent randomEvent;
	
	// Current route that will be use by the trader to go to the next destination
	private Route currentRoute; 
	
	// the amount of value that demanded by the pirates
	private int pirateDemand;
	
	
	
	/**
	 * Creates game environment that will contain the game
	 * @param ui The interface that this game should use
	 * @param ships The available ships of this game
	 * @param islands The available Islands of this game
	 * @param money The initial money of this game
	 */
	public GameEnvironment(GameEnviromentUi ui, ArrayList<Ship> ships, ArrayList<Island> islands, int money) {
		this.setUi(ui);
		this.setAvailableShips(ships);
		this.setAvailableIslands(islands);
		this.setMoney(money);
		int index = (int)Math.floor(Math.random()*(4-0+1)+0);
		this.currentLocation = this.availableIslands.get(index);
		this.randomEvent = new RandomEvent();
	}
	
	/**
	 * Starts this game. Must be called from the event dispatch thread if the user interface is a swing gui
	 * 
	 * This methods will calls GameEnvironmentUi #setupName(GameEnvironment) to initiate setup of the user interface
	 */
	public void startGame() {
		ui.setupName(this);
	}
	
	/**
	 * Function that use to set the name of the player and call the next function
	 * @param userName The user name of the player
	 */
	public void setName(String userName) {
		this.setUsername(userName);
		
		ui.setupAdventure();
	}
	
	/**
	 * Start the game by taking selected days and selected ship and call the next function
	 * @param days the selected days chosen by the player
	 * @param ship the selected ship chosen by the player
	 */
	public void startAdventure(int days, Ship ship) {
		this.setTravellingDays(0);
		this.setSelectedTravelingDays(days);
		this.setSelectedShip(ship);
		this.setRemainingDays(days);
		ui.enterMain();	
	}
	
	/**
	 * Function that use to enter the store and set the current location of the player and also set the current store.
	 */
	public void goToStore() {
		Island island = getCurrentLocation();
		Store store = island.getStore();
		ui.enterStore(store);
		
	}
	
	/**
	 * Function that call the function in the user interface to see the specification of the ship
	 */
	public void seeShipSpec() {
		ui.seeShip();
	}
	
	/**
	 * Function that use to see the Island that will be candidate chosen next destination and call the function in user interface
	 * @param island The chosen next island destination
	 */
	public void chooseIsland(Island island) {
		ui.seeIsland(island);	
	}
	
	/**
	 * Function to show the incident route with current location and chosen next destination
	 */
	public void showTheRoute() {
		ui.seeRoute();
	}	
	
	/**
	 * Take action to go to another Island which the player choose
	 * @param index The index of the list of available Islands
	 */
	public void chooseOtherIsland(int index) {
		if (this.getCurrentLocation() == this.getAvailableIslands().get(index)) {
			this.tellPlayer("Right now your ship docked on this island!");
		}
		else {
			Island island = this.getAvailableIslands().get(index);
			this.chooseIsland(island);
		}
	}
	
	/**
	 * Get the current used UI
	 * @return the current UI
	 */
	public GameEnviromentUi getUI() {
		return this.ui;
	}
	
	/**
	 * Set the UI
	 * @param ui the UI that want to be used
	 */
	public void setUi(GameEnviromentUi ui) {
		this.ui = ui;
	}
	
	/**
	 * Gets the list of available ships
	 * 
	 * @return the list of available ships
	 */
	public ArrayList<Ship> getAvailableShips() {
		return this.availableShips;
	}
	
	/**
	 * Set the available islands in the game
	 * @param availableIsland the islands that available in the game
	 */
	public void setAvailableIslands(ArrayList<Island> availableIsland) {
		this.availableIslands = availableIsland;
	}
	
	/**
	 * Gets the list of available Islands
	 * 
	 * @return the list of available Islands
	 */
	public ArrayList<Island> getAvailableIslands() {
		return availableIslands;
	}
	
	/**
	 * Set the Available ships that the player can choose from
	 * @param availableShips the list of ships that player can choose one
	 */
	public void setAvailableShips(ArrayList<Ship> availableShips) {
		this.availableShips = availableShips;
	}
	
	/**
	 * Gets the selected ship by the player
	 * 
	 * @return the selected ship
	 */
	public Ship getSelectedShip() {
		return selectedShip;
	}
	
	/**
	 * TSet the selected ship by the player
	 * @param chosenShip
	 */
	public void setSelectedShip(Ship chosenShip) {
		this.selectedShip = chosenShip;
	}
	
	/**
	 * Gets the user name 
	 * 
	 * @return the name of the player
	 */
	public String getName() {
		return userName;
	}
	
	
	/**
	 * Set the user name to the name of the player
	 * @param playerName the name that the player input
	 */
	public void setUsername(String playerName) {
		this.userName = playerName;
	}
	
	/**
	 * Gets the current traveling days
	 * 
	 * @return the traveling days
	 */
	public int getTravellingDays() {
		return this.travellingDays;
	}
	
	/**
	 * Set the current traveling days
	 * 
	 * @param days an updated current traveling days
	 */
	public void setTravellingDays(int days) {
		this.travellingDays = days;
	}
	

	
	/**
	 * Get the selected maximum traveling days that chosen by the player in the beginning of the game
	 * @return the selected the maximum traveling days
	 */
	public int getSelectedTravelingDays() {
		return this.selectedTravellingDays;
	}
	
	
	/**
	 * Set the maximum traveling days that the player has chosen
	 * @param numberDays the selected number of days
	 */
	public void setSelectedTravelingDays(int numberDays) {
		this.selectedTravellingDays = numberDays;
	}
	
	
	/**
	 * Gets the remaining days left of the game
	 * 
	 * @return the remaining days
	 */
	public int getRemainingDays() {
		return this.remainingDays;
	}
	
	
	/**
	 * Set the remaining days 
	 * 
	 * @param days an updated current remaining days
	 */
	public void setRemainingDays(int days) {
		this.remainingDays = days;
	}
	
	/**
	 * Gets the current money
	 * 
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}
	
	
	/**
	 * Set the money to the new amount current of money
	 * @param money the new amount of money
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	
	/**
	 * Gets the current location of the player
	 * 
	 * @return the current location
	 */
	public Island getCurrentLocation() {
		return currentLocation;
	}
	
	
	/**
	 * set the current location to the new current location
	 * @param island the new location
	 */
	public void setCurrentLocation(Island island) {
		this.currentLocation = island;
	}
	
	/**
	 * Gets the current next chosen Island
	 * 
	 * @return the current next chosen Island
	 */
	public Island getCurrentChosenIsland() {
		return this.currentChosenIsland;
	}
	
	
	/**
	 * Set the candidate of next destination chosen by the player
	 * 
	 * @param island the next place that the player choose
	 */
	public void setCurrentChosenIsland(Island island) {
		this.currentChosenIsland = island;
	}
	
	/**
	 * Get the random Event t
	 * @return the random event
	 */
	public RandomEvent getRandomEvent() {
		return this.randomEvent;
	}
	
	/**
	 * Gets the current route where the player is
	 * 
	 * @return the current route
	 */
	public Route getCurrentRoute() {
		return this.currentRoute;
	}
	
	/**
	 * Set the current route that the player use to go to another island
	 * 
	 * @param currentRoute  the route that the player use
	 */
	public void setCurrentRoute(Route currentRoute) {
		this.currentRoute = currentRoute;
	}
	
	/**
	 * Gets the amount of value that the pirates want to decide whether the player can continue the game or not
	 * 
	 * @return the amount of value being demand by the pirates
	 */
	public int getPiratesDemand() {
		pirateDemand = (int)Math.floor(Math.random()*(150-30+1)+30);
		return this.pirateDemand;
	}
	
	/**
	 * Set the pirate demand if the player lose play number game with pirate
	 * @param theDemand the demand of the pirate
	 */
	public void setPirateDemand(int theDemand) {
		this.pirateDemand = theDemand;
	}
	
	
	
	/**
	 * Function to calculate total sailing days through this route
	 * @param route The route that will be use by the player
	 * @return return the total days required
	 */
	public int calculateSailingDays(Route route) {
		int speed = this.getSelectedShip().getShipsSpeed();
		int distance = route.getDistance();
		return distance / speed;
	}
	
	/**
	 * Function to pay the crew members before go sailing and check the money of the player
	 * @param route The route that will be use by the player to go to next destination
	 */
	public void payCrew(Route route) {
		Ship ship = this.getSelectedShip();
		int numberCrew = ship.getCrew();
		int sailingDays = this.calculateSailingDays(route);
		int totalCost = numberCrew * sailingDays * 1;//1 is salary per day for crew
		if(this.checkEnoughMoney(totalCost)) {
			this.updateMoney(totalCost * -1);
			this.currentLocation = this.currentChosenIsland;
			this.setCurrentRoute(route);
			this.travellingDays += sailingDays;
			this.remainingDays = this.getSelectedTravellingDays() - this.getTravellingDays();
			ui.payCrew();	
		}
		else {
			if(this.checkItemAvailability()) {
				this.tellPlayer("You don't have enough money to pay your crews! you can sell your items!");
				this.goToStore();
			}
			else {
				ui.gameOver("You can't pay your crew because you don't have enough money \nyou also don't have any items in your ship that you can sell!");
			}
		}
		
	}
	
	/**
	 * Calling the loading function in the user interface class
	 */
	public void loading() {
		ui.loading();
	}
	
	/**
	 *  Gets the random event that might occur and call the meet random event function in the user interface
	 */
	public void meetRandomEvent() {
		String event = this.randomEvent.getRandomEvent();
		ui.meetRandomEvent(event);
	}
	
	/**
	 * Function that will be execute when meet the pirates in the route
	 */
	public void meetPirate() {
		int max = 1;
		int min = 0;
		int chooseGames = (int)Math.floor(Math.random()*(max-min+1)+min);
		if (chooseGames == 0) {
			ui.playRockPaperScissor();
		}
		else {
			ui.playRollingADie();
		}
	}
	
	/**
	 * Call the function in user interface to see the Items that has been sold
	 */
	public void seeSoldItems() {
		ui.seeSoldItem();
	}
	
	/**
	 * Function to going back to main menu by calling the function in the user interface class
	 */
	public void backToMain() {
		ui.enterMain();
	}
	
	/**
	 * Calling the function in user interface class and pass the message which show the reason why the game is over
	 * @param message The reason why the game is over and passed as a string message
	 */
	public void gameOver(String message) {
		ui.gameOver(message);
		
	}

	

	
	/**
	 * Gets the selected traveling days that the player choose in the beginning of the game
	 * 
	 * @return the selected traveling days
	 */
	public int getSelectedTravellingDays() {
		return this.selectedTravellingDays;
	}
	
	/**
	 * Function to updating the current money of the player
	 * 
	 * @param money The money added into current money
	 */
	public void updateMoney(int money) {
		this.money = this.getMoney() + money;
	}
	
	

	
	/**
	 * Gets the list of the routes from the current position of the player
	 * 
	 * @return the list of routes
	 */
	public ArrayList<Route> getListOfRoutes() {
		Island island = this.getCurrentLocation();
		ArrayList<Route> listOfRoutes = island.getRouteCollections();
		return listOfRoutes;
	}


	/**
	 * Function to check if the player able to but more goods from the given quantity and good
	 * 
	 * @param quantity the amount of quantity that the player want to buy
	 * @param good the good that player want to buy
	 * @param goodsPrice the price of the good chosen
	 * 
	 * @return true if the player able to buy, false otherwise
	 */
	public boolean checkConditionsForBuying(int quantity, Goods good, int goodsPrice) {
		int totalPrice = quantity * goodsPrice;
		double totalSize = quantity * good.getSize();
		if (checkEnoughMoney(totalPrice) && checkEnoughCapacity(totalSize)) 
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Function to check if the player have enough money to take another action such as buy Items and sail to other Island
	 * 
	 * @param total The total money needed
	 * 
	 * @return true if the player have enough money, false otherwise
	 */
	public boolean checkEnoughMoney(int total) {
		int money = getMoney();
		if (total <= money) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * Function to check of the capacity of the ship still enough to take more goods
	 * 
	 * @param totalSize the total size that will be added to the ship capacity
	 * 
	 * @return true if it is enough, false otherwise
	 */
	public boolean checkEnoughCapacity(double totalSize) {
		Ship ship = getSelectedShip();
		double capacity = ship.getCapacity();
		if (totalSize <= capacity) {
			return true;
		}
		else {
			return false;
		}
	
	}
	
	/**
	 * Function to buy goods with the given quantity and price
	 * 
	 * @param quantity the quantity that the player want to buy
	 * @param goods The good that the player want to buy
	 * @param goodsPrice the price of the good
	 */
	public void buyGoods(int quantity, Goods goods, int goodsPrice) {
		Ship selectedShip = this.getSelectedShip();
		double totalSize = quantity * goods.getSize();
		int totalPrice = quantity * goodsPrice;
		selectedShip.addGoods(goods, goodsPrice, totalSize, quantity);
		this.updateMoney(totalPrice * -1);
	}
	
	/**
	 * Function to check if the player have the given goods in the ship to be sell to the store
	 * 
	 * @param goods The goods that will be check
	 * @return true if the player have the goods, false otherwise
	 */
	public boolean checkAvailability(Goods goods) {
		Ship selectedShip = this.getSelectedShip();
		HashMap<Goods, Integer> myGoods = selectedShip.getMyGoods();
		if (myGoods.containsKey(goods)) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Function to check of the player have the amount of quantity of the goods that being demand by the store
	 * 
	 * @param quantity The amount of goods
	 * @param goods the goods that will be check
	 * @return true if the player have that amount of quantity, false otherwise
	 */
	public boolean checkQuantity(int quantity, Goods goods) {
		if (quantity <= goods.getQuantityOwned()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Function to sell goods from the player to the store
	 * @param goods The goods that the store want
	 * @param quantity The amount of quantity that will be sell
	 * @param goodPrice The price of the goods
	 */
	public void sellGoods(Goods goods, int quantity, int goodPrice) {
		int totalPrice = quantity * goodPrice;
		double totalSize = quantity * goods.getSize();
		this.updateMoney(totalPrice);
		Ship selectedShip = this.getSelectedShip();
		selectedShip.removeGoods(goods, quantity, totalSize);
		selectedShip.updateSoldItems(goods, currentLocation, quantity);
		
	}
	
	/**
	 * Function to check if the player already have all the equipments in the ship
	 * 
	 * @return true if the equipments full, false otherwise
	 */
	public boolean checkEquipmentFull() {
		Ship ship = this.getSelectedShip();
		return (ship.getNumberEquipment() == 5);
	}
	
	/**
	 * Function to buy the equipment from the store
	 * 
	 * @param equipment The equipment that the player want to buy
	 */
	public void buyEquipment(Equipment equipment) {
		Ship ship = this.getSelectedShip();
		this.updateMoney(-1 * equipment.getSellingPrice());
		ship.addEquipment(equipment);
	}
	
	/**
	 * Function to check if the ship's health is full
	 * 
	 * @return true if the ship's health in 100% condition, false otherwise
	 */
	public boolean checkMyShipHealth() {
		Ship ship = this.getSelectedShip();
		return (ship.getShipsStatus() == 100);	
	}
	
	/**
	 * Function to repair the ship when the ship's health is not in 100% condition after facing unfortunate weather
	 * 
	 */
	public void repairMyShip() {
		Ship ship = this.getSelectedShip();
		int totalRepairmentCost = this.calculateRepairmentCost();
		if (this.checkEnoughMoney(totalRepairmentCost)) {
			this.updateMoney(totalRepairmentCost * -1);
			ship.setShipStatus(100);
			this.tellPlayer("Your ship's health is now 100%");
			this.goToStore();
		}
		else {
			if (checkItemAvailability()) {
				this.tellPlayer("You don't have enough money to repair your ship, you should sell your goods first!");
				this.goToStore();
			}
			else {
				this.gameOver("You can't repair your ship because you don't have enough money \n you also don't have any items in your ship that you can sell!");
			}
		}
	}
	
	/**
	 * Function to calculate the repairment cost of the ship, cost to repair every damage is 1 coin
	 * 
	 * @return the total that should be paid by the player
	 */
	public int calculateRepairmentCost() {
		return (100 - this.getSelectedShip().getShipsStatus()) * 1; //cost to repair every damage is 1 coin
	}
	
	/**
	 * Function to tell the player a message by calling the function in user interface class
	 * 
	 * @param message that will be send to the player
	 */
	public void tellPlayer(String message) {
		ui.tellPlayer(message);
	}
			
	/**
	 * Function to check if the player still able to sell goods for the store in the current location
	 * 
	 * @param ship The selected ship
	 * @return true if the player still have the goods, false otherwise
	 */
	public boolean checkItemAvailability() {
		HashMap<Goods, Integer> myGoods = this.getSelectedShip().getMyGoods();
		Island island = this.getCurrentLocation();
		Store store = island.getStore();
		HashMap<Goods, Integer> interestedGoods = store.getInteresedGoods();
		for (Entry<Goods, Integer> set: interestedGoods.entrySet()) {
			Goods good = set.getKey();
			if (myGoods.containsKey(good)) {
				return true;
			}	
	}
		return false;
	}
	
	
	/**
	 * Gets the incident route between current Island and chosen next destination
	 * 
	 * @return the route between those to Islands
	 */
	public Route getIncidentRoutes() {
		ArrayList<Route> myRoutes = this.getListOfRoutes();
		for (Route route: myRoutes) {
			if (route.getOneEnd() == this.getCurrentLocation().getIslandName()) {
				if (route.getOtherEnd() == this.getCurrentChosenIsland().getIslandName()) {
					return route;
				}
			}
			else if (route.getOneEnd() == this.getCurrentChosenIsland().getIslandName()){
				if (route.getOtherEnd() == this.getCurrentLocation().getIslandName()) {
					return route;
				}
			}
			
		}
		return null;
	}
	
	
	/**
	 * Function to check if there is a random event that will be occur in the route chosen by the player
	 * 
	 * @return true if there is a random event, false otherwise
	 */
	public boolean checkInRouteEvent() {
		int probability = this.getCurrentRoute().getProbability() / 10;
		ArrayList<Integer> probabilityList = new ArrayList<Integer>(10);
		for(int i = 0; i < 10; i ++) {
			if (probability > 0) {
				probabilityList.add(1);
			}
			else {
				probabilityList.add(0);
			}
			probability -= 1;
		}
		
		int index = (int)Math.floor(Math.random()*(9-0+1)+0);
		
		if (probabilityList.get(index) == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	
	/**
	 * Function to take the next action when meet specific random event
	 * 
	 * @param event the random event that faced by the player
	 */
	public void actionRandomEvent(String event) {
		if(event == "PIRATE") 
		{
			this.meetPirate();
		}
		else if (event == "UNFORTUNATE WEATHER"){
			this.backToMain();
		}
		else {
			this.backToMain();
		}
	}
	
	/**
	 * Function to get the random damage that caused by unfortunate weather
	 * 
	 * @return the random damage that damage the ship
	 */
	public int meetUnfortunateWeather() {
		int max = 99;
		int min = 0;
		int randomDamage = (int)Math.floor(Math.random()*(max-min+1)+min);
		this.getSelectedShip().setShipStatus(100 - randomDamage);
		return randomDamage;
	}
	
	/**
	 * Function to get the random reward that rewarded by the sailor being rescued by the player
	 * 
	 * @return the amount of money rewarded by the sailor
	 */
	public int meetSailorToRescue() {
		int max = 99;
		int min = 0;
		int reward = (int)Math.floor(Math.random()*(max-min+1)+min);
		this.updateMoney(reward);
		return reward;
		
	}
	
	/**
	 * Function to calculate the winning probability when play rock paper scissors against pirated based on number of the equipment in ship and decide if the player can win or not
	 * 
	 * @return the decision if the player will win or not when play against pirates
	 */
	public int determineWinnningRPSGame() {
		double winningProbability = this.getSelectedShip().getProbability() * 10;
		double loseProbability = Math.floor(Math.random()*((10-winningProbability)-1+1)+1);
		ArrayList<Integer> winningProbabilityList = new ArrayList<Integer>();
		for(int i = 0; i < 10; i ++) {
			if (winningProbability > 0) {
				winningProbabilityList.add(2);
				winningProbability -= 1;
			}
			else {
				if(loseProbability > 0) {
					winningProbabilityList.add(0);
					loseProbability -= 1;
				}
				else {
					winningProbabilityList.add(1);
				}
				
			}
			
		}
		
		int randomIndex = (int)Math.floor(Math.random()*(9-0+1)+0);
		int decision = winningProbabilityList.get(randomIndex);
		return decision;
		
	}
	
	/**
	 * Function that will return what pirates throw when play rock paper scissors based on the decision
	 * 
	 * @param decision The decision whether the player win or lose
	 * @param choices The choices of the player to determine what the pirates should throw
	 * @return what the pirates will throw
	 */
	public String getWhatPiratesThrowRPS(int decision, String choices) {
	if (decision == 0) {
		if (choices == "Rock") {
			return "Paper";
		}
		else if (choices == "Paper") {
			return "Scissors";
		}
		else {
			return "Rock";
		}
	}
	else if (decision == 1) {
		return choices;
	}
	else {
		if (choices == "Rock") {
			return "Scissors";
		}
		else if (choices == "Paper") {
			return "Rock";
		}
		else {
			return "Paper";
		}
	}
		
	}
	
	/**
	 * Function to take the next action when the player play against pirates 
	 * 
	 * @param state the condition whether the player win, lose, or able to continue the game
	 * @param gameName the name of the number game played against pirates
	 */
	public void executeOkButton(String state, String gameName) {
		if (state == "Game over") {
			this.gameOver("You lose play "+ gameName + " game! the pirate took all of your money and your items in your ship!");
		}
		else if (state == "Win") {
			this.backToMain();
		}
		else {
			this.tellPlayer("You able to fulfill pirate's demand, but they take all of your goods in your ship!");
			this.backToMain();
		}
	}
	

	/**
	 * Function that will return what pirates throw when play rolling a die based on the decision
	 * 
	 * @param decision The decision whether the player win or lose
	 * @param choices The choices of the player to determine what the pirates should throw
	 * @return what the pirates will throw
	 */
	public int getWhatPiratesThrowRD(int decision, String choices) {
		java.util.List<Integer> evenNumber = Arrays.asList(2, 4, 6);
		java.util.List<Integer> oddNumber = Arrays.asList(1, 3, 5);
		int randomIndex = (int)Math.floor(Math.random()*(2-0+1)+0);
		if (decision == 0) {
		if (choices == "Even") {
			int result = oddNumber.get(randomIndex);
			return result;
		}
		else {
			int result = evenNumber.get(randomIndex);
			return result;
		}
	}
	else {
		if (choices == "Even") {
			int result = evenNumber.get(randomIndex);
			return result;
		}
		else {
			int result = oddNumber.get(randomIndex);
			return result;
		}
	}
		
	}
	

	
	/**
	 * Function to remove all the goods from the ship when the player lose against the pirates in p[laying a number game
	 */
	public void removeAllGoods() {
		Ship ship = this.getSelectedShip();
		ship.gotPirated();
	}
	
	/**
	 * Function to check if the pirates satisfy the goods that the player have in the ship
	 * 
	 * @return true if the player have the total value of goods in the ship
	 */
	public boolean checkPirateSatisfaction() {
		Ship ship = this.getSelectedShip();
		int value = ship.getTotalValueOwned();
		return (value >= this.getPiratesDemand());	
	}
	
	/**
	 * Function to calculate the winning probability when play rolling a die against pirated based on number of the equipment in ship and decide if the player can win or not
	 * 
	 * @return the decision if the player will win or not when play against pirates
	 */
	public int determineWinnningRollingADie() {
		double winningProbability = this.selectedShip.getProbability() * 10;
		ArrayList<Integer> winningProbabilityList = new ArrayList<Integer>();
		for(int i = 0; i < 10; i ++) {
			if (winningProbability > 0) {
				winningProbabilityList.add(1);
				winningProbability -= 1;
			}
			else {
				winningProbabilityList.add(0);
			}
		}
		
		int randomIndex = (int)Math.floor(Math.random()*(9-0+1)+0);
		int decision = winningProbabilityList.get(randomIndex);
		return decision;
		
	}
	
	/**
	 * Function to check if the island is accessible or not form the current location by checking the remaining days and money of the player
	 * 
	 * @param island The place where the player is 
	 * @return true if the Island is accessible, false otherwise
	 */
	public boolean checkAccessibleIsland(Island island) {
		ArrayList<Route> routes = island.getRouteCollections();
		ArrayList<Route> reachableIsland = new ArrayList<Route>();
		for (Route route: routes) {
			int distance = route.getDistance();
			Ship ship = this.getSelectedShip();
			int shipSpeed = ship.getShipsSpeed();
			int sailingDays = distance / shipSpeed;
			if (this.getRemainingDays() > sailingDays) {
				reachableIsland.add(route);
			}
		}
		if (reachableIsland.size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Function that used to check if the player able to sail to current chosen next destination
	 * 
	 * @param route The route that will be use to sailing
	 * 
	 * @return true if the Island is reachable, false otherwise
	 */
	public boolean isIslandReachable(Route route) {
		int distance = route.getDistance();
		Ship ship = this.getSelectedShip();
		int shipSpeed = ship.getShipsSpeed();
		int sailingDays = distance / shipSpeed;
		if (this.getRemainingDays() > sailingDays) {
			return true;
		}
		return false;
	}
	
	/**
	 * Function that used to check if the player able to sail to current chosen next destination and will take the action id true
	 * 
	 * @param theRoute The route that will be use to sailing
	 */
	public void checkAbleToSail(Route theRoute) {
		if (this.isIslandReachable(theRoute)) {
			if (this.checkMyShipHealth()) {
				this.payCrew(theRoute);
			}
			else {
				this.tellPlayer("You should repair your ship first!" + "\n" + "you will be redirected to store at your current Island" + "\n");
				this.goToStore();
			}
		}
		else {
			this.tellPlayer("This Island is not accessible because you don't have enough days left, you can choose other Island");
			this.backToMain();
		}
	}
	
	/**
	 * Count the total profit that made by the player
	 * 
	 * @return the total profit
	 */
	public int countProfit() {
		return this.getMoney() - 250;
	}
	
	/**
	 * Function that used to count the final score of the game 
	 * 
	 * @return the total final score
	 */
	public int countFinalScore() {
		int profitMade = this.countProfit();
		int actualDurationGame = this.getTravellingDays();
		int finalScore = profitMade + actualDurationGame;
		return finalScore;
	}
	
	
	/**
	 *  Function that used to decide which random event that will be occur 
	 */
	public void decideMeetRandomEvent() {
		if(this.checkInRouteEvent()) {
			this.tellPlayer("Oh no.., something is in your way!");
			this.meetRandomEvent();
		}
		else{
			this.tellPlayer("You have arrived at your destination!");
			this.backToMain();
		}
	}
	
	
	/**
	 * Construct a new game
	 */
	public void newGame() {
		Main.run(ui);
	}
	
}
