package fc_prj.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fc_prj.domain.Comment;
import fc_prj.domain.Question;
import fc_prj.domain.QuestionRepository;
import fc_prj.domain.User;
import fc_prj.domain.UserRepository;

@Controller
public class QuestionController {

//	ArrayList<Question> qList = new ArrayList<>();

	@Autowired
	private QuestionRepository questionRepository; 
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("qList", questionRepository.findAll());
		return "/index";
	}
	
	@GetMapping("/qna/form")
	public String form(Model model, HttpSession session) {
		if(session.getAttribute("sessionedUser") == null)
			return "redirect:/users/loginForm";
		return "/qna/form";
	}
	
	@PostMapping("/qna/create")
	public String create(Question question, HttpSession session) {
		Object objTempUser = session.getAttribute("sessionedUser");
		if(objTempUser == null) {
			return "redirect:/users/loginForm";
		}
		
		User loginUser = (User)objTempUser;
		question.setWriter(loginUser);
		
		questionRepository.save(question);
//		System.out.println(qList);
		return "redirect:/";
	}
	
	@GetMapping("/qna/{id}")
	public String showArticle(@PathVariable Long id, Model model) {
		Question question = questionRepository.getOne(id);
		List<Comment> comments = question.getComments();
		
		System.out.println(question.getTitle());
		System.out.println(comments.size());
	
		model.addAttribute("question", question);
		model.addAttribute("comments", comments);
		
		return "/qna/show";
	}

}
