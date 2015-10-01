package com.keenanzucker.photostream;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.widget.ImageView.ScaleType.CENTER_CROP;


public class StreamFragment extends Fragment {

    ArrayList<String> testURLs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_stream, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState)
    {
        testURLs.add("https://www.petfinder.com/wp-content/uploads/2012/11/dog-how-to-select-your-new-best-friend-thinkstock99062463.jpg");
        testURLs.add("http://www.safeoffleashdogplay.com/wp-content/uploads/2013/05/Puppy-3-dogs.jpg");
        testURLs.add("http://bebusinessed.com/wp-content/uploads/2014/03/734899052_13956580111.jpg");

        GridView gv = (GridView) view.findViewById(R.id.gridview);
        gv.setAdapter(new GridViewAdapter(getActivity()));
    }

    final class GridViewAdapter extends BaseAdapter {

        private final Context context;
        private final ArrayList<String> urls = new ArrayList<>();

        public GridViewAdapter(Context context) {
            this.context = context;

            urls.addAll(addURLs());
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            SquareImageView view = (SquareImageView) convertView;
            if (view == null) {
                view = new SquareImageView(context);
                view.setScaleType(CENTER_CROP);
            }

            // Get the image URL for the current position.
            String url = getItem(position);

            // Trigger the download of the URL asynchronously into the image view.
            Picasso.with(context) //
                    .load(url) //
                    .fit() //
                    .centerCrop()
                    .into(view);

            return view;
        }

        @Override public int getCount() {
            return urls.size();
        }

        @Override public String getItem(int position) {
            return urls.get(position);
        }

        @Override public long getItemId(int position) {
            return position;
        }

        public ArrayList<String> addURLs (){
            return testURLs;
        }
    }

}
