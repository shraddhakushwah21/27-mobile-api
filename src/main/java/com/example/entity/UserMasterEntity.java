package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="USER_MASTER")
public class UserMasterEntity {

	@Id
	@Column(name="USER_ID")
	@SequenceGenerator(name = "USER_ID_SEQ_GEN",sequenceName = "USER_ID_SEQ",allocationSize = 1,initialValue = 1)
	@GeneratedValue(generator = "USER_ID_SEQ_GEN",strategy = GenerationType.SEQUENCE)
	private Integer userId;
	
	@Column(name="USER_NAME")
	private String username;
	
	@Column(name="PHONE_NUMBER",unique = true)
	private String phoneNumber;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="IS_SPAM")
	private boolean isSpam;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE",updatable = false)
	private Date createdDate;

	@Column(name="UPDATED_BY",insertable = false)
	private String updatedBy;
	
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="UPDATE_DATE")
	private Date updatedDate;
	
}
