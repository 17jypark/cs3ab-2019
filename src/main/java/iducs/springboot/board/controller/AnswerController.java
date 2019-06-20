package iducs.springboot.board.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import iducs.springboot.board.domain.Answer;
import iducs.springboot.board.domain.Board;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.exception.ResourceNotFoundException;
import iducs.springboot.board.repository.UserRepository;
import iducs.springboot.board.service.AnswerService;
import iducs.springboot.board.service.BoardService;
import iducs.springboot.board.service.UserService;
import iducs.springboot.board.util.HttpSessionUtils;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	@Autowired AnswerService answerService; // 의존성 주입(Dependency Injection) 
	@Autowired BoardService questionService;
		
	@PostMapping("")
	// public String createUser(Answer answer, Model model, HttpSession session) {
	public String createAnswer(@PathVariable Long questionId, String contents,HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		Board question = questionService.getQuestionById(questionId);
		Answer newAnswer = new Answer(sessionUser, question, contents);
		answerService.saveAnswer(newAnswer);
		return String.format("redirect:/questions/%d", questionId);
	}
	
	@GetMapping("/{id}/edit")
	public String editQuestion(@PathVariable(value = "id") Long id, Model model, HttpSession session) {
		User writer = (User) session.getAttribute("user");
		if(HttpSessionUtils.isLogined(writer))
				return "redirect:/users/login-form";
		model.addAttribute("writer", writer);
		Answer answer = answerService.getAnswerById(id);
		model.addAttribute("answer", answer);
		
		return "/questions/edit2";
	}
	
	@PutMapping("/{id}")
	public String updateQuestionById(@PathVariable(value = "id") Long id, @Valid Board formQuestion, String contents, Model model) {
		Answer answer = answerService.getAnswerById(id);
		answer.setTitle(formQuestion.getTitle());
		answer.setContents(formQuestion.getContents());
		
		answerService.updateAnswer(answer);
		model.addAttribute("answer", answer);
		
		return "redirect:/questions/";
	}
	@DeleteMapping("/{id}")
	public String deleteQuestionById(@PathVariable(value = "id") Long id, Model model) {
		Answer answer = answerService.getAnswerById(id);
		answerService.deleteAnswer(answer);
		model.addAttribute("userId", answer.getWriter().getUserId());
		return "redirect:/questions";
	}
	

}
