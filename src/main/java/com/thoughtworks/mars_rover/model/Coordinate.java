package main.java.com.thoughtworks.mars_rover.model;

public class Coordinate {
	public int x; //x Coordinate
	public int y; //y Coordinate
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return x + " " + y;
	}
	
	
}
