package com.exercise.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.demo.dto.UserDTO;
import com.exercise.demo.entity.User;
import com.exercise.demo.exception.NoContentException;
import com.exercise.demo.exception.NotFoundException;
import com.exercise.demo.mapper.UserMapper;
import com.exercise.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> retrieveUsers() {

		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new NoContentException("No users found");
		}

		return users.stream().map(UserMapper.INSTANCE::userEntityToUserDTO).toList();

	}

	public UserDTO retrieveUserByUuid(UUID uuid) {

		Optional<User> optUser = userRepository.findById(uuid);
		if (optUser.isEmpty()) {
			throw new NotFoundException("No user found with UUID = " + uuid);
		}

		return UserMapper.INSTANCE.userEntityToUserDTO(optUser.get());

	}

	public void deleteUserByUuid(UUID uuid) {

		if (!userRepository.existsById(uuid)) {
			throw new NotFoundException("No user found with UUID = " + uuid);
		}

		userRepository.deleteById(uuid);

	}
	

}
