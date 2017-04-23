package com.book.repository.impl;

import com.book.controller.HomeController;
import com.book.model.Role;
import com.book.model.User;
import com.book.repository.CustomRepository;
import com.book.vo.UserVO;
import org.apache.log4j.Logger;
import org.hibernate.jpa.criteria.path.RootImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Hung on 4/23/2017.
 */
@Repository
public class CustomRepositoryImpl implements CustomRepository {
    private final static Logger log = Logger.getLogger(HomeController.class);


    @Autowired
    private EntityManager em;

    @Override
    public List<UserVO> getUserVOByUserName(String username) {
        List<UserVO> userVOs = null;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        log.info(em);
        Query q = em.createNativeQuery("select email, password, role.role_type from user left join role on user.role_key " +
                "= role.role_key where email = ?;");
        q.setParameter(1, username);
        List<Object[]> lst = q.getResultList();
        Iterator iterator = lst.iterator();
        while (iterator.hasNext()){
            userVOs = new ArrayList<>();
            Object[] objects = (Object[])iterator.next();
            UserVO userVO = new UserVO((String)objects[0], (String)objects[1], (String)objects[2]);
            userVOs.add(userVO);
            log.info(objects[1]);
        }
        return userVOs;
    }
}
