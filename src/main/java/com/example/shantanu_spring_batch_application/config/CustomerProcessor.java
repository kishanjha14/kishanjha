package com.example.shantanu_spring_batch_application.config;

import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.item.ItemProcessor;

import com.example.shantanu_spring_batch_application.entity.Customer;


public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

	static int i = 1;
	static int j = 1;

	@Override
	public Customer process(Customer customer) throws Exception {
		if (customer.getOrganization().equals("INFY") || customer.getOrganization().equals("VG")) {

			if (customer.getID() >= 0 && customer.getSSN().length() == 9 && isValidEmail(customer.getEmail())
					&& isValidString(customer.getName())) {
				if (customer.getOrganization().equals("INFY")) {
					customer.setCount(i++);
				} else
					customer.setCount(j++);
				customer.setTimestamp(Instant.now());
				return customer;
			} else
				return null;
		} else {
			return null;
		}
	}

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	public static boolean isValidEmail(String email) {
		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}

	public static boolean isValidString(String input) {
		String regex = "^[a-zA-Z ]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
}
