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
	private String rememberedOperation;

 	private static final String MULTIPLY = "*";
	private static final String DIVISION = "/";
	private static final String ADDITION = "+";
	private static final String SUBTRACT = "-";
	private static final String NONE = "";

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
		/* need to parse inputString as space separated "words".
		   Could write a DIY for loop to go through the String character by character
		   but I want a method like perl split to split a string into an array of words
                   on a regular expression. Use String .split(regex) useful page:
		   http://www.vogella.com/tutorials/JavaRegularExpressions/article.html
                   */
		quitProgram = false;
		foundError = false;
		outputString = ""; 
		/* uncomment following line if we want to have a calculator that does not carry 
                   memory for current operation between lines (as in the exercise sheet) */ /*
                rememberedOperation = NONE; */
		String[] words = inputString.split("\\s+"); // one or more white space characters
		for (int wc=0; wc < words.length; wc++) {
			this.processAWord(words[wc]);
			if (foundError) 
				resetCalculator();
			if (foundError || quitProgram) 
				return;
		}
		// normally return string of the value in the calculator
		outputString = valueInCalculator.toString(); 
		return;
	}

	private void processAWord( String word) {
		if (word.length() == 0) { // ignore any blank words
		} else if (this.operatorProcess(word)) {
		} else if (this.fractionProcess(word)) {
		} else if (this.wholeNumberProcess(word)) {
		} else if (this.absProcess(word)) {
		} else if (this.negProcess(word)) {
		} else if (this.clearProcess(word)) {
		} else if (this.quitProcess(word)) {
		} else {
			this.unRecognized(word);
		}
	}

	private boolean operatorProcess( String word) {
		/* recognizes an operators, process appropriately 
		   and return true */
		if ( word.equals(MULTIPLY) || word.equals(DIVISION) ||
		     word.equals(ADDITION) || word.equals(SUBTRACT) ) {
			if (!rememberedOperation.equals(NONE)) {
				outputString = "\nERROR you have input two operators: ";	
				outputString += rememberedOperation + " and ";
				outputString += word + ". ";
				foundError = true;
			}
			rememberedOperation = word;
			return true;
		}
		return false;
	}

	private boolean fractionProcess( String word) {
		if (word.matches("-?\\d+/\\d+")) { // 0 or 1 - followed by one or more digits, followed by /, ... and another number
			/* now want to get the numerator and denominator use a split on /
			   to get array and Integer.parseInt to convert. I think the regular expression
                           should mean that no errors can occur (famous last words */
			int numerator = Integer.parseInt(word.split("/")[0]); 
			int denominator = Integer.parseInt(word.split("/")[1]); 
			// denominator not allowed to be zero
			if (denominator== 0) {
				outputString = "\nERROR you have specified a fraction " + word + " with a zero denominator.";	
				foundError = true;
			} else {
				// use a separate function to actually deal with the fraction as need to do same will whole number
				dealWithFraction(  new Fraction( numerator, denominator));	
			}
			return true;	
		}
		return false;
	}
	private void dealWithFraction( Fraction inFraction) {
		if (!rememberedOperation.equals(NONE)) {
			// apply operator
			if (rememberedOperation.equals(MULTIPLY)) {
				valueInCalculator = valueInCalculator.multiply(inFraction);
			} else if (rememberedOperation.equals(DIVISION)) {
				if (inFraction.equals(new Fraction(0,1)) ) {
					outputString = "\nERROR you cannot divide by zero.";	
					foundError = true;
					return;
				}
                                valueInCalculator = valueInCalculator.divide(inFraction);
                        } else if (rememberedOperation.equals(ADDITION)) {
                                valueInCalculator = valueInCalculator.add(inFraction);
                        } else if (rememberedOperation.equals(SUBTRACT)) {
                                valueInCalculator = valueInCalculator.subtract(inFraction);
                        } else {
				assert (false) : "internal logic ERROR unrecognized rememberedOperation= " + rememberedOperation;
			}
			rememberedOperation = NONE; // clear operator
		} else {
			valueInCalculator = inFraction;
		}

	}

	private boolean wholeNumberProcess( String word) {
		if (word.matches("-?\\d+")) { // 0 or 1 - followed by one or more digits
			int numerator = Integer.parseInt(word); 
			dealWithFraction(  new Fraction( numerator, 1));	
			return true;	
		}
		return false;
	}

	private boolean absProcess( String word) {
		if (matchCaseInsensitiveAbbrev("abs",word)) {
			valueInCalculator = valueInCalculator.absValue();	
			return true;
		}
		return false;
	}

	private boolean negProcess( String word) {
		if (matchCaseInsensitiveAbbrev("neg",word)) {
			valueInCalculator = valueInCalculator.negate();	
			return true;
		}
		return false;
	}

	private boolean clearProcess( String word) {
		if (matchCaseInsensitiveAbbrev("clear",word)) {
			valueInCalculator = new Fraction(0, 1);
			rememberedOperation = NONE;
			return true;
		}
		return false;
	}

	private boolean quitProcess( String word) {
		if (matchCaseInsensitiveAbbrev("quit",word)) {
			System.out.println("debug need to write code for quit ....");
			return true;
		}
		return false;
	}

	private void unRecognized( String word) {
		outputString = "\nERROR unrecognized input '" + word + "' cannot process this";	
		foundError = true;
	}

	private void resetCalculator() {
		valueInCalculator = new Fraction(0, 1);
		rememberedOperation = NONE;
		outputString += "\n\tResetting calculator to its initial state (no operator and 0 as value)";
	}

	private static boolean matchCaseInsensitiveAbbrev( String key,  String word) {
		/* Returns true if word matches key (no regex allowed). 
		 * The match is case insensitive and abbreviation is allowed.
		 * So if key="abs" then get match for words:
                 * "A", "a", "AB", "aB", "Ab", ...                            
                 */
		key = key.toLowerCase();
		word = word.toLowerCase();
		if (word.length() < key.length()) {
			key = key.substring(0,word.length());
		}
		if (word.equals(key))
			return true;
		return false;
	}

}
