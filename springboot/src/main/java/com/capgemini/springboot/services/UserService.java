package com.capgemini.springboot.services;

import com.capgemini.springboot.entities.User;
import com.capgemini.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;
	//private final BCryptPasswordEncoder bCryptPasswordEncoder;


	public UserService(){
		userRepository=null;
		//bCryptPasswordEncoder=null;
	}
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	//	bCryptPasswordEncoder=null;
	}

	public void registerUser(User user) {
		//String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		//user.setPassword(encryptedPassword);
		userRepository.save(user);
	}
	public boolean authenticateUser(String username, String password) {
		User user = userRepository.findByUsername(username);

		return user != null && user.getPassword().equals(password);
	}
}
