package com.deng.sdring.ioc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/3/24.
 * <p>
 * 保存所有的bean
 */
public class BeansPool {
    private static BeansPool beansPool;

    public static BeansPool getInstance() {
        if (beansPool == null) {
            beansPool = new BeansPool();
        }
        return beansPool;
    }

    // 根据类型保存对象
    private Map<Class<?>, Object> map = new HashMap<>();

    public Object getObject(Class<?> clazz) {
        return map.get(clazz);
    }

    public void setObject(Class<?> clazz, Object object) {
        map.put(clazz, object);
    }
}
