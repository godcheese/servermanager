package com.gioov.util;

import javax.security.auth.Subject;
import java.io.*;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/18 10:20
 */
public class ZipUtil {


    public static void unCompress(File entryFile, String targetDirectory){
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(entryFile)));
            File childFile;
            ZipEntry zipEntry;
            String parentDirectory = targetDirectory;

            while ((zipEntry = zipInputStream.getNextEntry()) != null && !zipEntry.isDirectory()) {

                childFile = new File(parentDirectory, zipEntry.getName());
                if (!childFile.exists()) {
                    (new File(childFile.getParent())).mkdirs();
                }

                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(childFile));

                int len = -1;
                byte[] bytes = new byte[1024];
                while ((len = zipInputStream.read(bytes)) != -1) {
                    bufferedOutputStream.write(bytes, 0, len);
                }

                bufferedOutputStream.close();
            }
            zipInputStream.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void compress(File entryFile,String targetFilename, String targetZipDirectory) {
        try {
            ZipOutputStream zipOutput = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream( targetFilename)));
            ZipUtil.compressZip(zipOutput, entryFile, targetZipDirectory);
            zipOutput.closeEntry();
            zipOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void compressZip(ZipOutputStream zipOutputStream, File entryFile, String targetZipDirectory){
        if(entryFile.isDirectory()) {
            File[] listFiles = entryFile.listFiles();
            if (listFiles == null) {
                return;
            }
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    if(targetZipDirectory==null){
                        compressZip(zipOutputStream, file, file.getName());
                    } else {
                        compressZip(zipOutputStream, file, targetZipDirectory + File.separator + file.getName());
                    }
                } else {
                    zip(zipOutputStream, file, targetZipDirectory);
                }
            }
        } else {
            zip(zipOutputStream, entryFile, targetZipDirectory);
        }
    }

    private static void zip(ZipOutputStream zipOutputStream, File entryFile, String targetZipDirectory){
        try {
            ZipEntry zipEntry;
            if(targetZipDirectory==null){
                zipEntry = new ZipEntry(entryFile.getName());
            } else {
                zipEntry = new ZipEntry(targetZipDirectory + File.separator + entryFile.getName());
            }

            zipOutputStream.putNextEntry(zipEntry);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(entryFile));
            byte[] bytes = new byte[1024];
            int read = -1;
            while ((read = bufferedInputStream.read(bytes)) != -1) {
                zipOutputStream.write(bytes, 0, read);
            }
            bufferedInputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
