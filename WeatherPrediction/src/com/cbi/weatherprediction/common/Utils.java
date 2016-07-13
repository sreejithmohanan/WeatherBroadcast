package com.cbi.weatherprediction.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class Utils {
	public static final String DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SSZ = "yyyy-MM-dd'T'HH:mm:ssZ";

	/**
	 * Metho dto get time in W3C format pased on the given pattern
	 *
	 * @param timeInMillis
	 *            time in millis
	 * @param pattern
	 *            pattern for returning the time format
	 * @return time in W3C format
	 */
	public synchronized static String getTimeW3C(long timeInMillis,
			String pattern) {
		String str = "---";
		SimpleDateFormat dateFormat = getDateFormat();
		try {
			dateFormat.applyPattern(pattern);
			// dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			str = dateFormat.format(new Date(timeInMillis));
			str = str.substring(0, str.length() - 2) + ":"
					+ str.substring(str.length() - 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	private synchronized static SimpleDateFormat getDateFormat() {
		return new SimpleDateFormat();
	}
	
	
	/**
     * Method to format decimal values to the specified decimal places
     *
     * @param val          value to be formatted
     * @param decimalPlace number of decimal places required
     * @return formatted value
     */
    public static double getDecimalFormat(double val, int decimalPlace) {
        double d = 0.0;
        try {
            String decimalVal = String.format("%." + decimalPlace + "f", val);
            d = Double.parseDouble(decimalVal);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return d;
    }

}
