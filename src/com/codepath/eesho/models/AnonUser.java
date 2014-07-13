package com.codepath.eesho.models;

import java.io.Serializable;

public class AnonUser implements Serializable {
	private static final long serialVersionUID = -3779487540497329458L;

	String goal = null;
	
	long weightTarget = 0L;
	
	String weightTargetUnit = null;
	
	long currentWeight = 0L;
	
	String currentWeightUnit =  null;
	
	long height = 0L;
	
	String heightUnit = null;
	
	long timeCommitment = 0L;
	
	String timeCommentmentUnit = null;
	
	long goalCommitment = 0L;
	
	String goalCommitmentTimeUnit = null;
	
	String activity = null;
	
	String foodHabits = null;

	public String getHeightUnit() {
		return heightUnit;
	}

	public void setHeightUnit(String heightUnit) {
		this.heightUnit = heightUnit;
	}
	
	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getFoodHabits() {
		return foodHabits;
	}

	public void setFoodHabits(String foodHabits) {
		this.foodHabits = foodHabits;
	}

	
	public long getGoalCommitment() {
		return goalCommitment;
	}

	public void setGoalCommitment(long goalCommitment) {
		this.goalCommitment = goalCommitment;
	}

	public String getGoalCommitmentTimeUnit() {
		return goalCommitmentTimeUnit;
	}

	public void setGoalCommitmentTimeUnit(String goalCommitmentTimeUnit) {
		this.goalCommitmentTimeUnit = goalCommitmentTimeUnit;
	}
	
	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public long getWeightTarget() {
		return weightTarget;
	}

	public void setWeightTarget(long weightTarget) {
		this.weightTarget = weightTarget;
	}

	public String getWeightTargetUnit() {
		return weightTargetUnit;
	}

	public void setWeightTargetUnit(String weightTargetUnit) {
		this.weightTargetUnit = weightTargetUnit;
	}

	public long getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(long currentWeight) {
		this.currentWeight = currentWeight;
	}

	public String getCurrentWeightUnit() {
		return currentWeightUnit;
	}

	public void setCurrentWeightUnit(String currentWeightUnit) {
		this.currentWeightUnit = currentWeightUnit;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public long getTimeCommitment() {
		return timeCommitment;
	}

	public void setTimeCommitment(long timeCommitment) {
		this.timeCommitment = timeCommitment;
	}

	public String getTimeCommentmentUnit() {
		return timeCommentmentUnit;
	}

	public void setTimeCommentmentUnit(String timeCommentmentUnit) {
		this.timeCommentmentUnit = timeCommentmentUnit;
	}
	 
}
