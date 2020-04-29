package com.example.jwt;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1959848765345747875L;
	private String username;
	private String password;
}
