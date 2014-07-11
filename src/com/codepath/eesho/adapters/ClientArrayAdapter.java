package com.codepath.eesho.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.codepath.eesho.R;
import com.codepath.eesho.models.Article;
import com.codepath.eesho.models.User;

public class ClientArrayAdapter extends ArrayAdapter<User>{
	
	public static User user;
	
	public View getView(int position, View convertView, ViewGroup parent) {
		user = getItem(position);
		View v;
		
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			v = inflator.inflate(R.layout.client_item, parent, false);
		} else {
			v = convertView;
		}
		
		TextView tvName = (TextView) v.findViewById(R.id.tvName);
		Button btnPlan = (Button) v.findViewById(R.id.btnEditPlan);
		Button btnMsg = (Button) v.findViewById(R.id.btnMessage);
		
		tvName.setText(user.getUsernname());
		// set profile picture
		
		return v;
		
	}
	
	public ClientArrayAdapter(Context context, ArrayList<User> user) {
		super(context, 0, user);
	}

}
