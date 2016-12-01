package fc_prj.web;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fc_prj.domain.User;
import fc_prj.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
//	private ArrayList<User> users = new ArrayList<>();
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";  // html을 붙이지 않아도 list.html을 콜한
	}
	
	@PostMapping("")
	public String create(User user) {
		System.out.println("user : " + user);
//		users.add(user);
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		
		if(user == null) {
			System.out.println("login() : user null");
			
			return "redirect:/users/loginForm";
		}
		
		if(!password.equals(user.getPassword())) {
			System.out.println("login() : pw wrong");
			return "redirect:/users/loginForm";
		}
		
		if(!user.checkPassword(password)) {
			
		}
		
		session.setAttribute("sessionedUser", user);
		System.out.println("success");
		
		return "redirect:/" ;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionedUser");
		return "redirect:/";
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		return "/user/form";
	}
	
	@GetMapping("/{id}/form")
	public String modifyForm(User user, @PathVariable Long id, HttpSession session, Model model) {
		Object tempUser = session.getAttribute("sessionedUser");
		if(tempUser == null)
			return "redirect:/users/loginForm";
		
		User loginUser = (User)tempUser;
		if(!loginUser.matchId(id)) {
			throw new IllegalStateException("Can't modify others information");
		}
		
		User dbUser = userRepository.findOne(id);
		dbUser.setUser(user);
		userRepository.save(dbUser);
		
		model.addAttribute("user", dbUser);
		
		System.out.println("i'm here");
		return "/user/modify_form";
	}
	
	@PostMapping("/{id}/form")
	public String modifyUser(User user, Model model, @PathVariable Long id) {
		User tempUser = userRepository.findOne(id);	
		
		if(tempUser.setUser(user)) {
			userRepository.save(tempUser);
			System.out.println("saved");
		} else {
			System.out.println("Password error");
		}
				
		return "redirect:/users";
	}
	
	/*
	@GetMapping("/{id}")
	public String show(@PathVariable String id) {
		// blabla
	}*/

	
}
