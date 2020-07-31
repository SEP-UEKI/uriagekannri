package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExcelController {

	@Autowired
	UserService userService;

	 @RequestMapping("/download/excel")
	    public ModelAndView messagesXlsx() {
		 	List<Member> members = userService.FindALL();
	        return new ModelAndView(new MessagesXlsxView(), "messages", members);
	    }
}
