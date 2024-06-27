package com.example.blogapis;

import com.example.blogapis.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApisApplicationTests {
    @Autowired
    UserRepo userRepo;
    @Test
    void contextLoads() {
    }

    @Test
    public void getName(){

        System.out.println(userRepo.getClass().getName());
        System.out.println(userRepo.getClass().getPackageName());
    }
}
