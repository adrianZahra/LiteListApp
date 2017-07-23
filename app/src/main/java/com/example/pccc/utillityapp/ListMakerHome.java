package com.example.pccc.utillityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListMakerHome extends AppCompatActivity {
    ItemsList _itemsList;
    private EditText itemInput;
    private TextView listSizeNotice;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_maker_home);
        getSavedInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        itemInput = (EditText) findViewById(R.id.itemInput);
        listSizeNotice = (TextView) findViewById(R.id.listSizeNotice);
        listSizeNotice.setText("You have " + _itemsList.myItems.size() + " item(s) in your list");
        populateListView();
        removeItem();
    }

    //This method is called when an item is clicked. it removes the selected item from the list
    public void removeItem() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListMakerHome.this, _itemsList.adapter.getItem(position).toString() + " has been removed from your list", Toast.LENGTH_SHORT).show();
                _itemsList.myItems.remove(position);
                _itemsList.adapter.notifyDataSetChanged();
                listSizeNotice.setText("You have " + _itemsList.myItems.size() + " item(s) in your list");
            }
        });
    }

    //This button method is called when the Add item button is clicked. It takes the input and adds it to the list
    //it does not allow you to add blank space or the same item twice
    public void getItemInput(View view) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (itemInput.getText().toString().trim().isEmpty()) {
                    Toast.makeText(ListMakerHome.this, "You must give your item a name!", Toast.LENGTH_SHORT).show();
                } else if (_itemsList.myItems.contains(itemInput.getText().toString().trim())) {
                    Toast.makeText(ListMakerHome.this, "You have already added " + itemInput.getText().toString() + " to your list!", Toast.LENGTH_SHORT).show();
                    itemInput.setText("");
                } else {
                    _itemsList.myItems.add(itemInput.getText().toString().trim());
                    _itemsList.adapter.notifyDataSetChanged();
                    Toast.makeText(ListMakerHome.this, itemInput.getText().toString().trim() + " has been added to your list", Toast.LENGTH_SHORT).show();
                    listSizeNotice.setText("You have " + _itemsList.myItems.size() + " item(s) in your list");
                    itemInput.setText("");
                }

            }
        });
    }

    //This method is used to create the array adapter from the arraylist of items
    public void populateListView() {
        _itemsList.adapter = new ArrayAdapter<String>(this, R.layout.shopping_items, _itemsList.myItems);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(_itemsList.adapter);
    }

    //This method determines whether to use the saved instance intent data or create new data if there is none
    public void getSavedInstance() {
        try {
            if (_itemsList.myItems == null) {
                _itemsList = new ItemsList();
            } else {
                try {
                    _itemsList = (ItemsList) getIntent().getExtras().getSerializable("_itemsList");
                } catch (Exception ignored) {
                }
            }
        } catch (Exception e) {
            _itemsList = new ItemsList();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        listSizeNotice.setText("You have " + _itemsList.myItems.size() + " item(s) in your list");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_maker_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingViewIntent = new Intent(this, SettingsActivity.class);
            settingViewIntent.putExtra("_itemsList", _itemsList);
            startActivity(settingViewIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        _itemsList.adapter.notifyDataSetChanged();
    }

}
