package com.volvain.yash;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.volvain.yash.DAO.Database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Server.serverUri="https://projectmctibers.appspot.com";
        Database db= new Database(this);
        //TODO if id exists
        super.onCreate(savedInstanceState);
        Server.serverUri=this.getString(R.string.server);

        Log.i("gauravrmsc","a1");

        setContentView(R.layout.activity_main2);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
        Log.i("gaurav",""+1);
        if(db.checkId()){
            Log.i("gaurav","true");
            BackgroundWork.sync();
       if(this.getIntent().hasExtra("fragmentNo")){
            if(this.getIntent().getStringExtra("fragmentNo").equals("NotificationFragment"))
                loadFragment(new notificationsFragment());}
        else loadFragment(new homeFragment());}

        else{ loadFragment(new loginFragment());}

        /*else if(fragmentNo==3){
            loadFragment(new notificationsFragment());
            fragmentNo=1;
        }*/



    }

    private  boolean loadFragment(Fragment fragment){
        if (fragment !=null){
getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment =null;
        switch (menuItem.getItemId()){
            case R.id.navigation_home:
                fragment= new homeFragment();
                break;
            case  R.id.navigation_dashboard:
                fragment=new settingsFragment();
                break;
            case R.id.navigation_notifications:
                fragment=new notificationsFragment();
                break;
            case  R.id.login:
                fragment=new loginFragment();
                break;
        }
        return loadFragment(fragment);
    }

}
