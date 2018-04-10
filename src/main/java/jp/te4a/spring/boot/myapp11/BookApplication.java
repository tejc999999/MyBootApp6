package jp.te4a.spring.boot.myapp11;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class BookApplication {// implements CommandLineRunner {

//	@Autowired
//	BookBean bookBean;
	
/*	@Override
	public void run(String... strings) throws Exception {
		bookBean.setTitle("タイトル");
	}*/

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}
}
