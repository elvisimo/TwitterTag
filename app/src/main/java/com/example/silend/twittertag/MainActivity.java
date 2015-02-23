package com.example.silend.twittertag;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class MainActivity extends ListActivity {

    private static final String SEARCHES = "Searches";

    private EditText queryEditText;
    private EditText tagEditText;
    private SharedPreferences savedSearches;
    private ArrayList<String> tags;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queryEditText = (EditText)findViewById(R.id.queryEditText);
        tagEditText = (EditText)findViewById(R.id.tagEditText);

        savedSearches = getSharedPreferences(SEARCHES, MODE_PRIVATE);
        tags = new ArrayList<String>(savedSearches.getAll().keySet());
        Collections.sort(tags, String.CASE_INSENSITIVE_ORDER);

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, tags);
        setListAdapter(adapter);

        ImageButton saveButton = (ImageButton)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(saveButtonListener);

        getListView().setOnItemClickListener(itemClickListener);
        getListView().setOnItemLongClickListener(itemLongClickListener);
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
