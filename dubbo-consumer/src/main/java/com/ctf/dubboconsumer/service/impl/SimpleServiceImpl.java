package com.ctf.dubboconsumer.service.impl;

import com.ctf.dubboconsumer.service.SimpleService;


public class SimpleServiceImpl implements SimpleService {

    @Override
    public Object sayH() {
        System.out.println("实现方法SimpleServiceImpl");
        return "success";
    }
}
