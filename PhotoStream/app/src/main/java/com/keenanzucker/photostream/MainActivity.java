package com.keenanzucker.photostream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Button;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    //ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> tracks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);

        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
        //listView = (ListView)findViewById(R.id.listView);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);



        button.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v) {
               String searchText = editText.getText().toString();
               HttpHandler handler = new HttpHandler(MainActivity.this);
               handler.searchForImages(searchText, new ImageCallback() {
                   @Override
                   public void callback(ArrayList<String> imageSRC) {

                       String url1 = imageSRC.get(0);
                       Picasso.with(MainActivity.this)
                               .load(url1)
                               .into(imageView);

//                       if () {
//                           //Log.d("Success", Boolean.toString(success));
//                           // continue
//                       } else {
//                           //Log.d("Failure", Boolean.toString(success));
//                           // handle failure
//                       }
                   }
               });

               //adapter.notifyDataSetChanged();
           }
        });
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
}
