package com.example.pccc.utillityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Collections;

public class SettingsActivity extends AppCompatActivity {
    public ItemsList _itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        _itemsList = (ItemsList) getIntent().getExtras().getSerializable("_itemsList");
    }

    //This button method is called when the home button is pressed. It sends the user back to the home activity
    public void getHome(View view) {
        Intent homeViewIntent = new Intent(this, ListMakerHome.class);
        homeViewIntent.putExtra("_itemsList", _itemsList);
        startActivity(homeViewIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //This button method is called when the alphabetised button is pressed. It organizes the users list in alphabetical order.
    public void sortList(View view) {
        if (_itemsList.myItems.size() == 0) {
            Toast.makeText(SettingsActivity.this, "There was no items in your list to alphabetize", Toast.LENGTH_SHORT).show();
        } else {
            Collections.sort(_itemsList.myItems, String.CASE_INSENSITIVE_ORDER);
            _itemsList.adapter.notifyDataSetChanged();
            Toast.makeText(SettingsActivity.this, "Your list has been alphabetized", Toast.LENGTH_SHORT).show();
        }
    }

    //This button method allows the user to clear the list of all items
    public void clearList(View view) {
        _itemsList.myItems.clear();
        _itemsList.adapter.notifyDataSetChanged();
        Toast.makeText(SettingsActivity.this, "All items have been cleared from your list", Toast.LENGTH_SHORT).show();
    }
}
