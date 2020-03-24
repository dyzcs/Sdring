package com.deng.sdring.mvc;

import com.deng.sdring.mvc.annotations.RequestMapping;
import com.deng.sdring.mvc.enums.RequestMethod;
import com.deng.sdring.utils.PackageListUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Administrator on 2020/3/24.
 */
@Slf4j
public class RequestScanner {
    /**
     * 初始化HTTP请求的映射
     */
    public static void initMapping() {
        List<Class<?>> classes = PackageListUtils.getAllClass();

        for (Class<?> clazz : classes) {
            String classUrl = null;
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                classUrl = requestMapping.value();
            }

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                    String methodUrl = requestMapping.value();
                    // 只看方法上的HTTP METHOD
                    RequestMethod requestMethod = requestMapping.method();

                    methodUrl = classUrl == null ? methodUrl : classUrl + methodUrl;
                    log.info("Mapped URL [{} {}] onto handler of type [{}]", requestMethod.getValue(), methodUrl, method);
                    UrlMappingPool.getInstance().setMap(methodUrl, clazz, method, requestMethod);
                }
            }
        }
    }
}
