package com.deng.sdring.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2020/3/24.
 * <p>
 * 读取配置文件的信息
 */
public class ConfigUtil {
    private static String root = Objects.requireNonNull(PackageListUtils.class.getClassLoader().getResource("")).getPath();
    private static String separator = File.separator;

    public static Map<String, Object> getConfig() {
        String fileName = root + separator + "sdring.yaml";
        File file = new File(fileName);
        if (!file.exists()) {
            Map<String, Object> map = new HashMap<>();
            map.put("server", "jetty");
            map.put("port", 9999);
            return map;
        }
        try {
            InputStream input = new FileInputStream(file);
            Yaml yaml = new Yaml();
            Map<String, Object> map = yaml.load(input);
            if (map == null) {
                map = new HashMap<>();
            }
            if (!map.containsKey("server")) {
                map.put("server", "jetty");
            }
            if (!map.containsKey("port")) {
                map.put("port", 9999);
            }
            return map;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
