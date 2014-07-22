package com.codepath.eesho.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.eesho.R;

public class ForgotPasswordDialog extends DialogFragment {
	
	EditText etUserName;
	Button btnSubmit;
	
	public ForgotPasswordDialog() {
		// Do Nothing
	}
	
	public static ForgotPasswordDialog newInstance(String title) {
		ForgotPasswordDialog frag = new ForgotPasswordDialog();
		Bundle args = new Bundle();
		args.putString("title", title);
		frag.setArguments(args);
		return frag;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_forgot_password, container);
		etUserName = (EditText) view.findViewById(R.id.etUserName);
		String title = getArguments().getString("title", "Enter Email Address");
		getDialog().setTitle(title);
		
		// Show soft keyboard automatically
		etUserName.requestFocus();
		getDialog().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		return view;
	}
}
