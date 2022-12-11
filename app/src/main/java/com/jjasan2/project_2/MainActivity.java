package com.jjasan2.project_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView r_view;
    RVAdapter adapter;
    private boolean isList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r_view = findViewById(R.id.r_view);
        adapter = new RVAdapter(this);
        r_view.setHasFixedSize(true);
        r_view.setAdapter(adapter);

        // The default view is Grid view
        r_view.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.grid_option:
                if(!isList) {
                    Toast.makeText(getApplicationContext(), "Already in Grid view",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    isList = false;
                    adapter.setListView(false);
                    r_view.setLayoutManager(new GridLayoutManager(this,2));
                    adapter.notifyDataSetChanged();
                }
                return true;
            case R.id.list_option:
                if(isList) {
                    Toast.makeText(getApplicationContext(), "Already in List view",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    isList = true;
                    adapter.setListView(true);
                    r_view.setLayoutManager(new LinearLayoutManager(this));
                    adapter.notifyDataSetChanged();
                }
                return true;
            default:
                return false;
        }
    }
}