package com.test.testsrc4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebPageActivity extends AppCompatActivity {

    private WebView webView;
    //navigator
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);

        getSupportActionBar().setTitle("WebSite");
        getSupportActionBar().setSubtitle("http://lotto.mthai.com/");

        webView = (WebView)findViewById(R.id.webView);
        webView.loadUrl("http://lotto.mthai.com/lottery/result-4341.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        Toast.makeText(getApplicationContext(),"Web Page",Toast.LENGTH_SHORT).show();


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
