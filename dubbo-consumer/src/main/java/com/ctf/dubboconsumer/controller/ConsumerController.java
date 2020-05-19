package com.ctf.dubboconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ctf.dubboapi.service.ComputeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "consumerController/")
public class ConsumerController {

    @Reference
    private ComputeService computeService;

    @RequestMapping(value = "consume",method = RequestMethod.GET)
    public Object consume(){
        return computeService.compute(1,2);
    }
}
