package com.deng.sdring;

import com.deng.sdring.ioc.Context;
import com.deng.sdring.mvc.RequestScanner;
import com.deng.sdring.mvc.server.jetty.JettyServer;
import com.deng.sdring.test.Company;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2020/3/24.
 */
public class Test {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Context.init();
        RequestScanner.initMapping();
        JettyServer.start(8080);

        Company company = (Company) Context.getBean(Company.class);
        company.test();
    }
}
