package test.java.com.thoughtworks.mars_rover;

import static org.junit.Assert.*;
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


public class WorldTest {
	private World world;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Before
    public void setUp() {
        world = new World(5, 5);
    }
	
	@After
	public void cleanUp() {
	    world = null;
	}
	
	@Test
	public void testClearPosition() {
		boolean result = world.isVacant(new Coordinate(0, 0));
		assertTrue(result);
	}
	
	@Test
	public void testPositionIsBeyondBoundaries() {
		boolean result = world.isVacant(new Coordinate(6, 0));
		assertFalse(result);
	}
	
	@Test
	public void testRoverRegistersOnCreation() {
		Rover rover = new Rover(0, 0, CardinalDirection.WEST, world);
		boolean result = world.isVacant(rover.getCoordinate());
		assertFalse(result);
	}
	
	@Test
	public void testRoverInformsWorldAboutChangedPosition() {
		Coordinate coordinate = new Coordinate(0,0);
		Rover rover = new Rover(coordinate, CardinalDirection.EAST, world);
		Command[] commands = {Command.MOVE};
		rover.executeCommandSequence(commands);
		assertFalse(world.isVacant(rover.getCoordinate()));
		assertTrue(world.isVacant(coordinate));
	}
}
