/**	File Name: RockPaperScissors.java
 *	
 *	Project: RockPaperScissors
 *	
 *	Description: This program allows the user to play rock, paper, scissors against the
 *	computer.
 *
 *	Author: Erik Anderson
 *
 *	Date: 3/7/14
 */

import java.util.*;

public class RockPaperScissors {
	public static void main(String[] args) {
		int computerMove, playerMove, computerScore = 0, playerScore = 0;
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out
					.print("Choose a move: 1=Rock, 2=Paper, 3=Scissors, 0=Quit  ");
			playerMove = scan.nextInt();
			while (playerMove < 0 || playerMove > 3) {
				System.out.println("You must enter a number between 0 and 3.");
				System.out
						.print("Choose a move: 1=Rock, 2=Paper, 3=Scissors, 0=Quit  ");
				playerMove = scan.nextInt();
			} // while (playerMove < 0 || playerMove > 3)
			if (playerMove == 0) {
				break;
			} // if (playerMove == 0)
			computerMove = (int) (Math.random() * 3 + 1);
			System.out.println();
			switch (playerMove) {
			case 1:
				System.out.println("You chose Rock.");
				break;
			case 2:
				System.out.println("You chose Paper.");
				break;
			case 3:
				System.out.println("You chose Scissors.");
				break;
			} // switch (playerMove)
			switch (computerMove) {
			case 1:
				System.out.println("The computer chose Rock.");
				break;
			case 2:
				System.out.println("The computer chose Paper.");
				break;
			case 3:
				System.out.println("The computer chose Scissors.");
				break;
			} // switch (computerMove)
			switch ((computerMove - playerMove + 3) % 3) {
			case 0:
				System.out.println("It was a tie.");
				break;
			case 1:
				System.out.println("The computer won.");
				computerScore++;
				break;
			case 2:
				System.out.println("You won.");
				playerScore++;
				break;
			} // switch ((computerMove - playerMove + 3) % 3)
			System.out.println();
		} // while (true)
		System.out.println();
		System.out.println("Your score is " + playerScore);
		System.out.println("The computer's score is " + computerScore);
		if (playerScore > computerScore) {
			System.out.println("You won.");
		} else if (computerScore > playerScore) {
			System.out.println("The computer won.");
		} else {
			System.out.println("It was a tie.");
		} // if (playerScore > computerScore)
	} // main method
} // RockPaperScissors class
