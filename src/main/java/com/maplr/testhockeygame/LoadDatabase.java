package com.maplr.testhockeygame;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(TeamRepository teamRepository) {

    return args -> {
      log.info("Preloading " + teamRepository.save(new Team("Dominique Ducharme", 2019)));
      log.info("Preloading " + teamRepository.save(new Team("Dominique Ducharme", 2020)));
    };
  }

}