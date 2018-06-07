package com.qdh.mvc.common;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by qdh on 2018/5/16.
 */
public class Utils {
    public static Date currentDate() {
        return new Date();
    }

    private static String beforeBuckerName = "https://";
    private static String afterBuckerName = ".cos.ap-beijing.myqcloud.com/";

    public static String makeCosnUrl(String bucketName, String filePath, String fileName) {
        String output = "";
        output = beforeBuckerName + bucketName + afterBuckerName + filePath + "/" + fileName;
        return output;
    }

    public static String encodeStr(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCosnInfoFromUrl(String imageUrl) {
        String temp1 = imageUrl.replace(beforeBuckerName, "");
        String[] temp2 = temp1.split(afterBuckerName);
        String bucketName = temp2[0];
        String[] temp3 = temp2[1].split("/");
        String filePath = temp3[0];
        String fileName = temp3[1];
        return bucketName + ":_:" + filePath + ":_:" + fileName;
    }
}
