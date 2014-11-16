/*
 * Birbeck MSc Computer Science PiJ Coursework Two
 * author: Oliver S. Smart
 * date: from 15 Nov 2014
 *  
 * Simple text-based calculator to compute with fractions.
 *
 * Going to do something a bit-different from the exercise sheet.
 * It seems a bit strange that the calculator "remembers" the
 * fraction after a new line but forgets the operation. Surely
 * it would be more elegant to remember both. Also want a clean
 * way to return after user has asked for a quit.
 * 
 * Have a void "process" object method (rather than "evaluate") this
 * will take the user user input as inputString. 
 *
 * Use methods to access object variables to: 
 *  - give access to output message outputString
 *  - give access to a boolean quitProgram that says when user wants to quit
 *  - give access to a boolean foundError. Useful for testing but not 
 *    needed for actual operation.
 * 
 * Other object variables will remember the complete state (including 
 * "remember operation") between different input lines. 
 *
 * Note there is no need for any public access to the actual resulting 
 * fraction (but it would be easy to add).
 *
 * This approach should enable full testing for instance that entering "0/1"
 * produces an error message. (The "evaluate" method described in the exercise 
 * sheet involves direct printing of error message so precluding assert testing).
 *
 */
public class FractionCalculator {
	private String outputString;
	private boolean quitProgram;
	private boolean foundError;
	private Fraction valueInCalculator;
	private char rememberedOperation;

 	private static final char MULTIPLY = '*';
	private static final char DIVISION = '/';
	private static final char ADDITION = '+';
	private static final char SUBTRACT = '-';
	private static final char NONE = 0;

	public FractionCalculator() {
		valueInCalculator = new Fraction(0, 1);
		quitProgram = false;
		foundError = false;
		outputString = ""; // avoid null because want to use .equals
		rememberedOperation = NONE;
	}

	public String outputString() {
		return outputString;
	}

	public boolean quitProgram() {
		return quitProgram;
	}

	public boolean foundError() {
		return foundError;
	}


	public void process( String inputString) {
		System.out.println("debug call to process with inputString =\"" + inputString + "\"");

		/* need to parse inputString as space separated "words"
		   go write a DIY for loop to go through the String character by character
		   but I want a method like perl split to split a string into an array of words
                   on a regular expression. Use String .split(regex) useful page:
		   http://www.vogella.com/tutorials/JavaRegularExpressions/article.html
                   */

		String[] words = inputString.split("\\s+"); // one or more white space characters
		for (int wc=0; wc < words.length; wc++) {
			String word = words[wc];
			if (word.length() == 0) 
				continue; // ignore if zero length
			System.out.println("debug word= \"" + word + "\"");
		}
		if (inputString.equals("quit")) 
			quitProgram = true;
	}
	
	
}
