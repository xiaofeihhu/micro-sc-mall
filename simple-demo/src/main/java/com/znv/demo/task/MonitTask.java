package com.znv.demo.task;//package com.znv.demo.task;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///**
// * @author znv
// * @ClassName:
// * @Description: 设备监控任务
// * @date 2018/5/30 16:29
// */
//@Component
//public class MonitTask {
//    /**
//     * 日志
//     */
//    private static Logger logger = LoggerFactory.getLogger(MonitTask.class);
//
//
//    /**
//     * 设备服务器监控
//     */
//    @Scheduled(initialDelay = 10000,fixedRate = 50000)
//    @Async
//    public void monitDevice(){
//        logger.info("定时任务，不需要请删除....");
//
//    }
//
//
//}
