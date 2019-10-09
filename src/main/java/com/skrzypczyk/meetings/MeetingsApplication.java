package com.skrzypczyk.meetings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class MeetingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetingsApplication.class, args);
    }

}
