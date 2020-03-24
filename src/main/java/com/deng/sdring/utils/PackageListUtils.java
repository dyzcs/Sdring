package com.deng.sdring.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

/**
 * Created by Administrator on 2020/3/24.
 * <p>
 * 获取环境变量下的所有的包下面的所有的class
 */
public class PackageListUtils {
    // CLASSPATH
    private static String root = Objects.requireNonNull(PackageListUtils.class.getClassLoader().getResource("")).getPath();
    // 分隔符
    private static String separator = File.separator;
    private static List<Class<?>> classes;

    public static List<Class<?>> getAllClass() {
        // 更换CLASSPATH的样式
        root = new File(root).getPath();
        classes = new ArrayList<>();
        listFilesForFolder(new File(root));
        return classes;
    }

    private static void listFilesForFolder(File folder) {
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory()) {
                listFilesForFolder(file);
            } else {
                String path = file.getPath();
                if (path.endsWith(".class")) {
                    String classPath = path
                            // 去掉绝对路径
                            .replaceAll(Matcher.quoteReplacement(root), "")
                            // 更换间隔符
                            .replaceAll(Matcher.quoteReplacement(separator), ".")
                            // 去掉后缀
                            .replaceAll(".class", "");
                    if (classPath.startsWith(Matcher.quoteReplacement("."))) {
                        classPath = classPath.replaceFirst(Matcher.quoteReplacement("."), "");
                    }
                    try {
                        classes.add(Class.forName(classPath));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
