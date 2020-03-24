package com.deng.sdring;

import com.deng.sdring.ioc.Context;
import com.deng.sdring.mvc.RequestScanner;
import com.deng.sdring.mvc.server.jetty.JettyServer;
import com.deng.sdring.mvc.server.nio.NioServer;
import com.deng.sdring.utils.ConfigUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Administrator on 2020/3/24.
 */
public class Sdring {
    /**
     * 启动服务
     */
    public static void run() {
        try {
            // 依赖注入
            Context.init();
            // 初始化Http请求映射
            RequestScanner.initMapping();
            // 获取服务器配置
            Map<String, Object> config = ConfigUtil.getConfig();
            String server = (String) config.get("server");
            int port = (int) config.get("port");
            // 启动Http服务器
            if ("jetty".equals(server)) {
                JettyServer.start(port);
            } else if ("nio".equals(server)) {
                NioServer.start(port);
            } else {
                throw new RuntimeException("Unknown server type [" + server + "]");
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
