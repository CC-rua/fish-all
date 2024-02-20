package com.fish;

import com.fish.config.load.yaml.YamlConfigAutoReader;
import com.fish.net.tcp.TcpBootStrap;
import com.fish.task.TaskForSchedule;
import com.fish.task.TaskManager;
import com.fish.task.TaskType;
import com.fish.task.TickTask;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author: cc
 * @date: 2024/1/6
 */
@Slf4j
public class ClientMain extends TaskForSchedule {
    public static ClientMain INSTANCE = new ClientMain();

    private ArrayList<TickTask> tickTaskList = new ArrayList<>();

    public void registerTickTask(TickTask task) {
        tickTaskList.add(task);
    }

    public void unregisterTickTask(TickTask task) {
        tickTaskList.remove(task);
    }

    public static void main(String[] args) {
        //加载配置
        YamlConfigAutoReader.INSTANCE.autoReadYml();
        log.info("config load down");
        //启动服务
        TcpBootStrap.clientStart();
        log.info("server start");
        //开始tick
        TaskManager.getInstance().addScheduledTask(INSTANCE, TaskType.WORLD, 5000, 50);
        log.info("server tick tok");
    }

    @Override
    public void run() {
        try {
            tickTaskList.forEach(TickTask::tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
