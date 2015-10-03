package com.keenanzucker.photostream;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import static android.widget.ImageView.ScaleType.CENTER_CROP;

/**
 *  Fragment for the displaying a gridview of images. Accesses the SQL database to load images.
 *  Uses a longclick listener to delete images.
 *  Uses a custom SquareImageView to crop images to nice square to fit into gridview.
 *  Has back to search button that returns to the SearchFragment.
 *  Uses Picasso library to load images
 */

public class StreamFragment extends Fragment {


    ArrayList<String> imageURLs = new ArrayList<>();
    onBackButtonListener mBackButtonListener;
    DbHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_stream, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        dbHelper = new DbHelper(getActivity());
    }

    @Override
    public void onAttach(Context activity){
        super.onAttach(activity);
        mBackButtonListener = (onBackButtonListener) activity;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState)
    {
        imageURLs = dbHelper.getSavedImages();

        Button backToSearchButton;
        GridView gv = (GridView) view.findViewById(R.id.gridview);
        gv.setAdapter(new GridViewAdapter(getActivity()));

        backToSearchButton = (Button)view.findViewById(R.id.button3);

        gv.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        GridView gv = (GridView) view.findViewById(R.id.gridview);
                        GridViewAdapter gva = (GridViewAdapter) gv.getAdapter();
                        gva.removeURLs(pos);
                        // Return true consumes the long click event (marks it handled)
                        return true;
                    }
                }
        );

        backToSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackButtonListener.onBackButton();
            }
        });
    }

    public interface onBackButtonListener {
        public void onBackButton();
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

            String url = getItem(position);

            //Use Picasso to load square images into gridview
            Picasso.with(context)
                    .load(url)
                    .fit()
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
            return imageURLs;
        }

        public void removeURLs (int pos){
                String url = urls.get(pos);
                dbHelper.deleteImage(url);  //delete from local and from database
                urls.remove(pos);
                notifyDataSetChanged(); // update/refresh the view to show deleted items
        }
    }
}
