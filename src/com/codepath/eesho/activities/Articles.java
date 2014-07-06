package com.codepath.eesho.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.ArticlesArrayAdapter;

public class Articles extends Activity {
	
	private ArrayList<Articles> articles;
	private ArticlesArrayAdapter aArticles;
	ListView lvArticles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_articles);
	}
}
