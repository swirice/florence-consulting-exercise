package com.exercise.demo.dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {

	@NotNull
	private UUID uuid;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Flag.CASE_INSENSITIVE)
	private String email;

	@NotNull
	@Min(value = 0)
	private Integer age;

	private Boolean active = Boolean.TRUE;

}
