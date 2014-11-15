/*
 * Birbeck MSc Computer Science PiJ Coursework Two
 * author Oliver S. Smart
 * date from 15 Nov 2014
 *  
 * Based on:
 * "Created by keith for the second coursework assignment."
 *
 * Performs fraction calculations.
 */
public class Fraction {
	private int numerator;
	private int denominator;

	public Fraction(int num, int denom) throws IllegalArgumentException {
		if (denom == 0) {
			throw new IllegalArgumentException("Invalid fraction with denominator 0.");
		}
		int gcd = GreatestCommonDivisor(num, denom);
		setNumerator(num / gcd);
		setDenominator(denom / gcd);
	}

	@Override
	public String toString() {
		String resultStr = "" + getNumerator();
		if (getDenominator() != 1) {	
			resultStr += "/" + getDenominator();
		} 
		return resultStr;
	}

	public int getNumerator() {
		return numerator;
	}

	public void setNumerator(int num) {
		numerator = num;
	}

	public int getDenominator() {
		return denominator;
	}

	public void setDenominator(int num) {
		denominator = num;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Fraction fraction = (Fraction) o;

		if (getDenominator() != fraction.getDenominator())
			return false;
		if (getNumerator() != fraction.getNumerator())
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = numerator;
		result = 31 * result + denominator;
		return result;
	}

	public Fraction multiply(Fraction other) {
		int num = this.getNumerator() * other.getNumerator();
		int denom = this.getDenominator() * other.getDenominator();
		return new Fraction(num, denom);
	}

	public Fraction add(Fraction other) {
		int num = this.getNumerator()*other.getDenominator();
                num += other.getNumerator()*this.getDenominator();
		int denom = this.getDenominator() * other.getDenominator();
		return new Fraction(num, denom);
	}

	public Fraction subtract(Fraction other) {
		int num = this.getNumerator()*other.getDenominator();
                num -= other.getNumerator()*this.getDenominator();
		int denom = this.getDenominator() * other.getDenominator();
		return new Fraction(num, denom);
	}

	public Fraction divide(Fraction other) {
		int num = this.getNumerator() * other.getDenominator(); 
		int denom = this.getDenominator() * other.getNumerator();
		return new Fraction(num, denom);
	}

	public Fraction AbsValue() {
                int num = Math.abs(this.getNumerator());
                int denom =  Math.abs(this.getDenominator());
                return new Fraction(num, denom);
        }

	public Fraction Negate() {
                int num =  -this.getNumerator();
                int denom =  this.getDenominator();
                return new Fraction(num, denom);
        }

	private int GreatestCommonDivisor(int a, int b) {
		/* finds GreatestCommonDivisor by Euclid's method
		   http://en.wikipedia.org/wiki/Greatest_common_divisor#Using_Euclid.27s_algorithm
                 */
		while (b != 0) {
			int t = b;
			b = a % b;
			a = t;
		}
		return a;
	}
}
