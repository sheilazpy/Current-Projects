/**	
 * File Name: StringAccuracy.java
 *	
 *	Project: Rounding Test
 *	
 *	Description: A test of the accuracy of various rounding methods for output formatting.
 *
 *	Author: Erik Anderson
 *
 *	Date: 6/6/14
 */
package roundingtest;

import java.math.*;
import java.text.DecimalFormat;

public class StringAccuracy {

	public static void main(String[] args) {
		double x, r, f;
		String d;
		BigDecimal bd, bdv;
		DecimalFormat df;
		int re = 0, fe = 0, bde = 0, bdve = 0;

		df = new DecimalFormat("0.0##");
		df.setRoundingMode(RoundingMode.HALF_UP);

		for (int i = 0; i < 10000; i++) {
			x = i / 10000.0;
			r = Math.round(x * 1000.0) / 1000.0;
			f = Math.floor(x * 1000.0 + 0.5) / 1000.0;
			bd = new BigDecimal(x).setScale(3, RoundingMode.HALF_UP);
			bdv = BigDecimal.valueOf(x).setScale(3, RoundingMode.HALF_UP);
			d = df.format(x);
			if (!d.equals(Double.toString(r))) {
				re++;
				System.out.println(x + " round " + r);
			}
			if (!d.equals(Double.toString(f))) {
				fe++;
				System.out.println(x + " floor " + f);
			}
			if (!d.equals(bd.toString())) {
				bde++;
				System.out.println(x + " new BD " + bd);
			}
			if (!d.equals(bdv.toString())) {
				bdve++;
				System.out.println(x + " BD.valueOf " + bdv);
			}
		}
		System.out.println("Round errors: " + re);
		System.out.println("Floor errors: " + fe);
		System.out.println("New BD errors: " + bde);
		System.out.println("BD.valueOf errors: " + bdve);
	}

}
