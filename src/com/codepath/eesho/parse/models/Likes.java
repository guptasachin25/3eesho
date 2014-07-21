package com.codepath.eesho.parse.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Conversations")
public class Likes extends ParseObject {
	public Likes() {
		super();
	}
	
	public Likes(ParseUser user, Messages message) {
		setUser(user);
		setMessage(message);
	}
	
	public void setUser(ParseUser user) {
		put("user", user);
	}
	
	public void setMessage(Messages message) {
		put("message", message);
	}
	
	public ParseUser getUser() {
		return getParseUser("user");
	}
	
	public Messages getMessage() {
		return null;
	}
	
	
}
