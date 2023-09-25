package com.example.shantanu_spring_batch_application.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "CUSTOMERS_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(generator = "uuid")
	private int field_Id;
	@Column(name = "ID",unique=true)
	private int ID;
	@Column(name = "Name")
	private String name;
	@Column(name = "Organization")
	private String Organization;
	@Column(name = "Email")
	private String Email;
	@Column(name = "SSN")
	private String SSN;
	@Column(name = "crtd_ts")
	private Instant timestamp;
	@Column(name = "Record_Count")
	private int count;
}

