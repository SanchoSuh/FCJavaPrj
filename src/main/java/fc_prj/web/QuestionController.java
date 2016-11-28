package fc_prj.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fc_prj.domain.Question;
import fc_prj.domain.QuestionRepository;

@Controller
public class QuestionController {

//	ArrayList<Question> qList = new ArrayList<>();

	@Autowired
	private QuestionRepository questionRepository; 
	
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("qList", questionRepository.findAll());
		return "/index";
	}
	
	@GetMapping("/qna/form")
	public String form(Model model) {
		return "/qna/form";
	}
	
	@PostMapping("/qna/create")
	public String create(Question question) {
//		qList.add(question);
		questionRepository.save(question);
//		System.out.println(qList);
		return "redirect:/";
	}
}
