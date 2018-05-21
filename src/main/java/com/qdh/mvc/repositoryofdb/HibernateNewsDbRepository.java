package com.qdh.mvc.repositoryofdb;

import com.qdh.mvc.common.Utils;
import com.qdh.mvc.db.NewsDb;
import com.qdh.mvc.db.ProductInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by qdh on 2018/4/28.
 */
@Repository(value = "hibernateNewsDbRepository")
public class HibernateNewsDbRepository {
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
    public List<NewsDb> findAll() {
        return (List<NewsDb>) currectSession().createCriteria(NewsDb.class).list();
    }
    @Transactional(transactionManager = "txManager")
    public NewsDb insertOrUpdate(NewsDb input){
        if (input.getId() == 0){
            input.setNewsAddTime(Utils.currentDate());
            currectSession().save(input);
        } else {
            NewsDb newsDb = (NewsDb)currectSession().load(NewsDb.class, input.getId());
            newsDb.setNewsTitle(input.getNewsTitle());
            newsDb.setNewsDetail(input.getNewsDetail());
            newsDb.setNewsAddTime(input.getNewsAddTime());
            currectSession().saveOrUpdate(newsDb);
        }
        return input;
    }
    @Transactional(transactionManager = "txManager", readOnly = true)
    public NewsDb findNewsById(int id) {
        List output = currectSession().createCriteria(NewsDb.class)
                .add(Restrictions.eq("id", id)).list();
        if (output.size() > 0) {
            return (NewsDb) output.get(0);
        } else {
            return new NewsDb();
        }
    }

    @Transactional(transactionManager = "txManager", readOnly = true)
    public List<NewsDb> queryForPage(int offset, int length) {
        List<NewsDb> outputList = null;
        try {
            Query query = currectSession().createQuery("from NewsDb nd order by nd.newsAddTime desc ");
            query.setFirstResult(offset);
            query.setMaxResults(length);
            outputList = (List<NewsDb>)query.list();
        } catch (Exception e) {
            throw e;
        }
        return outputList;
    }
}
