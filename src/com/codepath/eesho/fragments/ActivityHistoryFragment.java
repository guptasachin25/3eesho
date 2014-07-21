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

public class ActivityHistoryFragment extends Fragment{
	
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
		
		ProgressBar pb = (ProgressBar) v.findViewById(R.id.pbCrunches);
		pb.setMax(100);
		pb.setProgress(50);
		
		ProgressBar pbPushups = (ProgressBar) v.findViewById(R.id.pbPushups);
		pbPushups.setMax(100);
		pbPushups.setProgress(30);
		
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

}
