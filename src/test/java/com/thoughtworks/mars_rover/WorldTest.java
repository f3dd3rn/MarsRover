package test.java.com.thoughtworks.mars_rover;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import main.java.com.thoughtworks.mars_rover.controller.World;
import main.java.com.thoughtworks.mars_rover.model.Coordinate;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test for main.java.com.thoughtworks.mars_rover.controller.World without mocking.
 * 
 * @author stephanie
 *
 */
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
		assertTrue(world.isVacant(new Coordinate(0, 0)));
	}
	
	@Test
	public void testPositionIsBeyondBoundaries() {
		assertFalse(world.isVacant(new Coordinate(6, 0)));
	}
	
	@Test
	public void testRegisterNewObjectAtPosition() {
		Coordinate coordinate = new Coordinate(2, 2);
		world.registerNewObjectAtPosition(coordinate);
		assertFalse(world.isVacant(coordinate));
	}
	
	@Test
	public void testOnPositionChange() {
		Coordinate previousCoordinate = new Coordinate(0,0);
		Coordinate currentCoordinate = new Coordinate(0,1);
		world.onPositionChange(previousCoordinate, currentCoordinate);
		assertFalse(world.isVacant(currentCoordinate));
		assertTrue(world.isVacant(previousCoordinate));
	}
}
