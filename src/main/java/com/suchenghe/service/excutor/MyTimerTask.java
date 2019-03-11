package com.suchenghe.service.excutor;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 请使用MyThreadExecutor，这个只是测试TimerTask功能类
 * <p>
 * 多线程并行处理定时任务时，Timer运行多个TimeTask时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用ScheduledExecutorService则没有这个问题
 *
 * @author SuChenghe
 * @date 2018/7/26 19:07
 */
public class MyTimerTask extends TimerTask {

    public static void main(String[] args) {

        Timer timer = new Timer();

        MyTimerTask myTimerTask = new MyTimerTask();

        timer.schedule(myTimerTask, 1000L, 3000L);

    }

    @Override
    public void run() {
        System.out.println("任务执行了");
    }

}
