package fc_prj.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fc_prj.model.Question;

@Controller
public class QuestionController {

	ArrayList<Question> qList = new ArrayList<>();
	
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("qList", qList);
		return "/index";
	}
	
	@GetMapping("/qna/form")
	public String form(Model model) {
		return "/qna/form";
	}
	
	@PostMapping("/qna/create")
	public String create(Question question) {
		qList.add(question);
		System.out.println(qList);
		return "redirect:/";
	}
}
