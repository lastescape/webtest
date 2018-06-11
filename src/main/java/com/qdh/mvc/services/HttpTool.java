package com.qdh.mvc.services;

import org.springframework.stereotype.Service;

/**
 * Created by qdh on 2018/6/11.
 */

public interface HttpTool {
    String responseByGet(String url) throws Exception;
    String getOpenId(String code);
}
