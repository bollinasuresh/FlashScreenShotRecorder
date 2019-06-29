package com.trishana.examples.Flash;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

//google flash selenium jars
//https://code.google.com/archive/p/flash-selenium/downloads


public class FlashSeleniumWebDriver {
	private final WebDriver webDriver;
	private final String flash_ObjectId;

//constructor for FlashSeleniumWebDriver
	public FlashSeleniumWebDriver(final WebDriver webDriver, final String flash_ObjectId) {

//initialize webDriver variable to use in this class methods
		this.webDriver = webDriver;

		// initialize flashObjectId variable which have real flash object ID to use in
		// this class methods
		this.flash_ObjectId = flash_ObjectId;
	}

	public String call(final String functionName, final String... args) {

		final Object result = ((JavascriptExecutor) webDriver).executeScript(makeJavaScriptFunction(functionName, args),
				new Object[0]);

		return result != null ? result.toString() : null;
	}

	private String makeJavaScriptFunction(final String function_Name, final String... args) {
		final StringBuffer function_Args = new StringBuffer();

		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				if (i > 0) {
					function_Args.append(",");
				}
				function_Args.append(String.format("'%1$s'", args[i]));
			}
		}
		return String.format("return document.%1$s.%2$s(%3$s);", flash_ObjectId, function_Name, function_Args);
	}

}