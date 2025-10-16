package GameCLI;

import java.util.Scanner;

public class NumberGuessingGameCLI {
	
	private static int secretNumber = (int) (Math.random() * 100 + 1);
	private static int userNumber;
	private static int attemptsCount = 0;
	private static int stage = 1;
	
	public static void main (String args []) {
		Scanner scanner = new Scanner(System.in); 
		printWelcomeText();
	    System.out.println("\nEnter your guess (or type quit):");
	    while (scanner.hasNextLine()){
		    String inputString = scanner.nextLine(); 
		    if (inputString.equals("quit")) {
		    	break;
		    } else {
		    	if (stage == 1 && isAValidNumber(inputString)) {
			    	userNumber = Integer.valueOf(inputString);
			    	++attemptsCount;
			    	if (userNumber == secretNumber) {
			    		if (attemptsCount == 1) {
			    			System.out.println("Congratulations! You guessed the correct number in " + attemptsCount + " attempt.");
			    		} else {
			    			System.out.println("Congratulations! You guessed the correct number in " + attemptsCount + " attempts.");
			    		}
			    		stage = 2;
			    		System.out.println("Do you want to play again? (Y/N)");
			    	} else if (userNumber < secretNumber) {
			    		System.out.println("Incorrect. The number is greater than " + userNumber);
			    	} else {
			    		System.out.println("Incorrect. The number is less than " + userNumber);	
			    	}
					//System.out.println("Secret Number is: " + secretNumber);
		    	} else if (stage == 2) {
		    		if (inputString.trim().toUpperCase().equals("Y")) {
		    			restartGame();
		    			printWelcomeText();
		    		    System.out.println("\nEnter your guess (or type quit):");
		    		} else if (inputString.trim().toUpperCase().equals("N")) {
		    			break;
		    		} else {
		    			System.out.println("Type Y if you want to play again.\nType N if you want to exit.");
		    		}
		    	
		    	} else {
		    		System.out.println("You must enter a number between 1 and 100, with no decimals.");
		    	}
		    }
	    }
	}
	
	private static boolean isAValidNumber (String numberStr) {
		try {
			return Integer.parseInt(numberStr) > 0 &&  Integer.parseInt(numberStr) <= 100 ? true : false;
		} catch (NumberFormatException nfe)  {
			return false;
		}
	}
	
	private static void restartGame () {
		attemptsCount = 0;
		stage = 1;
		secretNumber = (int) (Math.random() * 100 + 1);
	}
	
	private static void printWelcomeText () {
		System.out.println("Welcome to the Number Guessing Game!\n"
				+ "I'm thinking of a number between 1 and 100.\n"
				+ "You have 5 chances to guess the correct number.");
		System.out.println("\nLet's start the game!");
	}
}
