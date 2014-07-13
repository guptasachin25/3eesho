package com.codepath.eesho.models;

public class DailyDietPlan {
	public static DailyActivity<DietPlanSingleActivity> getDefaultPlan() {
		DailyActivity<DietPlanSingleActivity> dietPlan = new DailyActivity<DietPlanSingleActivity>();
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Drink Water", "7:00 AM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Drink Water", "9:00 AM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Drink Water", "11:00 AM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Drink Water", "1:00 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Drink Water", "3:00 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Drink Water", "5:00 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Drink Water", "7:00 PM"));
		
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Eat an Apple", "8:00 AM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Eat 2 Bread Slices", "8:00 AM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Drink 1 Glass Milk", "8:00 AM"));
		
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Eat Salad", "12:00 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Eat Chicken", "12:00 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Eat Curd", "12:00 PM"));
		
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Eat Fruits", "2:30 PM"));
		
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Tea/Coffee (Optional)", "4:00 PM"));
		
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Bread slices/Rice", "7:00 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Salad", "7:00 PM"));
		return dietPlan;
	}
}
