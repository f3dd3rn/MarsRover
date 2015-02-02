package test.java.com.thoughtworks.mars_rover;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import main.java.com.thoughtworks.mars_rover.Main;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

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
	public void testRun() {
		Main run = new Main();
		String[] args = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM".split("\\s");
		run.run(args);
		assertEquals("1 3 N" + System.getProperty("line.separator") + "5 1 E" + System.getProperty("line.separator"), outContent.toString());
	}

}
