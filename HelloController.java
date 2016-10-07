package com.sasanka.web.controller;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONStreamAware;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping(value =  "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("title", "Movie Flex Application");
		model.addObject("message", "This is protected page!");
		
		model.setViewName("admin");

		return model;

	}
	
	@RequestMapping(value = "/jsondata", method = RequestMethod.GET)
	public ModelAndView jsonPage() throws IOException, ParseException{
		
		JSONParser parser = new JSONParser();
		InputStream is = getClass().getResourceAsStream("/movielist.json");
		JSONArray json = (JSONArray) parser.parse(new InputStreamReader(is));
		
		ModelAndView model = new ModelAndView();
		model.addObject("JSON", json.toString());
		return model;
		
	}

	@RequestMapping(value = {"/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

}