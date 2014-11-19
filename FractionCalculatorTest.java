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
		FractionCalculator testFC = new FractionCalculator();

		lineToProcess = "1/2 - 3/4 * abs ";
		DescribeTest = "Coursework 2 sheet 'Extended example' table 1st line start from 0/1 '" + lineToProcess 
			+ "' should give Fraction.equals 1/4. ";
		resultFraction = testFC.evaluate( initialFraction, lineToProcess);	
		testPass = resultFraction.equals(new Fraction(1,4));
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		lineToProcess = "* 8 neg";
		DescribeTest = "Test giving previous result as the input fraction '" + lineToProcess 
			+ "' should give Fraction.equals -2/1 ";
		resultFraction = testFC.evaluate( resultFraction, lineToProcess);	
		testPass = resultFraction.equals(new Fraction(-2,1));
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);


		lineToProcess = "+ 4 abs qUiT  neg";
		DescribeTest = "Test entry of quit in mixed case  '" + lineToProcess 
			+ "' should give .quitProgram()==true";
		resultFraction = testFC.evaluate( resultFraction, lineToProcess);	
		testPass = testFC.quitProgram();
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);



		lineToProcess = "1 - - 1";
		DescribeTest = "Entering two operations: '" + lineToProcess + "' should result in...\n " +
			"\t (a) foundError() providing true to caller\n" +
			"\t (b) direct print out of an appropriate error message. So the next message\n" +
			"\t     should be an ERROR message! (This check must be by \"eye\".)";
		System.out.println("Now start test '"+ DescribeTest + "'");
		resultFraction = testFC.evaluate( initialFraction, lineToProcess);	
		DescribeTest = "Entering two operations: '" + lineToProcess +"' foundError()==true";
		testPass = testFC.foundError();
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
