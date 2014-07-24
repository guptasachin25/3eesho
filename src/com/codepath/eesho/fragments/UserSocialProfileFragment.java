package com.codepath.eesho.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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

	private TextView tvUserProfileName;
	private TextView targetTxt;
	protected RadioButton rd1_dialog;
	protected RadioButton rd2_dialog;
	protected RadioButton rd3_dialog;
	private TextView likesTxt;
	private TextView activityTxt;
	private TextView weightTxt;
	private ProgressBar progressBar;

	private int totalLikes = 0;
	private int totalActivityCount = 0;

	// The fragment for getting user input field names
	public static UserSocialProfileFragment newInstance(String userid) {
		UserSocialProfileFragment fragment = new UserSocialProfileFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container,  Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_user_social_profile, container,false);
		getActivity().getActionBar().setTitle("Profile");
		tvUserProfileName = (TextView)v. findViewById(R.id.tv_userSocialProfilename);
		targetTxt = (TextView) v.findViewById(R.id.etuserSocialProfileTargetText);
		likesTxt = (TextView) v.findViewById(R.id.tvSocialProfileLikes);
		activityTxt = (TextView) v.findViewById(R.id.tvSocialProfileActivity);
		weightTxt = (TextView) v.findViewById(R.id.tvSocialProfileWeight);
		progressBar = (ProgressBar) v.findViewById(R.id.pbLoading);
		
		progressBar.setVisibility(ProgressBar.VISIBLE);
		setUpUserData(ParseUser.getCurrentUser());
		listofShoutMessages();
		totalLikeMessages();
		totalActivity();
		progressBar.setVisibility(ProgressBar.INVISIBLE);
		return v;
	}

	private void totalActivity() {
		ParseQuery<MyActivity> query = ParseQuery.getQuery(MyActivity.class);
		query.whereEqualTo("user",ParseUser.getCurrentUser());
		
		query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
		query.findInBackground(new FindCallback<MyActivity>(){
			public void done(List<MyActivity> itemList, ParseException e){
				if(e == null) {
					totalActivityCount = 0;
					for(MyActivity activity: itemList) {
						totalActivityCount += activity.getDimension().intValue();
					}
				} else {
					Log.d("item", "Error: " + e.getMessage());
				} 
				Log.d("Total activity", "activity is here " + totalActivityCount);
				activityTxt.setText(String.valueOf(totalActivityCount));
			}
		});
	}

	private void totalLikeMessages() {
		ParseQuery<Messages> query = ParseQuery.getQuery(Messages.class);
		query.whereEqualTo("sender",ParseUser.getCurrentUser());
		
		query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
		query.findInBackground(new FindCallback<Messages>() {
			public void done(List<Messages> itemList, ParseException e){
				if(e == null) {
					totalLikes = 0;
					for(Messages msg: itemList) {
						totalLikes += msg.getLikes().intValue();
					}
				} else {
					Log.d("item", "Error: " + e.getMessage());
				} 
				Log.d("Total lIKES", "lIKES ARE HERE " + totalLikes);
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
		if(user.getString("name") != null) {
			tvUserProfileName.setText(user.getString("name"));
			targetTxt.setText(Html.fromHtml(UserDashBoardFragment.getUserTarget(ParseUser.getCurrentUser())));
		} if(user.getNumber("weight") != null) {
			weightTxt.setText(user.getNumber("weight").toString());
		}
	}
}


