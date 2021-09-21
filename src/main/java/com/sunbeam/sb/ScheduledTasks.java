package com.sunbeam.sb;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoClient;
import com.sunbeam.sb.controller.CountryController;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;


@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	private CountryController scCountryController;

//	@Scheduled(fixedRate = 5000)
//	@SchedulerLock(name = "scheduledTaskName")
    @Scheduled(cron = "0 0/15 * * * ?")
    @SchedulerLock(name = "TaskScheduler_scheduledTask", 
      lockAtLeastFor = "PT5M", lockAtMostFor = "PT14M")
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		scCountryController.getAllCountries();
	}
	
	@Bean
	public LockProvider lockProvider(MongoClient mongo) {
	    return new MongoLockProvider(mongo.getDatabase("testdb"));
	}
}