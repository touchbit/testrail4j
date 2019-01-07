/*
 * Copyright © 2018 Shaburov Oleg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
            Buggy.getExitHandler().exitRun(1, "'docker ps' call fail", e);
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
