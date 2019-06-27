package com.cosmose.demoHotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmose.demoHotel.json.ResultType;
import com.cosmose.demoHotel.model.User;
import com.cosmose.demoHotel.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public String addUser(User user) {
		Object u = userRepository.save(user);
		if(u == null) {
			return ResultType.RESULT_FAILURE;
		}
		else {
			return ResultType.RESULT_SUCCESS;
		}
	}
	
	public User getUserById(long id) {
		return userRepository.findById(id).get();
	}
}
