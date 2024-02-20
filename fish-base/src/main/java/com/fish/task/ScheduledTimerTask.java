package com.fish.task;


import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * 定时任务
 *
 * @author
 */
@Slf4j
public class ScheduledTimerTask extends TimerTask {
    /**
     *
     */
    TaskForSchedule task;

    ScheduledTimerTask(TaskForSchedule task) {
        this.task = task;
        task.setTimerTask(this);
    }

    @Override
    public void run() {
        try {
            task.run();
        } catch (Exception e) {
            log.error("run task error");
        }
    }

}
