package com.keenanzucker.photostream;

import java.util.ArrayList;

/**
 * Created by keenan on 9/17/15.
 * Interface for image callback. Returns a arraylist of image urls
 */
public interface ImageCallback {

    void callback(ArrayList<String> imageSRCs);

}
