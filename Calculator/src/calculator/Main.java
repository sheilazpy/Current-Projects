/**	File Name: Main.java
 *	
 *	Project: Calculator
 *	
 *	Description: This program implements a text-based calculator using a tree.
 *				 Can handle ()^/*-+  and real operands, including scientific notation.
 *
 *	Author: Erik Anderson
 *
 *	Date: 2/28/14
 */

package calculator;

import java.util.Scanner;

public class Main {

	/**
	 * Executes the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String input;
		OpTree calculator;
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("Enter a mathematical expression (# to quit):");
			input = scan.nextLine();
			if (input.charAt(0) == '#') {
				break;
			} // if (input.charAt(0) == '#')
			//try {
				calculator = new OpTree(input);
				System.out.println(calculator.getValue().stripTrailingZeros()
						.toPlainString());
			//} catch (NullPointerException e) {
				//System.out.println("Invalid expression. Please try again.");
			//} // try
		} // while (true)
		scan.close();
		System.out.println("Goodbye");
	}// public static void main(String[] args)
}// public class Main
