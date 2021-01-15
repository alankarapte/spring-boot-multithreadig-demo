package com.alankarpractice.executor.api.controller;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alankarpractice.executor.api.entity.User;
import com.alankarpractice.executor.api.service.UserService;

@RestController
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;


	/**
	 * 
	 * @param multipartFiles
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/users/csv", 
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> saveUsers(
			//'value = files' so when sending through postman need to give key as "files"
			@RequestParam(value = "files") MultipartFile[] multipartFiles
			//if we used 'MultipartHttpServletRequest' so when sending through postman no need to give key as "files"
			//but then need to extract MultipartFile list from it more 2-3 line code (^_^)
			//MultipartHttpServletRequest request
			) throws Exception {

		for (MultipartFile multipartFile : multipartFiles) {
			userService.saveUser(multipartFile);	
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * Here we using return type as CompletableFuture
	 * 
	 * @return
	 */
	@GetMapping(value = "/users", 	//no need of 'consumes' cause its get request
			produces = MediaType.APPLICATION_JSON_VALUE)
	public CompletableFuture<ResponseEntity> findAllUsers(){

		return userService.findAllUser().thenApply( ResponseEntity::ok );	//similar as above one
//		return userService.findAllUser().thenApply( data ->{ return ResponseEntity.ok(data); } ); 
	}
	
	@GetMapping(value = "/allUsersByMultiThread")
	public ResponseEntity<?> findAllUsersByMultiThread(){
		
		CompletableFuture<List<User>> users1 = userService.findAllUser();
		CompletableFuture<List<User>> users2 = userService.findAllUser();
		CompletableFuture<List<User>> users3 = userService.findAllUser();

		CompletableFuture.allOf(users1, users2, users3).join();
		
		return ResponseEntity.ok(HttpStatus.OK);
	}

	
}
