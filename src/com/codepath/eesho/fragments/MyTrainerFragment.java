package com.codepath.eesho.fragments;

import com.codepath.eesho.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyTrainerFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
@Override
public View onCreateView(LayoutInflater inflater,
	 ViewGroup container,  Bundle savedInstanceState) {
View v = inflater.inflate(R.layout.fragment_my_trainer, container,false);
return v;
}
}
