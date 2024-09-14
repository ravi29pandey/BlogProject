package com.ravi.blog.main;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.ravi.blog.controllers","com.ravi.blog.services","com.ravi.blog.services.impl"
,"com.ravi.blog.exceptions","com.ravi.blog.payloads"})
@EnableJpaRepositories(basePackages = {"com.ravi.blog.repositories"})
@EntityScan(basePackages = {"com.ravi.blog.entities"})
public class BlogProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogProjectApplication.class, args);

System.out.println("Hello , I'm up and running");
}
	
	
@Bean
public ModelMapper modelMapper() {
	return new ModelMapper();
	
}


}
