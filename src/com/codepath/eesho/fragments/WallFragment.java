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
import android.widget.Toast;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.MessagesAdapter;
import com.codepath.eesho.parse.models.Messages;
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
		messageAdapter.addAll(getMessages());
		setupListViewListener();
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
							System.out.println(arg0);
							return;
						}
						messageAdapter.notifyDataSetChanged();
					}
				});
				//Toast.makeText(getActivity(), "Message Liked", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private List<Messages> getMessages() {
		ParseQuery<Messages> query = getQuery();
		ParseQuery.getQuery(Messages.class);
		query.setLimit(30);
		query.addDescendingOrder("createdAt");
		try {
			return query.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Messages>();
		}
	}
}