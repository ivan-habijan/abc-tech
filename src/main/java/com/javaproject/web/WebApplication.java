package com.javaproject.web;

import com.javaproject.web.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class WebApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {

//        Account account1 = new Account(1, "HR3455345442", 1, "USD", 1, "2020-10-10");
//        accountRepository.save(account1);

    }

}
