package com.test.testsrc4;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    private TextView lotterytext, descriptiontext;

    //navigator
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        String lotteryNo = getIntent().getStringExtra("LOTTERYNO");
        String description = getIntent().getStringExtra("DESCRIPTION");

        lotterytext = (TextView)findViewById(R.id.lotterytext);
        descriptiontext =(TextView)findViewById(R.id.descriptiontext);

        lotterytext.setText(lotteryNo);
        descriptiontext.setText(description);

        //toggle
        drawerLayout = (DrawerLayout)findViewById(R.id.des);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nv);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case(R.id.nav_home):
                        Intent home = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(home);
                        break;
                    case(R.id.nav_lots):
                        Intent lots = new Intent(getApplicationContext(),Main2Activity.class);
                        startActivity(lots);
                        break;
                    case(R.id.nav_web):
                        Intent web = new Intent(getApplicationContext(),WebPageActivity.class);
                        startActivity(web);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
