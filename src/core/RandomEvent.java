package core;
import java.util.ArrayList;


/**
 * Class that models a random event that might occur in the routes
 *
 */
public class RandomEvent {
	
	/**
	 * A list that contain all types of random events that might occur
	 */
	public final ArrayList<String> TYPEOFEVENTS = new ArrayList<String>();
	
	/**
	 * Creates a list of random events that might occur
	 */
	public RandomEvent() {
		TYPEOFEVENTS.add("PIRATE");
		TYPEOFEVENTS.add("UNFORTUNATE WEATHER");
		TYPEOFEVENTS.add("SAILOR TO RESCUE");
	}
	
	/**
	 * Gets the random event by generate random number and access it through indexing
	 * 
	 * @return the random event
	 */
	public String getRandomEvent() {
		//random pick random event
		int random_int = (int)Math.floor(Math.random()*(2-0+1)+0);
		return TYPEOFEVENTS.get(random_int);
	}
	
	
}
