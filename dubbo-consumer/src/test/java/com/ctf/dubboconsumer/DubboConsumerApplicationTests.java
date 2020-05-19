package com.ctf.dubboconsumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ctf.dubboconsumer.controller.ConsumerController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DubboConsumerApplication.class)
class DubboConsumerApplicationTests {

    @Reference
    private ConsumerController consumerController;

    @Test
    void contextLoads() {
        Object obj = consumerController.consume();
        System.out.println(obj);
    }

}
