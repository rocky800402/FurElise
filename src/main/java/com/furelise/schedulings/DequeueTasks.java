package com.furelise.schedulings;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DequeueTasks {



    @Scheduled(fixedDelay = 60000, zone = "timeZone")
    public void TaskDequeueFixedDelay() {

        // do dequeue...
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void TaskDequeueFixedRate() {
        // do dequeue...
    }

    @Scheduled(initialDelay = 600000)
    public void TaskDequeueInitialDelay() {
        // do dequeue...
    }

    @Scheduled(cron = "0 1 0 0 0 1/5")
    public void TaskDequeueCron() {
        // do dequeue...
    }

}