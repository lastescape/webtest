package com.qdh.mvc.db;

import org.hibernate.annotations.Table;

import javax.persistence.*;

/**
 * Created by qdh on 2018/4/28.
 */
@Entity
@javax.persistence.Table(name = "product_info")
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_code")
    private String product_code;
    @Column(name = "product_name")
    private String product_name;
    @Column(name = "product_price")
    private Double product_price;
    @Column(name = "product_comment")
    private String product_comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public String getProduct_comment() {
        return product_comment;
    }

    public void setProduct_comment(String product_comment) {
        this.product_comment = product_comment;
    }
}
