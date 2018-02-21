package com.mymood.mymood;

import android.app.Application;

import java.util.HashMap;

/**
 * Created by Joan on 21/2/2018.
 */

public class UsersMap extends Application {

    private HashMap<String,String> hmap = new HashMap<String, String>();


    public HashMap getMap(){

        return hmap;
    }

    public boolean putUser(String user, String password){
        if (hmap.containsKey(user)) {
            return false;
        }
        else{
            hmap.put(user,password);
            return true;
        }
    }
}
