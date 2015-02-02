package test.java.com.thoughtworks.mars_rover;

import static org.junit.Assert.assertTrue;
import main.java.com.thoughtworks.mars_rover.model.CardinalDirection;
import main.java.com.thoughtworks.mars_rover.model.KeyToEnumUtil;

import org.junit.Test;

public class KeyToEnumUtilTest {
	@Test
    public void testGetByKey() {
		KeyToEnumUtil<String, CardinalDirection> util = new KeyToEnumUtil<String, CardinalDirection>();
		CardinalDirection c = util.getByKey("N", CardinalDirection.values());
		assertTrue(c.equals(CardinalDirection.NORTH)); 
	}
}
