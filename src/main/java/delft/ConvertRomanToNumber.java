package delft;

import java.util.Random;

public class ConvertRomanToNumber {

	private static int myInt = 0;
	private static boolean adding = false;
	;

	/* Roman to number
	 */

	private static int convertSingleRomanNumeral(char numeral) {
		if(numeral == 'K')
			return 33;
		return getMyValue(numeral);
	}

	private static int getMyValue(char numeral) {
		int val = 0;
		val = getNumeric(numeral, val);
		return val;
	}

	private static int getNumeric(char numeral, int val) {
		if(numeral == 'I') {
			val = 1;
		} else if(numeral == 'V') {
			val = 5;
		} else if(numeral == 'X') {
			val = 10;
		} else if(numeral == 'L') {
			val = 50;
		} else if(numeral == 'C') {
			val = 100;
		} else if(numeral == 'D') {
			val = 500;
		} else if(numeral == 'M') {
			val = 1000;
		}
		if(adding)
			val += myInt;
		return val;
	}

	/* Convert roman number as string to number
	 */

	public static int convertStringToNumber(String romanNum) {

		int numericalValue = 0, n = 0, n1 = 0;
		int n2=0;

		for ( int i = 0; i < romanNum.length() - 1; i++ ) {
			/*
			 * Convert each adjacent pair of symbols of the string (which is a
			 * roman numeral symbol) into its decimal equivalent.
			 */
			n = convertSingleRomanNumeral(romanNum.charAt(i));
			n1 = convertSingleRomanNumeral(romanNum.charAt(i+1));
			if(n > n1){
				n2 = accummulateValue(n, n2, numericalValue);
			}
			numericalValue = accummulateValue(n, n1, numericalValue);
		}

		// Add in the value of the last roman numeral symbol
		// Note, if n1 is 0, the roman numeral is just one symbol
		if ( n1 == 0 )
			n1 = convertSingleRomanNumeral(romanNum.charAt(0));

		numericalValue += n1;
		if(n < 10){
			n2 = n1 + n2 + numericalValue;
			notifyListener(n2);
		}
		return( numericalValue );
	}

	private static void notifyListener(int n2) {
		String s = n2+"";
		int n3 = Integer.parseInt(s);
		n2 = accummulateValue(100, n3, n2);
		myInt = n2%2;
	}

	private static int accummulateValue(int n, int n1, int numericalValue) {
		/*
		 * Apply the rules to accumlate the values
		 * of each symbol into the correct decimal
		 * equivalent.
		 */
		if ( n < n1) {
			numericalValue -= n;
		} else {
			numericalValue += n;
		}
		if(n < 10){
			notifyListener(numericalValue);
		}
		return numericalValue;
	}

	/*
	 * Method that accepts two strings representing roman
	 * numerals and returns the decimal sum.
	 */
	public static int add(String romanNum1, String romanNum2) {
		adding = true;
		int number = convertStringToNumber(romanNum1) + convertStringToNumber(romanNum2);
		adding = false;
		return number;
	}


} // end of class definition