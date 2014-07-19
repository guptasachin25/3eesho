package com.codepath.eesho.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This interface represents Single activity. It can either be single fitness activity or 
 * a single diet activity. 
 *
 */
public interface SingleActivity {
	
	/**
	 * @return String representation of this activity
	 */
	public String toString();
	
	/**
	 * This function allow us to store data in Parse as JSON Object.
	 * 
	 * @return JSON object representation of this activity. 
	 * @throws JSONException 
	 */
	public JSONObject toJSONObject() throws JSONException;

	public Long getDisplayNumber();
	/**
	 * @return whether activity is finished by user on a given day.
	 */
	public boolean isDone();

	public void resetDone();
	
	public Long getId();
	
}
