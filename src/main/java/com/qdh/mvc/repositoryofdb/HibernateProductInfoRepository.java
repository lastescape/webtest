package com.qdh.mvc.repositoryofdb;

import com.qdh.mvc.db.ProductInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;


/**
 * Created by qdh on 2018/4/28.
 */
@Repository(value = "hibernateProductInfoRepository")
public class HibernateProductInfoRepository {
    @Autowired
    private SessionFactory sessionFactory;

    private Session currectSession() {
        return sessionFactory.getCurrentSession();
    }
    @Transactional(transactionManager = "txManager", readOnly = true)
    public long count() {
        return findAll().size();
    }
    @Transactional(transactionManager = "txManager", readOnly = true)
    public List<ProductInfo> findAll() {
        return (List<ProductInfo>) currectSession().createCriteria(ProductInfo.class).list();
    }
    @Transactional(transactionManager = "txManager", readOnly = true)
    public int findIdByProductCode(String productCode){
        List output = currectSession().createCriteria(ProductInfo.class)
                .add(Restrictions.eq("product_code", productCode)).list();
        if (output.size() == 0) {
            return -1;
        } else {
            return ((ProductInfo)output.get(0)).getId();
        }
    }
    @Transactional(transactionManager = "txManager")
    public ProductInfo insertOrUpdate(ProductInfo input){
        int isHadFlag = findIdByProductCode(input.getProduct_code());
        if (isHadFlag == -1){
            currectSession().saveOrUpdate(input);
        } else {
            ProductInfo productInfo = (ProductInfo)currectSession().load(ProductInfo.class, isHadFlag);
            productInfo.setProduct_code(input.getProduct_code());
            productInfo.setProduct_name(input.getProduct_name());
            productInfo.setProduct_price(input.getProduct_price());
            productInfo.setProduct_comment(input.getProduct_comment());
            currectSession().saveOrUpdate(productInfo);
        }
        return input;
    }
    @Transactional(transactionManager = "txManager", readOnly = true)
    public ProductInfo findProductByCode(String code) {
        List output = currectSession().createCriteria(ProductInfo.class)
                .add(Restrictions.eq("product_code", code)).list();
        if (output.size() > 0) {
            return (ProductInfo) output.get(0);
        } else {
            return new ProductInfo();
        }
    }

}
