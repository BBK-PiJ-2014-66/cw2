/*
 * Birbeck MSc Computer Science PiJ Coursework Two
 * author: Oliver S. Smart
 * date: from 15 Nov 2014
 *  
 * Simple text-based calculator to compute with fractions.
 *
 */
public class FractionCalculator {
	private boolean quitProgram;
	private boolean foundError;

	public FractionCalculator() {
		quitProgram = false;
		foundError = false;
	}

	public boolean quitProgram() {
		return quitProgram;
	}

	public boolean foundError() {
		return foundError;
	}

	public Fraction evaluate( Fraction fraction, String inputString) {
		/* The required function. 
                 * use my take on a fractional calculator as it is written and tested
		 */
		FracCalcOliver myFCO = new FracCalcOliver();
		myFCO.process(("" + fraction)); // supply input fraction as a String (ugly but works)
		myFCO.process(inputString); // then user line
		foundError = myFCO.foundError();
		if (foundError) {
			// Directly print the "appropriate error message" mentioned on page 2 
			System.out.println(myFCO.outputString());
		}
		quitProgram = myFCO.quitProgram();
		return myFCO.getFraction();
	}

	public static void main( String args[]) {
		// if assert is being used test the classes used
		assert FractionTest.simpleTestsNoVerbose() : "FractionTest failed";
		assert FractionCalculatorTest.simpleTestsNoVerbose() : "FractionCalculatorTest failed";

		if ((args.length > 0) && args[0].equals("-a")) {
			FracCalcOliver.launch();
		} else {
			launchBasicTextCalculator();
		}
	}
	public static void launchBasicTextCalculator() {
		System.out.println("Simple text-based calculator to compute with fractions.\n" +
			"\tAuthor: Oliver S. Smart\n\n" +
			"\tN.B. if you want to run an alternative more-advanced\n" +
			"\tversion restart using the command line option -a\n\n" +
			"\tUsage: introduce space deliminated commands like:\n" +
			"\t  5/7 * 31/11 - 2 neg\n" +
			"\tyou can use fractions or whole numbers or one of the commands:\n" +
			"\t  abs neg clear quit               (abbreviations allowed).\n" +
			"\tThe result of the fraction is held in memory and can be used as the start of the next calculation.\n" +
			"\tTo stop the program enter quit or Ctrl-D.\n");
		Fraction initialFraction = new Fraction(0,1);
		Fraction currentFraction = initialFraction;
		FractionCalculator myFractCalc = new FractionCalculator();
		while (true) {
			System.out.print("Enter command: ");
			try {
				String userInput = System.console().readLine();
				/* required to quit cleanly on "end of input exception"
			 	 * I think this means after the user enters EOF (end of file)
			 	 * (ctrl-D in linux, ...).
			 	 * As System.console().readLine() is used https://docs.oracle.com/javase/7/docs/api/ 
                                 * says that readLine returns "A string containing the line read from the console, 
 				 * not including any line-termination characters, or null if an end of stream has 
				 * been reached.".
                                 * So entry of EOF does not result in an exception but instead in a null string.
			 	 */
				if (userInput==null) break;
				Fraction resultFraction = myFractCalc.evaluate( currentFraction, userInput);
				if (myFractCalc.quitProgram()) break;
				if (myFractCalc.foundError()) {

					currentFraction = initialFraction;
				} else {
					System.out.println("Result is " + resultFraction);
					currentFraction = resultFraction;
				}
			} catch ( Exception e) { // any exception from ReadLine or evaluate
					System.out.println("Error"); // required to use bareword "Error"
					currentFraction = initialFraction;
			}
		}
		System.out.println("Goodbye"); // required
	}
}
