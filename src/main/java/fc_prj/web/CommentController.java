package fc_prj.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fc_prj.domain.Comment;
import fc_prj.domain.CommentRepository;
import fc_prj.domain.Question;
import fc_prj.domain.QuestionRepository;
import fc_prj.domain.User;

@Controller
//@RequestMapping("/qna/{questionId}/comments")
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("/qna/{questionId}/addComment")
	public String addComment(@PathVariable Long questionId, @RequestParam("textarea_content") String contents, HttpSession session) {
		
		System.out.println(contents);
		Object objTempUser = session.getAttribute("sessionedUser");
		if(objTempUser == null) {
			return "redirect:/users/loginForm";
		}
		User loginUser = (User)objTempUser;
		
		/* 
		 * HttpSessionUtils.isLoginUser()
		 * HttpSessionUtils.getUserFromSession(session)
		 */
		
		try {
			Question bindedQuestion = questionRepository.findOne(questionId);
			System.out.println("addComment() : after getQid");
			
			Comment newComment = new Comment(bindedQuestion, loginUser, contents);
			System.out.println("and you");
			commentRepository.save(newComment);
			System.out.println("and you 2");
		} catch(Exception e) {
			System.out.println("!! Exception - Comment controler - addComment - getOne(id) of Question");
			System.out.println(e.getMessage());
		}	
		
		return "redirect:/qna/{questionId}";
		
	}
}
