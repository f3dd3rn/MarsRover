package test.java.com.thoughtworks.mars_rover;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import main.java.com.thoughtworks.mars_rover.Main;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MainTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUp() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	@Test
	public void testMainWithValidData() {
		String[] args = {"5 5", "1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM"};
		Main.main(args);
		
		String expectedData = "1 3 N\n5 1 E\n";
		assertEquals(expectedData,outContent.toString());
	}
	
	@Test
	public void testMainWithoutAnyData() {
		String[] args = {};
		Main.main(args);
		
		String expectedData = "No data given.\n";
		assertEquals(expectedData,outContent.toString());
	}
	
	@Test
	public void testMainWithInvalidData() {
		thrown.expect(NumberFormatException.class);
		String[] args = {"5 S"};
		Main.main(args);
	}
	
	@Test
	public void testMainWithMissingArgument() {
		thrown.expect(IllegalArgumentException.class);
		String[] args = {"5"};
		Main.main(args);
	}
}
