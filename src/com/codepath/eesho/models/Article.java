package com.codepath.eesho.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Articles")
public class Article extends ParseObject{
	
	public Article() {
		super();
	}
	
	public String getId() {
		return getString("objectId");
	}
	
	public String getArticleTitle() {
	    return getString("articleTitle");
	  }
	
	public String getArticleUrl() {
		return getString("articleUrl");
	}
	
	public String getArticleDesription() {
		return getString("articleDescription");
	}

}
