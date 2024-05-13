package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNum() {
		Random ran = new Random();
		int ranNum = ran.nextInt(1000);
		return ranNum;

	}

	public String getSystemDateYYYYDDMM() {
		Date dateObj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String startdate = sim.format(dateObj);
		return startdate;

	}

	public String getRequiredDateYYYYDDMM(int days) {
		Date dateObj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		sim.format(dateObj);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate = sim.format(cal.getTime());
		return reqDate;// enddate

	}

}
