package com.qdh.mvc.db;

import javax.persistence.*;

/**
 * Created by qdh on 2018/6/4.
 */
@Entity
@javax.persistence.Table(name = "product_div")
public class ProductDiv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_div_name")
    private String product_div_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_div_name() {
        return product_div_name;
    }

    public void setProduct_div_name(String product_div_name) {
        this.product_div_name = product_div_name;
    }
}
