package com.example.pccc.utillityapp;

import android.content.ClipData;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    /*
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    */

    @Test
    public void array_has_items() throws Exception {
        ItemsList _itemsList = new ItemsList();

        _itemsList.myItems.add("eggs");
        assertTrue(_itemsList.myItems.get(0).equals("eggs"));

        _itemsList.myItems.add("bread");
        assertTrue(_itemsList.myItems.get(1).equals("bread"));

        _itemsList.myItems.clear();
        assertTrue(_itemsList.myItems.isEmpty());
    }
}