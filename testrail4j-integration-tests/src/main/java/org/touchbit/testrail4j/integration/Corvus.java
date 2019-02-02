/*
 * MIT License
 *
 * Copyright © 2019 TouchBIT.
 * Copyright © 2019 Oleg Shaburov.
 * Copyright © 2018 Maria Vasilenko.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.touchbit.testrail4j.integration;

import org.slf4j.Logger;
import org.touchbit.buggy.core.Buggy;
import org.touchbit.buggy.core.utils.log.BuggyLog;
import sun.misc.Unsafe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */
public class Corvus extends Buggy {

    public static void main(String[] args) {
        setProgramName("Corvus");
        Buggy.prepare(args);
        disableWarning();
        waitMigrations();
        Buggy.main(args);
    }

    private static void waitMigrations() {
        Logger log = BuggyLog.framework();
        boolean migrationContains = true;
        // + 5 min
        long waitTime = new Date().getTime() + 300000;
        try {
            while (migrationContains) {
                List<String> lines = new ArrayList<>();
                Process p = Runtime.getRuntime().exec("docker ps");
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while (true) {
                    line = r.readLine();
                    if (line == null) {
                        break;
                    }
                    lines.add(line);
                }
                long count = lines.stream().filter(l -> l.toLowerCase().contains("migration")).count();
                if (count > 0) {
                    log.info("Waiting for migration to complete...");
                    Thread.sleep(500);
                } else {
                    log.info("Migration container not found. Running tests.");
                    migrationContains = false;
                }
                if (new Date().getTime() > waitTime) {
                    Buggy.getExitHandler().exitRun(1, "Timeout for loading migrations exceeded");
                }
            }
        } catch (IOException e) {
            Buggy.getExitHandler().exitRun(1, "'docker ps' call is fail", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void disableWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception ignore) {
            // ignore
        }
    }
}