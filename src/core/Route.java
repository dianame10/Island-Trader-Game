package core;

/**
 * Class that models a route to connecting Islands
 *
 */
public class Route {
	
	// one end of the connected Island
	private String oneEnd;
	
	// one end of the connected Island
	private String otherEnd;
	
	// a distance between two connected Island
	private int distance;

	// probability of random event might occur in this route
	private int probability;
	

	/**
	 * Construct a route to connecting two Island
	 * 
	 * @param distance The distance between two Island
	 * @param oneEnd either origin or destination between two connected Island
	 * @param otherEnd either origin0 or destination between two connected Island
	 * @param probability The probability of random event that might occur in this route
	 */
	public Route(int distance, String oneEnd, String otherEnd, int probability) {
		this.distance = distance;
		this.oneEnd = oneEnd;
		this.otherEnd = otherEnd;
		this.probability = probability;
	}
	
	/**
	 * Gets the one of two connected Island as either origin or destination Island
	 * 
	 * @return The name of the Island
	 */
	public String getOneEnd() {
		return this.oneEnd;
	}
	
	/**
	 * Gets the one of two connected Island as either origin or destination Island
	 * 
	 * @return The name of the Island
	 */
	public String getOtherEnd() {
		return this.otherEnd;
	}
	
	/**
	 * Gets the distance of two connected Island
	 * 
	 * @return the distance of this route
	 */
	public int getDistance() {
		return this.distance;
	}
	
	/**
	 * Gets the probability of random event that might occur in this route
	 * 
	 * @return the probability in this route
	 */
	public int getProbability() {
		return this.probability;
	}
	
	/**
	 * @return a String message that used to show the specification of this route
	 */
	public String toString() {
		return "Route between " + this.otherEnd + " and " + this.oneEnd + " with "+ probability+"% chances facing random event";
	}


}
