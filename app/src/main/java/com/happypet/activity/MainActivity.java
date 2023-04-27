package com.happypet.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.happypet.fragments.PetStoreOrderingFragment;
import com.happypet.R;
import com.happypet.fragments.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    /** Step 1: create a reference to the DrawerLayout */
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;
    private MaterialToolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Step 2: Get the DrawerLayout object from the layout XML file */
        mDrawerLayout = findViewById(R.id.nav_drawer_layout);

        /** Step 3: Get the NavigationView object from the layout SML file */
        mNavigationView = findViewById(R.id.nav_view);
        mToolBar = findViewById(R.id.topAppBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        /** Step 4: Set the listener for the NvigationView. The Main Activity shuould
         * imeplement the interface NavigationView.OnNavigationItemSelectedListener */
        mNavigationView.setNavigationItemSelectedListener(this);

        /** Step 5: Set up the ActionBarDrawerToggle */
        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this, // Activity / Context
                mDrawerLayout, // DrawerLayout
                R.string.navigation_drawer_open, // String to open
                R.string.navigation_drawer_close // String to close
        );
        /** Step 6: Include the mActionBarDrawerToggle as the listener to the DrawerLayout.
         *  The synchState() method is used to synchronize the state of the navigation drawer */
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        /** Step 7:Set the default fragment to the HomeFragment */
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment()).commit();
    }
    /** After opening the navigation drawer, when an item is selected, the fragment_container is
     * replaced with the corresponding fragment, either Home for PetStoreOrderingFragment*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {// Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_order:
                Log.d("Order Navigation Item", "selected");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PetStoreOrderingFragment()).commit();
                break;
            case R.id.nav_home:
                Log.d("Home Navigation Item", "selected");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            default:
                break;
        }

        /** Close the navigation drawer */
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /** Inflate the menu; this adds items to the action bar if it is present. */
        getMenuInflater().inflate(R.menu.nav_drawer_items, menu);
        return true;
    }
    /** Controls configuration change of drawer toggle between open and close*/
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
    //syncs the open/closed state of the navigation drawer
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }
}