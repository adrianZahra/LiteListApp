package com.example.pccc.utillityapp;


import android.widget.ArrayAdapter;


import java.io.Serializable;
import java.util.ArrayList;


//This class holds data for use in the App along with a constructor
class ItemsList implements Serializable {
     static ArrayAdapter<String> adapter;
     static ArrayList<String> myItems;

    ItemsList() {
        this.myItems = new ArrayList<String>();
    }
}