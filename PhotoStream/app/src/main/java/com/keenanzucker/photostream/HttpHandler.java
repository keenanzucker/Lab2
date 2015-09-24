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
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by keenan on 9/17/15.
 */
public class HttpHandler {

    public RequestQueue queue;

    public HttpHandler(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void searchSpotify(String searchQuery ){ //add in the callback class?

        String query = searchQuery.replaceAll(" ", "+");

        String url = "https://api.spotify.com/v1/search";
        url =  url + "?q=" + query;
        url = url + "&type=track";

        //Use an image request for actual  --> MAKE THIS WORK?!
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<String> trackName = new ArrayList<>();
                        //Log.d("Success", "Yay!"); //use Log.d for normal stuff
                        try {
                            JSONObject tracks = response.getJSONObject("tracks");
                            JSONArray items = tracks.getJSONArray("items");
                            for (int i = 0; i<items.length(); i++)
                            {
                                JSONObject song = items.getJSONObject(i);
                                String title = song.getString("name");
                                Log.d("title", title);
                                trackName.add(title);
                            }
                            //something with callback.callback
                        }
                            catch (Exception e){
                                Log.e("ERROR", e.getMessage());
                        }
                    }
                }
        );

        new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.e("ERROR!", error.getMessage());   //use Log.e for error messages, use thing.toString() or Boolean.toString()...
            }
        };

        queue.add(request);


    }
}
