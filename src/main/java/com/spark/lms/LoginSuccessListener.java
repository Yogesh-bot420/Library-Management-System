package com.spark.lms;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.spark.lms.service.UserService;

@Component
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private UserService userService;
	
	//to store information about the logged-in user
	@Autowired
	private HttpSession httpSession;
	
	//to retrive the logged-in user and store it in the session and display name
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
        User user = (User) event.getAuthentication().getPrincipal();
        String displayName = userService.getByUsername( user.getUsername() ).getDisplayName();
        httpSession.setAttribute("loggedInUserName", displayName);
	}
	
}
