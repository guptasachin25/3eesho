package com.codepath.eesho.fragments;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.codepath.eesho.models.Goal;
import com.codepath.eesho.models.User;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class UserProfileFragment extends DialogFragment {
	
	private String user_id;
	private TextView tvUserProfileName;
	private EditText phone;
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
	
	// The fragment for geting user input field names
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
			
			
		}

@Override
public View onCreateView(LayoutInflater inflater,
		@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.fragment_user_profile, container,false);
	// Email on click becomes editable otherwise it is non editable
	tvUserProfileName = (TextView)v. findViewById(R.id.etuserProfilename);
	phone = (EditText) v.findViewById(R.id.etuserProfilePhone);
	gender = (TextView) v.findViewById(R.id.tvusergender);
	profession = (TextView) v.findViewById(R.id.etuserProfileProfession);
	location = (TextView) v.findViewById(R.id.etuserProfileLocation);
	weight = (TextView) v.findViewById(R.id.tvuserProfileWeight);
	height = (TextView) v.findViewById(R.id.tvuserProfileHeight);
	dietHabit = (TextView) v.findViewById(R.id.tvuserProfileDietHabit);
	target = (TextView) v.findViewById(R.id.tvuserProfileTarget);
	professionClick();
	locationClick();
	genderClick();
	phoneClick();
	targetClick();
	weightClick();
	heightClick();
	dietHabitClick();
	ParseQuery<User> query = ParseQuery.getQuery(User.class);
	// Define our query conditions
	query.getInBackground(user_id, new GetCallback<User>() {
		
		@Override
		public void done(User user, ParseException e) {
			// TODO Auto-generated method stub
			if(e == null){
				setUpUser(user);
				//Log.d("Fragment", "Retrieved " + user.getUsernname() + " Data");
			}else {
				
			}
		}

		
	});

	return v;
}
private void setUpUser(User user) {
	// TODO Auto-generated method stub
	tvUserProfileName.setText(user.getUsernname());
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
	                   }
	               })
	               .setNegativeButton("Non-Vegitarian", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // Set gender to male
	                	   dietHabit.setText("Non-Vegetarian");
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
			// get the phone number typed in the edittext and send it to the backend to be saved. 
			
			
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
			// get the phone number typed in the edittext and send it to the backend to be saved. 
			//phone.setText(s);
			
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
			// get the phone number typed in the edittext and send it to the backend to be saved. 
			//phone.setText(s);
			
		}
	});
	
}
private void phoneClick() {
	phone.addTextChangedListener(new TextWatcher() {
		
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
			// get the phone number typed in the edittext and send it to the backend to be saved. 
			//phone.setText(s);
			
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
	                   }
	               })
	               .setNegativeButton("Male", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // Set gender to male
	                	   gender.setText("Male");
	                   }
	               })
	               .create()
	               .show();
			
		}// end of onClick
	});
	
	
}
}
