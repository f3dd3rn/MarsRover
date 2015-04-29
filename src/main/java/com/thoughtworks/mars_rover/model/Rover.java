package main.java.com.thoughtworks.mars_rover.model;

import java.util.concurrent.RejectedExecutionException;

import main.java.com.thoughtworks.mars_rover.controller.World;

/**
 * NASA rover, which moves forward after checking if front is clear and turns 90° left or right.
 * @author stephanie
 *
 */
public class Rover {	
	protected CardinalDirection facing;
	protected Coordinate position;
	protected World world;
	
	public Rover(Coordinate coordinate, CardinalDirection facing, World world) {
		position = coordinate;
		this.facing = facing;
		this.world = world;
		registerToWorld();
	}

	public Rover(int x, int y, CardinalDirection facing, World world) {
		position = new Coordinate(x, y);
		this.facing = facing;
		this.world = world;
		registerToWorld();
	}
	

	private void registerToWorld() {
		world.registerNewObjectAtPosition(position);
	}

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
	
	public String toString() {
		return position.toString() + " " + facing.toString();
	}

	public Coordinate move() throws RejectedExecutionException {
		Coordinate nextPosition = nextPosition(); 
		if(world.isVacant(nextPosition)){
			position = nextPosition;
			return position;
		}
		throw new RejectedExecutionException("Rover: " + toString() + " can not move, because next position is not vacant.");
	}

	public CardinalDirection getCardinalDirection() {
		return facing;
	}

	public Coordinate getCoordinate() {
		return position;
	}
	
	public String executeCommandSequence(Command[] commands) {
		for (Command command : commands) {
			executeCommand(command);
		}
		return toString();
	}

	private void executeCommand(Command command) {
		switch(command) {
			case LEFT:
				turn90DegreesLeft();
				break;
			
			case RIGHT:
				turn90DegreesRight();
				break;
			
			case MOVE:
				Coordinate coordinateBeforeMove = position;
				move();
				world.onPositionChange(coordinateBeforeMove, position);
				break;
	
			default:
				throw new UnsupportedOperationException("Undefined action for command: " + command);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((facing == null) ? 0 : facing.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rover other = (Rover) obj;
		if (facing != other.facing)
			return false;
		if (position == null && other.position != null) {
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
	
}
