package com.znv.mall;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yf
 * @Date: 2020/4/21
 * @Description:
 */
@SpringBootTest
public class Test2 {

    @Test
    public void test1() {
        double[] gpsxy = PositionUtil.gcj02_To_Gps84(121.554730245866,31.0365862665122);
        System.out.println(gpsxy[0]);
        System.out.println(gpsxy[1]);
    }
}
