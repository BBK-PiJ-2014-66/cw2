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
}
