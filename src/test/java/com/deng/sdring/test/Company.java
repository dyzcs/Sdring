package com.deng.sdring.test;

import com.deng.sdring.ioc.annotations.Component;
import com.deng.sdring.ioc.annotations.Resource;
import com.google.common.base.MoreObjects;

/**
 * Created by Administrator on 2020/3/24.
 */
@Component
public class Company {

    @Resource
    private Person person;

    @Resource
    private String name;

    public void test() {
        System.out.println(person + " hate " + name);
    }
}
