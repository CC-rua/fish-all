package com.fish.task;

/**
 * 任务类别
 */
public enum TaskType {
    WORLD,  // 主线程任务
    NET,    // 网络任务
    DB,     // 数据库任务
    CENTER,    // 中心服任务
    OTHER,  // 其他任务
    NULL    //空任务
    // 其它
}
