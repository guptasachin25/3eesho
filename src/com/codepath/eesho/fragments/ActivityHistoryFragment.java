package com.codepath.eesho.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codepath.eesho.R;

public class ActivityHistoryFragment extends Fragment{
	
	ProgressBar pbMiles;
	ProgressBar pbSitups;
	ProgressBar pbCrunches;
	ProgressBar pbPushups;
	ProgressBar pbLunges;
	ProgressBar pbShoulder;
	
	TextView tvMiles;
	TextView tvSitups;
	TextView tvCrunches;
	TextView tvPushups;
	TextView tvLunges;
	TextView tvShoulder;
	
	Button bWeek;
	Button bMonth;
	Button bAll;
	
	// keeps track of which history is currently displayed
	// 1 - week, 2 - month, 3 - all
	int currentlyClickedBtn = 1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_activity_history, container, false);
		
		bWeek = (Button) v.findViewById(R.id.btWeek);
		bMonth = (Button) v.findViewById(R.id.btMonth);
		bAll = (Button) v.findViewById(R.id.btAll);
		
		pbMiles = (ProgressBar) v.findViewById(R.id.pbMiles);
		pbSitups = (ProgressBar) v.findViewById(R.id.pbSitups);
		pbCrunches = (ProgressBar) v.findViewById(R.id.pbCrunches);
		pbPushups = (ProgressBar) v.findViewById(R.id.pbPushups);
		pbLunges = (ProgressBar) v.findViewById(R.id.pbLunges);
		pbShoulder = (ProgressBar) v.findViewById(R.id.pbShoulder);
		
		tvMiles = (TextView) v.findViewById(R.id.tvMilesCount);
		tvSitups = (TextView) v.findViewById(R.id.tvSitupsCount);
		tvCrunches = (TextView) v.findViewById(R.id.tvCrunchesCount);
		tvPushups = (TextView) v.findViewById(R.id.tvPushupsCount);
		tvLunges = (TextView) v.findViewById(R.id.tvLungesCount);
		tvShoulder = (TextView) v.findViewById(R.id.tvShoulderCount);

		setUpWeekBars();
		
		bMonth.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	bMonth.setTextColor(Color.parseColor("#FFFFFF"));
		        bMonth.setBackgroundResource(R.drawable.history_button);
		        if (currentlyClickedBtn == 1) {
		        	setToUnclick(bWeek);
		        } else {
		        	setToUnclick(bAll);
		        }
		        currentlyClickedBtn = 2;
		        setUpMonthBars();
		    }
		});
		
		bAll.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	bAll.setTextColor(Color.parseColor("#FFFFFF"));
		        bAll.setBackgroundResource(R.drawable.history_button);
		        if (currentlyClickedBtn == 1) {
		        	setToUnclick(bWeek);
		        } else {
		        	setToUnclick(bMonth);
		        }
		        currentlyClickedBtn = 3;
		        setUpAllBars();
		    }
		});
		
		bWeek.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	bWeek.setTextColor(Color.parseColor("#FFFFFF"));
		        bWeek.setBackgroundResource(R.drawable.history_button);
		        if (currentlyClickedBtn == 2) {
		        	setToUnclick(bMonth);
		        } else {
		        	setToUnclick(bAll);
		        }
		        currentlyClickedBtn = 1;
		        setUpWeekBars();
		    }
		});
		
		return v;
	}
	
	public void setToUnclick(Button b) {
		b.setBackgroundResource(R.drawable.history_button_unclicked);
		b.setTextColor(Color.parseColor("#a8a8a8"));
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void setBar (ProgressBar b, TextView tv, int max, int progress) {
		b.setMax(max);
		b.setProgress(progress);
		tv.setText(Integer.toString(progress));
	}
	
	public void setUpWeekBars() {
		
		setBar(pbMiles, tvMiles, 25, 50);
		setBar(pbSitups, tvSitups, 100, 30);
		setBar(pbCrunches, tvCrunches, 200, 34);
		setBar(pbPushups, tvPushups, 500, 200);
		setBar(pbLunges, tvLunges, 500, 125);
		setBar(pbShoulder, tvShoulder, 300, 175);
		
	}
	
	public void setUpMonthBars() {
		setBar(pbMiles, tvMiles, 50, 50);
		setBar(pbSitups, tvSitups, 400, 30);
		setBar(pbCrunches, tvCrunches, 300, 34);
		setBar(pbPushups, tvPushups, 500, 200);
		setBar(pbLunges, tvLunges, 900, 125);
		setBar(pbShoulder, tvShoulder, 300, 175);
	}
	
	public void setUpAllBars() {
		setBar(pbMiles, tvMiles, 500, 50);
		setBar(pbSitups, tvSitups, 10000, 30);
		setBar(pbCrunches, tvCrunches, 20000, 34);
		setBar(pbPushups, tvPushups, 50000, 200);
		setBar(pbLunges, tvLunges, 50000, 125);
		setBar(pbShoulder, tvShoulder, 10000, 175);	
	}

}
