package test.java.com.thoughtworks.mars_rover;

import static org.junit.Assert.*;
import main.java.com.thoughtworks.mars_rover.model.CardinalDirection;
import main.java.com.thoughtworks.mars_rover.model.Command;
import main.java.com.thoughtworks.mars_rover.model.KeyToEnumUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
/**
 * Test for main.java.com.thoughtworks.mars_rover.model.KeyToEnumUtil
 * 
 * @author stephanie
 *
 */
public class KeyToEnumUtilTest {
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@Test
    public void testGetCardinalDirectionByKey() {
		KeyToEnumUtil<String, CardinalDirection> util = new KeyToEnumUtil<String, CardinalDirection>();
		CardinalDirection c = util.getByKey("N", CardinalDirection.values());
		assertEquals(c, CardinalDirection.NORTH);
	}

	@Test
	public void testGetCommandByKey() {
		KeyToEnumUtil<String, Command> util = new KeyToEnumUtil<String, Command>();
		Command c = util.getByKey("M", Command.values());
		assertEquals(c, Command.MOVE);
	}
	
	@Test
    public void testThrowExceptionOnInvalidCardinalDirectionAbbrevition() {
		thrown.expect(IllegalArgumentException.class);
		KeyToEnumUtil<String, CardinalDirection> util = new KeyToEnumUtil<String, CardinalDirection>();
		util.getByKey("P", CardinalDirection.values());
	}

	@Test
	public void testThrowExceptionOnInvalidCommandAbbrevition() {
		thrown.expect(IllegalArgumentException.class);
		KeyToEnumUtil<String, Command> util = new KeyToEnumUtil<String, Command>();
		util.getByKey("O", Command.values());
	}
}
