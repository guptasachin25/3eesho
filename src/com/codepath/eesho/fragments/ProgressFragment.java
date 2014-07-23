package com.codepath.eesho.fragments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.codepath.eesho.R;
import com.codepath.eesho.parse.models.Weight;
import com.codepath.eesho.utils.Utils;
import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ProgressFragment extends Fragment{
	Line l = new Line();
	Line lProgress = new Line();
	LineGraph lgWeight;
	LineGraph lgProgress;
	Button bWeek;
	Button bMonth;
	Button bAll;
	boolean weightEntered = false;
	private NumberPicker minPicker;
	private NumberPicker maxPicker;
	ParseUser currentUser;
	
	
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
		
		// populate data if there's no data
		
		// check if there's a weight entry for today
		
		// check last weight entry and populate into 
		enteredTodaysWeight();
		
		lgWeight = (LineGraph) v.findViewById(R.id.graph);

		getWeights();
		
		return v;
	}
	
	public void enteredTodaysWeight() {
		final Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd"); // 3-letter month name & 2-char day of month
		final String d = formatter.format(calendar.getTime());
		
		currentUser = ParseUser.getCurrentUser();

		ParseQuery<Weight> query = ParseQuery.getQuery(Weight.class);
		System.out.println("date is : " + d);
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		query.whereContains("Date", d);

		query.findInBackground(new FindCallback<Weight>() {
			public void done(List<Weight> weights, ParseException e) {
				if (e == null && weights.size() < 1) {
					LayoutInflater inflater1 = (LayoutInflater)
							getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View npView = inflater1.inflate(R.layout.weight_picker_dialog, null);
					minPicker = (NumberPicker) npView.findViewById(R.id.first_picker);
					minPicker.setMaxValue(300);
					minPicker.setMinValue(100);
					minPicker.setValue(125);
					maxPicker = (NumberPicker) npView.findViewById(R.id.second_picker);
					maxPicker.setMaxValue(9);
					maxPicker.setMinValue(0);

					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
					builder.setTitle("Enter your weight for today!");
					builder.setView(npView);
					builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							int weightvalue = minPicker.getValue();
							Weight weight = new Weight(weightvalue, d);
							weight.setOwner(ParseUser.getCurrentUser());
							weight.saveInBackground();
							
						}
					});
					builder.create();
					builder.show();
				} else {

				}
				
			}
		});
	}
	
	public void setToUnclick(Button b) {
		b.setBackgroundResource(R.drawable.history_button_unclicked);
		b.setTextColor(Color.parseColor("#a8a8a8"));
	}
	
	public void setUpWeightGraph() {
		getWeights();
		
		l.setColor(Color.parseColor("#FFFFFF"));
		lgWeight.addLine(l);
//		lgWeight.setRangeY(110, 130);
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
				if (e == null && weights.size() > 1) {
					
					Line myLine = new Line();
					ArrayList<Integer> myPoints = new ArrayList<Integer>();
					for (int i = 0; i < weights.size(); i++) {
						myPoints.add(weights.get(i).getWeight());
					}
					
					int currentX = 0;
					for (int i = 0; i < myPoints.size(); i++) {
						LinePoint p = new LinePoint(currentX , myPoints.get(i));
						myLine.addPoint(p);
						currentX = currentX + 1;
					}
					
					myLine.setColor(Color.parseColor("#FFFFFF"));
					lgWeight.addLine(myLine);

					lgWeight.setRangeY((myPoints.get(myPoints.indexOf(Collections.min(myPoints)))) - 3, 
							(myPoints.get(myPoints.indexOf(Collections.max(myPoints))) + 3));
					
//					lgWeight.showMinAndMaxValues(true);
					lgWeight.setHorizontalFadingEdgeEnabled(true);
					lgWeight.setTextSize(20);
					lgWeight.showHorizontalGrid(true);
					lgWeight.setGridColor(Color.parseColor("#FFFFFF"));
					
					// this is to populate some data for users who don't have weight
					// only for demo purposes
				} else {
					
				}
				
			}
		});
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

}
