package com.ctf.dubboconsumer;

import com.ctf.dubboapi.service.ComputeService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.ProxyFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DubboConsumerApplication.class)
public class ConditionRouterTest {
    private Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
    private ProxyFactory proxy = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();


    @Test
    public void testRouter(){
        int port = 20990;
        URL url = URL.valueOf("dubbo://172.24.22.192:" + port + "/" + ComputeService.class.getName()).addParameter("rule","").addParameter("timeout",
                3000L);
        ComputeService computeService = proxy.getProxy(protocol.refer(ComputeService.class, url));
        System.out.println(computeService.compute(1,4));
    }

}
