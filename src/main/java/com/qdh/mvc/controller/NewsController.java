package com.qdh.mvc.controller;

import com.qdh.mvc.db.NewsDb;
import com.qdh.mvc.db.ProductInfo;
import com.qdh.mvc.repositoryofdb.HibernateNewsDbRepository;
import com.qdh.mvc.repositoryofdb.HibernateProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qdh on 2018/5/16.
 */
@Controller
@RequestMapping(value = "/news")
public class NewsController {

    @Autowired
    private HibernateNewsDbRepository hibernateNewsDbRepository;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public NewsDb insertOrUpdateNews(@RequestBody NewsDb newsDb){
        hibernateNewsDbRepository.insertOrUpdate(newsDb);
        return newsDb;
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    public NewsDb selectNewsByCode(@RequestParam("newsId") int id){
        NewsDb newsDb = hibernateNewsDbRepository.findNewsById(id);
        return newsDb;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<NewsDb> getAllNews() {
        List<NewsDb> newsDbs = new ArrayList<>();
        newsDbs = hibernateNewsDbRepository.findAll();
        return newsDbs;
    }

//    @RequestMapping(value = "/detail", method = RequestMethod.GET)
//    public String getNewsDetail() {
//        return "dl-kendo-news-detail/dl-kendo-news-detail";
//    }
}
