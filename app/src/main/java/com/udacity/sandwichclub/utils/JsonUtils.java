package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;




public class JsonUtils {

    private static String SC_NAME = "name";
    private static String SC_AKA = "alsoKnownAs";
    private static String SC_ORIGIN = "placeOfOrigin";
    private static String SC_DESCR = "description";
    private static String SC_INGREDIENTS = "ingredients";
    private static String SC_IMAGE = "image";
    private static String SC_MAIN = "mainName";



    public static Sandwich parseSandwichJson(String json) {



        String mainName = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        String name = null;
        List<String> ingredients = null;
        List<String> alsoKnownAs = null;


        try {
            JSONObject jsonObject = new JSONObject(json);
            mainName = jsonObject.getJSONObject(SC_NAME).getString(SC_MAIN);
            image = jsonObject.getString(SC_IMAGE);
            description = jsonObject.getString(SC_DESCR);
            ingredients = (List<String>) jsonObject.getJSONObject(SC_INGREDIENTS);
            alsoKnownAs = (List<String>) jsonObject.getJSONObject(SC_NAME).getJSONArray(SC_AKA);
            placeOfOrigin =jsonObject.getString(SC_ORIGIN);



        } catch (JSONException e) {
            Log.e("ERRRRR", String.valueOf(e));
            e.printStackTrace();
        }


        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }



    }
