package com.jbk.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.entity.Question;
import com.jbk.entity.User;

/* DAO classes are used to write database logic
 * @Repository is used for them .
 DAO(Data Access object)*
*/

@Repository
public class MyDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session;

	public List<Question> getQuestions() {
		session = sessionFactory.openSession();
		List<Question> allQuestions = session.createQuery("from Question").list();
		return allQuestions;
	}
	
	public int getQuestionsCount() {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Question.class);
		criteria.setProjection(Projections.rowCount());
		long count = (long) criteria.uniqueResult();
		return (int)count;
	}

	public String getPassword(String name) {
		session = sessionFactory.openSession();
		System.out.println("session created..");

		System.out.println(name);
		User user = session.get(User.class, name);
		if (user == null) {
			return null;
		} else {
			return user.getPass();
		}
	}

	public void insert(User user) {
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		session.save(user);
		tx.commit();
	}
}
