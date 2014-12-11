package model;

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
}
