/*
 * Birbeck MSc Computer Science PiJ Coursework Two
 * author: Oliver S. Smart
 * date: from 15 Nov 2014
 *  
 * Runs tests of FracCalcOliver function and provides an 
 * assert type test.
 *  
 */ 
public class FracCalcOliverTest{
	public static void main( String args[]) {
		/* if assert is being used run the FractionTest to test Fraction
                   as argument is false then non-verbose will be 
                   silent unless there is an error */
		assert FractionTest.simpleAssertTests(false) : "FractionTest failed";
		simpleTests( true); // run in verbose mode
	}

	public static boolean simpleTests( boolean beVerbose) {
		if (beVerbose)
                         System.out.println("FracCalcOliverTest tests verbose output:");

		FracCalcOliver testFracCalc = new FracCalcOliver();

		int numbFails = 0;
		boolean testPass;
		String DescribeTest;
		String lineToProcess; 

		lineToProcess = "1/2 * 3/4";
		DescribeTest = "Simple parsing and operation: input of '" + lineToProcess + "' should give '3/8'. ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.outputString().equals("3/8");
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		lineToProcess = "1/2 / 3/4";
		DescribeTest = "Simple parsing and operation: input of '" + lineToProcess + "' should give '2/3'. ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.outputString().equals("2/3");
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		lineToProcess = " 1/2   +  -1/4 ";
		DescribeTest = "Simple parsing and operation: input of '" + lineToProcess + "' should give '1/4'. ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.outputString().equals("1/4");
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		lineToProcess = "1/2 - 3/4";
		DescribeTest = "Simple parsing and operation: input of '" + lineToProcess + "' should give '-1/4'. ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.outputString().equals("-1/4");
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		lineToProcess = "-2/-3";
		DescribeTest = "Handling fractions with -ve denominator: input of '" + lineToProcess + "' should give '2/3'. ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.outputString().equals("2/3");
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		lineToProcess = "3 * 9";
		DescribeTest = "Whole numbers: input of '" + lineToProcess + "' should give '27'. ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.outputString().equals("27");
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		lineToProcess = "-3 * 2/3 aB";
		DescribeTest = "Abs: input of '" + lineToProcess + "' should give '2'. ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.outputString().equals("2");
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		lineToProcess = "3/7 N";
		DescribeTest = "Neg: input of '" + lineToProcess + "' should give '-3/7'. ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.outputString().equals("-3/7");
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		lineToProcess = "1/2 - Cl  9 * 7";
		DescribeTest = "Clear: input of '" + lineToProcess + "' should give '63'. ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.outputString().equals("63");
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		lineToProcess = "1/2 - quit 30 + 10";
		DescribeTest = "Quit: input of '" + lineToProcess + "' should result in quitProgram() true. ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.quitProgram();
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);


		lineToProcess = "1/2+1/2";
		DescribeTest = "Invalid input no space: input of '" + lineToProcess + "' should produce an ERROR. ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.foundError();
		DescribeTest += "Actually '" + testFracCalc.outputString() + "' is output.";
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		lineToProcess = "1/2 + - 3/4";
		DescribeTest = "Invalid input two operators : input of '" + lineToProcess + "' should produce an ERROR. ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.foundError();
		DescribeTest += "Actually '" + testFracCalc.outputString() + "' is output.";
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		lineToProcess = "1/0";
		DescribeTest = "Entering an invalid fraction input of '" + lineToProcess + "' should produce an ERROR. ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.foundError();
		DescribeTest += "Actually '" + testFracCalc.outputString() + "' is output.";
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		lineToProcess = "1/2 / 0/3";
		DescribeTest = "Try to divide by zero '" + lineToProcess + "' should produce an ERROR. ";
		testFracCalc.process(lineToProcess);
		testPass = testFracCalc.foundError();
		DescribeTest += "Actually '" + testFracCalc.outputString() + "' is output.";
		numbFails += FractionTest.likeAssert( testPass, DescribeTest, beVerbose);

		if (numbFails==0) {
			if (beVerbose) System.out.println("FracCalcOliverTest: All tests pass");
			return true;
		} else {
			if (beVerbose) System.out.println("FracCalcOliverTest: " + numbFails + " tests FAIL.");
			assert (false) : "FracCalcOliverTest failed "+ numbFails + " tests";
			return false;
		}


	}

}
