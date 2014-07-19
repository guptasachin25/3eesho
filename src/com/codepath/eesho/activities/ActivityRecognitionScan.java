package com.codepath.eesho.activities;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.ActivityRecognitionClient;

public class ActivityRecognitionScan implements GooglePlayServicesClient.ConnectionCallbacks, 
	GooglePlayServicesClient.OnConnectionFailedListener {

	private Context context;
	private static final String TAG = "ActivityRecognition";
	private static ActivityRecognitionClient mActivityRecognitionClient;
	private static PendingIntent callbackIntent;

	public ActivityRecognitionScan(Context context) {
		this.context=context;
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		Log.d(TAG, "onConnectionFailed");				
		Log.d(TAG, arg0.toString());				
	}

	public void startActivityRecognitionScan() {
		mActivityRecognitionClient	= new ActivityRecognitionClient(context, this, this);
		mActivityRecognitionClient.connect();
		Log.d(TAG,"startActivityRecognitionScan");

	}

	public void stopActivityRecognitionScan() {
		try {
			mActivityRecognitionClient.removeActivityUpdates(callbackIntent);
			Log.d(TAG,"stopActivityRecognitionScan");
		} catch (IllegalStateException e){
			// probably the scan was not set up, we'll ignore
		}
	}

	@Override
	public void onConnected(Bundle arg0) {
		Log.d(TAG, "On Connected");
		Intent intent = new Intent(context, 
				ActivityRecognitionService.class);
		callbackIntent = PendingIntent.getService(context, 0, intent,
		PendingIntent.FLAG_UPDATE_CURRENT);
		mActivityRecognitionClient.requestActivityUpdates(1000, callbackIntent);
		Log.d(TAG, "On Connected");
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}
	
	

}
