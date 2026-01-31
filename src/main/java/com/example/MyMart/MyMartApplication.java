package com.example.MyMart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MyMartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMartApplication.class, args);
	}

}
