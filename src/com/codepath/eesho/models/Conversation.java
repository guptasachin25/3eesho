package com.codepath.eesho.models;

import org.json.JSONArray;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Conversations")
public class Conversation extends ParseObject{
	
	public Conversation() {
		super();
	}
	
	public String getId() {
		return getString("objectId");
	}
	
	public String getUser() {
	    return getString("user_id");
	  }
	
	public String getExpert() {
		return getString("expert_id");
	}
	
	public JSONArray getMessages() {
		return getJSONArray("messages");
	}
	
	/*
	 * TODO
	 */
	public void addMessage(String message, String from) {
		
	}

}
