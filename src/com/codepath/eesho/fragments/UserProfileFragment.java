package com.codepath.eesho.fragments;

import com.codepath.eesho.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class UserProfileFragment extends Fragment {
	
	private long user_id;
	private EditText phone;
	private TextView gender;
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
	// Email on click becomes editable otherwise it is non editable 
	phone = (EditText) v.findViewById(R.id.etuserProfilePhone);
	gender = (TextView) v.findViewById(R.id.tvusergender);
	phone.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			((EditText)v).setEnabled(true);
		}
	});
	
	gender.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	});
	
phone.addTextChangedListener(new TextWatcher() {
	
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
		// get the phone number typed in the edittext and send it to the backend to be saved. 
		String getPhone = phone.getText().toString();
	}
});
	return v;
}
}
