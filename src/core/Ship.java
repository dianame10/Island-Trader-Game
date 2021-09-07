package core;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Creates a model of this Ship
 *
 */
public class Ship {
	
	// The name of this ship
	private final String name;

	// The number of crew of this ship
	private final int crew;
	
	// Maximum cargo capacity
	private double maximumCargoCapacity;
	
	// Rate of ships health
	private int shipsHealth;
	
	// The speed of this ship
	private final int speed;
	
	// List for ships equipment
	private ArrayList<Equipment> shipsEquipment = new ArrayList<Equipment>();
	
	// Winning probability of the player against the pirates
	private double probability;
	
	// Total value of the Goods in the ship
	private int totalValueOwned;
	
	// Wages of the crews in the ship per day
	private int wagesPerDay;
	
	// HashMap that stored a goods and price that owned by the trader
	HashMap<Goods, Integer> myGoods = new HashMap<Goods, Integer>();
	
	// HashMap that stored a goods and list of Island that has been sold and the place where it was sold. One Item could be sold in different places
	HashMap<Goods, ArrayList<String>> soldItems = new HashMap<Goods, ArrayList<String>>();
	
	
	/**
	 * Creates a ship that have name, crew members, ship's capacity, ship's health status and ship's speed
	 * 
	 * @param shipsName The name of the ship
	 * @param shipsCrew The number of crew members in this ship
	 * @param shipsCapacity The maximum cargo capacity of this ship
	 * @param shipsStatus The health status of this ship
	 * @param shipsSpeed The speed of this ship
	 */
	public Ship(String shipsName, int shipsCrew, int shipsCapacity, int shipsStatus, int shipsSpeed) {
		this.name = shipsName;
		this.crew = shipsCrew;
		this.maximumCargoCapacity = shipsCapacity;
		this.shipsHealth = shipsStatus;
		this.speed = shipsSpeed;
		this.setProbability(0.5);
		this.totalValueOwned = 0;
		this.wagesPerDay = shipsCrew * 1;// 1 is cost per crew per day
	}
	
	/**
	 * Set the probability of meeting random event to new probability
	 * @param probability the probability of meeting random event
	 */
	public void setProbability(double probability) {
		this.probability = probability;
	}
	/**
	 * Gets the name of this ship
	 * 
	 * @return the name of this ship
	 */
	public String getShipName() {
		return this.name;
	}
	
	
	/**
	 * Gets the wages cost to pay crew members per day
	 * 
	 * @return the wages cost per day
	 */
	public int getWagesPerDay() {
		return this.wagesPerDay;
	}
	
	
	/**
	 * Gets the total value of the goods that owned by the trader
	 * 
	 * @return the total value of the goods in the ship
	 */
	public int getTotalValueOwned() {
		return this.totalValueOwned;
	}
	
	/**
	 * Gets the number of crews in this ship
	 * 
	 * @return the number of crews
	 */
	public int getCrew() {
		return this.crew;
	}
	
	/**
	 * Gets the maximum capacity cargo of this ship
	 * 
	 * @return the maximum capacity cargo
	 */
	public double getCapacity() {
		return this.maximumCargoCapacity;
	}
	
	/**
	 * Gets the health status of this ship
	 * 
	 * @return the health status of this ship
	 */
	public int getShipsStatus() {
		return this.shipsHealth;
	}
	
	/**
	 * Gets the winning probability of this ship when play against pirates
	 * 
	 * @return the amount of winning probability
	 */
	public double getProbability() {
		return this.probability;
	}
	
	/**
	 * Gets the list of equipment in this ship
	 * 
	 * @return the list of equipment in this ship
	 */
	public ArrayList<Equipment> getEquipment() {
		return shipsEquipment;
	}
	
	/**
	 * Return all goods and its price in type HaspMap in this ship
	 * 
	 * @return HashMap that contains goods and its price
	 */
	public HashMap<Goods, Integer> getMyGoods() {
		return myGoods;
	}
	
	/**
	 * Return all goods and its sold place in type HaspMap of this ship
	 * 
	 * @return HashMap that contains goods and place it was sold 
	 */
	public HashMap<Goods, ArrayList<String>> getSoldItems() {
		return soldItems;
	}
	
	/**
	 * Gets the speed of this ship
	 * 
	 * @return the speed of this ship
	 */
	public int getShipsSpeed() {
		return this.speed;
	}
	
	/**
	 * Takes integer status as parameter and set the health status of this ship
	 * 
	 * @param status the new amount of ship's health
	 */
	public void setShipStatus(int status) {
		this.shipsHealth = status;
	}
	
	/**
	 * Function that show that trader has lose against pirates and pirated take all of the goods in the ship
	 */
	public void gotPirated() {
		for (HashMap.Entry<Goods, Integer> set: myGoods.entrySet()) {
			Goods good = set.getKey();
			good.setQuantityOwned(0);
		}
		myGoods.clear();
	}
	
	
	/**
	 * Buy new goods and add it into the ship
	 * 
	 * @param goods The new goods that just been purchase
	 * @param price The purchase price of the goods
	 * @param size the size of the goods
	 * @param quantity the amount of quantity of goods that just been purchased by trader
	 */
	public void addGoods(Goods goods, int price, double size, int quantity) {
		
		if(myGoods.containsKey(goods) == true ) {
			goods.updateQuantityOwned(quantity);
			this.totalValueOwned += (quantity * price);
			this.maximumCargoCapacity -= size;
		}
		else {
			this.maximumCargoCapacity -= size;
			goods.updateQuantityOwned(quantity);
			myGoods.put(goods, price);
		}
		
	}
	
	/**
	 * Remove the goods from the ship if the goods been sold or taken away by the pirates
	 * 
	 * @param good the good that has been sold or taken away
	 * @param quantity the amount of goods 
	 * @param freeCapacity the total capacity that has been taken away from the ship
	 */
	public void removeGoods(Goods good,int quantity, double freeCapacity) {
		
		this.maximumCargoCapacity += freeCapacity;
		int boughtPrice = this.myGoods.get(good);
		this.totalValueOwned -= (quantity * boughtPrice);
		if (quantity == good.getQuantityOwned()) {
			this.myGoods.remove(good);
			good.updateQuantityOwned(-1*quantity);
		}
		else {
			good.updateQuantityOwned(-1*quantity);
		}
	}
	
	/**
	 * Function that used to updating the sold items HashMap
	 * @param good the good that has been sold
	 * @param island The place where it was sold
	 * @param quantity the amount of goods that has been sold
	 */
	public void updateSoldItems(Goods good, Island island, int quantity) {
		good.setQuantitySold(quantity);
		if (soldItems.containsKey(good)) {
			this.soldItems.get(good).add(island.getIslandName());
		}
		else {
			ArrayList<String> islandSold = new ArrayList<String>();
			islandSold.add(island.getIslandName());
			this.soldItems.put(good, islandSold);
		}
	}
	
	
	/**
	 * Function that used to install or add new equipment for the ship
	 * @param extraEquipment the new equipment that just been purchase by the trader
	 */
	public void addEquipment(Equipment extraEquipment) {
		this.shipsEquipment.add(extraEquipment);
		this.setProbability(this.getProbability() + extraEquipment.getExtraProbability());
	}
	
	/**
	 * Gets the number of the equipment that has been purchased by the trader
	 * 
	 * @return the number if equipment in the ship 
	 */
	public int getNumberEquipment() {
		return this.shipsEquipment.size();
	}
	
	/**
	 * Function that used to check if the equipment been installed in the ship
	 * @param equipment the equipment that used to check
	 * @return true if ship already have that equipment, false otherwise
	 */
	public boolean checkEquipment(Equipment equipment) {
		return this.shipsEquipment.contains(equipment);
	}
	
	/**
	 *return string that contain the specification of the ship
	 */
	public String toString() {
		return "Ship: " + name + ", number of crews: " + crew + ", maximum cargo capacity: " + maximumCargoCapacity + 
				", ship's status: " + shipsHealth + "%" + ", speed: " + speed + " km/day";
	}

}
