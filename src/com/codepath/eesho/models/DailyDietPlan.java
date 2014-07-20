package com.codepath.eesho.models;

public class DailyDietPlan {
	public static DailyActivity<DietPlanSingleActivity> getDefaultPlan() {
		DailyActivity<DietPlanSingleActivity> dietPlan = new DailyActivity<DietPlanSingleActivity>();
		// Breakfast
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1 poached egg", "Breakfast", "8:00 AM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("A whole wheat English muffin", "Breakfast", "8:00 AM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1 cup sliced fennel sauteed", "Breakfast", "8:00 AM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1 tablespoon sliced basil", "Breakfast", "1:00 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Fresh peppermint tea ", "Breakfast", "1:00 PM"));
		
		// Morning snacks
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1 cup Greek style Yogurt", "Morning Snacks", "11:00 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("2 tablespoon roasted unsalted sunflower seeds", "Morning Snacks", "11:00 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1 tablespoon honey", "Morning Snacks", "11:00 PM"));
		
		// Lunch
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1 cup cooked Israeli couscous", "Lunch", "2:00 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Tossed with 2 pitted and chopped dried plums", 
				"Lunch", "2:00 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1/4 cup diced red bell pepper", "Lunch", "2:00 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1 tablespoon minced red onion", "Lunch", "2:00 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1 tablespoon homemade vinaigrette", "Lunch", "2:00 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1 tablespoon toasted pine nuts", "Lunch", "2:00 PM"));
		
		// Evening Snacks
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("An apple", "Evening Snacks", "4:30 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1 large wedge of cantaloupe melon", 
				"Evening Snacks", "4:30 PM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("2 slices lower-sodium ham", 
				"Evening Snacks", "4:30 PM"));
		
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1 ounce unsalted chopped pistachios", 
				"Evening Snacks", "4:30 PM"));
		
		// Dinner
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1 reduced-fat whole wheat tortilla", "Dinner", "7:00 AM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("3 ounces grilled shrimp", "Dinner", "7:00 AM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("2 tablespoons mashed avocado", "Dinner", "7:00 AM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1/4 cup fresh bell pepper salsa ", "Dinner", "7:00 AM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("1 cup baby arugula", "Dinner", "7:00 AM"));
		dietPlan.addActivity(DietPlanSingleActivity.getPlan("Fresh peppermint tea", "Dinner", "7:00 AM"));
		return dietPlan;
	}
}
