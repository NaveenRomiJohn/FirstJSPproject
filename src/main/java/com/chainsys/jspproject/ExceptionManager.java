package com.chainsys.jspproject;

public class ExceptionManager {
	public static String handleException(Exception e,String source,String message) {
		LogManager.logException(e,source,message);
		message +="Message: "+e.getMessage();
		String errorPage=HTMLHelper.getHTMLTemplate("ERROR",message);
		return errorPage;
	}
}
