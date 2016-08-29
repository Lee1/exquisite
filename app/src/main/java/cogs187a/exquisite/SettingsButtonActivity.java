package cogs187a.exquisite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

public class SettingsButtonActivity extends AppCompatActivity {

    public ImageButton plus;
    public ImageButton settings;

    public void init(){
        plus = (ImageButton)findViewById(R.id.plusButton);
        settings = (ImageButton)findViewById(R.id.settingsButton);

        // Links OnClickListeners to buttons
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(SettingsButtonActivity.this, plus);
                popup.getMenuInflater().inflate(R.menu.plus_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if(R.id.one == item.getItemId()) {

                        } else {

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
                Intent settingsIntent = new Intent(SettingsButtonActivity.this,SettingsButtonActivity.class);
                startActivity(settingsIntent);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_button);

        init();

    }
}
