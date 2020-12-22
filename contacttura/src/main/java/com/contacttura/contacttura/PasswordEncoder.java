package com.contacttura.contacttura;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	
	public static void main(String[] args) {
	BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
	
	System.out.println(pass.encode("root"));
	}
}