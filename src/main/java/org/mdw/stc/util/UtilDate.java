package org.mdw.stc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class UtilDate {

	public static String getDateHour() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date fecha = new Date();

			return format.format(fecha);
		} catch (Exception e) {
			return format.format(new Date());
		}
	}

	public static String getDayCurrent() {
		try {
			Date dNow = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(dNow);
			Integer day = cal.get(Calendar.DAY_OF_WEEK);

			return String.valueOf(day.equals(0) ? 6 : day);
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public static boolean betweenTime(String startTime, String endTime) {
		boolean isBetween = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		try {

			Calendar now = Calendar.getInstance();
			String timeCurrent = now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE);

			Date time1 = dateFormat.parse(startTime);
			Date time2 = dateFormat.parse(endTime);
			Date d = dateFormat.parse(timeCurrent);

			if (time1.before(d) && time2.after(d)) {
				isBetween = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return isBetween;
	}

	public static String refactTime(String t) {
		String time = "";
		String[] f = t.split(":");
		try {
			Integer d = 12;
			d += Integer.valueOf(f[0]);

			if (d.equals(24))
				d = 0;

			time = String.format("%-2s", d).replace(' ', '0');
		} catch (Exception e) {
			// TODO: handle exception
			// System.out.println("refactTime->" + e.getMessage());
			time = "00";
		}
		return time + ":" + f[1] + ":" + f[2];
	}

	public static Date convertDate(String d) {
		try {
			String[] sd = d.split(" ");
			String[] dc = sd[0].split("-");
			String date = "";

			for (int i = dc.length - 1; i >= 0; i--) {
				date += dc[i] + "-";
			}

			date = date.substring(0, date.length() - 1);
			String newDate = date + " " + refactTime(sd[1]);

			SimpleDateFormat _fi = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			return _fi.parse(newDate);
		} catch (Exception e) {
			// TODO: handle exception
			// System.out.println("convertDate->" + e.getMessage());
			return new Date();
		}
	}

	public static Date convertDateMongo(String _date) {
		try {
			String format = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'";
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = f.parse(_date + " 00:00:00");
			String _d = new SimpleDateFormat(format).format(date);
//			System.out.println(_d);

			Instant instant = Instant.parse(_d); // Pass your date.
			Date dd = Date.from(instant);
//			Date dd = new SimpleDateFormat(format).parse(_d);
//			System.out.println(dd);
			return dd;
		} catch (Exception e) {
			// TODO: handle exception
			return new Date();
		}
	}

	public static Date convertDateMongo(String _date, String time) {
		try {
			String format = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'";
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = f.parse(_date + time);
			String _d = new SimpleDateFormat(format).format(date);
//			System.out.println(_d);
			Date dd = new SimpleDateFormat(format).parse(_d);
//			System.out.println(dd);
			return dd;
		} catch (Exception e) {
			// TODO: handle exception
			return new Date();
		}
	}

	public static Date getCurrentDate() {
		try {
			String _f = "yyyy-MM-dd HH:mm:ss.SSS";
			String _date = new SimpleDateFormat(_f).format(new Date());
			return new SimpleDateFormat(_f).parse(_date);
		} catch (Exception e) {
			// TODO: handle exception
			return new Date();
		}
	}

}
