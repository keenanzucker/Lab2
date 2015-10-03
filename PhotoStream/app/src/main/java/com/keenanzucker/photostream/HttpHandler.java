package com.keenanzucker.photostream;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


/**
 * Created by keenan on 9/17/15.
 * Connects to the Google Custom Search. Takes in the search entry and queries a google image search.
 * Uses the callback interface to return the arraylist of image urls.
 * Gets a JSON object from the Google search and parses through the json to add the image url/link to the arraylist
 */
public class HttpHandler {

    public RequestQueue queue;

    public HttpHandler(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void searchForImages(String searchQuery, final ImageCallback callback) {

        String query = searchQuery.replaceAll(" ", "+");

        String url = "https://www.googleapis.com/customsearch/v1?key=AIzaSyDNzr6WCi5Dr6wN0K1_XMq7y1rvh12tIXw&cx=017978864744009989256:mqpurlr0zw4";
        url = url + "&q=" + query;
        url = url + "&searchType=image";
        
        JSONObject body = new JSONObject();
        try {
            body.put("random", "thing");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<String> imageSRC = new ArrayList<>();

                        try {
                            JSONArray Results = (JSONArray) response.get("items");

                            for (int i = 0; i < Results.length(); i++)
                            {
                                JSONObject image;
                                image = (JSONObject) Results.get(i);
                                imageSRC.add((String) image.get("link"));
                            }
                            callback.callback(imageSRC);        //Use callback interface to return arraylist of URLs.
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.getMessage());
                    }
                }
        );

        queue.add(request);
    }
}