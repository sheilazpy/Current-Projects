// The "Calculator1" class.
import hsa.*;
import java.util.*;
public class Calculator1
{
    public static void main (String[] args)
    {
	String number1, number2;
	double num1, num2;
	char operation;
	StringTokenizer findNums, findOps;
	Stdout.print ("Enter an equation:  ");
	String input = Stdin.readLine ();
	StringBuffer equation = new StringBuffer (input);
	for (int pos = 0 ; pos < equation.length () ; pos++)
	{
	    if ((equation.charAt (pos) < 42) || (equation.charAt (pos) > 57)
		    && (equation.charAt (pos) != 94) || (equation.charAt (pos) == 44))
	    {
		equation.deleteCharAt (pos);
		pos--;
	    } //if
	} //for
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
		if (equation.indexOf ("-") == 0)
		{
		    operation = findOps.nextToken ().charAt (0);
		}
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
		if (equation.indexOf ("-") == 0)
		{
		    operation = findOps.nextToken ().charAt (0);
		}
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
	while ((equation.indexOf ("+") != -1) || (equation.indexOf ("-", 1) != -1))
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
		if (equation.indexOf ("-") == 0)
		{
		    operation = findOps.nextToken ().charAt (0);
		    equation.deleteCharAt(0);
		    num1=-num1;
		}
		number2 = findNums.nextToken ();
		num2 = Double.parseDouble (number2);
	    }
	    while (operation != '+' && operation != '-');
	    if (operation == '+')
	    {
		equation.replace (equation.indexOf ("+") - number1.length (),
			equation.indexOf ("+") + number2.length () + 1, String.valueOf (num1 + num2));
	    }
	    if (operation == '-')
	    {
		equation.replace (equation.indexOf ("-", 1) - number1.length (),
			equation.indexOf ("-", 1) + number2.length () + 1, String.valueOf (num1 - num2));
	    }
	} //while
	Stdout.println (equation.toString ());
    } // main method
} // Calculator1 class


