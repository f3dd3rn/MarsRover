package main.java.com.thoughtworks.mars_rover.model;

import java.util.concurrent.RejectedExecutionException;

import main.java.com.thoughtworks.mars_rover.controller.Checkable;


public class Rover implements Movable {	
	protected CardinalDirection facing;
	protected Coordinate position;
	protected Checkable world;
	
	public Rover(Coordinate coordinate, CardinalDirection facing, Checkable world) {
		position = coordinate;
		this.facing = facing;
		this.world = world;
	}
	
	public Rover(int x, int y, CardinalDirection facing) {
		position = new Coordinate(x, y);
		this.facing = facing;
	}

	@Override
	public void turn90DegreesLeft() {
		switch(facing) {
		case NORTH:
			facing = CardinalDirection.WEST;
			break;
		case EAST:
			facing = CardinalDirection.NORTH;
			break;
		case SOUTH:
			facing = CardinalDirection.EAST;
			break;
		case WEST:
			facing = CardinalDirection.SOUTH;
			break;
		default:
			throw new UnsupportedOperationException("Unable to turn 90° left - unknown operation for cardinal direction: " + facing);
		}
	}
	
	@Override
	public void turn90DegreesRight() {
		switch(facing) {
			case NORTH:
				facing = CardinalDirection.EAST;
				break;
			case EAST:
				facing = CardinalDirection.SOUTH;
				break;
			case SOUTH:
				facing = CardinalDirection.WEST;
				break;
			case WEST:
				facing = CardinalDirection.NORTH;
				break;
			default:
				throw new UnsupportedOperationException("Unable to turn 90° right - unknown operation for cardinal direction: " + facing);
		}
	}
	
	protected Coordinate nextPosition() {
		switch(facing) {
			case NORTH:
				return new Coordinate(position.x, position.y+1);
			case EAST:
				return new Coordinate(position.x+1, position.y);
			case SOUTH:
				return new Coordinate(position.x, position.y-1);
			case WEST:
				return new Coordinate(position.x-1, position.y);
			default:
				throw new UnsupportedOperationException("Unable to move - unknown operation for cardinal direction: " + facing);
		}
	}
	
	@Override
	public String toString() {
		return position.toString() + " " + facing.toString();
	}

	@Override
	public Coordinate move() throws RejectedExecutionException {
		Coordinate nextPosition = nextPosition(); 
		if(world.isPositionClear(nextPosition)){
			position = nextPosition;
			return position;
		}
		throw new RejectedExecutionException("Rover: " + toString() + " can not move, because next position is not vacant.");
	}

	@Override
	public CardinalDirection getCardinalDirection() {
		return facing;
	}

	@Override
	public Coordinate getCoordinate() {
		return position;
	}
	
}
