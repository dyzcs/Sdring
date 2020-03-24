package com.deng.sdring.test;

import com.deng.sdring.ioc.annotations.Component;
import com.deng.sdring.mvc.annotations.Param;
import com.deng.sdring.mvc.annotations.RequestMapping;
import com.deng.sdring.mvc.enums.RequestMethod;

/**
 * Created by Administrator on 2020/3/24.
 */
@Component
@RequestMapping("/name")
public class Hello {

    @RequestMapping("/person")
    public String hello(@Param("name") String name, @Param("age") String age) {
        return "hello " + name + ", your age is " + Integer.valueOf(age);
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public String test(@Param("name") String name) {
        return "post " + name;
    }
}
