/**	File Name: OpTree.java
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

public class OpTree {

	private OpNode root;
	String expression;
	int position;

	/**
	 * Creates a tree with the specified expression.
	 * 
	 * @param expr
	 * @throws NullPointerException
	 */
	public OpTree(String expr) throws NullPointerException {
		expression = expr.replaceAll("\\s", ""); // Remove whitespace
		position = 0;
		checkParens();
		root = buildTree();
	} // public OpTree(String expr)

	/**
	 * @return root.value
	 * @throws NullPointerException
	 */
	public double getValue() throws NullPointerException {
		return root.getValue();
	} // public double getValue()

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
	 */
	private OpNode buildTree() throws NullPointerException {
		OpNode localRoot = null, currentNode = null, temp;
		char currentChar;
		while (position < expression.length()) {
			currentChar = expression.charAt(position);
			switch (currentChar) {
			case ')':
				position++;
				return localRoot;
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
				temp = getNextNode();
				if (localRoot == null) { // First character
					localRoot = getNextNode();
					localRoot.setLeftChild(temp);
					currentNode = localRoot;
				} else {
					currentNode.setRightChild(temp);
				} // if (localRoot == null)
				break;
			case '+':
			case '-':
				currentNode = getNextNode();
				currentNode.setLeftChild(localRoot);
				localRoot = currentNode;
				break;
			case '^':
				temp = getNextNode();
				temp.setLeftChild(currentNode.getRightChild());
				currentNode.setRightChild(temp);
				currentNode = temp;
				break;
			case '*':
			case '/':
				if (localRoot.getOperation() != '+'
						&& localRoot.getOperation() != '-') {
					currentNode = getNextNode();
					currentNode.setLeftChild(localRoot);
					localRoot = currentNode;
				} else {
					currentNode = localRoot;
					while (currentNode.getRightChild().getOperation() == '+'
							|| currentNode.getRightChild().getOperation() == '-') {
						currentNode = currentNode.getRightChild();
					} // while
					temp = getNextNode();
					temp.setLeftChild(currentNode.getRightChild());
					currentNode.setRightChild(temp);
					currentNode = temp;
				} // if
				break;
			} // switch (currentChar)
		} // while (position < expression.length())
		return localRoot;
	} // private OpNode buildTree()

	private OpNode getNextNode() {
		if (position >= expression.length()) {
			return new OpNode(')');
		}
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
			double value = Double.NaN;
			int end;
			for (end = expression.length(); end > position; end--) {
				try { // Find the number
					value = Double.parseDouble(expression.substring(position,
							end));
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
			position++;
		case ')':
			return new OpNode(current);
		default:
			return null;
		} // switch (current)
	}

} // public class OpTree
