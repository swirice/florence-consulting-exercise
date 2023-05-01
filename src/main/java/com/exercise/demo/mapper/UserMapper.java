package com.exercise.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.exercise.demo.dto.UserDTO;
import com.exercise.demo.entity.User;
import com.exercise.demo.model.UserFilter;

@Mapper
public interface UserMapper {

	public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	public abstract User dtoToEntity(UserDTO userDTO);

	public abstract UserDTO entityToDTO(User user);

	@Mapping(target = "uuid", ignore = true)
	@Mapping(target = "age", ignore = true)
	@Mapping(target = "email", ignore = true)
	public abstract User filterToEntity(UserFilter userfilter);

}
