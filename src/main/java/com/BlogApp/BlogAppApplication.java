package com.BlogApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class BlogAppApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

//	@PostConstruct
//	public void init() {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		System.out.println(System.getProperty("java.class.path"));
//	}

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
}
