package com.qdh.mvc.controller;

import com.qdh.mvc.common.Utils;
import com.qdh.mvc.db.ProductDiv;
import com.qdh.mvc.db.ProductInfo;
import com.qdh.mvc.pojo.NewsIndexInfo;
import com.qdh.mvc.pojo.ProductGroupItem;
import com.qdh.mvc.pojo.UserInfo;
import com.qdh.mvc.qcloud.CosnTool;
import com.qdh.mvc.repositoryofdb.HibernateProductDivRepository;
import com.qdh.mvc.repositoryofdb.HibernateProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private HibernateProductDivRepository hibernateProductDivRepository;

    @RequestMapping(value = "/allProduct", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductInfo> dbTest() {
        List<ProductInfo> productInfoList = new ArrayList<>();
        productInfoList = hibernateProductInfoRepository.findAll();
        return productInfoList;
    }

    @RequestMapping(value = "/allProductByGroup", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductGroupItem> getProductByGroup() {
        List<ProductInfo> productInfoList = new ArrayList<>();
        productInfoList = hibernateProductInfoRepository.findAll();
        Set<String> groupName = new HashSet<>();
        for (ProductInfo item : productInfoList) {
            groupName.add(item.getProduct_div());
        }
        List<ProductGroupItem> productGroupList = new ArrayList<>();
        for (String item : groupName) {
            ProductGroupItem productGroupItem = new ProductGroupItem();
            productGroupItem.setGroupName(item);
            List<ProductInfo> tempProductInfo = new ArrayList<>();
            for (ProductInfo item2 : productInfoList) {
                if (item2.getProduct_div().equals(item)) {
                    ProductInfo item3 = new ProductInfo();
                    item3.setProduct_div(item2.getProduct_div());
                    item3.setProduct_comment(item2.getProduct_comment());
                    item3.setProduct_price(item2.getProduct_price());
                    item3.setProduct_code(item2.getProduct_code());
                    item3.setId(item2.getId());
                    item3.setProduct_name(item2.getProduct_name());
                    tempProductInfo.add(item3);
                }
            }
            productGroupItem.setProductList(tempProductInfo);
            productGroupList.add(productGroupItem);
        }
        return productGroupList;
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

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        model.setViewName("loginPage");
        return model;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView toNewsDetail(@PathVariable int id) {
        ModelAndView model = new ModelAndView("dl-kendo-news-detail/dl-kendo-news-detail");
        model.addObject("newsDetailId", id);
        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ProductInfo upLoadImage(HttpServletRequest request, @RequestParam("product_comment") CommonsMultipartFile file,
                                @RequestParam("product_name") String product_name,
                                @RequestParam("product_code") String product_code,
                                @RequestParam("product_price") String product_price,
                                @RequestParam("product_div") String product_div, ModelMap model) throws Exception {

        CosnTool cosnTool = new CosnTool();
        cosnTool.init();
        Boolean uploadResult = false;
        ProductInfo productInfo = new ProductInfo();
        try {
            uploadResult = cosnTool.saveToCosn("kendo-picture-bucket-1256125960", "wx-mini-program-product-pic" , "p_" + product_code + ".jpg", file);
            if (uploadResult) {
                productInfo.setProduct_name(Utils.encodeStr(product_name));
                productInfo.setProduct_code(Utils.encodeStr(product_code));
                productInfo.setProduct_price(Double.parseDouble(product_price));
                productInfo.setProduct_comment(
                        Utils.makeCosnUrl("kendo-picture-bucket-1256125960", "wx-mini-program-product-pic" ,"p_" + product_code + ".jpg"));
                productInfo.setProduct_div(Utils.encodeStr(product_div));
                hibernateProductInfoRepository.insertOrUpdate(productInfo);
                return productInfo;
            }
        } catch (Exception e) {
            throw e;
        }
        cosnTool.shutDownClient();
        return productInfo;
    }

    @RequestMapping(value = "/productDiv", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductDiv> getProductDiv() {
        return hibernateProductDivRepository.findAll();
    }

    @RequestMapping(value = "/insertProDiv", method = RequestMethod.GET)
    @ResponseBody
    public ProductDiv insertProDiv(@RequestParam("divName") String divName){
        return hibernateProductDivRepository.insertDiv(divName);
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
    @ResponseBody
    public String deleteProduct(@RequestParam("deleteCode") String code) throws Exception {
        ProductInfo productInfo = hibernateProductInfoRepository.findProductByCode(code);
        String productImageUrl = productInfo.getProduct_comment();
        CosnTool cosnTool = new CosnTool();
        cosnTool.init();
        try {
            cosnTool.deleteFromCosn(productImageUrl);
        } catch (Exception e) {
            throw e;
        }
        cosnTool.shutDownClient();
        hibernateProductInfoRepository.deleteProductByCode(productInfo.getProduct_code());
        return "success";
    }

}
