package com.codepath.eesho.fragments;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.json.JSONException;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

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

public class UserDashBoardFragment extends Fragment {
	
	private ArrayList<SingleActivity> goals;
	private GoalArrayAdapter aGoals;
	ListView lvGoals;
	private DateTime date = new DateTime();
	int fragmentNumber;
	DailyActivity<FitnessPlanSingleActivity> dailyActivity = null;
	Goal myGoal;


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
						System.out.println("While inserting..." + activity.toJSONObject());
						goals.add(activity);
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
			ViewGroup container, Bundle savedInstanceState) {


		View v = inflater.inflate(R.layout.fragment_user_dashboard, container,false);

		ImageView ivPicture = (ImageView) v.findViewById(R.id.ivPicture);

		lvGoals = (ListView) v.findViewById(R.id.lvGoals);
		lvGoals.setAdapter(aGoals);
		lvGoals.setItemsCanFocus(true);
		lvGoals.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				System.out.println("I came here after click");
				try {
					myGoal.resetDone(dailyActivity, goals.get(position));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				myGoal.saveInBackground(new SaveCallback() {
					
					@Override
					public void done(ParseException arg0) {
						System.out.println(arg0);
						if(arg0 == null) {
							System.out.println("Data Saved");
						}
					}
				});
				aGoals.notifyDataSetChanged();
			}
		});
		
		return v;
	}
	
	public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
	    int targetWidth = 50;
	    int targetHeight = 50;
	    Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, 
	                        targetHeight,Bitmap.Config.ARGB_8888);

	    Canvas canvas = new Canvas(targetBitmap);
	    Path path = new Path();
	    path.addCircle(((float) targetWidth - 1) / 2,
	        ((float) targetHeight - 1) / 2,
	        (Math.min(((float) targetWidth), 
	        ((float) targetHeight)) / 2),
	        Path.Direction.CCW);

	    canvas.clipPath(path);
	    Bitmap sourceBitmap = scaleBitmapImage;
	    canvas.drawBitmap(sourceBitmap, 
	        new Rect(0, 0, sourceBitmap.getWidth(),
	        sourceBitmap.getHeight()), 
	        new Rect(0, 0, targetWidth, targetHeight), null);
	    return targetBitmap;
	}
}
