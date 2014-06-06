/**	
 * File Name: DoubleAccuracy.java
 *	
 *	Project: Rounding Test
 *	
 *	Description: A test of the precision of the double data type.
 *
 *	Author: Erik Anderson
 *
 *	Date: 6/6/14
 */
package roundingtest;

import java.math.*;
import java.text.DecimalFormat;

public class DoubleAccuracy {

	public static void main(String[] args) {
		double x, r, f, d, dv;
		BigDecimal bd, bdv, dx, dr, df, dd, ddv, mx, mr, mf, md, mdv;
		DecimalFormat dft;

		dft = new DecimalFormat("#.###");
		dft.setRoundingMode(RoundingMode.HALF_UP);
		mx = BigDecimal.ZERO;
		mr = BigDecimal.ZERO;
		mf = BigDecimal.ZERO;
		md = BigDecimal.ZERO;
		mdv = BigDecimal.ZERO;

		for (int i = 0; i < 10000; i++) {
			x = i / 10000.0;
			r = Math.round(x * 1000.0) / 1000.0;
			f = Math.floor(x * 1000.0 + 0.5) / 1000.0;
			d = Double.parseDouble(dft.format(x));
			dv = Double.valueOf(dft.format(x));
			
			bd = new BigDecimal(x);
			bdv = BigDecimal.valueOf(x);
			dx = bd.subtract(bdv).abs();
			mx = mx.max(dx);
			
			bd = new BigDecimal(r);
			bdv = BigDecimal.valueOf(r);
			dr = bd.subtract(bdv).abs();
			mr = mr.max(dr);
			
			bd = new BigDecimal(f);
			bdv = BigDecimal.valueOf(f);
			df = bd.subtract(bdv).abs();
			mf = mf.max(df);
			
			bd = new BigDecimal(d);
			bdv = BigDecimal.valueOf(d);
			dd = bd.subtract(bdv).abs();
			md = md.max(dd);
			
			bd = new BigDecimal(dv);
			bdv = BigDecimal.valueOf(dv);
			ddv = bd.subtract(bdv).abs();
			mdv = mdv.max(ddv);
		}

		System.out.println("Double error: " + mx);
		System.out.println("Round error: " + mr);
		System.out.println("Floor error: " + mf);
		System.out.println("Parse error: " + md);
		System.out.println("valueOf error: " + mdv);
	}

}
