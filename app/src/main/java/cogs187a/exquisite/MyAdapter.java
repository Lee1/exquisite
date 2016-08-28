package cogs187a.exquisite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Class: MyAdapter
 * An adapter made to place all list names and their corresponding words into their correct
 * placement in the ExpandableListView.
 */
public class MyAdapter extends BaseExpandableListAdapter {


    private Context context;
    private HashMap<String, List<String>> list_words_map;
    private List<String> list_names;


    public MyAdapter(Context context, List<String> names, HashMap<String, List<String>> childList) {
        this.context = context;
        this.list_names = names;
        this.list_words_map = childList;
    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * Place the List Names into their corresponding positions in the ExpandableListView
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        // Get the name of the current List to be rendered
        String list_name = list_names.get(groupPosition);

        // If the list name layout view has not already been created from the XML then do so now
        if(convertView == null){
            // Convert the list name xml layout to a view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_name, parent, false);
        }

        // Place the List name into the Text
        TextView list_name_text = (TextView) convertView.findViewById(R.id.list_name_text);
        list_name_text.setText(list_name);

        return convertView;

    }



    /**
     * Place the Words into their corresponding positions in the ExpandableListView
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        // Get the name of the current List to be rendered
        String word = (list_words_map.get(list_names.get(groupPosition))).get(childPosition);

        // If the word layout view has not already been created from the XML then do so now
        if(convertView == null){
            // Convert the word xml layout to a view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.word, parent, false);
        }

        // Place the word into the Text
        TextView list_name_text = (TextView) convertView.findViewById(R.id.word_text);
        list_name_text.setText(word);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
