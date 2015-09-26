package com.mo2o.Iberostar.utils;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

public class server {
	public static void startAppiumServer() throws Exception {

		CommandLine command = new CommandLine(
				"/Applications/Appium.app/Contents/Resources/node/bin/node");

		command.addArgument(
				"/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js",
				false);

		command.addArgument("--address", false);

		command.addArgument("127.0.0.1");

		command.addArgument("--port", false);

		command.addArgument("4444");

		command.addArgument("--no-reset", false);

		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

		DefaultExecutor executor = new DefaultExecutor();

		executor.setExitValue(1);

		executor.execute(command, resultHandler);

		Thread.sleep(7000);

	}

	// Stopping the Appium Server
	public static void stopAppiumServer() throws Exception {

		for (int i=0;i<10;i++){
		System.out.println("Se para el server");
		Runtime.getRuntime().exec("killall node");
		//Runtime.getRuntime().exec("killall adb");
		}

	}

}
