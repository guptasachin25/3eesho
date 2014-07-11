package com.codepath.eesho.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.codepath.eesho.R;
import com.codepath.eesho.fragments.ClientListFragment;
import com.codepath.eesho.fragments.EditPlanActivity;

public class ExpertHomeActivity extends FragmentActivity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expert_home);
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.flPlaceholder, new ClientListFragment());
		ft.commit();

	}
	
	public void goToMessages() {
		
	}
	
	public void goToEditPlan(View v) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.flPlaceholder, new EditPlanActivity());
		ft.commit();
	}
}
