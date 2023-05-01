package com.exercise.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exercise.demo.dto.BulkDeleteDTO;
import com.exercise.demo.dto.UserDTO;
import com.exercise.demo.model.UserFilter;
import com.exercise.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<UserDTO> getUsers(@RequestParam(required = false, name = "firstName") String firstName,
			@RequestParam(required = false, name = "lastName") String lastName,
			@RequestParam(required = false, name = "active") Boolean active) {
		UserFilter filter = UserFilter.builder().firstName(firstName).lastName(lastName).active(active).build();
		return userService.retrieveUsers(filter);
	}

	@GetMapping("/users/{uuid}")
	public UserDTO getUser(@PathVariable("uuid") UUID uuid) {
		return userService.retrieveUserByUuid(uuid);
	}

	@DeleteMapping("/users/{uuid}")
	public void deleteUserByUuid(@PathVariable("uuid") UUID uuid) {
		userService.deleteUserByUuid(uuid);
	}

	@DeleteMapping("/users")
	public void deleteUsersByUuid(@RequestBody BulkDeleteDTO payload) {
		userService.deleteUsers(payload);
	}

	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO insertUser(@RequestBody UserDTO payload) {
		return userService.createUser(payload);
	}

	@PutMapping("/users/{uuid}")
	public UserDTO updateUser(@PathVariable("uuid") UUID uuid, @RequestBody UserDTO payload) {
		return userService.updateUserByUuid(uuid, payload);
	}

	@PostMapping(value = "/users/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<UserDTO> importUserCsv(@RequestParam("file") MultipartFile file) {
		return userService.saveCsv(file);
	}
}
