/**	File Name: Main.java
 *	
 *	Project: NameGame
 *	
 *	Description: Simulates a game in which players draw names and are out when they draw their own name.
 *
 *	Author: Erik Anderson
 *
 *	Date: Jul 23, 2014
 */
package namegame;

import java.util.Random;

public class Main {

	/**
	 * Executes the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int players[] = new int[100];
		int count, round = 0, loop, index, temp, twos;
		boolean two;
		Random rand = new Random();
		for (int n = 1; n <= 100; n++) { // Iterate through number of players
			round = 0;
			twos = 0;
			for (int i = 0; i < 10000; i++) { // Play 10000 times
				count = n;
				two = false;
				while (count > 0) {
					for (loop = 0; loop < count; loop++) {
						players[loop] = loop; // Initialize array
					} // for (loop = 0; loop < count; loop++)
					for (loop = count; loop > 1; loop--) { // Randomize numbers
						index = rand.nextInt(loop);
						temp = players[loop - 1];
						players[loop - 1] = players[index];
						players[index] = temp;
					} // for (loop = count; loop > 1; loop--)
					temp = count;
					for (loop = 0; loop < count; loop++) { // Check eliminations
						if (players[loop] == loop) { // Drew own number
							temp--;
						} // if (players[loop] == loop)
					} // for (loop = 0; loop < count; loop++)
					count = temp; // Number of players left
					if (count == 2) { // Two players left
						two = true;
					} // if (count == 2)
					round++;
				} // while (count > 0)
				if (two) { // Count frequency of 2 winners
					twos++;
				} // if (two)
			} // for (int i = 0; i < 1000; i++)
			System.out.println("n: " + n + ", rounds: " + round / 10000.0
					+ ", two: " + twos);
		} // for (int n = 1; n <= 100; n++)
	} // public static void main(String[] args)

} // public class Main