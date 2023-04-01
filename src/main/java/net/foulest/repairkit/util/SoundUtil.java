package net.foulest.repairkit.util;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SoundUtil {

    private static final ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void playSound(String soundName) {
        Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty(soundName);

        if (runnable != null) {
            threadPool.execute(runnable);
        }
    }
}
