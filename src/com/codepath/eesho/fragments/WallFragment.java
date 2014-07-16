package com.codepath.eesho.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.MessagesAdapter;
import com.codepath.eesho.parse.models.Messages;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class WallFragment extends Fragment {
	private ArrayList<Messages> messages;
	private MessagesAdapter messageAdapter;
	ListView lvMessages;
	
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
		return;
	}

	private List<Messages> getMessages() {
		ParseQuery<Messages> query = ParseQuery.getQuery(Messages.class);
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
