package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		String startDate = "2016-07-30T19:16:01.001Z";
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		System.out.println(startDate);

		try {
			Date parseStartDate = dateFormat.parse(startDate);
			System.out.println(parseStartDate.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
