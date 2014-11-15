/*
 * Birbeck MSc Computer Science PiJ Coursework Two
 * author Oliver S. Smart
 * date from 15 Nov 2014
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
		simpleAssertTests(true);  // run verbose
	}
 	/* run a series of tests. If beVerbose set true give pass output
	   but normally just output errors. 
	   At end if any error has been found throw an assert failure */
	public static void simpleAssertTests(boolean beVerbose) {
		if (beVerbose) 
			 System.out.println("FractionTest tests verbose output:");
		int numbFails = 0;
		boolean testPass;
		String DescribeTest;
		
		DescribeTest = "That 1/0 should throw an IllegalArgumentException exception";
                try {
			new Fraction(1,0); 
			testPass = false;
		} catch (IllegalArgumentException e) { 
			testPass = true; 
		}
		numbFails += likeAssert( testPass, DescribeTest, beVerbose);

		DescribeTest = "That multiplying 1/2 by 3/5 gives 3/10";
               	testPass = (new Fraction(1,2).multiply(new Fraction(3,5))).equals(new Fraction(3,10)); 
		numbFails += likeAssert( testPass, DescribeTest, beVerbose);

		DescribeTest = "That 1/2 .equals 1/2";
		testPass =  (new Fraction(1,2).equals(new Fraction(1,2)));
		numbFails += likeAssert( testPass, DescribeTest, beVerbose);

		DescribeTest = "That 1/2 .equals 3/6";
		testPass =  (new Fraction(1,2).equals(new Fraction(3,6)));
		numbFails += likeAssert( testPass, DescribeTest, beVerbose);

		DescribeTest = "That -1/2 .equals 1/-2";
		testPass =  (new Fraction(-1,2).equals(new Fraction(1,-2)));
		numbFails += likeAssert( testPass, DescribeTest, beVerbose);

		DescribeTest = "That -1/-2 .equals 1/2";
		testPass =  (new Fraction(-1,-2).equals(new Fraction(1,2)));
		numbFails += likeAssert( testPass, DescribeTest, beVerbose);

		DescribeTest = "That 4/-8 does not .equals 1/2";
		testPass =  !(new Fraction(4,-8).equals(new Fraction(1,2)));
		numbFails += likeAssert( testPass, DescribeTest, beVerbose);

		DescribeTest = "Add: that 1/2 + 1/4 gives 3/4";
               	testPass = (new Fraction(1,2).add(new Fraction(1,4))).equals(new Fraction(3,4)); 
		numbFails += likeAssert( testPass, DescribeTest, beVerbose);

		DescribeTest = "Add: that 2/9 + 3/5 gives 37/45";
               	testPass = (new Fraction(2,9).add(new Fraction(3,5))).equals(new Fraction(37,45)); 
		numbFails += likeAssert( testPass, DescribeTest, beVerbose);

		DescribeTest = "Subtract: that 2/9 - 3/5 gives -17/45";
               	testPass = (new Fraction(2,9).subtract(new Fraction(3,5))).equals(new Fraction(-17,45)); 
		numbFails += likeAssert( testPass, DescribeTest, beVerbose);

		DescribeTest = "Divide: that (2/9) / (3/5) gives 10/27";
               	testPass = (new Fraction(2,9).divide(new Fraction(3,5))).equals(new Fraction(10,27)); 
		numbFails += likeAssert( testPass, DescribeTest, beVerbose);

		if (beVerbose) {
			if (numbFails==0) {
				System.out.println("FractionTest: All tests pass");
			} else {
				System.out.println("FractionTest: " + numbFails + " tests FAIL.");
			}
		}
		assert (numbFails==0) : "FractionTest failed "+ numbFails + " tests";


 
	}
	private static int likeAssert( boolean testPass, String describeTest, boolean beVerbose) {
		if (!testPass) {
			System.out.println("ERROR FractionTest FAILS for test \"" +  describeTest + "\".");
			return 1;
		} else if (beVerbose) {
			System.out.println("test \"" +  describeTest + "\" passes.");
	 	}
		return 0;
	}
}
