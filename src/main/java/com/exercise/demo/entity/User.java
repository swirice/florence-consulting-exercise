package com.exercise.demo.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "User")
@Table(name = "users", schema = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

	@Id
	@Column(name = "uuid", unique = true, nullable = false)
	private UUID uuid;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "age", nullable = false)
	private Integer age;

	@Column(name = "active", columnDefinition = "boolean default true", nullable = false)
	private Boolean active;

}
