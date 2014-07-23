package com.codepath.eesho.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.eesho.R;
import com.codepath.eesho.parse.models.Messages;
import com.facebook.widget.ProfilePictureView;
import com.parse.ParseUser;

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
		TextView likes = (TextView) v.findViewById(R.id.tvLikes);
		ProfilePictureView ivFacebookPicture = (ProfilePictureView) v.findViewById(R.id.ivFacebookPicture);
		ImageView ivPicture = (ImageView) v.findViewById(R.id.ivProfileImage);

		
		/**
		 * Get Image
		 */
		String facebookId = message.getFacebookId();
		if(facebookId != null) {
			ivPicture.setVisibility(ImageView.INVISIBLE);
			ivFacebookPicture.setVisibility(ImageView.VISIBLE);
			ivFacebookPicture.setProfileId((ParseUser.getCurrentUser().getString("facebook_id")));
		} else {
			ivFacebookPicture.setVisibility(ImageView.INVISIBLE);
			ivPicture.setVisibility(ImageView.VISIBLE);
		}
		
		tvDescription.setText(Html.fromHtml(message.getMessage()));	
		likes.setText(message.getLikes().toString());
		timestamp.setText(message.getTimeSinceCurrentTime(message.getTimestamp()));
		// Set image too here...
		return v;
	}
	
	public MessagesAdapter(Context context, ArrayList<Messages> messages) {
		super(context, 0, messages);
	}
}
