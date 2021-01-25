package com.example.demo;

import com.example.demo.Controllers.ProductController;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    private static ProductController PC;

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}
