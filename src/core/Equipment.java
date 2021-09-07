package core;

/**
 * child class that models an equipment
 */
public class Equipment extends Item {
	
	//Amount of winning probability that will be added to the ship when player buy this equipment
	private double probability;
	
	//Price of this equipment
	private int sellingPrice;
	
	/**
	 * Creates an equipment with the given characteristics
	 * 
	 * @param name for The name of the equipment
	 * @param probability for the amount of winning probability
	 * @param price for the price of this equipment
	 */
	public Equipment(String name, double probability, int price) {
		super(name);
		this.probability = probability;
		this.sellingPrice = price;
	}
	
	/**
	 * Gets the name of this equipment
	 * 
	 * @return the name of this equipment from superclass
	 */
	public String getName() {
		return super.getName();
	}
	
	/**
	 * Gets the selling price of this equipment
	 * 
	 * @return the selling price of this equipment
	 */
	public int getSellingPrice() {
		return sellingPrice;
	}
	
	/**
	 * Gets the winning probability that will help player play againts pirates
	 * 
	 * @return the extra winning probability
	 */
	public double getExtraProbability() {
		return this.probability;
	}
}
