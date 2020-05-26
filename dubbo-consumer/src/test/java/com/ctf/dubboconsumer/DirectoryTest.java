package com.ctf.dubboconsumer;

import com.ctf.dubboapi.service.ComputeService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.RegistryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest(classes = DubboConsumerApplication.class)
public class DirectoryTest {

    private RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();

    @Test
    public void testDirectory(){
        Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://127.0.0.1:2181"));
        URL url =  URL.valueOf("dubbo://172.24.22.192:20990/" +
                ComputeService.class.getName() +
                "?category=providers" +
                "&anyhost=true&application=dubbo-provider&deprecated=false&dubbo=2.0.2&dynamic=true&generic=false&interface=com.ctf.dubboapi.service.ComputeService&methods=compute&pid=1424&release=2.7.6&side=provider");
        registry.register(url);
    }
}
