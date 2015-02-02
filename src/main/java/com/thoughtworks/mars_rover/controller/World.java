package main.java.com.thoughtworks.mars_rover.controller;

import java.util.concurrent.RejectedExecutionException;

import main.java.com.thoughtworks.mars_rover.model.CardinalDirection;
import main.java.com.thoughtworks.mars_rover.model.Command;
import main.java.com.thoughtworks.mars_rover.model.Coordinate;
import main.java.com.thoughtworks.mars_rover.model.Movable;
import main.java.com.thoughtworks.mars_rover.model.Rover;

public class World implements Checkable {
	private Movable[][] movableObjectsOnWorld;
	
	private int width;
	private int height;
	
	public World(int outerX, int outerY) {
		width = outerX + 1;
		height = outerY + 1;
		movableObjectsOnWorld = new Movable[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				movableObjectsOnWorld[i][j] = null;
			}
		}
	}
	
	private Movable putRoverIntoWorld(Coordinate coordinate, CardinalDirection facing) {
		Movable movableObject;
		if(isPositionClear(coordinate)) {
			movableObject = new Rover(coordinate, facing, this); // the only available movable object right now
			movableObjectsOnWorld[coordinate.x][coordinate.y] = movableObject;
			return movableObject;
		} else if (movableObjectsOnWorld[coordinate.x][coordinate.y].getCardinalDirection() == facing ) {
			return movableObjectsOnWorld[coordinate.x][coordinate.y];
		}
		throw new RejectedExecutionException("Another movable object exists at this position: " + movableObjectsOnWorld[coordinate.x][coordinate.y].toString());
	}
	
	public String executeCommandSequence(int x, int y, CardinalDirection facing, char[] commands) {
		Coordinate coordinate = new Coordinate(x,y);
		Movable rover;
		rover = putRoverIntoWorld(coordinate, facing);
		for (char command : commands) {
			executeCommand(rover, Command.getByAbbreviation(String.valueOf(command)));
		}
		return rover.toString();
	}
	
	private void executeCommand(Movable movableObject, Command command) {
		switch(command){
			case LEFT:
				movableObject.turn90DegreesLeft();
				break;
				
			case RIGHT:
				movableObject.turn90DegreesRight();
				break;
				
			case MOVE:
				Coordinate coordinateBeforeMove = movableObject.getCoordinate();
				Coordinate coordinate = movableObject.move();
				if(coordinateBeforeMove != coordinate){
					movableObjectsOnWorld[coordinate.x][coordinate.y] = movableObject;
					movableObjectsOnWorld[coordinateBeforeMove.x][coordinateBeforeMove.y] = null;
				}
				break;
		
			default:
				throw new UnsupportedOperationException("Don't know what to do on the command: " + command);
		}
	}

	@Override
	public boolean isPositionClear(Coordinate coordinate) {
		if(null == movableObjectsOnWorld[coordinate.x][coordinate.y]) {
			return true;
		} else {
			return false;
		}
	}
}
