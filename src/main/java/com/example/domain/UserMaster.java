package com.example.domain;

import java.util.Optional;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class UserMaster {

	private Integer userId;
	private String username;
	private String phoneNumber;
	private String email;
	private boolean isSpam;
}
