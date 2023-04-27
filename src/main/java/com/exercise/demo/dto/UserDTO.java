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

	@NotNull
	@CsvBindByPosition(position = 0)
	private UUID uuid;

	@NotNull
	@CsvBindByPosition(position = 1)
	private String firstName;

	@NotNull
	@CsvBindByPosition(position = 2)
	private String lastName;

	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Flag.CASE_INSENSITIVE)
	@CsvBindByPosition(position = 3)
	private String email;

	@NotNull
	@Min(value = 0)
	@CsvBindByPosition(position = 4)
	private Integer age;

	@CsvBindByPosition(position = 5)
	private Boolean active = Boolean.TRUE;

}
