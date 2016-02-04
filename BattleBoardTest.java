/**
 * Automated white box test program for BattleBoard
 * @author Sarah Heckman
 */
public class BattleBoardTest {
	public static final int ROWS = 8;
        public static final int COLS = 8;
        public static final int NUMBER_OF_SHIPS = 4;
	/** A private copy of the BattleBoard */
	private BattleBoard board;
	
	/**
	 * Initializes the field
	 */
	public BattleBoardTest() {
		board = new BattleBoard(ROWS,COLS,NUMBER_OF_SHIPS);
	}
	
	/**
	 * Tests BattleBoard.addShip() method
	 */
	public void testAddShip() {
		board = new BattleBoard(ROWS,COLS,NUMBER_OF_SHIPS);
		System.out.println("testAddShip");
		
		//Test adding a Ship at a valid location
		boolean expectedOutput = true;
		boolean actualOutput = board.addShip(1, true, 3, 5);
		System.out.printf("Expected: %8s   Actual: %8s\n", expectedOutput, actualOutput);

		//Test adding a Ship at a location where there is already a ship
		expectedOutput = false;
		actualOutput = board.addShip(2, true, 3, 5);
		System.out.printf("Expected: %8s   Actual: %8s\n", expectedOutput, actualOutput);

		//Test adding a Ship that runs off the board
		expectedOutput = false;
		actualOutput = board.addShip(4, true, 8, 8);
		System.out.printf("Expected: %8s   Actual: %8s\n", expectedOutput, actualOutput);

		//Test if Ship is added after max number of ships
		expectedOutput = false;
		actualOutput = board.addShip(4, true, 6, 1);
		actualOutput = board.addShip(3, true, 0, 0);
		actualOutput = board.addShip(2, false, 1, 6);
		System.out.printf("Expected: %8s   Actual: %8s\n", expectedOutput, actualOutput);
	}
	
	/**
	 * Tests BattleBoard.getNumberOfShips() method
	 */
	public void testGetNumberOfShips() {
		board = new BattleBoard(ROWS,COLS,NUMBER_OF_SHIPS);
		System.out.println("testGetNumberOfShips");
		
		//Test that there are no Ships when a BattleBoard is initially created
		int expectedOutput = 0;
		int actualOutput = board.getNumberOfShips();
		System.out.printf("Expected: %8d   Actual: %8d\n", expectedOutput, actualOutput);
		
		//Test the number of Ships after adding a Ship
		board.addShip(1, true, 0, 3);
		expectedOutput = 1;
		actualOutput = board.getNumberOfShips();
		System.out.printf("Expected: %8d   Actual: %8d\n\n", expectedOutput, actualOutput);
		//Add a second ship
		expectedOutput = 2;
		board.addShip(2, true, 7, 0);
		actualOutput = board.getNumberOfShips();
		System.out.printf("Expected: %8d   Actual: %8d\n\n", expectedOutput, actualOutput);

		//Add a third ship
		expectedOutput = 3;
		board.addShip(3, false, 0, 7);
		actualOutput = board.getNumberOfShips();
		System.out.printf("Expected: %8d   Actual: %8d\n\n", expectedOutput, actualOutput);

		//Add a fourth ship
		expectedOutput = 4;
		board.addShip(4, false, 4, 7);
		actualOutput = board.getNumberOfShips();
		System.out.printf("Expected: %8d   Actual: %8d\n\n", expectedOutput, actualOutput);
	}
	
	/**
	 * Tests BattleBoard.getShips() method
	 */
	public void testGetShips() {
		board = new BattleBoard(ROWS,COLS,NUMBER_OF_SHIPS);
		System.out.println("testGetShips");
		
		//Test that all Ships are null when a BattleBoard is first created
		Ship [] ships = board.getShips();
		for (int i = 0; i < ships.length; i++) {
			try {
				ships[i].toString();
				System.out.printf("Expected: NullPointerException   Actual: Ship at index %d is not null\n", i);
			} catch (NullPointerException e) {
				System.out.printf("Expected: NullPointerException   Actual: NullPointerException\n");
			}
		}
		System.out.println();
		//Test that Ships are not null when Ships are added to the BattleBoard
		//ships[4] = new Ship(4, true, 3, 4);
		board.addShip(3, true, 3, 5);
		for (int i = 0; i < ships.length; i++) {
			try {
				ships[i].toString();
				System.out.printf("Expected: Ship at index 0 is not null  Actual: Ship at index %d is not null\n", i);
			} catch (NullPointerException e) {
				System.out.printf("Expected: NullPointerException   Actual: NullPointerException\n");
			}
		}
	}
	
	/**
	 * Tests BattleBoard.fireAtLocation() method
	 */
	public void testFireAtLocation() {
		board = new BattleBoard(ROWS,COLS,NUMBER_OF_SHIPS);
		System.out.println("testFireAtLocation");
		
		//Test firing at a location that has never been fired at before
		boolean expectedOutput = true;
		boolean actualOutput = board.fireAtLocation(0, 0);
		System.out.println("" + board.toString()); //print out board
		System.out.printf("Expected: %8s   Actual: %8s\n", expectedOutput, actualOutput);
		
		//Test firing at a location that has been fired.
		expectedOutput = false;
		actualOutput = board.fireAtLocation(0,0);
		System.out.println("" + board.toString()); //print out board
		System.out.printf("Expected: %8s   Actual: %8s\n", expectedOutput, actualOutput);
		
	}
	
	/**
	 * Tests BattleBoard.hasBeenHit() method
	 */
	public void testHasBeenHit() {
		board = new BattleBoard(ROWS,COLS,NUMBER_OF_SHIPS);
		System.out.println("TestHasBeenHit");
		
		//Test to ensure a location that has never been fired at really hasn't
		//been fired at
		boolean expectedOutput = false;
		boolean actualOutput = board.hasBeenHit(0, 0);
		System.out.println("" + board.toString());
		System.out.printf("Expected: %8s   Actual: %8s\n", expectedOutput, actualOutput);

		//Test a location that has been fired at.
		expectedOutput = true;
		board.fireAtLocation(0,0);
		System.out.println("" + board.toString());
		actualOutput = board.hasBeenHit(0, 0);
		System.out.printf("Expected: %8s   Actual: %8s\n", expectedOutput, actualOutput);
		
	}
	
	/**
	 * Tests BattleBoard.areAllShipsSunk() method
	 */
	public void testAreAllShipsSunk() {
		board = new BattleBoard(ROWS,COLS,NUMBER_OF_SHIPS);
		System.out.println("testAreAllShipsSunk");
		
		//Test that the provided ship isn't sunk after being hit once
		board.addShip(2, true, 0, 0);
		board.addShip(1, true, 3, 5);
		board.fireAtLocation(0, 0);
		board.fireAtLocation(3, 5);
		
		System.out.println("" + board.toString());
		boolean expectedOutput = false;
		boolean actualOutput = board.areAllShipsSunk();
		System.out.printf("Expected: %8s   Actual: %8s\n\n", expectedOutput, actualOutput);

		//Tests that a ship of length 2 is sunk after being hit twice.
		board.fireAtLocation(0, 1);
		System.out.println("" + board.toString());
		expectedOutput = true;
		actualOutput = board.areAllShipsSunk();
		System.out.printf("Expected: %8s   Actual: %8s\n\n", expectedOutput, actualOutput);

		
	}
	
	/**
	 * Starts the BattleBoard automated white box test cases.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		BattleBoardTest test = new BattleBoardTest();
		test.testAddShip();
		test.testGetNumberOfShips();
		test.testGetShips();
		test.testFireAtLocation();
		test.testHasBeenHit();
		test.testAreAllShipsSunk();
	}

}
