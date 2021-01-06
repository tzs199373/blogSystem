package com.example.blogengine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({"com.example.blogengine","com.example.common.component"})
@MapperScan("com.example.blogengine.mapper")
public class BlogengineApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogengineApplication.class, args);
	}

}
