package com.keenanzucker.photostream;

import android.provider.BaseColumns;

/**
 * Created by keenan on 10/2/15.
 */
public class ImageContract {

    public ImageContract(){}

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "IMAGES_URL";
        public static final String IMAGE_ITEM_COLUMN = "IMAGE";
    }


}
