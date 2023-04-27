package com.exercise.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserFilter {

	private String firstName;

	private String lastName;

}
