package com.lika85456.latinska_slovicka;

/**
 * Created by lika85456 on 25.11.2017.
 */
public class TimerLogger {
    private long time;
    private String log = "";
    private int step = 0;

    public TimerLogger() {
        time = System.currentTimeMillis();
        log += "Logging started at: " + time;
    }

    public void log() {
        step++;
        long tempTime = System.currentTimeMillis();
        this.log += "STEP: " + step + " TIME: " + (tempTime - time) + "\n";
        time = tempTime;
    }

    public String getLog() {
        return this.log;
    }
}
