package com.codepath.eesho.fragments;

import com.codepath.eesho.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserProfileFragment extends Fragment {
	
	private long user_id;
	// The fragment for geting user input field names
	public static UserProfileFragment newInstance(long user_id) {
		UserProfileFragment fragment = new UserProfileFragment();
		Bundle args = new Bundle();
		args.putLong("user_id", user_id);
		fragment.setArguments(args);
		return fragment;
	}
	@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			user_id = getArguments().getLong("user_id", 0);
		}
@Override
public View onCreateView(LayoutInflater inflater,
		@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.fragment_user_profile, container,false);
	return v;
}
}
