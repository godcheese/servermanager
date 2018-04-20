package com.gioov.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/16 21:28
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 获取项目当前运行根目录
     *
     * @return
     */
    public static String getCurrentRootPath() {
        String rootPath = null;
        try {
            // 兼容 Tomcat 部署时获取当前运行根目录
            String nodePath = FileUtil.class.getClassLoader().getResource("/").getPath();
            rootPath = nodePath.substring(1, nodePath.length() - 16);
        } catch (NullPointerException nullPointerException) {
            try {
                rootPath = new File("").getCanonicalPath();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rootPath;
    }


    /**
     * 过滤 / \ 等文件路径分隔符为系统默认分隔符
     * @param path
     * @return
     */
    public static String filterFileSeparator(String path){

        String temp = null;
        if (path != null) {
            path = path.trim();
            path = path.equals("") ? "/" : path;

            // 兼容 spring-boot:run 部署及Tomcat下war部署

            // windows \ ; linux /  全部统一替换为 /
            String separator = "/";

            // windows 下将 \ 转成\\，不然报错：java.lang.IllegalArgumentException: character to be escaped is missing
            // windows 下regex应该这样转 \\ => \\\\


            //        path = path.trim();
//        path = "".equals(path) ? "/" : path;

            String prefix1 = "/";
            String prefix2 = "\\";
            String prefix2regex = "\\\\";

            while (prefix1.equals(path.substring(0, 1))) {
                path = path.substring(1, path.length());
            }

            while (prefix2.equals(path.substring(0, 1))) {
                path = path.substring(1, path.length());
            }

            if (path.contains(prefix1)) {
                path = path.replaceAll(prefix1, separator);
            }

            if (path.contains(prefix2)) {
                path = path.replaceAll(prefix2regex, separator);
            }
            temp = path;
        }

        return temp;
    }


    public static void copyFile(File sourceFile,File targetFile){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(sourceFile);
            fileOutputStream = new FileOutputStream(targetFile);
            copyFile(fileInputStream, fileOutputStream);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(fileInputStream!=null) {
                    fileInputStream.close();
                }
                if(fileOutputStream!=null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyFile(FileInputStream sourceFileInputStream,FileOutputStream targetFileOutputStream){

        try {

            int len;
            byte[] bytes = new byte[1024];
            while ((len = sourceFileInputStream.read(bytes)) > 0) {
                targetFileOutputStream.write(bytes, 0, len);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
