package com.chatApi.demo.controllert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatApi.demo.dto.LoginByEmailDto;

import com.chatApi.demo.entity.User;

import com.chatApi.demo.response.UserAndContactResponse;
import com.chatApi.demo.service.MessageService;
import com.chatApi.demo.service.UserService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
@RestController
@RequestMapping("api")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	MessageService messageService;

//	provide all users except the user requesting 
//	along with the user information

	@GetMapping("/users")
	public UserAndContactResponse getAll(HttpSession session) {

		try {
			User user = (User) session.getAttribute("id");
			List<User> contacts = userService.getAll();

			// removing the current user from all user
			for (int i = 0; i < contacts.size(); i++) {
				if (contacts.get(i).getId() == user.getId()) {
					contacts.remove(i);
				}
			}

			UserAndContactResponse res = new UserAndContactResponse(user, contacts);
			return res;

		} catch (IllegalStateException e) {
			// in case of invalid session
			return null;
		}

	}

	@PostMapping("/users")
	public  ResponseEntity<User> registration(@RequestBody User user, HttpSession session) {

		User newUser = userService.save(user);
		session.setAttribute("id", user);

		return new ResponseEntity<User>(newUser, HttpStatus.CREATED) ;
	}
    
    	
	@PostMapping("/users/email")
	public ResponseEntity<User> logInByEmail(@RequestBody LoginByEmailDto emailDto, HttpSession session) {

		User user = userService.findByEmail(emailDto.getEmail());
        		
		if (user != null) {
			session.setAttribute("id", user);
		}else {
			return null;
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/users/logout")
	public ResponseEntity<String> signOut(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok("Successful");
	}

}
