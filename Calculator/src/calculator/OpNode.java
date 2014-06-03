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

import java.math.BigDecimal;
import java.math.RoundingMode;

class OpNode {

	private OpNode leftChild, rightChild;
	private char operation;
	private BigDecimal value;

	/**
	 * Creates a node with the specified value
	 * 
	 * @param val
	 */
	OpNode(BigDecimal val) {
		leftChild = null;
		rightChild = null;
		operation = '#'; // Indicates node holds a value
		value = val;
	} // OpNode(double val)

	/**
	 * Creates a node with the specified operation
	 * 
	 * @param op
	 */
	OpNode(char op) {
		leftChild = null;
		rightChild = null;
		operation = op;
		value = null; // Indicates node holds an operation
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
	 * 
	 * @return value
	 * @throws NullPointerException
	 */
	BigDecimal getValue() throws NullPointerException {
		switch (operation) {
		case '*':
			value = leftChild.getValue().multiply(rightChild.getValue());
			break;
		case '/':
			try {
				value = leftChild.getValue().divide(rightChild.getValue());
			} catch (ArithmeticException e) { // Unbounded precision
				value = leftChild.getValue().divide(rightChild.getValue(), 10,
						RoundingMode.HALF_UP);
			} // try
			break;
		case '+':
			if (leftChild != null) {
				value = leftChild.getValue().add(rightChild.getValue());
			} else { // Signed number
				value = rightChild.getValue();
			} // if (leftChild != null)
			break;
		case '-':
			if (leftChild != null) {
				value = leftChild.getValue().subtract(rightChild.getValue());
			} else { // Signed number
				value = rightChild.getValue().negate();
			} // if (leftChild != null)
			break;
		case '^':
			try {
				value = leftChild.getValue().pow(
						rightChild.getValue().intValueExact());
			} catch (ArithmeticException e) { // Non-integer exponent
				try {
					value = new BigDecimal(
							Math.pow(leftChild.getValue().doubleValue(),
									rightChild.getValue().doubleValue()));
				} catch (NumberFormatException err) { // Result out of range
				} // try
			} // try
			break;
		case ')':
			value = leftChild.getValue();
			break;
		} // switch (operation)
		operation = '#'; // Node now holds a value
		return value;
	} // double getValue()

} // class OpNode
