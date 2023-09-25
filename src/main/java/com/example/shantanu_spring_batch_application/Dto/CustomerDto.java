package com.example.shantanu_spring_batch_application.Dto;

import java.time.Instant;

import lombok.Data;

@Data
public class CustomerDto {
	private int field_Id;
	private String Organization;
	private Instant timestamp;
	private int count;
}