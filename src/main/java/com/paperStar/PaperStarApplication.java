package com.paperStar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.sql.DataSource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@CrossOrigin
public class PaperStarApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaperStarApplication.class, args);
    }

}
