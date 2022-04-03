package wk4_arrays;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class AfridaIslam_Project1_4DicePoker {
	/*
	Kevin Short, ITSS 3312.001, Project 1
	Project 1: Game of 4-Dice Draw Poker
	*/
	
	public static void main(String[] args) {
		
		// Declare Vars
		String name;
		int[] dice = new int[4];
		
		boolean fourKind = false;
		boolean threeKind = false;
		boolean strait = false;
		boolean twoPair = false;
		boolean pair = false;
		
		int drawNum = 0;
		int[] drawIndex = new int[3];
		
		// Declare Scanner object
		Scanner input = new Scanner(System.in);

		// Print game requirements and rules
		System.out.println("4-Dice_Draw_Poker is a poker game that uses dice instead of cards.");
		System.out.println("The computer will roll 4 dice and then identify the best hand.");
		System.out.println("The player can draw (re-roll) up to 3 dice to improve the hand.");
		System.out.println("Possible hands include 4-of-a-Kind, 3-of-a-Kind, 2-Pair, Pair, or a Straight.");
		System.out.println();

		// Prompt for and read-in name
		System.out.print("Enter your name: ");
		name = input.next();
		System.out.println();
		
		// Print welcome statement
		System.out.println("Hi " + name + ". Welcome to 4-Dice Draw Poker!");
		System.out.println();
		System.out.print("Press any letter-key and then enter to start the game and roll the dice. ");
		input.next();
		System.out.println();
		
		// Set dice values and sort the dice using Arrays.sort()
		dice = getDice();
		//dice = getDice(1,2,3,4,4);  // Use this for testing specific dice rolls 
		//prtDice(dice);  // Use this to print the dice rolls before the sort
		Arrays.sort(dice);
		//prtDice(dice);  // Use this to print dice after the sort if desired (use for testing)
		
		// Evaluate initial roll for hands
		fourKind = is4Kind(dice);
		if (!fourKind) threeKind = is3Kind(dice);
		if (!fourKind && !threeKind) twoPair = is2Pair(dice);
		if (!fourKind && !threeKind && !twoPair) pair = isPair(dice);
		strait = isStrait(dice);
		
		// Print results
		System.out.println();
		System.out.println("     ******* INITIAL HAND *******");
		System.out.println(" Roll   4Kind 3Kind 2Pair Pair Straight");
		System.out.println("------  ----- ----- ----- ---- --------");
		
		prtDice(dice);
		System.out.printf("%b %b %b %b %b \n", fourKind, threeKind, twoPair, pair, strait);
		System.out.println();
		
		// Get the number of dice to reroll (cards to draw)
		System.out.print("Enter the number of dice to draw (re-roll) - up to 3: ");
		drawNum = input.nextInt();
		
		// Get the indexes of the dice to reroll (draw)
		if (drawNum > 0) {
			System.out.print("Enter the index numbers (0 to 3) separated by a space "
					+ "of the dice you wish to draw: ");
			// Store the indexes entered in an array, drawIndex
			for (int i = 0; i < drawNum; i++) {
				drawIndex[i] = input.nextInt();
			}
		}
		System.out.println();
		
		// Roll (draw) new dice values, then sort and print the final roll values
		for (int i = 0; i < drawNum; i++) {
			dice[drawIndex[i]] = getDie();
		}
		//prtDice(dice);  // Use this to test Arrays.sort
		Arrays.sort(dice);
		
		// Reset the boolean vars for hands to false
		fourKind = false;
		threeKind = false;
		twoPair = false;
		pair = false;
		strait = false;
		
		// Evaluate dice values for hands after draw
		fourKind = is4Kind(dice);
		if (!fourKind) threeKind = is3Kind(dice);
		if (!fourKind && !threeKind) twoPair = is2Pair(dice);
		if (!fourKind && !threeKind && !twoPair) pair = isPair(dice);
		strait = isStrait(dice);
		
		// Print results after draw
		System.out.println();
		System.out.println("     ******** FINAL HAND ********");
		System.out.println(" Roll   4Kind 3Kind 2Pair Pair Straight");
		System.out.println(" ----   ----- ----- ----- ---- --------");
		
		prtDice(dice);
		System.out.printf("%b %b %b %b %b \n", fourKind, threeKind, twoPair, pair, strait);
		System.out.println();
		
		// Print the close statement
		System.out.println("Thank you for playing, " + name + ". Play again soon!");
		
		// Close the Scanner
		input.close();
		
	}  // End of main method, class methods are below ***********************************
	
	// Set dice with Random values and sort array
	public static int[] getDice() {
		Random random = new Random();
		int[] dice = new int[4];
		for (int i = 0; i < dice.length; i++) {
			dice[i] = random.nextInt(6) + 1;
		}
		return dice;
	}
	
	// Set dice with literal values (use this for testing) and sort array
	public static int[] getDice(int a, int b, int c, int d) {
		int[] dice = {a, b, c, d};
		return dice;
	}
	
	// Set individual dice value (use this for the draw)
	public static int getDie() {
		Random random = new Random();
		return random.nextInt(6) + 1;
	}
	
	// Print dice values 
	public static void prtDice(int[] d) {
		for (int z : d) System.out.print(z + " ");
		//System.out.println();
	}
	
	// Evaluate for 4-of-a-kind
	public static boolean is4Kind(int[] dice) {
		boolean fourKind = false;
		int count4 = 0;
		
		for (int i = 1; i < dice.length; i++) {
			if (dice[0] == dice[i]) {
				count4++;
			}
		}
		if (count4 == 3) {
			fourKind = true;
		}
		return fourKind;
	}

	// Evaluate for 3-of-a-kind
	public static boolean is3Kind(int[] dice) {
		boolean threeKind = false;
		int count3 = 0;
		
		// Check for 3Kind starting with index 0
		for (int i = 1; i < dice.length; i++) {
			if (dice[0] == dice[i]) {
				count3++;
			}
		}
		if (count3 == 2) {
			threeKind = true;
			return threeKind;
		}
		else {
			count3 = 0;
		}
		
		// Check for 3Kind starting with index 1
		for (int i = 2; i < dice.length; i++) {
			if (dice[1] == dice[i])
				count3++;
		}
		if (count3 == 2) threeKind = true;
		return threeKind;
	}
	
	// Evaluate for 2 pair
	public static boolean is2Pair(int[] dice) {
		boolean pair1 = false;
		int pair1Index = 0;
		boolean twoPair = false;
		
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
		return twoPair;
	}
	
	// Evaluate for a pair
	public static boolean isPair(int[] dice) {
		boolean pair = false;
		
		for (int i = 0; i < dice.length - 1; i++) {
			if (dice[i] == dice[i + 1]) {
				pair = true;
				break;
			}
		}
			return pair;
	}
	
	// Evaluate for a straight
	public static boolean isStrait(int[] dice) {
		boolean strait = false;
		
		for (int i = 0; i < dice.length - 1; i++) {
			if (dice[i] == dice[i + 1] - 1)
				strait = true;
			else {
				strait = false;
				return strait;
			}
		}
		return strait;
	}
		
}