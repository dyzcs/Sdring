package com.deng.sdring.mvc;

import com.deng.sdring.mvc.enums.RequestMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2020/3/24.
 */
@Getter
@Setter
@Builder
public class MethodDetail {
    private String url;
    private RequestMethod requestMethod;
    private Class<?> clazz;
    private Method method;
}
