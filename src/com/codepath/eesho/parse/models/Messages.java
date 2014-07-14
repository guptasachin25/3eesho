package com.codepath.eesho.parse.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("messages")
public class Messages extends ParseObject {
	
	public Messages() {
		super();
	} 
	
	public Messages(ParseUser sender, ParseUser receiver, String message) {
		super();
		setMessage(message);
		setSender(sender);
		setReceiver(receiver);
	}
	
	public void setMessage(String message) {
		put("message", message);
	}
	
	public void setSender(ParseUser user) {
		put("sender", user);
	}
	
	public void setReceiver(ParseUser user) {
		put("receiver", user);
	}
	
	public String getMessage() {
		return getString("message");
	}
	
	public ParseUser getSender() {
		return getParseUser("sender");
	}
	
	public ParseUser getReceiver() {
		return getParseUser("receiver");
	}
}
