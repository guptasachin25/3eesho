package com.codepath.eesho.adapters;

import java.util.List;

import org.json.JSONException;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

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
		
		CheckBox ch = (CheckBox) v.findViewById(R.id.cb1);
		System.out.println("I am inside adapter");
		try {
			System.out.println(goal.toJSONObject().toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		ch.setText(goal.toString());
		ch.setChecked(goal.isDone());
		ch.setClickable(false);
		ch.setFocusable(false);
		
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
