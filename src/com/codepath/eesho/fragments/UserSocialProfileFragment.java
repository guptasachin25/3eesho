package com.codepath.eesho.fragments;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.codepath.eesho.R;
import com.codepath.eesho.parse.models.Messages;
import com.codepath.eesho.parse.models.MyActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class UserSocialProfileFragment extends Fragment {
	
	private String user_id;
	private TextView tvUserProfileName;
	private TextView targetTxt;
	private ParseUser currentUser;
	protected RadioButton rd1_dialog;
	protected RadioButton rd2_dialog;
	protected RadioButton rd3_dialog;
	private TextView likesTxt;
	private TextView activityTxt;
	private TextView weightTxt;
	private int totalLikes = 0;
	private int totalActivityCount = 0;
	

	// The fragment for getting user input field names
	public static UserSocialProfileFragment newInstance(String userid) {
		UserSocialProfileFragment fragment = new UserSocialProfileFragment();
		Bundle args = new Bundle();
		args.putString("user_id", userid);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setHasOptionsMenu(true);
		Log.d("inprofileactivity", "user id in profile after activity pass it " + user_id + " first");
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container,  Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_user_social_profile, container,false);
		currentUser = ParseUser.getCurrentUser();
		tvUserProfileName = (TextView)v. findViewById(R.id.tv_userSocialProfilename);
		targetTxt = (TextView) v.findViewById(R.id.etuserSocialProfileTargetText);
		likesTxt = (TextView) v.findViewById(R.id.tvSocialProfileLikes);
		activityTxt = (TextView) v.findViewById(R.id.tvSocialProfileActivity);
		weightTxt = (TextView) v.findViewById(R.id.tvSocialProfileWeight);
		setUpUserData(ParseUser.getCurrentUser());
		listofShoutMessages();
		totalLikeMessages();
		totalActivity();
		return v;
	}
	
	private void totalActivity() {
		ParseQuery<MyActivity> query = ParseQuery.getQuery(MyActivity.class);
		query.whereEqualTo("user",ParseUser.getCurrentUser());
		query.findInBackground(new FindCallback<MyActivity>(){
			public void done(List<MyActivity> itemList, ParseException e){
				if(e == null){
					for(MyActivity activity: itemList) {
						totalActivityCount += activity.getDimension().intValue();
					}
				}else {
		            Log.d("item", "Error: " + e.getMessage());
		        }Log.d("Total activity", "activity is here " + totalActivityCount);
		        activityTxt.setText(String.valueOf(totalActivityCount));
			}
		});
	}

	private void totalLikeMessages() {
		
		ParseQuery<Messages> query = ParseQuery.getQuery(Messages.class);
		query.whereEqualTo("sender",ParseUser.getCurrentUser());
		query.findInBackground(new FindCallback<Messages>(){
			public void done(List<Messages> itemList, ParseException e){
				if(e == null){
					for(Messages msg: itemList) {
						totalLikes += msg.getLikes().intValue();
					}
				}else {
		            Log.d("item", "Error: " + e.getMessage());
		        }Log.d("Total lIKES", "lIKES ARE HERE " + totalLikes);
		        likesTxt.setText(String.valueOf(totalLikes));
			}

		});
		
	}

	private void listofShoutMessages() {
		Fragment myWallFragment = new MyWallFragment();
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.add(R.id.fr_myshoutMessage, myWallFragment).commit();
	}

	private void setUpUserData(ParseUser user) {
		if(user.getString("name") != null){
			tvUserProfileName.setText(user.getString("name"));
			targetTxt.setText(getUserTarget(ParseUser.getCurrentUser()));
		}if(user.getNumber("weight") != null){
			weightTxt.setText(user.getNumber("weight").toString());
		}
	else{
			Log.d("Getting the value for parse","value from parse is null");
		}
	}
	
	private String getUserTarget(ParseUser user) {
		String targetType = user.getString("target_type");
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
	}
	

}


