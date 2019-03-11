package com.suchenghe.common.util.file;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author SuChenghe
 * @date 2018/10/16 7:41
 */
public class FileUtil {

    /**
     * 创建文件
     *
     * @param directory 创建文件的位置
     * @param fileName  文件的名称
     */
    public static boolean createFile(String directory, String fileName) {
        if (StringUtils.isEmpty(directory) || StringUtils.isEmpty(fileName)) {
            return false;
        }
        //目录不存在,则创建该目录
        createDirectory(directory);

        if (!directory.endsWith(File.separator)) {
            directory = directory + File.separator;
        }
        //创建文件
        File file = new File(directory + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 创建目录
     *
     * @param directory 创建目录的位置
     */
    public static boolean createDirectory(String directory) {
        if (StringUtils.isEmpty(directory)) {
            return false;
        }
        //位置不存在,则创建该位置目录
        File dirr = new File(directory);
        if (!dirr.exists()) {
            //mkdirs()该方法在创建当前目录时会自动创建所有不存在的父目录
            dirr.mkdirs();
        }
        return true;
    }

    /**
     * 文件重命名
     *
     * @param directory
     * @param fileName
     * @param newFileName
     * @return
     */
    public static boolean renameFile(String directory, String fileName, String newFileName) {
        if (StringUtils.isEmpty(directory) || StringUtils.isEmpty(fileName) || StringUtils.isEmpty(newFileName)) {
            return false;
        }
        //位置是否存在
        File dir = new File(directory);
        if (!dir.exists()) {
            return false;
        }
        if (!directory.endsWith(File.separator)) {
            directory = directory + File.separator;
        }
        //文件是否存在
        File file = new File(directory + fileName);
        if (!file.exists()) {
            return false;
        }
        File newNameFile = new File(directory + newFileName);
        file.renameTo(newNameFile);

        return true;
    }

    /**
     * 删除文件或当前目录下的所有文件
     *
     * @param file
     * @return
     */
    public static boolean deleteAllFile(File file) {
        if (file.isDirectory()) {
            String[] chileren = file.list();
            for (int i = 0; i < chileren.length; i++) {
                File innerFile = new File(file, chileren[i]);
                boolean success = deleteAllFile(innerFile);
                if (!success) {
                    return false;
                }
            }
        }
        return file.delete();
    }


    public static void main(String[] args) {
        boolean result = createDirectory("D:\\file\\demo3\\demo5");
        System.out.println(result);

        result = createFile("D:\\file\\demo3\\demo5", "mydemo.txt");
        System.out.println(result);

        result = renameFile("D:\\file\\demo3\\demo5", "mydemo.txt", "mydemo3.txt");
        System.out.println(result);

//    result = deleteAllFile(new File("D:\\file\\demo3"));
//    System.out.println(result);
    }
}
