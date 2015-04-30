package main.java.com.thoughtworks.mars_rover.model;

public enum CardinalDirection {
	NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");
	
	private String abbreviation;
	
	private CardinalDirection(String abbreviation) {
		this.abbreviation = abbreviation;	
	}
	
	@Override
	public String toString() {
		return this.abbreviation;
	}
	
	/**
	 * Finds and returns CardinalDirection by its abbreviation.
	 * 
	 * @param direction: String: abbreviation of cardinal direction
	 * @return CardinalDirection
	 */
	public static CardinalDirection getByAbbreviation(String direction) {
		KeyToEnumUtil<String, CardinalDirection> keyToEnum = new KeyToEnumUtil<String, CardinalDirection>();
		return keyToEnum.getByKey(direction, values());
	}
}
