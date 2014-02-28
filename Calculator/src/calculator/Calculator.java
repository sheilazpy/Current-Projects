package calculator;
// The "Calculator" class.
import hsa.*;
import java.util.*;
public class Calculator
{
    public static void main (String[] args)
    {
	double number1, number2, result;
	char operation;
	StringTokenizer findNums, findOps;
	Stdout.print ("Enter an equation:  ");
	String equation = Stdin.readLine ();
	StringBuffer line = new StringBuffer (equation);
	for (int pos = 0 ; pos < line.length () ; pos++)
	{
	    if ((line.charAt (pos) < 42) || (line.charAt (pos) > 57)
		    && (line.charAt (pos) != 94) || (line.charAt (pos) == 44))
	    {
		line.deleteCharAt (pos);
		pos--;
	    } //if
	} //for
	findNums = new StringTokenizer (line.toString (), "()^*/+-");
	number1 = Double.parseDouble (findNums.nextToken ());
	result = number1;
	findOps = new StringTokenizer (line.toString (), "1234567890.");
	while (findNums.hasMoreTokens ())
	{
	    operation = findOps.nextToken ().charAt (0);
	    number2 = Double.parseDouble (findNums.nextToken ());
	    switch (operation)
	    {
		case '^':
		    result = Math.pow (number1, number2);
		    break;
		case '*':
		    result = number1 * number2;
		    break;
		case '/':
		    result = (double) (number1) / (double) (number2);
		    break;
		case '+':
		    result = number1 + number2;
		    break;
		case '-':
		    result = number1 - number2;
		    break;
		default:
		    Stdout.println ("Invalid operation");
		    break;
	    } //switch
	    number1 = result;
	} //while
	Stdout.println (result);
    } // main method
} // Calculator class


