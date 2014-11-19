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
		// The required function. 
                // use my take on a fractional calculator as it is written and tested
		FracCalcOliver myFCO = new FracCalcOliver();
		myFCO.process(("" + fraction)); // supply input fraction as a String (ugly but works)
		myFCO.process(inputString); // then user line
		foundError = myFCO.foundError();
		if (foundError) {
			/* Directly print the "appropriate error message" mentioned on page 2 */
			System.out.println(myFCO.outputString());
		}
		quitProgram = myFCO.quitProgram();
		return myFCO.getFraction();
	}

	public static void main( String args[]) {
		launchBasicTextCalculator();
	}
	public static void launchBasicTextCalculator() {
		System.out.println("Simple text-based calculator to compute with fractions.\n" +
			"author: Oliver S. Smart");
		Fraction initialFraction = new Fraction(0,1);
		Fraction currentFraction = initialFraction;
		FractionCalculator myFractCalc = new FractionCalculator();
		while (true) {
			System.out.print("Enter command: ");
			try {
				String userInput = System.console().readLine();
				/* required to quit cleanly on "end of input exception"
			 	* I think this means after the user enters EOF
			 	* (ctrl-D in linux, ...).
			 	* This does not produce an "exception" but rather results in
			 	* an null string.
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
			} catch ( Exception e) { // any exception from ReadLine
					System.out.println("Error"); // required to use bareword Error
					currentFraction = initialFraction;
			}
		}
		System.out.println("Goodbye"); 
	}


}
