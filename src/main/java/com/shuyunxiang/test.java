package com.shuyunxiang;

import com.shuyunxiang.SpringConfig.SpringConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class test {
    public static void main(String[] args) {
//        必须有@SpringBootApplication
            SpringApplication.run(SpringConfig.class, args);

    }
}
