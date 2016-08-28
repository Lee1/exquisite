package cogs187a.exquisite;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.VectorEnabledTintResources;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView)findViewById(R.id.expandableListView);
        List<String> Names = new ArrayList<String>();
        List<String> Recently = new ArrayList<String>();
        List<String> Library = new ArrayList<String>();
        List<String> Custom = new ArrayList<String>();
        HashMap<String,List<String>> ChildList = new HashMap<String, List<String>>();
        String list_names[] = getResources().getStringArray(R.array.list_names);
        String recently[] = getResources().getStringArray(R.array.recently);
        String library[] = getResources().getStringArray(R.array.library);
        String custom[] = getResources().getStringArray(R.array.custom);

        for (String name : list_names)
        {
            Names.add(name);
        }
        for (String name : recently)
        {
            Recently.add(name);
        }
        for (String name : library)
        {
            Library.add(name);
        }
        for (String name : custom)
        {
            Custom.add(name);
        }

        ChildList.put(Names.get(0),Recently);
        ChildList.put(Names.get(1),Library);
        ChildList.put(Names.get(2),Custom);

        MyAdapter myAdapter = new MyAdapter(this,Names,ChildList);
        expandableListView.setAdapter(myAdapter);

    }

}
