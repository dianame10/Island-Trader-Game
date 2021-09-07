package core;

import java.util.HashMap;

/**
 * Construct a model of store in the Island
 *
 */
public class Store {
	
	//HashMap of Goods that sell by the store and its price
	private HashMap<Goods,Integer> sellGoods;
	
	// HashMap of Goods that interested by the store and its offered price
	private HashMap<Goods, Integer> interestedGoods;
	
	// Equipment that sell by the store
	private Equipment equipment;
	
	/**
	 * Creates a store that have sell Goods, interested Goods, and equipment that store sell for the ship
	 * 
	 * @param sellGoods HashMap of Goods that sell by the store and its price
	 * @param interestedGoods HashMap of Goods that interested by the store and its offered price
	 * @param equipment the Equipment that sell by the store
	 */
	public Store(HashMap<Goods, Integer> sellGoods,HashMap<Goods, Integer> interestedGoods, Equipment equipment) {
		this.sellGoods = sellGoods;
		this.interestedGoods = interestedGoods;
		this.equipment = equipment;
		
	}
	

	/**
	 * Gets the equipment the store sell
	 * 
	 * @return the equipment
	 */
	public Equipment getEquipment() {
		return equipment;
	}
	
	/**
	 * Gets the HashMap that contain goods sell by the store and its price
	 * 
	 * @return the HashMap of the sellGoods
	 */
	public HashMap<Goods, Integer> getSellGoods() {
		return sellGoods;
	}
	
	/**
	 * Gets the HashMap that contain goods that interested by the Store and its price 
	 * 
	 * @return the HashMap of interestedGoods
	 */
	public HashMap<Goods, Integer> getInteresedGoods() {
		return interestedGoods;
	}
	
	
}
