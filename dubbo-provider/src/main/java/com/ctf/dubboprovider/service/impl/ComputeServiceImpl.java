package com.ctf.dubboprovider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ctf.dubboapi.service.ComputeService;
import org.springframework.stereotype.Component;

@Service
@Component
public class ComputeServiceImpl implements ComputeService {

    public Integer compute(int a ,int b ){

        return a+b;
    }
}
