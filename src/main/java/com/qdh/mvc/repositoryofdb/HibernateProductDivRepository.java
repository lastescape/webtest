package com.qdh.mvc.repositoryofdb;

import com.qdh.mvc.db.ProductDiv;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qdh on 2018/6/4.
 */
@Repository(value = "hibernateProductDivRepository")
public class HibernateProductDivRepository {
    @Autowired
    private SessionFactory sessionFactory;

    private Session currectSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional(transactionManager = "txManager", readOnly = true)
    public List<ProductDiv> findAll() {
        return (List<ProductDiv>) currectSession().createCriteria(ProductDiv.class).list();
    }
    @Transactional(transactionManager = "txManager")
    public ProductDiv insertDiv(String divName) {
        ProductDiv productDiv = new ProductDiv();
        productDiv.setProduct_div_name(divName);
        currectSession().saveOrUpdate(productDiv);
        return productDiv;
    }

}
