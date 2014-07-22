package com.codepath.eesho.fragments;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.codepath.eesho.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class UserSocialProfileFragment extends Fragment {
	
	private String user_id;
	private TextView tvUserProfileName;
	private TextView targetTxt;
	private ParseUser currentUser;
	protected RadioButton rd1_dialog;
	protected RadioButton rd2_dialog;
	protected RadioButton rd3_dialog;
	

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
		Log.d("inprofileactivity", "user id in profile after activity pass it " + user_id + " first");
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container,  Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_user_social_profile, container,false);
		currentUser = ParseUser.getCurrentUser();
		tvUserProfileName = (TextView)v. findViewById(R.id.tv_userSocialProfilename);
		targetTxt = (TextView) v.findViewById(R.id.etuserSocialProfileTargetText);
		
		usernameClick();
		System.out.println(ParseUser.getCurrentUser());
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
		}
	else{
			Log.d("Getting the value for parse","value from parse is null");
		}
	}
	
		private void usernameClick() {
		// TODO Auto-generated method stub
		tvUserProfileName.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//TODO Auto-generated method stub
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				if(s != null){
					currentUser.put("name", s.toString());
					currentUser.saveInBackground(new SaveCallback() {

						@Override
						public void done(ParseException e) {
							// TODO Auto-generated method stub
							Log.d("done","user name is changed");	
						}
					});
				}
			}
		});

	}

	
	private void targetClick() {
		// TODO Auto-generated method stub
		targetTxt.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) { 
				currentUser.put("location", s.toString());
				currentUser.saveInBackground(new SaveCallback() {

					@Override
					public void done(ParseException e) {
						// TODO Auto-generated method stub
						Log.d("done","changed location is saved");	
					}
				});

			}
		});
	}
	
	public void onSave(View v){	
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
