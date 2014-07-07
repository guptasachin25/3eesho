package com.codepath.eesho.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Articles")
public class Article extends ParseObject{
	
	public Article() {
		super();
	}
	
	public Article(String title, String description, String url) {
		super();
		setArticleTitle(title);
		setArticleDescription(description);
		setArticleUrl(url);
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
	
	public void setArticleTitle(String title) {
		put("articleTitle", title);
	}
	
	public void setArticleUrl(String url) {
		put("articleUrl", url);
	}
	
	public void setArticleDescription(String description) {
		put("articleDescription", description);
	}

}
