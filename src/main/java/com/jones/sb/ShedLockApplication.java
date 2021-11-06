package com.jones.sb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.jones.sb.model.Country;
import com.jones.sb.repository.CountryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

@SpringBootApplication
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class ShedLockApplication implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(ShedLockApplication.class);

    @Autowired
    private CountryRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(ShedLockApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

        repository.save(new Country("China", 1382050000));
        repository.save(new Country("India", 1313210000));

        repository.findAll().forEach((country) -> {
            logger.info("{}", country);
        });
	}

}
