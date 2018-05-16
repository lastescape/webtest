package com.qdh.mvc.db;



import com.mysql.fabric.xmlrpc.base.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by qdh on 2018/5/16.
 */
@Entity
@Table(name = "news_db")
public class NewsDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "news_title")
    private String newsTitle;
    @Column(name = "news_detail")
    private String newsDetail;
    @Column(name = "news_add_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date newsAddTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(String newsDetail) {
        this.newsDetail = newsDetail;
    }

    public Date getNewsAddTime() {
        return newsAddTime;
    }

    public void setNewsAddTime(Date newsAddTime) {
        this.newsAddTime = newsAddTime;
    }
}
