package com.jbk.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jbk.entity.Question;
import com.jbk.entity.User;
import com.jbk.models.Answer;
import com.jbk.service.QuestionService;

@Controller
public class LoginController {

	@Autowired
	QuestionService service;

	// http://localhost:8085/login ? name=jbk & pass=jbk
	@RequestMapping("/login")
	public ModelAndView login(User user, HttpServletRequest request) {
		List<Question> list = service.getAllQuestions();

		if (service.validate(user)) {
			// put questions in session

			HttpSession session = request.getSession();
			session.setAttribute("questions", list);
			session.setAttribute("qno", 0);
			session.setAttribute("score", 0);
			session.setAttribute("submittedDetails", new HashMap<Integer, Answer>());

			ModelAndView mv = new ModelAndView();
			mv.setViewName("questions");
			mv.addObject("wel", "welcome to online exam " + user.getName());
			mv.addObject("question", list.get(0));
			return mv;
		}

		else {
			return new ModelAndView("login", "error", "Invalid Password");
		}
	}

	@RequestMapping("/add")
	public ModelAndView add(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		service.insert(user);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("home");
		modelAndView.addObject("success", "Registration successful.please login");

		return modelAndView;
	}

	@RequestMapping("/registration")
	public ModelAndView showRegistration(HttpServletRequest req, HttpServletResponse res) {		
		return new ModelAndView("registration");
	}

	@RequestMapping("/")
	public String home() {
		return "login";
	}
}
