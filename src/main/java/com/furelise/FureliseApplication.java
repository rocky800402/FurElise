package com.furelise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan
public class FureliseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FureliseApplication.class, args);
	}

}
