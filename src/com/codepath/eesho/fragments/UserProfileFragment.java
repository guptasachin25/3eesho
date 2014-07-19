package com.codepath.eesho.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;

import com.codepath.eesho.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class UserProfileFragment extends DialogFragment {
	
	private String user_id;
	//private ParseUser currentUser; 
	private TextView tvUserProfileName;
	private TextView gender;
	private TextView location;
	private TextView weight;
	private TextView height;
	private TextView target;
	private NumberPicker feet;
	private NumberPicker inch;
	private NumberPicker minPicker;
	private NumberPicker maxPicker;
	private ParseUser currentUser;
	private TextView tv_profileage;
	private NumberPicker agePicker;
	protected RadioButton rd1_dialog;
	protected RadioButton rd2_dialog;
	protected RadioButton rd3_dialog;
	

	// The fragment for getting user input field names
	public static UserProfileFragment newInstance(String userid) {
		UserProfileFragment fragment = new UserProfileFragment();
		Bundle args = new Bundle();
		args.putString("user_id", userid);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		user_id = getArguments().getString("user_id");

		Log.d("inprofileactivity", "user id in profile after activity pass it " + user_id + " first");
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container,  Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_user_profile, container,false);
		currentUser = ParseUser.getCurrentUser();
		// get the current user object
		tv_profileage = (TextView)v. findViewById(R.id.tv_profileage);
		tvUserProfileName = (TextView)v. findViewById(R.id.tv_userProfilename);
		gender = (TextView) v.findViewById(R.id.tv_profile_gender);
		location = (TextView) v.findViewById(R.id.etuserProfileLocation);
		weight = (TextView) v.findViewById(R.id.tv_profile_weight);
		height = (TextView) v.findViewById(R.id.tv_profile_height);
		target = (TextView) v.findViewById(R.id.tv_profile_activity);
		ageClick();
		usernameClick();
		locationClick();
		genderClick();
		targetClick();
		weightClick();
		heightClick();
		
		imageViewCircle();
		System.out.println(ParseUser.getCurrentUser());
		setUpUserData(ParseUser.getCurrentUser());
		return v;

	}

	private void imageViewCircle() {
		// TODO Auto-generated method stub
		
	}

	private void setUpUserData(ParseUser user) {
		if(user.getString("dob") != null){
			tv_profileage.setText(user.getString("dob"));
		}if(user.getString("name") != null){
			tvUserProfileName.setText(user.getString("name"));
		} if(user.getString("sex") != null){
			gender.setText(user.getString("sex"));
		}  if( user.getString("location") != null){
			location.setText(user.getString("location"));
		} if( user.getNumber("weight") != null){
			weight.setText(user.getNumber("weight").toString());
		} if(user.getNumber("height_feet") != null && user.getNumber("height_inches") != null) {
			height.setText(user.getNumber("height_feet").toString() + "feet " + user.getNumber("height_inches").toString() + "inches");
		}  if(user.getString("activity_level") != null){
			target.setText(user.getString("activity_level"));
		}else{
			Log.d("Getting the value for parse","value from parse is null");
		}
	}
	
	private void weightClick() {
		weight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LayoutInflater inflater = (LayoutInflater)
						getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View npView = inflater.inflate(R.layout.weight_picker_dialog, null);
				minPicker = (NumberPicker) npView.findViewById(R.id.first_picker);
				minPicker.setMaxValue(771);
				minPicker.setMinValue(34);
				maxPicker = (NumberPicker) npView.findViewById(R.id.second_picker);
				maxPicker.setMaxValue(9);
				maxPicker.setMinValue(0);

				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("Weight:");
				builder.setView(npView);
				builder.setPositiveButton("Okay",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						int weightvalue = minPicker.getValue();
						int pointvalue = maxPicker.getValue();
						weight.setText(weightvalue+"."+pointvalue+"lb");
						currentUser.put("weight", weightvalue);
						currentUser.saveInBackground(new SaveCallback() {

							@Override
							public void done(ParseException e) {
								// TODO Auto-generated method stub
								Log.d("done","changed weight is saved");	
							}
						});
					}
				});
				builder.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				});
				builder.create();
				builder.show();
			}
		});	
	}

	private void usernameClick() {
		// TODO Auto-generated method stub
		tvUserProfileName.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//TODO Auto-generated method stub
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				if(s != null){
					currentUser.put("name", s.toString());
					currentUser.saveInBackground(new SaveCallback() {

						@Override
						public void done(ParseException e) {
							// TODO Auto-generated method stub
							Log.d("done","user name is changed");	
						}
					});
				}
			}
		});

	}

	private void heightClick() {
		height.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				LayoutInflater inflater = (LayoutInflater)
						getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View npView = inflater.inflate(R.layout.number_picker_dialog, null);
				feet = (NumberPicker) npView.findViewById(R.id.min_picker);
				feet.setMaxValue(7);
				feet.setMinValue(4);
				inch = (NumberPicker) npView.findViewById(R.id.max_picker);
				inch.setMaxValue(11);
				inch.setMinValue(0);

				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("Height");
				builder.setView(npView);
				builder.setPositiveButton("Okay",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						int feetvalue = feet.getValue();
						int inchvalue = inch.getValue();
						height.setText(feetvalue+"feet'"+inchvalue+"inches");
						currentUser.put("height_feet",feetvalue);
						currentUser.put("height_inches",inchvalue);
						currentUser.saveInBackground(new SaveCallback() {

							@Override
							public void done(ParseException e) {
								// TODO Auto-generated method stub
								Log.d("done","Saved the height in parse database");
							}
						});
					}
				});
				builder.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				});
				builder.create();
				builder.show();
			}
		});
	}
	private void ageClick() {
		tv_profileage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				LayoutInflater inflater = (LayoutInflater)
						getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View npView = inflater.inflate(R.layout.age_picker_dialog, null);
				agePicker = (NumberPicker) npView.findViewById(R.id.age_picker);
				agePicker.setMaxValue(100);
				agePicker.setMinValue(20);
			
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("Age:");
				builder.setView(npView);
				builder.setPositiveButton("Okay",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						int ageValue = agePicker.getValue();
						tv_profileage.setText(String.valueOf(ageValue));
						currentUser.put("dob", String.valueOf(ageValue));
						currentUser.saveInBackground(new SaveCallback() {

							@Override
							public void done(ParseException e) {
								// TODO Auto-generated method stub
								Log.d("done","changed dob is saved");	
							}
						});
					}
				});
				builder.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				});
				builder.create();
				builder.show();
			}
		});
	}
	
	private void targetClick() {
		target.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final CharSequence[] items={"High Activity","Medium Activity","Low Activity"};
			
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("Set Activity");
				
				builder.setPositiveButton("Okay",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						 if(currentUser.getString("activity_level") != null){
								target.setText(currentUser.getString("activity_level"));
							}
						}
				});
				builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						
						if("High Activity".equals(items[which]))
						{
							target.setText("High Activity");
						}
						else if("Medium Activity".equals(items[which]))
						{
							target.setText("Medium Activity");
							}
						else if("Low Activity".equals(items[which]))
						{
							target.setText("Low Activity");
							}
						currentUser.put("activity_level", items[which]);
						currentUser.saveInBackground(new SaveCallback() {

							@Override
							public void done(ParseException e) {
								// TODO Auto-generated method stub
								Log.d("done","changed target is saved");	
							}
						});
					}
				});
				builder.create();
				builder.show();
			}
		});

	}
	private void locationClick() {
		// TODO Auto-generated method stub
		location.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) { 
				currentUser.put("location", s.toString());
				currentUser.saveInBackground(new SaveCallback() {

					@Override
					public void done(ParseException e) {
						// TODO Auto-generated method stub
						Log.d("done","changed location is saved");	
					}
				});

			}
		});
	}


	private void genderClick() {
		gender.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setMessage("Change your Gender")
				.setPositiveButton("Female", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// send a request to backend that set gender as female
						gender.setText("Female");
						currentUser.put("sex", "Female");
						currentUser.saveInBackground(new SaveCallback() {

							@Override
							public void done(ParseException e) {
								// TODO Auto-generated method stub
								Log.d("done"," Gender changed to female");	
							}
						});
					}
				})
				.setNegativeButton("Male", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// Set gender to male
						gender.setText("Male");
						currentUser.put("sex", "Male");
						currentUser.saveInBackground(new SaveCallback() {

							@Override
							public void done(ParseException e) {
								// TODO Auto-generated method stub
								Log.d("done","Gender changed to male");	
							}
						});
					}
				})
				.create()
				.show();

			}// end of onClick
		});
	}
}
