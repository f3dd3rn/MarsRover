package test;

import static org.junit.Assert.*;
import model.CardinalDirection;
import model.Command;
import model.Rover;

import org.junit.Test;

/**
 * Test for Rover
 * @author stephanie feddern
 *
 */
public class RoverTest {
	
	@Test
    public void testLeftTurn() {
		Rover rover = new Rover(0, 2, CardinalDirection.valueOf("W"));
		rover.turn90DegreesLeft();
		assertTrue(rover.positionAndDirectionToString().equals("0 2 S")); 
	}
	
	@Test
	public void testRightTurn() {
		Rover rover = new Rover(0, 2, CardinalDirection.valueOf("W"));
		rover.turn90DegreesRight();
		assertTrue(rover.positionAndDirectionToString().equals("0 2 N"));
	}
	
	@Test
	public void testMoveForward() {
		Rover rover = new Rover(0, 2, CardinalDirection.valueOf("S"));
		rover.moveForward();
		assertTrue(rover.positionAndDirectionToString().equals("0 1 S"));
	}
	
	@Test
	public void testFirstCommandSequence() {
		Rover rover = new Rover(1, 2, CardinalDirection.valueOf("N"));
		rover.turn90DegreesLeft();
		rover.moveForward();
		rover.turn90DegreesLeft();
		rover.moveForward();
		rover.turn90DegreesLeft();
		rover.moveForward();
		rover.turn90DegreesLeft();
		rover.moveForward();
		rover.moveForward();
		//TODO: expect rover.toString to be "1 3 N"
	}
	
	@Test
	public void testOtherCommandSequence() {
		Rover rover = new Rover(1, 2, CardinalDirection.valueOf("N"));
		for (char commandAbbreviation : "LMLMLMLMM".toCharArray()) {
			executeCommand(String.valueOf(commandAbbreviation), rover);
		}
		
		assertTrue(rover.positionAndDirectionToString().equals("1 3 N"));
	}
	
	private void executeCommand(String singleCommand, Rover rover) {
		switch (Command.valueOf(singleCommand)) {
		case LEFT:
			rover.turn90DegreesLeft();
			break;
			
		case RIGHT:
			rover.turn90DegreesRight();
			break;
			
		case MOVE:
			rover.moveForward();
			break;

		default:
			throw new UnsupportedOperationException("Don't know what to do.");
		}
	}
}
