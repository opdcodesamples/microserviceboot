package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.example.demo.doa.TeamDao;
import com.example.demo.domain.Player;
import com.example.demo.domain.Team;

@SpringBootApplication
public class MicroserviceBootApplication extends SpringBootServletInitializer {

	@Autowired
	TeamDao teamDao;
	
	/**
	 * when I am running as a jar this method will be used
	 * @param args
	 */
	public static void main(String[] args) {
		//starts here
		SpringApplication.run(MicroserviceBootApplication.class, args);
	}
	
	
	/**
	 * when I am running as a war this method will be used
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
			
		return builder.sources(MicroserviceBootApplication.class);
	}
	
	@PostConstruct
	public void init() {
		
		Set<Player> players = new HashSet();
		players.add(new Player("Charlie Brown", "pitcher"));
		players.add(new Player("Snoopy", "shortstop"));
		
		Team team = new Team("California", "Peanuts", players);
		teamDao.save(team);
		System.out.println("======== >>> stored data successfully");
	}
	
	
}
