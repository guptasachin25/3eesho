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
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.codepath.eesho.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class UserProfileFragment extends DialogFragment {

	private String user_id;
	//private ParseUser currentUser; 
	private TextView tvUserProfileName;
	private EditText phone;
	private TextView email;
	private TextView gender;
	private TextView profession;
	private TextView location;
	private TextView weight;
	private TextView height;
	private TextView dietHabit;
	private TextView target;
	private NumberPicker feet;
	private NumberPicker inch;
	private NumberPicker minPicker;
	private NumberPicker maxPicker;
	private ParseUser currentUser;

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
		currentUser = ParseUser.getCurrentUser();
		Log.d("inprofileactivity", "user id in profile after activity pass it " + user_id + " first");
	}

@Override
public View onCreateView(LayoutInflater inflater,
		 ViewGroup container,  Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.fragment_user_profile, container,false);
	// get the current user object
	
	// Email on click becomes editable otherwise it is non editable
	tvUserProfileName = (TextView)v. findViewById(R.id.etuserProfilename);
	email = (TextView)v. findViewById(R.id.etuserProfileEmail);
	phone = (EditText) v.findViewById(R.id.etuserProfilePhone);
	gender = (TextView) v.findViewById(R.id.tvusergender);
	profession = (TextView) v.findViewById(R.id.etuserProfileProfession);
	location = (TextView) v.findViewById(R.id.etuserProfileLocation);
	weight = (TextView) v.findViewById(R.id.tvuserProfileWeight);
	height = (TextView) v.findViewById(R.id.tvuserProfileHeight);
	dietHabit = (TextView) v.findViewById(R.id.tvuserProfileDietHabit);
	target = (TextView) v.findViewById(R.id.tvuserProfileTarget);
	usernameClick();
	professionClick();
	locationClick();
	genderClick();
	phoneClick();
	targetClick();
	weightClick();
	heightClick();
	dietHabitClick();
	setUpUserData(currentUser);
	return v;
}

private void setUpUserData(ParseUser user) {
	if(user.getString("name") != null){
		tvUserProfileName.setText(user.getString("name"));
	} if( user.getString("email") != null){
		email.setText(user.getString("email"));
	} if( user.getNumber("phone") != null){
		phone.setText(user.getNumber("phone").toString());
	} if(user.getString("sex") != null){
		gender.setText(user.getString("sex"));
	} if(user.getString("profession") != null){
		profession.setText(user.getString("profession"));
	} if( user.getString("location") != null){
		location.setText(user.getString("location"));
	} if( user.getNumber("weight") != null){
		weight.setText(user.getNumber("weight").toString());
	} if(user.getNumber("height_feet") != null && (user.getNumber("height_inches") != null)){
		height.setText(user.getNumber("height_feet").toString()+"\'"+user.getNumber("height_inches").toString()+"\'\'");
	} 
if(user.getString("diet_habit") != null){
		dietHabit.setText(user.getString("diet_habit"));
	} if(user.getString("targetDescription") != null){
		target.setText(user.getString("targetDescription"));
	}else{
		Log.d("Getting the value for parse","value from parse is null");
	}
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
							Log.d("done","changed phone number is saved");	
						}
					});
				}
			}
	});
	
}

private void phoneClick() {
	phone.addTextChangedListener(new TextWatcher() {
		
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
				currentUser.put("phone", Long.parseLong(s.toString()));
					currentUser.saveInBackground(new SaveCallback() {
				
						@Override
						public void done(ParseException e) {
						// TODO Auto-generated method stub
							Log.d("done","changed phone number is saved");	
						}
					});
				}
			}
	});
	
}

private void dietHabitClick() {
	dietHabit.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage("What is your dietary Habit?")
	               .setPositiveButton("Vegitarian", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // send a request to backend that set gender as female
	                	   dietHabit.setText("Vegetarian");
	                	   currentUser.put("diet_habit", "Vegetarian");
           				   currentUser.saveInBackground(new SaveCallback() {
								
								@Override
								public void done(ParseException e) {
									// TODO Auto-generated method stub
									Log.d("done","veg changed");
								}
							});
	                   }
	               })
	               .setNegativeButton("Non-Vegitarian", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // Set gender to male
	                	   dietHabit.setText("Non-Vegetarian");
	                	   currentUser.put("diet_habit", "Non-Vegtarian");
	                	   currentUser.saveInBackground(new SaveCallback() {
							
							@Override
							public void done(ParseException e) {
								// TODO Auto-generated method stub
								Log.d("done","non-veg changed");	
							}
						});
	                   }
	               })
	               .create()
	               .show();
			
		}// end of onClick
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
				                height.setText(feetvalue+"\'"+inchvalue+"\'\'");
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
private void targetClick() {
	target.addTextChangedListener(new TextWatcher() {
		
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
			currentUser.put("targetDescription", s.toString());
     	   currentUser.saveInBackground(new SaveCallback() {
				
				@Override
				public void done(ParseException e) {
					// TODO Auto-generated method stub
					Log.d("done","target is saved");	
				}
			});
			
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

private void professionClick() {
profession.addTextChangedListener(new TextWatcher() {
		
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
			currentUser.put("profession", s.toString());
     	   currentUser.saveInBackground(new SaveCallback() {
				
				@Override
				public void done(ParseException e) {
					// TODO Auto-generated method stub
					Log.d("done","changed profession is saved ");	
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
