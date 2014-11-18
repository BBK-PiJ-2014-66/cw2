/*
 * Birbeck MSc Computer Science PiJ Coursework Two
 * author: Oliver S. Smart
 * date: from 15 Nov 2014
 *  
 * Simple text-based calculator to compute with fractions.
 *	
 */
public class FractionCalculator {
	public static Fraction evaluate( Fraction fraction, String inputString) {
		/* The required function. Do not know whether it should be static
		 * or a class function. Reasonable to assume static as input fraction
		 * "fraction" must provide the only memory for the the calculator
                 * state in between line inputs
                 *
		 *
		 * N.B. this function will return null if any error is found 
		 *      this function will exit the program if quit is entered
		 */
		Fraction retFraction = null; // return null if there is an error


                // use my take on a fractional calculator
		FracCalcOliver myFCO = new FracCalcOliver();

		myFCO.process( ("" + fraction)); // supply input fraction as a String
		myFCO.process(inputString); // then user line

		if (myFCO.foundError()) { 
			/* Print the "appropriate error message" mentioned on page 2 */
			System.out.println(myFCO.outputString());
		} else if (myFCO.quitProgram()) {
			System.out.println("Goodbye");
			System.exit(0); // terminate program with no error
		} else {
			System.out.println("debug need to write mechanism to get Fraction back from FracCalcOliver");
			retFraction = new Fraction(0,1);
		}

		return retFraction;
	}
}
