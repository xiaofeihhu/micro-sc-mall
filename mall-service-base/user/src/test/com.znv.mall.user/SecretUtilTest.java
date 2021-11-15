package com.znv.mall.user;

import com.znv.mall.user.util.SecretUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: yf
 * @Date: 2020/7/21
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecretUtilTest {

    @Autowired
    SecretUtil secretUtil;

    @Test
    public void encrypt() {
        secretUtil.test();
    }
}
