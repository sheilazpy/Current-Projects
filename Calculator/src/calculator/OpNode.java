/**	File Name: OpNode.java
 *	
 *	Project: Calculator
 *	
 *	Description: This class implements a node in the operation tree.
 *				 A node can hold either an operation or a value, and up to 2 children.
 *
 *	Author: Erik Anderson
 *
 *	Date: 3/24/14
 */

package calculator;

class OpNode {

	private OpNode leftChild, rightChild;
	private char operation;
	private double value;

	/**
	 * Creates a node with the specified value
	 * @param val
	 */
	OpNode(double val) {
		leftChild = null;
		rightChild = null;
		operation = '#'; // Indicates node holds a value
		value = val;
	} // OpNode(double val)

	/**
	 * Creates a node with the specified operation
	 * @param op
	 */
	OpNode(char op) {
		leftChild = null;
		rightChild = null;
		operation = op;
		value = Double.NaN; // Indicates node holds an operation
	}

	/**
	 * @param left
	 */
	void setLeftChild(OpNode left) {
		leftChild = left;
	} // void setLeftChild (OpNode left)

	/**
	 * @param right
	 */
	void setRightChild(OpNode right) {
		rightChild = right;
	} // void setRightChild (OpNode right)

	/**
	 * @return leftChild
	 */
	OpNode getLeftChild() {
		return leftChild;
	} // OpNode getLeftChild ()

	/**
	 * @return rightChild
	 */
	OpNode getRightChild() {
		return rightChild;
	} // OpNode getRightChild ()

	/**
	 * @return operation
	 */
	char getOperation() {
		return operation;
	} // char getOperation ()

	/**
	 * Calculates the value associated with the node
	 * @return value
	 * @throws NullPointerException
	 */
	double getValue() throws NullPointerException {
		switch (operation) {
		case '*':
			value = leftChild.getValue() * rightChild.getValue();
			break;
		case '/':
			value = leftChild.getValue() / rightChild.getValue();
			break;
		case '+':
			value = leftChild.getValue() + rightChild.getValue();
			break;
		case '-':
			value = leftChild.getValue() - rightChild.getValue();
			break;
		case '^':
			value = Math.pow(leftChild.getValue(), rightChild.getValue());
			break;
		case ')':
			value = leftChild.getValue();
			break;
		} // switch (operation)
		operation = '#'; // Node now holds a value
		return value;
	} // double getValue()

} // class OpNode
