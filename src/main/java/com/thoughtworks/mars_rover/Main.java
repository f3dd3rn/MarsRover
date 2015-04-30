package main.java.com.thoughtworks.mars_rover;

import java.util.Arrays;

import main.java.com.thoughtworks.mars_rover.controller.World;
import main.java.com.thoughtworks.mars_rover.model.CardinalDirection;
import main.java.com.thoughtworks.mars_rover.model.Command;
import main.java.com.thoughtworks.mars_rover.model.KeyToEnumUtil;
import main.java.com.thoughtworks.mars_rover.model.Rover;
/**
 * Main checks input, initializes application and forwards input.
 * 
 * @author stephanie
 *
 */
public class Main {
	
	public static void main(String[] args) {
		if(args.length > 0) {
			Main main = new Main();
			World world = main.createWorld(args[0]);
			String[] results = main.processDataForRovers(Arrays.copyOfRange(args, 1, args.length), world);
			main.printResults(results);
		} else {
			System.out.println("No data given.");
		}
	}
	
	private void printResults(String[] results) {
		for (String result : results) {
			System.out.println(result);
		}
	}

	private String[] processDataForRovers(String[] roverData, World world) {
		if((roverData.length % 2) == 0) {
			String[] results = new String[roverData.length/2];
			for (int i = 0; i+1 < roverData.length; i = i+2) {
				String[] roverCreationData = roverData[i].split(" ");
				Rover rover = createRover(roverCreationData, world);
				Command[] commands = convertCharsToCommands(roverData[i+1].toCharArray());
				results[i/2] = rover.executeCommandSequence(commands);
			}
			return results;
		}
		throw new IllegalArgumentException("Invalid data for rovers.");
	}

	private Command[] convertCharsToCommands(char[] charArray) {
		Command[] commands = new Command[charArray.length];
		KeyToEnumUtil<String, Command> keyToEnumUtil = new KeyToEnumUtil<String, Command>();
		for (int i = 0; i < charArray.length; i++) {
			commands[i] = keyToEnumUtil.getByKey(String.valueOf(charArray[i]), Command.values());
		}
		return commands;
	}

	private Rover createRover(String[] roverCreationData, World world) {
		if(roverCreationData.length > 2) {
			int x = Integer.parseInt(roverCreationData[0]);
			int y = Integer.parseInt(roverCreationData[1]);
			CardinalDirection facing = CardinalDirection.getByAbbreviation(roverCreationData[2]);
			return new Rover(x,y,facing,world);
		}
		throw new IllegalArgumentException("Missing arguments for rover creation. Given arguments: " + roverCreationData.toString());
	}

	private World createWorld(String data) {
		String[] args = data.split(" ");
		if(args.length > 1){
			int outerX = Integer.parseInt(args[0]);
			int outerY = Integer.parseInt(args[1]);
			return new World(outerX, outerY);
		}
		throw new IllegalArgumentException("Missing arguments for world creation. Given arguments: " + data);
	}
}
