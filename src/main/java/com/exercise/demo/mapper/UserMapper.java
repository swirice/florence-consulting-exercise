package com.exercise.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.exercise.demo.dto.UserDTO;
import com.exercise.demo.entity.User;
import com.exercise.demo.model.UserFilter;

@Mapper
public interface UserMapper {

	static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	abstract User userDTOToUserEntity(UserDTO userDTO);

	abstract UserDTO userEntityToUserDTO(User user);

	abstract User userFilterToUserEntity(UserFilter userfilter);

}