package com.codepath.eesho.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("User")
public class User extends ParseObject{
	
	public User() {
		super();
	}
	
	public String getUsernname() {
	    return getString("username");
	  }
	
	public String getPassword() {
		return getString("password");
	}
	
	public String getEmail() {
		return getString("email");
	}
	
	public boolean isExpert() {
		return getBoolean("isExpert");
	}

}
