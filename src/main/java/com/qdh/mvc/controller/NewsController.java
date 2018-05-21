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
public class NewsController {

    @Autowired
    private HibernateNewsDbRepository hibernateNewsDbRepository;

    @RequestMapping(value = "/news/update", method = RequestMethod.POST)
    @ResponseBody
    public NewsDb insertOrUpdateNews(@RequestBody NewsDb newsDb){
        hibernateNewsDbRepository.insertOrUpdate(newsDb);
        return newsDb;
    }

    @RequestMapping(value = "/news/select", method = RequestMethod.GET)
    @ResponseBody
    public NewsDb selectNewsByCode(@RequestParam("newsId") int id){
        NewsDb newsDb = hibernateNewsDbRepository.findNewsById(id);
        return newsDb;
    }

    @RequestMapping(value = "/news/all", method = RequestMethod.GET)
    @ResponseBody
    public List<NewsDb> getAllNews() {
        List<NewsDb> newsDbs = new ArrayList<>();
        newsDbs = hibernateNewsDbRepository.findAll();
        return newsDbs;
    }

    @RequestMapping(value = "/news/allcount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getAllCount() {
        return getAllNews().size();
    }

    @RequestMapping(value = "/news/pagecount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getPageCount() {
        if (Math.floorMod(getAllNews().size(), 5) == 0) {
            return Math.floorDiv(getAllNews().size(), 5);
        } else {
            return Math.floorDiv(getAllNews().size(), 5) + 1;
        }
    }

    @RequestMapping(value = "/news/pages/{page}", method = RequestMethod.GET)
    @ResponseBody
    public List<NewsDb> getNewsDetail(@PathVariable int page, @RequestParam(defaultValue = "5") int length) {
        int start_index = (page - 1) * length + 1;
        return hibernateNewsDbRepository.queryForPage(start_index, length);
    }
}
