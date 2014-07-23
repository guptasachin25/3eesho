package com.codepath.eesho.utils;

import java.util.Random;

import org.joda.time.DateTime;

import com.codepath.eesho.activities.UserMetricsActivity;
import com.codepath.eesho.models.WeeklyFitnessPlan;
import com.codepath.eesho.parse.models.Goal;
import com.codepath.eesho.parse.models.Plan;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Utils {
	
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static void setPlan(ParseUser user) {
		Utils.setFitnessPlan(user);
	}
	
	@SuppressWarnings("deprecation")
	public static void setFitnessPlan(final ParseUser user) {
		// Save plan 
		Plan newPlan = new Plan();
		newPlan.setUser(user);
		newPlan.setPlanType("fitness");
		final WeeklyFitnessPlan plan = WeeklyFitnessPlan.getDefaultPlan2();
		newPlan.setPlanDesc(plan.toJSON());
		newPlan.saveInBackground();

		for(int i = -7; i < 7; i++) {
			final DateTime date = new DateTime().plusDays(i);
			ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);
			query.whereEqualTo("user", user);
			query.whereEqualTo("date", date.toDateMidnight().toDate());
			
			query.getFirstInBackground(new GetCallback<Goal>() {
				@Override
				public void done(Goal goal, ParseException exception) {
					if(exception != null) {
						if(goal == null) {
							goal = new Goal();
						}
						goal.setDate(date.toDateMidnight().toDate());
						goal.setWeekDay(UserMetricsActivity.weekDayMap.get(date.getDayOfWeek()));
						goal.setPlan(plan.getPlan(date));
						goal.setUser(user);
						goal.saveInBackground();
					} else {
						System.out.println(exception);
					}
				}
			});
		}		
	}
}
