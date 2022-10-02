package com.qipt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("com.qipt.mapper")
@SpringBootApplication
public class BlogBeApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BlogBeApplication.class, args);
    }
    
}
