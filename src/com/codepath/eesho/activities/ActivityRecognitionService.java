package com.codepath.eesho.activities;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

public class ActivityRecognitionService extends IntentService {

	public ActivityRecognitionService(String name) {
		super(name);
	}

	private static final String TAG = "ActivityRecognition";

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "OnHandleIntent");
		if (ActivityRecognitionResult.hasResult(intent)) {
			ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
			Log.d(TAG, "ActivityRecognitionResult: "+ getFriendlyName(result.getMostProbableActivity().getType()));
			Log.d(TAG, result.toString());
		}
	}

	private static String getFriendlyName(int detected_activity_type){
		switch (detected_activity_type ) {
		case DetectedActivity.IN_VEHICLE:
			return "in vehicle";
		case DetectedActivity.ON_BICYCLE:
			return "on bike";
		case DetectedActivity.ON_FOOT:
			return "on foot";
		case DetectedActivity.TILTING:
			return "tilting";
		case DetectedActivity.STILL:
			return "still";
		default:
			return "unknown";
		}
	}
}
