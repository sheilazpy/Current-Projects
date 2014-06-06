/**	
 * File Name: SpeedTest.java
 *	
 *	Project: Rounding Test
 *	
 *	Description: A test of the execution speeds of various rounding methods.
 *
 *	Author: Erik Anderson
 *
 *	Date: 6/6/14
 */
package roundingtest;

import java.math.*;
import java.text.DecimalFormat;

public class SpeedTest {

	public static void main(String[] args) {
		double x, y;
		String z;
		BigDecimal bd;
		DecimalFormat df;
		int i;

		long roundTime = System.currentTimeMillis();
		for (i = 0; i < 1000000; i++) {
			x = i / 1000000.0;
			y = Math.round(x * 1000.0) / 1000.0;
			z = Double.toString(y);
		}
		roundTime = System.currentTimeMillis() - roundTime;
		System.out.println(roundTime);

		long floorTime = System.currentTimeMillis();
		for (i = 0; i < 1000000; i++) {
			x = i / 1000000.0;
			y = Math.floor(x * 1000.0 + 0.5) / 1000.0;
			z = Double.toString(y);
		}
		floorTime = System.currentTimeMillis() - floorTime;
		System.out.println(floorTime);

		long bdTime = System.currentTimeMillis();
		for (i = 0; i < 1000000; i++) {
			x = i / 1000000.0;
			bd = new BigDecimal(x).setScale(3, RoundingMode.HALF_UP);
			y = bd.doubleValue();
			z = bd.toString();
		}
		bdTime = System.currentTimeMillis() - bdTime;
		System.out.println(bdTime);

		long bdValueTime = System.currentTimeMillis();
		for (i = 0; i < 1000000; i++) {
			x = i / 1000000.0;
			bd = BigDecimal.valueOf(x).setScale(3, RoundingMode.HALF_UP);
			y = bd.doubleValue();
			z = bd.toString();
		}
		bdValueTime = System.currentTimeMillis() - bdValueTime;
		System.out.println(bdValueTime);

		long dfTime = System.currentTimeMillis();
		df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.HALF_UP);
		for (i = 0; i < 1000000; i++) {
			x = i / 1000000.0;
			z = df.format(x);
			y = Double.parseDouble(z);
		}
		dfTime = System.currentTimeMillis() - dfTime;
		System.out.println(dfTime);

		long dfValueTime = System.currentTimeMillis();
		df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.HALF_UP);
		for (i = 0; i < 1000000; i++) {
			x = i / 1000000.0;
			z = df.format(x);
			y = Double.valueOf(z);
		}
		dfValueTime = System.currentTimeMillis() - dfValueTime;
		System.out.println(dfValueTime);

	}

}
