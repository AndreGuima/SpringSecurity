package com.adpguima.web.customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adpguima.web.core.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@ResponseBody
	@RequestMapping(value = { "/", "/customer" })
	public String home() {
		return "Welcome Customer!";
	}

	@RequestMapping("/welcome/{userId}")
	public ModelAndView welcome(@PathVariable("userId") Integer id) {
		ModelAndView model = new ModelAndView("welcome");

		model.addObject("name", customerService.findNameById(id));

		return model;
	}
}
