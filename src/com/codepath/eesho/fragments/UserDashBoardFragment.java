package com.codepath.eesho.fragments;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.GoalArrayAdapter;
import com.codepath.eesho.models.DailyActivity;
import com.codepath.eesho.models.FitnessPlanSingleActivity;
import com.codepath.eesho.models.SingleActivity;
import com.codepath.eesho.parse.models.Goal;
import com.codepath.eesho.parse.models.MyActivity;
import com.facebook.widget.ProfilePictureView;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class UserDashBoardFragment extends Fragment {

	private ArrayList<SingleActivity> goals;
	private GoalArrayAdapter aGoals;
	ListView lvGoals;
	int fragmentNumber;
	DailyActivity<FitnessPlanSingleActivity> dailyActivity = null;
	Goal myGoal;
	TextView tvName;
	TextView tvGoal;
	TextView tvActivity;

	private String getUserName(ParseUser user) {
		return user.getString("name");
	}
	
	private String getUserTarget(ParseUser user) {
		String targetType = user.getString("target_type");
		if(targetType != null) {
			if(targetType.equalsIgnoreCase("run")) {
				return String.format(Locale.ENGLISH, "Run %d miles in %d months", 
						user.getNumber("target_run_distance"),
						user.getNumber("target_time"));
			} else if(targetType.toLowerCase().contains("weight")) {
				return String.format(Locale.ENGLISH, "Lose %d lbs weight in %d months",
						user.getNumber("target_weight"),
						user.getNumber("target_time"));
			} else {
				return "General Fitness";
			}
		} else {
			return "No Target";
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DateTime date = new DateTime();
		goals = new ArrayList<SingleActivity>();
		aGoals = new GoalArrayAdapter(getActivity(), goals);				
		System.out.println(date);
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

		ProfilePictureView ivPicture = (ProfilePictureView) v.findViewById(R.id.ivPicture);
		lvGoals = (ListView) v.findViewById(R.id.lvGoals);
		tvName = (TextView) v.findViewById(R.id.tvName);
		tvGoal = (TextView) v.findViewById(R.id.tvGoal);
		tvActivity = (TextView) v.findViewById(R.id.tvActivity);

		ivPicture.setPresetSize(ProfilePictureView.NORMAL);
		ivPicture.setProfileId((ParseUser.getCurrentUser().getString("facebook_id")));
		Log.d("Facebook", ParseUser.getCurrentUser().getString("facebook_id"));
		lvGoals.setAdapter(aGoals);
		lvGoals.setItemsCanFocus(true);

		tvName.setText(getUserName(ParseUser.getCurrentUser()));
		tvGoal.setText(getUserTarget(ParseUser.getCurrentUser()));

		lvGoals.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				final Boolean done;
				System.out.println("I came here after click");
				try {
					System.out.println(goals.get(position));
					System.out.println(((FitnessPlanSingleActivity) view.getTag()).toJSONObject());
					System.out.println(dailyActivity.toJson());
					FitnessPlanSingleActivity fitnessActivity = 
							(FitnessPlanSingleActivity) view.getTag();
					System.out.println(fitnessActivity.isDone());					
					myGoal.resetDone(dailyActivity, goals.get(position));
					System.out.println(fitnessActivity.isDone());

				} catch (JSONException e) {
					e.printStackTrace();
				}

				myGoal.saveInBackground(new SaveCallback() {
					@Override
					public void done(ParseException arg0) {
						System.out.println(arg0);
						if(arg0 == null) {
							System.out.println("Data Saved");
							FitnessPlanSingleActivity fitnessActivity = 
									(FitnessPlanSingleActivity) view.getTag();
							Long sign = fitnessActivity.isDone() ? 1L : -1L;
							System.out.println(sign);
							Long calories =  Math.abs((new Random().nextLong() % 100)) * sign;
							Long steps = Math.abs((new Random().nextLong() % 100)) * sign;
							MyActivity myActivity = 
									new MyActivity(ParseUser.getCurrentUser(), 
											fitnessActivity.getDescription(),
											fitnessActivity.getDisplayNumber() * sign,
											calories,
											steps);
							myActivity.saveInBackground();
							tvActivity.setText(
									Long.toString(Long.parseLong(tvActivity.getText().toString()) + steps));
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
