/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author 1003125
 */
public class Main {

    //Doesn't work with scientific notation.
    private static String doMath(double num1, double num2, char op) {
        switch (op) {
            case '^':
                return String.valueOf(Math.pow(num1, num2));
            case '*':
                return String.valueOf(num1 * num2);
            case '/':
                return String.valueOf(num1 / num2);
            case '+':
                return String.valueOf(num1 + num2);
            default:
                return ("Error");
        }//switch
    }//doMath method

    private static StringBuffer parseEq(StringBuffer equation, char op) {
        int pos;
        String numStr1, numStr2, opStr;
        char operation;
        double num1, num2;
        StringTokenizer findNums, findOps;
        while (equation.indexOf("--") == 0) {
            equation.deleteCharAt(0);
            equation.deleteCharAt(0);
        } //while
        while (equation.indexOf("--") != -1) {
            pos = equation.indexOf("--");
            equation.replace(pos, pos + 2, "+");
        }//while
        pos = 1;
        while (equation.indexOf("-", pos) != -1) {
            pos = equation.indexOf("-", pos);
            if (equation.charAt(pos - 1) > 47 && equation.charAt(pos - 1) < 58) {
                equation.insert(pos, "+");
            }//if
            pos += 2;
        }//while
        while (equation.indexOf(String.valueOf(op)) != -1) {
            findNums = new StringTokenizer(equation.toString(), "()^*/+");
            numStr2 = findNums.nextToken();
            num2 = Double.parseDouble(numStr2);
            findOps = new StringTokenizer(equation.toString(), "1234567890.-E");
            do {
                numStr1 = numStr2;
                num1 = num2;
                opStr = findOps.nextToken();
                operation = opStr.charAt(0);
                numStr2 = findNums.nextToken();
                num2 = Double.parseDouble(numStr2);
            } while (operation != op);
            pos = equation.indexOf(String.valueOf(operation));
            equation.replace(pos - numStr1.length(), pos + opStr.length() +
                    numStr2.length(), doMath(num1, num2, operation));
        }//while
        return equation;
    }//parseEq method

    private static StringBuffer parseEq(StringBuffer equation, char op1, char op2) {
        int pos;
        String numStr1, numStr2, opStr;
        char operation;
        double num1, num2;
        StringTokenizer findNums, findOps;
        while (equation.indexOf("--") == 0) {
            equation.deleteCharAt(0);
            equation.deleteCharAt(0);
        } //while
        while (equation.indexOf("--") != -1) {
            pos = equation.indexOf("--");
            equation.replace(pos, pos + 2, "+");
        }//while
        pos = 1;
        while (equation.indexOf("-", pos) != -1) {
            pos = equation.indexOf("-", pos);
            if (equation.charAt(pos - 1) >= 47 && equation.charAt(pos - 1) < 58) {
                equation.insert(pos, "+");
            }//if
            pos += 2;
        }//while
        while (equation.indexOf(String.valueOf(op1)) != -1 ||
                equation.indexOf(String.valueOf(op2)) != -1) {
            findNums = new StringTokenizer(equation.toString(), "()^*/+");
            numStr2 = findNums.nextToken();
            num2 = Double.parseDouble(numStr2);
            findOps = new StringTokenizer(equation.toString(), "1234567890.-E");
            do {
                numStr1 = numStr2;
                num1 = num2;
                opStr = findOps.nextToken();
                operation = opStr.charAt(0);
                numStr2 = findNums.nextToken();
                num2 = Double.parseDouble(numStr2);
            } while (operation != op1 && operation != op2);
            pos = equation.indexOf(String.valueOf(operation));
            equation.replace(pos - numStr1.length(), pos + opStr.length() +
                    numStr2.length(), doMath(num1, num2, operation));
        }//while
        return equation;
    }//parseEq method

    private static StringBuffer doOps(StringBuffer equation) {

        equation = parseEq(equation, '^');
        equation = parseEq(equation, '*', '/');
        equation = parseEq(equation, '+');
        return equation;
    } //doOps method

    public static void main(String[] args) {
        double result;
        int openParen, closeParen;
        StringBuffer equation, parenEq;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter an equation:  ");
        String input = scan.nextLine();
        equation = new StringBuffer(input);
        for (int pos = 0; pos < equation.length(); pos++) {
            if (equation.charAt(pos) < 40 || equation.charAt(pos) > 57 &&
                    equation.charAt(pos) != 94 || equation.charAt(pos) == 44) {
                equation.deleteCharAt(pos);
                pos--;
            } //if
        } //for
        while (equation.indexOf("(") != -1) {
            openParen = equation.indexOf("(");
            closeParen = equation.indexOf(")");
            if (closeParen == -1) {
                closeParen = equation.length();
            } //if
            parenEq = new StringBuffer(equation.substring(openParen + 1, closeParen));
            while (parenEq.indexOf("(") != -1) {
                openParen = equation.indexOf("(", openParen + 1);
                closeParen = equation.indexOf(")", closeParen - 1);
                if (closeParen == -1) {
                    closeParen = equation.length();
                } //if
                parenEq = new StringBuffer(equation.substring(openParen + 1, closeParen));
            } //while
            if (openParen > 0 && equation.charAt(openParen - 1) > 47 &&
                    equation.charAt(openParen - 1) < 58) {
                equation.insert(openParen, "*");
                openParen++;
                closeParen++;
            } //if
            if (closeParen < equation.length() - 1 && equation.charAt(closeParen + 1) > 47 && equation.charAt(closeParen + 1) < 58) {
                equation.insert(closeParen + 1, "*");
            } //if
            parenEq = doOps(parenEq);
            equation.replace(openParen, closeParen + 1, parenEq.toString());
        } //while
        equation = doOps(equation);
        result = Math.round(Double.parseDouble(equation.toString()) * 10000000000.0) / 10000000000.0;
        if (result % 1 == 0) {
            System.out.println((int) result);
        } //if
        else {
            System.out.println(result);
        } //else
    } // main method
}
