package com.codepath.eesho.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.eesho.R;
import com.codepath.eesho.models.Article;

public class ArticlesArrayAdapter extends ArrayAdapter<Article>{
	
	public static Article article;
	
	public View getView(int position, View convertView, ViewGroup parent) {
		article = getItem(position);
		View v;
		
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			v = inflator.inflate(R.layout.articles_item, parent, false);
		} else {
			v = convertView;
		}
		
		TextView tvTitle = (TextView) v.findViewById(R.id.tvTitle);
		TextView tvDescription = (TextView) v.findViewById(R.id.tvDescription);
		
		tvTitle.setText(article.getArticleTitle());
		tvDescription.setText(article.getArticleDesription());
		
		String url = article.getArticleUrl();
		
		return v;
		
	}
	
	public ArticlesArrayAdapter(Context context, ArrayList<Article> article) {
		super(context, 0, article);
	}
	

}
