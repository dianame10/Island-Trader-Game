package seng201_project;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import core.Equipment;
import core.GameEnvironment;
import core.Goods;
import core.Island;
import core.Route;
import core.Ship;
import core.Store;

import java.util.Scanner;

/**
 * A command line user interface for a GameEnvironment
 *
 */
public class CommandLine implements GameEnviromentUi{
	// Scanner for input
	private final Scanner scanner;
	
	//The Game Environment this screen interacts with
	private GameEnvironment game;
	
	
	/**
	 * Creating the scanner
	 */
	public CommandLine() {
		this.scanner = new Scanner(System.in);
	}
	
	
	/**
	 *Ask the user to input his name and check if it meets the requirements
	 */
	@Override
	public void setupName(GameEnvironment incomingGame) {
		this.game = incomingGame;
		boolean state = true;
		while(state == true) {
			System.out.println("Hey mate! What is your name?");
			try {
				String name = scanner.nextLine();
				if (name.matches(NAME_REGEX)) {
					state = false;
					game.setName(name);	
				}
				else {
					System.out.println(NAME_REQUIREMENTS);
				}
			} catch(Exception e){
				scanner.nextLine();
			}
		}
	}
		

	/**
	 *Ask the player to choose maximum traveling days and the ship he want to use
	 */
	@Override
	public void setupAdventure() {
		ArrayList<Ship> availableShips = new ArrayList<Ship>();
		availableShips = game.getAvailableShips();
		int selectedDays = 0;
		boolean stateAskPlayingDays = false;
		while (stateAskPlayingDays == false) {
			System.out.println("How many days you want your adventure last (Choose between 20 and 50 days)? ");
			try {
				int number = scanner.nextInt();
				if (number >= 20 && number <= 50) {
					stateAskPlayingDays = true;
					selectedDays = number;
				}
				else {
					System.out.println("You should choose between 20 and 50 days");
				}
	
			} catch (Exception e) {
				System.out.println(INPUT_REQUIREMENTS);
				scanner.nextLine();
			}
		}
		
		boolean stateChoosenShip = false;
		while (stateChoosenShip == false) 
		{
			System.out.println("Choose your ship!");
			printAvailableShips(availableShips);
			try {
				int choosenShip = scanner.nextInt();
				if (choosenShip <= availableShips.size() && choosenShip > 0)  {
					stateChoosenShip = true;
					Ship selectedShip = availableShips.get(choosenShip - 1);
					game.startAdventure(selectedDays, selectedShip);
				}
				else {
					System.out.println("You should choose ship from available ships!");
				}
			} catch (Exception e) {
				System.out.println(INPUT_REQUIREMENTS);
				scanner.nextLine();
			}
		}
		
	}
	
	/**
	 *Displays the the options that the player can do
	 *The options are:
	 *1. Go to other island
	 *2. See ship specification
	 *3. Go to store at current location
	 *4. end game
	 */
	@Override
	public void enterMain() {
		Island island = game.getCurrentLocation();
		if (game.checkAccessibleIsland(island)) 
		{
			boolean state = false;
			while (state == false) {
				ArrayList<Island> islands = game.getAvailableIslands();
				System.out.println("Your current money: " + game.getMoney()+ " coins.");
				System.out.println("You have : " + game.getRemainingDays() + " days left.");
				Island currentLoc = game.getCurrentLocation();
				String islandName = currentLoc.getIslandName();
				System.out.println("Your current location is: " + islandName + " Island." + "\n");
				System.out.println("What do you want to do? ");
				System.out.println("(1) See ship's specification");
				System.out.println("(2) Go to other Island");
				System.out.println("(3) Go to store in your current location");
				System.out.println("(4) End game");
				try {
					int selectedAction = scanner.nextInt();
					if (selectedAction <= 4 && selectedAction > 0) {
						state = true;
						if (selectedAction == 1) {
							game.seeShipSpec();
						}
						else if (selectedAction == 2) {
							printAvailableIslands(islands);
						}
						else if (selectedAction == 3) {
							game.goToStore();
						}
						else {
							game.gameOver("You ended the game");
						}
					}
					else {
						System.out.println("Please choose from actions above");
					}
				} catch (Exception e) {
					System.out.println(INPUT_REQUIREMENTS);
					scanner.nextLine();
				}
			}
		}
		else {
			boolean state = false;
			while (state == false)
			{
				System.out.println("Your current money: " + game.getMoney()+ " coins.");
				System.out.println("You have : " + game.getRemainingDays() + " days left.");
				Island currentLoc = game.getCurrentLocation();
				String islandName = currentLoc.getIslandName();
				System.out.println("Your current location is: " + islandName + " Island." + "\n");
				System.out.println("What do you want to do? ");
				System.out.println("(1) See ship's specification");
				System.out.println("(2) Go to store in your current location");
				System.out.println("(3) End Game");
				try {
					int selectedAction = scanner.nextInt();
					if (selectedAction <= 3 && selectedAction > 0) {
						state = true;
						if (selectedAction == 1) {
							game.seeShipSpec();
						}
						else if (selectedAction == 2) {
							game.goToStore();
						}
						else {
							game.gameOver("You ended the game");
						}
					}
					else {
						System.out.println("Please choose from actions above");
					}
					
				} catch (Exception e) {
					System.out.println(INPUT_REQUIREMENTS);
					scanner.nextLine();
				}
			}
		}
	}
	
	/**
	 *Display the information about the ship the player select and display the goods owned by the player
	 */
	@Override
	public void seeShip() {
		Ship selectedShip = game.getSelectedShip();
		String name = selectedShip.getShipName();
		int crew = selectedShip.getCrew();
		double cargo = selectedShip.getCapacity();
		double shipsHealth = selectedShip.getShipsStatus();
		int speed = selectedShip.getShipsSpeed();
		ArrayList<Equipment> equipments = selectedShip.getEquipment();
		HashMap<Goods, Integer> myGoods = selectedShip.getMyGoods();
		System.out.println("Ship's name: " + name);
		System.out.println("Number of crews: " + crew);
		System.out.println("Ship's health: " + shipsHealth);
		System.out.println("Speed: " + speed);
		System.out.println("Maximum cargo capacity: " + String.format("%.2f",cargo) + "\n");
		printEquipments(equipments);
		printMyGoods(myGoods);
		boolean state = false;
		while (state == false) {
			System.out.println("Choose your actions: ");
			System.out.println("(1) Go back!");
			System.out.println("(2) See your sold items.");
			try {
				int selectedAction = scanner.nextInt();
				if (selectedAction <= 2 && selectedAction > 0) {
					state = true;
					if (selectedAction == 1) {
						game.backToMain();;
					}
					else {
						game.seeSoldItems();
					}
				}
				else {
					System.out.println("Please choose from actions above");
				}
				
			} catch (Exception e) {
				System.out.println(INPUT_REQUIREMENTS);
				scanner.nextLine();
			}
		}
	}
	
	
	/**
	 *Display the list of goods that has been sold
	 */
	@Override
	public void seeSoldItem() {
		Ship selectedShip = game.getSelectedShip();
		HashMap<Goods, ArrayList<String>> soldItems = selectedShip.getSoldItems();
		int index = 1;
		System.out.println("Items that has been sold: ");
		for (HashMap.Entry<Goods, ArrayList<String>> set: soldItems.entrySet()) {
			String name = set.getKey().getName();
			int quantity = set.getKey().getQuantitySold();
			System.out.println("(" + index + ") Name: " +  name + " Quantity: " + quantity + " Loaction it was sold: " + set.getValue());
			index++;
		
		}
		System.out.println("\n");
		boolean state = false;
		while (state == false) {
			System.out.println("Choose your actions: ");
			System.out.println("(1) Go back!");
			try {
				int selectedAction = scanner.nextInt();
				if (selectedAction == 1) {
					
					state = true;
					game.seeShipSpec();
				}
				else {
					System.out.println("Please choose from actions above");
				}
			} catch (Exception e) {
				System.out.println(INPUT_REQUIREMENTS);
				scanner.nextLine();
			}
		}
	}
	

	/**
	 *Display about information about the island that the player want to see and display an option to set sail to the island
	 */
	@Override
	public void seeIsland(Island island) {
		String name = island.getIslandName();
		Store store = island.getStore();
		HashMap<Goods, Integer> sellGoods = store.getSellGoods();
		HashMap<Goods, Integer> interestedGoods = store.getInteresedGoods();
		Equipment sellEquipment = store.getEquipment();
		game.setCurrentChosenIsland(island);
		System.out.println("Island's name: " + name);
		printGoods(sellGoods, "The goods that the store in this Island sell: ");
		printGoods(interestedGoods, "The Goods that the store in this island want to buy:");
		System.out.println("\n" + "Equipment: " + sellEquipment.getName() + " Price : " + sellEquipment.getSellingPrice() + "\n");	
		boolean state = false;
		while (state == false) {
			System.out.println("Choose your actions: ");
			System.out.println("(1) Go back!");
			System.out.println("(2) See the routes to this Island");
			try 
			{
				int selectedAction = scanner.nextInt();
				if (selectedAction <= 2 && selectedAction > 0) {
				state = true;
				if (selectedAction == 1) {
					game.backToMain(); 
				}
				else {
					game.showTheRoute();
				}
			}
				else {
					System.out.println("Please choose from actions above");
				}
				
			} catch (Exception e) {
				System.out.println(INPUT_REQUIREMENTS);
				scanner.nextLine();
			}
		}	
	}
	
	/**
	 *Display the items that store sells and want to buy
	 *The player also able to do transaction here
	 */
	@Override
	public void enterStore(Store store) {
		HashMap<Goods, Integer> sellGoods = store.getSellGoods();
		HashMap<Goods, Integer> interestedGoods = store.getInteresedGoods();
		Equipment equipment = store.getEquipment();
		int currentMoney = game.getMoney();
		boolean state = false;
		while (state == false) {
			System.out.println("Your current money is: " + currentMoney);
			if (!game.checkMyShipHealth()) {
				int cost = 100 - (game.getSelectedShip().getShipsStatus());
				System.out.println("Your ship repairment cost is: " + cost);
			}
			System.out.println("Choose your actions: ");
			System.out.println("(1) Go back!");
			System.out.println("(2) See goods that for sale");
			System.out.println("(3) See goods that interested by the store");
			System.out.println("(4) See equipment for your ship");
			System.out.println("(5) Repair my ship");
			try { 
				int selectedAction = scanner.nextInt();
				if (selectedAction <= 5 && selectedAction > 0) {
				state = true;
				if (selectedAction == 1) {
					game.backToMain();
				}
				else if (selectedAction == 2) 
				{
					buyGoodsFromStore(sellGoods);
	
				}
				else if (selectedAction == 3) {
					sellGoodsForStore(interestedGoods);
				}
				else if (selectedAction == 4) {
					this.buyEquipment(equipment, store);
				}
				else {
					game.repairMyShip();
				}
			}

				else {
					System.out.println("Please choose from actions above");
				}
				
			} catch (Exception e) {
				System.out.println(INPUT_REQUIREMENTS);
				scanner.nextLine();
			}
		}
	}
	
	/**
	 *Display information to see the specification of the route between two islands
	 */
	@Override
	public void seeRoute() {
		Route route = game.getIncidentRoutes();
		System.out.println(route);
		System.out.println("Total sailing days required is "+ game.calculateSailingDays(route)+" days");
		boolean state = false;
		while (state == false) {
			System.out.println("Choose your actions: ");
			System.out.println("(1) Go back!");
			System.out.println("(2) Set Sail");
			try { 
				int selectedAction = scanner.nextInt();
				if (selectedAction <= 2 && selectedAction > 0) {
				state = true;
				if (selectedAction == 1) {
					game.backToMain();
				}
				else {
					if (game.isIslandReachable(route)) {
						if (game.checkMyShipHealth()) {
							game.payCrew(route);
						}
						else {
							System.out.println("You should repair your ship first!" + "\n" + "you will be redirected to store at your current Island" + "\n");
							game.repairMyShip();
						}
					}
					else {
						System.out.println("This Island is not accessible because you don't have enough days left, you can choose other Island");
						game.backToMain();
					}

				}
			}

				else {
					System.out.println("Please choose from actions above");
				}
				
			} catch (Exception e) {
				System.out.println(INPUT_REQUIREMENTS);
				scanner.nextLine();
			}
		}
		
	}
	
	/**
	 *Display the message when the crew is paid and player ready to sail
	 */
	@Override
	public void payCrew() {
		System.out.println("Crew paid!, You can start sailing now!");
		game.loading();
	}

	/**
	 *Display loading
	 */
	@Override
	public void loading() {
		System.out.println("Loading....");
		if(game.checkInRouteEvent()) {
			System.out.println("Oh no.., something is in your way!");
			game.meetRandomEvent();
		}
		else{
			game.backToMain();
		}
	}

	/**
	 *Display the information of what random event the player meet
	 */
	@Override
	public void meetRandomEvent(String event) {
		System.out.println("You meet " + event + "!");
		if(event == "PIRATE") 
		{
			System.out.println("You meet a pirates, you should play number games to against pirates");
			game.meetPirate();
		}
		else if (event == "UNFORTUNATE WEATHER"){
			int damage = game.meetUnfortunateWeather();
			System.out.println("Your ship's health has been reduced by " + damage + " because you just meet unfortunate weather");
			game.backToMain();
		}
		else {
			int reward = game.meetSailorToRescue();
			System.out.println("Your have been rewarded by sailors you rescued, they gave you " + reward + " coin");
			game.backToMain();
		}
	}
	
	/**
	 * Tell the player that the game is over, tell the player the reason
	 * Display the total profit and the final score
	 * Ask the player if he want to try again or not
	 */
	@Override
	public void gameOver(String message) {
		System.out.println(message);
		boolean state = false;
		while(state == false) {
			String username = game.getName();
			int durationGame = game.getTravellingDays();
			System.out.println("Name: " + username);
			System.out.println("You have play for: " + durationGame + " days");
			System.out.println("Total profit you have made: " + game.countProfit());
			System.out.println("Your final score is: " + this.getTotalProfit());
			System.out.println("Do you want to try again?");
			System.out.println("(1) Yes, I want to play again");
			System.out.println("(2) No, just quit");
			int selectedAction = scanner.nextInt();
			try {
				if (selectedAction <= 2 && selectedAction > 0) {
					state = true;
					if (selectedAction == 1) {
						game.newGame();
					}
				}
				else {
					System.out.println("Please choose from actions above");
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}

	/**
	 * print to terminal the available ships that the player can choose
	 * @param availableShips list of Ship object that the player can choose
	 */
	private void printAvailableShips(ArrayList<Ship> availableShips) {
		int index = 1;
		for (Ship ship: availableShips) {
			System.out.println("(" + index + ")" + ship);
			index++;
		}
	}
	
	/**
	 * Print the island that the player can travel to
	 * @param islands List of island object
	 */
	public void printAvailableIslands(ArrayList<Island> islands) 
	{
		boolean state = false;
		while (state == false) {
			System.out.println("Choose island that you want to have a look:");
			int num = 1;
			for (int i = 0; i < islands.size(); i++) {
				Island otherIsland = islands.get(i);
				String nameIsland = otherIsland.getIslandName();
				System.out.println("("+num+") "+ nameIsland);
				num++;	
			}
			int selectedAction = scanner.nextInt();
			try {
				if (selectedAction <= islands.size() && selectedAction >0){
					game.chooseOtherIsland(selectedAction-1);	
				}
				else {
					System.out.println("Please choose island between 1 and 4!");
				}
			} catch (Exception e) {
				System.out.println(INPUT_REQUIREMENTS);
				scanner.nextLine();
			}
		}
	}
	
	/**
	 * Display the equipment that the player has on his ship
	 * @param equipments list of equipment that the player owned
	 */
	public void printEquipments(ArrayList<Equipment> equipments) {
		int num = 1;
		System.out.println("Your ship's equipment(s): ");
		for (Equipment equipment: equipments) {
			System.out.println("(" + num + ") " + equipment.getName());
			num++;
		}
	}
	
	/**
	 * Print the goods that the player has and its purchase price
	 * @param myGoods Hash map of that store Goods as key and Integer price as value which the player owned 
	 */
	public void printMyGoods(HashMap<Goods, Integer> myGoods) {
		int index = 1;
		System.out.println("My Goods:");
		for (HashMap.Entry<Goods, Integer> set: myGoods.entrySet()) {
			String name = set.getKey().getName();
			int quantity = set.getKey().getQuantityOwned();
			
			System.out.println("(" + index + ") Name: " +  name + " Quantity: " + quantity + " Price: " + set.getValue());
			index++;
		}
		System.out.println("\n");
	}
	

	
	/**
	 * Print the list of goods
	 * @param Goods it can be interested goods that store want to buy, goods the the store sells, and goods that owned by the player. The type is has map
	 * @param message String to displays information about the goods 
	 */
	public void printGoods(HashMap<Goods, Integer> Goods,String message) {
		int index = 1;
		System.out.println("\n" + message);
		for (Entry<Goods, Integer> set: Goods.entrySet()) {
			String name = set.getKey().getName();
			System.out.println("(" + index + ") Name: " +  name + " Price: " + set.getValue() + " coins");
			index++;
		}
	}
	
	/**
	 * Use to get an array list of goods from hash map
	 * @param storeGoods The store where the player is
	 * @return a list of Goods 
	 */
	public ArrayList<Goods> accessingGoods(HashMap<Goods, Integer> storeGoods) {
		ArrayList<Goods> listOfGoods = new ArrayList<Goods>();
		for (Entry<Goods, Integer> set: storeGoods.entrySet()) {
			listOfGoods.add(set.getKey());
		}
		return listOfGoods;
	}


	
	/**
	 * Make player able to buy goods from the store
	 * @param sellGoods a hash map of goods that the store sells and also the price
	 */
	public void buyGoodsFromStore(HashMap<Goods, Integer> sellGoods) {
		ArrayList<Goods>  myList = accessingGoods(sellGoods);
		boolean state1 = false;
		
		while(state1 == false) {
			printGoods(sellGoods, "Goods that store sell:");
			System.out.println("(99) Cancel purchase");
			System.out.println("Select the goods you want to buy");
			int selectedAction = scanner.nextInt();
			try { 
				if (selectedAction == 99) {
					state1 = true;
					game.goToStore();
				}

				else if (selectedAction <= sellGoods.size() && selectedAction > 0) {
					Goods selectedGoods = myList.get(selectedAction - 1);
					int goodsPrice = sellGoods.get(selectedGoods);
					if (game.checkConditionsForBuying(1, selectedGoods, goodsPrice) == true) 
					{
						boolean state2 = false;
						while(state2 == false) {
							System.out.println("How many do you want to buy?" );
							try { 
								int quantity = scanner.nextInt();
								if (game.checkConditionsForBuying(quantity, selectedGoods, goodsPrice) == true) {
								state2 = true;
								game.buyGoods(quantity, selectedGoods, goodsPrice);
								System.out.println("Purchase Succesful!");
							}
								else {
									System.out.println("You either don't have enough money or enough capacity");
								}
							} 
							catch (Exception e) {
								System.out.println("You have to input an integer!");
								scanner.nextLine();
							}
						}
					}
					else {
						System.out.println("You either don't have enough money or enough capacity even to buy 1 item");
					}
				}

				else {
					System.out.println("Please choose from actions above");
				}
				
			} catch (Exception e) {
				System.out.println(INPUT_REQUIREMENTS);
				scanner.nextLine();
			}
		}	
	}
	

	/**
	 * Make player able to sell the goods that he owned to the store
	 * @param interestedGoods a hash map goods that the store want to buy and the price the store offers
	 */
	public void sellGoodsForStore(HashMap<Goods, Integer> interestedGoods) {
		ArrayList<Goods>  listInterestedGoods = accessingGoods(interestedGoods);
		
		boolean state1 = false;
		while(state1 == false) {
			System.out.println("(99) Cancel sell");
			printGoods(interestedGoods, "Goods that store want to buy: ");
			System.out.println("Select the goods you want to sell");
			int selectedAction = scanner.nextInt();
			try { 
				if (selectedAction == 99) {
					state1 = true;
					game.goToStore();
				}
				else if (selectedAction <= interestedGoods.size() && selectedAction > 0) {
					Goods selectedGoods = listInterestedGoods.get(selectedAction - 1);
					
					if (game.checkAvailability(selectedGoods)){
						System.out.println("You have " + selectedGoods.getQuantityOwned() +" "+ selectedGoods.getName() + " stored in your ship!");
						int goodsPrice = interestedGoods.get(selectedGoods);
						boolean state2 = false;
						while(state2 == false) {
							System.out.println("How many do you want to sell?" );
							int quantity = scanner.nextInt();
							
							try { 
								if (game.checkQuantity(quantity, selectedGoods)) {
								state2 = true;
								game.sellGoods(selectedGoods, quantity, goodsPrice);
								}
								else {
									System.out.println("You don't have that many quanity of " + selectedGoods.getName());
								}
							
							} 
							catch (Exception e) {
								System.out.println("You have to input an integer!");
								scanner.nextLine();
							}
						
						}
					}
					
					else {
						System.out.println("You don't have the item!");
					}
				}

				else {
					System.out.println("Please choose from actions above");
				}
				
			} catch (Exception e) {
				System.out.println(INPUT_REQUIREMENTS);
				scanner.nextLine();
			}
		}	
	}
	
	
	/**
	 * Make player able to buy equipment from the store
	 * @param equipmentForSale the Equipment object that the player can buy from the store
	 * @param store the current store at current location
	 */
	public void buyEquipment(Equipment equipmentForSale, Store store) {
		if (game.checkEquipmentFull()) {
			System.out.println("You can't add more equipment to your ship");
			game.goToStore();
		}
		else {
			boolean state = false;
			while(state==false) 
			{
				System.out.println("This store sell "+ equipmentForSale.getName());
				System.out.println("Price: "+equipmentForSale.getSellingPrice());
				ArrayList<Equipment> equipments = game.getSelectedShip().getEquipment();
				if(equipments.contains(equipmentForSale)) 
				{
					state = true;
					System.out.println("You can't add this equipment since you already have it set in your ship!");
					game.goToStore();
				}
				else 
				{
					System.out.println("Do you want to buy this equipment?");
					System.out.println("(1) Yes");
					System.out.println("(2) No");
					int selectedAction = scanner.nextInt();
					if (selectedAction == 1 || selectedAction == 2) 
					{
						state = true;
						if (selectedAction == 1) {
							int equipmentPrice = equipmentForSale.getSellingPrice();
							if (game.checkEnoughMoney(equipmentPrice)) {
								game.buyEquipment(equipmentForSale);
								System.out.println("The equipment has been added to your ship!");	
							}
							else {
								System.out.println("Sorry your money is not enought to buy this equipment");
							}

						}
						game.goToStore();
					}
					else
					{
						System.out.println("Please input 1 or 2");
					}
				}
			}
			
		}
	}
	

	/**
	 * tell the player a message by print the message to the terminal
	 * print message The message to the player
	 */
	@Override
	public void tellPlayer(String message) {
		System.out.println(message);
	}

	/**
	 *Make player play rock paper scissors game if the player meet pirate in random event and the system make the player play rock paper scissor game
	 */
	public void playRockPaperScissor() {
		System.out.println("The pirates ask you to play 'Rock Paper Scissors' in order to against the pirates. Now you have to choose what you want to throw. " + "\n" + "You can choose between: ");
		ArrayList<String> gameElements = new ArrayList<String>(3);
		gameElements.add("Rock");
		gameElements.add("Paper");
		gameElements.add("Scissors");
		boolean state = false;
		while (state == false) {
			System.out.println("(1) Rock");
			System.out.println("(2) Paper");
			System.out.println("(3) Scissors");
			int selectedAction = scanner.nextInt();
			try { if (selectedAction <= 3 && selectedAction > 0) {
				int decision = game.determineWinnningRPSGame();
				String piratesThrows = game.getWhatPiratesThrowRPS(decision, gameElements.get(selectedAction-1));
				System.out.println("The pirate throws " + piratesThrows);
				if (decision == 0) {
					state = true;
					int piratesDemand = game.getPiratesDemand();
					System.out.println("You lose! Pirates demands: " + piratesDemand + " coins");
					if (game.checkPirateSatisfaction()) {
						game.removeAllGoods();
						game.backToMain();
					}
					else {
						game.removeAllGoods();
						int money = game.getMoney();
						game.updateMoney(money * -1);
						game.gameOver("You lose play rock paper scissor game! \n the pirate took all of your money and your items in your ship!");
					}
				}
				else if (decision == 1) {
					System.out.println("You need to play again!");
				}
				else {
					state = true;
					System.out.println("You Win!! You can Continue your journey to your destination!");
					game.backToMain();
				}
				
			}

				else {
					System.out.println("Please choose from actions above");
				}
				
			} catch (Exception e) {
				System.out.println(INPUT_REQUIREMENTS);
				scanner.nextLine();
			}
			
		}

	}
	
	/**
	 *Make player play rolling a die game if the player meet pirate in random event and the system make the player play rolling a die game
	 */
	public void playRollingADie() {
		System.out.print("The pirates ask you to play 'Rolling a Die'. You have to predict what is the outcome. " + "\n" + "You can choose between: ");
		ArrayList<String> gameElements2 = new ArrayList<String>(3);
		gameElements2.add("Even");
		gameElements2.add("Odd");
		boolean state = false;
		while (state == false) {
			System.out.println("(1) Even");
			System.out.println("(2) Odd");
			try { 
				int selectedAction = scanner.nextInt();
				if (selectedAction <= 2 && selectedAction > 0) {
				int decision = game.determineWinnningRollingADie();
				int piratesThrows = game.getWhatPiratesThrowRD(decision, gameElements2.get(selectedAction-1));
				System.out.println("The die is showing " + piratesThrows);
				if (decision == 0) {
					state = true;
					int piratesDemand = game.getPiratesDemand();
					System.out.println("You lose! Pirates demands: " + piratesDemand + " coins");
					if (game.checkPirateSatisfaction()) {
						game.removeAllGoods();
						game.backToMain();
					}
					else {
						game.removeAllGoods();
						int money = game.getMoney();
						game.updateMoney(money * -1);
						game.gameOver("You lose play rock paper scissor game! \nthe pirate took all of your money and your items in your ship!");
					}
				}
				else {
					state = true;
					System.out.println("You Win!! You can Continue your journey to your destination!");
					game.backToMain();
				}
				
			}

				else {
					System.out.println("Please choose from actions above");
				}
				
			} catch (Exception e) {
				System.out.println(INPUT_REQUIREMENTS);
				scanner.nextLine();
			}
			
		}
	}

	/**
	 * Get the total profit calculated in Game Environment
	 * @return 0 if the profit is less than 0 otherwise return the profit
	 */
	public int getTotalProfit() {
		if (game.countProfit() >= 0) {
			return 0;
		}
		else {
			return game.countProfit();
		}
	}

}
