package com.codepath.eesho.fragments;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;
import org.json.JSONException;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.codepath.eesho.R;
import com.codepath.eesho.activities.UserMetricsActivity;
import com.codepath.eesho.adapters.ExpandableListAdapter;
import com.codepath.eesho.models.DailyActivity;
import com.codepath.eesho.models.FitnessPlanSingleActivity;
import com.codepath.eesho.models.SingleActivity;
import com.codepath.eesho.parse.models.Goal;
import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/*
 * TODO
 * the graph still is populating random data, but i'm thinking of removing the graph and putting in something
 * more visually pleasing. such as how many day streak the user is on
 */
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

		for(int i = -1; i < 7; i ++) {
			DateTime datetime = new DateTime();
			String weekDay = UserMetricsActivity.weekDayMap.get(datetime.plusDays(i).getDayOfWeek());
			listDataHeader.add(weekDay);
			List<String> li = new ArrayList<String>();
			getDoneTasks(datetime.plusDays(i).toDateMidnight().toDate(), 
					ParseUser.getCurrentUser(), li);
			listDataChild.put(weekDay, li);
		}
	}

	public static void getDoneTasks(Date date, ParseUser user, final List<String> li) {
		ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);

		query.whereEqualTo("user", user);
		query.whereEqualTo("date", date);

		query.getFirstInBackground(new GetCallback<Goal>() {

			@Override
			public void done(Goal goal, ParseException exception) {
				if(exception == null) {
					DailyActivity<FitnessPlanSingleActivity> dailyAcitivity = null;
					try {
						dailyAcitivity = goal.getDailyActivity();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(SingleActivity activity : dailyAcitivity.getActivityList()) {
						if(activity.isDone()) {
							li.add(activity.toString());
						}
					}
				}
			}
		});
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
