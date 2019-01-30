package com.adpguima.web.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adpguima.web.core.service.CustomerService;

@Controller
public class AdminController {

	@Autowired
	CustomerService customerService;

	@ResponseBody
	@RequestMapping(value = { "/", "/admin" })
	public String home() {
		return "Welcome Admin!";
	}

	@RequestMapping("/welcome/{userId}")
	public ModelAndView welcome(@PathVariable("userId") Integer id) {
		ModelAndView model = new ModelAndView("welcome");

		model.addObject("name", customerService.findNameById(id));

		return model;
	}
}
