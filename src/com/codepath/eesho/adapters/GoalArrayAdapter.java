package com.codepath.eesho.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.codepath.eesho.R;
import com.codepath.eesho.models.Goal;

public class GoalArrayAdapter extends ArrayAdapter<Goal> {
	
	public static Goal goal;
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		goal = getItem(position);
		
		View v;
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			v = inflator.inflate(R.layout.daily_activity_item, parent, false);
		} else {
			v = convertView;
		}
		
		CheckBox ch = (CheckBox) v.findViewById(R.id.cb1);
		ch.setText(goal.getGoalDescription());
		ch.setChecked(goal.isDone());
	
		return v;
	}
	
	
	public GoalArrayAdapter(Context context, List<Goal> goals) {
		super(context, 0, goals);

	}
	
}