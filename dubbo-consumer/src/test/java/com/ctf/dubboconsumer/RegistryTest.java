package com.ctf.dubboconsumer;

import com.ctf.dubboapi.service.ComputeService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.RegistryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DubboConsumerApplication.class)
public class RegistryTest {

    private RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();

    @Test
    public void registryTest(){
        Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://127.0.0.1:2181"));
        URL url =  URL.valueOf("condition://0.0.0.0/" +
                ComputeService.class.getName() +
                "?category=routers" +
                "&dynamic=false" +
                "&runtime=false" +
                "&force=true" +
                "&rule="+URL.encode(" => host != 172.24.22.192 ") );
        registry.register(url);
    }
}
