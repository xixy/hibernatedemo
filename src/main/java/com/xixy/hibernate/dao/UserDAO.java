package com.xixy.hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xixy.hibernate.beans.User;
import com.xixy.hibernate.util.HibernateSessionFactory;

public class UserDAO {
	public User getUser(String username) throws HibernateException {
		Session session = null;
		Transaction tx = null;
		User user = null;
		try {
			session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from User where username=?");
			query.setString(0, username.trim());
			user = (User) query.uniqueResult();
			query = null;
			tx.commit();
		} catch (HibernateException e) {
			throw e;
		} finally {
			if (tx != null) {
				tx.rollback();
			}
			HibernateSessionFactory.closeSession();
		}
		return user;
	}

}
