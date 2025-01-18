/*
 * Name:   Third
 * Author: Bhaskar S
 * Date:   01/18/2025
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.scripting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Objects;
import javax.script.*;

public class Third {
	private static final Logger LOGGER = LoggerFactory.getLogger(Third.class);

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java com.polarsparc.scripting.Third <script>");
			System.exit(1);
		}
		
		try {
			File script = new File(Objects.requireNonNull(Third.class.getClassLoader()
					.getResource(args[0])).getFile());

			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			
			boolean exit = false;
			while (! exit) {
				System.out.println("1. Open New CD");
				System.out.println("2. Quit");
				
				String choice = input.readLine();
				if (choice.equals("1")) {
					System.out.print("CD Amount: ");
					String amtStr = input.readLine();
					
					double amount = Double.parseDouble(amtStr);
					
					double rate = executeBusinessRules(script, amount);
					
					LOGGER.info("Interest rate: {}", rate);
				} else if (choice.equals("2")) {
					exit = true;
				}
			}
		}
		catch (Exception ex) {
			LOGGER.error("Exception", ex);
		}
	}
	
	public static double executeBusinessRules(File script, double amount) throws Exception {
		double rate = 0.0;
		
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByExtension("js");
		if (engine == null) {
			LOGGER.error("Could not find engine - Nashorn");
		}
		else {
			Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
			bindings.put("amount", amount);
			
			engine.eval(new FileReader(script));
			
			rate = (Double) bindings.get("rate");
		}
		
		return rate;
	}
}
