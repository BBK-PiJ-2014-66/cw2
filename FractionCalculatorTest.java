/*
 * Birbeck MSc Computer Science PiJ Coursework Two
 * author: Oliver S. Smart
 * date: from 15 Nov 2014
 *  
 * Runs tests of FractionCalculator function and provides an 
 * assert type test.
 *  
 */ 
public class FractionCalculatorTest{
	public static void main( String args[]) {
		simpleTests( true); // run in verbose mode
	}

	public static boolean simpleTests( boolean beVerbose) {
		if (beVerbose)
                         System.out.println("FractionTestCalculator tests verbose output:");
		FractionCalculator testFracCalc = new FractionCalculator();

		testFracCalc.process("quit");
		return true; // no error found
	}

}
