package com.suchenghe.common.util;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author SuChenghe
 * @date 2018/5/3 15:28
 */
public class CreateCodeUtil {
    /**
     * 生成随机数编号
     *
     * @return
     */
    private static FastDateFormat fdfCode = FastDateFormat.getInstance("yyyyMMddHHmmssSSS");

    /**
     * 生成32位全局唯一ID
     *
     * @return uuid
     */
    public static String createCodeByUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String createCodeByTime() {
        String sdf = fdfCode.format(new Date());
        int num = ThreadLocalRandom.current().nextInt(9000) + 1000;
        return sdf + num;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + "_____" + createCodeByUUID());
        }


    }


}
