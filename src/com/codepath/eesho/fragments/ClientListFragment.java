package com.codepath.eesho.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.ClientArrayAdapter;
import com.codepath.eesho.parse.models.User;

public class ClientListFragment extends Fragment{
	private ArrayList<User> clients;
	private ClientArrayAdapter aClients;
	GridView gvClients;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
      // Defines the xml file for the fragment
    	View v = inflater.inflate(R.layout.fragment_client_list, container, false);
      

		clients = new ArrayList<User>();
		gvClients = (GridView) v.findViewById(R.id.gvClients);
		/*
		 * TODO
		 * this should be pulling from the database based on which expert is logged in
		 * it should pull in all clients of an expert
		 */
		clients.add(new User("calren", "http://scienceblogs.com/gregladen/files/2012/12/Beautifull-cat-cats-14749885-1600-1200.jpg"));
		aClients = new ClientArrayAdapter(this.getActivity(), clients);
		gvClients.setAdapter(aClients);
		return v;
    }

}
