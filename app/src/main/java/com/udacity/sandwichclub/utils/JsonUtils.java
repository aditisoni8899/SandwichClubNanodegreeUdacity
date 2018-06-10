package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich =null;

        // Initializing keys
        final String KEY_NAME = "name",
                KEY_MAIN_NAME = "mainName",
                KEY_ALSO_KNOWN_AS = "alsoKnownAs",
                KEY_PLACE_OF_ORIGIN = "placeOfOrigin",
                KEY_DESCRIPTION = "description",
                KEY_IMAGE = "image",
                KEY_INGREDIENTS = "ingredients";

        try {
            //parsing base json string for one food item
            JSONObject baseJson = new JSONObject(json);

            //extracting first json object
            JSONObject nameJsonObject = baseJson.getJSONObject(KEY_NAME);

            // extracting string associated with key "mainName" in "name"object
            String mainName = nameJsonObject.optString(KEY_MAIN_NAME);

            JSONArray alsoKnowAsJsonArray = nameJsonObject.getJSONArray(KEY_ALSO_KNOWN_AS);
            List<String> alsoKnownAsList = jsonArrayValuesToStringList(alsoKnowAsJsonArray);

            String placeOfOrigin = baseJson.optString(KEY_PLACE_OF_ORIGIN);
            String description = baseJson.optString(KEY_DESCRIPTION);
            String imageURL = baseJson.optString(KEY_IMAGE);

            JSONArray ingredientsJsonArray = baseJson.getJSONArray(KEY_INGREDIENTS);
            List ingredientsArray = jsonArrayValuesToStringList(ingredientsJsonArray);


            sandwich = new Sandwich(mainName,alsoKnownAsList,placeOfOrigin,description,imageURL,ingredientsArray);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return sandwich;
    }

    private static List<String> jsonArrayValuesToStringList(JSONArray jsonArray) {
        List<String> myList = new ArrayList<String>();

        // Adding JSONArrayValues into myList.
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                myList.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return myList;
    }

}
