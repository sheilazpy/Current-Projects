// The "Calculator4" class.
import hsa.*;
import java.util.*;
public class Calculator4
{
    private static StringBuffer doOps (StringBuffer equation)
    {
	String number1, number2;
	double num1, num2;
	char operation;
	StringTokenizer findNums, findOps;
	while (equation.indexOf ("^") != -1)
	{
	    findNums = new StringTokenizer (equation.toString (), "()^*/+-");
	    number2 = findNums.nextToken ();
	    num2 = Double.parseDouble (number2);
	    findOps = new StringTokenizer (equation.toString (), "1234567890.");
	    do
	    {
		number1 = number2;
		num1 = num2;
		operation = findOps.nextToken ().charAt (0);
		// if (equation.indexOf ("-") == 0)
		// {
		//     operation = findOps.nextToken ().charAt (0);
		// }
		number2 = findNums.nextToken ();
		num2 = Double.parseDouble (number2);
	    }
	    while (operation != '^');
	    equation.replace (equation.indexOf ("^") - number1.length (),
		    equation.indexOf ("^") + number2.length () + 1, String.valueOf (Math.pow (num1, num2)));
	} //while
	while ((equation.indexOf ("*") != -1) || (equation.indexOf ("/") != -1))
	{
	    findNums = new StringTokenizer (equation.toString (), "()^*/+-");
	    number2 = findNums.nextToken ();
	    num2 = Double.parseDouble (number2);
	    findOps = new StringTokenizer (equation.toString (), "1234567890.");
	    do
	    {
		number1 = number2;
		num1 = num2;
		operation = findOps.nextToken ().charAt (0);
		// if (equation.indexOf ("-") == 0)
		// {
		//     operation = findOps.nextToken ().charAt (0);
		// }
		number2 = findNums.nextToken ();
		num2 = Double.parseDouble (number2);
	    }
	    while (operation != '*' && operation != '/');
	    if (operation == '*')
	    {
		equation.replace (equation.indexOf ("*") - number1.length (),
			equation.indexOf ("*") + number2.length () + 1, String.valueOf (num1 * num2));
	    }
	    if (operation == '/')
	    {
		equation.replace (equation.indexOf ("/") - number1.length (),
			equation.indexOf ("/") + number2.length () + 1, String.valueOf (num1 / num2));
	    }
	} //while
	Stdout.println(equation.toString());
	while ((equation.indexOf ("+") != -1) || (equation.indexOf ("-", 1) != -1))
	{
	    if (equation.indexOf ("+") < equation.indexOf ("-", 1) &&
		    equation.indexOf ("+") != -1)
	    {
		findNums = new StringTokenizer (equation.toString (), "()^*/+-");
		number2 = findNums.nextToken ();
		num2 = Double.parseDouble (number2);
		findOps = new StringTokenizer (equation.toString (), "1234567890.");
		do
		{
		    number1 = number2;
		    num1 = num2;
		    operation = findOps.nextToken ().charAt (0);
		    // if (equation.indexOf ("-") == 0)
		    // {
		    //     operation = findOps.nextToken ().charAt (0);
		    //     equation.deleteCharAt (0);
		    //     num1 = -num1;
		    // }
		    number2 = findNums.nextToken ();
		    num2 = Double.parseDouble (number2);
		}
		while (operation != '+');
		equation.replace (equation.indexOf ("+") - number1.length (),
			equation.indexOf ("+") + number2.length () + 1, String.valueOf (num1 + num2));
	    } //if
	    else
	    {
		findNums = new StringTokenizer (equation.toString (), "()^*/+-");
		number2 = findNums.nextToken ();
		num2 = Double.parseDouble (number2);
		findOps = new StringTokenizer (equation.toString (), "1234567890.");
		do
		{
		    number1 = number2;
		    num1 = num2;
		    operation = findOps.nextToken ().charAt (0);
		    number2 = findNums.nextToken ();
		    num2 = Double.parseDouble (number2);
		}
		while (operation != '-');
		equation.replace (equation.indexOf ("-", 1) - number1.length (),
			equation.indexOf ("-", 1) + number2.length () + 1, String.valueOf (num1 - num2));
	    } //else
	} //while
	return equation;
    } //doOps method


    public static void main (String[] args)
    {
	double result;
	int parenPos1, parenPos2;
	StringBuffer equation, parenEq;
	Stdout.print ("Enter an equation:  ");
	String input = Stdin.readLine ();
	equation = new StringBuffer (input);
	for (int pos = 0 ; pos < equation.length () ; pos++)
	{
	    if ((equation.charAt (pos) < 40) || (equation.charAt (pos) > 57)
		    && (equation.charAt (pos) != 94) || (equation.charAt (pos) == 44))
	    {
		equation.deleteCharAt (pos);
		pos--;
	    } //if
	} //for
	while (equation.indexOf ("(") != -1)
	{
	    parenPos1 = equation.indexOf ("(");
	    parenPos2 = equation.lastIndexOf (")");
	    parenEq = new StringBuffer (equation.substring (parenPos1 + 1, parenPos2));
	    while (parenEq.indexOf ("(") != -1)
	    {
		parenPos1 = equation.indexOf ("(", parenPos1 + 1);
		parenPos2 = equation.lastIndexOf (")", parenPos2 - 1);
		parenEq = new StringBuffer (equation.substring (parenPos1 + 1, parenPos2));
	    } //while
	    parenEq = doOps (parenEq);
	    equation.replace (parenPos1, parenPos2 + 1, parenEq.toString ());
	} //while
	equation = doOps (equation);
	result = Math.round (Double.parseDouble (equation.toString ()) * 10000000000.0) / 10000000000.0;
	if (result % 1 == 0)
	{
	    Stdout.println ((int) result);
	} //if
	else
	{
	    Stdout.println (result);
	} //else
    } // main method
} // Calculator4 class


