package com.chainsys.jspproject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	public static boolean checkStringForParseInt(String data) throws InvalidInputDataException {
		boolean result = false;
		for (int i = 0; i < data.length(); i++) 
		   {
			   if (!(data.charAt(i) >= '0' && data.charAt(i) <= '9'))
			   {
				   throw new InvalidInputDataException("The value in string must contain only numbers ");   
		       }   
		}
		return result;
	}

	public static boolean checkNumberForGreaterThanZero(int data) throws InvalidInputDataException {
		boolean result = false;
		if (data <= 0) {
			throw new InvalidInputDataException("The value must be greater then zero");
		}
		return result;
	}
	public static boolean checkNumberForGreaterThanZero(float data) throws InvalidInputDataException {
		boolean result = false;
		if (data <= 0) {
			throw new InvalidInputDataException("The value must be greater then zero");
		}
		return result;
	}
	public static boolean checkCharLessThanTwenty(String data) throws InvalidInputDataException {
		boolean result = false;
		int count = 0;
		for (int i = 0; i < data.length(); i++) {
			if (data.charAt(i) != ' ') {
				count++;
			}
			if (count >= 20) {
				throw new InvalidInputDataException("The value of character must be less then twenty digit");
			}
		}
		return result;
	}

	public static void checkEmail(String data) throws InvalidInputDataException{
		boolean result=false;
	    String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	    Pattern patt=Pattern.compile(regexPattern);
		Matcher match = patt.matcher(data);
		result=match.matches();
	    if(!result) throw new InvalidInputDataException("your email does not meet required length or complex");
	}

	public static boolean checkNumberLessThanTenDigit(Float data) throws InvalidInputDataException {
		boolean result = false;
		String d = Float.toString(data);
		int count = 0;
		for (int i = 0; i < d.length(); i++) {
			if (d.charAt(i) != ' ') {
				count++;
			}
			if (count >= 10) {
				throw new InvalidInputDataException("The Number must be less then ten digit");
			}
		}
		return result;
	}

	public static boolean checkNameContainsOnlyString(String data) throws InvalidInputDataException {
		boolean result = false;
		for (int i = 0; i < data.length(); i++) {
			if (data.charAt(i) >= '0' && data.charAt(i) <= '9') {
				throw new InvalidInputDataException("The value in string must contain only alphabets ");
			}
		}
		return result;
	}

	public static void checkPhone(String data) throws InvalidInputDataException {
		boolean result = false;
		String pattern = "^[0-9]{10}$";
		Pattern patt = Pattern.compile(pattern);
		Matcher match = patt.matcher(data);
		result = match.matches();
		if (!result)
			throw new InvalidInputDataException("please enter 10 digit ");
	}
	public static void checkStringOnly(String data) throws InvalidInputDataException {
		boolean result=false;
		String pattern="^[a-zA-Z]+(\\\\s[a-zA-Z]+)?$";
		Pattern patt=Pattern.compile(pattern);
		Matcher match = patt.matcher(data);
		result=match.matches();
		if(!result) throw new InvalidInputDataException("please enter characters (String) only");
	}
	public static void checkJobId(String data) throws InvalidInputDataException{
		boolean result=false;
		String pattern="^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
		Pattern patt=Pattern.compile(pattern);
		Matcher match = patt.matcher(data);
		result=match.matches();
		if(!result) throw new InvalidInputDataException("enter String format for job id");
	}
	public static void validateDate(String data) throws InvalidInputDataException {
		String[] date = data.split("/");
		for (int i = 0; i < date.length; i++) {
			boolean result = false;
			String pattern = "^[0-9]+$";
			Pattern patt = Pattern.compile(pattern);
			Matcher match = patt.matcher(date[i]);
			result = match.matches();
			if (!result)
				throw new InvalidInputDataException("please enter date as integer");
		}

		int day = Integer.parseInt(date[0]);
		int mon = Integer.parseInt(date[1]);
		int year = Integer.parseInt(date[2]);
		if (year > 1985 || year < 2022) {
			if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
				if (day < 0 && day > 31)
					throw new InvalidInputDataException("invalid day! day Should below 31");

			} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
				if (day < 0 && day > 30)
					throw new InvalidInputDataException("invalid day! day Should below 30");
			} else if (mon == 2)
				if (day < 0 && day > 28)
					throw new InvalidInputDataException("invalid day! day should below 28");
				else
					throw new InvalidInputDataException("Enter Valid Month");
		} else
			throw new InvalidInputDataException("Enter valid year");
	}

	public static void checkDate(String date) throws InvalidInputDataException {

		String pattern1 = "^((\\d{1})|(\\d{2}))/((\\d{1})|(\\d{2}))/\\d{4}$";
		if (!match(pattern1, date))
			throw new InvalidInputDataException("date must be number");

		String pattern2 = "^((\\d{1})|(\\d{2}))/(1[3-9]|[2-9][0-9])/\\d{4}$";
		if (match(pattern2, date))
			throw new InvalidInputDataException("month must be below 12");

		String pattern3 = "^((\\d{1})|(\\d{2}))/(0[13578]|1[02])/\\d{4}$";
		String pattern4 = "^(3[01]|[12][0-9]|0?[1-9])/(0?[13578]|1[02])/[0-9]{4}$";
		if (match(pattern3, date)) {
			if (!match(pattern4, date))
				throw new InvalidInputDataException("In this month day should be below 31");
		}

		String pattern5 = "^((\\d{1})|(\\d{2}))/(0?[469]|11)/(\\d{4})$";
		String pattern6 = "^((3[0]|[12][0-9]|0?[1-9]))/(0?[469]|11)/\\d{4}$";
		if (match(pattern5, date)) {
			if (!match(pattern6, date))
				throw new InvalidInputDataException("In this month day should be below 30");
		}

		String pattern7 = "((\\d{1})|(\\d{2}))/(2|02)/(([0-9][0-9][24680][048])|([0-9][0-9][13579][26]))$";
		String pattern8 = "^(([1][0-9]|0?[1-9])|(2[0-9]))/(2|02)/(([0-9][0-9][24680][048])|([0-9][0-9][13579][26]))$";
		if (match(pattern7, date))
			if (!match(pattern8, date))
				throw new InvalidInputDataException("In this month day should be below 29");

		String pattern9 = "((\\d{1})|(\\d{2}))/(2|02)/([0-9][0-9](([24680][1235679])|([13579][48013579])))$";
		String pattern10 = "^(([1][0-9]|0?[1-9])|(2[0-8]))/(2|02)/([0-9][0-9](([24680][1235679])|([13579][48013579])))$";
		if (match(pattern9, date))
			if (!match(pattern10, date))
				throw new InvalidInputDataException("In this month day should below 28");

	}

	public static void checkHireDate(String date) throws InvalidInputDataException {
		String pattern11 = "^((\\d{1})|(\\d{2}))/((\\d{1})|(\\d{2}))/((19[89][0-9])|(20([01][0-9])|20(2[0-2])))$";
		if (!match(pattern11, date))
			throw new InvalidInputDataException("hire date must be between 1980-2022");
	}

	public static void checkDOB(String date) throws InvalidInputDataException {
		String pattern12 = "^((\\d{1})|(\\d{2}))/((\\d{1})|(\\d{2}))/(((19(6[5-9])|(19[7-9][0-9]))|(200[0-2])))$";
		if (!match(pattern12, date))
			throw new InvalidInputDataException("Date of birth must between 1965 to 2002");
	}

	public static void checkAppointmentDate(String date) throws InvalidInputDataException {
		String pattern13 = "^((\\d{1})|(\\d{2}))/((\\d{1})|(\\d{2}))/(([0-1][0-9][0-9][0-9])|(20[01][0-9])|(202[01]))$";
		if (match(pattern13, date))
			throw new InvalidInputDataException("Appointment date must above 2022S");
	}

	public static void checkFloat(String data) throws InvalidInputDataException{
		String pattern="^[0-9]+(.[0-9]*)?$";
		if (!match(pattern, data))
			throw new InvalidInputDataException("please enter Integer or decimal number");
	}

	public static boolean match(String pattern, String date) {
		Pattern patt = Pattern.compile(pattern);
		Matcher match = patt.matcher(date);
		return match.matches();
	   }
}
