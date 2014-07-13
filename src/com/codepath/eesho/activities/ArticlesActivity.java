package com.codepath.eesho.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.ArticlesArrayAdapter;
import com.codepath.eesho.parse.models.Article;

public class ArticlesActivity extends Activity {
	
	private ArrayList<Article> articles;
	private ArticlesArrayAdapter aArticles;
	ListView lvArticles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_articles);

	}
	
}
