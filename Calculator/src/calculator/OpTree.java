/**	
 * File Name: OpTree.java
 *	
 *	Project: Calculator
 *	
 *	Description: This class implements an operation tree.
 *				 The tree is generated from an expression String.
 *
 *	Author: Erik Anderson
 *
 *	Date: 3/26/14
 */
package calculator;

import java.math.BigDecimal;

class OpTree {

	private OpNode root;
	private String expression;
	private int position;

	/**
	 * Creates a tree with the specified expression.
	 * 
	 * @param expr
	 * @throws NullPointerException
	 */
	OpTree(String expr) throws NullPointerException {
		// remove whitespace
		expression = expr.replaceAll("\\s", "").toLowerCase();
		position = 0;
		checkParens();
		root = buildTree();
	} // OpTree(String expr)

	/**
	 * @return root.value
	 * @throws NullPointerException
	 */
	BigDecimal getValue() throws NullPointerException {
		return root.getValue();
	} // BigDecimal getValue()

	/**
	 * Checks for mismatched parentheses. Adds leading or trailing parentheses
	 * to fix.
	 */
	private void checkParens() {
		int openCount, closeCount, difference, loop;
		openCount = count('(');
		closeCount = count(')');
		if (openCount == closeCount) {
			return;
		} // if (openCount == closeCount)
		if (openCount > closeCount) { // Add trailing parentheses
			difference = openCount - closeCount;
			for (loop = 0; loop < difference; loop++) {
				expression += ')';
			} // for (loop = 0; loop < difference; loop++)
		} else { // Add leading parentheses
			difference = closeCount - openCount;
			for (loop = 0; loop < difference; loop++) {
				expression = '(' + expression;
			} // for (loop = 0; loop < difference; loop++)
		} // if (openCount > closeCount)
		System.out.println("Warning: mismatched parentheses");
		System.out.println("Calculating: " + expression);
	} // private void checkParens()

	/**
	 * Counts the number of the specified character in the expression String.
	 * 
	 * @param paren
	 * @return count
	 */
	private int count(char paren) {
		int index = 0, count = 0;
		while (expression.indexOf(paren, index) != -1
				&& index < expression.length() - 1) {
			index = expression.indexOf(paren, index) + 1;
			count++;
		} // while
		return count;
	} // private int count(char paren)

	/**
	 * Builds the operation tree from the expression String. Handles parentheses
	 * recursively.
	 * 
	 * @return localRoot
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	private OpNode buildTree() throws NullPointerException,
			IllegalArgumentException {
		OpNode localRoot = null, currentNode = null, temp;
		boolean impliedMult;
		char currentChar;
		while (position < expression.length()) {
			impliedMult = false;
			currentChar = expression.charAt(position);
			temp = getNextNode();
			switch (currentChar) {
			case ')':
				localRoot.getValue(); // Parenthetical operand
				return localRoot;
			case '+':
			case '-':
				if (currentNode != null && currentNode.isOperation()) {
					// Signed number
					currentNode.setRightChild(temp);
					currentNode = temp;
				} else {
					currentNode = temp;
					currentNode.setLeftChild(localRoot);
					localRoot = currentNode;
				} // if (currentNode != null && currentNode.isOperation())
				break;
			case '(':
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '.':
			case 'e':
			case 'p':
			case 's':
			case 'c':
			case 't':
			case 'a':
			case 'l':
				if (localRoot == null) { // First OpNode
					localRoot = temp;
					currentNode = localRoot;
					break;
				} else if (currentNode.isOperation()) {
					currentNode.setRightChild(temp);
					currentNode = temp;
					break;
				} else { // Implied multiplication
					currentNode = new OpNode('*');
					currentNode.setRightChild(temp);
					temp = currentNode;
					impliedMult = true;
				} // if (localRoot == null)
			case '^':
			case '*':
			case '/':
			case '%':
				if (localRoot.precedes(temp)) {
					currentNode = temp;
					currentNode.setLeftChild(localRoot);
					localRoot = currentNode;
				} else {
					currentNode = localRoot;
					while (!currentNode.getRightChild().precedes(temp)) {
						currentNode = currentNode.getRightChild();
					} // while (!currentNode.getRightChild().precedes(temp))
					temp.setLeftChild(currentNode.getRightChild());
					currentNode.setRightChild(temp);
					currentNode = temp;
				} // if (localRoot.precedes(temp))
				if (impliedMult) {
					currentNode = currentNode.getRightChild();
				} // if (impliedMult)
				break;
			} // switch (currentChar)
		} // while (position < expression.length())
		return localRoot;
	} // private OpNode buildTree()

	/**
	 * Parses the operand or operation at the current position in the expression
	 * String. Updates the position counter.
	 * 
	 * @return nextNode
	 * @throws IllegalArgumentException
	 */
	private OpNode getNextNode() throws IllegalArgumentException {
		char current = expression.charAt(position);
		switch (current) {
		case '(':
			position++;
			return buildTree();
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
		case '.':
			BigDecimal value = null;
			int end;
			for (end = expression.length(); end > position; end--) {
				try { // Find the number
					value = new BigDecimal(expression.substring(position, end));
					break;
				} catch (NumberFormatException e) {
				} // try
			} // for (end = expression.length(); end > position; end--)
			position = end;
			return new OpNode(value);
		case '+':
		case '-':
		case '^':
		case '*':
		case '/':
		case '%':
			position++;
			return new OpNode(current);
		case ')':
			position++;
			return null;
		case 'e':
			position++;
			return new OpNode(BigDecimal.valueOf(Math.E));
		case 'p':
			if (expression.indexOf("pi", position) == position) {
				position += 2;
				return new OpNode(BigDecimal.valueOf(Math.PI));
			} // if (expression.indexOf("pi", position) == position)
			break;
		case 's':
			if (expression.indexOf("sqrt", position) == position) {
				position += 4;
				return new OpNode('r');
			} // if (expression.indexOf("sqrt", position) == position)
			if (expression.indexOf("sin", position) == position) {
				position += 3;
				return new OpNode(current);
			} // if (expression.indexOf("sin", position) == position)
			break;
		case 'c':
			if (expression.indexOf("cos", position) == position) {
				position += 3;
				return new OpNode(current);
			} // if (expression.indexOf("cos", position) == position)
			break;
		case 't':
			if (expression.indexOf("tan", position) == position) {
				position += 3;
				return new OpNode(current);
			} // if (expression.indexOf("tan", position) == position)
			break;
		case 'a':
			if (expression.indexOf("asin", position) == position) {
				position += 4;
				return new OpNode('S');
			} // if (expression.indexOf("asin", position) == position)
			if (expression.indexOf("acos", position) == position) {
				position += 4;
				return new OpNode('C');
			} // if (expression.indexOf("acos", position) == position)
			if (expression.indexOf("atan", position) == position) {
				position += 4;
				return new OpNode('T');
			} // if (expression.indexOf("atan", position) == position)
			if (expression.indexOf("abs", position) == position) {
				position += 3;
				return new OpNode('|');
			} // if (expression.indexOf("abs", position) == position)
			break;
		case 'l':
			if (expression.indexOf("log", position) == position) {
				position += 3;
				return new OpNode(current);
			} // if (expression.indexOf("log", position) == position)
			if (expression.indexOf("ln", position) == position) {
				position += 2;
				return new OpNode('n');
			} // if (expression.indexOf("ln", position) == position)
			break;
		} // switch (current)
		throw new IllegalArgumentException("Invalid character " + current
				+ " at position " + position);
	} // private OpNode getNextNode()

} // class OpTree
