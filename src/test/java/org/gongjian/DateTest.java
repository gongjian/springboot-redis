package org.gongjian;

import java.util.Calendar;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		getDate2();
	}

	private static void getDate1() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		if (!(cal.get(Calendar.HOUR_OF_DAY) == 0 && cal.get(Calendar.MINUTE) < 10)) {
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 10);
		cal.set(Calendar.SECOND, 0);

		System.out.println(cal.getTime());
	}

	private static void getDate2() {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, -1);

		System.out.println(cal.getTime());
	}

}
