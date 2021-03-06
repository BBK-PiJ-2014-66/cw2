/* 
 * Birbeck MSc Computer Science PiJ Coursework Two
 * author: Oliver S. Smart
 * date: from 15 Nov 2014
 *  
 * Based on:
 * "Created by keith for the second coursework assignment."
 *
 * Runs test on the Fraction class. 
 *
 * 	- Implement afresh using a method rather than in main. 
 *	  Idea is to allow tests to be run in programs that use the 
 *	  class during development. If any problems found then throw
	  an assert failure as this allows user to turn on/off.
 *	- Replace original "test" method taking two equalities
 *	  with one that is like assert that takes a boolean.
 *	  this allows testing for negatives...
 * 
 */
public class FractionTest {
	public static void main(String[] args) {
		simpleTests(true);  // run verbose
	}
	public static boolean simpleTestsNoVerbose() { // for assert tests
		return simpleTests(false);  // non-verbose only printing errors.
	} 
 	/* run a series of tests. If beVerbose set true give pass output
	   but normally just output errors. 
	   At end if any error has been found throw an assert failure
           or return false */
	public static boolean simpleTests(boolean beVerbose) {
		if (beVerbose) 
			 System.out.println("FractionTest tests verbose output:");
		int numbFails = 0;
		boolean testPass;
		String DescribeTest;
		
		DescribeTest = "Divide by zero: that 1/0 should throw an IllegalArgumentException exception";
                try {
			new Fraction(1,0); 
			testPass = false;
		} catch (IllegalArgumentException e) { 
			testPass = true; 
		}
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = ".equal method: that 1/2 .equals 1/2";
		testPass =  (new Fraction(1,2).equals(new Fraction(1,2)));
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = ".equal method: that 1/2 equals 3/6";
		testPass =  (new Fraction(1,2).equals(new Fraction(3,6)));
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = ".equal method: that -1/2 equals 1/-2";
		testPass =  (new Fraction(-1,2).equals(new Fraction(1,-2)));
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = ".equal method: that -1/-2 equals 1/2";
		testPass =  (new Fraction(-1,-2).equals(new Fraction(1,2)));
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = ".equal method: that 4/-8 does not equal 1/2";
		testPass =  !(new Fraction(4,-8).equals(new Fraction(1,2)));
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = "Multiply: that multiplying 1/2 by 3/5 gives 3/10";
               	testPass = (new Fraction(1,2).multiply(new Fraction(3,5))).equals(new Fraction(3,10)); 
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = "Divide: that (2/9) / (3/5) gives 10/27";
               	testPass = (new Fraction(2,9).divide(new Fraction(3,5))).equals(new Fraction(10,27)); 
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = "Divide by zero: that (1/1) / (0/1) should throw an IllegalArgumentException exception";
                try {
			new Fraction(1,1).divide(new Fraction(0,1));
			testPass = false;
		} catch (IllegalArgumentException e) { 
			testPass = true; 
		}
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = "Add: that 1/2 + 1/4 gives 3/4";
               	testPass = (new Fraction(1,2).add(new Fraction(1,4))).equals(new Fraction(3,4)); 
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = "Add: that 2/9 + 3/5 gives 37/45";
               	testPass = (new Fraction(2,9).add(new Fraction(3,5))).equals(new Fraction(37,45)); 
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = "Subtract: that 2/9 - 3/5 gives -17/45";
               	testPass = (new Fraction(2,9).subtract(new Fraction(3,5))).equals(new Fraction(-17,45)); 
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = "absValue: that absValue(-7/9) = 7/9";
		testPass = (new Fraction(-7,9).absValue().equals(new Fraction(7,9)));
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = "negate: that negate(7/9) = -7/9";
		testPass = (new Fraction(7,9).negate().equals(new Fraction(-7,9)));
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = ".toString(): that (6/5).toString() is equal to \"6/5\" ";
		testPass =  (new Fraction(6,5).toString().equals("6/5"));
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = ".toString(): that (6/1).toString() is equal to \"6\" ";
		testPass =  (new Fraction(6,1).toString().equals("6"));
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = ".toString(): that (-1/4).toString() is equal to \"-1/4\" ";
		testPass =  (new Fraction(-1,4).toString().equals("-1/4"));
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);

		DescribeTest = ".toString(): that (1/-4).toString() is equal to \"-1/4\" ";
		testPass =  (new Fraction(1,-4).toString().equals("-1/4"));
		numbFails += printTestResults( testPass, DescribeTest, beVerbose);


		if (numbFails==0) {
			if (beVerbose) System.out.println("FractionTest: All tests pass");
			return true;
		} else {
			if (beVerbose) System.out.println("FractionTest: " + numbFails + " tests FAIL.");
			assert (false) : "FractionTest failed "+ numbFails + " tests";
			return false;
		}
	}
	public static int printTestResults( boolean testPass, String describeTest, boolean beVerbose) {
                // a function to print the result tests returns 0 for pass and 1 for fail.
		if (!testPass) {
			System.out.println("ERROR get FAIL for test \"" +  describeTest + "\".\n");
			return 1;
		} else if (beVerbose) {
			System.out.println("test \"" +  describeTest + "\" passes.\n");
	 	}
		return 0;
	}
}
