/*
 * Birbeck MSc Computer Science PiJ Coursework Two
 * author: Oliver S. Smart
 * date: from 15 Nov 2014
 *  
 * Runs tests of the FractionCalculator function evaluate
 *  
 */ 
public class FractionCalculatorTest{
	public static void main( String args[]) {
		simpleTests( true); // run in verbose mode
	}

	public static boolean simpleTests( boolean beVerbose) {
		if (beVerbose)
                         System.out.println("FractionCalculator verbose output:");

		int numbFails = 0;
		boolean testPass;
		String DescribeTest;
		String lineToProcess; 
		Fraction initialFraction = new Fraction(0,1);
		Fraction resultFraction;

		lineToProcess = "1/2 - 3/4 * abs ";
		DescribeTest = "Extended example table 1st line: '" + lineToProcess + "' should give '1/4'. ";
		resultFraction = FractionCalculator.evaluate( initialFraction, "lineToProcess");	
		testPass = resultFraction.equals("1/4");
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		if (numbFails==0) {
			if (beVerbose) System.out.println("FractionCalculatorTest: All tests pass");
			return true;
		} else {
			if (beVerbose) System.out.println("FractionCalculatorTest: " + numbFails + " tests FAIL.");
			assert (false) : "FractionCalculatorTest failed "+ numbFails + " tests";
			return false;
		}

	}
}
