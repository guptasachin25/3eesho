package com.codepath.eesho;

import android.app.Application;

import com.codepath.eesho.models.Article;
import com.codepath.eesho.models.Goal;
import com.codepath.eesho.models.User;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseTwitterUtils;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Goal.class);
        ParseObject.registerSubclass(Article.class);
        ParseObject.registerSubclass(User.class);

        
        Parse.initialize(this, "Iyx6g7dZxvqi1o3ECmMxTLHhqys3OAhOS5YB2DV1", "JEm4N1zcYnvIgUcSLNGJj2z1S5I30MkCBPfDnti9");
        ParseFacebookUtils.initialize("629605243814245");
        ParseTwitterUtils.initialize("OMRjiobCKSwX9zEZIx3cgz2lM", "FzvomIfq6cMjcYImZoFVbtkTLwjyhGyMK0jpiMxFtfJztHAgUI");
        
//
//        ParseObject testObject = new ParseObject("TestObject");
//      	testObject.put("foo", "bar");
//      	testObject.saveInBackground();
  
  }		
}
