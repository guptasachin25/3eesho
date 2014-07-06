package com.codepath.eesho.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Expert")
public class Expert extends ParseObject{
	
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
