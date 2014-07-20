package com.codepath.eesho.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.eesho.R;
import com.codepath.eesho.parse.models.Messages;

public class MessagesAdapter extends ArrayAdapter<Messages>{
	public static Messages message;
	
	public View getView(int position, View convertView, ViewGroup parent) {
		message = getItem(position);
		View v;
		
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			v = inflator.inflate(R.layout.messages_item, parent, false);
		} else {
			v = convertView;
		}
		
		TextView tvDescription = (TextView) v.findViewById(R.id.tvDescription);
		TextView timestamp = (TextView) v.findViewById(R.id.tvTimespan);
		
		tvDescription.setText(Html.fromHtml(message.getMessage()));	
		System.out.println(message.getTimestamp());
		timestamp.setText(message.getTimeSinceCurrentTime(message.getTimestamp()));
		return v;
	}
	
	public MessagesAdapter(Context context, ArrayList<Messages> messages) {
		super(context, 0, messages);
	}
}
