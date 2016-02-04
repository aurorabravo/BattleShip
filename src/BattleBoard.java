public class BattleBoard {
	
	/** the number of rows in the 2 dimensional array of Squares */
	private int numberOfRows;
	/** the number of columns in the 2 dimensional array of Squares */
	private int numberOfColumns;
	/** a 2 dimensional array of Squares */
	private Square [][] squareArray;
	/** The maximum number of Ships */
	private int maximumNumberOfShips;
	/** an array of Ships */
	private Ship [] shipArray;
	/** the number of Ships on the board */
	private int numShips;
	
	
	public static void main(String [] args) {
		BattleBoard b = new BattleBoard(8,8,4);
		System.out.println("" +  b);

	}


	/** This is the constructor of the BattleBoard class. Fields listed above should be
	    initialized. Throw an IllegalArgumentException if numberOfRows <1, 
		numberOfCoumns < 1,or maximumNumberOfShips < 0 . */
	public BattleBoard(int numberOfRows, int numberOfColumns, int maximumNumberOfShips) {
		if ( numberOfRows < 1 || numberOfColumns < 1 || maximumNumberOfShips < 0 ) {
			throw new IllegalArgumentException();
		}
		
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = numberOfColumns;
		this.maximumNumberOfShips = maximumNumberOfShips;
		this.numShips = 0;
		
		squareArray = new Square[numberOfRows][numberOfColumns];
		for (int i = 0; i < squareArray.length; i++) {
			for (int j = 0; j < squareArray[i].length; j++) {
				squareArray[i][j] = new Square();
			}
		}
		shipArray = new Ship[maximumNumberOfShips];
	}

	//adds a ship to the board, meaning all Squares the length of the ship
	// starting at the startrow and startCol must be updated appropiately.
	// the isHorizontal determines which direction to fill Squares.
	// the method returns treu if the ship is succesfully added to the
	// BattleBoard and false if the Ship could not be added due to 
	// another Ship in the way, reaching a BattleBoard boundary,
	// or if the array of Ships is full.
	// *** If the Ship cannot be placed, then the Squares should not change.
	public boolean addShip(int length, boolean isHorizontal, int startRow, int startCol) {
		Ship ship = new Ship(length, isHorizontal, startRow, startCol);
		shipArray[numShips] = new Ship(length, isHorizontal, startRow, startCol);
		//tests if out of bounds
		if (startRow >= numberOfRows || startCol >= numberOfColumns) {
			return false;
		}

		//tests if ship ship already there
		if (squareArray[startRow][startCol].hasShip()) {
			return false;
		}
		//vertical
		if (!isHorizontal && (startRow + length) > numberOfRows) {
			return false;
		} 
		//horizontal
		if (isHorizontal && (startCol + length) > numberOfColumns) {
			return false;
		}
		//max number of ships
		if ((shipArray.length - 1) >= maximumNumberOfShips)  {
			return false;
		}

		for (int i = 0; i < length; i++) { //tests if there is a ship in the way
			if (!isHorizontal && squareArray[startRow + i][startCol].hasShip()) {;
				return false;
			} else if (isHorizontal && squareArray[startRow][startCol + i].hasShip()) {
				return false;
			}
		}
		
		if(isHorizontal) { 
			for (int i = 0; i < ship.getLength(); i++) {
				squareArray[startRow][startCol + i].addShip(ship);
			}
		} else {
			for (int i = 0; i < ship.getLength(); i++) {
				squareArray[startRow + i][startCol].addShip(ship);
			}
		}

		numShips++;
		return true;	
	}

	//returns the number of Ships deployed on the BattleBoard.
	public int getNumberOfShips() {
		return numShips;
		
	}

	//returns an array of the Ships deployed on the BattleBoard
	public Ship[] getShips(){
		return shipArray;
	}

	//If the Square located at the specified row and column has not been
	// previously hit, the Square should be fired at and true should be returned.
	// If the Square has already been hit, it should not be hit again and false should be returned
	// Throw an IllegalArgumentException if row < 0, col < 0, row >= number of rows, or col >= number of columns
	public boolean fireAtLocation(int row, int col){
		if (row < 0 || col < 0 || row >= numberOfRows || col >= numberOfColumns) {
			throw new IllegalArgumentException();
		}
		if (squareArray[row][col].hasBeenHit() == false){
			squareArray[row][col].fireAt();
			return true;
		
		}
		return false;
	}

	//returns true if the enemy has already fired on the Square located at the speified
	// row and column. Throw an IllegalArgumentException if row < 0, col < 0, row >= number of rows, 
	// or col >= number of columns.
	public boolean hasBeenHit(int row, int col){
		if (row < 0 || col < 0 || row >= numberOfRows || col >= numberOfColumns) {
			throw new IllegalArgumentException();
		}

		if (squareArray[row][col].hasBeenHit()) {
			return true;
		}


		return false;
	
	}

	//returns true if all of the Ships on the BattleBoard have been sunk
	// by enemy fire.
	public boolean areAllShipsSunk(){
		int sunkenShips = 0;

		for (int i = 0; i < numShips; i++) {
			System.out.println("ship at i " + shipArray[i]);
			if (shipArray[i].isSunk()) {
				sunkenShips++;
			}
		}

		System.out.println("sunkenships " + sunkenShips);
		System.out.println("numships " + this.numShips);
		if (sunkenShips == numShips) {
			return true;
		}

		return false;
	}

	//returns the number of rows in the BattleBoard.
	public int getNumberOfRows(){
		return numberOfRows;
	}

	//returns the number of columns in the BattleBoard
	public int getNumberOfColumns(){
		return numberOfColumns;
	}

	//returns a String representation of a BattleBoard. Hint: using the
	// toString() method of the Square class, you can easily create a String
	// representation of the BattleBoard similar to the one on the instructions.
	public String toString(){
		String board = "";
		for(int i=0; i < squareArray.length; i++){
			for(int j = 0; j < squareArray[i].length; j++){
				if (squareArray[i][j] != null) {
					board +="" + squareArray[i][j].toString();
				}
			}
			board += "\n";
		}
		//System.out.println();
		return board;
	}
}
