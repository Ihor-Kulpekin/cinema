package com.webencyclop.demo.repository.implementation.forUser;

import com.webencyclop.demo.model.Ordering;
import com.webencyclop.demo.repository.interfaces.forUser.UserHistoryOrderingsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserHistoryOrderingsRepositoryImpl implements UserHistoryOrderingsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Ordering> userHistoryOrderings(int id) {
        Session session = sessionFactory.getCurrentSession().getSession();
        return null;
    }
}
