package com.keenanzucker.photostream;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Context;

import com.google.api.services.customsearch.model.Search;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    EditText editText;
    Button searchButton;
    Button nextButton;
    Button saveButton;
    int nextImage = 0;
    ArrayList<String> imageURLs = new ArrayList<>(); //will turn into database

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState)
    {
        editText = (EditText)view.findViewById(R.id.editText);
        searchButton = (Button)view.findViewById(R.id.button);
        nextButton = (Button)view.findViewById(R.id.button2);
        saveButton = (Button)view.findViewById(R.id.button4);
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String searchText = editText.getText().toString();
                HttpHandler handler = new HttpHandler(getActivity());
                handler.searchForImages(searchText, new ImageCallback() {
                    @Override
                    public void callback(ArrayList<String> imageSRC) {

                        String url1 = imageSRC.get(0);
                        Picasso.with(getActivity())
                                .load(url1)
                                .into(imageView);
                    }
                });

            }
        });


        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                nextImage += 1;
                String searchText = editText.getText().toString();
                HttpHandler handler = new HttpHandler(getActivity());
                handler.searchForImages(searchText, new ImageCallback() {
                    @Override
                    public void callback(ArrayList<String> imageSRC) {

                        String url1 = imageSRC.get(nextImage);
                        Picasso.with(getActivity())
                                .load(url1)
                                .into(imageView);
                    }
                });
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String searchText = editText.getText().toString();
                HttpHandler handler = new HttpHandler(getActivity());
                handler.searchForImages(searchText, new ImageCallback() {
                    @Override
                    public void callback(ArrayList<String> imageSRC) {
                        String url1 = imageSRC.get(nextImage);
                        imageURLs.add(url1);
                    }
                });
            }
        });


    }

}
