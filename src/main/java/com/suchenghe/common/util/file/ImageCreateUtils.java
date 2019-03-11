package com.suchenghe.common.util.file;

import com.suchenghe.common.util.CreateCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author SuChenghe
 * @date 2018/7/29 17:36
 */
public class ImageCreateUtils {

    final static Logger LOGGER = LoggerFactory.getLogger(ImageCreateUtils.class);

    private final static String HTTP_LOWER = "http";
    private final static String HTTP_UPPER = "HTTP";

    /**
     * 上传文件(到相对路径)
     * <p>
     * tomcat  server.xml配置
     * 一 <Context docBase="d:/MyRunDataLog/uploadimages/" path="/wwdlsg/uploadimages/" reloadable="true" />（<Host></Host>中添加）
     * http://localhost:8092/MyRunDataLog/uploadimages/2012.png，可以访问d:/MyRunDataLog/uploadimages/的文件
     * <p>
     * 二  <Context crossContext="true" reloadable="true"></Context>
     * 实现同一个tomcat下的多个web应用之间实现ServletContext对象访问。该属性主要用于跨应用访问数据。
     *
     * @param multipartFile
     * @param savePath
     * @return
     */
    public static void uploadFile(MultipartFile multipartFile, String savePath) throws IOException {
        if (null != multipartFile) {
            //上传文件的原有属性
            String fileName = multipartFile.getOriginalFilename();
            String[] fs = fileName.split("[.]");
            String type = fs[fs.length - 1].toLowerCase();

            //判断该路径是否存在
            File fileDirectory = new File(savePath);
            if (!fileDirectory.isDirectory()) {
                fileDirectory.mkdirs();
            }
            //保存文件
            File file = new File(fileDirectory + "/" + fileName + "." + type);
            multipartFile.transferTo(file);

        } else {
            LOGGER.warn("上传文件为空");
        }
    }

    /**
     * 从网上拉取照片信息或者本地获取字节数据
     *
     * @param urlPath
     */
    public static byte[] getImageByteOutputStreamFromUrl(String urlPath) {
        byte[] data = new byte[0];
        if (StringUtils.isEmpty(urlPath)) {
            return data;
        }
        //通过输入流获取图片数据
        InputStream inputStream = null;
        if (urlPath.startsWith(HTTP_LOWER) || urlPath.startsWith(HTTP_UPPER)) {
            //new一个URL对象
            URL url;
            //打开链接
            HttpURLConnection conn = null;
            try {
                url = new URL(urlPath);
                conn = (HttpURLConnection) url.openConnection();
                //设置超时时间为5秒
                conn.setConnectTimeout(5 * 1000);
                //防止屏蔽程序抓取而返回403错误
                conn.setRequestProperty("User-Agent",
                        "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                //通过输入流获取图片数据
                inputStream = conn.getInputStream();
            } catch (Exception e) {
                LOGGER.error("获取网络照片输入流报错");
            }
        } else {
            //默认为本地图片
            File file = new File(urlPath);
            try {
                inputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                LOGGER.error("获取本地照片输入流报错");
            }
        }

        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        try {
            data = readInputStream(inputStream);
        } catch (IOException e) {
            LOGGER.error("获取二进制数据报错");
        }
        return data;
    }

    /**
     * 从网上拉取照片信息或者本地获取照片并且保存
     *
     * @param urlPath
     * @param savePath
     */
    public static boolean saveImageForUrl(String urlPath, String savePath, String fileName) {
        byte[] imageByteArray = getImageByteOutputStreamFromUrl(urlPath);
        //保存图片
        try {
            //判断该路径是否存在
            File fileDirectory = new File(savePath);
            if (!fileDirectory.isDirectory()) {
                fileDirectory.mkdirs();
            }
            File imageFile = new File(savePath + fileName);
            //创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            //写入数据
            outStream.write(imageByteArray);
            //关闭输出流
            outStream.close();
        } catch (Exception e) {
            LOGGER.error("存储照片出错");
            return false;
        }
        return true;
    }


    /**
     * 读取输入流
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    public static void main(String[] args) {
        //保存图片
        String urlPath = "http://img.zcool.cn/community/01ad8e572b1a4a32f875a3998a937b.jpg@1280w_1l_2o_100sh.jpg";
        String savePath = "d:/MyRunDataLog/uploadimages/";
        String fileName = CreateCodeUtil.createCodeByTime();
        //成功存入
        boolean isSuccess = saveImageForUrl(urlPath, savePath, fileName + ".jpg");
        System.out.println(isSuccess);
        //再次存入
        isSuccess = saveImageForUrl(savePath + fileName + ".jpg", savePath, fileName + "2.jpg");
        System.out.println(isSuccess);

    }


}
