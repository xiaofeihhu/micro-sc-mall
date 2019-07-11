package com.znv.mall.consumer.controller;

import com.znv.mall.consumer.provider.ConcurentServiceProvider;
import com.znv.mall.core.entity.vo.Result;
import com.znv.mall.core.util.ThreadPoolHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther: yf
 * @Date: 2019-6-29
 * @Description:
 */
@Api("并发接口测试类")
@RestController
public class ConcurentTestController {

    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    private BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();

    @Autowired
    ConcurentServiceProvider concurentServiceProvider;

    @GetMapping("/concurentTest1")
    @ApiOperation("高并发测试接口1")
    public Result concurentTest1() {

        List<String> resultList = new ArrayList();
        for (int i =0; i<10; i++) {
            Result result = concurentServiceProvider.concurentTest("param"+i);
            resultList.add(String.valueOf(result.getData()));
        }
        return Result.success(resultList);
    }

    @GetMapping("/concurentTest2")
    @ApiOperation("高并发测试接口2")
    public Result concurentTest2() {
        List<String> resultList = new ArrayList();
        CompletableFuture[] cf = new CompletableFuture[100];
        for (int i =0; i<100; i++) {
            final int j = i;
            cf[i] = CompletableFuture.supplyAsync(()-> String.valueOf(concurentServiceProvider.concurentTest("param"+j).getData()), executor)
                    .thenAccept((r)->resultList.add(r));
        }
        CompletableFuture.allOf(cf).join();
        return Result.success(resultList);
    }
}
