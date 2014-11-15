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
 * So use object variables to 
 *  - give access to the "Value in calculator" fraction
 *  - give access to any error message on an error (null means success) 
 *  - give access to a variable that says when user wants to quit
 *  - remember the complete state (including "remember operation")
 *    between different lines.
 *
 * .evaluate method should then not perform any Input or Output at all.
 *
 * This should enable better testing for instance checking that 
 *
 * Also include the Testing method in the class but invoke it from
 * FractionCalculatorTest main. As the methods and tests has to be
 * developed in parallel then it make sense to do together.
 */
public class FractionCalculator {
	private Fraction valueInCalculator;
	private String errorMessage;
	private boolean quitProgram;
	private String rememberedOperation;

 	private static final String MULTIPLY = "*";
	private static final String DIVISION = "/";
	private static final String ADDITION = "+";
	private static final String SUBTRACT = "-";

	FractionCalculator() {
		valueInCalculator = new Fraction(0, 1);
		errorMessage = null;
		rememberedOperation = null;
		quitProgram = false;
	}

	public void evaluate( String inputString) {
		System.out.println("debug call to evaluate with inputString =\"" + inputString + "\"");
		if (inputString.equals("quit")) 
			quitProgram = true;
	}

	public static void simpleTests( boolean beVerbose) {
		if (beVerbose)
                         System.out.println("FractionTestCalculator tests verbose output:");
		FractionCalculator testFracCalc = new FractionCalculator();

		testFracCalc.evaluate("quit");
		System.out.println("debug quitProgram = " + testFracCalc.quitProgram);
	}
}
