package com.deng.sdring.ioc;

import com.deng.sdring.ioc.annotations.Bean;
import com.deng.sdring.ioc.annotations.Component;
import com.deng.sdring.ioc.annotations.Configuration;
import com.deng.sdring.ioc.annotations.Resource;
import com.deng.sdring.utils.PackageListUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Administrator on 2020/3/24.
 * <p>
 * 实现依赖注入
 */
@Slf4j
public class Context {
    // 创建 -> 保存 -> 获取 -> 使用

    public static void init() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        List<Class<?>> classes = PackageListUtils.getAllClass();
        // 扫描类并且创建bean，把bean保存到内存中
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Component.class) || clazz.isAnnotationPresent(Configuration.class)) {
                log.info("Create bean for [{}]", clazz);
                BeansPool.getInstance().setObject(clazz, clazz.newInstance());
            }
        }

        // 把用户自定义的bean保存到内存中去
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Configuration.class)) {
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Bean.class)) {
                        Object classObject = BeansPool.getInstance().getObject(clazz);
                        Object o = method.invoke(classObject);
                        log.info("Create bean for [{}] by method [{}]", o.getClass(), method);
                        BeansPool.getInstance().setObject(o.getClass(), o);
                    }
                }
            }
        }

        // 把内存中的bean注入到对象中去
        for (Class<?> clazz : classes) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Resource.class)) {
                    Object classObject = BeansPool.getInstance().getObject(clazz);
                    Object fieldObject = BeansPool.getInstance().getObject(field.getType());

                    field.setAccessible(true);
                    field.set(classObject, fieldObject);
                    log.info("Inject bean [{}] type [{}] into [{}]", fieldObject, field.getType(), clazz);
                }
            }
        }
    }

    public static Object getBean(Class<?> clazz) {
        return BeansPool.getInstance().getObject(clazz);
    }
}
