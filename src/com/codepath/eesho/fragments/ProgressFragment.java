package com.codepath.eesho.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.codepath.eesho.R;
import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;

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
		lgProgress = (LineGraph) v.findViewById(R.id.progressGraph);

		setUpWeightGraph();
		setUpProgressGraph();
		
		return v;
	}
	
	public void setToUnclick(Button b) {
		b.setBackgroundResource(R.drawable.history_button_unclicked);
		b.setTextColor(Color.parseColor("#a8a8a8"));
	}
	
	public void setUpWeightGraph() {
		LinePoint p = new LinePoint();
		p.setX(0);
		p.setY(5);
		l.addPoint(p);
		p = new LinePoint();
		p.setX(8);
		p.setY(8);
		l.addPoint(p);
		p = new LinePoint();
		p.setX(10);
		p.setY(4);
		l.addPoint(p);
		l.setColor(Color.parseColor("#00bdab"));
		lgWeight.addLine(l);
		lgWeight.setRangeY(0, 10);
		lgWeight.showMinAndMaxValues(true);

	}
	
	public void setUpProgressGraph() {
		LinePoint p = new LinePoint();
		p.setX(0);
		p.setY(5);
		lProgress.addPoint(p);
		p = new LinePoint();
		p.setX(8);
		p.setY(8);
		lProgress.addPoint(p);
		p = new LinePoint();
		p.setX(10);
		p.setY(4);
		lProgress.addPoint(p);
		lProgress.setColor(Color.parseColor("#00bdab"));
		lgProgress.addLine(l);
		lgProgress.setRangeY(0, 10);
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

}
