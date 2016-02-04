/** Square class represents a location in a BattleBoard's grid. */
public class Square {

	/** whether the ship has been hit by enemy fire */
	private boolean hasHit;

	/** the ship located in its space (if there is a ship) */
	private Ship shipExist;

	//constructor for this class (optional)
	public Square() {
		hasHit = false;
		shipExist = null;
	}

	public static void main(String [] args) {
		Square s = new Square();
		Ship b = new Ship(1, true, 0,0);
		s.addShip(b);
		s.fireAt();
		System.out.println("" + b);
		System.out.println("" + s);

	}

	//returns true if the Square has been hit by enemy fire, false otherwise.
	public boolean hasBeenHit() {
		return hasHit;
	}

	//returns a ship instance if there is a ship that includes the square, or
	//null if there is no such ship.
	public Ship getShip() {
		if (this.hasShip()) {
			return shipExist;	
		}
		return null;
	}
	
	//the Square should update the fact that it has been hit,
	//if a ship is occupying the square, it should also call the ship's 
	// hit() method to let it know it has been hit.
	public void fireAt() {
		hasHit = true;
		if (shipExist != null) {
			shipExist.hit();
		}
	}

	//returns true if the Square contains a Ship.
	public boolean hasShip() {
		if (shipExist != null) {
			return true;
		}
		return false;
	}

	//add the given ship to the Square
	public void addShip(Ship ship) {
		shipExist = ship;
	}


	//returns a single character string indicating the state of the square.
	//"-" if square does not contain ship and has not been hit
	//"W" if the square has been hit and does not contain a ship (W for White)
	//"R" if the square has been hit and contains a ship (R for Red)
	//"1","2","3, or "4" if the square has not been hit and
	//contains a ship (number return should correspond to length of ship
	public String toString() {
		String status = null;
		if (!hasShip() && !hasHit) {
			status = "-";
		} else if (!hasShip() && hasHit) {
			status = "W";
		} else if (hasShip() && hasHit) {
			status = "R";
		} else if (hasShip() && !hasHit) {
			status = "" + shipExist.getLength();
		}
	
		return status;	
	}
}

