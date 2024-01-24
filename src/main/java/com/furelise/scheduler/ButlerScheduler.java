package com.furelise.scheduler;

import com.furelise.estabcase.service.DispatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class ButlerScheduler {
    private final Logger logger = LoggerFactory.getLogger(ButlerScheduler.class);
    private final String TW_ZONE_ID = "Asia/Taipei";
    @Autowired
    private DispatchService dispatchService;

    @Scheduled(cron = "${assign.work.emp.cron}", zone = TW_ZONE_ID)
    public void assignWork() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of(TW_ZONE_ID));
        if (!(now.getHour() < 7)) {
            
            this.logger.info("[assignWork] start assign work");
            dispatchService.assignWorkToEmp();
        }
    }

//    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
//    public void TaskDequeueFixedRate() {
//        // do dequeue...
//    }
//
//    @Scheduled(initialDelay = 600000)
//    public void TaskDequeueInitialDelay() {
//        // do dequeue...
//    }
//
//    @Scheduled(cron = "0 1 0 0 0 1/5", zone = TW_ZONE_ID)
//    public void TaskDequeueCron() {
//        // do dequeue...
//    }

}