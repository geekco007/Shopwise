package com.geekco.shopwise.shopwise;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    private ViewPager mViewPager;
    private SectionPageAdapter mSectionPageAdapter;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            Toast.makeText(this," logined",Toast.LENGTH_SHORT).show();
        } else {

            Intent startpage= new Intent(MainActivity.this,StartActivity.class );
            startActivity(startpage);
        }
        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("ShopWise");

        mViewPager = (ViewPager)findViewById(R.id.tabpager);
        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionPageAdapter);
        mTabLayout = (TabLayout) findViewById(R.id.main_tab);
        mTabLayout.setupWithViewPager(mViewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()== R.id.main_logout)
        {
            FirebaseAuth.getInstance().signOut();
            Intent startpage= new Intent(MainActivity.this,StartActivity.class );
            startActivity(startpage);
        }

        if(item.getItemId()== R.id.main_profile)
        {

            Intent profile= new Intent(MainActivity.this,ProfileActivity.class );
            startActivity(profile);
        }

        return true;
    }
}
