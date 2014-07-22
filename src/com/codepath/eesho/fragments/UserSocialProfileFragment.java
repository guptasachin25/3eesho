package com.codepath.eesho.fragments;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.codepath.eesho.R;
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
		return v;
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
		}if(user.getString("weight") != null){
			weightTxt.setText(user.getNumber("weight").toString());
			targetTxt.setText(getUserTarget(ParseUser.getCurrentUser()));
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


