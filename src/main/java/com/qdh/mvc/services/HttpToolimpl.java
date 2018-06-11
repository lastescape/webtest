package com.qdh.mvc.services;

import com.qdh.mvc.common.Constants;
import com.qdh.mvc.common.Utils;
import net.sf.json.JSONObject;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.stereotype.Service;

/**
 * Created by qdh on 2018/6/11.
 */
@Service("httpToolUtils")
public class HttpToolimpl implements HttpTool{

    private HttpClient httpClient;

    public HttpToolimpl() {
// Instantiate and configure the SslContextFactory
        SslContextFactory sslContextFactory = new SslContextFactory();

        // Instantiate HttpClient with the SslContextFactory
        httpClient = new HttpClient(sslContextFactory);

        // Configure HttpClient, for example:
        httpClient.setFollowRedirects(false);


    }

    @Override
    public String responseByGet(String url) throws Exception {
        // Start HttpClient
        httpClient.start();
        // get task code
        ContentResponse code_response = httpClient.GET("http://domain.com/entity/1");
        httpClient.stop();
        return code_response.getContentAsString();
    }

    /**
     * 获取用户的openId，并放入session
     * @param code 微信返回的code
     */
    @Override
    public String getOpenId(String code) {

        String resultOpenId = "";
        String oauth2_url = Constants.oauth2_url.replace("APPID", Constants.appid).replace("SECRET", Constants.appsecret).replace("CODE", code);
        System.out.println("oauth2_url:"+oauth2_url);
        JSONObject jsonObject = Utils.httpsRequestToJsonObject(oauth2_url, "POST", null);
        System.out.println("jsonObject:"+jsonObject);
        Object errorCode = jsonObject.get("errcode");
        if(errorCode != null) {
            System.out.println("code不合法");
        }else{
            String openId = jsonObject.getString("openid");
            System.out.println("openId:"+openId);
            resultOpenId = openId;
        }
        return resultOpenId;
    }
}
