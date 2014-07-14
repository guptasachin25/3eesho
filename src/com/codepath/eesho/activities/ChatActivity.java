package com.codepath.eesho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.codepath.eesho.R;
import com.codepath.eesho.parse.models.Messages;
import com.parse.ParseUser;

public class ChatActivity extends Activity {
	Button btnChatTitle;
	Button btnSend;
	ListView lvChatContent;
	EditText etCompose;
	
	ParseUser sender;
	ParseUser receiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		Intent intent = getIntent();
		sender = (ParseUser) intent.getSerializableExtra("sender");
		receiver = (ParseUser) intent.getSerializableExtra("receiver");
		setViews();
	}
	
	private void setViews() {
		btnChatTitle = (Button) findViewById(R.id.btnChatTitle);
		btnSend = (Button) findViewById(R.id.btnSend);
		lvChatContent = (ListView) findViewById(R.id.lvChatContent);
		etCompose = (EditText) findViewById(R.id.etCompose);
	}
	
	public void onMessageSend(View v) {
		String message = etCompose.getText().toString();
		// If message is not null or empty
		if(message != null && !message.equals("")) {
			Messages msg = new Messages(sender, receiver, message);
			msg.saveInBackground();
		}
	}
}
