package com.haier.mywork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.haier.mywork.mapper")
public class MyworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyworkApplication.class, args);
	}

}
