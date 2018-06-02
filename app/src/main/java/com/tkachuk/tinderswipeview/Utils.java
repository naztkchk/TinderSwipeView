package com.tkachuk.tinderswipeview;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    public static List <Profile> loadProfiles (Context context){

        GsonBuilder builder = new GsonBuilder();
        Gson gson= builder.create();

        try {
        JSONArray array =     array = new JSONArray(loadJSONFromAssets(context, "profiles.json"));
        List<Profile> profileList = new ArrayList<>();

            for(int i=0 ; i<array.length(); i++){
            Profile profile = gson.fromJson(array.getString(i), Profile.class);
            profileList.add(profile);
        }
        return profileList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String loadJSONFromAssets(Context context, String jsonFileName) {

        String json = null;
        InputStream is = null;
        try {
            AssetManager assetManager = context.getAssets();
            Log.d(TAG, "path "+jsonFileName);

            is = assetManager.open(jsonFileName);
            int size = is.available();
            byte [] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }
}
