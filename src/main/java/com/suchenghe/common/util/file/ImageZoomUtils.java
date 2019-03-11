package com.suchenghe.common.util.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @author SuChenghe
 * @date 2018/10/17 19:10
 */
public class ImageZoomUtils {

    final static Logger LOGGER = LoggerFactory.getLogger(ImageZoomUtils.class);

    private final static DecimalFormat DF_ONE_DECIMAL = new DecimalFormat("0.0");
    private final static DecimalFormat DF_ZERO_DECIMAL = new DecimalFormat("0");

    /**
     * 缩放图片
     *
     * @param src
     * @param dest
     * @param maxSize  图片大小，单位为k
     * @param type     图片类型
     * @param zooParam 缩放参数,参数越小,越接近指定大小，但处理时间也会增长,默认0.05
     */
    public static int zooImage(String src, String dest, Integer maxSize, String type, Float zooParam) {
        long startTime = System.currentTimeMillis();
        File srcFile = new File(src);
        //原图片大小
        long fileSize = srcFile.length();
        //文件大于size k时,才进行缩放,注意:size以K为单位
        if (fileSize < maxSize * 1024) {
            return 0;
        }
        //读取照片信息
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(srcFile);
        } catch (IOException e) {
            LOGGER.error("读取照片信息出错");
        }
        //原照片的高度和宽度
        int originWidth = bufferedImage.getWidth();
        int originHeight = bufferedImage.getHeight();

        //生成图片
        BufferedImage outPutImage = null;
        byte[] imageByte = new byte[0];
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //获取长宽缩放比例(默认从1开始)
        Float rate = 1f;
        if (null == zooParam || zooParam == 0) {
            zooParam = 0.05f;
        } else if (zooParam >= 1) {
            return -1;
        }
        //循环处理，直到符合指定的大小
        while (imageByte.length == 0 || imageByte.length > maxSize * 1024) {
            try {
                os.close();
                os = new ByteArrayOutputStream();
                //调整后的图片的宽度和高度
                int width = (int) (originWidth * rate);
                int height = (int) (originHeight * rate);
                outPutImage = getBufferedImage(bufferedImage, width, height);
                ImageIO.write(outPutImage, type, os);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //获取照片数据流大小
            imageByte = os.toByteArray();
            rate = rate - zooParam;
        }

        try {
            ImageIO.write(outPutImage, type, new File(dest));
        } catch (IOException e) {
            LOGGER.error("写入照片信息出错");
        }

        LOGGER.info("原图片大小:{},最终大小:{},缩放次数:{},耗时:{}", DF_ONE_DECIMAL.format(fileSize / 1024) + "KB", DF_ONE_DECIMAL.format(imageByte.length / 1024) + "KB",
                DF_ZERO_DECIMAL.format((1 - rate) / zooParam), (System.currentTimeMillis() - startTime) + "毫秒");

        return 1;

    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        File dir = new File("D:\\file\\lishuiphoto");
        File[] subFiles = dir.listFiles();
        int n = 0;
        for (File sub : subFiles) {
            String dest = "D:\\file\\lishuiphoto\\" + sub.getName();
            int typePosition = dest.lastIndexOf(".");
            zooImage(sub.getAbsolutePath(), dest, 400, dest.substring(typePosition + 1), 0.05f);
        }
        System.out.println("共耗时:" + (System.currentTimeMillis() - startTime) + "毫秒");
    }

    /**
     * 生成结果图片
     */
    private static BufferedImage getBufferedImage(BufferedImage bufferedImage, int width, int height) {
        Image image = bufferedImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
        //构造一个类型为预定义图像类型之一的BufferedImage
        BufferedImage outPutImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //创建一个Graphics2D,可以将它绘制到此 BufferedImage中
        Graphics graphics = outPutImage.getGraphics();
        //控制颜色
        graphics.setColor(Color.white);
        //使用Graphics2D上下文的设置,填充Shape的内部区域。
        graphics.fillRect(0, 0, width, height);
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();

        return outPutImage;
    }

}
