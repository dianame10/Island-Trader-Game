package core;
import java.util.ArrayList;

/**
 * Class that models an Island
 *
 */
public class Island {
	
	// The name of the Island
	private String name;
	
	// Store's name in this Island
	private Store storeName;
	
	// The collection of the routes that connect to this Island
	private ArrayList<Route> routeCollections;
	
	/**
	 * Construct an Island with name, store name and collection of routes that connect to this Island
	 * 
	 * @param name The name of this Island
	 * @param storeName The store's name in this Island
	 * @param routeCollections a collections of routes that connect to this Island
	 */
	public Island(String name, Store storeName, ArrayList<Route> routeCollections) {
		this.name = name;
		this.storeName = storeName;
		this.routeCollections = routeCollections;
		
	}
	
	/**
	 * Gets the Island's name
	 * 
	 * @return the name of this Island
	 */
	public String getIslandName() {
		return name;
	}
	
	/**
	 * Gets the collection of routes that connect to this Island
	 * 
	 * @return the collection of the routes
	 */
	public ArrayList<Route> getRouteCollections() {
		return routeCollections;
	}
	
	/**
	 * Gets the name of the store in this Island 
	 * 
	 * @return the name of the store
	 */
	public Store getStore() {
		return storeName;
	}
	
	

}
