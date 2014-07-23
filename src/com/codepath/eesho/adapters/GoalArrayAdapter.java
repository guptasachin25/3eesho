package com.codepath.eesho.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.eesho.R;
import com.codepath.eesho.models.SingleActivity;

public class GoalArrayAdapter extends ArrayAdapter<SingleActivity> {
	
	public GoalArrayAdapter(Context context, List<SingleActivity> goals) {
		super(context, 0, goals);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final SingleActivity goal = getItem(position);
		View v;
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			v = inflator.inflate(R.layout.daily_activity_item, parent, false);
		} else {
			v = convertView;
		}
		
		TextView number = (TextView) v.findViewById(R.id.tvNumber);
		TextView activity = (TextView) v.findViewById(R.id.tvActivity);
		ImageView checkbox = (ImageView) v.findViewById(R.id.ivCheck);
		
		activity.setText(goal.toString());
		number.setText(Long.toString(goal.getDisplayNumber()));
		
		if(goal.isDone()) {
			checkbox.setImageResource(R.drawable.ic_check_done);
			activity.setTextColor(Color.parseColor("#d7d7d7"));
			number.setTextColor(Color.parseColor("#bdbdbd"));
		} else {
			checkbox.setImageResource(R.drawable.ic_check);
			activity.setTextColor(Color.parseColor("#696969"));
			number.setTextColor(Color.parseColor("#000000"));
		}
		v.setTag(goal);
		//
		/*
		ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		       @Override
		       public void onCheckedChanged(CompoundButton buttonView , final boolean isChecked) {
		    	   ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);
			   		// Define our query conditions
			   	   query.getInBackground(goal.getId(), new GetCallback<Goal>() {
			   	   public void done(Goal gameScore, ParseException e) {
			   		   gameScore.put("done", isChecked);
			   		   gameScore.saveInBackground();
			   	  }
			   	});
		       }
		   }
		);
		*/
		return v;
	}
}
