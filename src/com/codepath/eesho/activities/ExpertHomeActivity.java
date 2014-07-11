package com.codepath.eesho.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.codepath.eesho.R;
import com.codepath.eesho.fragments.ClientListFragment;

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
//		Intent i = new Intent(ExpertHomeActivity.this, EditPlanActivity.class);
//        startActivity(i);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		// Replace the container with the new fragment
		ft.replace(R.id.flPlaceholder, new EditPlanActivity());
		// or ft.add(R.id.your_placeholder, new FooFragment());
		// Execute the changes specified
		ft.commit();
	}
}
