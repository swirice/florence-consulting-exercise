package com.exercise.demo.dto;

import java.util.UUID;

import com.opencsv.bean.CsvBindByPosition;

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

	@CsvBindByPosition(position = 0, required = false)
	private UUID uuid;

	@NotNull
	@CsvBindByPosition(position = 1, required = true)
	private String firstName;

	@NotNull
	@CsvBindByPosition(position = 2, required = true)
	private String lastName;

	@NotNull
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Flag.CASE_INSENSITIVE)
	@CsvBindByPosition(position = 3, required = true)
	private String email;

	@NotNull
	@Min(value = 0)
	@CsvBindByPosition(position = 4, required = true)
	private Integer age;

	@CsvBindByPosition(position = 5, required = false)
	private Boolean active = Boolean.TRUE;

}
