package test.java.com.thoughtworks.mars_rover;

import static org.junit.Assert.assertTrue;
import main.java.com.thoughtworks.mars_rover.controller.World;
import main.java.com.thoughtworks.mars_rover.model.CardinalDirection;

import org.junit.Before;
import org.junit.Test;


public class WorldTest {
	private World world;
	
	@Before
    public void setUp() {
        world = new World(5, 5);
    }
	
	@Test
	public void testFirstCommandSequence() {
		String result = world.executeCommandSequence(1, 2, CardinalDirection.getByAbbreviation("N"), "LMLMLMLMM".toCharArray());
		
		assertTrue(result.equals("1 3 N"));
	}
	
	@Test
	public void testSecondCommandSequence() {
		String result = world.executeCommandSequence(3, 3, CardinalDirection.getByAbbreviation("E"), "MMRMMRMRRM".toCharArray());
		
		assertTrue(result.equals("5 1 E"));
	}
}
