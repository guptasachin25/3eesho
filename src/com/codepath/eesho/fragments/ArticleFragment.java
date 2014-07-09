package com.codepath.eesho.fragments;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.ArticlesArrayAdapter;
import com.codepath.eesho.models.Article;

public class ArticleFragment extends Fragment {
	private ArrayList<Article> articles;
	private ArticlesArrayAdapter aArticles;
	ListView lvArticles;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
		@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_articles, container, false);
		
		articles = new ArrayList<Article>();
		lvArticles = (ListView) v.findViewById(R.id.lvArticles);
		articles.add(new Article("Title", "Description blah blah", "http://3eesho.com/articles/3331/%D9%85%D9%83%D9%85%D9%84%D8%A7%D8%AA+%D8%A7%D9%84%D8%B5%D9%8A%D8%A7%D9%85"));
		articles.add(new Article("Another Title", "This description is even better than the last one", "http://www.yahoo.com"));
		aArticles = new ArticlesArrayAdapter(this.getActivity(), articles);
		lvArticles.setAdapter(aArticles);
		setupListViewListener();
		
		return v;
	}
	
	private void setupListViewListener() {

		lvArticles.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
								
				String url = articles.get(position).getArticleUrl();
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);

			}
		});
	}
}
