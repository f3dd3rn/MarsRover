package main.java.com.thoughtworks.mars_rover.controller;

import main.java.com.thoughtworks.mars_rover.model.Coordinate;

public interface Checkable { // Could not come up with better naming... Any ideas?
	public boolean isPositionClear(Coordinate coordinate);
}
