package com.codepath.eesho.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.ExpandableListAdapter;
import com.codepath.eesho.models.Goal;
import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class UserDashBoardFragment extends Fragment {
	ArrayList<Bar> points = new ArrayList<Bar>();
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    
//    static ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);
	static ArrayList<String> doneGoals = new ArrayList<String>();
	
    boolean prepareBarGraph = false;
    
	@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
		ViewGroup container, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_user_dashboard, container,false);
		
		BarGraph g = (BarGraph) v.findViewById(R.id.graph);
		if (!prepareBarGraph) {
			setUpBarGraph();
			prepareBarGraph = true;
		}
		g.setBars(points);
		g.setShowBarText(false);
		
		expListView = (ExpandableListView) v.findViewById(R.id.lvExp);
		prepareListData();
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        
		return v;
	}
	
	private void prepareListData() {
		
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader.add("Monday");
        listDataHeader.add("Tuesday");
        listDataHeader.add("Wednesday");
        listDataHeader.add("Thursday");
        listDataHeader.add("Friday");
        listDataHeader.add("Saturday");
        listDataHeader.add("Sunday");

        // Adding child data
        List<String> monday = new ArrayList<String>();
        getDoneTasks(2, "caren", monday);
        
        List<String> tuesday = new ArrayList<String>();
        getDoneTasks(3, "caren", tuesday);
        
        List<String> wed = new ArrayList<String>();
        getDoneTasks(4, "caren", wed);
        
        List<String> thurs = new ArrayList<String>();
        getDoneTasks(5, "caren", thurs);
        
        final List<String> fri = new ArrayList<String>();
        getDoneTasks(6, "caren", fri);
        
        List<String> sat = new ArrayList<String>();
        getDoneTasks(7, "caren", sat);
        
        List<String> sun = new ArrayList<String>();
        getDoneTasks(1, "caren", sun);

        listDataChild.put(listDataHeader.get(0), monday);
        listDataChild.put(listDataHeader.get(1), tuesday);
        listDataChild.put(listDataHeader.get(2), wed);
        listDataChild.put(listDataHeader.get(3), thurs);
        listDataChild.put(listDataHeader.get(4), fri);
        listDataChild.put(listDataHeader.get(5), sat);
        listDataChild.put(listDataHeader.get(6), sun);

    }
	
	public static ArrayList<String> getDoneTasks(int dayOfWeek, String username, final List<String> li) {
	    ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);

		query.whereEqualTo("username", username);
		query.whereEqualTo("dayOfWeek", dayOfWeek);
		query.whereEqualTo("done", true);
		
		query.findInBackground(new FindCallback<Goal>() {
		    public void done(List<Goal> goals, ParseException e) {
		        if (e == null) {
		            for (int i = 0; i < goals.size(); i++) {
		            	li.add(goals.get(i).getGoalDescription().toString());
		            	System.out.println(goals.get(i).getGoalDescription().toString());
		            }
		            
		        } else {
		            Log.d("item", "Error: " + e.getMessage());
		        }
		    }
		});
		
		return doneGoals;
	}

	
	public void setUpBarGraph() {		
		Bar d = new Bar();
		d.setColor(Color.parseColor("#99CC00"));
		d.setName("Monday");
		d.setValue(100);
		
		Bar d2 = new Bar();
		d2.setColor(Color.parseColor("#FFBB33"));
		d2.setName("Tuesday");
		d2.setValue(200);
		
		Bar d3 = new Bar();
		d3.setColor(Color.parseColor("#ff3232"));
		d3.setName("Wednesday");
		d3.setValue(1000);
		
		Bar d4 = new Bar();
		d4.setColor(Color.parseColor("#38B0DE"));
		d4.setName("Thursday");
		d4.setValue(800);
		
		Bar d5 = new Bar();
		d5.setColor(Color.parseColor("#99CC00"));
		d5.setName("Friday");
		d5.setValue(800);
		
		Bar d6 = new Bar();
		d6.setColor(Color.parseColor("#FFBB33"));
		d6.setName("Saturday");
		d6.setValue(700);
		
		Bar d7 = new Bar();
		d7.setColor(Color.parseColor("#ff3232"));
		d7.setName("Sunday");
		d7.setValue(1000);
		
		points.add(d);
		points.add(d2);
		points.add(d3);
		points.add(d4);
		points.add(d5);
		points.add(d6);
		points.add(d7);

	}
}
