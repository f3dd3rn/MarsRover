package main.java.com.thoughtworks.mars_rover.model;

import java.util.concurrent.RejectedExecutionException;

public interface Movable {
	public Coordinate move() throws RejectedExecutionException;
	public CardinalDirection getCardinalDirection();
	public void turn90DegreesRight();
	public void turn90DegreesLeft();
	public Coordinate getCoordinate();
}
