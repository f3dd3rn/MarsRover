package test.java.com.thoughtworks.mars_rover;

import static org.junit.Assert.assertTrue;
import main.java.com.thoughtworks.mars_rover.controller.Checkable;
import main.java.com.thoughtworks.mars_rover.controller.World;
import main.java.com.thoughtworks.mars_rover.model.CardinalDirection;
import main.java.com.thoughtworks.mars_rover.model.Coordinate;
import main.java.com.thoughtworks.mars_rover.model.Rover;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Test for Rover. Testing important functionality.
 * @author stephanie feddern
 */
public class RoverTest {
	private Checkable world;
	
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
		Rover rover = new Rover(new Coordinate(0, 2), CardinalDirection.getByAbbreviation("W"), world);
		rover.turn90DegreesLeft();
		assertTrue(rover.toString().equals("0 2 S")); 
	}
	
	@Test
	public void testRightTurn() {
		Rover rover = new Rover(new Coordinate(0, 2), CardinalDirection.getByAbbreviation("W"), world);
		rover.turn90DegreesRight();
		assertTrue(rover.toString().equals("0 2 N"));
	}
	
	@Test
	public void testMoveForward() {
		Rover rover = new Rover(new Coordinate(0, 2), CardinalDirection.getByAbbreviation("S"), world);
		rover.move();
		assertTrue(rover.toString().equals("0 1 S"));
	}
}
