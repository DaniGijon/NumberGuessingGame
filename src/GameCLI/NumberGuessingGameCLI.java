package GameCLI;

import java.util.Scanner;

public class NumberGuessingGameCLI {
	
	private static int secretNumber = (int) (Math.random() * 100 + 1);
	private static int userNumber;
	private static int attemptsCount = 0;
	private static int stage = 1;
	private static int chances;
	private static int max = 3;
	private static long startClock;
	private static int highscore;
	
	public static void main (String args []) {
		Scanner scanner = new Scanner(System.in); 
		printWelcomeText();
		printSelectDifficulty();
	    
	    while (scanner.hasNextLine()){
		    String inputString = scanner.nextLine().trim(); 
		    if (inputString.equals("quit")) {
		    	break;
		    } else {
		    	if (stage == 1 && isAValidNumber(inputString, max)) {
		    		chances = numChancesByDifficulty(inputString);
		    		stage = 2;
		    		max = 100;
		    		startClock = System.currentTimeMillis();
		    		System.out.println("\nGreat! You have selected the " + printDifficulty(inputString) + " difficulty level [" + chances + " chances].");
		    		System.out.println("\nLet's start the game!");
		    		System.out.println("\nEnter your guess (or type quit):");
		    	} else if (stage == 2 && chances > 0 && isAValidNumber(inputString, max)) {
			    	userNumber = Integer.valueOf(inputString);
			    	++attemptsCount;
			    	if (userNumber == secretNumber) {
			    		if (attemptsCount == 1) {
			    			System.out.println("Congratulations! You guessed the correct number in " + attemptsCount + " attempt.");
			    		} else {
			    			System.out.println("Congratulations! You guessed the correct number in " + attemptsCount + " attempts.");
			    		}
			    		long elapsedTimeMillis = System.currentTimeMillis()-startClock;
			    		float elapsedTimeSec = elapsedTimeMillis/1000F;
			    		System.out.println("It took you " + elapsedTimeSec + " seconds.");
			    		checkHighscore(attemptsCount);
			    		stage = 3;
			    		System.out.println("Do you want to play again? (Y/N)");
			    	} else if (userNumber < secretNumber) {
			    		System.out.println("Incorrect. The number is greater than " + userNumber);
			    		chances --;
			    	} else {
			    		System.out.println("Incorrect. The number is less than " + userNumber);	
			    		chances --;
			    	}
			    	if (chances == 0) {
			    		System.out.println("You lost. Secret Number was: " + secretNumber);
			    		stage = 3;
			    		System.out.println("Do you want to play again? (Y/N)");
			    	}
		    	} else if (stage == 3) {
		    		if (inputString.trim().toUpperCase().equals("Y")) {
		    			restartGame();
		    			printWelcomeText();
		    			printSelectDifficulty();
		    		} else if (inputString.trim().toUpperCase().equals("N")) {
		    			break;
		    		} else {
		    			System.out.println("Type Y if you want to play again.\nType N if you want to exit.");
		    		}
		    	
		    	} else {
		    		printEnterValidNumber(max);
		    	}
		    }
	    }
	}
	
	private static boolean isAValidNumber (String numberStr, int max) {
		try {
			return Integer.parseInt(numberStr) > 0 &&  Integer.parseInt(numberStr) <= max ? true : false;
		} catch (NumberFormatException nfe)  {
			return false;
		}
	}
	
	private static void restartGame () {
		attemptsCount = 0;
		stage = 1;
		max = 3;
		secretNumber = (int) (Math.random() * 100 + 1);
	}
	
	private static int numChancesByDifficulty (String inputString) {
		int numChances = 10;
		if (inputString.equals("2")) {
			numChances = 5;
		} else if (inputString.equals("3")) {
			numChances = 3;
		}
		return numChances;
	}
	
	private static void checkHighscore (int numAttempts) {
		boolean isANewHighscore = false;
		if (highscore > 0) {
			if (numAttempts < highscore) {
				isANewHighscore = true;
			}
		} else {
			isANewHighscore = true;
		}
		
		if (isANewHighscore) {
			highscore = numAttempts;
			System.out.println("Awesome! You setted a new Highscore: " + numAttempts + " attempts.");
		}
	}
	
	private static void printEnterValidNumber(int max) {
		System.out.println("You must enter a number between 1 and " + max + ", with no decimals.");
	}
	
	private static void printWelcomeText () {
		System.out.println("Welcome to the Number Guessing Game!\n"
				+ "I'm thinking of a number between 1 and 100.\n"
				+ "You have a few chances to guess the correct number.");
	}
	
	private static void printSelectDifficulty () {
		System.out.println("\n"
				+ "Please select the difficulty level:\n"
				+ "1. Easy (10 chances)\n"
				+ "2. Medium (5 chances)\n"
				+ "3. Hard (3 chances)");
		System.out.println("\nEnter your choice:");
	}
	
	private static String printDifficulty (String inputString) {
		switch (inputString) {
			case "1": 
				return "Easy";
			case "2":
				return "Medium";
			case "3":
				return "Hard";
		}
		return inputString;
	}
}
