package com.gioov.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.acl.LastOwnerException;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/18 16:44
 */
public class DatabaseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseUtil.class);

    public static boolean mysqlExportDatabase(String host, String port, String name, String charset, String username, String password, String filename, String targetPath)  {
        File saveFile = new File(targetPath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        if (!targetPath.endsWith(File.separator)) {
            targetPath = targetPath + File.separator;
        }

        StringBuilder stringBuilder = new StringBuilder();
        String mysqldumpFilename = FileUtil.getCurrentRootPath() + File.separator + "data" + File.separator + "mysqldump.exe";

        stringBuilder.append(mysqldumpFilename);
        stringBuilder.append(" --opt").append(" -h").append(host).append(" -P").append(port);
        stringBuilder.append(" --user=").append(username) .append(" --password=").append(password).append(" --lock-all-tables=true");
        stringBuilder.append(" --result-file=").append(targetPath).append(filename).append(" --default-character-set=").append(charset).append(" ").append(name);
        try {
            Process process = Runtime.getRuntime().exec(stringBuilder.toString());
            if (process.waitFor() == 0) {// 0 表示线程正常终止。
                return true;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
