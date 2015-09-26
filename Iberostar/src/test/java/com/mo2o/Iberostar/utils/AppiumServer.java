package com.mo2o.Iberostar.utils;

import java.io.IOException;

public class AppiumServer {

	private static Process process;

	private static String STARTSERVER = "/Applications/Appium.app/Contents/Resources/node/bin/node /Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js";

	public static void start() throws IOException, InterruptedException{
		Runtime runtime = Runtime.getRuntime();

		process  = runtime.exec(STARTSERVER);
		Thread.sleep(7000);
		if(process != null){
			System.out.println("Appium server started");
		}
	}

	public static void stop(){

		if (process != null){
			process.destroy();
		}
		System.out.println("Appium server stopped");
	}
}
