package com.ctf.dubboconsumer.service;

import org.apache.dubbo.common.extension.SPI;

@SPI
public interface SimpleService {

    Object sayH();
}
