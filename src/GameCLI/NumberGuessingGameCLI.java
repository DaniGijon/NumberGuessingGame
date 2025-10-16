package GameCLI;

import java.util.Scanner;

public class NumberGuessingGameCLI {
	
	private static int secretNumber = (int) (Math.random() * 100 + 1);
	private static int userNumber;
	
	public static void main (String args []) {
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Welcome to the Number Guessing Game!\n"
				+ "I'm thinking of a number between 1 and 100.\n"
				+ "You have 5 chances to guess the correct number.");
	    System.out.println("Enter your guess (or type quit):");
	    while (scanner.hasNextLine()){
		    String inputString = scanner.nextLine(); 
		    if (inputString.equals("quit")) {
		    	break;
		    } else {
		    	if (isAValidNumber(inputString)) {
			    	userNumber = Integer.valueOf(inputString);
	
				    System.out.println("Your Number is: " + userNumber);
					System.out.println("Secret Number is: " + secretNumber);
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
}
