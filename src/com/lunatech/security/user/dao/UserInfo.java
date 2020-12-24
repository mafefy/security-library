package com.lunatech.security.user.dao;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data

public class UserInfo{

	private Long id;
	private String name;
	private Boolean suspended;
	private String role;
}
