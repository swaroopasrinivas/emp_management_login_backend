package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.RegistrationService;

@RestController
@RequestMapping("/api/v1/")
public class RegistrationController {
	
	
		@Autowired
		private RegistrationService service; //whenever user submits registration form, data should be saved in db
		
		@PostMapping("/registeruser")
		@CrossOrigin(origins="http://localhost:4200")
		public User registerUser(@RequestBody User user) throws Exception{
			
			//to check if email is already present or not
			
			String tempEmailId = user.getEmailId();
			
			if(tempEmailId != null && !"".equals(tempEmailId)) {
				User userobj = service.fetchUserByEmailId(tempEmailId);
				
				if(userobj != null) {
					throw new Exception("user with this" +tempEmailId+"is already existing");
				}
			}
			User userObj = null;
			userObj = service.saveUser(user);
			return userObj;
		}
		
		//login method
		
		@PostMapping("/login")
		@CrossOrigin(origins="http://localhost:4200")
		public User loginUser(@RequestBody User user) throws Exception {
			
			//check combo of emailid+password is present-yes:login else: exception
			
			String tempEmailId = user.getEmailId();
			String tempPass = user.getPassword();
			User userObj = null;
			
			if(tempEmailId != null && tempPass!= null) {
				userObj= service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
			}
			if(userObj == null) {
				throw new Exception("Bad credentials");
			}
			return userObj;
		}

}
