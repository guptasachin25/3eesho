package com.codepath.eesho.fragments;

import com.codepath.eesho.parse.models.Messages;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class LikesWallFragment extends WallFragment {

	@Override
	ParseQuery<Messages> getQuery() {
		ParseQuery<Messages> query = ParseQuery.getQuery(Messages.class);
		query.setLimit(30);
		query.addDescendingOrder("createdAt");
		query.whereEqualTo("users_who_liked", ParseUser.getCurrentUser());
		query.include("sender");
		return query;
	}
}
