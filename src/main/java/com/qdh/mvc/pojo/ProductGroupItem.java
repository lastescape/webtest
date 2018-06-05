package com.qdh.mvc.pojo;

import com.qdh.mvc.db.ProductInfo;

import java.util.List;

/**
 * Created by qdh on 2018/6/4.
 */
public class ProductGroupItem {

    private String groupName;

    private List<ProductInfo> productList;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ProductInfo> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductInfo> productList) {
        this.productList = productList;
    }
}
