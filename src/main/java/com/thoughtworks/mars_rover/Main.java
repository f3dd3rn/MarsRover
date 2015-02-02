package main.java.com.thoughtworks.mars_rover;

import java.util.Arrays;

import main.java.com.thoughtworks.mars_rover.controller.World;
import main.java.com.thoughtworks.mars_rover.model.CardinalDirection;

public class Main {
	private World world;
	
	public void run(String[] input) {
		processInput(input);
	}
	
	private void processInput(String[] input){
		processWorldInput(input[0], input[1]);
		processInputForRovers(Arrays.copyOfRange(input, 2, input.length));
	}
	
	private void processWorldInput(String x, String y){
		world = new World(Integer.parseInt(x), Integer.parseInt(y));
	}
	
	private void processInputForRovers(String[] input){
		String[] inputRover = new String[3];
		if(input.length % 4 > 0) {
			throwInvalidInputException();
		}
		for (int i = 0; i < input.length; i++) {
			if(i % 4 == 3){
				String endPositionOfRover = world.executeCommandSequence(
					Integer.parseInt(inputRover[0]), 
					Integer.parseInt(inputRover[1]), 
					CardinalDirection.getByAbbreviation(inputRover[2]), 
					input[i].toCharArray()
				);
				System.out.println(endPositionOfRover);
			} else {
				inputRover[i % 4] = input[i];
			}
		}
	}
	
	private void throwInvalidInputException(){
		throw new IllegalArgumentException("Input is invalid");
	}
	
	public static void main(String[] args) {
		Main run = new Main();
		run.run(args);
	}
}
