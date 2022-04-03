package AfridaIslam_Project3;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AfridaIslam_Project3 {
	public static void main(String[] args) {

		// Afrida Islam
		// ITSS 3312.001
		// Project 3 Exceptions

		// Declare Vars

		// Instantiate system objects
		Hands hands = new Hands();
		Session session = new Session();
		Poker_EH poker_eh = new Poker_EH();
		// Declare Scanner object
		Scanner input = new Scanner(System.in);
		int seconds = 2;
		// Print game requirements and rules
		System.out.println("4-Dice_Draw_Poker is a poker game that uses dice instead of cards.");
		System.out.println("The computer will roll 4 dice and then identify the best hand.");
		System.out.println("The player can draw (re-roll) up to 3 dice to improve the hand.");
		System.out.println("Possible hands include 4-of-a-Kind, 3-of-a-Kind, 2-Pair, Pair, or a Straight.");
		System.out.println();

		// Prompt for and read-in name
		System.out.print("Enter your name: ");
		session.setName(input.next());
		System.out.println();

		// Print welcome statement
		System.out.println("Hi " + session.getName() + ". Welcome to 4-Dice Draw Poker!");
		System.out.println();

		System.out.print("How many games would you like to play? ");
		session.setGames(poker_eh.getEntry("prompt1", " The number of games needs to be greater than or equal to 1 "));

		System.out.println("Press any letter-key and then enter to start the game and roll the dice. ");
		poker_eh.pauseGames(seconds);
		poker_eh.getEntry("prompt4", "");
		System.out.println();

		// Start while loop for number of games
		int counter = 0;
		while (counter < session.getGames()) {

			// Set all boolean var hands to false
			hands.resetHands();

			// Set dice values and sort the dice using Arrays.sort()
			hands.setDice();
			// hands.setDice(1, 2, 5, 6); // Use this for testing specific dice rolls
			hands.sortDice();
			System.out.println();

			// Evaluate initial roll and set hand boolean values
			hands.is4Kind();
			hands.is3Kind();
			hands.is2Pair();
			hands.isPair();
			hands.isStrait();

			// Remove duplicate true values for hands
			// Also this method sets the value for hands.hand var
			hands.deDupe();
			poker_eh.pauseGames(seconds);
			// Print results
			System.out.println();
			System.out.println("   ****** Game " + (counter + 1) + " INITIAL HAND ******");
			System.out.println(" Roll   4Kind 3Kind 2Pair Pair Straight");
			System.out.println("------  ----- ----- ----- ---- --------");

			hands.prtDice();
			hands.prtHands();
			System.out.println();

			// Get the number of dice to reroll (cards to draw)
			System.out.print("Enter the number of dice to draw (re-roll) - up to 3: ");
			int y = poker_eh.getEntry("prompt2", "");

			hands.setDrawNum(y);

			// Get the indexes of the dice to reroll (draw)
			poker_eh.pauseGames(seconds);
			if (hands.getDrawNum() > 0) {
				System.out.print(
						"Enter the index numbers (0 to 3) separated by a space " + "of the dice you wish to draw: ");

				// Store the indexes entered in an array
				int[] tempIndex = new int[hands.getDrawNum()];
				for (int i = 0; i < hands.getDrawNum(); i++) {
					tempIndex[i] = poker_eh.getEntry("prompt3", "");
				}
				// poker_eh.pauseGames(seconds);
				// Pass tempIndex array to hands.setNewDice method
				hands.setDrawIndexes(tempIndex);
			}
			System.out.println();

			// Re-roll / set new dice values
			hands.setNewDice();

			// Sort the new array of dice
			hands.sortDice();

			// Reset all boolean vars for hands to false
			hands.resetHands();

			// Evaluate dice values for hands after draw
			hands.is4Kind();
			hands.is3Kind();
			hands.is2Pair();
			hands.isPair();
			hands.isStrait();

			// Remove duplicate true values for hands
			hands.deDupe();
			poker_eh.pauseGames(seconds);
			// Print results after draw
			System.out.println();
			System.out.println("  ******* Game " + (counter + 1) + " FINAL HAND ********");
			System.out.println(" Roll   4Kind 3Kind 2Pair Pair Straight");
			System.out.println(" ----   ----- ----- ----- ---- --------");

			hands.prtDice();
			hands.prtHands();
			System.out.println();

			counter++; // Increments counter for while loop

			// Create record for the game
			session.setRecord(counter, hands.getDice(), hands.getHand());

		} // End of while loop for games to play

		session.prtRecords(); // Prints the game records
		System.out.println();

		// Print the close statement
		System.out.println("Thank you for playing, " + session.getName() + ". Play again soon!");
		// invoking poker eh
		// poker_eh.getEntry("prompt1", "");

		// Close the Scanner
		input.close();

	} // End of main method, class methods are below
		// ***********************************
}

class Hands {

	// Variables
	private int[] dice = new int[4];

	private boolean fourKind = false;
	private boolean threeKind = false;
	private boolean strait = false;
	private boolean twoPair = false;
	private boolean pair = false;
	private String hand;

	private int drawNum = 0;
	private int[] drawIndex = new int[3];

	// Constructors

	// Methods are below

	// Set dice values from Random values
	public void setDice() {
		Random random = new Random();
		for (int i = 0; i < dice.length; i++) {
			dice[i] = random.nextInt(6) + 1;
		}
	}

	// Set dice values from arg values
	public void setDice(int a, int b, int c, int d) {
		int[] tempDice = { a, b, c, d };
		dice = tempDice;
	}

	// Print dice values
	public void prtDice() {
		for (int z : dice)
			System.out.print(z + " ");
	}

	// Set individual dice value (use this for the draw)
	public int setDie() {
		Random random = new Random();
		return random.nextInt(6) + 1;
	}

	// Sort the dice array
	public void sortDice() {
		Arrays.sort(dice);
	}

	// Get the dice array
	public int[] getDice() {
		return dice;
	}

	// Evaluate for 4-of-a-kind
	public void is4Kind() {
		int count4 = 0;

		for (int i = 1; i < dice.length; i++) {
			if (dice[0] == dice[i]) {
				count4++;
			}
		}
		if (count4 == 3) {
			fourKind = true;
		}
	}

	// Get fourKind boolean value
	public boolean get4Kind() {
		return fourKind;
	}

	// Evaluate for 3-of-a-kind
	public void is3Kind() {
		int count3 = 0;

		// Check for 3Kind starting with index 0
		for (int i = 1; i < dice.length; i++) {
			if (dice[0] == dice[i]) {
				count3++;
			}
		}
		if (count3 == 2) {
			threeKind = true;
		} else {
			count3 = 0;
		}

		// Check for 3Kind starting with index 1
		for (int i = 2; i < dice.length; i++) {
			if (dice[1] == dice[i])
				count3++;
		}
		if (count3 == 2)
			threeKind = true;
	}

	// Get threeKind boolean value
	public boolean get3Kind() {
		return threeKind;
	}

	// Evaluate for a pair
	public void isPair() {
		for (int i = 0; i < dice.length - 1; i++) {
			if (dice[i] == dice[i + 1]) {
				pair = true;
				break;
			}
		}
	}

	// Get pair boolean value
	public boolean getPair() {
		return pair;
	}

	// Evaluate for 2 pair
	public void is2Pair() {
		boolean pair1 = false;
		int pair1Index = 0;

		// Determine if there is 1 pair in the dice
		for (int i = 0; i < dice.length - 1; i++) {
			if (dice[i] == dice[i + 1]) {
				pair1 = true;
				pair1Index = i + 1;
				break;
			}
		}

		// If there is 1 pair, check for a second pair
		if (pair1) {
			for (int i = pair1Index; i < dice.length - 1; i++) {
				if (dice[i] == dice[i + 1]) {
					twoPair = true;
				}
			}
		}
	}

	// Get twoPair boolean value
	public boolean get2Pair() {
		return twoPair;
	}

	// Evaluate for a straight
	public void isStrait() {

		for (int i = 0; i < dice.length - 1; i++) {
			if (dice[i] == dice[i + 1] - 1)
				strait = true;
			else {
				strait = false;
			}
			if (!strait)
				break;
		}
	}

	// Get strait boolean value
	public boolean getStrait() {
		return strait;
	}

	// Remove duplicate true values for hands
	public void deDupe() {
		if (fourKind) {
			threeKind = false;
			twoPair = false;
			pair = false;
			hand = "4-of-a-Kind";
		}

		if (threeKind) {
			twoPair = false;
			pair = false;
			hand = "3-of-a-Kind";
		}

		if (twoPair) {
			pair = false;
			hand = "2 Pair";
		}

		if (pair)
			hand = "Pair";
		if (strait)
			hand = "Straight";
	}

	// Get the final hand
	public String getHand() {
		return hand;
	}

	// Print the boolean hand values
	public void prtHands() {
		System.out.printf("%b %b %b %b %b \n", fourKind, threeKind, twoPair, pair, strait);
	}

	// Set the number of dice to re-roll
	public void setDrawNum(int n) {
		drawNum = n;
	}

	// Get the number of dice to re-roll
	public int getDrawNum() {
		return drawNum;
	}

	// Set the indexes to be re-rolled
	public void setDrawIndexes(int[] index) {
		for (int i = 0; i < index.length; i++) {
			drawIndex[i] = index[i];
		}
	}

	// Set the new dice values
	public void setNewDice() {
		for (int i = 0; i < drawNum; i++) {
			dice[drawIndex[i]] = setDie();
		}
	}

	// Reset all of the hand boolean values to false
	public void resetHands() {
		fourKind = false;
		threeKind = false;
		twoPair = false;
		pair = false;
		strait = false;
	}

} // End of Hands class

class Session {

	// Variables
	private String name;
	private int games;
	private String record;
	private String[] recArray;

	// Constructors

	// Methods
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setGames(int n) {
		games = n;
		recArray = new String[games];
	}

	public int getGames() {
		return games;
	}

	public void setRecord(int counter, int[] dice, String hand) {
		record = counter + ":  " + dice[0] + " " + dice[1] + " " + dice[2] + " " + dice[3] + " " + hand;
		recArray[counter - 1] = record;
	}

	// Print the game records
	public void prtRecords() {
		System.out.println("Games Played This Session");
		for (String z : recArray)
			System.out.println(z);
	}
}

//The number of games needs to be equal to  or greater than 1  but not greater than 99 prompt1
//the reroll number  0 -3 prompt2
// the number of index prompt3 it is essentially the same as prompt 2 except for the error message
// Prompt 4 makes sure that only characters are input for the letter input 
// Poker EH class is placed here

class Poker_EH {

	public int getEntry(String prompt, String errMsg) {
		Scanner scanner = new Scanner(System.in);
		int value = 0;
		boolean tryAgain = true;
		do {

			if (prompt.equals("prompt1"))

			{

				String line = scanner.nextLine();
				String[] lineSplit = line.split(" ");

				/*
				 * for (int i = 0; i < line.length(); i++) { if
				 * (Character.isDigit(line.charAt(i)) == false)
				 * 
				 * { System.out.println(" cannot be a character"); tryAgain = true; continue;
				 * 
				 * }
				 */

				try {

					value = Integer.parseInt(lineSplit[0]); // could throw an exception
					tryAgain = false;

					if (value < 1 || value > 99) {
						// System.out.println(" The number of games needs to be greater than or equal to
						// 1 ");
						tryAgain = true;
						throw new IllegalArgumentException();
					}

				} catch (InputMismatchException ime) {
					System.out.println(ime);
					System.out.println(errMsg);
				} catch (NumberFormatException nfe) {
					System.out.println(nfe);
					System.out.println(errMsg);
				}

				catch (IllegalArgumentException iae) {
					System.out.println(iae);
					System.out.println(errMsg);
				}

			} else if (prompt.equals("prompt2")) {
				System.out.println("The reroll number needs to be between 0 and 3");
				String line = scanner.nextLine();
				String[] lineSplit = line.split(" ");

				try {
					value = Integer.parseInt(lineSplit[0]); // could throw an exception
					tryAgain = false;

					if (value < 0 || value > 3) {
						tryAgain = true;
						throw new IllegalArgumentException();

					}
				}
				/*
				 * for (int i = 0; i < line.length(); i++) { if
				 * (Character.isDigit(line.charAt(i)) == false) {
				 * System.out.println(" cannot be a character"); }
				 * 
				 * if (value < 0 || value > 3) { tryAgain = true; throw new
				 * IllegalArgumentException(); } } }
				 */
				catch (InputMismatchException ime) {
					System.out.println(ime);
					System.out.println(errMsg);
				} catch (NumberFormatException nfe) {
					System.out.println(nfe);
					System.out.println(errMsg);
				}

				catch (IllegalArgumentException iae) {
					System.out.println(iae);
					System.out.println(errMsg);
				}

			} else if (prompt.equals("prompt3")) {
				System.out.println("The number of index needs to be between 0 and 3");
				String line = scanner.nextLine();
				String[] lineSplit = line.split(" ");

				try {
					value = Integer.parseInt(lineSplit[0]); // could throw an exception
					tryAgain = false;

					if (value < 0 || value > 3) {
						tryAgain = true;
						throw new IllegalArgumentException();

					}
				}
				/*
				 * for (int i = 0; i < line.length(); i++) { if
				 * (Character.isDigit(line.charAt(i)) == false) {
				 * System.out.println(" cannot be a character"); }
				 * 
				 * if (value < 0 || value > 3) { tryAgain = true; throw new
				 * IllegalArgumentException(); } } }
				 */
				catch (InputMismatchException ime) {
					System.out.println(ime);
					System.out.println(errMsg);
				} catch (NumberFormatException nfe) {
					System.out.println(nfe);
					System.out.println(errMsg);
				}

				catch (IllegalArgumentException iae) {
					System.out.println(iae);
					System.out.println(errMsg);
				}
			} else if (prompt.equals("prompt4")) {
				System.out.println("Only characters");
				String line = scanner.nextLine();
				String[] lineSplit = line.split(" ");
				tryAgain = false;

				try {
					if (line.length() != 1) {
						// throw new IllegalArgumentException();
					}
					if (Character.isLetter(line.charAt(0)) == false) {
						tryAgain = true;
						throw new IllegalArgumentException();

					}
				} catch (InputMismatchException ime) {
					System.out.println(ime);
					System.out.println(errMsg);
				} catch (NumberFormatException nfe) {
					System.out.println(nfe);
					System.out.println(errMsg);
				}

				catch (IllegalArgumentException iae) {
					System.out.println(iae);
					System.out.println(errMsg);
				}
			}
		} while (tryAgain);
		return value;
	}

	// pauseGames method starts here
	public void pauseGames(int seconds) {
		System.out.println(" Rolling the dice...");
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}

	}

}
