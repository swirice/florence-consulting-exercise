package com.exercise.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.exercise.demo.dto.UserDTO;
import com.exercise.demo.entity.User;
import com.exercise.demo.exception.NoDataFoundException;
import com.exercise.demo.exception.NoResultsFoundException;
import com.exercise.demo.mapper.UserMapper;
import com.exercise.demo.model.UserFilter;
import com.exercise.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> retrieveUsers(UserFilter filter) {

		User probe = UserMapper.INSTANCE.userFilterToUserEntity(filter);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues();
		Example<User> example = Example.of(probe, matcher);

		List<User> users = userRepository.findAll(example);
		if (users.isEmpty()) {
			String message = String.format("No users found having following criteria = %s", filter);
			log.warn("retrieveUsers() - {}", message);
			throw new NoResultsFoundException(message);
		}

		List<UserDTO> output = users.stream().map(UserMapper.INSTANCE::userEntityToUserDTO).toList();
		log.info("retrieveUsers() - {} users have been found", output.size());

		return output;

	}

	public UserDTO retrieveUserByUuid(UUID uuid) {

		Optional<User> optUser = userRepository.findById(uuid);
		if (optUser.isEmpty()) {
			String message = String.format("No user found with UUID = %s", uuid);
			log.error("retrieveUserByUuid(UUID) - {}", message);
			throw new NoDataFoundException(message);
		}

		UserDTO output = UserMapper.INSTANCE.userEntityToUserDTO(optUser.get());
		log.info("retrieveUserByUuid(UUID) - user having UUID = {} has been found", output.getUuid());

		return output;

	}

	public void deleteUserByUuid(UUID uuid) {

		if (!userRepository.existsById(uuid)) {
			String message = String.format("No user found with UUID = %s", uuid);
			log.error("deleteUserByUuid(UUID) - {}", message);
			throw new NoDataFoundException(message);
		}

		userRepository.deleteById(uuid);
		log.info("deleteUserByUuid(UUID) - User with UUID {} has been correctly deleted", uuid);

	}

	public UserDTO createUser(UserDTO input) {

		input.setUuid(UUID.randomUUID());

		User newUser = userRepository.saveAndFlush(UserMapper.INSTANCE.userDTOToUserEntity(input));
		log.info("createUser(UserDTO) - new user with UUID = {} successfully created", newUser.getUuid());

		return UserMapper.INSTANCE.userEntityToUserDTO(newUser);

	}

	public UserDTO updateUserByUuid(UUID uuid, UserDTO input) {

		if (!userRepository.existsById(uuid)) {
			String message = String.format("No user found with UUID = %s", uuid);
			log.error("updateUserByUuid(UUID, UserDTO) - {}", message);
			throw new NoDataFoundException(message);
		}

		input.setUuid(uuid);
		User updatedUser = userRepository.saveAndFlush(UserMapper.INSTANCE.userDTOToUserEntity(input));
		log.info("updateUserByUuid(UUID, UserDTO) - user with UUID = {} successfully updated", updatedUser.getUuid());

		return UserMapper.INSTANCE.userEntityToUserDTO(updatedUser);

	}

}
