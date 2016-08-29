package cogs187a.exquisite;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Variables
    ExpandableListView expandableListView;
    public ImageButton plus;
    public ImageButton settings;

    // Initialize the buttons
    public void init() {
        View view = this.getCurrentFocus();
        plus = (ImageButton)findViewById(R.id.plusButton);
        settings = (ImageButton)findViewById(R.id.settingsButton);

        // Links OnClickListeners to buttons
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, plus);
                popup.getMenuInflater().inflate(R.menu.plus_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if(R.id.one == item.getItemId()) {
                            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                            // Set an EditText view to get user input
                            final EditText input = new EditText(MainActivity.this);
                            input.setHint("List Name");
                            alert.setView(input);

                            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    // Do something with value!
                                }
                            });

                            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // Canceled.
                                }
                            });
                            alert.show();
                        } else {
                            LayoutInflater factory = LayoutInflater.from(MainActivity.this);

                            //text_entry is an Layout XML file containing two text field to display in alert dialog
                            final View textEntryView = factory.inflate(R.layout.text_entry, null);
                            final EditText input1 = (EditText) textEntryView.findViewById(R.id.editText1);
                            final EditText input2 = (EditText) textEntryView.findViewById(R.id.editText2);
                            input1.setHint("New Word");
                            input2.setHint("Definition");

                            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                            alert.setView(textEntryView).setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            Log.i("AlertDialog","TextEntry 1 Entered "+input1.getText().toString());
                                            Log.i("AlertDialog","TextEntry 2 Entered "+input2.getText().toString());
                                            /* User clicked OK so do some stuff */
                                        }
                                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                         /*
                                        * User clicked cancel so do some stuff
                                        */
                                        }
                                    });
                            alert.show();
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(MainActivity.this,SettingsButtonActivity.class);
                startActivity(settingsIntent);
            }
        });

        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Start at activity_main
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization
        init();

        /**
         * Overview:
         * 1. Convert XML formatted lists to real lists
         * 2. Map the names of each list to their corresponding real list
         * 3. Place these names and list values into the expandable list view
         */

        /* 1 ------------------------------------------------------------------------------------ */
        // Create List Object for each Vocab List
        List<String> Names = new ArrayList<String>(); // Names of each list
        List<String> Recently = new ArrayList<String>();   // Words in Recently added
        List<String> Library = new ArrayList<String>();    // Words in the Library List
        List<String> Custom = new ArrayList<String>();     // Custom created words

        // Create a map from the Vocab List name to the corresponding list object
        HashMap<String,List<String>> ChildList = new HashMap<String, List<String>>();

        // Convert XML string array to real string array
        String list_names[] = getResources().getStringArray(R.array.list_names);
        String recently[] = getResources().getStringArray(R.array.recently);
        String library[] = getResources().getStringArray(R.array.library);
        String custom[] = getResources().getStringArray(R.array.custom);

        // Convert each string array into a string list
        for (String name : list_names) Names.add(name);
        for (String name : recently)   Recently.add(name);
        for (String name : library)    Library.add(name);
        for (String name : custom)     Custom.add(name);

        /* 2 ------------------------------------------------------------------------------------ */
        // Map the list name to the corresponding word list
        ChildList.put(Names.get(0),Recently);
        ChildList.put(Names.get(1),Library);
        ChildList.put(Names.get(2),Custom);

        /* 3 ------------------------------------------------------------------------------------ */
        // Retrieve the ExpandableListView
        expandableListView = (ExpandableListView)findViewById(R.id.expandableListView);

        // Create an adapter to place the List names and Corresponding sets of values into the view
        MyAdapter myAdapter = new MyAdapter(this,Names,ChildList);
        expandableListView.setAdapter(myAdapter);

        // Expand Recently Viewed By Default
        expandableListView.expandGroup(0);

    }

}
