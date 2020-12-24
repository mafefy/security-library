package com.lunatech.security.authorization;

import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lunatech.security.user.dao.UserInfo;

public class AuthorizationService {


	private static String  jwtSecret;
	private static Long expireInterval;

	public static void configure(String secrect , Long interval) {
		expireInterval = interval;
		jwtSecret = secrect;
	}
	
	private Date getTokenExpire() {
		Date now = new Date();
		now.setTime(now.getTime() + expireInterval);
		return now;
	}

	public String generateToken(UserInfo user) {
		String token = null;
		try {
			Algorithm algorithm = Algorithm.HMAC512(jwtSecret);
			token = JWT.create().withIssuer(issuer).withExpiresAt(getTokenExpire()).withClaim(id, user.getId())
					.withClaim(role, user.getRole()).withClaim(name, user.getName())
					.withClaim(suspended, user.getSuspended()).sign(algorithm);
		} catch (JWTCreationException exception) {
			System.err.println("JWT  system generation errror");
		}
		
		return token;
	}

	private static String issuer = "authentication-service";
	private static String id = "id";
	private static String role = "role";
	private static String name = "name";
	private static String suspended = "suspended";

	public AuthorizationStatus getStatus(String token)  {
		AuthorizationStatus status = new AuthorizationStatus();
		if ( token == null ) {
			status.setAuthorized(false);
			status.setExpired(false);
			return status;
		}
		try {
			Algorithm algorithm = Algorithm.HMAC512(jwtSecret);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build(); // Reusable verifier instance
			DecodedJWT jwt = verifier.verify(token);
			status.setAuthorized(true);
			status.setUserId(jwt.getClaim(id).asLong());
			status.setName(jwt.getClaim(name).asString());
			status.setRole(jwt.getClaim(role).asString());
			status.setSuspended(jwt.getClaim(suspended).asBoolean());
		} catch (TokenExpiredException exe) {
			status.setExpired(true);
			status.setAuthorized(false);
		} catch (JWTVerificationException exe) {
			status.setAuthorized(false);
		}
		return status;
	}
}
