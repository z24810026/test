package com.example.MyFinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.MyFinance")
public class MyFinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFinanceApplication.class, args);
	}

}
