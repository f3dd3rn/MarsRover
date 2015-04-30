package main.java.com.thoughtworks.mars_rover.controller;

import java.util.concurrent.RejectedExecutionException;

import main.java.com.thoughtworks.mars_rover.model.Coordinate;

/**
 * World keeps track of each position's state in its coordinate system
 * and gives information about a position's status.
 * 
 * @author stephanie
 *
 */
public class World {
	private boolean[][] vacancyCoordinateSystem;
	
	private int width;
	private int height;
	
	public World(int outerX, int outerY) {
		width = outerX + 1;
		height = outerY + 1;
		vacancyCoordinateSystem = new boolean[width][height];
		initialize();
	}
	
	private void initialize() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				setVacant(x, y);
			}
		}
	}
	
	public boolean isVacant(Coordinate coordinate) {
		try {
			return vacancyCoordinateSystem[coordinate.x][coordinate.y];
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public void registerNewObjectAtPosition(Coordinate coordinate) {
		if(isVacant(coordinate)) {
			setOccupied(coordinate.x, coordinate.y);
		} else {
			throw new RejectedExecutionException("Position: " + coordinate.toString() + " already occpied.");
		}
	}
	
	public void onPositionChange(Coordinate previousCoordinate, Coordinate currentCoordinate) {
		occupyPosition(currentCoordinate);
		clearPosition(previousCoordinate);
	}
	
	private void clearPosition(Coordinate coordinate) {
		setVacant(coordinate.x, coordinate.y);
	}

	private void occupyPosition(Coordinate coordinate) {
		setOccupied(coordinate.x, coordinate.y);
	}
	
	private void setVacant(int x, int y) {
		vacancyCoordinateSystem[x][y] = true;
	}
	
	private void setOccupied(int x, int y) {
		vacancyCoordinateSystem[x][y] = false;
	}
}
