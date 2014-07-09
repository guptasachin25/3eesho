package com.codepath.eesho.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.ExpandableListAdapter;
import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;

public class UserDashBoardFragment extends Fragment {
	ArrayList<Bar> points = new ArrayList<Bar>();
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    
    boolean prepareBarGraph = false;
    
	@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
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
        listDataHeader.add("Yesterday");
        listDataHeader.add("Today");
        listDataHeader.add("Tomorrow");
 
        // Adding child data
        List<String> yesterday = new ArrayList<String>();
        yesterday.add("Run for 10 minutes");
        yesterday.add("Eat Fruits");
        yesterday.add("Eat Veggies");
 
        List<String> today = new ArrayList<String>();
        today.add("Bike for 30 minutes");
        today.add("Swim for 20 minutes");
        today.add("Eat Salad for lunch");
 
        List<String> tomorrow = new ArrayList<String>();
        tomorrow.add("Drink fruit smoothie for breakfast");
        tomorrow.add("Eat salad for dinner");
        tomorrow.add("Walk for 30 minutes");
        tomorrow.add("Biked for 60 minutes");
 
        listDataChild.put(listDataHeader.get(0), yesterday);
        listDataChild.put(listDataHeader.get(1), today);
        listDataChild.put(listDataHeader.get(2), tomorrow);
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
