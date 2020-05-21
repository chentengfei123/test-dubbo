package com.ctf.dubboconsumer;

import com.ctf.dubboapi.service.ComputeService;
import com.ctf.dubboconsumer.controller.ConsumerController;
import com.ctf.dubboconsumer.service.SimpleService;
import com.ctf.dubboconsumer.service.impl.SimpleServiceImpl;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.remoting.Constants;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.ProxyFactory;
import org.apache.dubbo.rpc.model.ApplicationModel;
import org.apache.dubbo.rpc.protocol.dubbo.DubboProtocol;
import org.apache.dubbo.rpc.support.ProtocolUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ServiceLoader;

@SpringBootTest(classes = DubboConsumerApplication.class)
class DubboConsumerApplicationTests {

    @Test
    void contextLoads() {
        ServiceLoader<SimpleService> serviceLoader = ServiceLoader.load(SimpleService.class);
        serviceLoader.forEach(simpleService  -> {
            System.out.println(simpleService.sayH());
        });
    }

    @Test
    public void testExtensionLoader(){
        ExtensionLoader<SimpleService> extensionLoader = ExtensionLoader.getExtensionLoader(SimpleService.class);
        extensionLoader.getExtension("imp1").sayH();
        extensionLoader.getExtension("imp2").sayH();
    }

//    private Protocol protocol = ExtensionLoader.getExtensionLoader(DubboProtocol.class).getAdaptiveExtension();
    private Protocol protocol = new DubboProtocol();
    private ProxyFactory proxy = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();

    /**
     *dubbo协议服务导出
     */
    @Test
    public void testDubboProtocol(){
        SimpleService simpleService1 = new SimpleServiceImpl();
        int port = NetUtils.getAvailablePort();
        URL url = URL.valueOf("dubbo://127.0.0.1:" + port + "/" + SimpleService.class.getName()).addParameter("timeout",
                3000L);
        protocol.export(proxy.getInvoker(simpleService1, SimpleService.class, url));
        SimpleService simpleService2  = proxy.getProxy(protocol.refer(SimpleService.class, url));
        System.out.println(simpleService2.sayH());

    }

    /**
     *dubbo协议 服务引入
     */
    @Test
    public void testDubboProtocol1(){
        int port = 20990;
        URL url = URL.valueOf("dubbo://172.24.22.192:" + port + "/" + ComputeService.class.getName()+"?category=providers").addParameter("timeout",
                3000L);
        ComputeService computeService = proxy.getProxy(protocol.refer(ComputeService.class, url));
        System.out.println(computeService.compute(1,4));
    }

    /**
     * 注册测试
     */


    /**
     * 路由测试
     */

    /**
     * 负载均衡测试
     */

}
