package com.fish.task;

import java.util.concurrent.ScheduledFuture;

/**
 * 用于定时线程池的任务数据结构
 *
 * @author lincy
 */
public abstract class TaskForSchedule {

    private ScheduledFuture<?> future;

    private ScheduledTimerTask timerTask;

    /**
     * @param timerTask the timertask to set
     */
    void setTimerTask(ScheduledTimerTask timerTask) {
        this.timerTask = timerTask;
    }

    /**
     * @param future the future to set
     */
    void setFuture(ScheduledFuture<?> future) {
        this.future = future;
    }

    /**
     * 接口方法
     */
    public abstract void run();

    /**
     * @return
     */
    public boolean cancel() {
        timerTask.cancel();
        if (future != null)
            return future.cancel(true);
        else
            return false;
    }

}
