package core;

/**
 * Child class of Items class that models a goods
 *
 */
public class Goods extends Item {
	
	// The size of the Goods
	private double size;
	 
	/**
	 * Attribute that used to initialize the quantity owned by the trader as 0
	 */
	public int quantityOwned;
	
	/**
	 * Attribute that used to initialize the quantity sold by the trader as 0
	 */
	public int quantitySold;
	
	
	/**
	 * Creates object that will be sale by the store
	 * 
	 * @param name The name of the Goods
	 * @param size The size of the Goods
	 */
	public Goods(String name, double size) {
		super(name);
		this.size = size;
		this.quantityOwned = 0;
		this.quantitySold = 0;
	}
	
	
	/**
	 * Takes parameter quantity and set the quantity of the goods owned by the trader
	 * 
	 * @param quantity The quantity of the Goods
	 */
	public void setQuantityOwned( int quantity ) {
		this.quantityOwned = quantity;
	}
	
	/**
	 * Takes parameter quantity and set the quantity of the goods that has been sold by the trader
	 * 
	 * @param quantity The quantity of the Goods
	 */
	public void setQuantitySold( int quantity ) {
		this.quantitySold = quantity;
	}
	
	/**
	 * Takes parameter quantity and updating the quantity owned by the trader
	 * 
	 * @param quantity The quantity of the goods
	 */
	public void updateQuantityOwned(int quantity) {
		this.quantityOwned += quantity;
	}
	
	/**
	 * Takes parameter quantity and updating the quantity of the goods that has been sold by the trader
	 * 
	 * @param quantity The quantity of the goods
	 */
	public void updateQuantitySold(int quantity) {
		this.quantitySold += quantity;
	}
	
	/**
	 * Gets the quantity owned by the trader
	 * 
	 * @return the quantity owned by the trader
	 */
	public int getQuantityOwned() {
		return this.quantityOwned;
	}
	
	/**
	 * Gets the quantity sold by the trader
	 * 
	 * @return the quantity sold by the trader
	 */
	public int getQuantitySold() {
		return this.quantitySold;
	}
	
	/**
	 * Gets the size of the goods
	 * 
	 * @return the size of the goods
	 */
	public double getSize() {
		return this.size;
	}
	
}
