package com.jbk.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jbk.entity.Question;
import com.jbk.models.Answer;
import com.jbk.service.QuestionService;

@Controller
public class QuestionController {
	
	@Autowired
	QuestionService service;

	@RequestMapping("/next")
	public ModelAndView next(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView();

		try {
			HttpSession session = req.getSession();
			int nextQno = (Integer) session.getAttribute("qno") + 1;
			ArrayList<Question> al = (ArrayList<Question>) session.getAttribute("questions");

			if (nextQno == al.size()) {
				mv.addObject("message", "Question are over . click on previous or end exam");
			}

			mv.setViewName("questions");

			if (nextQno < al.size()) {
				Question question = al.get(nextQno);
				mv.addObject("question", question);
				session.setAttribute("qno", nextQno);
				return mv;
			} else {
				Question question = al.get(al.size() - 1);
				mv.addObject("question", question);
				return mv;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}

	@RequestMapping("submitAns")
	public void submitAnswer(Answer answer, HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Inside submitAnswer");

		HttpSession session = req.getSession();
		System.out.println(answer);

		HashMap<Integer, Answer> hm = (HashMap<Integer, Answer>) session.getAttribute("submittedDetails");
		hm.put(answer.qno, answer);

		session.setAttribute("submittedDetails", hm);
	}

	@RequestMapping("/endExam")
	public ModelAndView endExam(HttpServletRequest req, HttpServletResponse res)
	{
		int totalScore = 0;
		HttpSession session = req.getSession();

		HashMap<Integer, Answer> hm = (HashMap<Integer, Answer>) session.getAttribute("submittedDetails");
		Collection<Answer> c = hm.values();

		for (Answer ans : c) {
			System.out.println(ans.submittedAnswer + " " + ans.originalAnswer);
		}

		for (Answer obj : c) {
			if (obj.originalAnswer.equals(obj.submittedAnswer)) {
				totalScore++;				
			}
		}
		int totalCount = service.getQuestionsCount();
		
		int result = (totalScore * 100)/totalCount;
		return new ModelAndView("result", "score", result);
	}

	@RequestMapping("/previous")
	public ModelAndView previous(HttpServletRequest req, HttpServletResponse res) {

		HttpSession session = req.getSession();
		ArrayList<Question> al = (ArrayList<Question>) session.getAttribute("questions");

		int preQno = (Integer) session.getAttribute("qno") - 1;

		if (preQno < 0)
			preQno = al.size() - 1;

		session.setAttribute("qno", preQno);
		Question question = al.get(preQno);

		ModelAndView mv = new ModelAndView();
		mv.addObject("question", question);
		mv.setViewName("questions");

		return mv;
	}
}
