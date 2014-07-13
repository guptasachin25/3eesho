package com.codepath.eesho.parse.models;

import com.parse.ParseClassName;
import com.parse.ParseUser;

@ParseClassName("Expert")
public class Expert extends ParseUser {
	
	public Expert() {
		super();
	}
	
	public String getProfilePicture() {
	    return getString("profile_url");
	  }
	
	public String getName() {
		return getString("name");
	}
	
	/*
	 * get expert's about me blurb
	 */
	public String getDescription() {
		return getString("description");
	}

}
