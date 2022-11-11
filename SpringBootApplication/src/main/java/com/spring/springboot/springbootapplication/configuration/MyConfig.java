package com.spring.springboot.springbootapplication.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.spring.springboot.springbootapplication")
@EnableWebMvc
@EnableTransactionManagement
public class MyConfig {
}
