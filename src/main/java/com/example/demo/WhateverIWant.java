package com.example.demo;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.doa.TeamDao;
import com.example.demo.domain.Team;

@Controller
@RestController
public class WhateverIWant {
	
	private Team team;
	
	@Autowired
	TeamDao teamDao;
	
	@PostConstruct
	public void init() {
		// moving below code to the Application class as we are using Spring Data
		
		/*Set<Player> players = new HashSet();
		players.add(new Player("Charlie Brown", "pitcher"));
		players.add(new Player("Snoopy", "shortstop"));
		
		team = new Team("California", "Peanuts", players);*/
	}
	
	@RequestMapping("/test")
	public @ResponseBody String test() {
		return "hello world!";
	}
	
	// Note: if we are annotating the class with @RestContoller then Map will be ignored and the response will not get anything from the map
	@RequestMapping("/hi/{name}")
	public String hiThere(Map model, @PathVariable String name) {
		model.put("name", name);		
		return "hello";
	}
	
	@RequestMapping("/teams_nolongerused_since_usning_restresource_see_TeamDao/{name}")
	public @ResponseBody Team team(@PathVariable String name) {
			
		Team team = teamDao.findByName(name);		
		System.out.println("========> found following team: " + team);		
		return team;
	}
}
