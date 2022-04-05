package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.example.demo.db.GestorDB;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
	
}
