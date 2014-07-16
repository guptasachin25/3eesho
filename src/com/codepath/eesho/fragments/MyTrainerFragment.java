package com.codepath.eesho.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.eesho.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class MyTrainerFragment extends Fragment {
	private TextView tvChooseTrainerName;
	private TextView etMyTrainerDescription;
	private TextView etMyExpertIn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
@Override
public View onCreateView(LayoutInflater inflater,
	 ViewGroup container,  Bundle savedInstanceState) {
View v = inflater.inflate(R.layout.fragment_my_trainer, container,false);

tvChooseTrainerName = (TextView)v. findViewById(R.id.tvChooseTrainerName);
etMyTrainerDescription = (TextView)v. findViewById(R.id.etMyTrainerDescription);
etMyExpertIn = (TextView)v. findViewById(R.id.etMyExpertIn);
ParseQuery<ParseUser> query = ParseUser.getQuery();
query.whereEqualTo("isExpert", true);

query.getFirstInBackground(new GetCallback<ParseUser>() {
	public void done(ParseUser user, ParseException e) {
		 if (e == null) {
		      Log.d("user", "found the expert");
		      setUpExpertProfile(user);  
		    } else {
		    	Log.d("expert","Expert can't be found");
		    }
	};
});


return v;
}
private void setUpExpertProfile(ParseUser user) {
	// TODO Auto-generated method stub
	if(user.getString("name") != null){
		tvChooseTrainerName.setText(user.getString("name"));
	}if(user.getString("profile_description") != null){
		etMyTrainerDescription.setText(user.getString("profile_description"));
	}if(user.getString("expert_type") !=null){
		etMyExpertIn.setText(user.getString("expert_type"));
	}
}
}
