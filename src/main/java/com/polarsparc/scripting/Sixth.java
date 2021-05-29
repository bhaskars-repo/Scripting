/*
 * Name:   Sixth
 * Author: Bhaskar S
 * Date:   05/28/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.scripting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

public class Sixth {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sixth.class);

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java com.polarsparc.scripting.Sixth <script> <amount>");
            System.exit(1);
        }

        try {
            File script = new File(Objects.requireNonNull(Sixth.class.getClassLoader()
                    .getResource(args[0])).getFile());

            SixthHolder holder = new SixthHolder();
            holder.setAmount(Double.parseDouble(args[1]));

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByExtension("js");
            if (engine == null) {
                LOGGER.error("Could not find engine - Nashorn");
            }
            else {
                engine.eval(new FileReader(script));

                Invocable invocable = (Invocable) engine;

                Object rateFees = invocable.invokeFunction("computeRateAndFees", holder);

                LOGGER.info("Amount: {}, Rate & Fees [{}]: {}",
                        holder.getAmount(), rateFees.getClass().getName(), rateFees.toString());
            }
        }
        catch (Exception ex) {
            LOGGER.error("Exception", ex);
        }
    }
}
