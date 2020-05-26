package com.ctf.dubboprovider.service.impl;

import com.ctf.dubboapi.service.ComputeService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service
@Component
public class ComputeServiceImpl implements ComputeService {

    public Integer compute(int a ,int b ){
        System.out.println("正在计算");
        return a+b;
    }

    public Object count(int a , int b){
        System.out.println("正在求和");
        return 100;
    }
}
