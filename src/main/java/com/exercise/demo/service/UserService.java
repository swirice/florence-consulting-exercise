package com.exercise.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.exercise.demo.attachment.CsvManager;
import com.exercise.demo.dto.BulkDeleteDTO;
import com.exercise.demo.dto.UserDTO;
import com.exercise.demo.entity.User;
import com.exercise.demo.exception.NoDataFoundException;
import com.exercise.demo.exception.NoResultsFoundException;
import com.exercise.demo.exception.NotSupportedException;
import com.exercise.demo.mapper.UserMapper;
import com.exercise.demo.model.UserFilter;
import com.exercise.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// Other extensions can be added in order to be managed
	private static final List<String> FILE_ACCEPTED_EXTENSIONS = Arrays.asList("csv");

	public List<UserDTO> retrieveUsers(UserFilter filter) {

		User probe = UserMapper.INSTANCE.filterToEntity(filter);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues();
		Example<User> example = Example.of(probe, matcher);

		List<User> users = userRepository.findAll(example);
		if (users.isEmpty()) {
			String message = String.format("No users found having following criteria = %s", filter);
			log.warn("retrieveUsers(UserFilter) - {}", message);
			throw new NoResultsFoundException(message);
		}

		List<UserDTO> output = users.stream().map(UserMapper.INSTANCE::entityToDTO).toList();
		log.info("retrieveUsers(UserFilter) - {} users have been found", output.size());

		return output;

	}

	public UserDTO retrieveUserByUuid(UUID uuid) {

		Optional<User> optUser = userRepository.findById(uuid);
		if (optUser.isEmpty()) {
			String message = String.format("No user found with UUID = %s", uuid);
			log.error("retrieveUserByUuid(UUID) - {}", message);
			throw new NoDataFoundException(message);
		}

		UserDTO output = UserMapper.INSTANCE.entityToDTO(optUser.get());
		log.info("retrieveUserByUuid(UUID) - User having UUID = {} has been found", output.getUuid());

		return output;

	}

	@Transactional
	public void deleteUserByUuid(UUID uuid) {

		if (!userRepository.existsById(uuid)) {
			String message = String.format("No user found with UUID = %s", uuid);
			log.error("deleteUserByUuid(UUID) - {}", message);
			throw new NoDataFoundException(message);
		}

		userRepository.deleteById(uuid);
		log.info("deleteUserByUuid(UUID) - User with UUID {} has been correctly deleted", uuid);

	}

	@Transactional
	public void deleteUsers(BulkDeleteDTO payload) {

		userRepository.deleteAllById(payload.getUuids());
		log.info("deleteUsers(BulkDeleteDTO) - {} users (if existing) have been correctly deleted",
				payload.getUuids().size());

	}

	@Transactional
	public UserDTO createUser(UserDTO input) {

		input.setUuid(UUID.randomUUID());

		User newUser = userRepository.saveAndFlush(UserMapper.INSTANCE.dtoToEntity(input));
		log.info("createUser(UserDTO) - New user with UUID = {} successfully created", newUser.getUuid());

		return UserMapper.INSTANCE.entityToDTO(newUser);

	}

	@Transactional
	public UserDTO updateUserByUuid(UUID uuid, UserDTO input) {

		if (!userRepository.existsById(uuid)) {
			String message = String.format("No user found with UUID = %s", uuid);
			log.error("updateUserByUuid(UUID, UserDTO) - {}", message);
			throw new NoDataFoundException(message);
		}

		input.setUuid(uuid);
		User updatedUser = userRepository.saveAndFlush(UserMapper.INSTANCE.dtoToEntity(input));
		log.info("updateUserByUuid(UUID, UserDTO) - User with UUID = {} successfully updated", updatedUser.getUuid());

		return UserMapper.INSTANCE.entityToDTO(updatedUser);

	}

	@Transactional
	public List<UserDTO> saveCsv(MultipartFile file) {

		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());

		if (!FILE_ACCEPTED_EXTENSIONS.contains(fileExtension)) {
			String message = String.format("File with extension %s is not supported!", fileExtension);
			log.error("saveCsv(MultipartFile) - {}", message);
			throw new NotSupportedException(message + " Supported exceptions are: " + FILE_ACCEPTED_EXTENSIONS);
		}

		List<UserDTO> rows = CsvManager.userCsvToList(file);

		List<User> users = rows.stream().map(u -> {
			if (u.getUuid() == null) {
				u.setUuid(UUID.randomUUID());
			}
			return UserMapper.INSTANCE.dtoToEntity(u);
		}).toList();

		List<User> newUsers = userRepository.saveAllAndFlush(users);
		List<UserDTO> output = newUsers.stream().map(UserMapper.INSTANCE::entityToDTO).toList();
		log.info("saveCsv(MultipartFile) - {} users have been created/updated", output.size());

		return output;

	}

}
