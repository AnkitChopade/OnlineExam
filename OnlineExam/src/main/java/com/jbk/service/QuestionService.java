package com.jbk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.MyDao;
import com.jbk.entity.Question;
import com.jbk.entity.User;

/* Business logic methods should be written in Service Class , 
 * which is annotated with @Service annotation
 
 * In service class , we define reference of DAO class.
 */

@Service
public class QuestionService {

	@Autowired
	MyDao dao;

	public List<Question> getAllQuestions() {
		return dao.getQuestions();
	}
	
	public int getQuestionsCount() {
		return dao.getQuestionsCount();
	}

	public boolean validate(User user) {
		String dbPassword = dao.getPassword(user.getName());

		if (dbPassword == null)
			return false;

		if (dbPassword.equals(user.getPass())) {
			return true;
		} else {
			return false;
		}
	}

	public void insert(User user) {
		dao.insert(user);
	}
}