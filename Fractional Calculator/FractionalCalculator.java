package fracCalc4;

import java.util.Scanner;

public class FractionalCalculator
{
	// It takes any mixed fraction, fraction or an integer and returns the fraction form of that number. 
	public static String convertToFraction(String input)
	{
		int Slash = input.indexOf("/");
		if (Slash == -1)
		{
			String fraction = input + "/1";
			return fraction;
		}
		int underScore = input.indexOf("_");
		if (underScore == -1)
		{
			return input;
		}
		else
		{
			String integer = input.substring(0, underScore);
			int realNumber = Integer.parseInt(integer);
			int slash = input.indexOf("/");
			String num = input.substring(underScore + 1, slash);
			int numerator = Integer.parseInt(num);
			if (realNumber < 0)
			{
				numerator = numerator * -1;
			}
			String deno = input.substring(slash + 1);
			int denominator = Integer.parseInt(deno);
			int operand = denominator * realNumber + numerator;
			String output = operand + "/" + denominator;
			return output;
		}
	}

	//takes the left operand, the operator and the right operand and gives an answer.
	public static String calculate(String left, String operator, String right)
	{
		int leftSlash = left.indexOf("/"); 						//slash in left operand.
		String leftNum = left.substring(0, leftSlash); 			//the numerator of the left operand.
		int leftNumerator = Integer.parseInt(leftNum); 			//turn the numerator of the left operand into integer.
		String leftDeno = left.substring(leftSlash + 1); 		//the denominator of the left operand.
		int leftDenominator = Integer.parseInt(leftDeno);		//turn the denominator of the left operand into integer.
		
		int rightSlash = right.indexOf("/");					//slash in right operand.
		String rightNum = right.substring(0, rightSlash);		//the numerator of the right operand.
		int rightNumerator = Integer.parseInt(rightNum);		//turn the numerator of the right operand into integer.
		String rightDeno = right.substring(rightSlash + 1);		//the denominator of the right operand.
		int rightDenominator = Integer.parseInt(rightDeno);		//turn the denominator of the right operand into integer.
		int numerator =0;
		int denominator = 0;
		
		if (operator.equals("+"))
		{
			numerator = leftNumerator*rightDenominator + rightNumerator*leftDenominator ;		//calculate the numerators.
			denominator = leftDenominator * rightDenominator;									//calculate the denominators.
		}
		if (operator.equals("-"))
		{
			numerator = leftNumerator*rightDenominator - rightNumerator*leftDenominator;		//calculate the numerators.
			denominator = leftDenominator * rightDenominator;									//calculate the denominators.
		}
		if (operator.equals("*"))
		{
			numerator = leftNumerator * rightNumerator;											//calculate the numerators.
			denominator = leftDenominator * rightDenominator;									//calculate the denominators.
		}
		if (operator.equals("/"))
		{
			numerator = leftNumerator * rightDenominator;										//calculate the numerators.
			denominator = leftDenominator *rightNumerator;										//calculate the denominators.
		}
		String fractionAnswer = numerator + "/" + denominator;
		return fractionAnswer;
	}
	
	public static String reduce(String fraction)
	{
		int slash = fraction.indexOf("/");
		String num = fraction.substring(0, slash);
		int numerator = Integer.parseInt(num);
		String deno = fraction.substring(slash + 1);
		int denominator = Integer.parseInt(deno);
		if(numerator < 0)
		{
			int n = numerator * -1;
			int greatestSoFar = 1;
			for (int i = 1; i <= n; i++)
			{
				if (n % i == 0 && denominator % i == 0)
				{
					greatestSoFar = i;
				}
			}
			n = n / greatestSoFar;
			numerator = n * -1;
			denominator = denominator / greatestSoFar;
			String fractionAnswer = numerator + "/" + denominator;
			return fractionAnswer;
		}
		else
		{
			int greatestSoFar = 1;
			for (int i = 1; i <= numerator; i++)
			{
				if (numerator % i == 0 && denominator % i == 0)
				{
					greatestSoFar = i;
				}
			}
			numerator = numerator / greatestSoFar;
			denominator = denominator / greatestSoFar;
			String fractionAnswer = numerator + "/" + denominator;
			return fractionAnswer;
		}
	}
	
	public static String convertToMixed(String fraction)
	{
		int slash = fraction.indexOf("/");
		String num = fraction.substring(0, slash);
		int numerator = Integer.parseInt(num);
		String deno = fraction.substring(slash + 1);
		int denominator = Integer.parseInt(deno);
		if (denominator == 1)
		{
			String convertAnswer = Integer.toString(numerator);
			return convertAnswer;
		}
		if (numerator < 0)
		{
			numerator = numerator * -1;
			int divide = numerator / denominator;
			int realNumber = numerator % denominator;
			divide = divide * -1;
			String convertAnswer = divide + "_" + realNumber + "/" + denominator;
			return convertAnswer;
		}
		if (numerator > denominator)
		{
			int divide = numerator / denominator;
			int realNumber = numerator % denominator;
			String convertAnswer = divide + "_" + realNumber + "/" + denominator;
			return convertAnswer;
		}
		return fraction;
	}
	
	public static void main(String[] args)
	{
		System.out.println("Welcome to the Fraction Calculator!");
		Scanner calc = new Scanner(System.in);
		System.out.print("Enter an expression (or \"quit\"): ");
		String numbers = calc.nextLine();
		while (!(numbers.equals("quit")))
		{
			int space = numbers.indexOf(" ");
			String leftOperand = numbers.substring(0, space);								//find the left operand.
			String leftFraction = convertToFraction(leftOperand);							//convert the left operand into fraction.
			int secondSpace = numbers.lastIndexOf(" ");
			String operator = numbers.substring(space + 1, secondSpace);
			String rightOperand = numbers.substring(secondSpace + 1, numbers.length());		//find the right operand.
			String rightFraction = convertToFraction(rightOperand);							//convert the right operand into fraction.
			String answer = calculate(leftFraction, operator, rightFraction);				//calculate the two operands.
			String reducedAnswer = reduce(answer);
			String finalAnswer = convertToMixed(reducedAnswer);
			System.out.println(finalAnswer);
			System.out.print("Enter an expression (or \"quit\"): ");
			numbers = calc.nextLine();
		}
		System.out.print("Goodbye!");
	}
}
