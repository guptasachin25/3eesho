package com.codepath.eesho.fragments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.GoalArrayAdapter;
import com.codepath.eesho.parse.models.Goal;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

/*
 * this class is for the expert's view when he wants to edit a client's goals
 */
@SuppressLint("ValidFragment")
public class DailyEditPlanFragment extends Fragment{
	private ArrayList<Goal> goals;
	private GoalArrayAdapter aGoals;
	ListView lvGoals;
	Button btnAdd;
	EditText etNewGoal;
	
	private int day;
	int dayOfWeek;
	
	public DailyEditPlanFragment(int i) {
		day = i;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);
		// Define our query conditions
		/*
		 * TODO
		 * this should be pulling in whichever user is logged in
		 */
		query.whereEqualTo("username", "caren");
				
		goals = new ArrayList<Goal>();
		//aGoals = new GoalArrayAdapter(getActivity(), goals);
		
		Calendar calendar = Calendar.getInstance();
		dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);	
		
		if (day == 1) {
			query.whereEqualTo("dayOfWeek", dayOfWeek);
		} else if (day == 0) {
			if (day == 1) {
				query.whereEqualTo("dayOfWeek", 7);
			} else {
				query.whereEqualTo("dayOfWeek", dayOfWeek-1);
			}
		} else if (day == 2) {
			if (day == 7) {
				query.whereEqualTo("dayOfWeek", 1);
			} else {
				query.whereEqualTo("dayOfWeek", dayOfWeek + 1);
			}
		}
		
		query.findInBackground(new FindCallback<Goal>() {
		    public void done(List<Goal> resultGoal, ParseException e) {
		        if (e == null) {
		        	for (int i = 0; i < resultGoal.size(); i++) {
		        		try {
							goals.add(new Goal(resultGoal.get(i).isDone(), resultGoal.get(i).getGoalDescription()));
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            }
		            
		            aGoals.notifyDataSetChanged();
		            
		        } else {
		            Log.d("item", "Error: " + e.getMessage());
		        }
		    }
		});
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
		 ViewGroup container,  Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_daily_edit_plan, container, false);
		
		lvGoals = (ListView) v.findViewById(R.id.lvGoals);
		lvGoals.setAdapter(aGoals);
		btnAdd = (Button) v.findViewById(R.id.btnAdd);
		etNewGoal = (EditText) v.findViewById(R.id.etNewGoal);
		
		btnAdd.setOnClickListener(new OnClickListener(){
			@Override
            public void onClick(View v)
            {
				String goalToAdd = etNewGoal.getText().toString();
				goals.add(new Goal(false, goalToAdd));
				aGoals.notifyDataSetChanged();
				etNewGoal.setText("");
				etNewGoal.setHint("Add a new goal");
				
				int whatDayGoal = 0;
				
				if (day == 1) {
					if (dayOfWeek == 7) {
						whatDayGoal = 1;
					} else {
						whatDayGoal = dayOfWeek + 1;
					}
				} else if (day == 0) {
					whatDayGoal = dayOfWeek;
				} else if (day == 2) {
					if (day == 7) {
						whatDayGoal = 2;
					} else if (day == 6) {
						whatDayGoal = 1;
					} else {
						whatDayGoal = dayOfWeek + 2;
					}
				}
				
				Goal newGoal = new Goal(false, goalToAdd);
				
				/*
				 * TODO
				 * this should save for whichever user is logged in
				 */
				newGoal.setUserName("caren");
				newGoal.setDayOfWeek(whatDayGoal);
				newGoal.saveInBackground();
		   } 
		}); 
		
		return v;
	}

}