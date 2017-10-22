package com.example.kibb.xpera.damiz.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.kibb.xpera.R;
import com.example.kibb.xpera.damiz.config.Constants;

/**
 * Created by KIBB on 8/30/2017.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar mToolbar;
    private DrawerLayout mDlLayout;
    private NavigationView mNvMenu;
    private ActionBarDrawerToggle mToggle;

    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        switchToBook();
    }
    private void initView(){
        mDlLayout= (DrawerLayout) findViewById(R.id.id_dl_main_layout);
        mNvMenu= (NavigationView) findViewById(R.id.id_nv_menu);
        mToolbar= (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(mToolbar);
        mToggle= new ActionBarDrawerToggle(this,mDlLayout,mToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDlLayout.setDrawerListener(mToggle);
        mToggle.syncState();
        mNvMenu.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed(){
        if (mDlLayout.isDrawerOpen(GravityCompat.START)){
            mDlLayout.closeDrawer(GravityCompat.START);
        }else {
            doExitApp();
        }
    }

    private void doExitApp(){
        if (System.currentTimeMillis() - mExitTime > 2000){
            Snackbar.make(mDlLayout, "click to exit",Snackbar.LENGTH_SHORT).show();
            mExitTime= System.currentTimeMillis();
        }else {
            finish();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id) {
            case R.id.id_nav_book:
                switchToBook();
                break;
            case R.id.id_nav_blog:
                switchToBlog();
                break;
            case R.id.id_nav_explain:
                switchToExplain();
                break;
            case R.id.id_nav_github:
                toGithub();
                break;
        }
        mDlLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    // open system browser to jump to github
    private void toGithub(){
        Uri uri= Uri.parse(Constants.GITHUB_URL);
        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
    private void switchToBook(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_fl_content, new BookFragment()).commit();
        mToolbar.setTitle("Book Information");
    }

    private void switchToExplain(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_fl_content, new ExplainFragment()).commit();
        mToolbar.setTitle("Instruction for Use");
    }
    private void switchToBlog(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_fl_content, new BlogFragment()).commit();
        mToolbar.setTitle("Author Blog");
    }
}
