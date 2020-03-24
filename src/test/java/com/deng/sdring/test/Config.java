package com.deng.sdring.test;

import com.deng.sdring.ioc.annotations.Bean;
import com.deng.sdring.ioc.annotations.Configuration;

/**
 * Created by Administrator on 2020/3/24.
 */
@Configuration
public class Config {
    @Bean
    public String init() {
        return "list";
    }
}
