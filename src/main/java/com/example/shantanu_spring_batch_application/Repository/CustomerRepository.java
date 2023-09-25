package com.example.shantanu_spring_batch_application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shantanu_spring_batch_application.entity.Customer;

public interface CustomerRepository  extends JpaRepository<Customer,Integer> {

}