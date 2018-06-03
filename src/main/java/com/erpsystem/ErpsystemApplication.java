package com.erpsystem;

import com.erpsystem.sample.PopulateDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ErpsystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ErpsystemApplication.class, args);
    }

    @Autowired
    private PopulateDatabase populateDatabase;

    @Override
    public void run(String... args) throws Exception {
        populateDatabase.resetDatabase();
        populateDatabase.populateDatabaseWithSampleData();
    }
}
