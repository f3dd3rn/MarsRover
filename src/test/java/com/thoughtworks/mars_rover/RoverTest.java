package test.java.com.thoughtworks.mars_rover;

import static org.junit.Assert.*;

import java.util.concurrent.RejectedExecutionException;

import main.java.com.thoughtworks.mars_rover.controller.World;
import main.java.com.thoughtworks.mars_rover.model.CardinalDirection;
import main.java.com.thoughtworks.mars_rover.model.Command;
import main.java.com.thoughtworks.mars_rover.model.Coordinate;
import main.java.com.thoughtworks.mars_rover.model.Rover;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
/**
 * Test for main.java.com.thoughtworks.mars_rover.model.Rover
 * 
 * @author stephanie
 *
 */
public class RoverTest {
	private World world;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() {
	    world = new World(4,4);
	}
	
	@After
	public void cleanUp() {
	    world = null;
	}
	
	@Test
    public void testLeftTurn() {
		Rover rover = new Rover(new Coordinate(0, 2), CardinalDirection.WEST, world);
		rover.turn90DegreesLeft();
		assertEquals(rover.toString(),"0 2 S"); 
	}
	
	@Test
	public void testRightTurn() {
		Rover rover = new Rover(new Coordinate(0, 2), CardinalDirection.WEST, world);
		rover.turn90DegreesRight();
		assertEquals(rover.toString(),"0 2 N");
	}
	
	@Test
	public void testMoveForward() {
		Rover rover = new Rover(0, 2, CardinalDirection.SOUTH, world);
		Command[] commands = {Command.MOVE};
		rover.executeCommandSequence(commands);
		assertEquals(rover.toString(),"0 1 S");
	}
	
	@Test
	public void testStayInPositionToNotExceedWorldBoundaries() {
		thrown.expect(RejectedExecutionException.class);
		Rover rover = new Rover(0, 0, CardinalDirection.SOUTH, world);
		Command[] commands = {Command.MOVE};
		rover.executeCommandSequence(commands);
	}
	
	@Test
	public void testStayInPositionToNotCrashOtherObject() {
		thrown.expect(RejectedExecutionException.class);
		new Rover(0, 1, CardinalDirection.WEST, world);
		Rover rover2 = new Rover(0, 0, CardinalDirection.NORTH, world);
		Command[] commands = {Command.MOVE};
		rover2.executeCommandSequence(commands);
	}
}
