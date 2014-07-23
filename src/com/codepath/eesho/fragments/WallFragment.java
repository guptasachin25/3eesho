package com.codepath.eesho.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.MessagesAdapter;
import com.codepath.eesho.parse.models.Messages;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public abstract class WallFragment extends Fragment {
	private ArrayList<Messages> messages;
	private MessagesAdapter messageAdapter;
	ListView lvMessages;

	abstract ParseQuery<Messages> getQuery();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_messages, container, false);
		messages = new ArrayList<Messages>();
		lvMessages = (ListView) v.findViewById(R.id.lvMessages);

		messageAdapter = new MessagesAdapter(this.getActivity(), messages);
		lvMessages.setAdapter(messageAdapter);
		setupListViewListener();
		getMessages();
		return v;
	}

	private void setupListViewListener() {
		lvMessages.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				final Messages message = messages.get(position);
				message.like();
				message.addUserWhoLiked(ParseUser.getCurrentUser());
				message.saveInBackground(new SaveCallback() {
					@Override
					public void done(ParseException arg0) {
						if(arg0 != null) {
							return;
						}
						messageAdapter.notifyDataSetChanged();
					}
				});
			}
		});
	}

	private void getMessages() {
		ParseQuery<Messages> query = getQuery();
		ParseQuery.getQuery(Messages.class);
		query.setLimit(30);
		query.addDescendingOrder("createdAt");
		query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
		query.findInBackground(new FindCallback<Messages>() {
			@Override
			public void done(List<Messages> messageList, ParseException exception) {
				if(exception == null) {
					for(Messages message: messageList) {
						messageAdapter.add(message);
						messageAdapter.notifyDataSetChanged();
					}
				}
			}
		});
	}
}