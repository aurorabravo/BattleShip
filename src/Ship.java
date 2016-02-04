/**
 * The Ship class represents a single ship in a fleet of ships.
 */
public class Ship {

	/** Ship's length */
	private int length;

	/** number of times ship has been hit */
	private int timesHit;

	/** its orientation (horizontal or vertical) */
	private boolean isHorizontal;

	/** the row containing the upper left corner of the ship */
	private int startRow;

	/** the column containing the upper left corner of the ship */
	private int startCol;
	

	//main method for testing ship class
	public static void main(String [] args) {
		Ship b = new Ship(4, true, 2, 2);
		System.out.println(""+ b);
		System.out.println(""+ b.getLength());
		System.out.println(""+ b.isHorizontal());
		System.out.println(""+ b.getStartRow());
		System.out.println(""+ b.getStartCol()); 
		b.hit();
		b.hit();
		b.hit();
		b.hit();
		System.out.println("" + b);
		System.out.println(""+ b.isSunk());
		System.out.println("\nPrints toString method:");
		System.out.println(""+ b.toString()); 

		
	}

	//must initialize the class's instance variables using the param values.
	//throw an illegalargumentexception if lenght < 1, startRow < 0, or startCol <0
	public Ship(int length, boolean isHorizontal, int startRow, int startCol) {
		if (length < 1 || startRow < 0 || startCol < 0) {
			throw new IllegalArgumentException();
		}

		//how to initialize instance variables using param values?
		this.length = length;
		this.isHorizontal = isHorizontal;
		this.startRow = startRow;
		this.startCol = startCol;
		timesHit = 0;
	}

	//returns the length of the ship
	public int getLength() {
		return length;
	}

	//returns true if the ship has horizontal orientation, false otherwise.
	public boolean isHorizontal() {
		return isHorizontal;
	}

	//returns the row of the upper left corner of the Ship
	public int getStartRow() {
		return startRow;
	}

	//returns the column of the upper left corner of the ship.
	public int getStartCol() {
		return startCol;
	}

	//simulates hitting the ship(updates appropiate instance variable) (timesHit)
	public void hit() {
		//how to test if ship is hit?
		timesHit += 1;
	}

	//returns true if the ship is sunk (has been hit as many times as its length)
	public boolean isSunk() {
		if (timesHit == length) {
			return true;
		}
		return false;
	}

	//returns a string representation of a ship that lists its length, location, orientation, number
	// of times hit, and if the ship is sunk. (this allows to p rint all the info about ship when testing the class)
	public String toString() {
		return "Length: " + length + 
			"\nlocation: " + "(" + startRow + "," + startCol + ")" +
			"\norientation (true if horizontal, false otherwise): " + isHorizontal + 
			"\nNumber of Times hit: " + timesHit + "\n" +
			"Is ship sunk? " + isSunk() + "\n";
	}

}
