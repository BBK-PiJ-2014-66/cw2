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
		/* if assert is being used run the FractionTest to test Fraction
                   as argument is false then non-verbose will be 
                   silent unless there is an error */
		assert FractionTest.simpleAssertTests(false) : "FractionTest failed";
		simpleTests( true); // run in verbose mode
	}

	public static boolean simpleTests( boolean beVerbose) {
		if (beVerbose)
                         System.out.println("FractionTestCalculator tests verbose output:");

		FractionCalculator testFracCalc = new FractionCalculator();

		int numbFails = 0;
		boolean testPass;
		String DescribeTest;
		String lineToProcess; 

		lineToProcess = "1/2 + 1";
		DescribeTest = "Simple parsing and operation: input of \"" + lineToProcess + "\" should give \"3/2\". ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.outputString().equals("3/2");
		DescribeTest += "Actually \"" + testFracCalc.outputString() + "\" is output.";
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
