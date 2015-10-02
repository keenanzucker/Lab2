package com.keenanzucker.photostream;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchFragment.onLibraryListener, StreamFragment.onBackButtonListener {

    public ArrayList<String> imageURLs = new ArrayList<>();
    Button libraryButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);   // Modify so changing between fragment views!
        //setContentView(R.layout.fragment_stream);

        Fragment searchFrag = new SearchFragment();
        Fragment streamFrag = new StreamFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container, searchFrag);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLibrary(){
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new StreamFragment())
                .commit();
    }

    @Override
    public void onBackButton(){
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new SearchFragment())
                .commit();
    }
}
