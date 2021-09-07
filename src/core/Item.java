package core;


/**
 * Parent class of Goods and Equipment class the models an Item
 */
public class Item {
	
	//The name of the Items of type String
	private String name;
	

	/**
	 * Creates an Items that can be stored in the store and ship
	 * 
	 * @param name The name of the Items
	 */
	public Item(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name of the Item
	 * 
	 * @return the name of the Item
	 */
	public String getName() {
		return this.name;
	}
	
}


