package model;

public enum Command {
	LEFT("L"), RIGHT("R"), MOVE("M");
	
	private String abbreviation;
	
	private Command(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	@Override
	public String toString() {
		return abbreviation;
	}
}
