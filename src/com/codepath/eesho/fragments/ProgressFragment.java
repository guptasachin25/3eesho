package com.codepath.eesho.fragments;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codepath.eesho.R;
import com.codepath.eesho.parse.models.Weight;
import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ProgressFragment extends Fragment{
	Line l = new Line();
	Line lProgress = new Line();
	LineGraph lgWeight;
	LineGraph lgProgress;
	Button bWeek;
	Button bMonth;
	Button bAll;
	
	// keeps track of which history is currently displayed
	// 1 - week, 2 - month, 3 - all
	int currentlyClickedBtn = 1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_progress, container, false);
		
		bWeek = (Button) v.findViewById(R.id.btWeek);
		bMonth = (Button) v.findViewById(R.id.btMonth);
		bAll = (Button) v.findViewById(R.id.btAll);

		lgWeight = (LineGraph) v.findViewById(R.id.graph);

		setUpWeightGraph();
		
		return v;
	}
	
	public void setToUnclick(Button b) {
		b.setBackgroundResource(R.drawable.history_button_unclicked);
		b.setTextColor(Color.parseColor("#a8a8a8"));
	}
	
	public void setUpWeightGraph() {
		getWeights();
		
		l.setColor(Color.parseColor("#FFFFFF"));
		lgWeight.addLine(l);
		lgWeight.setRangeY(160, 190);
//		lgWeight.showMinAndMaxValues(true);
		lgWeight.setHorizontalFadingEdgeEnabled(true);
		lgWeight.setTextSize(20);
		lgWeight.showHorizontalGrid(true);
		lgWeight.setGridColor(Color.parseColor("#FFFFFF"));
	}
	
	public void addPoint(float x, int y) {
		LinePoint p = new LinePoint(x , y);
		l.addPoint(p);
	}
	
	public void plotPoints(ArrayList<Integer> points) {
		float currentX = 0;
		for (int i = 0; i < points.size(); i++) {
			addPoint(currentX, points.get(i));
			currentX = currentX + (50 / points.size());
		}
	}
	
	public void getWeights() {
		ParseQuery<Weight> query = ParseQuery.getQuery(Weight.class);
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		query.orderByAscending("createdAt");
		query.findInBackground(new FindCallback<Weight>() {
			public void done(List<Weight> weights, ParseException e) {
				ArrayList<Integer> points = new ArrayList<Integer>();
				for (int i = 0; i < weights.size(); i++) {
					points.add(weights.get(i).getWeight());
				}
				plotPoints(points);
			}
		});
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

}
