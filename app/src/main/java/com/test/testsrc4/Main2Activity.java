package com.test.testsrc4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private RecyclerView rvLottery;
    private LotteryAdapter lottery_adapter;
    private List<LotteryModel> lotteryModelList;
    private DatabaseHelper mDBHelper;

    //navigator
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rvLottery=(RecyclerView) findViewById(R.id.rvLottery);
        rvLottery.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mDBHelper=new DatabaseHelper(this);

        File database=getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(database.exists() == false){
            mDBHelper.getReadableDatabase();
            if(copyDatabase(this)){
                Toast.makeText(getApplicationContext(),"Copy success",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(),"Copy failed",Toast.LENGTH_LONG).show();
                return;
            }
        }

        lotteryModelList =mDBHelper.getListLottery("");

        lottery_adapter =new LotteryAdapter();
        lottery_adapter.setData(lotteryModelList);
        rvLottery.setAdapter(lottery_adapter);

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchLottery(s);
                return false;
            }
        });

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


    private void searchLottery(String lotSearch){
        lotteryModelList.clear();
        lotteryModelList = mDBHelper.getListLottery(lotSearch);
        lottery_adapter.setData(lotteryModelList);
        rvLottery.setAdapter(lottery_adapter);
    }

    private boolean copyDatabase(Context context){
        try{
            InputStream inputStream=context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName=DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream=new FileOutputStream(outFileName);
            byte[] buff=new byte[1024];
            int length=0;
            while ((length=inputStream.read(buff)) >0){
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("Database","Copy Success");
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //Select Menu Web --> webview

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.nav_web){
//            Intent intent = new Intent(Main2Activity.this, WebPageActivity.class);
//            startActivity(intent);
//            return true;
//        } if(id == R.id.nav_home){
//            Intent intent = new Intent(Main2Activity.this,MainActivity.class);
//            startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }
}

