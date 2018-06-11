package com.qdh.mvc.common;

import com.qdh.mvc.pojo.PayInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
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

    /**
     * 扩展xstream使其支持CDATA
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                //增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    public static String payInfoToXML(PayInfo pi) {
        xstream.alias("xml", pi.getClass());
        return xstream.toXML(pi);
    }


    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(String xml) throws Exception {
        Map<String, String> map = new HashMap<String, String>();

        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();

        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        return map;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    public static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param
     * @return
     */
    public static String byteToHexStr(byte bytes) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(bytes >>> 4) & 0X0F];
        tempArr[1] = Digit[bytes & 0X0F];

        String s = new String(tempArr);
        return s;
    }

    /**
     * 获取ip地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            return request.getRemoteAddr();
        }
        byte[] ipAddr = addr.getAddress();
        String ipAddrStr = "";
        for (int i = 0; i < ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i] & 0xFF;
        }
        return ipAddrStr;
    }

    public static JSONObject httpsRequestToJsonObject(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            StringBuffer buffer = httpsRequest(requestUrl, requestMethod, outputStr);
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            System.out.println("连接超时："+ce.getMessage());
        } catch (Exception e) {
            System.out.println("https请求异常："+e.getMessage());
        }
        return jsonObject;
    }


    private static StringBuffer httpsRequest(String requestUrl, String requestMethod, String output)
            throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, MalformedURLException,
            IOException, ProtocolException, UnsupportedEncodingException {

        URL url = new URL(requestUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod(requestMethod);
        if (null != output) {
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(output.getBytes("UTF-8"));
            outputStream.close();
        }

        // 从输入流读取返回内容
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        StringBuffer buffer = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }

        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream = null;
        connection.disconnect();
        return buffer;
    }

    public static Map<String, String> httpsRequestToXML(String requestUrl, String requestMethod, String outputStr) {
        Map<String, String> result = new HashMap<>();
        try {
            StringBuffer buffer = httpsRequest(requestUrl, requestMethod, outputStr);
            result = Utils.parseXml(buffer.toString());
        } catch (ConnectException ce) {
            System.out.println("连接超时："+ce.getMessage());
        } catch (Exception e) {
            System.out.println("https请求异常："+e.getMessage());
        }
        return result;
    }

    /**
     * 获取签名
     * @param payInfo
     * @return
     * @throws Exception
     */
    public static String getSign(PayInfo payInfo) throws Exception {
        String signTemp = "appid="+payInfo.getAppid()
                +"&attach="+payInfo.getAttach()
                +"&body="+payInfo.getBody()
                +"&device_info="+payInfo.getDevice_info()
                +"&mch_id="+payInfo.getMch_id()
                +"&nonce_str="+payInfo.getNonce_str()
                +"&notify_url="+payInfo.getNotify_url()
                +"&openid="+payInfo.getOpenid()
                +"&out_trade_no="+payInfo.getOut_trade_no()
                +"&spbill_create_ip="+payInfo.getSpbill_create_ip()
                +"&total_fee="+payInfo.getTotal_fee()
                +"&trade_type="+payInfo.getTrade_type()
                +"&key="+Constants.key; //这个key注意

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.reset();
        md5.update(signTemp.getBytes("UTF-8"));
        String sign = Utils.byteToStr(md5.digest()).toUpperCase();
        return sign;
    }
}
