package main.java.com.thoughtworks.mars_rover.model;

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

	/**
	 * Finds and returns Command by its abbreviation.
	 * 
	 * @param command: String: abbreviation of command
	 * @return Command
	 */
	public static Command getByAbbreviation(String command) {
		KeyToEnumUtil<String, Command> keyToEnum = new KeyToEnumUtil<String, Command>();
		return keyToEnum.getByKey(command, values());
	}
}
