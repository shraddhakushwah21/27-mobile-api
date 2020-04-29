package com.example.jwt;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5587943598078638453L;
	private String jwtToken;
	
}
