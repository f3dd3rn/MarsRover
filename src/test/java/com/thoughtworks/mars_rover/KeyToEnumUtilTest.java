package test.java.com.thoughtworks.mars_rover;

import static org.junit.Assert.assertTrue;
import main.java.com.thoughtworks.mars_rover.model.CardinalDirection;
import main.java.com.thoughtworks.mars_rover.model.Command;
import main.java.com.thoughtworks.mars_rover.model.KeyToEnumUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class KeyToEnumUtilTest {
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@Test
    public void testGetCardinalDirectionByKey() {
		KeyToEnumUtil<String, CardinalDirection> util = new KeyToEnumUtil<String, CardinalDirection>();
		CardinalDirection c = util.getByKey("N", CardinalDirection.values());
		assertTrue(c.equals(CardinalDirection.NORTH));
	}

	@Test
	public void testGetCommandByKey() {
		KeyToEnumUtil<String, Command> util = new KeyToEnumUtil<String, Command>();
		Command c = util.getByKey("M", Command.values());
		assertTrue(c.equals(Command.MOVE));
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
