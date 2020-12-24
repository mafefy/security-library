package com.lunatech.security;

import javax.naming.AuthenticationNotSupportedException;

import com.lunatech.security.authorization.AuthorizationService;
import com.lunatech.security.authorization.AuthorizationStatus;
import com.lunatech.security.authorization.UserRole;
import com.lunatech.security.user.dao.UserInfo;

public class Starter {

	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello man");
		AuthorizationService service = new AuthorizationService();
		AuthorizationService.configure("alkdsjlasdfjalf", 343434343L);
		UserInfo info = new UserInfo();
		info.setSuspended(false);
		info.setName("eshta man");
		info.setRole("fancy user");
		info.setId(3434L);
		String token= service.generateToken(info);
		System.out.println(token);
		AuthorizationStatus status = service.getStatus(token);
		System.out.println(status.toString());
	}
	*/
}
