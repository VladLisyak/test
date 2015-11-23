package com.lisyak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LinkController {


	@RequestMapping(value="/")
	public ModelAndView mainPage() {
		return indexPage();
	}
	
	@RequestMapping(value="/index")
	public ModelAndView indexPage() {
		return new ModelAndView("/WEB-INF/index.html");
	}
}
