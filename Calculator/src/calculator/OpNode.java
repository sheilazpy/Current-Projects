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
	private int order;

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
		setOrder();
	} // OpNode(BigDecimal val)

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
		setOrder();
	} // OpNode(char op)

	/**
	 * Determines the order of operations associated with the OpNode
	 * 
	 * @param op
	 */
	private void setOrder() {
		switch (operation) {
		case '^':
			order = 1;
			break;
		case '*':
		case '/':
			order = 2;
			break;
		case '+':
		case '-':
			order = 3;
			break;
		case '#':
		default:
			order = 0;
			break;
		} // switch (operation)
	} // private void setOrder()

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
	 * Recursively calculates the value associated with the OpNode
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
					value = BigDecimal.valueOf(Math
							.pow(leftChild.getValue().doubleValue(), rightChild
									.getValue().doubleValue()));
				} catch (NumberFormatException err) { // Result out of range
				} // try
			} // try
			break;
		case ')':
			value = leftChild.getValue();
			break;
		} // switch (operation)
		operation = '#'; // Node now holds a value
		setOrder();
		return value;
	} // double getValue()

	/**
	 * Determines the order of operations precedence of this OpNode in relation
	 * to OpNode node. Assumes this comes before node in the expression String.
	 * 
	 * @param node
	 * @return true if this precedes node, false otherwise
	 */
	boolean precedes(OpNode node) {
		return order <= node.order;
	} // boolean precedes(OpNode node)

	/**
	 * @return true if this holds an operation, false otherwise
	 */
	boolean isOperation() {
		return operation != '#';
	} // boolean isOperation()

	/**
	 * @return true if this holds a value, false otherwise
	 */
	boolean isValue() {
		return operation == '#';
	} // boolean isValue()

} // class OpNode
