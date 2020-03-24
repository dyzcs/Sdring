package com.deng.sdring.mvc.server.nio;

import com.deng.sdring.mvc.enums.RequestMethod;
import com.google.common.base.MoreObjects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * Created by Administrator on 2020/3/24.
 */
@Setter
@Getter
@NoArgsConstructor
public class HttpRequest {

    private RequestMethod requestMethod;
    private String url;
    private Map<String, String> headers;
    private Map<String, String> params;
    private String version;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("requestMethod", requestMethod)
                .add("url", url)
                .add("headers", headers)
                .add("params", params)
                .add("version", version)
                .toString();
    }
}
