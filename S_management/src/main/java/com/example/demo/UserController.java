package com.example.demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller

public class UserController {
@Autowired
	private UserRepository userRepository;
@Autowired
	UserService userService;
@RequestMapping(value = "/all", method = RequestMethod.GET)
public String getAllUsers(@Validated User user,  BindingResult result,
							@PageableDefault(size = 10) Pageable pageable,
							Model model) {


	Page<User> wordPage = userService.searchUser(pageable);
	PageWrapper<User> page = new PageWrapper<User>(wordPage, "/all");
	model.addAttribute("page", page);
	model.addAttribute("users", page.getContent());
	return "index";
	}


@GetMapping(value = "/add")
	public String displayAdd(Model model) {
	model.addAttribute("user", new User());
	return "add";
	}
}
