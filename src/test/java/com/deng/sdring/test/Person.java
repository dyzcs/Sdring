package com.deng.sdring.test;

import com.deng.sdring.ioc.annotations.Component;
import com.google.common.base.MoreObjects;

/**
 * Created by Administrator on 2020/3/24.
 */
@Component
public class Person {
    private String name = "zhangsan";

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .toString();
    }
}
