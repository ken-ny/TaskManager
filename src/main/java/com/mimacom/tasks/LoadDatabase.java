package com.mimacom.tasks;

import com.mimacom.tasks.entities.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TasksRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Task("Code for mimacom", true)));
            log.info("Preloading " + repository.save(new Task("Deliver Code", false)));
        };
    }
}
