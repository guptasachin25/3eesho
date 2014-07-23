package com.codepath.eesho.fragments;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.GoalArrayAdapter;
import com.codepath.eesho.models.DailyActivity;
import com.codepath.eesho.models.FitnessPlanSingleActivity;
import com.codepath.eesho.models.SingleActivity;
import com.codepath.eesho.parse.models.Goal;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

@SuppressLint("ValidFragment")
public class DayPlanFragment extends Fragment{

	private ArrayList<SingleActivity> goals;
	private GoalArrayAdapter aGoals;
	ListView lvGoals;
	private DateTime date;
	int fragmentNumber;
	DailyActivity<FitnessPlanSingleActivity> dailyActivity = null;
	Goal myGoal;
	
	public DayPlanFragment(int fragmentNumber) {
		this.fragmentNumber = fragmentNumber;
		date = new DateTime().plusDays(fragmentNumber - 1);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_todaygoals_list, container, false);
		lvGoals = (ListView) v.findViewById(R.id.lvGoals);
		lvGoals.setAdapter(aGoals);
		lvGoals.setItemsCanFocus(true);
		lvGoals.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				try {
					myGoal.resetDone(dailyActivity, goals.get(position));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				myGoal.saveInBackground(new SaveCallback() {
					
					@Override
					public void done(ParseException arg0) {
						if(arg0 != null) {
							System.out.println(arg0);
						}
					}
				});
				aGoals.notifyDataSetChanged();
			}
		});
		return v;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		goals = new ArrayList<SingleActivity>();
		aGoals = new GoalArrayAdapter(getActivity(), goals);

		ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		query.whereEqualTo("date", date.toDateMidnight().toDate());

		query.getFirstInBackground(new GetCallback<Goal>() {
			public void done(Goal goal, ParseException e) {
				if (e == null) {
					myGoal = goal;
					try {
						dailyActivity = goal.getDailyActivity();
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
					for(FitnessPlanSingleActivity activity: dailyActivity.getActivityList()) {
						goals.add(activity);
					}
					aGoals.notifyDataSetChanged();

				} else {
					Log.d("item", "Error: " + e.getMessage());
				}
			}
		});
	}

}
