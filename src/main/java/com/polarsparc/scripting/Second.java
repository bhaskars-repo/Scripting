/*
 * Name:   Second
 * Author: Bhaskar S
 * Date:   05/28/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.scripting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import javax.script.*;

public class Second {
	private static final Logger LOGGER = LoggerFactory.getLogger(Second.class);

	public static void main(String[] args) {
		Random random = new Random();
		
		ScriptEngineManager manager = new ScriptEngineManager();
		
		ScriptEngine engineOne = manager.getEngineByExtension("js");
		ScriptEngine engineTwo = manager.getEngineByExtension("js");
		if (engineOne == null || engineTwo == null) {
			LOGGER.error("Could not find engine - Nashorn");
		}
		else {
			Bindings bindings = engineOne.getBindings(ScriptContext.ENGINE_SCOPE);
			bindings.put("num", random.nextInt(1000));

			LOGGER.info("Executing using engine-1 (1) ... ");

			try {
				engineOne.eval("print('num = ' + num);");
			}
			catch (Throwable ex) {
				LOGGER.error("Exception", ex);
			}

			LOGGER.info("Executing using engine-2 (1) ... ");

			try {
				engineTwo.eval("print('num = ' + num);"); // Will throw exception
			}
			catch (Throwable ex) {
				LOGGER.error("Exception", ex);
			}

			bindings = engineTwo.getBindings(ScriptContext.GLOBAL_SCOPE);
			bindings.put("val", random.nextInt(1000));

			LOGGER.info("Executing using engine-1 (2) ... ");

			try {
				engineOne.eval("print('val = ' + val);");
			}
			catch (Throwable ex) {
				LOGGER.error("Exception", ex);
			}

			LOGGER.info("Executing using engine-2 (2) ... ");

			try {
				engineTwo.eval("print('val = ' + val);");
			}
			catch (Throwable ex) {
				LOGGER.error("Exception", ex);
			}
		}
	}
}
