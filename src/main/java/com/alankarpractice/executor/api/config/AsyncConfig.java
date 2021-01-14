package com.alankarpractice.executor.api.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Here I configure thread pool related stuff
 * 
 * @author Alankar Apte
 * 
 * https://tomcat.apache.org/tomcat-7.0-doc/config/executor.html
 */

@Configuration
@EnableAsync	//it will inform spring framework to run your task in background using thread pool concept
public class AsyncConfig {

	/*
	 * here we configure thread pool related stuff
	 * Creating bean of Executor (configure ThreadPoolTaskExecutor)
	 * 
	 * Even if you not configure this Executor it won't create this 'ThreadPoolTaskExecutor', Instead it will create 'SimpleAsyncTaskExecutor'
	 */
	@Bean(name = "taskExecutor") 
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();	//Create object of thread pool task executor
		//Set thread pool size, queue capacity all that stuff
		executor.setCorePoolSize(2);	//capacity; minimum number of workers to keep alive
		executor.setMaxPoolSize(2);		// the maximum number of threads that can ever be created
		executor.setQueueCapacity(100); 	//	at a time this no. of task can wait in queue 
		executor.setThreadNamePrefix("userThread-"); //name prefix for each thread created by the executor. The thread name for an individual thread will be namePrefix+threadNumber
		executor.initialize();
		return executor;
	}
}
