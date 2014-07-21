package com.codepath.eesho.parse.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("messages")
public class Messages extends ParseObject {
	
	ParseUser sender;
	ParseUser receiver;
	String message;
	
	public Messages() {
		super();
	} 
	
	public Messages(String message) {
		this.message = message;
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
	
	public Date getTimestamp() {
		return getCreatedAt();
	}
	
	public String getTimeSinceCurrentTime(Date date) {
		long seconds = (new DateTime().getMillis() - date.getTime()) / 1000;
		System.out.println(new DateTime().getMillis());
		System.out.println(date.getTime());
		
		if (seconds < 60L) {
			return String.valueOf(seconds) + 's';
		} else if (seconds < 3600) {
			return String.valueOf(seconds / 60) + 'm';
		} else if (seconds < 3600 * 24) {
			return String.valueOf(seconds / 3600) + 'h';
		} else {
			return String.valueOf(seconds / 86400) + 'd';
		}
	}

	public void like() {
		increment("like");
	}
	
	public Number getLikes() {
		Number likes = getNumber("like");
		return likes != null ? likes : 0;
	}
	
	public void addUserWhoLiked(ParseUser user) {
		List<ParseUser> users = new ArrayList<ParseUser>();
		users.add(user);
		addAll("users_who_liked", users);
	}
}
