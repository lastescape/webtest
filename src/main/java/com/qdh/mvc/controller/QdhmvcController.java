package com.qdh.mvc.controller;

import com.qdh.mvc.db.ProductInfo;
import com.qdh.mvc.pojo.NewsIndexInfo;
import com.qdh.mvc.pojo.UserInfo;
import com.qdh.mvc.repositoryofdb.HibernateProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qdh on 2018/4/10.
 */
@Controller
public class QdhmvcController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String resultOutput(@RequestParam("e") String input) {
        return input.toUpperCase();
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute(new UserInfo());
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String checkUser(@Validated UserInfo userInfo){
        return "okPage";
    }

    @Autowired
    private HibernateProductInfoRepository hibernateProductInfoRepository;

    @RequestMapping(value = "/dbtest", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductInfo> dbTest(@RequestParam("db") String db) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        productInfoList = hibernateProductInfoRepository.findAll();
        return productInfoList;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ProductInfo insertOrUpdateProduct(@RequestBody ProductInfo productInfo){
        hibernateProductInfoRepository.insertOrUpdate(productInfo);
        return productInfo;
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    public ProductInfo selectProductByCode(@RequestParam("code") String code){
        return hibernateProductInfoRepository.findProductByCode(code);
    }

    @RequestMapping(value = "/h5", method = RequestMethod.GET)
    public ModelAndView h5Show() {
        ArrayList<NewsIndexInfo> newsIndexList = new ArrayList<>();
        NewsIndexInfo newsIndexInfo1 = new NewsIndexInfo();
        newsIndexInfo1.setTarget_url("#");
        newsIndexInfo1.setTitle("第一个");
        NewsIndexInfo newsIndexInfo2 = new NewsIndexInfo();
        newsIndexInfo2.setTarget_url("#");
        newsIndexInfo2.setTitle("第二个");
        newsIndexList.add(newsIndexInfo1);
        newsIndexList.add(newsIndexInfo2);

        ModelAndView modelAndView = new ModelAndView("dl-kendo-index/dl_kendo_index");
        modelAndView.addObject("users", newsIndexList);

        return modelAndView;
    }
}
