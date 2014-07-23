package com.codepath.eesho.fragments;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import org.joda.time.DateTime;
import org.json.JSONException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.eesho.R;
import com.codepath.eesho.activities.ShoutActivity;
import com.codepath.eesho.adapters.GoalArrayAdapter;
import com.codepath.eesho.models.DailyActivity;
import com.codepath.eesho.models.FitnessPlanSingleActivity;
import com.codepath.eesho.models.SingleActivity;
import com.codepath.eesho.parse.models.Goal;
import com.codepath.eesho.parse.models.Messages;
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
	Button btShout;
	ListView lvGoals;
	int fragmentNumber;
	DailyActivity<FitnessPlanSingleActivity> dailyActivity = null;
	Goal myGoal;
	TextView tvName;
	TextView tvGoal;
	TextView tvActivity;
	ProfilePictureView ivFacebookPicture;
	ImageView ivPicture;
	
	private String getUserName(ParseUser user) {
		return user.getString("name");
	}
	
	public int getDoneGoals() {
		int doneCount = 0;
		for (SingleActivity s : goals) {
			if (s.isDone()) {
				doneCount ++;
			}
		}
		return doneCount;
	}
	
	public static String getUserTarget(ParseUser user) {
		String target = null;
		String targetType = user.getString("target_type");
		Number targetRun = user.getNumber("target_run_distance");
		Number targetTime = user.getNumber("target_time");
		Number targetWeight = user.getNumber("target_weight");
		
		if(targetType != null) {
			if(targetType.equalsIgnoreCase("run") && targetRun != null && targetTime != null) {
				return String.format(Locale.ENGLISH, "Run %d miles in %d months", 
						targetRun, targetTime);
			} else if(targetType.toLowerCase().contains("weight") && targetWeight != null && targetTime != null) {
				return String.format(Locale.ENGLISH, "Lose %d lbs weight in %d months",
						targetWeight, targetTime);
			} else if (targetType.equalsIgnoreCase("fitness")) {
				return "General Fitness";
			}
		}
		
		return "<font color='#00BCD4'><u><b>Set Target</b></u></font>";
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		goals = new ArrayList<SingleActivity>();
		aGoals = new GoalArrayAdapter(getActivity(), goals);				
		
		ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		query.whereEqualTo("date", new DateTime().toDateMidnight().toDate());

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
						
						// may cause null pointer exception, but is workaround for now
						changeShoutButton(getDoneGoals());
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

		ivFacebookPicture = (ProfilePictureView) v.findViewById(R.id.ivFacebookPicture);
		ivPicture = (ImageView) v.findViewById(R.id.ivCircularPicture);
		lvGoals = (ListView) v.findViewById(R.id.lvGoals);
		tvName = (TextView) v.findViewById(R.id.tvName);
		tvGoal = (TextView) v.findViewById(R.id.tvGoal);
		tvActivity = (TextView) v.findViewById(R.id.tvActivity);
		btShout = (Button) v.findViewById(R.id.btShout);

		ivFacebookPicture.setPresetSize(ProfilePictureView.NORMAL);
		String facebookPic = ParseUser.getCurrentUser().getString("facebook_id");
		if(facebookPic != null) {
			ivPicture.setVisibility(ImageView.INVISIBLE);
			ivFacebookPicture.setVisibility(ImageView.VISIBLE);
			ivFacebookPicture.setProfileId((ParseUser.getCurrentUser().getString("facebook_id")));
			Log.d("Facebook", ParseUser.getCurrentUser().getString("facebook_id"));
			
		} else {
			ivFacebookPicture.setVisibility(ImageView.INVISIBLE);
			ivPicture.setVisibility(ImageView.VISIBLE);
		}
		lvGoals.setAdapter(aGoals);
		lvGoals.setItemsCanFocus(true);

		tvName.setText(getUserName(ParseUser.getCurrentUser()));
		tvGoal.setText(Html.fromHtml(getUserTarget(ParseUser.getCurrentUser())));
		
		// this doesnt work??
		changeShoutButton(getDoneGoals());
		
		btShout.setOnClickListener(new OnClickListener(){
	        @Override
	        public void onClick(View view) {
	    		int numOfGoals = goals.size();
	    		int doneCount = getDoneGoals();
	    		if (doneCount == numOfGoals) {
	    			Toast.makeText(getActivity(), "Awesome job!", Toast.LENGTH_SHORT).show();
	    			shout();
	    		} else if (doneCount == numOfGoals - 1) {
	    			Toast.makeText(getActivity(), "You're almost done!", Toast.LENGTH_SHORT).show();
	    		} else {
	    			Toast.makeText(getActivity(), "Do some more exercise!", Toast.LENGTH_SHORT).show();
	    		}
	        }
	    });

		lvGoals.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				final Boolean done;
				try {
					FitnessPlanSingleActivity fitnessActivity = 
							(FitnessPlanSingleActivity) view.getTag();
					myGoal.resetDone(dailyActivity, goals.get(position));
				} catch (JSONException e) {
					e.printStackTrace();
				}

				myGoal.saveInBackground(new SaveCallback() {
					@Override
					public void done(ParseException arg0) {
						if(arg0 == null) {
							FitnessPlanSingleActivity fitnessActivity = 
									(FitnessPlanSingleActivity) view.getTag();
							Long sign = fitnessActivity.isDone() ? 1L : -1L;
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
							changeShoutButton(getDoneGoals());
						}
					}
				});
				aGoals.notifyDataSetChanged();
			}
		});
		return v;
	}
	
	public void changeShoutButton(int num) {
		switch (num) {
			case 1: btShout.setBackgroundResource(R.drawable.shout_button_1);
				break;
        	case 2: btShout.setBackgroundResource(R.drawable.shout_button_2);
                break;
        	case 3:btShout.setBackgroundResource(R.drawable.shout_button);
        		break;
        	case 0: btShout.setBackgroundResource(R.drawable.shout_button_0);
    			break;
		}
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
	
	@SuppressWarnings("deprecation")
	public void shout() {
		DateTime date = new DateTime();
		ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		query.whereEqualTo("date", date.toDateMidnight().toDate());

		query.getFirstInBackground(new GetCallback<Goal>() {
			public void done(Goal goal, ParseException e) {
				if (e == null) {
					try {
						StringBuilder builder = new StringBuilder();
						builder.append("<font color='#01DFD7'><b>" + ParseUser.getCurrentUser().getString("name") + 
								"</b></font>" +" did ");
						boolean flag = false;
						for(FitnessPlanSingleActivity activity: goal.getDailyActivity().getActivityList()) {
							if(activity.isDone()) {
								if(activity.getDescription().toLowerCase().contains("run")) {
									Messages message = new Messages();
									message.setMessage(String.format("<font color='#01DFD7'><b>%s</b></font> ran %d miles", 
											ParseUser.getCurrentUser().getString("name"), activity.getDistance()));

									message.setSender(ParseUser.getCurrentUser());
									message.saveInBackground();
									continue;
								} else if(activity.getDescription().toLowerCase().contains("walk")) {
									Messages message = new Messages();
									message.setMessage(String.format("<font color='#01DFD7'><b>%s</b></font> walked for %d minutes", 
											ParseUser.getCurrentUser().getString("name"), activity.getDuration()));
									message.setSender(ParseUser.getCurrentUser());
									message.saveInBackground();
									continue;
								} else {
									flag = true;
									builder.append(activity.getRepititions() * activity.getSets() + " " + 
											activity.getDescription() + ", ");
								}
							}
						}
						
						if(flag) {
							Messages message = new Messages();
							String m = builder.toString().trim();
							message.setMessage(m.substring(0, m.length()-1) + ".");
							message.setSender(ParseUser.getCurrentUser());
							message.saveInBackground();
						}

					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				} else {
					Log.d("item", "Error: " + e.getMessage());
				}
			}
		});
		Intent intent = new Intent(this.getActivity(), ShoutActivity.class);
		startActivity(intent);
	}
}
