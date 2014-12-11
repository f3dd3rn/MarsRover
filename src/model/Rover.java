package model;

public class Rover {	
	protected CardinalDirection facing;
	protected Coordinate position;
	
	public Rover(int x, int y, CardinalDirection facing) {
		position = new Coordinate(x, y);
		this.facing = facing;
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
		case EAST:
			facing = CardinalDirection.SOUTH;
		case SOUTH:
			facing = CardinalDirection.WEST;
		case WEST:
			facing = CardinalDirection.NORTH;
		default:
			throw new UnsupportedOperationException("Unable to turn 90° right - unknown operation for cardinal direction: " + facing);
		}
	}
	
	public void moveForward() {
		Coordinate nextPosition = nextPosition(); 
		//if(false != true/*TODO: !field.isPlaceClear(nextPosition)*/){
			//throw new UnsupportedOperationException("Rover cannot move forward, front is not clear!");
		//} else {
			//TODO: field.occupyPlace(nextPosition);
			//TODO: field.clearPlace(position);
			position = nextPosition;
		//}
	}
	
	protected Coordinate nextPosition() {
		switch(facing) {
		case NORTH:
			return new Coordinate(position.x, position.y+1);
		case EAST:
			return new Coordinate(position.x, position.y);
		case SOUTH:
			return new Coordinate(position.x, position.y-1);
		case WEST:
			return new Coordinate(position.x, position.y);
		default:
			throw new UnsupportedOperationException("Unable to move - unknown operation for cardinal direction: " + facing);
		}
	}
	
	public String positionAndDirectionToString() {
		return position.toString() + " " + facing.toString();
	}
	
	
}
