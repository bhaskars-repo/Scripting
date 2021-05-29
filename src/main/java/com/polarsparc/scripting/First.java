/*
 * Name:   First
 * Author: Bhaskar S
 * Date:   05/28/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.scripting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.io.*;
import javax.script.*;

public class First {
	private static final Logger LOGGER = LoggerFactory.getLogger(First.class);

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: java com.polarsparc.scripting.First <script> <name> <number>");
			System.exit(1);
		}
		
		String name = args[1];
		
		int num = 0;
		
		try {
			num = Integer.parseInt(args[2]);
		}
		catch (Exception ex) {
			LOGGER.error("Exception", ex);
		}
		
		if (num <= 0) {
			num = 5;
		}
		
		ScriptEngineManager manager = new ScriptEngineManager();

		List<ScriptEngineFactory> factories = manager.getEngineFactories();
		factories.forEach(factory -> {
			LOGGER.info("Engine name: {}, Version: {}, Language: {}, Language version: {}",
					factory.getEngineName(),
					factory.getEngineVersion(),
					factory.getLanguageName(),
					factory.getLanguageVersion());

			factory.getNames().forEach(alias -> LOGGER.info("Engine alias: {}", alias));
		});

		ScriptEngine engine = manager.getEngineByExtension("js");
		if (engine == null) {
			LOGGER.error("Could not find engine - Nashorn");
		}
		else {
			Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
			bindings.put("name", name);
			bindings.put("num", num);

			try {
				File script = new File(Objects.requireNonNull(First.class.getClassLoader()
						.getResource(args[0])).getFile());
				engine.eval(new FileReader(script));
			} catch (Throwable ex) {
				LOGGER.error("Exception", ex);
			}
		}
	}
}
