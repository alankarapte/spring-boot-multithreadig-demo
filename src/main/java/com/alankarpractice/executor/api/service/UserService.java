package com.alankarpractice.executor.api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alankarpractice.executor.api.entity.User;
import com.alankarpractice.executor.api.repository.UserRepository;

/**
 * @author Alankar Apte
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(UserService.class);



	/**
	 * This is utility method which will parse our CSV file
	 */
	private List<User> parseCsv(final MultipartFile multipartFile) throws Exception{
		final List<User> users = new  ArrayList<User>();
		try {
			try (final BufferedReader br = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))){
				String line;
				while( (line = br.readLine()) != null ) {
					final String[] data = line.split(",");
					final User user = new User();
					user.setName(data[0]);
					user.setEmail(data[1]);
					user.setGender(data[2]);
					users.add(user);
				}
				return users;
			}
		} catch (Exception e) {
			logger.error("failed to parse Csv file {} ", e);
			throw new Exception("failed to parse Csv file {} ", e);
		}
	}
}
