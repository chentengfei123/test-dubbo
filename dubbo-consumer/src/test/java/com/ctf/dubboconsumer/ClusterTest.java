package com.ctf.dubboconsumer;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.AppResponse;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcInvocation;
import org.apache.dubbo.rpc.cluster.Directory;
import org.apache.dubbo.rpc.cluster.support.FailbackClusterInvoker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = DubboConsumerApplication.class)
public class ClusterTest {

    List<Invoker<FailbackClusterInvoker>> invokers = new ArrayList<Invoker<FailbackClusterInvoker>>();
    URL url = URL.valueOf("test://test:11/test?retries=2&failbacktasks=2");
    Invoker<FailbackClusterInvoker> invoker = mock(Invoker.class);
    RpcInvocation invocation = new RpcInvocation();
    Directory<FailbackClusterInvoker> dic;
    Result result = new AppResponse();

    @BeforeEach
    public void setUp() throws Exception {

        dic = mock(Directory.class);
        given(dic.getUrl()).willReturn(url);
        given(dic.getConsumerUrl()).willReturn(url);
        given(dic.list(invocation)).willReturn(invokers);
        given(dic.getInterface()).willReturn(FailbackClusterInvoker.class);
        invocation.setMethodName("method1");
        invokers.add(invoker);
    }

    @AfterEach
    public void tearDown() {

        dic = null;
        invocation = new RpcInvocation();
        invokers.clear();
    }
    private void resetInvokerToException() {
        given(invoker.invoke(invocation)).willThrow(new RuntimeException());
        given(invoker.getUrl()).willReturn(url);
        given(invoker.getInterface()).willReturn(FailbackClusterInvoker.class);
    }

    @Test
    public void testFailoverClusterInvoker(){
        resetInvokerToException();
        FailbackClusterInvoker<FailbackClusterInvoker> invoker = new FailbackClusterInvoker<>(
                dic);
        Result ret = invoker.invoke(invocation);
        System.out.println();
    }

    @Test
    public void test(){

    }
}
