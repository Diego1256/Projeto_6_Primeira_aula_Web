package br.com.cotiinformatica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class RegisterContrroller {
	
	@RequestMapping(value = "/register")
	public ModelAndView register(){
		
		ModelAndView modelAndView = new ModelAndView("register");
		return modelAndView;
		
	}

}
